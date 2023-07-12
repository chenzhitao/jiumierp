package com.ruoyi.project.sale.saleOrderDelivery.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleOrderProduct.service.ISaleOrderProductService;
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
import com.ruoyi.project.sale.saleOrderDelivery.domain.SaleOrderDelivery;
import com.ruoyi.project.sale.saleOrderDelivery.service.ISaleOrderDeliveryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 送货排程Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleOrderDelivery")
public class SaleOrderDeliveryController extends BaseController
{
    private String prefix = "sale/saleOrderDelivery";

    @Autowired
    private ISaleOrderDeliveryService saleOrderDeliveryService;

    @Autowired
    private ISaleOrderProductService saleOrderProductService;

    @RequiresPermissions("sale:saleOrderDelivery:view")
    @GetMapping()
    public String saleOrderDelivery()
    {
        return prefix + "/saleOrderDelivery";
    }

    /**
     * 查询送货排程列表
     */
    @RequiresPermissions("sale:saleOrderDelivery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleOrderDelivery saleOrderDelivery)
    {
        startPage();
        List<SaleOrderDelivery> list = saleOrderDeliveryService.selectSaleOrderDeliveryList(saleOrderDelivery);
        return getDataTable(list);
    }

    /**
     * 导出送货排程列表
     */
    @RequiresPermissions("sale:saleOrderDelivery:export")
    @Log(title = "送货排程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleOrderDelivery saleOrderDelivery)
    {
        List<SaleOrderDelivery> list = saleOrderDeliveryService.selectSaleOrderDeliveryList(saleOrderDelivery);
        ExcelUtil<SaleOrderDelivery> util = new ExcelUtil<SaleOrderDelivery>(SaleOrderDelivery.class);
        return util.exportExcel(list, "saleOrderDelivery");
    }

    /**
     * 新增送货排程
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleOrderProduct saleOrderProduct = saleOrderProductService.selectSaleOrderProductById(id);
        SaleOrderDelivery saleOrderDelivery = new SaleOrderDelivery();
        saleOrderDelivery.setSaleOrderProductId(id);
        Integer sendQty = saleOrderDeliveryService.selectSendQty(saleOrderDelivery);
        BeanUtils.copyProperties(saleOrderProduct, saleOrderDelivery);
        saleOrderDelivery.setSaleOrderProductId(saleOrderProduct.getId());
        saleOrderDelivery.setId(-1L);
        saleOrderDelivery.setQty(saleOrderProduct.getQty() - sendQty);
        saleOrderDelivery.setSendQty(sendQty);
        mmap.put("saleOrderDelivery", saleOrderDelivery);
        return prefix + "/edit";
    }

    /**
     * 新增保存送货排程
     */
    @RequiresPermissions("sale:saleOrderDelivery:add")
    @Log(title = "送货排程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleOrderDelivery saleOrderDelivery)
    {
        return toAjax(saleOrderDeliveryService.insertSaleOrderDelivery(saleOrderDelivery));
    }

    /**
     * 修改送货排程
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleOrderDelivery saleOrderDelivery = saleOrderDeliveryService.selectSaleOrderDeliveryById(id);
        mmap.put("saleOrderDelivery", saleOrderDelivery);
        return prefix + "/edit";
    }

    /**
     * 修改保存送货排程
     */
    @RequiresPermissions("sale:saleOrderDelivery:edit")
    @Log(title = "送货排程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleOrderDelivery saleOrderDelivery)
    {
        return toAjax(saleOrderDeliveryService.updateSaleOrderDelivery(saleOrderDelivery));
    }

    /**
     * 删除送货排程
     */
    @RequiresPermissions("sale:saleOrderDelivery:remove")
    @Log(title = "送货排程", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleOrderDeliveryService.deleteSaleOrderDeliveryByIds(ids));
    }

    /**
     * 选择销售产品
     */
    @GetMapping("/selectSaleOrderDelivery/{customerId}")
    public String selectSaleOrderDelivery(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        SaleOrderDelivery saleOrderDelivery = new SaleOrderDelivery();
        saleOrderDelivery.setCustomerId(customerId);
        mmap.put("saleOrderDelivery", saleOrderDelivery);
        return prefix + "/selectSaleOrderDelivery";
    }
}
