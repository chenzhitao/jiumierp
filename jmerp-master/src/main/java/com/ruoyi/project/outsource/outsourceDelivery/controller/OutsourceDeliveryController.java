package com.ruoyi.project.outsource.outsourceDelivery.controller;

import java.util.List;

import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDeliveryExport;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrder;
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
import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDelivery;
import com.ruoyi.project.outsource.outsourceDelivery.service.IOutsourceDeliveryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 外发到货Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/outsource/outsourceDelivery")
public class OutsourceDeliveryController extends BaseController
{
    private String prefix = "outsource/outsourceDelivery";

    @Autowired
    private IOutsourceDeliveryService outsourceDeliveryService;

    @RequiresPermissions("outsource:outsourceDelivery:view")
    @GetMapping()
    public String outsourceDelivery()
    {
        return prefix + "/outsourceDelivery";
    }

    /**
     * 查询外发到货列表
     */
    @RequiresPermissions("outsource:outsourceDelivery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutsourceDelivery outsourceDelivery)
    {
        startPage();
        List<OutsourceDelivery> list = outsourceDeliveryService.selectOutsourceDeliveryList(outsourceDelivery);
        return getDataTable(list);
    }

    /**
     * 导出外发到货列表
     */
    @RequiresPermissions("outsource:outsourceDelivery:export")
    @Log(title = "外发到货", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutsourceDelivery outsourceDelivery)
    {
        List<OutsourceDeliveryExport> list = outsourceDeliveryService.selectOutsourceDeliveryExportList(outsourceDelivery);
        ExcelUtil<OutsourceDeliveryExport> util = new ExcelUtil<OutsourceDeliveryExport>(OutsourceDeliveryExport.class);
        return util.exportExcel(list, "外发到货");
    }

    /**
     * 新增外发到货
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存外发到货
     */
    @RequiresPermissions("outsource:outsourceDelivery:add")
    @Log(title = "外发到货", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OutsourceDelivery outsourceDelivery)
    {
        return toAjax(outsourceDeliveryService.insertOutsourceDelivery(outsourceDelivery));
    }

    /**
     * 修改外发到货
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceDelivery outsourceDelivery = outsourceDeliveryService.selectOutsourceDeliveryById(id);
        mmap.put("outsourceDelivery", outsourceDelivery);
        return prefix + "/outsourceDeliveryDetail";
    }

    /**
     * 修改保存外发到货
     */
    @RequiresPermissions("outsource:outsourceDelivery:edit")
    @Log(title = "外发到货", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OutsourceDelivery outsourceDelivery)
    {
        return toAjax(outsourceDeliveryService.updateOutsourceDelivery(outsourceDelivery));
    }

    /**
     * 删除外发到货
     */
    @RequiresPermissions("outsource:outsourceDelivery:remove")
    @Log(title = "外发到货", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(outsourceDeliveryService.deleteOutsourceDeliveryByIds(ids));
    }

    /**
     * 创建外发到货
     */
    @RequiresPermissions("outsource:outsourceDelivery:add")
    @Log(title = "创建外发到货", businessType = BusinessType.INSERT)
    @PostMapping("/createOutsourceDelivery")
    @ResponseBody
    public AjaxResult createOutsourceDelivery(OutsourceDelivery outsourceDelivery){
        return AjaxResult.success(outsourceDeliveryService.createOutsourceDelivery(outsourceDelivery));
    }

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @PostMapping("/countAmount")
    @ResponseBody
    public AjaxResult countAmount(OutsourceDelivery outsourceDelivery) {
        OutsourceDelivery result = outsourceDeliveryService.countAmount(outsourceDelivery);
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
        OutsourceDelivery outsourceDelivery = new OutsourceDelivery();
        outsourceDelivery = outsourceDeliveryService.selectOutsourceDeliveryById(id);
        mmap.put("outsourceDelivery", outsourceDelivery);
        return prefix + "/print";
    }

}
