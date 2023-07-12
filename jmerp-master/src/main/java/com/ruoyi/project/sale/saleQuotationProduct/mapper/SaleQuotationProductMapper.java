package com.ruoyi.project.sale.saleQuotationProduct.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;

/**
 * 报价产品Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleQuotationProductMapper 
{
    /**
     * 查询报价产品
     * 
     * @param id 报价产品ID
     * @return 报价产品
     */
    public SaleQuotationProduct selectSaleQuotationProductById(Long id);

    /**
     * 查询报价产品列表
     * 
     * @param saleQuotationProduct 报价产品
     * @return 报价产品集合
     */
    public List<SaleQuotationProduct> selectSaleQuotationProductList(SaleQuotationProduct saleQuotationProduct);
    public List<SaleQuotationProduct> selectMultProductList(SaleQuotationProduct saleQuotationProduct);

    /**
     * 新增报价产品
     * 
     * @param saleQuotationProduct 报价产品
     * @return 结果
     */
    public int insertSaleQuotationProduct(SaleQuotationProduct saleQuotationProduct);

    /**
     * 修改报价产品
     * 
     * @param saleQuotationProduct 报价产品
     * @return 结果
     */
    public int updateSaleQuotationProduct(SaleQuotationProduct saleQuotationProduct);

    /**
     * 删除报价产品
     * 
     * @param id 报价产品ID
     * @return 结果
     */
    public int deleteSaleQuotationProductById(Long id);

    /**
     * 批量删除报价产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleQuotationProductByIds(String[] ids);
}
