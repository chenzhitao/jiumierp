package com.ruoyi.project.outsource.outsourceCheckingProcess.controller;

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
import com.ruoyi.project.outsource.outsourceCheckingProcess.domain.OutsourceCheckingProcess;
import com.ruoyi.project.outsource.outsourceCheckingProcess.service.IOutsourceCheckingProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 外发对账工序Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/outsource/outsourceCheckingProcess")
public class OutsourceCheckingProcessController extends BaseController
{
    private String prefix = "outsource/outsourceCheckingProcess";

    @Autowired
    private IOutsourceCheckingProcessService outsourceCheckingProcessService;

    @RequiresPermissions("outsource:outsourceCheckingProcess:view")
    @GetMapping()
    public String outsourceCheckingProcess()
    {
        return prefix + "/outsourceCheckingProcess";
    }

    /**
     * 查询外发对账工序列表
     */
    @RequiresPermissions("outsource:outsourceCheckingProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutsourceCheckingProcess outsourceCheckingProcess)
    {
        startPage();
        List<OutsourceCheckingProcess> list = outsourceCheckingProcessService.selectOutsourceCheckingProcessList(outsourceCheckingProcess);
        return getDataTable(list);
    }

    /**
     * 导出外发对账工序列表
     */
    @RequiresPermissions("outsource:outsourceCheckingProcess:export")
    @Log(title = "外发对账工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutsourceCheckingProcess outsourceCheckingProcess)
    {
        List<OutsourceCheckingProcess> list = outsourceCheckingProcessService.selectOutsourceCheckingProcessList(outsourceCheckingProcess);
        ExcelUtil<OutsourceCheckingProcess> util = new ExcelUtil<OutsourceCheckingProcess>(OutsourceCheckingProcess.class);
        return util.exportExcel(list, "外发对账工序数据");
    }

    /**
     * 新增外发对账工序
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存外发对账工序
     */
    @RequiresPermissions("outsource:outsourceCheckingProcess:add")
    @Log(title = "外发对账工序", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OutsourceCheckingProcess outsourceCheckingProcess)
    {
        return toAjax(outsourceCheckingProcessService.insertOutsourceCheckingProcess(outsourceCheckingProcess));
    }

    /**
     * 修改外发对账工序
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceCheckingProcess outsourceCheckingProcess = outsourceCheckingProcessService.selectOutsourceCheckingProcessById(id);
        mmap.put("outsourceCheckingProcess", outsourceCheckingProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存外发对账工序
     */
    @RequiresPermissions("outsource:outsourceCheckingProcess:edit")
    @Log(title = "外发对账工序", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OutsourceCheckingProcess outsourceCheckingProcess)
    {
        return toAjax(outsourceCheckingProcessService.updateOutsourceCheckingProcess(outsourceCheckingProcess));
    }

    /**
     * 删除外发对账工序
     */
    @RequiresPermissions("outsource:outsourceCheckingProcess:remove")
    @Log(title = "外发对账工序", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(outsourceCheckingProcessService.deleteOutsourceCheckingProcessByIds(ids));
    }
}
