package com.ruoyi.project.sale.saleQuotationMult.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMultLog;
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
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMult;
import com.ruoyi.project.sale.saleQuotationMult.service.ISaleQuotationMultService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 多数量报价Controller
 * 
 * @author fangzhou
 * @date 2021-04-21
 */
@Controller
@RequestMapping("/sale/saleQuotationMult")
public class SaleQuotationMultController extends BaseController
{
    private String prefix = "sale/saleQuotationMult";

    @Autowired
    private ISaleQuotationMultService saleQuotationMultService;

    @RequiresPermissions("sale:saleQuotationMult:view")
    @GetMapping()
    public String saleQuotationMult()
    {
        return prefix + "/saleQuotationMult";
    }

    /**
     * 查询多数量报价列表
     */
    @RequiresPermissions("sale:saleQuotationMult:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleQuotationMult saleQuotationMult)
    {
        startPage();
        List<SaleQuotationMult> list = saleQuotationMultService.selectSaleQuotationMultList(saleQuotationMult);
        return getDataTable(list);
    }

    /**
     * 导出多数量报价列表
     */
    @RequiresPermissions("sale:saleQuotationMult:export")
    @Log(title = "多数量报价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleQuotationMult saleQuotationMult)
    {
        List<SaleQuotationMult> list = saleQuotationMultService.selectSaleQuotationMultList(saleQuotationMult);
        ExcelUtil<SaleQuotationMult> util = new ExcelUtil<SaleQuotationMult>(SaleQuotationMult.class);
        return util.exportExcel(list, "saleQuotationMult");
    }

    /**
     * 新增多数量报价
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存多数量报价
     */
    @RequiresPermissions("sale:saleQuotationMult:add")
    @Log(title = "多数量报价", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleQuotationMult saleQuotationMult)
    {
        return toAjax(saleQuotationMultService.insertSaleQuotationMult(saleQuotationMult));
    }

    /**
     * 修改多数量报价
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleQuotationMult saleQuotationMult = saleQuotationMultService.selectSaleQuotationMultById(id);
        mmap.put("saleQuotationMult", saleQuotationMult);
        return prefix + "/edit";
    }

    /**
     * 修改保存多数量报价
     */
    @RequiresPermissions("sale:saleQuotationMult:edit")
    @Log(title = "多数量报价", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleQuotationMult saleQuotationMult)
    {
        return toAjax(saleQuotationMultService.updateSaleQuotationMult(saleQuotationMult));
    }

    /**
     * 删除多数量报价
     */
    @RequiresPermissions("sale:saleQuotationMult:remove")
    @Log(title = "多数量报价", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleQuotationMultService.deleteSaleQuotationMultByIds(ids));
    }

    /**
     * 打开日志
     * @Author 方舟
     * @Date 2021/4/23 15:24:16
    **/
    @GetMapping("/calculateLog/{id}")
    public String calculateLog(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleQuotationMult saleQuotationMult = saleQuotationMultService.selectSaleQuotationMultById(id);
        mmap.put("saleQuotationMult", saleQuotationMult);
        return prefix + "/calculateLog";
    }

    /**
     * 加载日志汇总
     * @Author 方舟
     * @Date 2021/4/23 15:24:24
     **/
    @PostMapping("/selectCalculateLogTitle")
    @ResponseBody
    public AjaxResult selectCalculateLogTitle(SaleQuotationMult saleQuotationMult)
    {
        SaleQuotationMult orgVO = saleQuotationMultService.selectSaleQuotationMultById(saleQuotationMult.getId());
        return AjaxResult.success("加载成功",orgVO);
    }

    /**
     * 加载日志数据
     * @Author 方舟
     * @Date 2021/4/23 15:24:24
    **/
    @PostMapping( "/selectCalculateLogList")
    @ResponseBody
    public AjaxResult selectCalculateLogList(SaleQuotationMult saleQuotationMult)
    {
        SaleQuotationMult orgVO = saleQuotationMultService.selectSaleQuotationMultById(saleQuotationMult.getId());
        String logText = orgVO.getCalculateLog();
        if(null==logText){
            return AjaxResult.success("加载成功",new ArrayList<SaleQuotationMultLog>());
        }
        JSONArray jsonArray = JSONArray.parseArray(logText);
        List<SaleQuotationMultLog> logList = JSONArray.parseArray(jsonArray.toJSONString(), SaleQuotationMultLog.class);
        return AjaxResult.success("加载成功",logList);
    }


}
