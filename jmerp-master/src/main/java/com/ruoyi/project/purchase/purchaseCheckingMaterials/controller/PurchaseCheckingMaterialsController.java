package com.ruoyi.project.purchase.purchaseCheckingMaterials.controller;

import java.util.List;

import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseDelivery.service.IPurchaseDeliveryService;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain.PurchaseDeliveryMaterials;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.service.IPurchaseDeliveryMaterialsService;
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
import com.ruoyi.project.purchase.purchaseCheckingMaterials.domain.PurchaseCheckingMaterials;
import com.ruoyi.project.purchase.purchaseCheckingMaterials.service.IPurchaseCheckingMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购对账材料Controller
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Controller
@RequestMapping("/purchase/purchaseCheckingMaterials")
public class PurchaseCheckingMaterialsController extends BaseController
{
    private String prefix = "purchase/purchaseCheckingMaterials";

    @Autowired
    private IPurchaseCheckingMaterialsService purchaseCheckingMaterialsService;

    @RequiresPermissions("purchase:purchaseCheckingMaterials:view")
    @GetMapping()
    public String purchaseCheckingMaterials()
    {
        return prefix + "/purchaseCheckingMaterials";
    }

    /**
     * 查询采购对账材料列表
     */
    @RequiresPermissions("purchase:purchaseCheckingMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PurchaseCheckingMaterials purchaseCheckingMaterials)
    {
        startPage();
        List<PurchaseCheckingMaterials> list = purchaseCheckingMaterialsService.selectPurchaseCheckingMaterialsList(purchaseCheckingMaterials);
        return getDataTable(list);
    }

    /**
     * 导出采购对账材料列表
     */
    @RequiresPermissions("purchase:purchaseCheckingMaterials:export")
    @Log(title = "采购对账材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseCheckingMaterials purchaseCheckingMaterials)
    {
        List<PurchaseCheckingMaterials> list = purchaseCheckingMaterialsService.selectPurchaseCheckingMaterialsList(purchaseCheckingMaterials);
        ExcelUtil<PurchaseCheckingMaterials> util = new ExcelUtil<PurchaseCheckingMaterials>(PurchaseCheckingMaterials.class);
        return util.exportExcel(list, "采购对账材料数据");
    }

    /**
     * 新增采购对账材料
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购对账材料
     */
    @RequiresPermissions("purchase:purchaseCheckingMaterials:add")
    @Log(title = "采购对账材料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchaseCheckingMaterials purchaseCheckingMaterials)
    {
        return toAjax(purchaseCheckingMaterialsService.insertPurchaseCheckingMaterials(purchaseCheckingMaterials));
    }

    /**
     * 修改采购对账材料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseCheckingMaterials purchaseCheckingMaterials = purchaseCheckingMaterialsService.selectPurchaseCheckingMaterialsById(id);
        mmap.put("purchaseCheckingMaterials", purchaseCheckingMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购对账材料
     */
    @RequiresPermissions("purchase:purchaseCheckingMaterials:edit")
    @Log(title = "采购对账材料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchaseCheckingMaterials purchaseCheckingMaterials)
    {
        return toAjax(purchaseCheckingMaterialsService.updatePurchaseCheckingMaterials(purchaseCheckingMaterials));
    }

    /**
     * 删除采购对账材料
     */
    @RequiresPermissions("purchase:purchaseCheckingMaterials:remove")
    @Log(title = "采购对账材料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseCheckingMaterialsService.deletePurchaseCheckingMaterialsByIds(ids));
    }
}
