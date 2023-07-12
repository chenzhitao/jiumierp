package com.ruoyi.project.finance.financeStandard.controller;

import java.util.List;

import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
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
import com.ruoyi.project.finance.financeStandard.domain.FinanceStandard;
import com.ruoyi.project.finance.financeStandard.service.IFinanceStandardService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 计费标准Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/finance/financeStandard")
public class FinanceStandardController extends BaseController
{
    private String prefix = "finance/financeStandard";

    @Autowired
    private IFinanceStandardService financeStandardService;

    @RequiresPermissions("finance:financeStandard:view")
    @GetMapping()
    public String financeStandard()
    {
        return prefix + "/financeStandard";
    }

    /**
     * 查询计费标准列表
     */
    @RequiresPermissions("finance:financeStandard:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceStandard financeStandard)
    {
        startPage();
        List<FinanceStandard> list = financeStandardService.selectFinanceStandardList(financeStandard);
        return getDataTable(list);
    }

    /**
     * 导出计费标准列表
     */
    @RequiresPermissions("finance:financeStandard:export")
    @Log(title = "计费标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceStandard financeStandard)
    {
        List<FinanceStandard> list = financeStandardService.selectFinanceStandardList(financeStandard);
        ExcelUtil<FinanceStandard> util = new ExcelUtil<FinanceStandard>(FinanceStandard.class);
        return util.exportExcel(list, "计费标准");
    }

    /**
     * 新增计费标准
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        FinanceStandard financeStandard = new FinanceStandard();
        financeStandard.setId(-1L);
        financeStandard.setStatus("vaild");
        mmap.put("financeStandard", financeStandard);
        return prefix + "/edit";
    }

    /**
     * 新增保存计费标准
     */
    @RequiresPermissions("finance:financeStandard:add")
    @Log(title = "计费标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceStandard financeStandard)
    {
        return toAjax(financeStandardService.insertFinanceStandard(financeStandard));
    }

    /**
     * 修改计费标准
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceStandard financeStandard = financeStandardService.selectFinanceStandardById(id);
        mmap.put("financeStandard", financeStandard);
        return prefix + "/edit";
    }

    /**
     * 修改保存计费标准
     */
    @RequiresPermissions("finance:financeStandard:edit")
    @Log(title = "计费标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceStandard financeStandard)
    {
        return toAjax(financeStandardService.updateFinanceStandard(financeStandard));
    }

    /**
     * 删除计费标准
     */
    @RequiresPermissions("finance:financeStandard:remove")
    @Log(title = "计费标准", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeStandardService.deleteFinanceStandardByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "供应商信息", businessType = BusinessType.IMPORT)
    @RequiresPermissions("finance:financeStandard:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<FinanceStandard> util = new ExcelUtil<FinanceStandard>(FinanceStandard.class);
        List<FinanceStandard> financeStandardList = util.importExcel(file.getInputStream());
        String message = financeStandardService.importFinanceStandard(financeStandardList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 导入模板
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<FinanceStandard> util = new ExcelUtil<FinanceStandard>(FinanceStandard.class);
        return util.importTemplateExcel("计费标准");
    }

    /**
     * 获取一个信息
     * @Author 方舟
     * @Date 2021/5/26 9:32:01
    **/
    @PostMapping("/getSingleInfo")
    @ResponseBody
    public AjaxResult getSingleInfo(FinanceStandard financeStandard) throws Exception
    {
        FinanceStandard resultVO = financeStandardService.selectFinanceStandardById(financeStandard.getId());
        return AjaxResult.success(resultVO);
    }

}

