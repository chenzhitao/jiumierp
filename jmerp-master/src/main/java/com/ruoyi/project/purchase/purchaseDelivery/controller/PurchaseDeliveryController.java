package com.ruoyi.project.purchase.purchaseDelivery.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.config.configSupplierContact.service.IConfigSupplierContactService;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDeliveryExport;
import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
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
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseDelivery.service.IPurchaseDeliveryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购到货Controller
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Controller
@RequestMapping("/purchase/purchaseDelivery")
public class PurchaseDeliveryController extends BaseController
{
    private String prefix = "purchase/purchaseDelivery";

    @Autowired
    private IPurchaseDeliveryService purchaseDeliveryService;
    @Autowired
    private IConfigSupplierContactService configSupplierContactService;

    @RequiresPermissions("purchase:purchaseDelivery:view")
    @GetMapping()
    public String purchaseDelivery()
    {
        return prefix + "/purchaseDelivery";
    }

    /**
     * 查询采购到货列表
     */
    @RequiresPermissions("purchase:purchaseDelivery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PurchaseDelivery purchaseDelivery)
    {
        startPage();
        List<PurchaseDelivery> list = purchaseDeliveryService.selectPurchaseDeliveryList(purchaseDelivery);
        return getDataTable(list);
    }

    /**
     * 导出采购到货列表
     */
    @RequiresPermissions("purchase:purchaseDelivery:export")
    @Log(title = "采购到货", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseDelivery purchaseDelivery)
    {
        List<PurchaseDeliveryExport> list = purchaseDeliveryService.selectPurchaseDeliveryExportList(purchaseDelivery);
        ExcelUtil<PurchaseDeliveryExport> util = new ExcelUtil<PurchaseDeliveryExport>(PurchaseDeliveryExport.class);
        return util.exportExcel(list, "采购到货");
    }

    /**
     * 新增采购到货
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购到货
     */
    @RequiresPermissions("purchase:purchaseDelivery:add")
    @Log(title = "采购到货", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchaseDelivery purchaseDelivery)
    {
        return toAjax(purchaseDeliveryService.insertPurchaseDelivery(purchaseDelivery));
    }

    /**
     * 修改采购到货
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseDelivery purchaseDelivery = purchaseDeliveryService.selectPurchaseDeliveryById(id);
        mmap.put("purchaseDelivery", purchaseDelivery);
        return prefix + "/purchaseDeliveryDetail";
    }

    /**
     * 修改保存采购到货
     */
    @RequiresPermissions("purchase:purchaseDelivery:edit")
    @Log(title = "采购到货", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchaseDelivery purchaseDelivery)
    {
        if(!StringUtils.isEmpty(purchaseDelivery.getRzyValue1())&&"approve".equals(purchaseDelivery.getRzyValue1())){
            purchaseDelivery.setApprover(ShiroUtils.getSysUser().getUserName());
            purchaseDelivery.setApprovalTime(DateUtils.getNowDate());
        }
        return toAjax(purchaseDeliveryService.updatePurchaseDelivery(purchaseDelivery));
    }

    /**
     * 删除采购到货
     */
    @RequiresPermissions("purchase:purchaseDelivery:remove")
    @Log(title = "采购到货", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseDeliveryService.deletePurchaseDeliveryByIds(ids));
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("purchase:purchaseDelivery:add")
    @Log(title = "创建采购到货", businessType = BusinessType.INSERT)
    @PostMapping("/createPurchaseDelivery")
    @ResponseBody
    public AjaxResult createPurchaseDelivery(PurchaseDelivery purchaseDelivery)
    {
        PurchaseDelivery result = purchaseDeliveryService.createPurchaseDelivery(purchaseDelivery);
        return AjaxResult.success("创建采购到货成功",result);
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("purchase:purchaseDelivery:add")
    @Log(title = "创建采购到货", businessType = BusinessType.INSERT)
    @PostMapping("/createPurchaseDeliveryByOrderId")
    @ResponseBody
    public AjaxResult createPurchaseDeliveryByOrderId(PurchaseOrder purchaseOrder)
    {
        PurchaseDelivery result = purchaseDeliveryService.createPurchaseDeliveryByOrderId(purchaseOrder);
        return AjaxResult.success("创建采购到货成功",result);
    }

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @PostMapping("/calculatorAmount")
    @ResponseBody
    public AjaxResult calculatorAmount(PurchaseDelivery purchaseDelivery)
    {
        return AjaxResult.success(purchaseDeliveryService.calculatorAmount(purchaseDelivery));
    }

    /**
     * 获取默认联系人
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @PostMapping("/getDefaultContact")
    @ResponseBody
    public AjaxResult getDefaultContact(ConfigSupplierContact configSupplierContact)
    {
        return AjaxResult.success(configSupplierContactService.getDefaultContact(configSupplierContact));
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseDelivery purchaseDelivery = new PurchaseDelivery();
        purchaseDelivery = purchaseDeliveryService.selectPurchaseDeliveryById(id);
        mmap.put("purchaseDelivery", purchaseDelivery);
        return prefix + "/print";
    }
}
