package com.ruoyi.project.warehouse.warehouseInventory.mapper;

import java.util.List;
import com.ruoyi.project.warehouse.warehouseInventory.domain.WarehouseInventory;

/**
 * 库存统计Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface WarehouseInventoryMapper 
{
    /**
     * 查询库存统计
     * 
     * @param id 库存统计ID
     * @return 库存统计
     */
    public WarehouseInventory selectWarehouseInventoryById(Long id);

    /**
     * 查询库存统计列表
     * 
     * @param warehouseInventory 库存统计
     * @return 库存统计集合
     */
    public List<WarehouseInventory> selectWarehouseInventoryList(WarehouseInventory warehouseInventory);

    /**
     * 新增库存统计
     * 
     * @param warehouseInventory 库存统计
     * @return 结果
     */
    public int insertWarehouseInventory(WarehouseInventory warehouseInventory);

    /**
     * 修改库存统计
     * 
     * @param warehouseInventory 库存统计
     * @return 结果
     */
    public int updateWarehouseInventory(WarehouseInventory warehouseInventory);

    /**
     * 删除库存统计
     * 
     * @param id 库存统计ID
     * @return 结果
     */
    public int deleteWarehouseInventoryById(Long id);

    /**
     * 空数据
     * @Author 方舟
     * @Date 2021/4/20 9:17:38
     **/
    public WarehouseInventory createEmptyWarehouseInventory(WarehouseInventory warehouseInventory);

    /**
     * 需要整理的
     * @Author 方舟
     * @Date 2021/4/27 17:40:46
    **/
    public List<WarehouseInventory> selectGroupInventory(WarehouseInventory warehouseInventory);

    /**
     * 批量删除库存统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWarehouseInventoryByIds(String[] ids);
}
