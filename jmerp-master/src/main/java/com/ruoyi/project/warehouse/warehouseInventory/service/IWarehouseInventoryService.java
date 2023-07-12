package com.ruoyi.project.warehouse.warehouseInventory.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configCorrugated.domain.ConfigCorrugated;
import com.ruoyi.project.warehouse.warehouseInventory.domain.WarehouseInventory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 库存统计Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface IWarehouseInventoryService 
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
     * 批量删除库存统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWarehouseInventoryByIds(String ids);

    /**
     * 删除库存统计信息
     * 
     * @param id 库存统计ID
     * @return 结果
     */
    public int deleteWarehouseInventoryById(Long id);


    /**
     * 导入数据
     *
     * @param warehouseInventoryList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importWarehouseInventory(List<WarehouseInventory> warehouseInventoryList, Boolean isUpdateSupport);

    /**
     * 空数据
     * @Author 方舟
     * @Date 2021/4/20 9:17:38
    **/
    public WarehouseInventory createEmptyWarehouseInventory(WarehouseInventory warehouseInventory);

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 17:13:19
    **/
    public void insertInventory(String recordSource, Long warehouseId, Integer qty, Long productId, Long materialsId);

    /**
     * 合并整理
     * @Author 方舟
     * @Date 2021/4/27 17:13:19
     **/
    public WarehouseInventory reorganize(WarehouseInventory warehouseInventory);

    /**
     * 更换仓库
     * @Author 方舟
     * @Date 2021/4/27 17:13:19
     **/
    public WarehouseInventory moveWarehouse(WarehouseInventory warehouseInventory);
}
