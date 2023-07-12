package com.ruoyi.project.sale.saleDelivery.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrder;
import com.ruoyi.project.outsource.outsourceOrderMaterials.domain.OutsourceOrderMaterials;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDeliveryExport;
import com.ruoyi.project.sale.saleDeliveryProduct.domain.SaleDeliveryProduct;
import com.ruoyi.project.sale.saleDeliveryProduct.mapper.SaleDeliveryProductMapper;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleChecking.domain.SaleChecking;
import com.ruoyi.project.sale.saleOrder.mapper.SaleOrderMapper;
import com.ruoyi.project.sale.saleOrderDelivery.domain.SaleOrderDelivery;
import com.ruoyi.project.sale.saleOrderDelivery.mapper.SaleOrderDeliveryMapper;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleOrderProduct.mapper.SaleOrderProductMapper;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleDelivery.mapper.SaleDeliveryMapper;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.service.ISaleDeliveryService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 送货单Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleDeliveryServiceImpl implements ISaleDeliveryService 
{
    @Autowired
    private SaleDeliveryMapper saleDeliveryMapper;
    @Autowired
    private SaleDeliveryProductMapper saleDeliveryProductMapper;
    @Autowired
    private SaleOrderProductMapper saleOrderProductMapper;
    @Autowired
    private SaleOrderDeliveryMapper saleOrderDeliveryMapper;
    @Autowired
    private SaleOrderMapper saleOrderMapper;
    @Autowired
    private IWarehouseRecordService warehouseRecordService;

    /**
     * 查询送货单
     * 
     * @param id 送货单ID
     * @return 送货单
     */
    @Override
    public SaleDelivery selectSaleDeliveryById(Long id)
    {
        return saleDeliveryMapper.selectSaleDeliveryById(id);
    }

    /**
     * 查询送货单列表
     * 
     * @param saleDelivery 送货单
     * @return 送货单
     */
    @Override
    public List<SaleDelivery> selectSaleDeliveryList(SaleDelivery saleDelivery)
    {
        return saleDeliveryMapper.selectSaleDeliveryList(saleDelivery);
    }
    @Override
    public List<SaleDeliveryExport> selectSaleDeliveryExportList(SaleDelivery saleDelivery)
    {
        return saleDeliveryMapper.selectSaleDeliveryExportList(saleDelivery);
    }

    /**
     * 新增送货单
     * 
     * @param saleDelivery 送货单
     * @return 结果
     */
    @Override
    public int insertSaleDelivery(SaleDelivery saleDelivery)
    {
        saleDelivery.setCreateBy(ShiroUtils.getSysUser().getUserName());
        saleDelivery.setCreateTime(DateUtils.getNowDate());
        saleDelivery.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        saleDelivery.setUpdateTime(DateUtils.getNowDate());
        return saleDeliveryMapper.insertSaleDelivery(saleDelivery);
    }

    /**
     * 修改送货单
     * 
     * @param saleDelivery 送货单
     * @return 结果
     */
    @Override
    public int updateSaleDelivery(SaleDelivery saleDelivery)
    {
        saleDelivery.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        saleDelivery.setUpdateTime(DateUtils.getNowDate());
        warehouseChange(saleDelivery);
        return saleDeliveryMapper.updateSaleDelivery(saleDelivery);
    }

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 16:45:19
     **/
    private void warehouseChange(SaleDelivery saleDelivery){
        String editType = saleDelivery.getRzyValue1();
        if(!StringUtils.isEmpty(editType)){
            SaleDelivery deliveryVO = saleDeliveryMapper.selectSaleDeliveryById(saleDelivery.getId());
            SaleDeliveryProduct saleDeliveryProduct = new SaleDeliveryProduct();
            saleDeliveryProduct.setSaleDeliveryId(saleDelivery.getId());
            List<SaleDeliveryProduct> list = saleDeliveryProductMapper.selectSaleDeliveryProductList(saleDeliveryProduct);
            for (int i=0;i<list.size();i++){
                SaleDeliveryProduct product = list.get(i);
                if("approve".equals(editType)){
                    //出库
                    warehouseRecordService.productInbound("XSSH",product.getWarehouseId(),(0-product.getQty()),product.getProductId(),"");
                }else if("unapprove".equals(editType)){
                    //入库
                    warehouseRecordService.productInbound("XSSH",product.getWarehouseId(),product.getQty(),product.getProductId(),"反审退回");
                }
            }
        }
    }

    /**
     * 删除送货单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleDeliveryByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            SaleDelivery param = saleDeliveryMapper.selectSaleDeliveryById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = saleDeliveryMapper.deleteSaleDeliveryById(Long.parseLong(arr[i]));
                saleDeliveryMapper.deleteSaleDeliveryProductById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = saleDeliveryMapper.updateSaleDelivery(param);
            }
        }
        return result;
        //return saleDeliveryMapper.deleteSaleDeliveryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除送货单信息
     * 
     * @param id 送货单ID
     * @return 结果
     */
    @Override
    public int deleteSaleDeliveryById(Long id)
    {
        int result = 0;
        SaleDelivery param = saleDeliveryMapper.selectSaleDeliveryById(id);
        if("draft".equals(param.getStatus())){
            result = saleDeliveryMapper.deleteSaleDeliveryById(id);
            saleDeliveryMapper.deleteSaleDeliveryProductById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = saleDeliveryMapper.updateSaleDelivery(param);
        }
        return result;
        //return saleDeliveryMapper.deleteSaleDeliveryById(id);
    }

    /**
     * 创建外发到货
     */
    @Override
    public SaleDelivery createSaleDelivery(SaleDelivery saleDelivery){
        String[] ids = Convert.toStrArray(saleDelivery.getIds());
        Long headerId = 0L;
        for (int i=0;i<ids.length;i++){
            Long saleOrderDeliveryId = Long.parseLong(ids[i]);
            SaleOrderDelivery saleOrderDelivery = saleOrderDeliveryMapper.selectSaleOrderDeliveryById(saleOrderDeliveryId);
            SaleOrderProduct saleOrderProduct = saleOrderProductMapper.selectSaleOrderProductById(saleOrderDelivery.getSaleOrderProductId());
            SaleOrder saleOrder = saleOrderMapper.selectSaleOrderById(saleOrderDelivery.getSaleOrderId());
            if(!StringUtils.isEmpty(saleDelivery.getRzyValue2())&&"A".equals(saleDelivery.getRzyValue2())){
                headerId = saleDelivery.getId();
            }else if(i==0){
                SaleDelivery headerVO = new SaleDelivery();
                BeanUtils.copyProperties(saleOrder, headerVO);
                headerVO.setStatus("draft");
                headerVO.setAmount(new BigDecimal(0));
                headerVO.setDeliveryDate(DateUtils.getNowDate());
                insertSaleDelivery(headerVO);
                headerId = headerVO.getId();
            }
            SaleDeliveryProduct saleDeliveryProduct = new SaleDeliveryProduct();
            BeanUtils.copyProperties(saleOrderProduct, saleDeliveryProduct);
            Integer qty = saleOrderDelivery.getQty() - saleOrderDelivery.getSendQty();
            BigDecimal price = saleOrderProduct.getPrice();
            saleDeliveryProduct.setSaleDeliveryId(headerId);
            saleDeliveryProduct.setSaleOrderDeliveryId(saleOrderDeliveryId);
            saleDeliveryProduct.setCustomerId(saleOrder.getCustomerId());
            saleDeliveryProduct.setQty(qty);
            saleDeliveryProduct.setDeliveryDate(DateUtils.getNowDate());
            saleDeliveryProduct.setAmount(price.multiply(new BigDecimal(qty)));
            saleDeliveryProductMapper.insertSaleDeliveryProduct(saleDeliveryProduct);
        }
        saleDelivery.setId(headerId);
        return saleDelivery;
    }

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @Override
    public SaleDelivery countAmount(SaleDelivery saleDelivery){
        SaleDeliveryProduct saleDeliveryProduct = new SaleDeliveryProduct();
        saleDeliveryProduct.setSaleDeliveryId(saleDelivery.getId());
        BigDecimal amount = new BigDecimal(0);
        List<SaleDeliveryProduct> list = saleDeliveryProductMapper.selectSaleDeliveryProductList(saleDeliveryProduct);
        for (int i=0;i<list.size();i++){
            amount = amount.add(list.get(i).getAmount());
        }
        saleDelivery.setAmount(amount);
        saleDeliveryMapper.updateSaleDelivery(saleDelivery);
        return saleDelivery;
    }
}
