package com.ruoyi.project.purchase.purchaseOrder.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 采购订单Service接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface IPurchaseOrderService 
{
    /**
     * 查询采购订单
     * 
     * @param id 采购订单ID
     * @return 采购订单
     */
    public PurchaseOrder selectPurchaseOrderById(Long id);

    /**
     * 查询采购订单列表
     * 
     * @param purchaseOrder 采购订单
     * @return 采购订单集合
     */
    public List<PurchaseOrder> selectPurchaseOrderList(PurchaseOrder purchaseOrder);

    /**
     * 新增采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    public int insertPurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 修改采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    public int updatePurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 批量删除采购订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseOrderByIds(String ids);

    /**
     * 删除采购订单信息
     * 
     * @param id 采购订单ID
     * @return 结果
     */
    public int deletePurchaseOrderById(Long id);


    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    public PurchaseOrder calculatorAmount(PurchaseOrder purchaseOrder);
}
