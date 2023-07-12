package com.ruoyi.project.purchase.purchaseRequest.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configEmployee.domain.ConfigEmployee;
import com.ruoyi.project.purchase.purchaseRequest.domain.PurchaseRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 采购申请Service接口
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public interface IPurchaseRequestService 
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
     * 批量删除采购申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchaseRequestByIds(String ids);

    /**
     * 删除采购申请信息
     * 
     * @param id 采购申请ID
     * @return 结果
     */
    public int deletePurchaseRequestById(Long id);

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    public PurchaseRequest createPurchaseRequest(PurchaseRequest purchaseRequest);

    /**
     * 导入用户数据
     *
     * @param purchaseRequestList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importPurchaseRequest(List<PurchaseRequest> purchaseRequestList, Boolean isUpdateSupport);
}
