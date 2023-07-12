package com.ruoyi.project.sale.saleOrderMaterials.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrder.service.ISaleOrderService;
import com.ruoyi.project.sale.saleOrderDelivery.domain.SaleOrderDelivery;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
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
import com.ruoyi.project.sale.saleOrderMaterials.domain.SaleOrderMaterials;
import com.ruoyi.project.sale.saleOrderMaterials.service.ISaleOrderMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 客户带料Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleOrderMaterials")
public class SaleOrderMaterialsController extends BaseController
{
    private String prefix = "sale/saleOrderMaterials";

    @Autowired
    private ISaleOrderMaterialsService saleOrderMaterialsService;

    @Autowired
    private ISaleOrderService saleOrderService;

    @RequiresPermissions("sale:saleOrderMaterials:view")
    @GetMapping()
    public String saleOrderMaterials()
    {
        return prefix + "/saleOrderMaterials";
    }

    /**
     * 查询客户带料列表
     */
    @RequiresPermissions("sale:saleOrderMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleOrderMaterials saleOrderMaterials)
    {
        startPage();
        List<SaleOrderMaterials> list = saleOrderMaterialsService.selectSaleOrderMaterialsList(saleOrderMaterials);
        return getDataTable(list);
    }

    @PostMapping("/selectlist")
    @ResponseBody
    public AjaxResult selectlist(SaleOrderMaterials saleOrderMaterials)
    {
        List<SaleOrderMaterials> list = saleOrderMaterialsService.selectSaleOrderMaterialsList(saleOrderMaterials);
        return AjaxResult.success(list);
    }

    /**
     * 导出客户带料列表
     */
    @RequiresPermissions("sale:saleOrderMaterials:export")
    @Log(title = "客户带料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleOrderMaterials saleOrderMaterials)
    {
        List<SaleOrderMaterials> list = saleOrderMaterialsService.selectSaleOrderMaterialsList(saleOrderMaterials);
        ExcelUtil<SaleOrderMaterials> util = new ExcelUtil<SaleOrderMaterials>(SaleOrderMaterials.class);
        return util.exportExcel(list, "saleOrderMaterials");
    }

    /**
     * 新增客户带料
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleOrder saleOrder = saleOrderService.selectSaleOrderById(id);
        SaleOrderMaterials saleOrderMaterials = new SaleOrderMaterials();
        BeanUtils.copyProperties(saleOrder, saleOrderMaterials);
        saleOrderMaterials.setId(-1L);
        saleOrderMaterials.setQty(0);
        saleOrderMaterials.setSaleOrderId(id);
        mmap.put("saleOrderMaterials", saleOrderMaterials);
        return prefix + "/edit";
    }

    /**
     * 新增保存客户带料
     */
    @RequiresPermissions("sale:saleOrderMaterials:add")
    @Log(title = "客户带料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleOrderMaterials saleOrderMaterials)
    {
        return toAjax(saleOrderMaterialsService.insertSaleOrderMaterials(saleOrderMaterials));
    }

    /**
     * 修改客户带料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleOrderMaterials saleOrderMaterials = saleOrderMaterialsService.selectSaleOrderMaterialsById(id);
        mmap.put("saleOrderMaterials", saleOrderMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户带料
     */
    @RequiresPermissions("sale:saleOrderMaterials:edit")
    @Log(title = "客户带料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleOrderMaterials saleOrderMaterials)
    {
        return toAjax(saleOrderMaterialsService.updateSaleOrderMaterials(saleOrderMaterials));
    }

    /**
     * 删除客户带料
     */
    @RequiresPermissions("sale:saleOrderMaterials:remove")
    @Log(title = "客户带料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleOrderMaterialsService.deleteSaleOrderMaterialsByIds(ids));
    }
}
