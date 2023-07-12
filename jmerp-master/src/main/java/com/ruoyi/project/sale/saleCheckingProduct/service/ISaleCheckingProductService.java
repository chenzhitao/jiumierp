package com.ruoyi.project.sale.saleCheckingProduct.service;

import java.util.List;
import com.ruoyi.project.sale.saleCheckingProduct.domain.SaleCheckingProduct;

/**
 * 送货产品对账Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleCheckingProductService 
{
    /**
     * 查询送货产品对账
     * 
     * @param id 送货产品对账ID
     * @return 送货产品对账
     */
    public SaleCheckingProduct selectSaleCheckingProductById(Long id);

    /**
     * 查询送货产品对账列表
     * 
     * @param saleCheckingProduct 送货产品对账
     * @return 送货产品对账集合
     */
    public List<SaleCheckingProduct> selectSaleCheckingProductList(SaleCheckingProduct saleCheckingProduct);

    /**
     * 新增送货产品对账
     * 
     * @param saleCheckingProduct 送货产品对账
     * @return 结果
     */
    public int insertSaleCheckingProduct(SaleCheckingProduct saleCheckingProduct);

    /**
     * 修改送货产品对账
     * 
     * @param saleCheckingProduct 送货产品对账
     * @return 结果
     */
    public int updateSaleCheckingProduct(SaleCheckingProduct saleCheckingProduct);

    /**
     * 批量删除送货产品对账
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleCheckingProductByIds(String ids);

    /**
     * 删除送货产品对账信息
     * 
     * @param id 送货产品对账ID
     * @return 结果
     */
    public int deleteSaleCheckingProductById(Long id);
}
