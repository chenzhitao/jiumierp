package com.ruoyi.project.config.configEmployee.controller;

import java.util.List;

import com.ruoyi.project.config.configCorrugated.domain.ConfigCorrugated;
import com.ruoyi.project.system.user.domain.User;
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
import com.ruoyi.project.config.configEmployee.domain.ConfigEmployee;
import com.ruoyi.project.config.configEmployee.service.IConfigEmployeeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工信息Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configEmployee")
public class ConfigEmployeeController extends BaseController
{
    private String prefix = "config/configEmployee";

    @Autowired
    private IConfigEmployeeService configEmployeeService;

    @RequiresPermissions("config:configEmployee:view")
    @GetMapping()
    public String configEmployee()
    {
        return prefix + "/configEmployee";
    }

    /**
     * 查询员工信息列表
     */
    @RequiresPermissions("config:configEmployee:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigEmployee configEmployee)
    {
        startPage();
        List<ConfigEmployee> list = configEmployeeService.selectConfigEmployeeList(configEmployee);
        return getDataTable(list);
    }

    /**
     * 导出员工信息列表
     */
    @RequiresPermissions("config:configEmployee:export")
    @Log(title = "员工信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigEmployee configEmployee)
    {
        List<ConfigEmployee> list = configEmployeeService.selectConfigEmployeeList(configEmployee);
        ExcelUtil<ConfigEmployee> util = new ExcelUtil<ConfigEmployee>(ConfigEmployee.class);
        return util.exportExcel(list, "员工信息");
    }

    /**
     * 新增员工信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存员工信息
     */
    @RequiresPermissions("config:configEmployee:add")
    @Log(title = "员工信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigEmployee configEmployee)
    {
        return toAjax(configEmployeeService.insertConfigEmployee(configEmployee));
    }

    /**
     * 修改员工信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigEmployee configEmployee = configEmployeeService.selectConfigEmployeeById(id);
        mmap.put("configEmployee", configEmployee);
        return prefix + "/edit";
    }

    /**
     * 修改保存员工信息
     */
    @RequiresPermissions("config:configEmployee:edit")
    @Log(title = "员工信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigEmployee configEmployee)
    {
        return toAjax(configEmployeeService.updateConfigEmployee(configEmployee));
    }

    /**
     * 删除员工信息
     */
    @RequiresPermissions("config:configEmployee:remove")
    @Log(title = "员工信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configEmployeeService.deleteConfigEmployeeByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
    **/
    @Log(title = "员工信息", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configEmployee:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigEmployee> util = new ExcelUtil<ConfigEmployee>(ConfigEmployee.class);
        List<ConfigEmployee> configEmployeeList = util.importExcel(file.getInputStream());
        String message = configEmployeeService.importConfigEmployee(configEmployeeList, updateSupport);
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
        ExcelUtil<ConfigEmployee> util = new ExcelUtil<ConfigEmployee>(ConfigEmployee.class);
        return util.importTemplateExcel("员工信息");
    }
}
