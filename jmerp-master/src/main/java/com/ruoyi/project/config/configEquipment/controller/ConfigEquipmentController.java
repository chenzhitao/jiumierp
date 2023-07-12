package com.ruoyi.project.config.configEquipment.controller;

import java.util.List;

import com.ruoyi.project.config.configEmployee.domain.ConfigEmployee;
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
import com.ruoyi.project.config.configEquipment.domain.ConfigEquipment;
import com.ruoyi.project.config.configEquipment.service.IConfigEquipmentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 设备管理Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configEquipment")
public class ConfigEquipmentController extends BaseController
{
    private String prefix = "config/configEquipment";

    @Autowired
    private IConfigEquipmentService configEquipmentService;

    @RequiresPermissions("config:configEquipment:view")
    @GetMapping()
    public String configEquipment()
    {
        return prefix + "/configEquipment";
    }

    /**
     * 查询设备管理列表
     */
    @RequiresPermissions("config:configEquipment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigEquipment configEquipment)
    {
        startPage();
        List<ConfigEquipment> list = configEquipmentService.selectConfigEquipmentList(configEquipment);
        return getDataTable(list);
    }

    /**
     * 导出设备管理列表
     */
    @RequiresPermissions("config:configEquipment:export")
    @Log(title = "设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigEquipment configEquipment)
    {
        List<ConfigEquipment> list = configEquipmentService.selectConfigEquipmentList(configEquipment);
        ExcelUtil<ConfigEquipment> util = new ExcelUtil<ConfigEquipment>(ConfigEquipment.class);
        return util.exportExcel(list, "设备管理");
    }

    /**
     * 新增设备管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存设备管理
     */
    @RequiresPermissions("config:configEquipment:add")
    @Log(title = "设备管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigEquipment configEquipment)
    {
        return toAjax(configEquipmentService.insertConfigEquipment(configEquipment));
    }

    /**
     * 修改设备管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigEquipment configEquipment = configEquipmentService.selectConfigEquipmentById(id);
        mmap.put("configEquipment", configEquipment);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备管理
     */
    @RequiresPermissions("config:configEquipment:edit")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigEquipment configEquipment)
    {
        return toAjax(configEquipmentService.updateConfigEquipment(configEquipment));
    }

    /**
     * 删除设备管理
     */
    @RequiresPermissions("config:configEquipment:remove")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configEquipmentService.deleteConfigEquipmentByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "设备管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configEquipment:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigEquipment> util = new ExcelUtil<ConfigEquipment>(ConfigEquipment.class);
        List<ConfigEquipment> configEquipmentList = util.importExcel(file.getInputStream());
        String message = configEquipmentService.importConfigEquipment(configEquipmentList, updateSupport);
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
        ExcelUtil<ConfigEquipment> util = new ExcelUtil<ConfigEquipment>(ConfigEquipment.class);
        return util.importTemplateExcel("设备管理");
    }

    /**
     * 打开标准尺寸
     * @Author 方舟
     * @Date 2021/6/24 10:51:15
    **/
    @GetMapping("/paperSize")
    public String paperSize()
    {
        return prefix + "/paperSize";
    }

}
