package com.ruoyi.project.sale.saleOrderProduct.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 销售产品Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleOrderProductService 
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
     * 批量删除销售产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleOrderProductByIds(String ids);

    /**
     * 删除销售产品信息
     * 
     * @param id 销售产品ID
     * @return 结果
     */
    public int deleteSaleOrderProductById(Long id);

    /**
     * 新建产品
     * @Author 方舟
     * @Date 2021/4/23 10:14:36
     **/
    public SaleOrderProduct createProduct(SaleOrderProduct saleOrderProduct);
}
