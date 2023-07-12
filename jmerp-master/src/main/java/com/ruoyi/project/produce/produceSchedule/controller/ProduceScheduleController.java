package com.ruoyi.project.produce.produceSchedule.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceReport.domain.ProduceReport;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceScheduleExport;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
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
import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import com.ruoyi.project.produce.produceSchedule.service.IProduceScheduleService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 生产排程Controller
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Controller
@RequestMapping("/produce/produceSchedule")
public class ProduceScheduleController extends BaseController
{
    private String prefix = "produce/produceSchedule";

    @Autowired
    private IProduceScheduleService produceScheduleService;

    @RequiresPermissions("produce:produceSchedule:view")
    @GetMapping()
    public String produceSchedule()
    {
        return prefix + "/produceSchedule";
    }

    /**
     * 查询生产排程列表
     */
    @RequiresPermissions("produce:produceSchedule:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProduceSchedule produceSchedule)
    {
        startPage();
        List<ProduceSchedule> list = produceScheduleService.selectProduceScheduleList(produceSchedule);
        return getDataTable(list);
    }
    /**
     * 查询生产排程列表
     */
    @RequiresPermissions("produce:produceSchedule:list")
    @PostMapping("/listExport")
    @ResponseBody
    public TableDataInfo listExport(ProduceSchedule produceSchedule)
    {
        startPage();
        List<ProduceScheduleExport> list = produceScheduleService.selectProduceScheduleExportList(produceSchedule);
        return getDataTable(list);
    }

    /**
     * 导出生产排程列表
     */
    @RequiresPermissions("produce:produceSchedule:export")
    @Log(title = "生产排程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProduceSchedule produceSchedule)
    {
        List<ProduceScheduleExport> list = produceScheduleService.selectProduceScheduleExportList(produceSchedule);
        ExcelUtil<ProduceScheduleExport> util = new ExcelUtil<ProduceScheduleExport>(ProduceScheduleExport.class);
        return util.exportExcel(list, "生产排程");
    }

    /**
     * 新增生产排程
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        ProduceSchedule produceSchedule = new ProduceSchedule();
        produceSchedule.setProduceDate(DateUtils.getNowDate());
        mmap.put("produceSchedule", produceSchedule);
        return prefix + "/add";
    }

    /**
     * 新增保存生产排程
     */
    @RequiresPermissions("produce:produceSchedule:add")
    @Log(title = "生产排程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProduceSchedule produceSchedule)
    {
        return toAjax(produceScheduleService.insertProduceSchedule(produceSchedule));
    }

    /**
     * 新增返回ID
     * @Author 方舟
     * @Date 2021/5/14 14:17:39
    **/
    @RequiresPermissions("produce:produceSchedule:add")
    @Log(title = "生产排程", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult createProduceSchedule(ProduceSchedule produceSchedule)
    {
        return AjaxResult.success(produceScheduleService.createProduceSchedule(produceSchedule));
    }

    /**
     * 修改生产排程
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceSchedule produceSchedule = produceScheduleService.selectProduceScheduleById(id);
        mmap.put("produceSchedule", produceSchedule);
        return prefix + "/produceScheduleDetail";
    }

    /**
     * 修改保存生产排程
     */
    @RequiresPermissions("produce:produceSchedule:edit")
    @Log(title = "生产排程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProduceSchedule produceSchedule)
    {
        return toAjax(produceScheduleService.updateProduceSchedule(produceSchedule));
    }

    /**
     * 删除生产排程
     */
    @RequiresPermissions("produce:produceSchedule:remove")
    @Log(title = "生产排程", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(produceScheduleService.deleteProduceScheduleByIds(ids));
    }

    /**
     * 完工
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
    **/
    @PostMapping("/workDone")
    @ResponseBody
    public AjaxResult workDone(ProduceSchedule produceSchedule)
    {
        return AjaxResult.success(produceScheduleService.workDone(produceSchedule));
    }
    /**
     * 报产
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
    **/
    @PostMapping("/workReport")
    @ResponseBody
    public AjaxResult workReport(ProduceSchedule produceSchedule)
    {
        return AjaxResult.success(produceScheduleService.workReport(produceSchedule));
    }
    /**
     * 入库
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
    **/
    @PostMapping("/workInbound")
    @ResponseBody
    public AjaxResult workInbound(ProduceSchedule produceSchedule)
    {
        return AjaxResult.success(produceScheduleService.workInbound(produceSchedule));
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceSchedule produceSchedule = new ProduceSchedule();
        produceSchedule = produceScheduleService.selectProduceScheduleById(id);
        mmap.put("produceSchedule", produceSchedule);
        return prefix + "/print";
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/openReportCard/{id}")
    public String openReportCard(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceSchedule produceSchedule = new ProduceSchedule();
        produceSchedule = produceScheduleService.selectProduceScheduleById(id);
        mmap.put("produceSchedule", produceSchedule);
        return prefix + "/reportCard";
    }

    /**
     * 审批
     * @param saleOrder
     * @return
     */
    @PostMapping("/batchApprove")
    @ResponseBody
    public AjaxResult batchApprove(ProduceSchedule produceSchedule)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(produceSchedule.getIds());
        for (int i=0;i<arr.length;i++){
            ProduceSchedule saveVO = new ProduceSchedule();
            Long id = Long.parseLong(arr[i]);
            saveVO.setId(id);
            saveVO.setStatus("normal");
            saveVO.setRzyValue1("approve");
            saveVO.setApprover(ShiroUtils.getSysUser().getUserName());
            saveVO.setApprovalTime(DateUtils.getNowDate());
            result = produceScheduleService.updateProduceSchedule(saveVO);
        }
        return AjaxResult.success("审批成功",produceSchedule);
    }

    /**
     * 修改停机
     */
    @GetMapping("/pausePage/{id}")
    public String pausePage(@PathVariable("id") Long id, ModelMap mmap) {
        ProduceSchedule produceSchedule = produceScheduleService.selectProduceScheduleById(id);
        mmap.put("produceSchedule", produceSchedule);
        return prefix + "/pauseInfo";
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/openBatchReport")
    public String openBatchReport()
    {
        return prefix + "/batchReport";
    }

    /**
     * 审批
     * @param saleOrder
     * @return
     */
    @PostMapping("/batchReport")
    @ResponseBody
    public AjaxResult batchReport(ProduceSchedule produceSchedule)
    {
        produceScheduleService.batchReport(produceSchedule);
        return AjaxResult.success("入库成功",produceSchedule);
    }

}
