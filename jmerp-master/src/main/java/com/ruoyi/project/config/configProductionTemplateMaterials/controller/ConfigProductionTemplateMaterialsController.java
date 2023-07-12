package com.ruoyi.project.config.configProductionTemplateMaterials.controller;

import java.util.List;

import com.ruoyi.project.config.configQuotationTemplateMaterials.domain.ConfigQuotationTemplateMaterials;
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
import com.ruoyi.project.config.configProductionTemplateMaterials.domain.ConfigProductionTemplateMaterials;
import com.ruoyi.project.config.configProductionTemplateMaterials.service.IConfigProductionTemplateMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 生产工艺卡材料Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configProductionTemplateMaterials")
public class ConfigProductionTemplateMaterialsController extends BaseController
{
    private String prefix = "config/configProductionTemplateMaterials";

    @Autowired
    private IConfigProductionTemplateMaterialsService configProductionTemplateMaterialsService;

    @RequiresPermissions("config:configProductionTemplateMaterials:view")
    @GetMapping()
    public String configProductionTemplateMaterials()
    {
        return prefix + "/configProductionTemplateMaterials";
    }

    /**
     * 查询生产工艺卡材料列表
     */
    @RequiresPermissions("config:configProductionTemplateMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigProductionTemplateMaterials configProductionTemplateMaterials)
    {
        startPage();
        List<ConfigProductionTemplateMaterials> list = configProductionTemplateMaterialsService.selectConfigProductionTemplateMaterialsList(configProductionTemplateMaterials);
        return getDataTable(list);
    }

    /**
     * 导出生产工艺卡材料列表
     */
    @RequiresPermissions("config:configProductionTemplateMaterials:export")
    @Log(title = "生产工艺卡材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigProductionTemplateMaterials configProductionTemplateMaterials)
    {
        List<ConfigProductionTemplateMaterials> list = configProductionTemplateMaterialsService.selectConfigProductionTemplateMaterialsList(configProductionTemplateMaterials);
        ExcelUtil<ConfigProductionTemplateMaterials> util = new ExcelUtil<ConfigProductionTemplateMaterials>(ConfigProductionTemplateMaterials.class);
        return util.exportExcel(list, "生产工艺卡材料");
    }

    /**
     * 新增生产工艺卡材料
     */
    /*@GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }*/
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigProductionTemplateMaterials configProductionTemplateMaterials = new ConfigProductionTemplateMaterials();
        configProductionTemplateMaterials.setProductionTemplateId(id);
        mmap.put("configProductionTemplateMaterials", configProductionTemplateMaterials);
        return prefix + "/add";
    }

    /**
     * 新增保存生产工艺卡材料
     */
    @RequiresPermissions("config:configProductionTemplateMaterials:add")
    @Log(title = "生产工艺卡材料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigProductionTemplateMaterials configProductionTemplateMaterials)
    {
        return toAjax(configProductionTemplateMaterialsService.insertConfigProductionTemplateMaterials(configProductionTemplateMaterials));
    }

    /**
     * 修改生产工艺卡材料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigProductionTemplateMaterials configProductionTemplateMaterials = configProductionTemplateMaterialsService.selectConfigProductionTemplateMaterialsById(id);
        mmap.put("configProductionTemplateMaterials", configProductionTemplateMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产工艺卡材料
     */
    @RequiresPermissions("config:configProductionTemplateMaterials:edit")
    @Log(title = "生产工艺卡材料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigProductionTemplateMaterials configProductionTemplateMaterials)
    {
        return toAjax(configProductionTemplateMaterialsService.updateConfigProductionTemplateMaterials(configProductionTemplateMaterials));
    }

    /**
     * 删除生产工艺卡材料
     */
    @RequiresPermissions("config:configProductionTemplateMaterials:remove")
    @Log(title = "生产工艺卡材料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configProductionTemplateMaterialsService.deleteConfigProductionTemplateMaterialsByIds(ids));
    }
}
