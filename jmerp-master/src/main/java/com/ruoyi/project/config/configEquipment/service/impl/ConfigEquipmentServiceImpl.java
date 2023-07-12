package com.ruoyi.project.config.configEquipment.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configEmployee.domain.ConfigEmployee;
import com.ruoyi.project.config.configEmployee.service.impl.ConfigEmployeeServiceImpl;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configEquipment.mapper.ConfigEquipmentMapper;
import com.ruoyi.project.config.configEquipment.domain.ConfigEquipment;
import com.ruoyi.project.config.configEquipment.service.IConfigEquipmentService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 设备管理Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigEquipmentServiceImpl implements IConfigEquipmentService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigEquipmentServiceImpl.class);

    @Autowired
    private ConfigEquipmentMapper configEquipmentMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询设备管理
     * 
     * @param id 设备管理ID
     * @return 设备管理
     */
    @Override
    public ConfigEquipment selectConfigEquipmentById(Long id)
    {
        return configEquipmentMapper.selectConfigEquipmentById(id);
    }

    /**
     * 查询设备管理列表
     * 
     * @param configEquipment 设备管理
     * @return 设备管理
     */
    @Override
    public List<ConfigEquipment> selectConfigEquipmentList(ConfigEquipment configEquipment)
    {
        return configEquipmentMapper.selectConfigEquipmentList(configEquipment);
    }

    /**
     * 新增设备管理
     * 
     * @param configEquipment 设备管理
     * @return 结果
     */
    @Override
    public int insertConfigEquipment(ConfigEquipment configEquipment)
    {
        configEquipment.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configEquipment.setCreateTime(DateUtils.getNowDate());
        configEquipment.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configEquipment.setUpdateTime(DateUtils.getNowDate());
        return configEquipmentMapper.insertConfigEquipment(configEquipment);
    }

    /**
     * 修改设备管理
     * 
     * @param configEquipment 设备管理
     * @return 结果
     */
    @Override
    public int updateConfigEquipment(ConfigEquipment configEquipment)
    {
        configEquipment.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configEquipment.setUpdateTime(DateUtils.getNowDate());
        return configEquipmentMapper.updateConfigEquipment(configEquipment);
    }

    /**
     * 删除设备管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigEquipmentByIds(String ids)
    {
        return configEquipmentMapper.deleteConfigEquipmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理ID
     * @return 结果
     */
    @Override
    public int deleteConfigEquipmentById(Long id)
    {
        return configEquipmentMapper.deleteConfigEquipmentById(id);
    }

    /**
     * 导入用户数据
     *
     * @param configEquipmentList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigEquipment(List<ConfigEquipment> configEquipmentList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configEquipmentList) || configEquipmentList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigEquipment configEquipment : configEquipmentList){
            boolean checkFlag = false;
            try{
                //名称必填
                if(StringUtils.isEmpty(configEquipment.getEquipmentName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 设备名称必填");
                }

                //转换部门id
                if(!StringUtils.isEmpty(configEquipment.getDeptName())){
                    configEquipment.setDeptId(rzyCommonMapper.findIdByName(configEquipment.getDeptName(),"sys_dept","dept_name","dept_id"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigEquipment(configEquipment);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configEquipment.getEquipmentName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、设备 " + configEquipment.getEquipmentName() + " 导入失败：";
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
