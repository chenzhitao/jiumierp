package com.ruoyi.project.purchase.purchaseDelivery.mapper;

import java.util.List;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDeliveryExport;

/**
 * 采购到货Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface PurchaseDeliveryMapper 
{
    /**
     * 查询采购到货
     * 
     * @param id 采购到货ID
     * @return 采购到货
     */
    public PurchaseDelivery selectPurchaseDeliveryById(Long id);

    /**
     * 查询采购到货列表
     * 
     * @param purchaseDelivery 采购到货
     * @return 采购到货集合
     */
    public List<PurchaseDelivery> selectPurchaseDeliveryList(PurchaseDelivery purchaseDelivery);
    public List<PurchaseDeliveryExport> selectPurchaseDeliveryExportList(PurchaseDelivery purchaseDelivery);

    /**
     * 新增采购到货
     * 
     * @param purchaseDelivery 采购到货
     * @return 结果
     */
    public int insertPurchaseDelivery(PurchaseDelivery purchaseDelivery);

    /**
     * 修改采购到货
     * 
     * @param purchaseDelivery 采购到货
     * @return 结果
     */
    public int updatePurchaseDelivery(PurchaseDelivery purchaseDelivery);

    /**
     * 删除采购到货
     * 
     * @param id 采购到货ID
     * @return 结果
     */
    public int deletePurchaseDeliveryById(Long id);
    public int deletePurchaseDeliveryMaterialsById(Long id);

    /**
     * 批量删除采购到货
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseDeliveryByIds(String[] ids);
}
