package com.ruoyi.project.outsource.outsourceReturnProcess.controller;

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
import com.ruoyi.project.outsource.outsourceReturnProcess.domain.OutsourceReturnProcess;
import com.ruoyi.project.outsource.outsourceReturnProcess.service.IOutsourceReturnProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 外发退货工序Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/outsource/outsourceReturnProcess")
public class OutsourceReturnProcessController extends BaseController
{
    private String prefix = "outsource/outsourceReturnProcess";

    @Autowired
    private IOutsourceReturnProcessService outsourceReturnProcessService;

    @RequiresPermissions("outsource:outsourceReturnProcess:view")
    @GetMapping()
    public String outsourceReturnProcess()
    {
        return prefix + "/outsourceReturnProcess";
    }

    /**
     * 查询外发退货工序列表
     */
    @RequiresPermissions("outsource:outsourceReturnProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutsourceReturnProcess outsourceReturnProcess)
    {
        startPage();
        List<OutsourceReturnProcess> list = outsourceReturnProcessService.selectOutsourceReturnProcessList(outsourceReturnProcess);
        return getDataTable(list);
    }

    /**
     * 导出外发退货工序列表
     */
    @RequiresPermissions("outsource:outsourceReturnProcess:export")
    @Log(title = "外发退货工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutsourceReturnProcess outsourceReturnProcess)
    {
        List<OutsourceReturnProcess> list = outsourceReturnProcessService.selectOutsourceReturnProcessList(outsourceReturnProcess);
        ExcelUtil<OutsourceReturnProcess> util = new ExcelUtil<OutsourceReturnProcess>(OutsourceReturnProcess.class);
        return util.exportExcel(list, "外发退货工序数据");
    }

    /**
     * 新增外发退货工序
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存外发退货工序
     */
    @RequiresPermissions("outsource:outsourceReturnProcess:add")
    @Log(title = "外发退货工序", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OutsourceReturnProcess outsourceReturnProcess)
    {
        return toAjax(outsourceReturnProcessService.insertOutsourceReturnProcess(outsourceReturnProcess));
    }

    /**
     * 修改外发退货工序
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceReturnProcess outsourceReturnProcess = outsourceReturnProcessService.selectOutsourceReturnProcessById(id);
        mmap.put("outsourceReturnProcess", outsourceReturnProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存外发退货工序
     */
    @RequiresPermissions("outsource:outsourceReturnProcess:edit")
    @Log(title = "外发退货工序", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OutsourceReturnProcess outsourceReturnProcess)
    {
        return toAjax(outsourceReturnProcessService.updateOutsourceReturnProcess(outsourceReturnProcess));
    }

    /**
     * 删除外发退货工序
     */
    @RequiresPermissions("outsource:outsourceReturnProcess:remove")
    @Log(title = "外发退货工序", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(outsourceReturnProcessService.deleteOutsourceReturnProcessByIds(ids));
    }

}
