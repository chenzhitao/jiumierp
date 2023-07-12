package com.ruoyi.project.config.configWarehouse.service;

import java.util.List;

import com.ruoyi.project.config.configBoard.domain.ConfigBoard;
import com.ruoyi.project.config.configWarehouse.domain.ConfigWarehouse;

/**
 * 仓库配置Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigWarehouseService 
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
     * 批量删除仓库配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigWarehouseByIds(String ids);

    /**
     * 删除仓库配置信息
     * 
     * @param id 仓库配置ID
     * @return 结果
     */
    public int deleteConfigWarehouseById(Long id);

    /**
     * 导入数据
     *
     * @param configWarehouseList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigWarehouse(List<ConfigWarehouse> configWarehouseList, Boolean isUpdateSupport);
}
