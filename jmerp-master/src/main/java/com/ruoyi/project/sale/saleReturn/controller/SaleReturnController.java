package com.ruoyi.project.sale.saleReturn.controller;

import java.util.List;

import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleReturn.domain.SaleReturnExport;
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
import com.ruoyi.project.sale.saleReturn.domain.SaleReturn;
import com.ruoyi.project.sale.saleReturn.service.ISaleReturnService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 退货单Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleReturn")
public class SaleReturnController extends BaseController
{
    private String prefix = "sale/saleReturn";

    @Autowired
    private ISaleReturnService saleReturnService;

    @RequiresPermissions("sale:saleReturn:view")
    @GetMapping()
    public String saleReturn()
    {
        return prefix + "/saleReturn";
    }

    /**
     * 查询退货单列表
     */
    @RequiresPermissions("sale:saleReturn:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleReturn saleReturn)
    {
        startPage();
        List<SaleReturn> list = saleReturnService.selectSaleReturnList(saleReturn);
        return getDataTable(list);
    }

    /**
     * 导出退货单列表
     */
    @RequiresPermissions("sale:saleReturn:export")
    @Log(title = "退货单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleReturn saleReturn)
    {
        List<SaleReturnExport> list = saleReturnService.selectSaleReturnExportList(saleReturn);
        ExcelUtil<SaleReturnExport> util = new ExcelUtil<SaleReturnExport>(SaleReturnExport.class);
        return util.exportExcel(list, "退货单");
    }

    /**
     * 新增退货单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存退货单
     */
    @RequiresPermissions("sale:saleReturn:add")
    @Log(title = "退货单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleReturn saleReturn)
    {
        return toAjax(saleReturnService.insertSaleReturn(saleReturn));
    }

    /**
     * 修改退货单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleReturn saleReturn = saleReturnService.selectSaleReturnById(id);
        mmap.put("saleReturn", saleReturn);
        return prefix + "/saleReturnDetail";
    }

    /**
     * 修改保存退货单
     */
    @RequiresPermissions("sale:saleReturn:edit")
    @Log(title = "退货单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleReturn saleReturn)
    {
        return toAjax(saleReturnService.updateSaleReturn(saleReturn));
    }

    /**
     * 删除退货单
     */
    @RequiresPermissions("sale:saleReturn:remove")
    @Log(title = "退货单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleReturnService.deleteSaleReturnByIds(ids));
    }

    /**
     * 创建外发到货
     */
    @RequiresPermissions("sale:saleReturn:add")
    @Log(title = "创建退货单", businessType = BusinessType.INSERT)
    @PostMapping("/createSaleReturn")
    @ResponseBody
    public AjaxResult createSaleReturn(SaleReturn saleReturn){
        return AjaxResult.success(saleReturnService.createSaleReturn(saleReturn));
    }

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @PostMapping("/countAmount")
    @ResponseBody
    public AjaxResult countAmount(SaleReturn saleReturn) {
        SaleReturn result = saleReturnService.countAmount(saleReturn);
        return AjaxResult.success("计算成功", result);
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleReturn saleReturn = new SaleReturn();
        saleReturn = saleReturnService.selectSaleReturnById(id);
        mmap.put("saleReturn", saleReturn);
        return prefix + "/print";
    }
}
