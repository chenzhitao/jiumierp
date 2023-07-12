package com.ruoyi.project.sale.saleQuotationMultDetail.controller;

import java.util.List;
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
import com.ruoyi.project.sale.saleQuotationMultDetail.domain.SaleQuotationMultDetail;
import com.ruoyi.project.sale.saleQuotationMultDetail.service.ISaleQuotationMultDetailService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 多数量报价明细Controller
 * 
 * @author fangzhou
 * @date 2021-04-21
 */
@Controller
@RequestMapping("/sale/saleQuotationMultDetail")
public class SaleQuotationMultDetailController extends BaseController
{
    private String prefix = "sale/saleQuotationMultDetail";

    @Autowired
    private ISaleQuotationMultDetailService saleQuotationMultDetailService;

    @RequiresPermissions("sale:saleQuotationMultDetail:view")
    @GetMapping()
    public String saleQuotationMultDetail()
    {
        return prefix + "/saleQuotationMultDetail";
    }

    /**
     * 查询多数量报价明细列表
     */
    @RequiresPermissions("sale:saleQuotationMultDetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleQuotationMultDetail saleQuotationMultDetail)
    {
        startPage();
        List<SaleQuotationMultDetail> list = saleQuotationMultDetailService.selectSaleQuotationMultDetailList(saleQuotationMultDetail);
        return getDataTable(list);
    }

    /**
     * 导出多数量报价明细列表
     */
    @RequiresPermissions("sale:saleQuotationMultDetail:export")
    @Log(title = "多数量报价明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleQuotationMultDetail saleQuotationMultDetail)
    {
        List<SaleQuotationMultDetail> list = saleQuotationMultDetailService.selectSaleQuotationMultDetailList(saleQuotationMultDetail);
        ExcelUtil<SaleQuotationMultDetail> util = new ExcelUtil<SaleQuotationMultDetail>(SaleQuotationMultDetail.class);
        return util.exportExcel(list, "saleQuotationMultDetail");
    }

    /**
     * 新增多数量报价明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存多数量报价明细
     */
    @RequiresPermissions("sale:saleQuotationMultDetail:add")
    @Log(title = "多数量报价明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleQuotationMultDetail saleQuotationMultDetail)
    {
        return toAjax(saleQuotationMultDetailService.insertSaleQuotationMultDetail(saleQuotationMultDetail));
    }

    /**
     * 修改多数量报价明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleQuotationMultDetail saleQuotationMultDetail = saleQuotationMultDetailService.selectSaleQuotationMultDetailById(id);
        mmap.put("saleQuotationMultDetail", saleQuotationMultDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存多数量报价明细
     */
    @RequiresPermissions("sale:saleQuotationMultDetail:edit")
    @Log(title = "多数量报价明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleQuotationMultDetail saleQuotationMultDetail)
    {
        return toAjax(saleQuotationMultDetailService.updateSaleQuotationMultDetail(saleQuotationMultDetail));
    }

    /**
     * 删除多数量报价明细
     */
    @RequiresPermissions("sale:saleQuotationMultDetail:remove")
    @Log(title = "多数量报价明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleQuotationMultDetailService.deleteSaleQuotationMultDetailByIds(ids));
    }
}
