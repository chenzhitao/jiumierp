package com.ruoyi.project.sale.saleReturnProduct.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleReturnProduct.domain.SaleReturnProduct;

/**
 * 退货产品Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleReturnProductMapper 
{
    /**
     * 查询退货产品
     * 
     * @param id 退货产品ID
     * @return 退货产品
     */
    public SaleReturnProduct selectSaleReturnProductById(Long id);

    /**
     * 查询退货产品列表
     * 
     * @param saleReturnProduct 退货产品
     * @return 退货产品集合
     */
    public List<SaleReturnProduct> selectSaleReturnProductList(SaleReturnProduct saleReturnProduct);

    /**
     * 新增退货产品
     * 
     * @param saleReturnProduct 退货产品
     * @return 结果
     */
    public int insertSaleReturnProduct(SaleReturnProduct saleReturnProduct);

    /**
     * 修改退货产品
     * 
     * @param saleReturnProduct 退货产品
     * @return 结果
     */
    public int updateSaleReturnProduct(SaleReturnProduct saleReturnProduct);

    /**
     * 删除退货产品
     * 
     * @param id 退货产品ID
     * @return 结果
     */
    public int deleteSaleReturnProductById(Long id);

    /**
     * 批量删除退货产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleReturnProductByIds(String[] ids);
}
