package com.ruoyi.project.purchase.purchaseDeliveryMaterials.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseDelivery.service.IPurchaseDeliveryService;
import com.ruoyi.project.purchase.purchaseOrderMaterials.domain.PurchaseOrderMaterials;
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
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain.PurchaseDeliveryMaterials;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.service.IPurchaseDeliveryMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购到货材料Controller
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Controller
@RequestMapping("/purchase/purchaseDeliveryMaterials")
public class PurchaseDeliveryMaterialsController extends BaseController
{
    private String prefix = "purchase/purchaseDeliveryMaterials";

    @Autowired
    private IPurchaseDeliveryMaterialsService purchaseDeliveryMaterialsService;
    @Autowired
    private IPurchaseDeliveryService purchaseDeliveryService;

    @RequiresPermissions("purchase:purchaseDeliveryMaterials:view")
    @GetMapping()
    public String purchaseDeliveryMaterials()
    {
        return prefix + "/purchaseDeliveryMaterials";
    }

    /**
     * 查询采购到货材料列表
     */
    @RequiresPermissions("purchase:purchaseDeliveryMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PurchaseDeliveryMaterials purchaseDeliveryMaterials)
    {
        startPage();
        List<PurchaseDeliveryMaterials> list = purchaseDeliveryMaterialsService.selectPurchaseDeliveryMaterialsList(purchaseDeliveryMaterials);
        return getDataTable(list);
    }

    /**
     * 导出采购到货材料列表
     */
    @RequiresPermissions("purchase:purchaseDeliveryMaterials:export")
    @Log(title = "采购到货材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseDeliveryMaterials purchaseDeliveryMaterials)
    {
        List<PurchaseDeliveryMaterials> list = purchaseDeliveryMaterialsService.selectPurchaseDeliveryMaterialsList(purchaseDeliveryMaterials);
        ExcelUtil<PurchaseDeliveryMaterials> util = new ExcelUtil<PurchaseDeliveryMaterials>(PurchaseDeliveryMaterials.class);
        return util.exportExcel(list, "采购到货材料数据");
    }

    /**
     * 新增采购订单材料
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseDeliveryMaterials purchaseDeliveryMaterials = new PurchaseDeliveryMaterials();
        purchaseDeliveryMaterials.setPurchaseDeliveryId(id);
        purchaseDeliveryMaterials.setId(-1L);
        purchaseDeliveryMaterials.setQty(0);
        purchaseDeliveryMaterials.setPrice(new BigDecimal(0));
        purchaseDeliveryMaterials.setDeliveryDate(DateUtils.getNowDate());
        mmap.put("purchaseDeliveryMaterials", purchaseDeliveryMaterials);
        return prefix + "/edit";
    }

    /**
     * 新增保存采购到货材料
     */
    @RequiresPermissions("purchase:purchaseDeliveryMaterials:add")
    @Log(title = "采购到货材料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchaseDeliveryMaterials purchaseDeliveryMaterials)
    {
        return toAjax(purchaseDeliveryMaterialsService.insertPurchaseDeliveryMaterials(purchaseDeliveryMaterials));
    }

    /**
     * 修改采购到货材料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseDeliveryMaterials purchaseDeliveryMaterials = purchaseDeliveryMaterialsService.selectPurchaseDeliveryMaterialsById(id);
        mmap.put("purchaseDeliveryMaterials", purchaseDeliveryMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购到货材料
     */
    @RequiresPermissions("purchase:purchaseDeliveryMaterials:edit")
    @Log(title = "采购到货材料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchaseDeliveryMaterials purchaseDeliveryMaterials)
    {
        return toAjax(purchaseDeliveryMaterialsService.updatePurchaseDeliveryMaterials(purchaseDeliveryMaterials));
    }

    /**
     * 删除采购到货材料
     */
    @RequiresPermissions("purchase:purchaseDeliveryMaterials:remove")
    @Log(title = "采购到货材料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseDeliveryMaterialsService.deletePurchaseDeliveryMaterialsByIds(ids));
    }

    /**
     * 选择销售产品
     */
    @GetMapping("/selectPurchaseDeliveryMaterials/{id}/{supplierId}/{rzyValue1}")
    public String selectPurchaseDeliveryMaterials(@PathVariable("id") Long id,@PathVariable("supplierId") Long supplierId,@PathVariable("rzyValue1") String rzyValue1, ModelMap mmap)
    {
        PurchaseDeliveryMaterials purchaseDeliveryMaterials = new PurchaseDeliveryMaterials();
        purchaseDeliveryMaterials.setPurchaseReturnId(id);
        purchaseDeliveryMaterials.setRzyValue1(rzyValue1);
        if(supplierId!=-1){
            purchaseDeliveryMaterials.setSupplierId(supplierId);
        }
        mmap.put("purchaseDeliveryMaterials", purchaseDeliveryMaterials);
        return prefix + "/selectPurchaseDeliveryMaterials";
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @PostMapping("/createPurchaseDelivery")
    @ResponseBody
    public AjaxResult createPurchaseDelivery(PurchaseDelivery purchaseDelivery)
    {
        PurchaseDelivery result = purchaseDeliveryService.createPurchaseDelivery(purchaseDelivery);
        return AjaxResult.success("添加到货材料",result);
    }
}
