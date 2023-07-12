package com.ruoyi.project.sale.saleQuotationProcess.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleQuotationProcess.mapper.SaleQuotationProcessMapper;
import com.ruoyi.project.sale.saleQuotationProcess.domain.SaleQuotationProcess;
import com.ruoyi.project.sale.saleQuotationProcess.service.ISaleQuotationProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 报价工序Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleQuotationProcessServiceImpl implements ISaleQuotationProcessService 
{
    @Autowired
    private SaleQuotationProcessMapper saleQuotationProcessMapper;

    /**
     * 查询报价工序
     * 
     * @param id 报价工序ID
     * @return 报价工序
     */
    @Override
    public SaleQuotationProcess selectSaleQuotationProcessById(Long id)
    {
        return saleQuotationProcessMapper.selectSaleQuotationProcessById(id);
    }

    /**
     * 查询报价工序列表
     * 
     * @param saleQuotationProcess 报价工序
     * @return 报价工序
     */
    @Override
    public List<SaleQuotationProcess> selectSaleQuotationProcessList(SaleQuotationProcess saleQuotationProcess)
    {
        return saleQuotationProcessMapper.selectSaleQuotationProcessList(saleQuotationProcess);
    }

    /**
     * 新增报价工序
     * 
     * @param saleQuotationProcess 报价工序
     * @return 结果
     */
    @Override
    public int insertSaleQuotationProcess(SaleQuotationProcess saleQuotationProcess)
    {
        return saleQuotationProcessMapper.insertSaleQuotationProcess(saleQuotationProcess);
    }

    /**
     * 修改报价工序
     * 
     * @param saleQuotationProcess 报价工序
     * @return 结果
     */
    @Override
    public int updateSaleQuotationProcess(SaleQuotationProcess saleQuotationProcess)
    {
        return saleQuotationProcessMapper.updateSaleQuotationProcess(saleQuotationProcess);
    }

    /**
     * 删除报价工序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationProcessByIds(String ids)
    {
        return saleQuotationProcessMapper.deleteSaleQuotationProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报价工序信息
     * 
     * @param id 报价工序ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationProcessById(Long id)
    {
        return saleQuotationProcessMapper.deleteSaleQuotationProcessById(id);
    }
}
