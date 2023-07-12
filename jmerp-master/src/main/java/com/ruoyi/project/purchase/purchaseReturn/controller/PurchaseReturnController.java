package com.ruoyi.project.purchase.purchaseReturn.controller;

import java.util.List;

import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.config.configSupplierContact.service.IConfigSupplierContactService;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturnExport;
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
import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturn;
import com.ruoyi.project.purchase.purchaseReturn.service.IPurchaseReturnService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购退货Controller
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Controller
@RequestMapping("/purchase/purchaseReturn")
public class PurchaseReturnController extends BaseController
{
    private String prefix = "purchase/purchaseReturn";

    @Autowired
    private IPurchaseReturnService purchaseReturnService;
    @Autowired
    private IConfigSupplierContactService configSupplierContactService;

    @RequiresPermissions("purchase:purchaseReturn:view")
    @GetMapping()
    public String purchaseReturn()
    {
        return prefix + "/purchaseReturn";
    }

    /**
     * 查询采购退货列表
     */
    @RequiresPermissions("purchase:purchaseReturn:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PurchaseReturn purchaseReturn)
    {
        startPage();
        List<PurchaseReturn> list = purchaseReturnService.selectPurchaseReturnList(purchaseReturn);
        return getDataTable(list);
    }

    /**
     * 导出采购退货列表
     */
    @RequiresPermissions("purchase:purchaseReturn:export")
    @Log(title = "采购退货", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseReturn purchaseReturn)
    {
        List<PurchaseReturnExport> list = purchaseReturnService.selectPurchaseReturnExportList(purchaseReturn);
        ExcelUtil<PurchaseReturnExport> util = new ExcelUtil<PurchaseReturnExport>(PurchaseReturnExport.class);
        return util.exportExcel(list, "采购退货");
    }

    /**
     * 新增采购退货
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存采购退货
     */
    @RequiresPermissions("purchase:purchaseReturn:add")
    @Log(title = "采购退货", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchaseReturn purchaseReturn)
    {
        return toAjax(purchaseReturnService.insertPurchaseReturn(purchaseReturn));
    }

    /**
     * 修改采购退货
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseReturn purchaseReturn = purchaseReturnService.selectPurchaseReturnById(id);
        mmap.put("purchaseReturn", purchaseReturn);
        return prefix + "/purchaseReturnDetail";
    }

    /**
     * 修改保存采购退货
     */
    @RequiresPermissions("purchase:purchaseReturn:edit")
    @Log(title = "采购退货", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchaseReturn purchaseReturn)
    {
        return toAjax(purchaseReturnService.updatePurchaseReturn(purchaseReturn));
    }

    /**
     * 删除采购退货
     */
    @RequiresPermissions("purchase:purchaseReturn:remove")
    @Log(title = "采购退货", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseReturnService.deletePurchaseReturnByIds(ids));
    }


    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("purchase:purchaseReturn:add")
    @Log(title = "创建采购退货", businessType = BusinessType.INSERT)
    @PostMapping("/createPurchaseReturn")
    @ResponseBody
    public AjaxResult createPurchaseReturn(PurchaseReturn purchaseReturn)
    {
        PurchaseReturn result = purchaseReturnService.createPurchaseReturn(purchaseReturn);
        return AjaxResult.success("创建采购退货成功",result);
    }

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @PostMapping("/calculatorAmount")
    @ResponseBody
    public AjaxResult calculatorAmount(PurchaseReturn purchaseReturn)
    {
        return AjaxResult.success(purchaseReturnService.calculatorAmount(purchaseReturn));
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
        PurchaseReturn purchaseReturn = new PurchaseReturn();
        purchaseReturn = purchaseReturnService.selectPurchaseReturnById(id);
        mmap.put("purchaseReturn", purchaseReturn);
        return prefix + "/print";
    }
}
