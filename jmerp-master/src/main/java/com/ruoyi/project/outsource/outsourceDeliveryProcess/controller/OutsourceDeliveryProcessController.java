package com.ruoyi.project.outsource.outsourceDeliveryProcess.controller;

import java.util.List;

import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDelivery;
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
import com.ruoyi.project.outsource.outsourceDeliveryProcess.domain.OutsourceDeliveryProcess;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.service.IOutsourceDeliveryProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 外发到货工序Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/outsource/outsourceDeliveryProcess")
public class OutsourceDeliveryProcessController extends BaseController
{
    private String prefix = "outsource/outsourceDeliveryProcess";

    @Autowired
    private IOutsourceDeliveryProcessService outsourceDeliveryProcessService;

    @RequiresPermissions("outsource:outsourceDeliveryProcess:view")
    @GetMapping()
    public String outsourceDeliveryProcess()
    {
        return prefix + "/outsourceDeliveryProcess";
    }

    /**
     * 查询外发到货工序列表
     */
    @RequiresPermissions("outsource:outsourceDeliveryProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutsourceDeliveryProcess outsourceDeliveryProcess)
    {
        startPage();
        List<OutsourceDeliveryProcess> list = outsourceDeliveryProcessService.selectOutsourceDeliveryProcessList(outsourceDeliveryProcess);
        return getDataTable(list);
    }

    /**
     * 导出外发到货工序列表
     */
    @RequiresPermissions("outsource:outsourceDeliveryProcess:export")
    @Log(title = "外发到货工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutsourceDeliveryProcess outsourceDeliveryProcess)
    {
        List<OutsourceDeliveryProcess> list = outsourceDeliveryProcessService.selectOutsourceDeliveryProcessList(outsourceDeliveryProcess);
        ExcelUtil<OutsourceDeliveryProcess> util = new ExcelUtil<OutsourceDeliveryProcess>(OutsourceDeliveryProcess.class);
        return util.exportExcel(list, "外发到货工序数据");
    }

    /**
     * 新增外发到货工序
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存外发到货工序
     */
    @RequiresPermissions("outsource:outsourceDeliveryProcess:add")
    @Log(title = "外发到货工序", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OutsourceDeliveryProcess outsourceDeliveryProcess)
    {
        return toAjax(outsourceDeliveryProcessService.insertOutsourceDeliveryProcess(outsourceDeliveryProcess));
    }

    /**
     * 修改外发到货工序
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceDeliveryProcess outsourceDeliveryProcess = outsourceDeliveryProcessService.selectOutsourceDeliveryProcessById(id);
        mmap.put("outsourceDeliveryProcess", outsourceDeliveryProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存外发到货工序
     */
    @RequiresPermissions("outsource:outsourceDeliveryProcess:edit")
    @Log(title = "外发到货工序", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OutsourceDeliveryProcess outsourceDeliveryProcess)
    {
        return toAjax(outsourceDeliveryProcessService.updateOutsourceDeliveryProcess(outsourceDeliveryProcess));
    }

    /**
     * 删除外发到货工序
     */
    @RequiresPermissions("outsource:outsourceDeliveryProcess:remove")
    @Log(title = "外发到货工序", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(outsourceDeliveryProcessService.deleteOutsourceDeliveryProcessByIds(ids));
    }

    /**
     * 新增外发到货工序
     */
    @GetMapping("/selectOutsourceDeliveryProcess/{supplierId}/{rzyValue1}")
    public String selectOutsourceDeliveryProcess(@PathVariable("supplierId") Long supplierId, @PathVariable("rzyValue1") String rzyValue1, ModelMap mmap)
    {
        OutsourceDelivery outsourceDelivery = new OutsourceDelivery();
        outsourceDelivery.setSupplierId(supplierId);
        outsourceDelivery.setRzyValue1(rzyValue1);
        mmap.put("outsourceDelivery", outsourceDelivery);
        return prefix + "/selectOutsourceDeliveryProcess";
    }

}
