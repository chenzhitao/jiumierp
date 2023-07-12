package com.ruoyi.project.sale.saleOrderProduct.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrder.service.ISaleOrderService;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
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
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleOrderProduct.service.ISaleOrderProductService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 销售产品Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleOrderProduct")
public class SaleOrderProductController extends BaseController
{
    private String prefix = "sale/saleOrderProduct";

    @Autowired
    private ISaleOrderProductService saleOrderProductService;

    @Autowired
    private ISaleOrderService saleOrderService;

    @RequiresPermissions("sale:saleOrderProduct:view")
    @GetMapping()
    public String saleOrderProduct()
    {
        return prefix + "/saleOrderProduct";
    }

    /**
     * 查询销售产品列表
     */
    @RequiresPermissions("sale:saleOrderProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleOrderProduct saleOrderProduct)
    {
        startPage();
        List<SaleOrderProduct> list = saleOrderProductService.selectSaleOrderProductList(saleOrderProduct);
        return getDataTable(list);
    }

    /**
     * 导出销售产品列表
     */
    @RequiresPermissions("sale:saleOrderProduct:export")
    @Log(title = "销售产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleOrderProduct saleOrderProduct)
    {
        List<SaleOrderProduct> list = saleOrderProductService.selectSaleOrderProductList(saleOrderProduct);
        ExcelUtil<SaleOrderProduct> util = new ExcelUtil<SaleOrderProduct>(SaleOrderProduct.class);
        return util.exportExcel(list, "saleOrderProduct");
    }

    /**
     * 新增销售产品
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleOrder saleOrder = saleOrderService.selectSaleOrderById(id);
        SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
        BeanUtils.copyProperties(saleOrder, saleOrderProduct);
        saleOrderProduct.setId(-1L);
        saleOrderProduct.setSaleOrderId(id);
        saleOrderProduct.setPrice(new BigDecimal(0));
        saleOrderProduct.setQty(0);
        mmap.put("saleOrderProduct", saleOrderProduct);
        return prefix + "/edit";
    }

    /**
     * 新增保存销售产品
     */
    @RequiresPermissions("sale:saleOrderProduct:add")
    @Log(title = "销售产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleOrderProduct saleOrderProduct)
    {
        return toAjax(saleOrderProductService.insertSaleOrderProduct(saleOrderProduct));
    }

    /**
     * 修改销售产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleOrderProduct saleOrderProduct = saleOrderProductService.selectSaleOrderProductById(id);
        mmap.put("saleOrderProduct", saleOrderProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售产品
     */
    @RequiresPermissions("sale:saleOrderProduct:edit")
    @Log(title = "销售产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleOrderProduct saleOrderProduct)
    {
        int result = 0;
        if(saleOrderProduct.getId().equals(-1L)){
            result = saleOrderProductService.insertSaleOrderProduct(saleOrderProduct);
        }else{
            result = saleOrderProductService.updateSaleOrderProduct(saleOrderProduct);
        }
        return toAjax(result);
    }

    /**
     * 删除销售产品
     */
    @RequiresPermissions("sale:saleOrderProduct:remove")
    @Log(title = "销售产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleOrderProductService.deleteSaleOrderProductByIds(ids));
    }

    /**
     * 新建产品
     * @Author 方舟
     * @Date 2021/4/22 16:23:02
     **/
    @GetMapping("/addProduct/{saleOrderId}")
    public String addProduct(@PathVariable("saleOrderId") Long saleOrderId, ModelMap mmap)
    {
        SaleOrder saleOrder = saleOrderService.selectSaleOrderById(saleOrderId);
        SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
        BeanUtils.copyProperties(saleOrder, saleOrderProduct);
        saleOrderProduct.setSaleOrderId(saleOrderId);
        mmap.put("saleOrderProduct", saleOrderProduct);
        return prefix + "/createProduct";
    }

    /**
     * 新建产品
     * @Author 方舟
     * @Date 2021/4/23 10:14:36
     **/
    @PostMapping("/createProduct")
    @ResponseBody
    public AjaxResult createProduct(SaleOrderProduct saleOrderProduct)
    {
        SaleOrderProduct resultVO = saleOrderProductService.createProduct(saleOrderProduct);
        return AjaxResult.success("操作成功", resultVO);
    }


    /**
     * 选择销售产品
     */
    @GetMapping("/selectSaleOrderProduct")
    public String selectSaleOrderProduct()
    {
        return prefix + "/selectSaleOrderProduct";
    }

    /**
     * 选择产品
     * @Author 方舟
     * @Date 2021/5/14 15:18:00
     **/
    @GetMapping("/selectSaleOrderProductOS/{supplierId}")
    public String selectSaleOrderProductOS(@PathVariable("supplierId") Long supplierId, ModelMap mmap)
    {
        SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
        saleOrderProduct.setSupplierTempId(supplierId);
        mmap.put("saleOrderProduct", saleOrderProduct);
        return prefix + "/selectSaleOrderProductOS";
    }
}
