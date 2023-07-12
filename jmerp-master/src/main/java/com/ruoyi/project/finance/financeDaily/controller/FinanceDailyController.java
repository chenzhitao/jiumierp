package com.ruoyi.project.finance.financeDaily.controller;

import java.util.List;

import com.ruoyi.project.finance.financeDaily.domain.FinanceDailyExport;
import com.ruoyi.project.outsource.outsourceChecking.domain.OutsourceChecking;
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
import com.ruoyi.project.finance.financeDaily.domain.FinanceDaily;
import com.ruoyi.project.finance.financeDaily.service.IFinanceDailyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 生产日报Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/finance/financeDaily")
public class FinanceDailyController extends BaseController
{
    private String prefix = "finance/financeDaily";

    @Autowired
    private IFinanceDailyService financeDailyService;

    @RequiresPermissions("finance:financeDaily:view")
    @GetMapping()
    public String financeDaily()
    {
        return prefix + "/financeDaily";
    }

    /**
     * 查询生产日报列表
     */
    @RequiresPermissions("finance:financeDaily:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceDaily financeDaily)
    {
        startPage();
        List<FinanceDaily> list = financeDailyService.selectFinanceDailyList(financeDaily);
        return getDataTable(list);
    }

    /**
     * 导出生产日报列表
     */
    @RequiresPermissions("finance:financeDaily:export")
    @Log(title = "生产日报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceDaily financeDaily)
    {
        List<FinanceDailyExport> list = financeDailyService.selectFinanceDailyExportList(financeDaily);
        ExcelUtil<FinanceDailyExport> util = new ExcelUtil<FinanceDailyExport>(FinanceDailyExport.class);
        return util.exportExcel(list, "生产日报");
    }

    /**
     * 新增生产日报
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生产日报
     */
    @RequiresPermissions("finance:financeDaily:add")
    @Log(title = "生产日报", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceDaily financeDaily)
    {
        return AjaxResult.success(financeDailyService.insertFinanceDaily(financeDaily));
    }

    /**
     * 修改生产日报
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceDaily financeDaily = financeDailyService.selectFinanceDailyById(id);
        mmap.put("financeDaily", financeDaily);
        return prefix + "/financeDailyDetail";
    }

    /**
     * 修改保存生产日报
     */
    @RequiresPermissions("finance:financeDaily:edit")
    @Log(title = "生产日报", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceDaily financeDaily)
    {
        return toAjax(financeDailyService.updateFinanceDaily(financeDaily));
    }

    /**
     * 删除生产日报
     */
    @RequiresPermissions("finance:financeDaily:remove")
    @Log(title = "生产日报", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeDailyService.deleteFinanceDailyByIds(ids));
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceDaily financeDaily = new FinanceDaily();
        financeDaily = financeDailyService.selectFinanceDailyById(id);
        mmap.put("financeDaily", financeDaily);
        return prefix + "/print";
    }

}
