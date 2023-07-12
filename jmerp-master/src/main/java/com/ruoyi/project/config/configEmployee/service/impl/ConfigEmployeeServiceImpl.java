package com.ruoyi.project.config.configEmployee.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.mapper.DeptMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configEmployee.mapper.ConfigEmployeeMapper;
import com.ruoyi.project.config.configEmployee.domain.ConfigEmployee;
import com.ruoyi.project.config.configEmployee.service.IConfigEmployeeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 员工信息Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigEmployeeServiceImpl implements IConfigEmployeeService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigEmployeeServiceImpl.class);

    @Autowired
    private ConfigEmployeeMapper configEmployeeMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询员工信息
     * 
     * @param id 员工信息ID
     * @return 员工信息
     */
    @Override
    public ConfigEmployee selectConfigEmployeeById(Long id)
    {
        return configEmployeeMapper.selectConfigEmployeeById(id);
    }

    /**
     * 查询员工信息列表
     * 
     * @param configEmployee 员工信息
     * @return 员工信息
     */
    @Override
    public List<ConfigEmployee> selectConfigEmployeeList(ConfigEmployee configEmployee)
    {
        return configEmployeeMapper.selectConfigEmployeeList(configEmployee);
    }

    /**
     * 新增员工信息
     * 
     * @param configEmployee 员工信息
     * @return 结果
     */
    @Override
    public int insertConfigEmployee(ConfigEmployee configEmployee)
    {
        configEmployee.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configEmployee.setCreateTime(DateUtils.getNowDate());
        configEmployee.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configEmployee.setUpdateTime(DateUtils.getNowDate());
        return configEmployeeMapper.insertConfigEmployee(configEmployee);
    }

    /**
     * 修改员工信息
     * 
     * @param configEmployee 员工信息
     * @return 结果
     */
    @Override
    public int updateConfigEmployee(ConfigEmployee configEmployee)
    {
        configEmployee.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configEmployee.setUpdateTime(DateUtils.getNowDate());
        return configEmployeeMapper.updateConfigEmployee(configEmployee);
    }

    /**
     * 删除员工信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigEmployeeByIds(String ids)
    {
        return configEmployeeMapper.deleteConfigEmployeeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除员工信息信息
     * 
     * @param id 员工信息ID
     * @return 结果
     */
    @Override
    public int deleteConfigEmployeeById(Long id)
    {
        return configEmployeeMapper.deleteConfigEmployeeById(id);
    }

    /**
     * 导入用户数据
     *
     * @param configEmployeeList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigEmployee(List<ConfigEmployee> configEmployeeList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configEmployeeList) || configEmployeeList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigEmployee configEmployee : configEmployeeList){
            boolean checkFlag = false;
            try{
                //姓名必填
                if(StringUtils.isEmpty(configEmployee.getEmployeeName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 姓名必填");
                }
                //工号必填
                if(StringUtils.isEmpty(configEmployee.getEmployeeNo())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 工号必填");
                }
                //手机号必填
                if(StringUtils.isEmpty(configEmployee.getCellPhone())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 手机号必填");
                }
                //转换部门id
                if(!StringUtils.isEmpty(configEmployee.getDeptName())){
                    configEmployee.setDeptId(rzyCommonMapper.findIdByName(configEmployee.getDeptName(),"sys_dept","dept_name","dept_id"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigEmployee(configEmployee);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configEmployee.getEmployeeName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、员工 " + configEmployee.getEmployeeName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0){
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
