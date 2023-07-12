package com.ruoyi.project.config.configEmployee.service;

import java.util.List;
import com.ruoyi.project.config.configEmployee.domain.ConfigEmployee;
import com.ruoyi.project.system.user.domain.User;

/**
 * 员工信息Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigEmployeeService 
{
    /**
     * 查询员工信息
     * 
     * @param id 员工信息ID
     * @return 员工信息
     */
    public ConfigEmployee selectConfigEmployeeById(Long id);

    /**
     * 查询员工信息列表
     * 
     * @param configEmployee 员工信息
     * @return 员工信息集合
     */
    public List<ConfigEmployee> selectConfigEmployeeList(ConfigEmployee configEmployee);

    /**
     * 新增员工信息
     * 
     * @param configEmployee 员工信息
     * @return 结果
     */
    public int insertConfigEmployee(ConfigEmployee configEmployee);

    /**
     * 修改员工信息
     * 
     * @param configEmployee 员工信息
     * @return 结果
     */
    public int updateConfigEmployee(ConfigEmployee configEmployee);

    /**
     * 批量删除员工信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigEmployeeByIds(String ids);

    /**
     * 删除员工信息信息
     * 
     * @param id 员工信息ID
     * @return 结果
     */
    public int deleteConfigEmployeeById(Long id);

    /**
     * 导入用户数据
     *
     * @param configEmployeeList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigEmployee(List<ConfigEmployee> configEmployeeList, Boolean isUpdateSupport);
}
