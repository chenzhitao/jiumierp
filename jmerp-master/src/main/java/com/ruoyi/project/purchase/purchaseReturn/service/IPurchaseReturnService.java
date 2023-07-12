package com.ruoyi.project.purchase.purchaseReturn.service;

import java.util.List;

import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturn;
import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturnExport;

/**
 * 采购退货Service接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface IPurchaseReturnService 
{
    /**
     * 查询采购退货
     * 
     * @param id 采购退货ID
     * @return 采购退货
     */
    public PurchaseReturn selectPurchaseReturnById(Long id);

    /**
     * 查询采购退货列表
     * 
     * @param purchaseReturn 采购退货
     * @return 采购退货集合
     */
    public List<PurchaseReturn> selectPurchaseReturnList(PurchaseReturn purchaseReturn);
    public List<PurchaseReturnExport> selectPurchaseReturnExportList(PurchaseReturn purchaseReturn);

    /**
     * 新增采购退货
     * 
     * @param purchaseReturn 采购退货
     * @return 结果
     */
    public int insertPurchaseReturn(PurchaseReturn purchaseReturn);

    /**
     * 修改采购退货
     * 
     * @param purchaseReturn 采购退货
     * @return 结果
     */
    public int updatePurchaseReturn(PurchaseReturn purchaseReturn);

    /**
     * 批量删除采购退货
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseReturnByIds(String ids);

    /**
     * 删除采购退货信息
     * 
     * @param id 采购退货ID
     * @return 结果
     */
    public int deletePurchaseReturnById(Long id);

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    public PurchaseReturn createPurchaseReturn(PurchaseReturn purchaseReturn);

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    public PurchaseReturn calculatorAmount(PurchaseReturn purchaseReturn);
}
