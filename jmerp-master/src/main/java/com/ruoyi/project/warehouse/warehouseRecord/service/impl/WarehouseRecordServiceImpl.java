package com.ruoyi.project.warehouse.warehouseRecord.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.warehouse.warehouseInventory.domain.WarehouseInventory;
import com.ruoyi.project.warehouse.warehouseInventory.mapper.WarehouseInventoryMapper;
import com.ruoyi.project.warehouse.warehouseInventory.service.IWarehouseInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.warehouse.warehouseRecord.mapper.WarehouseRecordMapper;
import com.ruoyi.project.warehouse.warehouseRecord.domain.WarehouseRecord;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 出入库记录Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class WarehouseRecordServiceImpl implements IWarehouseRecordService 
{
    @Autowired
    private WarehouseRecordMapper warehouseRecordMapper;
    @Autowired
    private IWarehouseInventoryService warehouseInventoryService;

    /**
     * 查询出入库记录
     * 
     * @param id 出入库记录ID
     * @return 出入库记录
     */
    @Override
    public WarehouseRecord selectWarehouseRecordById(Long id)
    {
        return warehouseRecordMapper.selectWarehouseRecordById(id);
    }

    /**
     * 查询出入库记录列表
     * 
     * @param warehouseRecord 出入库记录
     * @return 出入库记录
     */
    @Override
    public List<WarehouseRecord> selectWarehouseRecordList(WarehouseRecord warehouseRecord)
    {
        return warehouseRecordMapper.selectWarehouseRecordList(warehouseRecord);
    }

    /**
     * 新增出入库记录
     * 
     * @param warehouseRecord 出入库记录
     * @return 结果
     */
    @Override
    public int insertWarehouseRecord(WarehouseRecord warehouseRecord)
    {
        warehouseRecord.setCreateBy(ShiroUtils.getSysUser().getUserName());
        warehouseRecord.setCreateTime(DateUtils.getNowDate());
        warehouseRecord.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        warehouseRecord.setUpdateTime(DateUtils.getNowDate());
        return warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);
    }

    /**
     * 修改出入库记录
     * 
     * @param warehouseRecord 出入库记录
     * @return 结果
     */
    @Override
    public int updateWarehouseRecord(WarehouseRecord warehouseRecord)
    {
        warehouseRecord.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        warehouseRecord.setUpdateTime(DateUtils.getNowDate());
        return warehouseRecordMapper.updateWarehouseRecord(warehouseRecord);
    }

    /**
     * 删除出入库记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWarehouseRecordByIds(String ids)
    {
        return warehouseRecordMapper.deleteWarehouseRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除出入库记录信息
     * 
     * @param id 出入库记录ID
     * @return 结果
     */
    @Override
    public int deleteWarehouseRecordById(Long id)
    {
        return warehouseRecordMapper.deleteWarehouseRecordById(id);
    }

    /**
     * 出入库
     * 手动调整	SDTZ
     * 客户带料	KHDL
     * 销售送货	XSSH
     * 销售退货	XSTH
     * 采购到货	CGDT
     * 采购退货	CGTH
     * 外发带料	WFDL
     * 外发到货	WFDH
     * 外发退货	WFTH
     * 生产入库	SCRK
     * 生产领料	SCLL
     * 生产退料	SCTL
     * @Author 方舟
     * @Date 2021/4/20 11:34:39
     **/
    @Override
    public void productInbound(String recordSource, Long warehouseId, Integer qty, Long productId,String remark){
        if(null==warehouseId){
            warehouseId = -1L;
        }
        WarehouseRecord warehouseRecord = new WarehouseRecord();
        warehouseRecord.setRecordSource(recordSource);
        warehouseRecord.setWarehouseId(warehouseId);
        warehouseRecord.setProductId(productId);
        warehouseRecord.setQty(qty);
        warehouseRecord.setRemark(remark);
        if(!qty.equals(0)){
            insertWarehouseRecord(warehouseRecord);
        }
        //库存增加
        if(!StringUtils.isEmpty(recordSource)&&!"SDTZ".equals(recordSource)&&!qty.equals(0)){
            warehouseInventoryService.insertInventory(recordSource,warehouseId,qty,productId,null);
        }

    }

    @Override
    public void productOutbound(String recordSource, Long warehouseId, Integer qty, Long productId,String remark){
        if(null==warehouseId){
            warehouseId = -1L;
        }
        WarehouseRecord warehouseRecord = new WarehouseRecord();
        warehouseRecord.setRecordSource(recordSource);
        warehouseRecord.setWarehouseId(warehouseId);
        warehouseRecord.setProductId(productId);
        warehouseRecord.setQty(0-qty);
        warehouseRecord.setRemark(remark);
        if(!qty.equals(0)){
            insertWarehouseRecord(warehouseRecord);
        }
        //库存增加
        if(!StringUtils.isEmpty(recordSource)&&!"SDTZ".equals(recordSource)&&!qty.equals(0)){
            warehouseInventoryService.insertInventory(recordSource,warehouseId,(0-qty),productId,null);
        }
    }

    @Override
    public void materialsInbound(String recordSource, Long warehouseId, Integer qty, Long materialsId,String remark){
        if(null==warehouseId){
            warehouseId = -1L;
        }
        WarehouseRecord warehouseRecord = new WarehouseRecord();
        warehouseRecord.setRecordSource(recordSource);
        warehouseRecord.setWarehouseId(warehouseId);
        warehouseRecord.setMaterialsId(materialsId);
        warehouseRecord.setQty(qty);
        warehouseRecord.setRemark(remark);
        if(!qty.equals(0)){
            insertWarehouseRecord(warehouseRecord);
        }
        //库存增加
        if(!StringUtils.isEmpty(recordSource)&&!"SDTZ".equals(recordSource)){
            warehouseInventoryService.insertInventory(recordSource,warehouseId,qty,null,materialsId);
        }
    }

    @Override
    public void materialsOutbound(String recordSource, Long warehouseId, Integer qty, Long materialsId,String remark){
        if(null==warehouseId){
            warehouseId = -1L;
        }
        WarehouseRecord warehouseRecord = new WarehouseRecord();
        warehouseRecord.setRecordSource(recordSource);
        warehouseRecord.setWarehouseId(warehouseId);
        warehouseRecord.setMaterialsId(materialsId);
        warehouseRecord.setQty(0-qty);
        warehouseRecord.setRemark(remark);
        if(!qty.equals(0)){
            insertWarehouseRecord(warehouseRecord);
        }
        //库存增加
        if(!StringUtils.isEmpty(recordSource)&&!"SDTZ".equals(recordSource)&&!qty.equals(0)){
            warehouseInventoryService.insertInventory(recordSource,warehouseId,(0-qty),null,materialsId);
        }

    }

}
