package com.ruoyi.project.purchase.purchaseChecking.mapper;

import java.util.List;
import com.ruoyi.project.purchase.purchaseChecking.domain.PurchaseChecking;

/**
 * 采购对账Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface PurchaseCheckingMapper 
{
    /**
     * 查询采购对账
     * 
     * @param id 采购对账ID
     * @return 采购对账
     */
    public PurchaseChecking selectPurchaseCheckingById(Long id);

    /**
     * 查询采购对账列表
     * 
     * @param purchaseChecking 采购对账
     * @return 采购对账集合
     */
    public List<PurchaseChecking> selectPurchaseCheckingList(PurchaseChecking purchaseChecking);

    /**
     * 新增采购对账
     * 
     * @param purchaseChecking 采购对账
     * @return 结果
     */
    public int insertPurchaseChecking(PurchaseChecking purchaseChecking);

    /**
     * 修改采购对账
     * 
     * @param purchaseChecking 采购对账
     * @return 结果
     */
    public int updatePurchaseChecking(PurchaseChecking purchaseChecking);

    /**
     * 删除采购对账
     * 
     * @param id 采购对账ID
     * @return 结果
     */
    public int deletePurchaseCheckingById(Long id);
    public int deletePurchaseCheckingMaterialsById(Long id);

    /**
     * 批量删除采购对账
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseCheckingByIds(String[] ids);
}
