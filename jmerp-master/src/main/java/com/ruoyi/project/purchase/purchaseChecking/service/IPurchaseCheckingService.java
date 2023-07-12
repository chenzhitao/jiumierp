package com.ruoyi.project.purchase.purchaseChecking.service;

import java.util.List;
import com.ruoyi.project.purchase.purchaseChecking.domain.PurchaseChecking;
import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturn;

/**
 * 采购对账Service接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface IPurchaseCheckingService 
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
     * 批量删除采购对账
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseCheckingByIds(String ids);

    /**
     * 删除采购对账信息
     * 
     * @param id 采购对账ID
     * @return 结果
     */
    public int deletePurchaseCheckingById(Long id);

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    public PurchaseChecking createPurchaseChecking(PurchaseChecking purchaseChecking);

    /**
     * 计算金额
     * @Author 方舟
     * @Date 2021/5/27 14:45:12
     **/
    public PurchaseChecking calculateAmount(PurchaseChecking purchaseChecking);
}
