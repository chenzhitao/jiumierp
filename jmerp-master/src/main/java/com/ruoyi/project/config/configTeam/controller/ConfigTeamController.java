package com.ruoyi.project.config.configTeam.controller;

import java.util.List;

import com.ruoyi.project.config.configBoard.domain.ConfigBoard;
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
import com.ruoyi.project.config.configTeam.domain.ConfigTeam;
import com.ruoyi.project.config.configTeam.service.IConfigTeamService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 班组配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configTeam")
public class ConfigTeamController extends BaseController
{
    private String prefix = "config/configTeam";

    @Autowired
    private IConfigTeamService configTeamService;

    @RequiresPermissions("config:configTeam:view")
    @GetMapping()
    public String configTeam()
    {
        return prefix + "/configTeam";
    }

    /**
     * 查询班组配置列表
     */
    @RequiresPermissions("config:configTeam:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigTeam configTeam)
    {
        startPage();
        List<ConfigTeam> list = configTeamService.selectConfigTeamList(configTeam);
        return getDataTable(list);
    }

    /**
     * 导出班组配置列表
     */
    @RequiresPermissions("config:configTeam:export")
    @Log(title = "班组配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigTeam configTeam)
    {
        List<ConfigTeam> list = configTeamService.selectConfigTeamList(configTeam);
        ExcelUtil<ConfigTeam> util = new ExcelUtil<ConfigTeam>(ConfigTeam.class);
        return util.exportExcel(list, "班组配置");
    }

    /**
     * 新增班组配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存班组配置
     */
    @RequiresPermissions("config:configTeam:add")
    @Log(title = "班组配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigTeam configTeam)
    {
        return toAjax(configTeamService.insertConfigTeam(configTeam));
    }

    /**
     * 修改班组配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigTeam configTeam = configTeamService.selectConfigTeamById(id);
        mmap.put("configTeam", configTeam);
        return prefix + "/edit";
    }

    /**
     * 修改保存班组配置
     */
    @RequiresPermissions("config:configTeam:edit")
    @Log(title = "班组配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigTeam configTeam)
    {
        return toAjax(configTeamService.updateConfigTeam(configTeam));
    }

    /**
     * 删除班组配置
     */
    @RequiresPermissions("config:configTeam:remove")
    @Log(title = "班组配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configTeamService.deleteConfigTeamByIds(ids));
    }


    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "班组配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configTeam:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigTeam> util = new ExcelUtil<ConfigTeam>(ConfigTeam.class);
        List<ConfigTeam> configTeamList = util.importExcel(file.getInputStream());
        String message = configTeamService.importConfigTeam(configTeamList, updateSupport);
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
        ExcelUtil<ConfigTeam> util = new ExcelUtil<ConfigTeam>(ConfigTeam.class);
        return util.importTemplateExcel("班组配置");
    }
}
