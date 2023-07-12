package com.ruoyi.project.warehouse.warehouseInventory.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.warehouse.warehouseInventory.mapper.WarehouseInventoryMapper;
import com.ruoyi.project.warehouse.warehouseInventory.domain.WarehouseInventory;
import com.ruoyi.project.warehouse.warehouseInventory.service.IWarehouseInventoryService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 库存统计Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class WarehouseInventoryServiceImpl implements IWarehouseInventoryService 
{
    @Autowired
    private WarehouseInventoryMapper warehouseInventoryMapper;

    private static final Logger log = LoggerFactory.getLogger(WarehouseInventoryServiceImpl.class);

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询库存统计
     * 
     * @param id 库存统计ID
     * @return 库存统计
     */
    @Override
    public WarehouseInventory selectWarehouseInventoryById(Long id)
    {
        return warehouseInventoryMapper.selectWarehouseInventoryById(id);
    }

    /**
     * 查询库存统计列表
     * 
     * @param warehouseInventory 库存统计
     * @return 库存统计
     */
    @Override
    public List<WarehouseInventory> selectWarehouseInventoryList(WarehouseInventory warehouseInventory)
    {
        return warehouseInventoryMapper.selectWarehouseInventoryList(warehouseInventory);
    }

    /**
     * 新增库存统计
     * 
     * @param warehouseInventory 库存统计
     * @return 结果
     */
    @Override
    public int insertWarehouseInventory(WarehouseInventory warehouseInventory)
    {
        warehouseInventory.setCreateBy(ShiroUtils.getSysUser().getUserName());
        warehouseInventory.setCreateTime(DateUtils.getNowDate());
        warehouseInventory.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        warehouseInventory.setUpdateTime(DateUtils.getNowDate());
        return warehouseInventoryMapper.insertWarehouseInventory(warehouseInventory);
    }

    /**
     * 修改库存统计
     * 
     * @param warehouseInventory 库存统计
     * @return 结果
     */
    @Override
    public int updateWarehouseInventory(WarehouseInventory warehouseInventory)
    {
        warehouseInventory.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        warehouseInventory.setUpdateTime(DateUtils.getNowDate());
        return warehouseInventoryMapper.updateWarehouseInventory(warehouseInventory);
    }

    /**
     * 删除库存统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWarehouseInventoryByIds(String ids)
    {
        return warehouseInventoryMapper.deleteWarehouseInventoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存统计信息
     * 
     * @param id 库存统计ID
     * @return 结果
     */
    @Override
    public int deleteWarehouseInventoryById(Long id)
    {
        return warehouseInventoryMapper.deleteWarehouseInventoryById(id);
    }

    /**
     * 空数据
     * @Author 方舟
     * @Date 2021/4/20 9:17:38
     **/
    @Override
    public WarehouseInventory createEmptyWarehouseInventory(WarehouseInventory warehouseInventory){
        return warehouseInventoryMapper.createEmptyWarehouseInventory(warehouseInventory);
    }

    /**
     * 导入数据
     *
     * @param warehouseInventoryList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importWarehouseInventory(List<WarehouseInventory> warehouseInventoryList, Boolean isUpdateSupport){
        if (StringUtils.isNull(warehouseInventoryList) || warehouseInventoryList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (WarehouseInventory warehouseInventory : warehouseInventoryList){
            boolean checkFlag = false;
            //空字符串处理
            warehouseInventory = (WarehouseInventory) EntityUtils.nullStringToNull(warehouseInventory);
            try{
                //名称必填
                if(StringUtils.isEmpty(warehouseInventory.getWarehouseName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 仓库必填");
                }
                if(StringUtils.isEmpty(warehouseInventory.getMaterialsName())&&StringUtils.isEmpty(warehouseInventory.getProductName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 产品或者材料必填其一");
                }
                //转id
                if(!StringUtils.isEmpty(warehouseInventory.getProductName())){
                    warehouseInventory.setProductId(rzyCommonMapper.findIdByName(warehouseInventory.getProductName(),"config_product","product_name","id"));
                }
                if(!StringUtils.isEmpty(warehouseInventory.getMaterialsName())){
                    warehouseInventory.setMaterialsId(rzyCommonMapper.findIdByName(warehouseInventory.getMaterialsName(),"config_materials","materials_name","id"));
                }
                if(!StringUtils.isEmpty(warehouseInventory.getWarehouseName())){
                    warehouseInventory.setWarehouseId(rzyCommonMapper.findIdByName(warehouseInventory.getWarehouseName(),"config_warehouse","warehouse_name","id"));
                }
                //成功
                if(!checkFlag){
                    warehouseInventory.setWarehouseType("SDTZ");
                    this.insertWarehouseInventory(warehouseInventory);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + warehouseInventory.getWarehouseName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、库存 " + warehouseInventory.getWarehouseName() + " 导入失败：";
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

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 17:13:19
     **/
    @Override
    public void insertInventory(String recordSource, Long warehouseId, Integer qty, Long productId, Long materialsId){
        WarehouseInventory warehouseInventory = new WarehouseInventory();
        warehouseInventory.setWarehouseId(warehouseId);
        warehouseInventory.setGoodsType(recordSource);
        warehouseInventory.setProductId(productId);
        warehouseInventory.setMaterialsId(materialsId);
        warehouseInventory.setQty(qty);
        insertWarehouseInventory(warehouseInventory);
        //reorganizeProcess();
    }

    /**
     * 合并整理
     * @Author 方舟
     * @Date 2021/4/27 17:13:19
     **/
    @Override
    public WarehouseInventory reorganize(WarehouseInventory warehouseInventory){
        reorganizeProcess();
        return warehouseInventory;
    }

    /**
     * 库存整理
     * @Author 方舟
     * @Date 2021/4/27 17:16:37
    **/
    private void reorganizeProcess(){
        WarehouseInventory warehouseInventory = new WarehouseInventory();
        List<WarehouseInventory> groupList = warehouseInventoryMapper.selectGroupInventory(warehouseInventory);
        for (int i=0;i<groupList.size();i++){
            WarehouseInventory groupVO = groupList.get(i);
            String[] ids = Convert.toStrArray(groupVO.getIds());
            WarehouseInventory baseVO = new WarehouseInventory();
            boolean isUpdate = false;
            for (int j=0;j<ids.length;j++){
                Long invId = Long.parseLong(ids[j]);
                if(j==0){
                    //第一个留着,把后面的数量加上去,然后删除
                    baseVO = warehouseInventoryMapper.selectWarehouseInventoryById(invId);
                }else{
                    WarehouseInventory otherVO = warehouseInventoryMapper.selectWarehouseInventoryById(invId);
                    Integer qty = otherVO.getQty();
                    baseVO.setQty(baseVO.getQty()+qty);
                    isUpdate = true;
                    warehouseInventoryMapper.deleteWarehouseInventoryById(otherVO.getId());
                }
            }
            if(isUpdate){
                //如果是0就直接删除
                if(baseVO.getQty().equals(0)){
                    warehouseInventoryMapper.deleteWarehouseInventoryById(baseVO.getId());
                }else{
                    warehouseInventoryMapper.updateWarehouseInventory(baseVO);
                }
            }
        }
        //selectGroupInventory
    }

    /**
     * 更换仓库
     * @Author 方舟
     * @Date 2021/4/27 17:13:19
     **/
    @Override
    public WarehouseInventory moveWarehouse(WarehouseInventory warehouseInventory){
        String[] arr = Convert.toStrArray(warehouseInventory.getIds());
        for (int i=0;i<arr.length;i++){
            Long id = Long.parseLong(arr[i]);
            WarehouseInventory saveVO = new WarehouseInventory();
            saveVO.setId(id);
            saveVO.setWarehouseId(warehouseInventory.getWarehouseId());
            warehouseInventoryMapper.updateWarehouseInventory(saveVO);
        }
        return warehouseInventory;
    }

}
