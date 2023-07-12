package com.ruoyi.project.config.configEquipment.mapper;

import java.util.List;
import com.ruoyi.project.config.configEquipment.domain.ConfigEquipment;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;

/**
 * 设备管理Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigEquipmentMapper 
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
     * 删除设备管理
     * 
     * @param id 设备管理ID
     * @return 结果
     */
    public int deleteConfigEquipmentById(Long id);

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigEquipmentByIds(String[] ids);

    /**
     * 根据施工单单号找印刷设备
     * @Author 方舟
     * @Date 2021/8/18 19:35:10
    **/
    public List<ConfigEquipment> selectConfigEquipmentsByProduceOrderId(ProduceOrder produceOrder);
}
