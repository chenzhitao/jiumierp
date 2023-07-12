package com.ruoyi.project.purchase.purchaseReturnMaterials.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain.PurchaseDeliveryMaterials;
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
import com.ruoyi.project.purchase.purchaseReturnMaterials.domain.PurchaseReturnMaterials;
import com.ruoyi.project.purchase.purchaseReturnMaterials.service.IPurchaseReturnMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购退货材料Controller
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Controller
@RequestMapping("/purchase/purchaseReturnMaterials")
public class PurchaseReturnMaterialsController extends BaseController
{
    private String prefix = "purchase/purchaseReturnMaterials";

    @Autowired
    private IPurchaseReturnMaterialsService purchaseReturnMaterialsService;

    @RequiresPermissions("purchase:purchaseReturnMaterials:view")
    @GetMapping()
    public String purchaseReturnMaterials()
    {
        return prefix + "/purchaseReturnMaterials";
    }

    /**
     * 查询采购退货材料列表
     */
    @RequiresPermissions("purchase:purchaseReturnMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PurchaseReturnMaterials purchaseReturnMaterials)
    {
        startPage();
        List<PurchaseReturnMaterials> list = purchaseReturnMaterialsService.selectPurchaseReturnMaterialsList(purchaseReturnMaterials);
        return getDataTable(list);
    }

    /**
     * 导出采购退货材料列表
     */
    @RequiresPermissions("purchase:purchaseReturnMaterials:export")
    @Log(title = "采购退货材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseReturnMaterials purchaseReturnMaterials)
    {
        List<PurchaseReturnMaterials> list = purchaseReturnMaterialsService.selectPurchaseReturnMaterialsList(purchaseReturnMaterials);
        ExcelUtil<PurchaseReturnMaterials> util = new ExcelUtil<PurchaseReturnMaterials>(PurchaseReturnMaterials.class);
        return util.exportExcel(list, "采购退货材料数据");
    }

    /**
     * 新增采购退货材料
     */
    /**
     * 新增采购订单材料
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseReturnMaterials purchaseReturnMaterials = new PurchaseReturnMaterials();
        purchaseReturnMaterials.setPurchaseReturnId(id);
        purchaseReturnMaterials.setId(-1L);
        purchaseReturnMaterials.setReturnQty(0);
        purchaseReturnMaterials.setPrice(new BigDecimal(0));
        purchaseReturnMaterials.setReturnType("THKK");
        purchaseReturnMaterials.setReturnDate(DateUtils.getNowDate());
        mmap.put("purchaseReturnMaterials", purchaseReturnMaterials);
        return prefix + "/edit";
    }

    /**
     * 新增保存采购退货材料
     */
    @RequiresPermissions("purchase:purchaseReturnMaterials:add")
    @Log(title = "采购退货材料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchaseReturnMaterials purchaseReturnMaterials)
    {
        return toAjax(purchaseReturnMaterialsService.insertPurchaseReturnMaterials(purchaseReturnMaterials));
    }

    /**
     * 修改采购退货材料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseReturnMaterials purchaseReturnMaterials = purchaseReturnMaterialsService.selectPurchaseReturnMaterialsById(id);
        mmap.put("purchaseReturnMaterials", purchaseReturnMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购退货材料
     */
    @RequiresPermissions("purchase:purchaseReturnMaterials:edit")
    @Log(title = "采购退货材料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchaseReturnMaterials purchaseReturnMaterials)
    {
        return toAjax(purchaseReturnMaterialsService.updatePurchaseReturnMaterials(purchaseReturnMaterials));
    }

    /**
     * 删除采购退货材料
     */
    @RequiresPermissions("purchase:purchaseReturnMaterials:remove")
    @Log(title = "采购退货材料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseReturnMaterialsService.deletePurchaseReturnMaterialsByIds(ids));
    }
}
