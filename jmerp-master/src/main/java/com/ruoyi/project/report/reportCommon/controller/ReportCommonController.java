package com.ruoyi.project.report.reportCommon.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.ruoyi.project.report.reportCommon.domain.ReportProductionFinancial;
import com.ruoyi.project.report.reportCommon.domain.ReportPurchaseIncoming;
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
import com.ruoyi.project.report.reportCommon.domain.ReportCommon;
import com.ruoyi.project.report.reportCommon.service.IReportCommonService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 报表公共Controller
 * 
 * @author fangzhou
 * @date 2021-06-04
 */
@Controller
@RequestMapping("/report/reportCommon")
public class ReportCommonController extends BaseController
{
    private String prefix = "report/reportCommon";

    @Autowired
    private IReportCommonService reportCommonService;

    @RequiresPermissions("report:reportCommon:view")
    @GetMapping()
    public String reportCommon()
    {
        return prefix + "/reportCommon";
    }

    /**
     * 查询报表公共列表
     */
    @RequiresPermissions("report:reportCommon:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReportCommon reportCommon)
    {
        startPage();
        List<ReportCommon> list = reportCommonService.selectReportCommonList(reportCommon);
        return getDataTable(list);
    }

    /**
     * 导出报表公共列表
     */
    @RequiresPermissions("report:reportCommon:export")
    @Log(title = "报表公共", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReportCommon reportCommon)
    {
        List<ReportCommon> list = reportCommonService.selectReportCommonList(reportCommon);
        ExcelUtil<ReportCommon> util = new ExcelUtil<ReportCommon>(ReportCommon.class);
        return util.exportExcel(list, "报表公共数据");
    }

    /**
     * 新增报表公共
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存报表公共
     */
    @RequiresPermissions("report:reportCommon:add")
    @Log(title = "报表公共", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ReportCommon reportCommon)
    {
        return toAjax(reportCommonService.insertReportCommon(reportCommon));
    }

    /**
     * 修改报表公共
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ReportCommon reportCommon = reportCommonService.selectReportCommonById(id);
        mmap.put("reportCommon", reportCommon);
        return prefix + "/edit";
    }

    /**
     * 修改保存报表公共
     */
    @RequiresPermissions("report:reportCommon:edit")
    @Log(title = "报表公共", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ReportCommon reportCommon)
    {
        return toAjax(reportCommonService.updateReportCommon(reportCommon));
    }

    /**
     * 删除报表公共
     */
    @RequiresPermissions("report:reportCommon:remove")
    @Log(title = "报表公共", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(reportCommonService.deleteReportCommonByIds(ids));
    }

    /**
     * 来料报表
     * @Author 方舟
     * @Date 2021/6/4 10:57:49
    **/
    @RequiresPermissions("report:reportCommon:view")
    @GetMapping("/purchaseReport")
    public String purchaseReport(ModelMap mmap)
    {
        ReportCommon reportCommon = new ReportCommon();
        Calendar calendar1 = Calendar.getInstance();
        reportCommon.setEndDeliveryDate(calendar1.getTime());
        calendar1.add(Calendar.DATE, -7);
        reportCommon.setBeginDeliveryDate(calendar1.getTime());
        mmap.put("reportCommon", reportCommon);
        return "report/purchaseReport/purchaseReport";
    }

    /**
     * 来料报表
     * @Author 方舟
     * @Date 2021/6/4 12:26:56
    **/
    @PostMapping("/purchaseIncomingList")
    @ResponseBody
    public TableDataInfo purchaseIncomingList(ReportCommon reportCommon)
    {
        startPage();
        List<ReportPurchaseIncoming> list = reportCommonService.purchaseIncomingList(reportCommon);
        return getDataTable(list);
    }

    /**
     * 来料报表数量
     * @Author 方舟
     * @Date 2021/6/7 9:43:56
     **/
    @PostMapping("/purchaseIncomingQtyList")
    @ResponseBody
    public AjaxResult purchaseIncomingQtyList(ReportCommon reportCommon)
    {
        startPage();
        List<ReportPurchaseIncoming> list = reportCommonService.purchaseIncomingQtyList(reportCommon);
        return AjaxResult.success(list);
    }

    /**
     * 生产财务报表
     * @Author 方舟
     * @Date 2021/6/4 10:57:49
     **/
    @RequiresPermissions("report:reportCommon:view")
    @GetMapping("/productionFinancialReport")
    public String productionFinancialReport(ModelMap mmap)
    {
        ReportCommon reportCommon = new ReportCommon();
        //获取前月的第一天
        Calendar cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        reportCommon.setBeginProduceDate(cal_1.getTime());
        reportCommon.setEndProduceDate(cale.getTime());
        mmap.put("reportCommon", reportCommon);
        return "report/produceReport/szrzProductionFinancialReportA";
    }


    /**
     * 生产财务报表
     * @Author 方舟
     * @Date 2021/6/4 12:26:56
    **/
    @PostMapping("/productionFinancialList")
    @ResponseBody
    public TableDataInfo productionFinancialList(ReportCommon reportCommon)
    {
        startPage();
        List<ReportProductionFinancial> list = reportCommonService.productionFinancialList(reportCommon);
        return getDataTable(list);
    }


    /**
     * 导出报表公共列表
     */
    @RequiresPermissions("report:reportCommon:export")
    @Log(title = "生产财务报表", businessType = BusinessType.EXPORT)
    @PostMapping("/productionFinancialExport")
    @ResponseBody
    public AjaxResult productionFinancialExport(ReportCommon reportCommon)
    {
        List<ReportProductionFinancial> list = reportCommonService.productionFinancialList(reportCommon);
        ExcelUtil<ReportProductionFinancial> util = new ExcelUtil<ReportProductionFinancial>(ReportProductionFinancial.class);
        return util.exportExcel(list, "生产财务报表");
    }
}
