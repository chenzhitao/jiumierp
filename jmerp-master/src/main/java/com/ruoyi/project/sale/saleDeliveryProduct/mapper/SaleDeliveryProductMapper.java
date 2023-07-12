package com.ruoyi.project.sale.saleDeliveryProduct.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleDeliveryProduct.domain.SaleDeliveryProduct;

/**
 * 送货产品Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleDeliveryProductMapper 
{
    /**
     * 查询送货产品
     * 
     * @param id 送货产品ID
     * @return 送货产品
     */
    public SaleDeliveryProduct selectSaleDeliveryProductById(Long id);

    /**
     * 查询送货产品列表
     * 
     * @param saleDeliveryProduct 送货产品
     * @return 送货产品集合
     */
    public List<SaleDeliveryProduct> selectSaleDeliveryProductList(SaleDeliveryProduct saleDeliveryProduct);

    /**
     * 新增送货产品
     * 
     * @param saleDeliveryProduct 送货产品
     * @return 结果
     */
    public int insertSaleDeliveryProduct(SaleDeliveryProduct saleDeliveryProduct);

    /**
     * 修改送货产品
     * 
     * @param saleDeliveryProduct 送货产品
     * @return 结果
     */
    public int updateSaleDeliveryProduct(SaleDeliveryProduct saleDeliveryProduct);

    /**
     * 删除送货产品
     * 
     * @param id 送货产品ID
     * @return 结果
     */
    public int deleteSaleDeliveryProductById(Long id);

    /**
     * 批量删除送货产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleDeliveryProductByIds(String[] ids);
}
