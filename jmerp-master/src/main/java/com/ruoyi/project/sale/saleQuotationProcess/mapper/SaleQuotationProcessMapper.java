package com.ruoyi.project.sale.saleQuotationProcess.mapper;

import java.util.List;
import com.ruoyi.project.sale.saleQuotationProcess.domain.SaleQuotationProcess;

/**
 * 报价工序Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface SaleQuotationProcessMapper 
{
    /**
     * 查询报价工序
     * 
     * @param id 报价工序ID
     * @return 报价工序
     */
    public SaleQuotationProcess selectSaleQuotationProcessById(Long id);

    /**
     * 查询报价工序列表
     * 
     * @param saleQuotationProcess 报价工序
     * @return 报价工序集合
     */
    public List<SaleQuotationProcess> selectSaleQuotationProcessList(SaleQuotationProcess saleQuotationProcess);

    /**
     * 新增报价工序
     * 
     * @param saleQuotationProcess 报价工序
     * @return 结果
     */
    public int insertSaleQuotationProcess(SaleQuotationProcess saleQuotationProcess);

    /**
     * 修改报价工序
     * 
     * @param saleQuotationProcess 报价工序
     * @return 结果
     */
    public int updateSaleQuotationProcess(SaleQuotationProcess saleQuotationProcess);

    /**
     * 删除报价工序
     * 
     * @param id 报价工序ID
     * @return 结果
     */
    public int deleteSaleQuotationProcessById(Long id);
    public int deleteSaleQuotationProcessByProductId(Long id);
    public int deleteSaleQuotationProcessByTempId(Long id);

    /**
     * 批量删除报价工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSaleQuotationProcessByIds(String[] ids);
    public int deleteSaleQuotationProcessByProductIds(String[] ids);
    public int deleteSaleQuotationProcessByTempIds(String[] ids);
}
