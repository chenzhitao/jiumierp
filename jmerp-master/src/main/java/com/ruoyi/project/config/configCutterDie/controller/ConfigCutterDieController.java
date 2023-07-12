package com.ruoyi.project.config.configCutterDie.controller;

import java.util.List;

import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
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
import com.ruoyi.project.config.configCutterDie.domain.ConfigCutterDie;
import com.ruoyi.project.config.configCutterDie.service.IConfigCutterDieService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 刀模配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configCutterDie")
public class ConfigCutterDieController extends BaseController
{
    private String prefix = "config/configCutterDie";

    @Autowired
    private IConfigCutterDieService configCutterDieService;

    @RequiresPermissions("config:configCutterDie:view")
    @GetMapping()
    public String configCutterDie()
    {
        return prefix + "/configCutterDie";
    }

    /**
     * 查询刀模配置列表
     */
    @RequiresPermissions("config:configCutterDie:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigCutterDie configCutterDie)
    {
        startPage();
        List<ConfigCutterDie> list = configCutterDieService.selectConfigCutterDieList(configCutterDie);
        return getDataTable(list);
    }

    /**
     * 导出刀模配置列表
     */
    @RequiresPermissions("config:configCutterDie:export")
    @Log(title = "刀模配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigCutterDie configCutterDie)
    {
        List<ConfigCutterDie> list = configCutterDieService.selectConfigCutterDieList(configCutterDie);
        ExcelUtil<ConfigCutterDie> util = new ExcelUtil<ConfigCutterDie>(ConfigCutterDie.class);
        return util.exportExcel(list, "刀模配置");
    }

    /**
     * 新增刀模配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存刀模配置
     */
    @RequiresPermissions("config:configCutterDie:add")
    @Log(title = "刀模配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigCutterDie configCutterDie)
    {
        return toAjax(configCutterDieService.insertConfigCutterDie(configCutterDie));
    }

    /**
     * 修改刀模配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigCutterDie configCutterDie = configCutterDieService.selectConfigCutterDieById(id);
        mmap.put("configCutterDie", configCutterDie);
        return prefix + "/edit";
    }

    /**
     * 修改保存刀模配置
     */
    @RequiresPermissions("config:configCutterDie:edit")
    @Log(title = "刀模配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigCutterDie configCutterDie)
    {
        return toAjax(configCutterDieService.updateConfigCutterDie(configCutterDie));
    }

    /**
     * 删除刀模配置
     */
    @RequiresPermissions("config:configCutterDie:remove")
    @Log(title = "刀模配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configCutterDieService.deleteConfigCutterDieByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "刀模配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configCutterDie:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigCutterDie> util = new ExcelUtil<ConfigCutterDie>(ConfigCutterDie.class);
        List<ConfigCutterDie> configCutterDieList = util.importExcel(file.getInputStream());
        String message = configCutterDieService.importConfigCutterDie(configCutterDieList, updateSupport);
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
        ExcelUtil<ConfigCutterDie> util = new ExcelUtil<ConfigCutterDie>(ConfigCutterDie.class);
        return util.importTemplateExcel("刀模配置");
    }
}
