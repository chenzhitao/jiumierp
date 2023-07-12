package com.ruoyi.project.outsource.outsourceOrderProcess.controller;

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
import com.ruoyi.project.outsource.outsourceOrderProcess.domain.OutsourceOrderProcess;
import com.ruoyi.project.outsource.outsourceOrderProcess.service.IOutsourceOrderProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 外发加工工序Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/outsource/outsourceOrderProcess")
public class OutsourceOrderProcessController extends BaseController
{
    private String prefix = "outsource/outsourceOrderProcess";

    @Autowired
    private IOutsourceOrderProcessService outsourceOrderProcessService;

    @RequiresPermissions("outsource:outsourceOrderProcess:view")
    @GetMapping()
    public String outsourceOrderProcess()
    {
        return prefix + "/outsourceOrderProcess";
    }

    /**
     * 查询外发加工工序列表
     */
    @RequiresPermissions("outsource:outsourceOrderProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutsourceOrderProcess outsourceOrderProcess)
    {
        startPage();
        List<OutsourceOrderProcess> list = outsourceOrderProcessService.selectOutsourceOrderProcessList(outsourceOrderProcess);
        return getDataTable(list);
    }

    /**
     * 导出外发加工工序列表
     */
    @RequiresPermissions("outsource:outsourceOrderProcess:export")
    @Log(title = "外发加工工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutsourceOrderProcess outsourceOrderProcess)
    {
        List<OutsourceOrderProcess> list = outsourceOrderProcessService.selectOutsourceOrderProcessList(outsourceOrderProcess);
        ExcelUtil<OutsourceOrderProcess> util = new ExcelUtil<OutsourceOrderProcess>(OutsourceOrderProcess.class);
        return util.exportExcel(list, "外发加工工序数据");
    }

    /**
     * 新增外发加工工序
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存外发加工工序
     */
    @RequiresPermissions("outsource:outsourceOrderProcess:add")
    @Log(title = "外发加工工序", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OutsourceOrderProcess outsourceOrderProcess)
    {
        return toAjax(outsourceOrderProcessService.insertOutsourceOrderProcess(outsourceOrderProcess));
    }

    /**
     * 修改外发加工工序
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceOrderProcess outsourceOrderProcess = outsourceOrderProcessService.selectOutsourceOrderProcessById(id);
        mmap.put("outsourceOrderProcess", outsourceOrderProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存外发加工工序
     */
    @RequiresPermissions("outsource:outsourceOrderProcess:edit")
    @Log(title = "外发加工工序", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OutsourceOrderProcess outsourceOrderProcess)
    {
        return toAjax(outsourceOrderProcessService.updateOutsourceOrderProcess(outsourceOrderProcess));
    }

    /**
     * 删除外发加工工序
     */
    @RequiresPermissions("outsource:outsourceOrderProcess:remove")
    @Log(title = "外发加工工序", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(outsourceOrderProcessService.deleteOutsourceOrderProcessByIds(ids));
    }


    /**
     * 选择外发加工工序
     */
    @GetMapping("/selectOutsourceOrderProcess/{supplierId}")
    public String selectOutsourceOrderProcess(@PathVariable("supplierId") Long supplierId, ModelMap mmap)
    {
        OutsourceOrder outsourceOrder = new OutsourceOrder();
        outsourceOrder.setSupplierId(supplierId);
        mmap.put("outsourceOrder", outsourceOrder);
        return prefix + "/selectOutsourceOrderProcess";
    }
}
