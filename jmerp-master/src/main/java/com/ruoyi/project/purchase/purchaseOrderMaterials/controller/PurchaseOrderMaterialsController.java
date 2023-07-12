package com.ruoyi.project.purchase.purchaseOrderMaterials.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseDelivery.service.IPurchaseDeliveryService;
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
import com.ruoyi.project.purchase.purchaseOrderMaterials.domain.PurchaseOrderMaterials;
import com.ruoyi.project.purchase.purchaseOrderMaterials.service.IPurchaseOrderMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购订单材料Controller
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Controller
@RequestMapping("/purchase/purchaseOrderMaterials")
public class PurchaseOrderMaterialsController extends BaseController
{
    private String prefix = "purchase/purchaseOrderMaterials";

    @Autowired
    private IPurchaseOrderMaterialsService purchaseOrderMaterialsService;

    @RequiresPermissions("purchase:purchaseOrderMaterials:view")
    @GetMapping()
    public String purchaseOrderMaterials()
    {
        return prefix + "/purchaseOrderMaterials";
    }

    /**
     * 查询采购订单材料列表
     */
    @RequiresPermissions("purchase:purchaseOrderMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PurchaseOrderMaterials purchaseOrderMaterials)
    {
        startPage();
        List<PurchaseOrderMaterials> list = purchaseOrderMaterialsService.selectPurchaseOrderMaterialsList(purchaseOrderMaterials);
        return getDataTable(list);
    }

    /**
     * 导出采购订单材料列表
     */
    @RequiresPermissions("purchase:purchaseOrderMaterials:export")
    @Log(title = "采购订单材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseOrderMaterials purchaseOrderMaterials)
    {
        List<PurchaseOrderMaterials> list = purchaseOrderMaterialsService.selectPurchaseOrderMaterialsList(purchaseOrderMaterials);
        ExcelUtil<PurchaseOrderMaterials> util = new ExcelUtil<PurchaseOrderMaterials>(PurchaseOrderMaterials.class);
        return util.exportExcel(list, "采购订单材料数据");
    }

    /**
     * 新增采购订单材料
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseOrderMaterials purchaseOrderMaterials = new PurchaseOrderMaterials();
        purchaseOrderMaterials.setPurchaseOrderId(id);
        purchaseOrderMaterials.setId(-1L);
        purchaseOrderMaterials.setQty(0);
        purchaseOrderMaterials.setPrice(new BigDecimal(0));
        purchaseOrderMaterials.setDeliveryDate(DateUtils.getNowDate());
        mmap.put("purchaseOrderMaterials", purchaseOrderMaterials);
        return prefix + "/edit";
    }

    /**
     * 新增保存采购订单材料
     */
    @RequiresPermissions("purchase:purchaseOrderMaterials:add")
    @Log(title = "采购订单材料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchaseOrderMaterials purchaseOrderMaterials)
    {
        return toAjax(purchaseOrderMaterialsService.insertPurchaseOrderMaterials(purchaseOrderMaterials));
    }

    /**
     * 修改采购订单材料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseOrderMaterials purchaseOrderMaterials = purchaseOrderMaterialsService.selectPurchaseOrderMaterialsById(id);
        mmap.put("purchaseOrderMaterials", purchaseOrderMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购订单材料
     */
    @RequiresPermissions("purchase:purchaseOrderMaterials:edit")
    @Log(title = "采购订单材料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchaseOrderMaterials purchaseOrderMaterials)
    {
        return toAjax(purchaseOrderMaterialsService.updatePurchaseOrderMaterials(purchaseOrderMaterials));
    }

    /**
     * 删除采购订单材料
     */
    @RequiresPermissions("purchase:purchaseOrderMaterials:remove")
    @Log(title = "采购订单材料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseOrderMaterialsService.deletePurchaseOrderMaterialsByIds(ids));
    }


    /**
     * 选择销售产品
     */
    @GetMapping("/selectPurchaseOrderMaterials/{id}/{supplierId}")
    public String selectPurchaseOrderMaterials(@PathVariable("id") Long id,@PathVariable("supplierId") Long supplierId, ModelMap mmap)
    {
        PurchaseOrderMaterials purchaseOrderMaterials = new PurchaseOrderMaterials();
        purchaseOrderMaterials.setPurchaseDeliveryId(id);
        if(supplierId!=-1){
            purchaseOrderMaterials.setSupplierId(supplierId);
        }
        mmap.put("purchaseOrderMaterials", purchaseOrderMaterials);
        return prefix + "/selectPurchaseOrderMaterials";
    }
 }
