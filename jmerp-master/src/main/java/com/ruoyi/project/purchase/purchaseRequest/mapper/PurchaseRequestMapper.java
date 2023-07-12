package com.ruoyi.project.purchase.purchaseRequest.mapper;

import java.util.List;
import com.ruoyi.project.purchase.purchaseRequest.domain.PurchaseRequest;

/**
 * 采购申请Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface PurchaseRequestMapper 
{
    /**
     * 查询采购申请
     * 
     * @param id 采购申请ID
     * @return 采购申请
     */
    public PurchaseRequest selectPurchaseRequestById(Long id);

    /**
     * 查询采购申请列表
     * 
     * @param purchaseRequest 采购申请
     * @return 采购申请集合
     */
    public List<PurchaseRequest> selectPurchaseRequestList(PurchaseRequest purchaseRequest);

    /**
     * 新增采购申请
     * 
     * @param purchaseRequest 采购申请
     * @return 结果
     */
    public int insertPurchaseRequest(PurchaseRequest purchaseRequest);

    /**
     * 修改采购申请
     * 
     * @param purchaseRequest 采购申请
     * @return 结果
     */
    public int updatePurchaseRequest(PurchaseRequest purchaseRequest);

    /**
     * 删除采购申请
     * 
     * @param id 采购申请ID
     * @return 结果
     */
    public int deletePurchaseRequestById(Long id);

    /**
     * 批量删除采购申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseRequestByIds(String[] ids);
}
