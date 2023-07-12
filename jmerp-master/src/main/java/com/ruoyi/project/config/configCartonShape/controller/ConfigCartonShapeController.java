package com.ruoyi.project.config.configCartonShape.controller;

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
import com.ruoyi.project.config.configCartonShape.domain.ConfigCartonShape;
import com.ruoyi.project.config.configCartonShape.service.IConfigCartonShapeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 箱型配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configCartonShape")
public class ConfigCartonShapeController extends BaseController
{
    private String prefix = "config/configCartonShape";

    @Autowired
    private IConfigCartonShapeService configCartonShapeService;

    @RequiresPermissions("config:configCartonShape:view")
    @GetMapping()
    public String configCartonShape()
    {
        return prefix + "/configCartonShape";
    }

    /**
     * 查询箱型配置列表
     */
    @RequiresPermissions("config:configCartonShape:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigCartonShape configCartonShape)
    {
        startPage();
        List<ConfigCartonShape> list = configCartonShapeService.selectConfigCartonShapeList(configCartonShape);
        return getDataTable(list);
    }

    /**
     * 导出箱型配置列表
     */
    @RequiresPermissions("config:configCartonShape:export")
    @Log(title = "箱型配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigCartonShape configCartonShape)
    {
        List<ConfigCartonShape> list = configCartonShapeService.selectConfigCartonShapeList(configCartonShape);
        ExcelUtil<ConfigCartonShape> util = new ExcelUtil<ConfigCartonShape>(ConfigCartonShape.class);
        return util.exportExcel(list, "configCartonShape");
    }

    /**
     * 新增箱型配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存箱型配置
     */
    @RequiresPermissions("config:configCartonShape:add")
    @Log(title = "箱型配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigCartonShape configCartonShape)
    {
        return toAjax(configCartonShapeService.insertConfigCartonShape(configCartonShape));
    }

    /**
     * 修改箱型配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigCartonShape configCartonShape = configCartonShapeService.selectConfigCartonShapeById(id);
        mmap.put("configCartonShape", configCartonShape);
        return prefix + "/edit";
    }

    /**
     * 修改保存箱型配置
     */
    @RequiresPermissions("config:configCartonShape:edit")
    @Log(title = "箱型配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigCartonShape configCartonShape)
    {
        return toAjax(configCartonShapeService.updateConfigCartonShape(configCartonShape));
    }

    /**
     * 根据ID查询对象
     * @Author 方舟
     * @Date 2021/5/6 10:13:49
    **/
    @PostMapping("/selectCartonShapeById")
    @ResponseBody
    public AjaxResult selectCartonShapeById(ConfigCartonShape configCartonShape)
    {
        ConfigCartonShape resultVO = configCartonShapeService.selectConfigCartonShapeById(configCartonShape.getId());
        return AjaxResult.success(resultVO);
    }

    /**
     * 删除箱型配置
     */
    @RequiresPermissions("config:configCartonShape:remove")
    @Log(title = "箱型配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configCartonShapeService.deleteConfigCartonShapeByIds(ids));
    }
}
