package com.ruoyi.project.sale.saleReturnProduct.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleReturnProduct.mapper.SaleReturnProductMapper;
import com.ruoyi.project.sale.saleReturnProduct.domain.SaleReturnProduct;
import com.ruoyi.project.sale.saleReturnProduct.service.ISaleReturnProductService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 退货产品Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleReturnProductServiceImpl implements ISaleReturnProductService 
{
    @Autowired
    private SaleReturnProductMapper saleReturnProductMapper;

    /**
     * 查询退货产品
     * 
     * @param id 退货产品ID
     * @return 退货产品
     */
    @Override
    public SaleReturnProduct selectSaleReturnProductById(Long id)
    {
        return saleReturnProductMapper.selectSaleReturnProductById(id);
    }

    /**
     * 查询退货产品列表
     * 
     * @param saleReturnProduct 退货产品
     * @return 退货产品
     */
    @Override
    public List<SaleReturnProduct> selectSaleReturnProductList(SaleReturnProduct saleReturnProduct)
    {
        return saleReturnProductMapper.selectSaleReturnProductList(saleReturnProduct);
    }

    /**
     * 新增退货产品
     * 
     * @param saleReturnProduct 退货产品
     * @return 结果
     */
    @Override
    public int insertSaleReturnProduct(SaleReturnProduct saleReturnProduct)
    {
        return saleReturnProductMapper.insertSaleReturnProduct(saleReturnProduct);
    }

    /**
     * 修改退货产品
     * 
     * @param saleReturnProduct 退货产品
     * @return 结果
     */
    @Override
    public int updateSaleReturnProduct(SaleReturnProduct saleReturnProduct)
    {
        return saleReturnProductMapper.updateSaleReturnProduct(saleReturnProduct);
    }

    /**
     * 删除退货产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleReturnProductByIds(String ids)
    {
        return saleReturnProductMapper.deleteSaleReturnProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退货产品信息
     * 
     * @param id 退货产品ID
     * @return 结果
     */
    @Override
    public int deleteSaleReturnProductById(Long id)
    {
        return saleReturnProductMapper.deleteSaleReturnProductById(id);
    }
}
