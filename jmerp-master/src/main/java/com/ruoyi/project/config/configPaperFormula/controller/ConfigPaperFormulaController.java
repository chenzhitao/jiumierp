package com.ruoyi.project.config.configPaperFormula.controller;

import java.util.List;

import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
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
import com.ruoyi.project.config.configPaperFormula.domain.ConfigPaperFormula;
import com.ruoyi.project.config.configPaperFormula.service.IConfigPaperFormulaService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 纸张配方Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configPaperFormula")
public class ConfigPaperFormulaController extends BaseController
{
    private String prefix = "config/configPaperFormula";

    @Autowired
    private IConfigPaperFormulaService configPaperFormulaService;

    @RequiresPermissions("config:configPaperFormula:view")
    @GetMapping()
    public String configPaperFormula()
    {
        return prefix + "/configPaperFormula";
    }

    /**
     * 查询纸张配方列表
     */
    @RequiresPermissions("config:configPaperFormula:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigPaperFormula configPaperFormula)
    {
        startPage();
        List<ConfigPaperFormula> list = configPaperFormulaService.selectConfigPaperFormulaList(configPaperFormula);
        return getDataTable(list);
    }

    /**
     * 导出纸张配方列表
     */
    @RequiresPermissions("config:configPaperFormula:export")
    @Log(title = "纸张配方", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigPaperFormula configPaperFormula)
    {
        List<ConfigPaperFormula> list = configPaperFormulaService.selectConfigPaperFormulaList(configPaperFormula);
        ExcelUtil<ConfigPaperFormula> util = new ExcelUtil<ConfigPaperFormula>(ConfigPaperFormula.class);
        return util.exportExcel(list, "纸张配方");
    }

    /**
     * 新增纸张配方
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存纸张配方
     */
    @RequiresPermissions("config:configPaperFormula:add")
    @Log(title = "纸张配方", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigPaperFormula configPaperFormula)
    {
        return toAjax(configPaperFormulaService.insertConfigPaperFormula(configPaperFormula));
    }

    /**
     * 修改纸张配方
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigPaperFormula configPaperFormula = configPaperFormulaService.selectConfigPaperFormulaById(id);
        mmap.put("configPaperFormula", configPaperFormula);
        return prefix + "/edit";
    }

    /**
     * 修改保存纸张配方
     */
    @RequiresPermissions("config:configPaperFormula:edit")
    @Log(title = "纸张配方", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigPaperFormula configPaperFormula)
    {
        return toAjax(configPaperFormulaService.updateConfigPaperFormula(configPaperFormula));
    }

    /**
     * 删除纸张配方
     */
    @RequiresPermissions("config:configPaperFormula:remove")
    @Log(title = "纸张配方", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configPaperFormulaService.deleteConfigPaperFormulaByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "纸张配方", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configPaperFormula:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigPaperFormula> util = new ExcelUtil<ConfigPaperFormula>(ConfigPaperFormula.class);
        List<ConfigPaperFormula> configPaperFormulaList = util.importExcel(file.getInputStream());
        String message = configPaperFormulaService.importConfigPaperFormula(configPaperFormulaList, updateSupport);
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
        ExcelUtil<ConfigPaperFormula> util = new ExcelUtil<ConfigPaperFormula>(ConfigPaperFormula.class);
        return util.importTemplateExcel("纸张配方");
    }
}
