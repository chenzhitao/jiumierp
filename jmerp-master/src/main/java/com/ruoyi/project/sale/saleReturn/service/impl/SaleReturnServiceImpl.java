package com.ruoyi.project.sale.saleReturn.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturn;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.mapper.SaleDeliveryMapper;
import com.ruoyi.project.sale.saleDeliveryProduct.domain.SaleDeliveryProduct;
import com.ruoyi.project.sale.saleDeliveryProduct.mapper.SaleDeliveryProductMapper;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrder.mapper.SaleOrderMapper;
import com.ruoyi.project.sale.saleOrderDelivery.domain.SaleOrderDelivery;
import com.ruoyi.project.sale.saleOrderDelivery.mapper.SaleOrderDeliveryMapper;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleOrderProduct.mapper.SaleOrderProductMapper;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturnExport;
import com.ruoyi.project.sale.saleReturnProduct.domain.SaleReturnProduct;
import com.ruoyi.project.sale.saleReturnProduct.mapper.SaleReturnProductMapper;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleReturn.mapper.SaleReturnMapper;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturn;
import com.ruoyi.project.sale.saleReturn.service.ISaleReturnService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 退货单Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleReturnServiceImpl implements ISaleReturnService 
{
    @Autowired
    private SaleReturnMapper saleReturnMapper;
    @Autowired
    private SaleReturnProductMapper saleReturnProductMapper;
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
     * 查询退货单
     * 
     * @param id 退货单ID
     * @return 退货单
     */
    @Override
    public SaleReturn selectSaleReturnById(Long id)
    {
        return saleReturnMapper.selectSaleReturnById(id);
    }

    /**
     * 查询退货单列表
     * 
     * @param saleReturn 退货单
     * @return 退货单
     */
    @Override
    public List<SaleReturn> selectSaleReturnList(SaleReturn saleReturn)
    {
        return saleReturnMapper.selectSaleReturnList(saleReturn);
    }
    @Override
    public List<SaleReturnExport> selectSaleReturnExportList(SaleReturn saleReturn)
    {
        return saleReturnMapper.selectSaleReturnExportList(saleReturn);
    }

    /**
     * 新增退货单
     * 
     * @param saleReturn 退货单
     * @return 结果
     */
    @Override
    public int insertSaleReturn(SaleReturn saleReturn)
    {
        saleReturn.setCreateBy(ShiroUtils.getSysUser().getUserName());
        saleReturn.setCreateTime(DateUtils.getNowDate());
        saleReturn.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        saleReturn.setUpdateTime(DateUtils.getNowDate());
        return saleReturnMapper.insertSaleReturn(saleReturn);
    }

    /**
     * 修改退货单
     * 
     * @param saleReturn 退货单
     * @return 结果
     */
    @Override
    public int updateSaleReturn(SaleReturn saleReturn)
    {
        saleReturn.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        saleReturn.setUpdateTime(DateUtils.getNowDate());
        changeStatus(saleReturn);
        warehouseChange(saleReturn);
        return saleReturnMapper.updateSaleReturn(saleReturn);
    }

    /**
     * 删除退货单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleReturnByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            SaleReturn param = saleReturnMapper.selectSaleReturnById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = saleReturnMapper.deleteSaleReturnById(Long.parseLong(arr[i]));
                saleReturnMapper.deleteSaleReturnProductById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = saleReturnMapper.updateSaleReturn(param);
            }
        }
        return result;
        //return saleReturnMapper.deleteSaleReturnByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退货单信息
     * 
     * @param id 退货单ID
     * @return 结果
     */
    @Override
    public int deleteSaleReturnById(Long id)
    {
        int result = 0;
        SaleReturn param = saleReturnMapper.selectSaleReturnById(id);
        if("draft".equals(param.getStatus())){
            result = saleReturnMapper.deleteSaleReturnById(id);
            saleReturnMapper.deleteSaleReturnProductById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = saleReturnMapper.updateSaleReturn(param);
        }
        return result;
        //return saleReturnMapper.deleteSaleReturnById(id);
    }

    /**
     * 创建外发到货
     */
    @Override
    public SaleReturn createSaleReturn(SaleReturn saleReturn){
        String[] ids = Convert.toStrArray(saleReturn.getIds());
        Long headerId = 0L;
        for (int i=0;i<ids.length;i++){
            Long saleDeliveryProductId = Long.parseLong(ids[i]);
            SaleDeliveryProduct saleDeliveryProduct = saleDeliveryProductMapper.selectSaleDeliveryProductById(saleDeliveryProductId);
            SaleDelivery saleDelivery = saleDeliveryMapper.selectSaleDeliveryById(saleDeliveryProduct.getSaleDeliveryId());
            if(!StringUtils.isEmpty(saleReturn.getRzyValue2())&&"A".equals(saleReturn.getRzyValue2())){
                headerId = saleReturn.getId();
            }else if(i==0){
                SaleReturn headerVO = new SaleReturn();
                BeanUtils.copyProperties(saleDelivery, headerVO);
                headerVO.setStatus("draft");
                headerVO.setAmount(new BigDecimal(0));
                headerVO.setReturnDate(DateUtils.getNowDate());
                insertSaleReturn(headerVO);
                headerId = headerVO.getId();
            }
            SaleReturnProduct saleReturnProduct = new SaleReturnProduct();
            BeanUtils.copyProperties(saleDeliveryProduct, saleReturnProduct);
            saleReturnProduct.setSaleReturnId(headerId);
            saleReturnProduct.setSaleDeliveryProductId(saleDeliveryProductId);
            saleReturnProduct.setCustomerId(saleDelivery.getCustomerId());
            saleReturnProduct.setQty(0);
            saleReturnProduct.setReturnType("THKK");
            saleReturnProduct.setReturnDate(DateUtils.getNowDate());
            saleReturnProduct.setAmount(new BigDecimal(0));
            saleReturnProductMapper.insertSaleReturnProduct(saleReturnProduct);
        }
        saleReturn.setId(headerId);
        return saleReturn;
    }

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @Override
    public SaleReturn countAmount(SaleReturn saleReturn){
        SaleReturnProduct saleReturnProduct = new SaleReturnProduct();
        saleReturnProduct.setSaleReturnId(saleReturn.getId());
        BigDecimal amount = new BigDecimal(0);
        List<SaleReturnProduct> list = saleReturnProductMapper.selectSaleReturnProductList(saleReturnProduct);
        for (int i=0;i<list.size();i++){
            amount = amount.add(list.get(i).getAmount());
        }
        saleReturn.setAmount(amount);
        saleReturnMapper.updateSaleReturn(saleReturn);
        return saleReturn;
    }

    /**
     * 申请时处理
     * @Author 方舟
     * @Date 2021/5/21 10:37:00
     **/
    private void changeStatus(SaleReturn saleReturn){
        String editType = saleReturn.getRzyValue1();
        SaleReturnProduct saleReturnProduct = new SaleReturnProduct();
        saleReturnProduct.setSaleReturnId(saleReturn.getId());
        List<SaleReturnProduct> list = saleReturnProductMapper.selectSaleReturnProductList(saleReturnProduct);
        for (int i=0;i<list.size();i++){
            SaleReturnProduct productVO = list.get(i);
            SaleDeliveryProduct odpVO = saleDeliveryProductMapper.selectSaleDeliveryProductById(productVO.getSaleDeliveryProductId());
            if(!StringUtils.isEmpty(editType)){
                //只有退货补数才有货物库存数量变化,不涉及金额
                if("THBS".equals(productVO.getReturnType())){
                    Integer minusQty = 0;
                    if("approve".equals(editType)){
                        //对应的到货+数量
                        minusQty = odpVO.getQty() - productVO.getQty();
                    }else if("unapprove".equals(editType)){
                        //对应的到货-数量
                        minusQty = odpVO.getQty() + productVO.getQty();
                    }
                    odpVO.setQty(minusQty);
                    Integer returnQty = 0;
                    if("approve".equals(editType)){
                        //对应的到货+数量
                        returnQty = odpVO.getReturnQty()+productVO.getQty();
                    }else if("unapprove".equals(editType)){
                        //对应的到货-数量
                        returnQty = odpVO.getReturnQty()-productVO.getQty();
                    }
                    odpVO.setReturnQty(returnQty);
                    saleDeliveryProductMapper.updateSaleDeliveryProduct(odpVO);
                }

            }
        }
    }

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 16:45:19
     **/
    private void warehouseChange(SaleReturn saleReturn){
        String editType = saleReturn.getRzyValue1();
        if(!StringUtils.isEmpty(editType)){
            SaleReturnProduct saleReturnProduct = new SaleReturnProduct();
            saleReturnProduct.setSaleReturnId(saleReturn.getId());
            List<SaleReturnProduct> list = saleReturnProductMapper.selectSaleReturnProductList(saleReturnProduct);
            for (int i=0;i<list.size();i++){
                SaleReturnProduct product = list.get(i);
                //只有退货补数才有货物库存数量变化,不涉及金额
                if("THBS".equals(product.getReturnType())){
                    if("approve".equals(editType)){
                        //出库
                        warehouseRecordService.productInbound("XSTH",product.getWarehouseId(),product.getQty(),product.getProductId(),"");
                    }else if("unapprove".equals(editType)){
                        //入库
                        warehouseRecordService.productInbound("XSTH",product.getWarehouseId(),0-product.getQty(),product.getProductId(),"反审退回");
                    }
                }

            }
        }
    }
}
