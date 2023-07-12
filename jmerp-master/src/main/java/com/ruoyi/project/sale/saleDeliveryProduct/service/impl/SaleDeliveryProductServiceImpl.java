package com.ruoyi.project.sale.saleDeliveryProduct.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleDeliveryProduct.mapper.SaleDeliveryProductMapper;
import com.ruoyi.project.sale.saleDeliveryProduct.domain.SaleDeliveryProduct;
import com.ruoyi.project.sale.saleDeliveryProduct.service.ISaleDeliveryProductService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 送货产品Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleDeliveryProductServiceImpl implements ISaleDeliveryProductService 
{
    @Autowired
    private SaleDeliveryProductMapper saleDeliveryProductMapper;

    /**
     * 查询送货产品
     * 
     * @param id 送货产品ID
     * @return 送货产品
     */
    @Override
    public SaleDeliveryProduct selectSaleDeliveryProductById(Long id)
    {
        return saleDeliveryProductMapper.selectSaleDeliveryProductById(id);
    }

    /**
     * 查询送货产品列表
     * 
     * @param saleDeliveryProduct 送货产品
     * @return 送货产品
     */
    @Override
    public List<SaleDeliveryProduct> selectSaleDeliveryProductList(SaleDeliveryProduct saleDeliveryProduct)
    {
        if(null!=saleDeliveryProduct.getCustomerId()&&saleDeliveryProduct.getCustomerId().equals(-1L)){
            saleDeliveryProduct.setCustomerId(null);
        }
        return saleDeliveryProductMapper.selectSaleDeliveryProductList(saleDeliveryProduct);
    }

    /**
     * 新增送货产品
     * 
     * @param saleDeliveryProduct 送货产品
     * @return 结果
     */
    @Override
    public int insertSaleDeliveryProduct(SaleDeliveryProduct saleDeliveryProduct)
    {
        return saleDeliveryProductMapper.insertSaleDeliveryProduct(saleDeliveryProduct);
    }

    /**
     * 修改送货产品
     * 
     * @param saleDeliveryProduct 送货产品
     * @return 结果
     */
    @Override
    public int updateSaleDeliveryProduct(SaleDeliveryProduct saleDeliveryProduct)
    {
        return saleDeliveryProductMapper.updateSaleDeliveryProduct(saleDeliveryProduct);
    }

    /**
     * 删除送货产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleDeliveryProductByIds(String ids)
    {
        return saleDeliveryProductMapper.deleteSaleDeliveryProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除送货产品信息
     * 
     * @param id 送货产品ID
     * @return 结果
     */
    @Override
    public int deleteSaleDeliveryProductById(Long id)
    {
        return saleDeliveryProductMapper.deleteSaleDeliveryProductById(id);
    }
}
