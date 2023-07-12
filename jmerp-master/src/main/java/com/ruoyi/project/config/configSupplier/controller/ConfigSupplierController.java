package com.ruoyi.project.config.configSupplier.controller;

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
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.config.configSupplier.service.IConfigSupplierService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 供应商信息Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configSupplier")
public class ConfigSupplierController extends BaseController
{
    private String prefix = "config/configSupplier";

    @Autowired
    private IConfigSupplierService configSupplierService;

    @RequiresPermissions("config:configSupplier:view")
    @GetMapping()
    public String configSupplier()
    {
        return prefix + "/configSupplier";
    }

    /**
     * 查询供应商信息列表
     */
    @RequiresPermissions("config:configSupplier:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigSupplier configSupplier)
    {
        startPage();
        List<ConfigSupplier> list = configSupplierService.selectConfigSupplierList(configSupplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商信息列表
     */
    @RequiresPermissions("config:configSupplier:export")
    @Log(title = "供应商信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigSupplier configSupplier)
    {
        List<ConfigSupplier> list = configSupplierService.selectConfigSupplierList(configSupplier);
        ExcelUtil<ConfigSupplier> util = new ExcelUtil<ConfigSupplier>(ConfigSupplier.class);
        return util.exportExcel(list, "供应商信息");
    }

    /**
     * 新增供应商信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存供应商信息
     */
    @RequiresPermissions("config:configSupplier:add")
    @Log(title = "供应商信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigSupplier configSupplier)
    {
        return toAjax(configSupplierService.insertConfigSupplier(configSupplier));
    }

    /**
     * 修改供应商信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigSupplier configSupplier = configSupplierService.selectConfigSupplierById(id);
        mmap.put("configSupplier", configSupplier);
        return prefix + "/edit";
    }

    /**
     * 修改保存供应商信息
     */
    @RequiresPermissions("config:configSupplier:edit")
    @Log(title = "供应商信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigSupplier configSupplier)
    {
        return toAjax(configSupplierService.updateConfigSupplier(configSupplier));
    }

    /**
     * 删除供应商信息
     */
    @RequiresPermissions("config:configSupplier:remove")
    @Log(title = "供应商信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configSupplierService.deleteConfigSupplierByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "供应商信息", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configSupplier:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigSupplier> util = new ExcelUtil<ConfigSupplier>(ConfigSupplier.class);
        List<ConfigSupplier> configSupplierList = util.importExcel(file.getInputStream());
        String message = configSupplierService.importConfigSupplier(configSupplierList, updateSupport);
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
        ExcelUtil<ConfigSupplier> util = new ExcelUtil<ConfigSupplier>(ConfigSupplier.class);
        return util.importTemplateExcel("客户信息");
    }
}
