package com.ruoyi.project.sale.saleQuotation.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotationExport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 报价单Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface ISaleQuotationService 
{
    /**
     * 查询报价单
     * 
     * @param id 报价单ID
     * @return 报价单
     */
    public SaleQuotation selectSaleQuotationById(Long id);

    /**
     * 查询报价单列表
     * 
     * @param saleQuotation 报价单
     * @return 报价单集合
     */
    public List<SaleQuotation> selectSaleQuotationList(SaleQuotation saleQuotation);
    public List<SaleQuotationExport> selectSaleQuotationExportList(SaleQuotation saleQuotationExport);

    /**
     * 新增报价单
     * 
     * @param saleQuotation 报价单
     * @return 结果
     */
    public int insertSaleQuotation(SaleQuotation saleQuotation);

    /**
     * 修改报价单
     * 
     * @param saleQuotation 报价单
     * @return 结果
     */
    public SaleQuotation updateSaleQuotation(SaleQuotation saleQuotation);

    /**
     * 批量删除报价单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleQuotationByIds(String ids);

    /**
     * 删除报价单信息
     * 
     * @param id 报价单ID
     * @return 结果
     */
    public int deleteSaleQuotationById(Long id);

    /**
     * 选择客户创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    public SaleQuotation createSaleQuotation(SaleQuotation saleQuotation);

    /**
     * 报价单计算
     * @Author 方舟
     * @Date 2021/4/21 14:22:23
     **/
    public SaleQuotation calculator(SaleQuotation saleQuotation);

    /**
     * 导入数据
     *
     * @param saleQuotationList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importSaleQuotation(List<SaleQuotation> saleQuotationList, Boolean isUpdateSupport);

}
