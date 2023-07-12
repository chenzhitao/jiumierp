package com.ruoyi.project.purchase.purchaseChecking.controller;

import java.util.List;

import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturn;
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
import com.ruoyi.project.purchase.purchaseChecking.domain.PurchaseChecking;
import com.ruoyi.project.purchase.purchaseChecking.service.IPurchaseCheckingService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购对账Controller
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Controller
@RequestMapping("/purchase/purchaseChecking")
public class PurchaseCheckingController extends BaseController
{
    private String prefix = "purchase/purchaseChecking";

    @Autowired
    private IPurchaseCheckingService purchaseCheckingService;

    @RequiresPermissions("purchase:purchaseChecking:view")
    @GetMapping()
    public String purchaseChecking()
    {
        return prefix + "/purchaseChecking";
    }

    /**
     * 查询采购对账列表
     */
    @RequiresPermissions("purchase:purchaseChecking:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PurchaseChecking purchaseChecking)
    {
        startPage();
        List<PurchaseChecking> list = purchaseCheckingService.selectPurchaseCheckingList(purchaseChecking);
        return getDataTable(list);
    }

    /**
     * 导出采购对账列表
     */
    @RequiresPermissions("purchase:purchaseChecking:export")
    @Log(title = "采购对账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseChecking purchaseChecking)
    {
        List<PurchaseChecking> list = purchaseCheckingService.selectPurchaseCheckingList(purchaseChecking);
        ExcelUtil<PurchaseChecking> util = new ExcelUtil<PurchaseChecking>(PurchaseChecking.class);
        return util.exportExcel(list, "采购对账");
    }

    /**
     * 新增采购对账
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购对账
     */
    @RequiresPermissions("purchase:purchaseChecking:add")
    @Log(title = "采购对账", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchaseChecking purchaseChecking)
    {
        return toAjax(purchaseCheckingService.insertPurchaseChecking(purchaseChecking));
    }

    /**
     * 修改采购对账
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseChecking purchaseChecking = purchaseCheckingService.selectPurchaseCheckingById(id);
        mmap.put("purchaseChecking", purchaseChecking);
        return prefix + "/purchaseCheckingDetail";
    }

    /**
     * 修改保存采购对账
     */
    @RequiresPermissions("purchase:purchaseChecking:edit")
    @Log(title = "采购对账", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchaseChecking purchaseChecking)
    {
        return toAjax(purchaseCheckingService.updatePurchaseChecking(purchaseChecking));
    }

    /**
     * 删除采购对账
     */
    @RequiresPermissions("purchase:purchaseChecking:remove")
    @Log(title = "采购对账", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseCheckingService.deletePurchaseCheckingByIds(ids));
    }


    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("purchase:purchaseChecking:add")
    @Log(title = "创建采购对账", businessType = BusinessType.INSERT)
    @PostMapping("/createPurchaseChecking")
    @ResponseBody
    public AjaxResult createPurchaseChecking(PurchaseChecking purchaseChecking)
    {
        PurchaseChecking result = purchaseCheckingService.createPurchaseChecking(purchaseChecking);
        return AjaxResult.success("创建采购对账成功",result);
    }

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @PostMapping("/calculateAmount")
    @ResponseBody
    public AjaxResult calculateAmount(PurchaseChecking purchaseChecking)
    {
        return AjaxResult.success(purchaseCheckingService.calculateAmount(purchaseChecking));
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseChecking purchaseChecking = new PurchaseChecking();
        purchaseChecking = purchaseCheckingService.selectPurchaseCheckingById(id);
        mmap.put("purchaseChecking", purchaseChecking);
        return prefix + "/print";
    }
}
