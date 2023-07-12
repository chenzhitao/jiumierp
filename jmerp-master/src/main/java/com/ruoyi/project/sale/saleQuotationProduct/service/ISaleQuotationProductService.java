package com.ruoyi.project.sale.saleQuotationProduct.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 报价产品Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleQuotationProductService 
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
     * 批量删除报价产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleQuotationProductByIds(String ids);

    /**
     * 删除报价产品信息
     * 
     * @param id 报价产品ID
     * @return 结果
     */
    public int deleteSaleQuotationProductById(Long id);

    /**
     * 添加已有产品
     * @Author 方舟
     * @Date 2021/4/22 13:20:47
     **/
    public SaleQuotationProduct createSaleQuotationProduct(SaleQuotationProduct saleQuotationProduct);

    /**
     * 新建产品
     * @Author 方舟
     * @Date 2021/4/23 10:15:27
    **/
    public SaleQuotationProduct createProduct(SaleQuotationProduct saleQuotationProduct);

    /**
     * 更换工艺卡
     * @Author 方舟
     * @Date 2021/4/23 10:14:36
     **/
    public SaleQuotationProduct selectSaleQuotationExec(SaleQuotationProduct saleQuotationProduct);

    /**
     * 导入数据
     *
     * @param saleQuotationProductList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importSaleQuotationProduct(List<SaleQuotationProduct> saleQuotationProductList, Boolean isUpdateSupport);
}
