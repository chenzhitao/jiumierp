package com.ruoyi.project.config.configWarehouse.controller;

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
import com.ruoyi.project.config.configWarehouse.domain.ConfigWarehouse;
import com.ruoyi.project.config.configWarehouse.service.IConfigWarehouseService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 仓库配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configWarehouse")
public class ConfigWarehouseController extends BaseController
{
    private String prefix = "config/configWarehouse";

    @Autowired
    private IConfigWarehouseService configWarehouseService;

    @RequiresPermissions("config:configWarehouse:view")
    @GetMapping()
    public String configWarehouse()
    {
        return prefix + "/configWarehouse";
    }

    /**
     * 查询仓库配置列表
     */
    @RequiresPermissions("config:configWarehouse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigWarehouse configWarehouse)
    {
        startPage();
        List<ConfigWarehouse> list = configWarehouseService.selectConfigWarehouseList(configWarehouse);
        return getDataTable(list);
    }

    /**
     * 导出仓库配置列表
     */
    @RequiresPermissions("config:configWarehouse:export")
    @Log(title = "仓库配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigWarehouse configWarehouse)
    {
        List<ConfigWarehouse> list = configWarehouseService.selectConfigWarehouseList(configWarehouse);
        ExcelUtil<ConfigWarehouse> util = new ExcelUtil<ConfigWarehouse>(ConfigWarehouse.class);
        return util.exportExcel(list, "仓库配置");
    }

    /**
     * 新增仓库配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库配置
     */
    @RequiresPermissions("config:configWarehouse:add")
    @Log(title = "仓库配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigWarehouse configWarehouse)
    {
        return toAjax(configWarehouseService.insertConfigWarehouse(configWarehouse));
    }

    /**
     * 修改仓库配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigWarehouse configWarehouse = configWarehouseService.selectConfigWarehouseById(id);
        mmap.put("configWarehouse", configWarehouse);
        return prefix + "/edit";
    }

    /**
     * 修改保存仓库配置
     */
    @RequiresPermissions("config:configWarehouse:edit")
    @Log(title = "仓库配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigWarehouse configWarehouse)
    {
        return toAjax(configWarehouseService.updateConfigWarehouse(configWarehouse));
    }

    /**
     * 删除仓库配置
     */
    @RequiresPermissions("config:configWarehouse:remove")
    @Log(title = "仓库配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configWarehouseService.deleteConfigWarehouseByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "仓库配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configWarehouse:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigWarehouse> util = new ExcelUtil<ConfigWarehouse>(ConfigWarehouse.class);
        List<ConfigWarehouse> configWarehouseList = util.importExcel(file.getInputStream());
        String message = configWarehouseService.importConfigWarehouse(configWarehouseList, updateSupport);
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
        ExcelUtil<ConfigWarehouse> util = new ExcelUtil<ConfigWarehouse>(ConfigWarehouse.class);
        return util.importTemplateExcel("仓库配置");
    }
}
