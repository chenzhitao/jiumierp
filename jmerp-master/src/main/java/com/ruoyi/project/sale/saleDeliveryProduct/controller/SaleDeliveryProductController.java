package com.ruoyi.project.sale.saleDeliveryProduct.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.config.ShiroConfig;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleDelivery.service.ISaleDeliveryService;
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
import com.ruoyi.project.sale.saleDeliveryProduct.domain.SaleDeliveryProduct;
import com.ruoyi.project.sale.saleDeliveryProduct.service.ISaleDeliveryProductService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 送货产品Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleDeliveryProduct")
public class SaleDeliveryProductController extends BaseController
{
    private String prefix = "sale/saleDeliveryProduct";

    @Autowired
    private ISaleDeliveryService saleDeliveryService;

    @Autowired
    private ISaleDeliveryProductService saleDeliveryProductService;

    @RequiresPermissions("sale:saleDeliveryProduct:view")
    @GetMapping()
    public String saleDeliveryProduct()
    {
        return prefix + "/saleDeliveryProduct";
    }

    /**
     * 查询送货产品列表
     */
    @RequiresPermissions("sale:saleDeliveryProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleDeliveryProduct saleDeliveryProduct)
    {
        startPage();
        List<SaleDeliveryProduct> list = saleDeliveryProductService.selectSaleDeliveryProductList(saleDeliveryProduct);
        return getDataTable(list);
    }
    /**
     * 查询送货产品列表
     */
    @RequiresPermissions("sale:saleDeliveryProduct:list")
    @PostMapping("/selectList")
    @ResponseBody
    public AjaxResult selectList(SaleDeliveryProduct saleDeliveryProduct)
    {
        List<SaleDeliveryProduct> list = saleDeliveryProductService.selectSaleDeliveryProductList(saleDeliveryProduct);
        return AjaxResult.success(list);
    }

    /**
     * 导出送货产品列表
     */
    @RequiresPermissions("sale:saleDeliveryProduct:export")
    @Log(title = "送货产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleDeliveryProduct saleDeliveryProduct)
    {
        List<SaleDeliveryProduct> list = saleDeliveryProductService.selectSaleDeliveryProductList(saleDeliveryProduct);
        ExcelUtil<SaleDeliveryProduct> util = new ExcelUtil<SaleDeliveryProduct>(SaleDeliveryProduct.class);
        return util.exportExcel(list, "saleDeliveryProduct");
    }

    /**
     * 新增送货产品
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleDelivery saleDelivery = saleDeliveryService.selectSaleDeliveryById(id);
        SaleDeliveryProduct saleDeliveryProduct = new SaleDeliveryProduct();
        saleDeliveryProduct.setDeliveryDate(DateUtils.getNowDate());
        saleDeliveryProduct.setQty(0);
        saleDeliveryProduct.setPrice(new BigDecimal(0));
        saleDeliveryProduct.setFreeQty(0);
        saleDeliveryProduct.setAmount(new BigDecimal(0));
        saleDeliveryProduct.setCustomerId(saleDelivery.getCustomerId());
        saleDeliveryProduct.setSaleDeliveryId(saleDelivery.getId());
        mmap.put("saleDeliveryProduct", saleDeliveryProduct);
        return prefix + "/add";
    }

    /**
     * 新增保存送货产品
     */
    @RequiresPermissions("sale:saleDeliveryProduct:add")
    @Log(title = "送货产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleDeliveryProduct saleDeliveryProduct)
    {
        return toAjax(saleDeliveryProductService.insertSaleDeliveryProduct(saleDeliveryProduct));
    }

    /**
     * 修改送货产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleDeliveryProduct saleDeliveryProduct = saleDeliveryProductService.selectSaleDeliveryProductById(id);
        mmap.put("saleDeliveryProduct", saleDeliveryProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存送货产品
     */
    @RequiresPermissions("sale:saleDeliveryProduct:edit")
    @Log(title = "送货产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleDeliveryProduct saleDeliveryProduct)
    {
        return toAjax(saleDeliveryProductService.updateSaleDeliveryProduct(saleDeliveryProduct));
    }

    /**
     * 删除送货产品
     */
    @RequiresPermissions("sale:saleDeliveryProduct:remove")
    @Log(title = "送货产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleDeliveryProductService.deleteSaleDeliveryProductByIds(ids));
    }

    /**
     * 修改送货产品
     */
    @GetMapping("/selectSaleDeliveryProduct/{customerId}/{rzyValue1}")
    public String selectSaleDeliveryProduct(@PathVariable("customerId") Long customerId, @PathVariable("rzyValue1") String rzyValue1, ModelMap mmap)
    {
        SaleDeliveryProduct saleDeliveryProduct = new SaleDeliveryProduct();
        saleDeliveryProduct.setCustomerId(customerId);
        saleDeliveryProduct.setRzyValue1(rzyValue1);
        mmap.put("saleDeliveryProduct", saleDeliveryProduct);
        return prefix + "/selectSaleDeliveryProduct";
    }

}
