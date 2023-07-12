package com.ruoyi.project.config.configTeamMember.controller;

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
import com.ruoyi.project.config.configTeamMember.domain.ConfigTeamMember;
import com.ruoyi.project.config.configTeamMember.service.IConfigTeamMemberService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 班组组员Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configTeamMember")
public class ConfigTeamMemberController extends BaseController
{
    private String prefix = "config/configTeamMember";

    @Autowired
    private IConfigTeamMemberService configTeamMemberService;

    @RequiresPermissions("config:configTeamMember:view")
    @GetMapping()
    public String configTeamMember()
    {
        return prefix + "/configTeamMember";
    }

    /**
     * 查询班组组员列表
     */
    @RequiresPermissions("config:configTeamMember:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigTeamMember configTeamMember)
    {
        startPage();
        List<ConfigTeamMember> list = configTeamMemberService.selectConfigTeamMemberList(configTeamMember);
        return getDataTable(list);
    }

    /**
     * 导出班组组员列表
     */
    @RequiresPermissions("config:configTeamMember:export")
    @Log(title = "班组组员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigTeamMember configTeamMember)
    {
        List<ConfigTeamMember> list = configTeamMemberService.selectConfigTeamMemberList(configTeamMember);
        ExcelUtil<ConfigTeamMember> util = new ExcelUtil<ConfigTeamMember>(ConfigTeamMember.class);
        return util.exportExcel(list, "configTeamMember");
    }

    /**
     * 新增班组组员
     */
    @GetMapping("/add/{teamId}")
    public String add(@PathVariable("teamId") Long teamId, ModelMap mmap)
    {
        ConfigTeamMember configTeamMember = new ConfigTeamMember();
        configTeamMember.setTeamId(teamId);
        mmap.put("configTeamMember", configTeamMember);
        return prefix + "/add";
    }

    /**
     * 新增保存班组组员
     */
    @RequiresPermissions("config:configTeamMember:add")
    @Log(title = "班组组员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigTeamMember configTeamMember)
    {
        return toAjax(configTeamMemberService.insertConfigTeamMember(configTeamMember));
    }

    /**
     * 修改班组组员
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigTeamMember configTeamMember = configTeamMemberService.selectConfigTeamMemberById(id);
        mmap.put("configTeamMember", configTeamMember);
        return prefix + "/edit";
    }

    /**
     * 修改保存班组组员
     */
    @RequiresPermissions("config:configTeamMember:edit")
    @Log(title = "班组组员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigTeamMember configTeamMember)
    {
        return toAjax(configTeamMemberService.updateConfigTeamMember(configTeamMember));
    }

    /**
     * 删除班组组员
     */
    @RequiresPermissions("config:configTeamMember:remove")
    @Log(title = "班组组员", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configTeamMemberService.deleteConfigTeamMemberByIds(ids));
    }
}
