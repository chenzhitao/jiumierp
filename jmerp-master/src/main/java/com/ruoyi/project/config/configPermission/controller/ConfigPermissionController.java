package com.ruoyi.project.config.configPermission.controller;

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
import com.ruoyi.project.config.configPermission.domain.ConfigPermission;
import com.ruoyi.project.config.configPermission.service.IConfigPermissionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 数据权限Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configPermission")
public class ConfigPermissionController extends BaseController
{
    private String prefix = "config/configPermission";

    @Autowired
    private IConfigPermissionService configPermissionService;

    @RequiresPermissions("config:configPermission:view")
    @GetMapping()
    public String configPermission()
    {
        return prefix + "/configPermission";
    }

    /**
     * 查询数据权限列表
     */
    @RequiresPermissions("config:configPermission:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigPermission configPermission)
    {
        startPage();
        List<ConfigPermission> list = configPermissionService.selectConfigPermissionList(configPermission);
        return getDataTable(list);
    }

    /**
     * 导出数据权限列表
     */
    @RequiresPermissions("config:configPermission:export")
    @Log(title = "数据权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigPermission configPermission)
    {
        List<ConfigPermission> list = configPermissionService.selectConfigPermissionList(configPermission);
        ExcelUtil<ConfigPermission> util = new ExcelUtil<ConfigPermission>(ConfigPermission.class);
        return util.exportExcel(list, "configPermission");
    }

    /**
     * 新增数据权限
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存数据权限
     */
    @RequiresPermissions("config:configPermission:add")
    @Log(title = "数据权限", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigPermission configPermission)
    {
        return toAjax(configPermissionService.insertConfigPermission(configPermission));
    }

    /**
     * 修改数据权限
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        ConfigPermission configPermission = new ConfigPermission();
        configPermission.setUserId(userId);
        mmap.put("configPermission", configPermission);
        return prefix + "/edit";
    }

    /**
     * 修改保存数据权限
     */
    @RequiresPermissions("config:configPermission:edit")
    @Log(title = "数据权限", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigPermission configPermission)
    {
        return toAjax(configPermissionService.updateConfigPermission(configPermission));
    }

    /**
     * 删除数据权限
     */
    @RequiresPermissions("config:configPermission:remove")
    @Log(title = "数据权限", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configPermissionService.deleteConfigPermissionByIds(ids));
    }

    /**
     *
     * @Author 方舟
     * @Date 2021/4/14 21:41:17
    **/
    @PostMapping( "/saveConfigPermission")
    @ResponseBody
    public AjaxResult saveConfigPermission(ConfigPermission configPermission)
    {
        return toAjax(configPermissionService.saveConfigPermission(configPermission));
    }
}
