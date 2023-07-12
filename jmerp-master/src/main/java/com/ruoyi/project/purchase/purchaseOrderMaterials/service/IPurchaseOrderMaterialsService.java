package com.ruoyi.project.purchase.purchaseOrderMaterials.service;

import java.util.List;

import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
import com.ruoyi.project.purchase.purchaseOrderMaterials.domain.PurchaseOrderMaterials;

/**
 * 采购订单材料Service接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface IPurchaseOrderMaterialsService 
{
    /**
     * 查询采购订单材料
     * 
     * @param id 采购订单材料ID
     * @return 采购订单材料
     */
    public PurchaseOrderMaterials selectPurchaseOrderMaterialsById(Long id);

    /**
     * 查询采购订单材料列表
     * 
     * @param purchaseOrderMaterials 采购订单材料
     * @return 采购订单材料集合
     */
    public List<PurchaseOrderMaterials> selectPurchaseOrderMaterialsList(PurchaseOrderMaterials purchaseOrderMaterials);
    public List<PurchaseOrderMaterials> selectPurchaseOrderMaterialsList(PurchaseOrder purchaseOrder);

    /**
     * 新增采购订单材料
     * 
     * @param purchaseOrderMaterials 采购订单材料
     * @return 结果
     */
    public int insertPurchaseOrderMaterials(PurchaseOrderMaterials purchaseOrderMaterials);

    /**
     * 修改采购订单材料
     * 
     * @param purchaseOrderMaterials 采购订单材料
     * @return 结果
     */
    public int updatePurchaseOrderMaterials(PurchaseOrderMaterials purchaseOrderMaterials);

    /**
     * 批量删除采购订单材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseOrderMaterialsByIds(String ids);

    /**
     * 删除采购订单材料信息
     * 
     * @param id 采购订单材料ID
     * @return 结果
     */
    public int deletePurchaseOrderMaterialsById(Long id);
}
