package com.ruoyi.project.purchase.purchaseDeliveryMaterials.mapper;

import java.util.List;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain.PurchaseDeliveryMaterials;

/**
 * 采购到货材料Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface PurchaseDeliveryMaterialsMapper 
{
    /**
     * 查询采购到货材料
     * 
     * @param id 采购到货材料ID
     * @return 采购到货材料
     */
    public PurchaseDeliveryMaterials selectPurchaseDeliveryMaterialsById(Long id);

    /**
     * 查询采购到货材料列表
     * 
     * @param purchaseDeliveryMaterials 采购到货材料
     * @return 采购到货材料集合
     */
    public List<PurchaseDeliveryMaterials> selectPurchaseDeliveryMaterialsList(PurchaseDeliveryMaterials purchaseDeliveryMaterials);

    /**
     * 新增采购到货材料
     * 
     * @param purchaseDeliveryMaterials 采购到货材料
     * @return 结果
     */
    public int insertPurchaseDeliveryMaterials(PurchaseDeliveryMaterials purchaseDeliveryMaterials);

    /**
     * 修改采购到货材料
     * 
     * @param purchaseDeliveryMaterials 采购到货材料
     * @return 结果
     */
    public int updatePurchaseDeliveryMaterials(PurchaseDeliveryMaterials purchaseDeliveryMaterials);

    /**
     * 删除采购到货材料
     * 
     * @param id 采购到货材料ID
     * @return 结果
     */
    public int deletePurchaseDeliveryMaterialsById(Long id);

    /**
     * 批量删除采购到货材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseDeliveryMaterialsByIds(String[] ids);
}
