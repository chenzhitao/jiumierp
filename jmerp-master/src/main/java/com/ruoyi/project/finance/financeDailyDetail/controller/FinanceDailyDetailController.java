package com.ruoyi.project.finance.financeDailyDetail.controller;

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
import com.ruoyi.project.finance.financeDailyDetail.domain.FinanceDailyDetail;
import com.ruoyi.project.finance.financeDailyDetail.service.IFinanceDailyDetailService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 排程明细Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/finance/financeDailyDetail")
public class FinanceDailyDetailController extends BaseController
{
    private String prefix = "finance/financeDailyDetail";

    @Autowired
    private IFinanceDailyDetailService financeDailyDetailService;

    @RequiresPermissions("finance:financeDailyDetail:view")
    @GetMapping()
    public String financeDailyDetail()
    {
        return prefix + "/financeDailyDetail";
    }

    /**
     * 查询排程明细列表
     */
    @RequiresPermissions("finance:financeDailyDetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceDailyDetail financeDailyDetail)
    {
        startPage();
        List<FinanceDailyDetail> list = financeDailyDetailService.selectFinanceDailyDetailList(financeDailyDetail);
        return getDataTable(list);
    }

    /**
     * 导出排程明细列表
     */
    @RequiresPermissions("finance:financeDailyDetail:export")
    @Log(title = "排程明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceDailyDetail financeDailyDetail)
    {
        List<FinanceDailyDetail> list = financeDailyDetailService.selectFinanceDailyDetailList(financeDailyDetail);
        ExcelUtil<FinanceDailyDetail> util = new ExcelUtil<FinanceDailyDetail>(FinanceDailyDetail.class);
        return util.exportExcel(list, "排程明细数据");
    }

    /**
     * 新增排程明细
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceDailyDetail financeDailyDetail = new FinanceDailyDetail();
        financeDailyDetail.setFinanceDailyId(id);
        financeDailyDetail.setId(-1L);
        mmap.put("financeDailyDetail", financeDailyDetail);
        return prefix + "/edit";
    }

    /**
     * 新增保存排程明细
     */
    @RequiresPermissions("finance:financeDailyDetail:add")
    @Log(title = "排程明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceDailyDetail financeDailyDetail)
    {
        return toAjax(financeDailyDetailService.insertFinanceDailyDetail(financeDailyDetail));
    }

    /**
     * 修改排程明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceDailyDetail financeDailyDetail = financeDailyDetailService.selectFinanceDailyDetailById(id);
        mmap.put("financeDailyDetail", financeDailyDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存排程明细
     */
    @RequiresPermissions("finance:financeDailyDetail:edit")
    @Log(title = "排程明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceDailyDetail financeDailyDetail)
    {
        return toAjax(financeDailyDetailService.updateFinanceDailyDetail(financeDailyDetail));
    }

    /**
     * 删除排程明细
     */
    @RequiresPermissions("finance:financeDailyDetail:remove")
    @Log(title = "排程明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeDailyDetailService.deleteFinanceDailyDetailByIds(ids));
    }


    /**
     * 创建日报班组
     * @Author 方舟
     * @Date 2021/5/25 12:13:55
    **/
    @RequiresPermissions("finance:financeDailyDetail:add")
    @Log(title = "创建日报班组", businessType = BusinessType.INSERT)
    @PostMapping("/createFinanceDailyTeam")
    @ResponseBody
    public AjaxResult createFinanceDailyTeam(FinanceDailyDetail financeDailyDetail){
        return AjaxResult.success(financeDailyDetailService.createFinanceDailyTeam(financeDailyDetail));
    }

    /**
     * 打开分配
     */
    @GetMapping("/setup/{id}")
    public String setup(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceDailyDetail financeDailyDetail = financeDailyDetailService.selectFinanceDailyDetailById(id);
        mmap.put("financeDailyDetail", financeDailyDetail);
        return prefix + "/setupAmount";
    }
}
