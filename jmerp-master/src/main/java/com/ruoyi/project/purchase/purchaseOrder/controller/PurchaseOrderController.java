package com.ruoyi.project.purchase.purchaseOrder.controller;

import java.util.List;

import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.produce.produceWarehouse.domain.ProduceWarehouse;
import com.ruoyi.project.purchase.purchaseOrderMaterials.domain.PurchaseOrderMaterials;
import com.ruoyi.project.purchase.purchaseOrderMaterials.service.IPurchaseOrderMaterialsService;
import com.ruoyi.project.purchase.purchaseRequest.domain.PurchaseRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
import com.ruoyi.project.purchase.purchaseOrder.service.IPurchaseOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购订单Controller
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Controller
@RequestMapping("/purchase/purchaseOrder")
public class PurchaseOrderController extends BaseController
{
    private String prefix = "purchase/purchaseOrder";

    @Autowired
    private IPurchaseOrderService purchaseOrderService;
    @Autowired
    private IPurchaseOrderMaterialsService purchaseOrderMaterialsService;

    @RequiresPermissions("purchase:purchaseOrder:view")
    @GetMapping()
    public String purchaseOrder()
    {
        return prefix + "/purchaseOrder";
    }

    /**
     * 查询采购订单列表
     */
    @RequiresPermissions("purchase:purchaseOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PurchaseOrder purchaseOrder)
    {
        startPage();
        List<PurchaseOrder> list = purchaseOrderService.selectPurchaseOrderList(purchaseOrder);
        return getDataTable(list);
    }

    /**
     * 导出采购订单列表
     */
    @RequiresPermissions("purchase:purchaseOrder:export")
    @Log(title = "采购订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseOrder purchaseOrder)
    {
        List<PurchaseOrderMaterials> list = purchaseOrderMaterialsService.selectPurchaseOrderMaterialsList(purchaseOrder);
        ExcelUtil<PurchaseOrderMaterials> util = new ExcelUtil<PurchaseOrderMaterials>(PurchaseOrderMaterials.class);
        return util.exportExcel(list, "采购订单");
    }

    /**
     * 新增采购订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购订单
     */
    @RequiresPermissions("purchase:purchaseOrder:add")
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchaseOrder purchaseOrder)
    {
        return toAjax(purchaseOrderService.insertPurchaseOrder(purchaseOrder));
    }

    /**
     * 修改采购订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseOrder purchaseOrder = purchaseOrderService.selectPurchaseOrderById(id);
        mmap.put("purchaseOrder", purchaseOrder);
        return prefix + "/purchaseOrderDetail";
    }

    /**
     * 修改保存采购订单
     */
    @RequiresPermissions("purchase:purchaseOrder:edit")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchaseOrder purchaseOrder)
    {
        return toAjax(purchaseOrderService.updatePurchaseOrder(purchaseOrder));
    }

    /**
     * 删除采购订单
     */
    @RequiresPermissions("purchase:purchaseOrder:remove")
    @Log(title = "采购订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseOrderService.deletePurchaseOrderByIds(ids));
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("purchase:purchaseOrder:add")
    @Log(title = "创建采购订单", businessType = BusinessType.INSERT)
    @PostMapping("/createPurchaseOrder")
    @ResponseBody
    public AjaxResult createPurchaseOrder(PurchaseOrder purchaseOrder)
    {
        PurchaseOrder result = purchaseOrderService.createPurchaseOrder(purchaseOrder);
        return AjaxResult.success("创建采购订单成功",result);
    }

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @PostMapping("/calculatorAmount")
    @ResponseBody
    public AjaxResult calculatorAmount(PurchaseOrder purchaseOrder)
    {
        return AjaxResult.success(purchaseOrderService.calculatorAmount(purchaseOrder));
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder = purchaseOrderService.selectPurchaseOrderById(id);
        mmap.put("purchaseOrder", purchaseOrder);
        return prefix + "/print";
    }

}
