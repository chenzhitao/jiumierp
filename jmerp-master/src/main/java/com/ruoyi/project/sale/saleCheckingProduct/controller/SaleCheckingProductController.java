package com.ruoyi.project.sale.saleCheckingProduct.controller;

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
import com.ruoyi.project.sale.saleCheckingProduct.domain.SaleCheckingProduct;
import com.ruoyi.project.sale.saleCheckingProduct.service.ISaleCheckingProductService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 送货产品对账Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleCheckingProduct")
public class SaleCheckingProductController extends BaseController
{
    private String prefix = "sale/saleCheckingProduct";

    @Autowired
    private ISaleCheckingProductService saleCheckingProductService;

    @RequiresPermissions("sale:saleCheckingProduct:view")
    @GetMapping()
    public String saleCheckingProduct()
    {
        return prefix + "/saleCheckingProduct";
    }

    /**
     * 查询送货产品对账列表
     */
    @RequiresPermissions("sale:saleCheckingProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleCheckingProduct saleCheckingProduct)
    {
        startPage();
        List<SaleCheckingProduct> list = saleCheckingProductService.selectSaleCheckingProductList(saleCheckingProduct);
        return getDataTable(list);
    }

    /**
     * 导出送货产品对账列表
     */
    @RequiresPermissions("sale:saleCheckingProduct:export")
    @Log(title = "送货产品对账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleCheckingProduct saleCheckingProduct)
    {
        List<SaleCheckingProduct> list = saleCheckingProductService.selectSaleCheckingProductList(saleCheckingProduct);
        ExcelUtil<SaleCheckingProduct> util = new ExcelUtil<SaleCheckingProduct>(SaleCheckingProduct.class);
        return util.exportExcel(list, "saleCheckingProduct");
    }

    /**
     * 新增送货产品对账
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存送货产品对账
     */
    @RequiresPermissions("sale:saleCheckingProduct:add")
    @Log(title = "送货产品对账", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleCheckingProduct saleCheckingProduct)
    {
        return toAjax(saleCheckingProductService.insertSaleCheckingProduct(saleCheckingProduct));
    }

    /**
     * 修改送货产品对账
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleCheckingProduct saleCheckingProduct = saleCheckingProductService.selectSaleCheckingProductById(id);
        mmap.put("saleCheckingProduct", saleCheckingProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存送货产品对账
     */
    @RequiresPermissions("sale:saleCheckingProduct:edit")
    @Log(title = "送货产品对账", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleCheckingProduct saleCheckingProduct)
    {
        return toAjax(saleCheckingProductService.updateSaleCheckingProduct(saleCheckingProduct));
    }

    /**
     * 删除送货产品对账
     */
    @RequiresPermissions("sale:saleCheckingProduct:remove")
    @Log(title = "送货产品对账", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleCheckingProductService.deleteSaleCheckingProductByIds(ids));
    }
}
