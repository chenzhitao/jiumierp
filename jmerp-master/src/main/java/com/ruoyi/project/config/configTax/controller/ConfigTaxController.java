package com.ruoyi.project.config.configTax.controller;

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
import com.ruoyi.project.config.configTax.domain.ConfigTax;
import com.ruoyi.project.config.configTax.service.IConfigTaxService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 税率Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configTax")
public class ConfigTaxController extends BaseController
{
    private String prefix = "config/configTax";

    @Autowired
    private IConfigTaxService configTaxService;

    @RequiresPermissions("config:configTax:view")
    @GetMapping()
    public String configTax()
    {
        return prefix + "/configTax";
    }

    /**
     * 查询税率列表
     */
    @RequiresPermissions("config:configTax:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigTax configTax)
    {
        startPage();
        List<ConfigTax> list = configTaxService.selectConfigTaxList(configTax);
        return getDataTable(list);
    }

    /**
     * 导出税率列表
     */
    @RequiresPermissions("config:configTax:export")
    @Log(title = "税率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigTax configTax)
    {
        List<ConfigTax> list = configTaxService.selectConfigTaxList(configTax);
        ExcelUtil<ConfigTax> util = new ExcelUtil<ConfigTax>(ConfigTax.class);
        return util.exportExcel(list, "税率配置");
    }

    /**
     * 新增税率
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存税率
     */
    @RequiresPermissions("config:configTax:add")
    @Log(title = "税率", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigTax configTax)
    {
        return toAjax(configTaxService.insertConfigTax(configTax));
    }

    /**
     * 修改税率
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigTax configTax = configTaxService.selectConfigTaxById(id);
        mmap.put("configTax", configTax);
        return prefix + "/edit";
    }

    /**
     * 修改保存税率
     */
    @RequiresPermissions("config:configTax:edit")
    @Log(title = "税率配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigTax configTax)
    {
        return toAjax(configTaxService.updateConfigTax(configTax));
    }

    /**
     * 删除税率
     */
    @RequiresPermissions("config:configTax:remove")
    @Log(title = "税率配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configTaxService.deleteConfigTaxByIds(ids));
    }
}
