package com.ruoyi.project.config.configCorrugated.controller;

import java.util.List;

import com.ruoyi.project.config.configCorrugated.domain.ConfigCorrugated;
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
import com.ruoyi.project.config.configCorrugated.service.IConfigCorrugatedService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 楞型配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configCorrugated")
public class ConfigCorrugatedController extends BaseController
{
    private String prefix = "config/configCorrugated";

    @Autowired
    private IConfigCorrugatedService configCorrugatedService;

    @RequiresPermissions("config:configCorrugated:view")
    @GetMapping()
    public String configCorrugated()
    {
        return prefix + "/configCorrugated";
    }

    /**
     * 查询楞型配置列表
     */
    @RequiresPermissions("config:configCorrugated:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigCorrugated configCorrugated)
    {
        startPage();
        List<ConfigCorrugated> list = configCorrugatedService.selectConfigCorrugatedList(configCorrugated);
        return getDataTable(list);
    }

    /**
     * 导出楞型配置列表
     */
    @RequiresPermissions("config:configCorrugated:export")
    @Log(title = "楞型配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigCorrugated configCorrugated)
    {
        List<ConfigCorrugated> list = configCorrugatedService.selectConfigCorrugatedList(configCorrugated);
        ExcelUtil<ConfigCorrugated> util = new ExcelUtil<ConfigCorrugated>(ConfigCorrugated.class);
        return util.exportExcel(list, "楞型配置");
    }

    /**
     * 新增楞型配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存楞型配置
     */
    @RequiresPermissions("config:configCorrugated:add")
    @Log(title = "楞型配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigCorrugated configCorrugated)
    {
        return toAjax(configCorrugatedService.insertConfigCorrugated(configCorrugated));
    }

    /**
     * 修改楞型配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigCorrugated configCorrugated = configCorrugatedService.selectConfigCorrugatedById(id);
        mmap.put("configCorrugated", configCorrugated);
        return prefix + "/edit";
    }

    /**
     * 修改保存楞型配置
     */
    @RequiresPermissions("config:configCorrugated:edit")
    @Log(title = "楞型配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigCorrugated configCorrugated)
    {
        return toAjax(configCorrugatedService.updateConfigCorrugated(configCorrugated));
    }

    /**
     * 删除楞型配置
     */
    @RequiresPermissions("config:configCorrugated:remove")
    @Log(title = "楞型配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configCorrugatedService.deleteConfigCorrugatedByIds(ids));
    }


    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "楞型配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configCorrugated:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigCorrugated> util = new ExcelUtil<ConfigCorrugated>(ConfigCorrugated.class);
        List<ConfigCorrugated> configCorrugatedList = util.importExcel(file.getInputStream());
        String message = configCorrugatedService.importConfigCorrugated(configCorrugatedList, updateSupport);
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
        ExcelUtil<ConfigCorrugated> util = new ExcelUtil<ConfigCorrugated>(ConfigCorrugated.class);
        return util.importTemplateExcel("楞型配置");
    }
}
