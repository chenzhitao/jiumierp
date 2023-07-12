package com.ruoyi.project.finance.financeDaily.mapper;

import java.util.List;
import com.ruoyi.project.finance.financeDaily.domain.FinanceDaily;
import com.ruoyi.project.finance.financeDaily.domain.FinanceDailyExport;

/**
 * 生产日报Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface FinanceDailyMapper 
{
    /**
     * 查询生产日报
     * 
     * @param id 生产日报ID
     * @return 生产日报
     */
    public FinanceDaily selectFinanceDailyById(Long id);

    /**
     * 查询生产日报列表
     * 
     * @param financeDaily 生产日报
     * @return 生产日报集合
     */
    public List<FinanceDaily> selectFinanceDailyList(FinanceDaily financeDaily);
    public List<FinanceDailyExport> selectFinanceDailyExportList(FinanceDaily financeDaily);

    /**
     * 新增生产日报
     * 
     * @param financeDaily 生产日报
     * @return 结果
     */
    public int insertFinanceDaily(FinanceDaily financeDaily);

    /**
     * 修改生产日报
     * 
     * @param financeDaily 生产日报
     * @return 结果
     */
    public int updateFinanceDaily(FinanceDaily financeDaily);

    /**
     * 删除生产日报
     * 
     * @param id 生产日报ID
     * @return 结果
     */
    public int deleteFinanceDailyById(Long id);
    public int deleteFinanceDailyDetailById(Long id);
    public int deleteFinanceDailyEmployeeById(Long id);

    /**
     * 批量删除生产日报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceDailyByIds(String[] ids);
}
