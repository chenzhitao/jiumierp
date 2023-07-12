package com.ruoyi.project.produce.produceReport.controller;

import java.util.List;

import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import com.ruoyi.project.produce.produceScheduleProcess.domain.ProduceScheduleProcess;
import com.ruoyi.project.produce.produceScheduleProcess.service.IProduceScheduleProcessService;
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
import com.ruoyi.project.produce.produceReport.domain.ProduceReport;
import com.ruoyi.project.produce.produceReport.service.IProduceReportService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 产量上报Controller
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Controller
@RequestMapping("/produce/produceReport")
public class ProduceReportController extends BaseController
{
    private String prefix = "produce/produceReport";

    @Autowired
    private IProduceReportService produceReportService;
    @Autowired
    private IProduceScheduleProcessService produceScheduleProcessService;

    @RequiresPermissions("produce:produceReport:view")
    @GetMapping()
    public String produceReport()
    {
        return prefix + "/produceReport";
    }

    /**
     * 查询产量上报列表
     */
    @RequiresPermissions("produce:produceReport:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProduceReport produceReport)
    {
        startPage();
        List<ProduceReport> list = produceReportService.selectProduceReportList(produceReport);
        return getDataTable(list);
    }

    /**
     * 导出产量上报列表
     */
    @RequiresPermissions("produce:produceReport:export")
    @Log(title = "产量上报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProduceReport produceReport)
    {
        List<ProduceReport> list = produceReportService.selectProduceReportList(produceReport);
        ExcelUtil<ProduceReport> util = new ExcelUtil<ProduceReport>(ProduceReport.class);
        return util.exportExcel(list, "produceReport");
    }

    /**
     * 新增产量上报
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产量上报
     */
    @RequiresPermissions("produce:produceReport:add")
    @Log(title = "产量上报", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProduceReport produceReport)
    {
        return toAjax(produceReportService.insertProduceReport(produceReport));
    }

    /**
     * 修改产量上报
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceReport produceReport = produceReportService.selectProduceReportById(id);
        mmap.put("produceReport", produceReport);
        return prefix + "/edit";
    }

    /**
     * 修改保存产量上报
     */
    @RequiresPermissions("produce:produceReport:edit")
    @Log(title = "产量上报", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProduceReport produceReport)
    {
        return toAjax(produceReportService.updateProduceReport(produceReport));
    }

    /**
     * 删除产量上报
     */
    @RequiresPermissions("produce:produceReport:remove")
    @Log(title = "产量上报", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(produceReportService.deleteProduceReportByIds(ids));
    }

    /**
     * 报产
     * @Author 方舟
     * @Date 2021/5/17 10:53:28
    **/
    @PostMapping("/reportWork")
    @ResponseBody
    public AjaxResult reportWork(ProduceReport produceReport)
    {
        return AjaxResult.success(produceReportService.reportWork(produceReport));
    }
    /**
     * 退回排程
     * @Author 方舟
     * @Date 2021/5/17 10:53:28
    **/
    @PostMapping("/toSchedule")
    @ResponseBody
    public AjaxResult toSchedule(ProduceReport produceReport)
    {
        return AjaxResult.success(produceReportService.toSchedule(produceReport));
    }
    /**
     * 入库
     * @Author 方舟
     * @Date 2021/5/17 10:53:28
    **/
    @PostMapping("/inbound")
    @ResponseBody
    public AjaxResult inbound(ProduceReport produceReport)
    {
        return AjaxResult.success(produceReportService.inbound(produceReport));
    }

    @GetMapping("/reportNumber/{type}/{id}")
    public String reportNumber(@PathVariable("id") Long id,@PathVariable("type") Integer type, ModelMap mmap)
    {
        ProduceScheduleProcess produceScheduleProcess = produceScheduleProcessService.selectProduceScheduleProcessById(id);
        if(type.equals(1)){
            produceScheduleProcess.setQty(produceScheduleProcess.getProduceQty()-produceScheduleProcess.getQty());
            produceScheduleProcess.setRzyValue1("report");
            mmap.put("produceScheduleProcess", produceScheduleProcess);
        }else{
            produceScheduleProcess.setQty(0);
            produceScheduleProcess.setId(id);
            produceScheduleProcess.setRzyValue1("warehouse");
            produceScheduleProcess.setProduceQty(produceScheduleProcess.getProduceQty());
            mmap.put("produceScheduleProcess", produceScheduleProcess);
        }
        return prefix + "/reportNumber";
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceReport produceReport = new ProduceReport();
        produceReport = produceReportService.selectProduceReportById(id);
        mmap.put("produceReport", produceReport);
        return prefix + "/print";
    }


}
