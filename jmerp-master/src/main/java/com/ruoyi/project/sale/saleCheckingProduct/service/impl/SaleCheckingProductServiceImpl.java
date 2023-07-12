package com.ruoyi.project.sale.saleCheckingProduct.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleCheckingProduct.mapper.SaleCheckingProductMapper;
import com.ruoyi.project.sale.saleCheckingProduct.domain.SaleCheckingProduct;
import com.ruoyi.project.sale.saleCheckingProduct.service.ISaleCheckingProductService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 送货产品对账Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleCheckingProductServiceImpl implements ISaleCheckingProductService 
{
    @Autowired
    private SaleCheckingProductMapper saleCheckingProductMapper;

    /**
     * 查询送货产品对账
     * 
     * @param id 送货产品对账ID
     * @return 送货产品对账
     */
    @Override
    public SaleCheckingProduct selectSaleCheckingProductById(Long id)
    {
        return saleCheckingProductMapper.selectSaleCheckingProductById(id);
    }

    /**
     * 查询送货产品对账列表
     * 
     * @param saleCheckingProduct 送货产品对账
     * @return 送货产品对账
     */
    @Override
    public List<SaleCheckingProduct> selectSaleCheckingProductList(SaleCheckingProduct saleCheckingProduct)
    {
        return saleCheckingProductMapper.selectSaleCheckingProductList(saleCheckingProduct);
    }

    /**
     * 新增送货产品对账
     * 
     * @param saleCheckingProduct 送货产品对账
     * @return 结果
     */
    @Override
    public int insertSaleCheckingProduct(SaleCheckingProduct saleCheckingProduct)
    {
        return saleCheckingProductMapper.insertSaleCheckingProduct(saleCheckingProduct);
    }

    /**
     * 修改送货产品对账
     * 
     * @param saleCheckingProduct 送货产品对账
     * @return 结果
     */
    @Override
    public int updateSaleCheckingProduct(SaleCheckingProduct saleCheckingProduct)
    {
        return saleCheckingProductMapper.updateSaleCheckingProduct(saleCheckingProduct);
    }

    /**
     * 删除送货产品对账对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleCheckingProductByIds(String ids)
    {
        return saleCheckingProductMapper.deleteSaleCheckingProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除送货产品对账信息
     * 
     * @param id 送货产品对账ID
     * @return 结果
     */
    @Override
    public int deleteSaleCheckingProductById(Long id)
    {
        return saleCheckingProductMapper.deleteSaleCheckingProductById(id);
    }
}
