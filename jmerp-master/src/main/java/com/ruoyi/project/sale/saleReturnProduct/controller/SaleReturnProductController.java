package com.ruoyi.project.sale.saleReturnProduct.controller;

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
import com.ruoyi.project.sale.saleReturnProduct.domain.SaleReturnProduct;
import com.ruoyi.project.sale.saleReturnProduct.service.ISaleReturnProductService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 退货产品Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleReturnProduct")
public class SaleReturnProductController extends BaseController
{
    private String prefix = "sale/saleReturnProduct";

    @Autowired
    private ISaleReturnProductService saleReturnProductService;

    @RequiresPermissions("sale:saleReturnProduct:view")
    @GetMapping()
    public String saleReturnProduct()
    {
        return prefix + "/saleReturnProduct";
    }

    /**
     * 查询退货产品列表
     */
    @RequiresPermissions("sale:saleReturnProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleReturnProduct saleReturnProduct)
    {
        startPage();
        List<SaleReturnProduct> list = saleReturnProductService.selectSaleReturnProductList(saleReturnProduct);
        return getDataTable(list);
    }

    /**
     * 导出退货产品列表
     */
    @RequiresPermissions("sale:saleReturnProduct:export")
    @Log(title = "退货产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleReturnProduct saleReturnProduct)
    {
        List<SaleReturnProduct> list = saleReturnProductService.selectSaleReturnProductList(saleReturnProduct);
        ExcelUtil<SaleReturnProduct> util = new ExcelUtil<SaleReturnProduct>(SaleReturnProduct.class);
        return util.exportExcel(list, "saleReturnProduct");
    }

    /**
     * 新增退货产品
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存退货产品
     */
    @RequiresPermissions("sale:saleReturnProduct:add")
    @Log(title = "退货产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleReturnProduct saleReturnProduct)
    {
        return toAjax(saleReturnProductService.insertSaleReturnProduct(saleReturnProduct));
    }

    /**
     * 修改退货产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleReturnProduct saleReturnProduct = saleReturnProductService.selectSaleReturnProductById(id);
        mmap.put("saleReturnProduct", saleReturnProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存退货产品
     */
    @RequiresPermissions("sale:saleReturnProduct:edit")
    @Log(title = "退货产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleReturnProduct saleReturnProduct)
    {
        return toAjax(saleReturnProductService.updateSaleReturnProduct(saleReturnProduct));
    }

    /**
     * 删除退货产品
     */
    @RequiresPermissions("sale:saleReturnProduct:remove")
    @Log(title = "退货产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleReturnProductService.deleteSaleReturnProductByIds(ids));
    }
}
