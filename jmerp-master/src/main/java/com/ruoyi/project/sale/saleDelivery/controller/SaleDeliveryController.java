package com.ruoyi.project.sale.saleDelivery.controller;

import java.util.List;

import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDeliveryExport;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
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
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.service.ISaleDeliveryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 送货单Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleDelivery")
public class SaleDeliveryController extends BaseController
{
    private String prefix = "sale/saleDelivery";

    @Autowired
    private ISaleDeliveryService saleDeliveryService;

    @RequiresPermissions("sale:saleDelivery:view")
    @GetMapping()
    public String saleDelivery()
    {
        return prefix + "/saleDelivery";
    }

    /**
     * 查询送货单列表
     */
    @RequiresPermissions("sale:saleDelivery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleDelivery saleDelivery)
    {
        startPage();
        List<SaleDelivery> list = saleDeliveryService.selectSaleDeliveryList(saleDelivery);
        return getDataTable(list);
    }

    /**
     * 导出送货单列表
     */
    @RequiresPermissions("sale:saleDelivery:export")
    @Log(title = "送货单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleDelivery saleDelivery)
    {
        List<SaleDeliveryExport> list = saleDeliveryService.selectSaleDeliveryExportList(saleDelivery);
        ExcelUtil<SaleDeliveryExport> util = new ExcelUtil<SaleDeliveryExport>(SaleDeliveryExport.class);
        return util.exportExcel(list, "送货单");
    }

    /**
     * 新增送货单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存送货单
     */
    @RequiresPermissions("sale:saleDelivery:add")
    @Log(title = "送货单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleDelivery saleDelivery)
    {
        return toAjax(saleDeliveryService.insertSaleDelivery(saleDelivery));
    }

    /**
     * 修改送货单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleDelivery saleDelivery = saleDeliveryService.selectSaleDeliveryById(id);
        mmap.put("saleDelivery", saleDelivery);
        return prefix + "/saleDeliveryDetail";
    }

    /**
     * 修改保存送货单
     */
    @RequiresPermissions("sale:saleDelivery:edit")
    @Log(title = "送货单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleDelivery saleDelivery)
    {
        return toAjax(saleDeliveryService.updateSaleDelivery(saleDelivery));
    }

    /**
     * 删除送货单
     */
    @RequiresPermissions("sale:saleDelivery:remove")
    @Log(title = "送货单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleDeliveryService.deleteSaleDeliveryByIds(ids));
    }


    /**
     * 创建外发到货
     */
    @RequiresPermissions("sale:saleDelivery:add")
    @Log(title = "创建送货单", businessType = BusinessType.INSERT)
    @PostMapping("/createSaleDelivery")
    @ResponseBody
    public AjaxResult createSaleDelivery(SaleDelivery saleDelivery){
        return AjaxResult.success(saleDeliveryService.createSaleDelivery(saleDelivery));
    }

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @PostMapping("/countAmount")
    @ResponseBody
    public AjaxResult countAmount(SaleDelivery saleDelivery) {
        SaleDelivery result = saleDeliveryService.countAmount(saleDelivery);
        return AjaxResult.success("计算成功", result);
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print//{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleDelivery saleDelivery = new SaleDelivery();
        saleDelivery = saleDeliveryService.selectSaleDeliveryById(id);
        mmap.put("saleDelivery", saleDelivery);
        return prefix + "/print";
    }
}
