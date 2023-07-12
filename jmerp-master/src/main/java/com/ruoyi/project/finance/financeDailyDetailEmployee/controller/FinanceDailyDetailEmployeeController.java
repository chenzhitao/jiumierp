package com.ruoyi.project.finance.financeDailyDetailEmployee.controller;

import java.util.List;

import com.ruoyi.project.finance.financeDailyDetailEmployee.domain.FinanceDailyDetailEmployeeWage;
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
import com.ruoyi.project.finance.financeDailyDetailEmployee.domain.FinanceDailyDetailEmployee;
import com.ruoyi.project.finance.financeDailyDetailEmployee.service.IFinanceDailyDetailEmployeeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 排程员工Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/finance/financeDailyDetailEmployee")
public class FinanceDailyDetailEmployeeController extends BaseController
{
    private String prefix = "finance/financeDailyDetailEmployee";

    @Autowired
    private IFinanceDailyDetailEmployeeService financeDailyDetailEmployeeService;

    @RequiresPermissions("finance:financeDailyDetailEmployee:view")
    @GetMapping()
    public String financeDailyDetailEmployee()
    {
        return prefix + "/financeDailyDetailEmployee";
    }

    /**
     * 查询排程员工列表
     */
    @RequiresPermissions("finance:financeDailyDetailEmployee:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceDailyDetailEmployee financeDailyDetailEmployee)
    {
        startPage();
        List<FinanceDailyDetailEmployee> list = financeDailyDetailEmployeeService.selectFinanceDailyDetailEmployeeList(financeDailyDetailEmployee);
        return getDataTable(list);
    }


    /**
     * 导出排程员工列表
     */
    @RequiresPermissions("finance:financeDailyDetailEmployee:export")
    @Log(title = "排程员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceDailyDetailEmployee financeDailyDetailEmployee)
    {
        List<FinanceDailyDetailEmployee> list = financeDailyDetailEmployeeService.selectFinanceDailyDetailEmployeeList(financeDailyDetailEmployee);
        ExcelUtil<FinanceDailyDetailEmployee> util = new ExcelUtil<FinanceDailyDetailEmployee>(FinanceDailyDetailEmployee.class);
        return util.exportExcel(list, "排程员工数据");
    }

    /**
     * 新增排程员工
     */
    @GetMapping("/add/{financeDailyId}/{financeDailyDetailId}")
    public String add(@PathVariable("financeDailyId") Long financeDailyId,@PathVariable("financeDailyDetailId") Long financeDailyDetailId, ModelMap mmap)
    {
        FinanceDailyDetailEmployee paramVO = new FinanceDailyDetailEmployee();
        paramVO.setFinanceDailyDetailId(financeDailyDetailId);
        List<FinanceDailyDetailEmployee> list = financeDailyDetailEmployeeService.selectFinanceDailyDetailEmployeeList(paramVO);
        FinanceDailyDetailEmployee financeDailyDetailEmployee = new FinanceDailyDetailEmployee();
        financeDailyDetailEmployee.setFinanceDailyDetailId(financeDailyDetailId);
        financeDailyDetailEmployee.setFinanceDailyId(financeDailyId);
        financeDailyDetailEmployee.setProcessOrder(list.size()+1);
        mmap.put("financeDailyDetailEmployee", financeDailyDetailEmployee);
        return prefix + "/add";
    }

    /**
     * 新增保存排程员工
     */
    @RequiresPermissions("finance:financeDailyDetailEmployee:add")
    @Log(title = "排程员工", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceDailyDetailEmployee financeDailyDetailEmployee)
    {
        return toAjax(financeDailyDetailEmployeeService.insertFinanceDailyDetailEmployee(financeDailyDetailEmployee));
    }

    /**
     * 修改排程员工
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceDailyDetailEmployee financeDailyDetailEmployee = financeDailyDetailEmployeeService.selectFinanceDailyDetailEmployeeById(id);
        mmap.put("financeDailyDetailEmployee", financeDailyDetailEmployee);
        return prefix + "/edit";
    }

    /**
     * 修改保存排程员工
     */
    @RequiresPermissions("finance:financeDailyDetailEmployee:edit")
    @Log(title = "排程员工", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceDailyDetailEmployee financeDailyDetailEmployee)
    {
        return toAjax(financeDailyDetailEmployeeService.updateFinanceDailyDetailEmployee(financeDailyDetailEmployee));
    }

    /**
     * 删除排程员工
     */
    @RequiresPermissions("finance:financeDailyDetailEmployee:remove")
    @Log(title = "排程员工", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeDailyDetailEmployeeService.deleteFinanceDailyDetailEmployeeByIds(ids));
    }

    /**
     * 工资页面
     * @Author 方舟
     * @Date 2021/5/26 14:50:35
     **/
    @RequiresPermissions("finance:financeDaily:view")
    @GetMapping("/financeWage")
    public String financeWage()
    {
        return prefix + "/financeWage";
    }

    /**
     * 查询工资汇总
     */
    @RequiresPermissions("finance:financeDailyDetailEmployee:list")
    @PostMapping("/wageList")
    @ResponseBody
    public TableDataInfo wageList(FinanceDailyDetailEmployeeWage financeDailyDetailEmployeeWage)
    {
        startPage();
        List<FinanceDailyDetailEmployeeWage> list = financeDailyDetailEmployeeService.selectFinanceDailyDetailEmployeeWageList(financeDailyDetailEmployeeWage);
        return getDataTable(list);
    }

    /**
     * 导出排程员工列表
     */
    @RequiresPermissions("finance:financeDailyDetailEmployee:export")
    @Log(title = "排程员工", businessType = BusinessType.EXPORT)
    @PostMapping("/wageExport")
    @ResponseBody
    public AjaxResult wageExport(FinanceDailyDetailEmployeeWage financeDailyDetailEmployeeWage)
    {
        List<FinanceDailyDetailEmployeeWage> list = financeDailyDetailEmployeeService.selectFinanceDailyDetailEmployeeWageList(financeDailyDetailEmployeeWage);
        ExcelUtil<FinanceDailyDetailEmployeeWage> util = new ExcelUtil<FinanceDailyDetailEmployeeWage>(FinanceDailyDetailEmployeeWage.class);
        return util.exportExcel(list, "员工工资汇总");
    }
}
