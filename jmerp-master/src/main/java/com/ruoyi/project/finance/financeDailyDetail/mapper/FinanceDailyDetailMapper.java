package com.ruoyi.project.finance.financeDailyDetail.mapper;

import java.util.List;
import com.ruoyi.project.finance.financeDailyDetail.domain.FinanceDailyDetail;

/**
 * 排程明细Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface FinanceDailyDetailMapper 
{
    /**
     * 查询排程明细
     * 
     * @param id 排程明细ID
     * @return 排程明细
     */
    public FinanceDailyDetail selectFinanceDailyDetailById(Long id);

    /**
     * 查询排程明细列表
     * 
     * @param financeDailyDetail 排程明细
     * @return 排程明细集合
     */
    public List<FinanceDailyDetail> selectFinanceDailyDetailList(FinanceDailyDetail financeDailyDetail);

    /**
     * 新增排程明细
     * 
     * @param financeDailyDetail 排程明细
     * @return 结果
     */
    public int insertFinanceDailyDetail(FinanceDailyDetail financeDailyDetail);

    /**
     * 修改排程明细
     * 
     * @param financeDailyDetail 排程明细
     * @return 结果
     */
    public int updateFinanceDailyDetail(FinanceDailyDetail financeDailyDetail);

    /**
     * 删除排程明细
     * 
     * @param id 排程明细ID
     * @return 结果
     */
    public int deleteFinanceDailyDetailById(Long id);

    /**
     * 批量删除排程明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceDailyDetailByIds(String[] ids);
}
