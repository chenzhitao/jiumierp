package com.ruoyi.project.purchase.purchaseReturnMaterials.service;

import java.util.List;
import com.ruoyi.project.purchase.purchaseReturnMaterials.domain.PurchaseReturnMaterials;

/**
 * 采购退货材料Service接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface IPurchaseReturnMaterialsService 
{
    /**
     * 查询采购退货材料
     * 
     * @param id 采购退货材料ID
     * @return 采购退货材料
     */
    public PurchaseReturnMaterials selectPurchaseReturnMaterialsById(Long id);

    /**
     * 查询采购退货材料列表
     * 
     * @param purchaseReturnMaterials 采购退货材料
     * @return 采购退货材料集合
     */
    public List<PurchaseReturnMaterials> selectPurchaseReturnMaterialsList(PurchaseReturnMaterials purchaseReturnMaterials);

    /**
     * 新增采购退货材料
     * 
     * @param purchaseReturnMaterials 采购退货材料
     * @return 结果
     */
    public int insertPurchaseReturnMaterials(PurchaseReturnMaterials purchaseReturnMaterials);

    /**
     * 修改采购退货材料
     * 
     * @param purchaseReturnMaterials 采购退货材料
     * @return 结果
     */
    public int updatePurchaseReturnMaterials(PurchaseReturnMaterials purchaseReturnMaterials);

    /**
     * 批量删除采购退货材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseReturnMaterialsByIds(String ids);

    /**
     * 删除采购退货材料信息
     * 
     * @param id 采购退货材料ID
     * @return 结果
     */
    public int deletePurchaseReturnMaterialsById(Long id);
}
