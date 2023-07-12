package com.ruoyi.project.sale.saleQuotation.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotationExport;

/**
 * 报价单Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleQuotationMapper 
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
    public List<SaleQuotationExport> selectSaleQuotationExportList(SaleQuotation saleQuotation);

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
    public int updateSaleQuotation(SaleQuotation saleQuotation);

    /**
     * 删除报价单
     * 
     * @param id 报价单ID
     * @return 结果
     */
    public int deleteSaleQuotationById(Long id);

    /**
     * 批量删除报价单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleQuotationByIds(String[] ids);
}
