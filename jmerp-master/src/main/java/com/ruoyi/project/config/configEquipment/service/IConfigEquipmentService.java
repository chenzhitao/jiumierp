package com.ruoyi.project.config.configEquipment.service;

import java.util.List;

import com.ruoyi.project.config.configEmployee.domain.ConfigEmployee;
import com.ruoyi.project.config.configEquipment.domain.ConfigEquipment;

/**
 * 设备管理Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigEquipmentService 
{
    /**
     * 查询设备管理
     * 
     * @param id 设备管理ID
     * @return 设备管理
     */
    public ConfigEquipment selectConfigEquipmentById(Long id);

    /**
     * 查询设备管理列表
     * 
     * @param configEquipment 设备管理
     * @return 设备管理集合
     */
    public List<ConfigEquipment> selectConfigEquipmentList(ConfigEquipment configEquipment);

    /**
     * 新增设备管理
     * 
     * @param configEquipment 设备管理
     * @return 结果
     */
    public int insertConfigEquipment(ConfigEquipment configEquipment);

    /**
     * 修改设备管理
     * 
     * @param configEquipment 设备管理
     * @return 结果
     */
    public int updateConfigEquipment(ConfigEquipment configEquipment);

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigEquipmentByIds(String ids);

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理ID
     * @return 结果
     */
    public int deleteConfigEquipmentById(Long id);

    /**
     * 导入用户数据
     *
     * @param configEquipmentList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigEquipment(List<ConfigEquipment> configEquipmentList, Boolean isUpdateSupport);
}
