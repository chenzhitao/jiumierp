package com.ruoyi.project.sale.saleQuotationProduct.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.config.configBoard.domain.ConfigBoard;
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
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
import com.ruoyi.project.sale.saleQuotationProduct.service.ISaleQuotationProductService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报价产品Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleQuotationProduct")
public class SaleQuotationProductController extends BaseController
{
    private String prefix = "sale/saleQuotationProduct";

    @Autowired
    private ISaleQuotationProductService saleQuotationProductService;

    @RequiresPermissions("sale:saleQuotationProduct:view")
    @GetMapping()
    public String saleQuotationProduct()
    {
        return prefix + "/saleQuotationProduct";
    }

    /**
     * 查询报价产品列表
     */
    @RequiresPermissions("sale:saleQuotationProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleQuotationProduct saleQuotationProduct)
    {
        startPage();
        List<SaleQuotationProduct> list = new ArrayList<SaleQuotationProduct>();
        if(!StringUtils.isEmpty(saleQuotationProduct.getRzyValue1())&&"select".equals(saleQuotationProduct.getRzyValue1())){
            list = saleQuotationProductService.selectMultProductList(saleQuotationProduct);
        }else{
            list = saleQuotationProductService.selectSaleQuotationProductList(saleQuotationProduct);
        }
        return getDataTable(list);
    }

    /**
     * 导出报价产品列表
     */
    @RequiresPermissions("sale:saleQuotationProduct:export")
    @Log(title = "报价产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleQuotationProduct saleQuotationProduct)
    {
        List<SaleQuotationProduct> list = saleQuotationProductService.selectSaleQuotationProductList(saleQuotationProduct);
        ExcelUtil<SaleQuotationProduct> util = new ExcelUtil<SaleQuotationProduct>(SaleQuotationProduct.class);
        return util.exportExcel(list, "saleQuotationProduct");
    }

    /**
     * 新增报价产品
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存报价产品
     */
    @RequiresPermissions("sale:saleQuotationProduct:add")
    @Log(title = "报价产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleQuotationProduct saleQuotationProduct)
    {
        return toAjax(saleQuotationProductService.insertSaleQuotationProduct(saleQuotationProduct));
    }

    /**
     * 修改报价产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleQuotationProduct saleQuotationProduct = saleQuotationProductService.selectSaleQuotationProductById(id);
        mmap.put("saleQuotationProduct", saleQuotationProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存报价产品
     */
    @RequiresPermissions("sale:saleQuotationProduct:edit")
    @Log(title = "报价产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleQuotationProduct saleQuotationProduct)
    {
        return toAjax(saleQuotationProductService.updateSaleQuotationProduct(saleQuotationProduct));
    }

    /**
     * 删除报价产品
     */
    @RequiresPermissions("sale:saleQuotationProduct:remove")
    @Log(title = "报价产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleQuotationProductService.deleteSaleQuotationProductByIds(ids));
    }

    /**
     * 添加已有产品
     * @Author 方舟
     * @Date 2021/4/22 13:20:47
    **/
    @PostMapping( "/createSaleQuotationProduct")
    @ResponseBody
    public AjaxResult createSaleQuotationProduct(SaleQuotationProduct saleQuotationProduct)
    {
        SaleQuotationProduct resultVO = saleQuotationProductService.createSaleQuotationProduct(saleQuotationProduct);
        return AjaxResult.success("操作成功", resultVO);
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "报价产品", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configBoard:import")
    @PostMapping("/importData/{id}")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport, @PathVariable("id") Long saleQuotationId) throws Exception
    {
        ExcelUtil<SaleQuotationProduct> util = new ExcelUtil<SaleQuotationProduct>(SaleQuotationProduct.class);
        List<SaleQuotationProduct> saleQuotationProductList = util.importExcel(file.getInputStream());
        for (int i=0;i<saleQuotationProductList.size();i++){
            saleQuotationProductList.get(i).setSaleQuotationId(saleQuotationId);
        }
        String message = saleQuotationProductService.importSaleQuotationProduct(saleQuotationProductList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入模板
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SaleQuotationProduct> util = new ExcelUtil<SaleQuotationProduct>(SaleQuotationProduct.class);
        return util.importTemplateExcel("报价产品");
    }

    /**
     * 新建产品
     * @Author 方舟
     * @Date 2021/4/22 16:23:02
    **/
    @GetMapping("/addProduct/{saleQuotationId}")
    public String addProduct(@PathVariable("saleQuotationId") Long saleQuotationId, ModelMap mmap)
    {
        SaleQuotationProduct saleQuotationProduct = new SaleQuotationProduct();
        saleQuotationProduct.setId(saleQuotationId);
        mmap.put("saleQuotationProduct", saleQuotationProduct);
        return prefix + "/createProduct";
    }

    /**
     * 新建产品
     * @Author 方舟
     * @Date 2021/4/23 10:14:36
    **/
    @PostMapping("/createProduct")
    @ResponseBody
    public AjaxResult createProduct(SaleQuotationProduct saleQuotationProduct)
    {
        SaleQuotationProduct resultVO = saleQuotationProductService.createProduct(saleQuotationProduct);
        return AjaxResult.success("操作成功", resultVO);
    }

    /**
     * 更换工艺卡
     * @Author 方舟
     * @Date 2021/4/23 10:14:36
    **/
    @PostMapping("/selectSaleQuotationExec")
    @ResponseBody
    public AjaxResult selectSaleQuotationExec(SaleQuotationProduct saleQuotationProduct)
    {
        SaleQuotationProduct resultVO = saleQuotationProductService.selectSaleQuotationExec(saleQuotationProduct);
        return AjaxResult.success(resultVO.getRzyValue1(), resultVO);
    }


    /**
     * 选择保价产品
     */
    @GetMapping("/selectQuotationProduct")
    public String selectQuotationProduct()
    {
        return prefix + "/selectQuotationProduct";
    }

}
