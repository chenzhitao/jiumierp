package com.ruoyi.project.warehouse.warehouseRecord.service;

import java.util.List;
import com.ruoyi.project.warehouse.warehouseRecord.domain.WarehouseRecord;

/**
 * 出入库记录Service接口
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public interface IWarehouseRecordService 
{
    /**
     * 查询出入库记录
     * 
     * @param id 出入库记录ID
     * @return 出入库记录
     */
    public WarehouseRecord selectWarehouseRecordById(Long id);

    /**
     * 查询出入库记录列表
     * 
     * @param warehouseRecord 出入库记录
     * @return 出入库记录集合
     */
    public List<WarehouseRecord> selectWarehouseRecordList(WarehouseRecord warehouseRecord);

    /**
     * 新增出入库记录
     * 
     * @param warehouseRecord 出入库记录
     * @return 结果
     */
    public int insertWarehouseRecord(WarehouseRecord warehouseRecord);

    /**
     * 修改出入库记录
     * 
     * @param warehouseRecord 出入库记录
     * @return 结果
     */
    public int updateWarehouseRecord(WarehouseRecord warehouseRecord);

    /**
     * 批量删除出入库记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWarehouseRecordByIds(String ids);

    /**
     * 删除出入库记录信息
     * 
     * @param id 出入库记录ID
     * @return 结果
     */
    public int deleteWarehouseRecordById(Long id);

    /**
     * 出入库
     * @Author 方舟
     * @Date 2021/4/20 11:34:39
    **/
    public void productInbound(String boundType, Long warehouseId, Integer qty, Long productId,String remark);
    public void productOutbound(String boundType, Long warehouseId, Integer qty, Long productId,String remark);
    public void materialsInbound(String boundType, Long warehouseId, Integer qty, Long materialsId,String remark);
    public void materialsOutbound(String boundType, Long warehouseId, Integer qty, Long materialsId,String remark);
}
