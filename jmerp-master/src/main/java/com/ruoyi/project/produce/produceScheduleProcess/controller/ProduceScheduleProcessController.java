package com.ruoyi.project.produce.produceScheduleProcess.controller;

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
import com.ruoyi.project.produce.produceScheduleProcess.domain.ProduceScheduleProcess;
import com.ruoyi.project.produce.produceScheduleProcess.service.IProduceScheduleProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 生产排程工序Controller
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Controller
@RequestMapping("/produce/produceScheduleProcess")
public class ProduceScheduleProcessController extends BaseController
{
    private String prefix = "produce/produceScheduleProcess";

    @Autowired
    private IProduceScheduleProcessService produceScheduleProcessService;

    @RequiresPermissions("produce:produceScheduleProcess:view")
    @GetMapping()
    public String produceScheduleProcess()
    {
        return prefix + "/produceScheduleProcess";
    }

    /**
     * 查询生产排程工序列表
     */
    @RequiresPermissions("produce:produceScheduleProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProduceScheduleProcess produceScheduleProcess)
    {
        startPage();
        List<ProduceScheduleProcess> list = produceScheduleProcessService.selectProduceScheduleProcessList(produceScheduleProcess);
        return getDataTable(list);
    }

    /**
     * 导出生产排程工序列表
     */
    @RequiresPermissions("produce:produceScheduleProcess:export")
    @Log(title = "生产排程工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProduceScheduleProcess produceScheduleProcess)
    {
        List<ProduceScheduleProcess> list = produceScheduleProcessService.selectProduceScheduleProcessList(produceScheduleProcess);
        ExcelUtil<ProduceScheduleProcess> util = new ExcelUtil<ProduceScheduleProcess>(ProduceScheduleProcess.class);
        return util.exportExcel(list, "produceScheduleProcess");
    }

    /**
     * 新增生产排程工序
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生产排程工序
     */
    @RequiresPermissions("produce:produceScheduleProcess:add")
    @Log(title = "生产排程工序", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProduceScheduleProcess produceScheduleProcess)
    {
        return toAjax(produceScheduleProcessService.insertProduceScheduleProcess(produceScheduleProcess));
    }

    /**
     * 修改生产排程工序
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceScheduleProcess produceScheduleProcess = produceScheduleProcessService.selectProduceScheduleProcessById(id);
        mmap.put("produceScheduleProcess", produceScheduleProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产排程工序
     */
    @RequiresPermissions("produce:produceScheduleProcess:edit")
    @Log(title = "生产排程工序", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProduceScheduleProcess produceScheduleProcess)
    {
        return toAjax(produceScheduleProcessService.updateProduceScheduleProcess(produceScheduleProcess));
    }

    /**
     * 删除生产排程工序
     */
    @RequiresPermissions("produce:produceScheduleProcess:remove")
    @Log(title = "生产排程工序", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(produceScheduleProcessService.deleteProduceScheduleProcessByIds(ids));
    }

    /**
     * 添加工序
     * @Author 方舟
     * @Date 2021/5/14 15:33:56
    **/
    @RequiresPermissions("produce:produceScheduleProcess:add")
    @Log(title = "创建生产排程工序", businessType = BusinessType.INSERT)
    @PostMapping("/createScheduleProcess")
    @ResponseBody
    public AjaxResult createScheduleProcess(ProduceScheduleProcess produceScheduleProcess)
    {
        return AjaxResult.success("添加工序成功",produceScheduleProcessService.createScheduleProcess(produceScheduleProcess));
    }

    /**
     * 完工
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    @PostMapping("/workDone")
    @ResponseBody
    public AjaxResult workDone(ProduceScheduleProcess produceScheduleProcess){
        return AjaxResult.success(produceScheduleProcessService.workDone(produceScheduleProcess));
    }

    /**
     * 撤回
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    @PostMapping("/workBack")
    @ResponseBody
    public AjaxResult workBack(ProduceScheduleProcess produceScheduleProcess){
        return AjaxResult.success(produceScheduleProcessService.workBack(produceScheduleProcess));
    }

    /**
     * 选择工序
     * @Author 方舟
     * @Date 2021/5/14 15:18:00
     **/
    @GetMapping("/selectProduceScheduleProcess")
    public String selectProduceScheduleProcess()
    {
        return prefix + "/selectProduceScheduleProcess";
    }

    /**
     * N天生产数量
     * @Author 方舟
     * @Date 2021/6/3 9:38:39
    **/
    @PostMapping("/produceQtyChartData")
    @ResponseBody
    public AjaxResult produceQtyChartData(ProduceScheduleProcess produceScheduleProcess){
        return AjaxResult.success(produceScheduleProcessService.produceQtyChartData(produceScheduleProcess));
    }

    /**
     * 今日排程
     * @Author 方舟
     * @Date 2021/6/3 9:38:39
    **/
    @PostMapping("/todayScheduleData")
    @ResponseBody
    public AjaxResult todayScheduleData(ProduceScheduleProcess produceScheduleProcess){
        return AjaxResult.success(produceScheduleProcessService.selectProduceScheduleProcessList(produceScheduleProcess));
    }




}
