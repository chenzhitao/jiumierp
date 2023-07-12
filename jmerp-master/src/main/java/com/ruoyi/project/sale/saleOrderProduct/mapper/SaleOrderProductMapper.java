package com.ruoyi.project.sale.saleOrderProduct.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;

/**
 * 销售产品Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleOrderProductMapper 
{
    /**
     * 查询销售产品
     * 
     * @param id 销售产品ID
     * @return 销售产品
     */
    public SaleOrderProduct selectSaleOrderProductById(Long id);

    /**
     * 查询销售产品列表
     * 
     * @param saleOrderProduct 销售产品
     * @return 销售产品集合
     */
    public List<SaleOrderProduct> selectSaleOrderProductList(SaleOrderProduct saleOrderProduct);
    public List<SaleOrderProduct> selectSaleOrderProductList2(SaleOrderProduct saleOrderProduct);

    /**
     * 新增销售产品
     * 
     * @param saleOrderProduct 销售产品
     * @return 结果
     */
    public int insertSaleOrderProduct(SaleOrderProduct saleOrderProduct);

    /**
     * 修改销售产品
     * 
     * @param saleOrderProduct 销售产品
     * @return 结果
     */
    public int updateSaleOrderProduct(SaleOrderProduct saleOrderProduct);

    /**
     * 删除销售产品
     * 
     * @param id 销售产品ID
     * @return 结果
     */
    public int deleteSaleOrderProductById(Long id);

    /**
     * 批量删除销售产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleOrderProductByIds(String[] ids);

    /**
     * 找未排程产品
     * @Author 方舟
     * @Date 2021/4/28 9:45:37
    **/
    public List<SaleOrderProduct> selectUnsendProduct(Long id);
}
