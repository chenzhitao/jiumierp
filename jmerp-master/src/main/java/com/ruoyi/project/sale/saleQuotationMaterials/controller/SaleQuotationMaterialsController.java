package com.ruoyi.project.sale.saleQuotationMaterials.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.sale.saleQuotationProcess.domain.SaleQuotationProcess;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
import com.ruoyi.project.sale.saleQuotationProduct.service.ISaleQuotationProductService;
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
import com.ruoyi.project.sale.saleQuotationMaterials.domain.SaleQuotationMaterials;
import com.ruoyi.project.sale.saleQuotationMaterials.service.ISaleQuotationMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 报价材料Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleQuotationMaterials")
public class SaleQuotationMaterialsController extends BaseController
{
    private String prefix = "sale/saleQuotationMaterials";

    @Autowired
    private ISaleQuotationMaterialsService saleQuotationMaterialsService;

    @Autowired
    private ISaleQuotationProductService saleQuotationProductService;

    @RequiresPermissions("sale:saleQuotationMaterials:view")
    @GetMapping()
    public String saleQuotationMaterials()
    {
        return prefix + "/saleQuotationMaterials";
    }

    /**
     * 查询报价材料列表
     */
    @RequiresPermissions("sale:saleQuotationMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleQuotationMaterials saleQuotationMaterials)
    {
        startPage();
        List<SaleQuotationMaterials> list = saleQuotationMaterialsService.selectSaleQuotationMaterialsList(saleQuotationMaterials);
        return getDataTable(list);
    }

    /**
     * 导出报价材料列表
     */
    @RequiresPermissions("sale:saleQuotationMaterials:export")
    @Log(title = "报价材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleQuotationMaterials saleQuotationMaterials)
    {
        List<SaleQuotationMaterials> list = saleQuotationMaterialsService.selectSaleQuotationMaterialsList(saleQuotationMaterials);
        ExcelUtil<SaleQuotationMaterials> util = new ExcelUtil<SaleQuotationMaterials>(SaleQuotationMaterials.class);
        return util.exportExcel(list, "saleQuotationMaterials");
    }

    /**
     * 新增报价材料
     */
    @GetMapping("/add/{saleQuotationProductId}")
    public String add(@PathVariable("saleQuotationProductId") Long saleQuotationProductId, ModelMap mmap)
    {
        SaleQuotationProduct saleQuotationProduct = saleQuotationProductService.selectSaleQuotationProductById(saleQuotationProductId);
        SaleQuotationMaterials saleQuotationMaterials = new SaleQuotationMaterials();
        BeanUtils.copyProperties(saleQuotationProduct, saleQuotationMaterials);
        saleQuotationMaterials.setSaleQuotationProductId(saleQuotationProductId);
        saleQuotationMaterials.setIsGetProcessQty("N");
        saleQuotationMaterials.setId(-1L);
        saleQuotationMaterials.setTimes(new BigDecimal(1));
        mmap.put("saleQuotationMaterials", saleQuotationMaterials);
        return prefix + "/edit";
    }

    /**
     * 新增保存报价材料
     */
    @RequiresPermissions("sale:saleQuotationMaterials:add")
    @Log(title = "报价材料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleQuotationMaterials saleQuotationMaterials)
    {
        return toAjax(saleQuotationMaterialsService.insertSaleQuotationMaterials(saleQuotationMaterials));
    }

    /**
     * 修改报价材料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleQuotationMaterials saleQuotationMaterials = saleQuotationMaterialsService.selectSaleQuotationMaterialsById(id);
        mmap.put("saleQuotationMaterials", saleQuotationMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存报价材料
     */
    @RequiresPermissions("sale:saleQuotationMaterials:edit")
    @Log(title = "报价材料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleQuotationMaterials saleQuotationMaterials)
    {
        return toAjax(saleQuotationMaterialsService.updateSaleQuotationMaterials(saleQuotationMaterials));
    }

    /**
     * 删除报价材料
     */
    @RequiresPermissions("sale:saleQuotationMaterials:remove")
    @Log(title = "报价材料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleQuotationMaterialsService.deleteSaleQuotationMaterialsByIds(ids));
    }
}
