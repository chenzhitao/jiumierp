package com.ruoyi.project.config.configWarehouse.mapper;

import java.util.List;
import com.ruoyi.project.config.configWarehouse.domain.ConfigWarehouse;

/**
 * 仓库配置Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigWarehouseMapper 
{
    /**
     * 查询仓库配置
     * 
     * @param id 仓库配置ID
     * @return 仓库配置
     */
    public ConfigWarehouse selectConfigWarehouseById(Long id);

    /**
     * 查询仓库配置列表
     * 
     * @param configWarehouse 仓库配置
     * @return 仓库配置集合
     */
    public List<ConfigWarehouse> selectConfigWarehouseList(ConfigWarehouse configWarehouse);

    /**
     * 新增仓库配置
     * 
     * @param configWarehouse 仓库配置
     * @return 结果
     */
    public int insertConfigWarehouse(ConfigWarehouse configWarehouse);

    /**
     * 修改仓库配置
     * 
     * @param configWarehouse 仓库配置
     * @return 结果
     */
    public int updateConfigWarehouse(ConfigWarehouse configWarehouse);

    /**
     * 删除仓库配置
     * 
     * @param id 仓库配置ID
     * @return 结果
     */
    public int deleteConfigWarehouseById(Long id);

    /**
     * 批量删除仓库配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigWarehouseByIds(String[] ids);
}
