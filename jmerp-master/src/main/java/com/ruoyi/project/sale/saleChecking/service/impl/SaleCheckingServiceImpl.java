package com.ruoyi.project.sale.saleChecking.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configCustomer.mapper.ConfigCustomerMapper;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.config.configSupplier.mapper.ConfigSupplierMapper;
import com.ruoyi.project.sale.saleCheckingProduct.domain.SaleCheckingProduct;
import com.ruoyi.project.sale.saleCheckingProduct.mapper.SaleCheckingProductMapper;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.mapper.SaleDeliveryMapper;
import com.ruoyi.project.sale.saleDeliveryProduct.domain.SaleDeliveryProduct;
import com.ruoyi.project.sale.saleDeliveryProduct.mapper.SaleDeliveryProductMapper;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturn;
import com.ruoyi.project.sale.saleReturnProduct.domain.SaleReturnProduct;
import com.ruoyi.project.sale.saleReturnProduct.mapper.SaleReturnProductMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleChecking.mapper.SaleCheckingMapper;
import com.ruoyi.project.sale.saleChecking.domain.SaleChecking;
import com.ruoyi.project.sale.saleChecking.service.ISaleCheckingService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 销售对账Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleCheckingServiceImpl implements ISaleCheckingService 
{
    @Autowired
    private SaleCheckingMapper saleCheckingMapper;
    @Autowired
    private SaleCheckingProductMapper saleCheckingProductMapper;
    @Autowired
    private SaleDeliveryProductMapper saleDeliveryProductMapper;
    @Autowired
    private SaleReturnProductMapper saleReturnProductMapper;
    @Autowired
    private SaleDeliveryMapper saleDeliveryMapper;
    @Autowired
    private ConfigSupplierMapper configSupplierMapper;
    @Autowired
    private ConfigCustomerMapper configCustomerMapper;

    /**
     * 查询销售对账
     * 
     * @param id 销售对账ID
     * @return 销售对账
     */
    @Override
    public SaleChecking selectSaleCheckingById(Long id)
    {
        return saleCheckingMapper.selectSaleCheckingById(id);
    }

    /**
     * 查询销售对账列表
     * 
     * @param saleChecking 销售对账
     * @return 销售对账
     */
    @Override
    public List<SaleChecking> selectSaleCheckingList(SaleChecking saleChecking)
    {
        return saleCheckingMapper.selectSaleCheckingList(saleChecking);
    }

    /**
     * 新增销售对账
     * 
     * @param saleChecking 销售对账
     * @return 结果
     */
    @Override
    public int insertSaleChecking(SaleChecking saleChecking)
    {
        saleChecking.setCreateBy(ShiroUtils.getSysUser().getUserName());
        saleChecking.setCreateTime(DateUtils.getNowDate());
        saleChecking.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        saleChecking.setUpdateTime(DateUtils.getNowDate());
        if(null==saleChecking.getCheckingDate()){
            saleChecking.setCheckingDate(DateUtils.getNowDate());
        }
        return saleCheckingMapper.insertSaleChecking(saleChecking);
    }

    /**
     * 修改销售对账
     * 
     * @param saleChecking 销售对账
     * @return 结果
     */
    @Override
    public int updateSaleChecking(SaleChecking saleChecking)
    {
        saleChecking.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        saleChecking.setUpdateTime(DateUtils.getNowDate());
        return saleCheckingMapper.updateSaleChecking(saleChecking);
    }

    /**
     * 删除销售对账对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleCheckingByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            SaleChecking param = saleCheckingMapper.selectSaleCheckingById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = saleCheckingMapper.deleteSaleCheckingById(Long.parseLong(arr[i]));
                saleCheckingMapper.deleteSaleCheckingProductById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = saleCheckingMapper.updateSaleChecking(param);
            }
        }
        return result;
        //return saleCheckingMapper.deleteSaleCheckingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售对账信息
     * 
     * @param id 销售对账ID
     * @return 结果
     */
    @Override
    public int deleteSaleCheckingById(Long id)
    {
        int result = 0;
        SaleChecking param = saleCheckingMapper.selectSaleCheckingById(id);
        if("draft".equals(param.getStatus())){
            result = saleCheckingMapper.deleteSaleCheckingById(id);
            saleCheckingMapper.deleteSaleCheckingProductById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = saleCheckingMapper.updateSaleChecking(param);
        }
        return result;
        //return saleCheckingMapper.deleteSaleCheckingById(id);
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public SaleChecking createSaleChecking(SaleChecking saleChecking){
        String[] arr = Convert.toStrArray(saleChecking.getIds());
        Long headerId = 0L;
        BigDecimal taxRate = new BigDecimal(0);
        for (int i=0;i<arr.length;i++){
            Long materialsLineId = Long.parseLong(arr[i]);
            SaleDeliveryProduct saleDeliveryProduct = saleDeliveryProductMapper.selectSaleDeliveryProductById(materialsLineId);
            SaleDelivery saleDelivery = saleDeliveryMapper.selectSaleDeliveryById(saleDeliveryProduct.getSaleDeliveryId());
            if(!StringUtils.isEmpty(saleChecking.getRzyValue2())&&"A".equals(saleChecking.getRzyValue2())){
                headerId = saleChecking.getId();
                taxRate = saleDelivery.getTaxRate();
            }else{
                if(i==0){
                    //insert头
                    SaleChecking headerVO = new SaleChecking();
                    BeanUtils.copyProperties(saleDelivery, headerVO);
                    headerVO.setCheckingDate(DateUtils.getNowDate());
                    headerVO.setStatus("draft");
                    headerVO.setAmount(new BigDecimal(0));
                    insertSaleChecking(headerVO);
                    headerId = headerVO.getId();
                }
            }
            //insert行
            SaleCheckingProduct saleCheckingProduct = new SaleCheckingProduct();
            BeanUtils.copyProperties(saleDeliveryProduct, saleCheckingProduct);
            saleCheckingProduct.setSaleDeliveryProductId(materialsLineId);
            saleCheckingProduct.setSaleCheckingId(headerId);
            saleCheckingProductMapper.insertSaleCheckingProduct(saleCheckingProduct);
        }
        saleChecking.setId(headerId);
        return calculateAmount(saleChecking);
    }


    /**
     * 计算金额
     * @Author 方舟
     * @Date 2021/5/27 14:45:12
     **/
    @Override
    public SaleChecking calculateAmount(SaleChecking saleChecking){
        Long saleCheckingId = saleChecking.getId();
        BigDecimal deliveryAmount = new BigDecimal(0);
        BigDecimal returnAmount = new BigDecimal(0);
        BigDecimal amount = new BigDecimal(0);
        SaleDeliveryProduct paramDeliveryVO = new SaleDeliveryProduct();
        paramDeliveryVO.setSaleCheckingId(saleCheckingId);
        List<SaleDeliveryProduct> dmlist = saleDeliveryProductMapper.selectSaleDeliveryProductList(paramDeliveryVO);
        for (int i=0;i<dmlist.size();i++){
            deliveryAmount = deliveryAmount.add(dmlist.get(i).getAmount());
        }
        SaleReturnProduct paramReturnVO = new SaleReturnProduct();
        paramReturnVO.setSaleCheckingId(saleCheckingId);
        List<SaleReturnProduct> rmlist = saleReturnProductMapper.selectSaleReturnProductList(paramReturnVO);
        for (int i=0;i<rmlist.size();i++){
            returnAmount = returnAmount.add(rmlist.get(i).getAmount());
        }
        amount = deliveryAmount.subtract(returnAmount);
        SaleChecking saveVO = new SaleChecking();
        saveVO.setId(saleCheckingId);
        saveVO.setDeliveryAmount(deliveryAmount);
        saveVO.setReturnAmount(returnAmount);
        saveVO.setAmount(amount);
        updateSaleChecking(saveVO);
        return saveVO;
    }
}
