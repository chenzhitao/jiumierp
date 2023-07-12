package com.ruoyi.project.purchase.purchaseCheckingMaterials.service;

import java.util.List;
import com.ruoyi.project.purchase.purchaseCheckingMaterials.domain.PurchaseCheckingMaterials;

/**
 * 采购对账材料Service接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface IPurchaseCheckingMaterialsService 
{
    /**
     * 查询采购对账材料
     * 
     * @param id 采购对账材料ID
     * @return 采购对账材料
     */
    public PurchaseCheckingMaterials selectPurchaseCheckingMaterialsById(Long id);

    /**
     * 查询采购对账材料列表
     * 
     * @param purchaseCheckingMaterials 采购对账材料
     * @return 采购对账材料集合
     */
    public List<PurchaseCheckingMaterials> selectPurchaseCheckingMaterialsList(PurchaseCheckingMaterials purchaseCheckingMaterials);

    /**
     * 新增采购对账材料
     * 
     * @param purchaseCheckingMaterials 采购对账材料
     * @return 结果
     */
    public int insertPurchaseCheckingMaterials(PurchaseCheckingMaterials purchaseCheckingMaterials);

    /**
     * 修改采购对账材料
     * 
     * @param purchaseCheckingMaterials 采购对账材料
     * @return 结果
     */
    public int updatePurchaseCheckingMaterials(PurchaseCheckingMaterials purchaseCheckingMaterials);

    /**
     * 批量删除采购对账材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseCheckingMaterialsByIds(String ids);

    /**
     * 删除采购对账材料信息
     * 
     * @param id 采购对账材料ID
     * @return 结果
     */
    public int deletePurchaseCheckingMaterialsById(Long id);
}
