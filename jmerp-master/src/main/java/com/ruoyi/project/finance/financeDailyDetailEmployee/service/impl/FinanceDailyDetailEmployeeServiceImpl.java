package com.ruoyi.project.finance.financeDailyDetailEmployee.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.finance.financeDailyDetailEmployee.domain.FinanceDailyDetailEmployeeWage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.finance.financeDailyDetailEmployee.mapper.FinanceDailyDetailEmployeeMapper;
import com.ruoyi.project.finance.financeDailyDetailEmployee.domain.FinanceDailyDetailEmployee;
import com.ruoyi.project.finance.financeDailyDetailEmployee.service.IFinanceDailyDetailEmployeeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 排程员工Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class FinanceDailyDetailEmployeeServiceImpl implements IFinanceDailyDetailEmployeeService 
{
    @Autowired
    private FinanceDailyDetailEmployeeMapper financeDailyDetailEmployeeMapper;

    /**
     * 查询排程员工
     * 
     * @param id 排程员工ID
     * @return 排程员工
     */
    @Override
    public FinanceDailyDetailEmployee selectFinanceDailyDetailEmployeeById(Long id)
    {
        return financeDailyDetailEmployeeMapper.selectFinanceDailyDetailEmployeeById(id);
    }

    /**
     * 查询排程员工列表
     * 
     * @param financeDailyDetailEmployee 排程员工
     * @return 排程员工
     */
    @Override
    public List<FinanceDailyDetailEmployee> selectFinanceDailyDetailEmployeeList(FinanceDailyDetailEmployee financeDailyDetailEmployee)
    {
        return financeDailyDetailEmployeeMapper.selectFinanceDailyDetailEmployeeList(financeDailyDetailEmployee);
    }

    @Override
    public List<FinanceDailyDetailEmployeeWage> selectFinanceDailyDetailEmployeeWageList(FinanceDailyDetailEmployeeWage financeDailyDetailEmployeeWage){
        return financeDailyDetailEmployeeMapper.selectFinanceDailyDetailEmployeeWageList(financeDailyDetailEmployeeWage);
    }

    /**
     * 新增排程员工
     * 
     * @param financeDailyDetailEmployee 排程员工
     * @return 结果
     */
    @Override
    public int insertFinanceDailyDetailEmployee(FinanceDailyDetailEmployee financeDailyDetailEmployee)
    {
        return financeDailyDetailEmployeeMapper.insertFinanceDailyDetailEmployee(financeDailyDetailEmployee);
    }

    /**
     * 修改排程员工
     * 
     * @param financeDailyDetailEmployee 排程员工
     * @return 结果
     */
    @Override
    public int updateFinanceDailyDetailEmployee(FinanceDailyDetailEmployee financeDailyDetailEmployee)
    {
        if(!StringUtils.isEmpty(financeDailyDetailEmployee.getRzyValue1())&&"batch".equals(financeDailyDetailEmployee.getRzyValue1())){
            int result = 0;
            String[] arr = Convert.toStrArray(financeDailyDetailEmployee.getIds());
            String[] processOrderArr = Convert.toStrArray(financeDailyDetailEmployee.getProcessOrderArr());
            String[] employeePriceArr = Convert.toStrArray(financeDailyDetailEmployee.getEmployeePriceArr());
            String[] employeeScaleArr = Convert.toStrArray(financeDailyDetailEmployee.getEmployeeScaleArr());
            for (int i=0;i<arr.length;i++){
                Long id = Long.parseLong(arr[i]);
                BigDecimal employeePrice = new BigDecimal(employeePriceArr[i]);
                BigDecimal employeeScale = new BigDecimal(employeeScaleArr[i]);
                FinanceDailyDetailEmployee saveVO = new FinanceDailyDetailEmployee();
                saveVO.setId(id);
                saveVO.setFinanceDailyDetailId(financeDailyDetailEmployee.getFinanceDailyDetailId());
                saveVO.setEmployeeScale(employeeScale);
                saveVO.setEmployeePrice(employeePrice);
                saveVO.setPrice(financeDailyDetailEmployee.getPrice());
                result = financeDailyDetailEmployeeMapper.updateFinanceDailyDetailEmployee(saveVO);
            }
            return result;
        }else{
            return financeDailyDetailEmployeeMapper.updateFinanceDailyDetailEmployee(financeDailyDetailEmployee);
        }
    }

    /**
     * 删除排程员工对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFinanceDailyDetailEmployeeByIds(String ids)
    {
        return financeDailyDetailEmployeeMapper.deleteFinanceDailyDetailEmployeeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除排程员工信息
     * 
     * @param id 排程员工ID
     * @return 结果
     */
    @Override
    public int deleteFinanceDailyDetailEmployeeById(Long id)
    {
        return financeDailyDetailEmployeeMapper.deleteFinanceDailyDetailEmployeeById(id);
    }
}
