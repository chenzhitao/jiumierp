package com.ruoyi.project.config.configQuotationTemplateMaterials.controller;

import java.util.List;

import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;
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
import com.ruoyi.project.config.configQuotationTemplateMaterials.domain.ConfigQuotationTemplateMaterials;
import com.ruoyi.project.config.configQuotationTemplateMaterials.service.IConfigQuotationTemplateMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 报价工艺卡材料Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configQuotationTemplateMaterials")
public class ConfigQuotationTemplateMaterialsController extends BaseController
{
    private String prefix = "config/configQuotationTemplateMaterials";

    @Autowired
    private IConfigQuotationTemplateMaterialsService configQuotationTemplateMaterialsService;

    @RequiresPermissions("config:configQuotationTemplateMaterials:view")
    @GetMapping()
    public String configQuotationTemplateMaterials()
    {
        return prefix + "/configQuotationTemplateMaterials";
    }

    /**
     * 查询报价工艺卡材料列表
     */
    @RequiresPermissions("config:configQuotationTemplateMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials)
    {
        startPage();
        List<ConfigQuotationTemplateMaterials> list = configQuotationTemplateMaterialsService.selectConfigQuotationTemplateMaterialsList(configQuotationTemplateMaterials);
        return getDataTable(list);
    }

    /**
     * 导出报价工艺卡材料列表
     */
    @RequiresPermissions("config:configQuotationTemplateMaterials:export")
    @Log(title = "报价工艺卡材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials)
    {
        List<ConfigQuotationTemplateMaterials> list = configQuotationTemplateMaterialsService.selectConfigQuotationTemplateMaterialsList(configQuotationTemplateMaterials);
        ExcelUtil<ConfigQuotationTemplateMaterials> util = new ExcelUtil<ConfigQuotationTemplateMaterials>(ConfigQuotationTemplateMaterials.class);
        return util.exportExcel(list, "报价工艺卡材料");
    }

    /**
     * 新增报价工艺卡材料
     */
    /*@GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }*/
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigQuotationTemplateMaterials configQuotationTemplateMaterials = new ConfigQuotationTemplateMaterials();
        configQuotationTemplateMaterials.setQuotationTemplateId(id);
        mmap.put("configQuotationTemplateMaterials", configQuotationTemplateMaterials);
        return prefix + "/add";
    }

    /**
     * 新增保存报价工艺卡材料
     */
    @RequiresPermissions("config:configQuotationTemplateMaterials:add")
    @Log(title = "报价工艺卡材料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials)
    {
        return toAjax(configQuotationTemplateMaterialsService.insertConfigQuotationTemplateMaterials(configQuotationTemplateMaterials));
    }

    /**
     * 修改报价工艺卡材料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigQuotationTemplateMaterials configQuotationTemplateMaterials = configQuotationTemplateMaterialsService.selectConfigQuotationTemplateMaterialsById(id);
        mmap.put("configQuotationTemplateMaterials", configQuotationTemplateMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存报价工艺卡材料
     */
    @RequiresPermissions("config:configQuotationTemplateMaterials:edit")
    @Log(title = "报价工艺卡材料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials)
    {
        return toAjax(configQuotationTemplateMaterialsService.updateConfigQuotationTemplateMaterials(configQuotationTemplateMaterials));
    }

    /**
     * 删除报价工艺卡材料
     */
    @RequiresPermissions("config:configQuotationTemplateMaterials:remove")
    @Log(title = "报价工艺卡材料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configQuotationTemplateMaterialsService.deleteConfigQuotationTemplateMaterialsByIds(ids));
    }
}
