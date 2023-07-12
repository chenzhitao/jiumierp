package com.ruoyi.project.finance.financeDailyDetailEmployee.service;

import java.util.List;
import com.ruoyi.project.finance.financeDailyDetailEmployee.domain.FinanceDailyDetailEmployee;
import com.ruoyi.project.finance.financeDailyDetailEmployee.domain.FinanceDailyDetailEmployeeWage;

/**
 * 排程员工Service接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface IFinanceDailyDetailEmployeeService 
{
    /**
     * 查询排程员工
     * 
     * @param id 排程员工ID
     * @return 排程员工
     */
    public FinanceDailyDetailEmployee selectFinanceDailyDetailEmployeeById(Long id);

    /**
     * 查询排程员工列表
     * 
     * @param financeDailyDetailEmployee 排程员工
     * @return 排程员工集合
     */
    public List<FinanceDailyDetailEmployee> selectFinanceDailyDetailEmployeeList(FinanceDailyDetailEmployee financeDailyDetailEmployee);
    public List<FinanceDailyDetailEmployeeWage> selectFinanceDailyDetailEmployeeWageList(FinanceDailyDetailEmployeeWage financeDailyDetailEmployeeWage);

    /**
     * 新增排程员工
     * 
     * @param financeDailyDetailEmployee 排程员工
     * @return 结果
     */
    public int insertFinanceDailyDetailEmployee(FinanceDailyDetailEmployee financeDailyDetailEmployee);

    /**
     * 修改排程员工
     * 
     * @param financeDailyDetailEmployee 排程员工
     * @return 结果
     */
    public int updateFinanceDailyDetailEmployee(FinanceDailyDetailEmployee financeDailyDetailEmployee);

    /**
     * 批量删除排程员工
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceDailyDetailEmployeeByIds(String ids);

    /**
     * 删除排程员工信息
     * 
     * @param id 排程员工ID
     * @return 结果
     */
    public int deleteFinanceDailyDetailEmployeeById(Long id);
}
