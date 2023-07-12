package com.ruoyi.project.sale.saleChecking.controller;

import java.util.List;

import com.ruoyi.project.purchase.purchaseChecking.domain.PurchaseChecking;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturn;
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
import com.ruoyi.project.sale.saleChecking.domain.SaleChecking;
import com.ruoyi.project.sale.saleChecking.service.ISaleCheckingService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 销售对账Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleChecking")
public class SaleCheckingController extends BaseController
{
    private String prefix = "sale/saleChecking";

    @Autowired
    private ISaleCheckingService saleCheckingService;

    @RequiresPermissions("sale:saleChecking:view")
    @GetMapping()
    public String saleChecking()
    {
        return prefix + "/saleChecking";
    }

    /**
     * 查询销售对账列表
     */
    @RequiresPermissions("sale:saleChecking:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleChecking saleChecking)
    {
        startPage();
        List<SaleChecking> list = saleCheckingService.selectSaleCheckingList(saleChecking);
        return getDataTable(list);
    }

    /**
     * 导出销售对账列表
     */
    @RequiresPermissions("sale:saleChecking:export")
    @Log(title = "销售对账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleChecking saleChecking)
    {
        List<SaleChecking> list = saleCheckingService.selectSaleCheckingList(saleChecking);
        ExcelUtil<SaleChecking> util = new ExcelUtil<SaleChecking>(SaleChecking.class);
        return util.exportExcel(list, "销售对账");
    }

    /**
     * 新增销售对账
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存销售对账
     */
    @RequiresPermissions("sale:saleChecking:add")
    @Log(title = "销售对账", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleChecking saleChecking)
    {
        return toAjax(saleCheckingService.insertSaleChecking(saleChecking));
    }

    /**
     * 修改销售对账
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleChecking saleChecking = saleCheckingService.selectSaleCheckingById(id);
        mmap.put("saleChecking", saleChecking);
        return prefix + "/saleCheckingDetail";
    }

    /**
     * 修改保存销售对账
     */
    @RequiresPermissions("sale:saleChecking:edit")
    @Log(title = "销售对账", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleChecking saleChecking)
    {
        return toAjax(saleCheckingService.updateSaleChecking(saleChecking));
    }

    /**
     * 删除销售对账
     */
    @RequiresPermissions("sale:saleChecking:remove")
    @Log(title = "销售对账", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleCheckingService.deleteSaleCheckingByIds(ids));
    }


    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("sale:saleChecking:add")
    @Log(title = "创建销售对账", businessType = BusinessType.INSERT)
    @PostMapping("/createSaleChecking")
    @ResponseBody
    public AjaxResult createSaleChecking(SaleChecking saleChecking)
    {
        SaleChecking result = saleCheckingService.createSaleChecking(saleChecking);
        return AjaxResult.success("创建销售对账成功",result);
    }

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @PostMapping("/calculateAmount")
    @ResponseBody
    public AjaxResult calculateAmount(SaleChecking saleChecking)
    {
        return AjaxResult.success(saleCheckingService.calculateAmount(saleChecking));
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleChecking saleChecking = new SaleChecking();
        saleChecking = saleCheckingService.selectSaleCheckingById(id);
        mmap.put("saleChecking", saleChecking);
        return prefix + "/print";
    }
}
