package com.ruoyi.project.config.configSupplierContact.controller;

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
import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.config.configSupplierContact.service.IConfigSupplierContactService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 供应商联系人Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configSupplierContact")
public class ConfigSupplierContactController extends BaseController
{
    private String prefix = "config/configSupplierContact";

    @Autowired
    private IConfigSupplierContactService configSupplierContactService;

    @RequiresPermissions("config:configSupplierContact:view")
    @GetMapping()
    public String configSupplierContact()
    {
        return prefix + "/configSupplierContact";
    }

    /**
     * 查询供应商联系人列表
     */
    @RequiresPermissions("config:configSupplierContact:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigSupplierContact configSupplierContact)
    {
        startPage();
        List<ConfigSupplierContact> list = configSupplierContactService.selectConfigSupplierContactList(configSupplierContact);
        return getDataTable(list);
    }

    /**
     * 导出供应商联系人列表
     */
    @RequiresPermissions("config:configSupplierContact:export")
    @Log(title = "供应商联系人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigSupplierContact configSupplierContact)
    {
        List<ConfigSupplierContact> list = configSupplierContactService.selectConfigSupplierContactList(configSupplierContact);
        ExcelUtil<ConfigSupplierContact> util = new ExcelUtil<ConfigSupplierContact>(ConfigSupplierContact.class);
        return util.exportExcel(list, "configSupplierContact");
    }

    /**
     * 新增供应商联系人
     */
    @GetMapping("/add/{supplierId}")
    public String add(@PathVariable("supplierId") Long supplierId, ModelMap mmap)
    {
        ConfigSupplierContact configSupplierContact = new ConfigSupplierContact();
        configSupplierContact.setSupplierId(supplierId);
        configSupplierContact.setIsDefault("N");
        mmap.put("configSupplierContact", configSupplierContact);
        return prefix + "/add";
    }

    /**
     * 新增保存供应商联系人
     */
    @RequiresPermissions("config:configSupplierContact:add")
    @Log(title = "供应商联系人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigSupplierContact configSupplierContact)
    {
        return toAjax(configSupplierContactService.insertConfigSupplierContact(configSupplierContact));
    }

    /**
     * 修改供应商联系人
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigSupplierContact configSupplierContact = configSupplierContactService.selectConfigSupplierContactById(id);
        mmap.put("configSupplierContact", configSupplierContact);
        return prefix + "/edit";
    }

    /**
     * 修改保存供应商联系人
     */
    @RequiresPermissions("config:configSupplierContact:edit")
    @Log(title = "供应商联系人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigSupplierContact configSupplierContact)
    {
        return toAjax(configSupplierContactService.updateConfigSupplierContact(configSupplierContact));
    }

    /**
     * 删除供应商联系人
     */
    @RequiresPermissions("config:configSupplierContact:remove")
    @Log(title = "供应商联系人", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configSupplierContactService.deleteConfigSupplierContactByIds(ids));
    }

    /**
     * 设为默认
     * @Author 方舟
     * @Date 2021/4/14 15:04:29
    **/
    @PostMapping( "/setDefaultContact")
    @ResponseBody
    public AjaxResult setDefaultContact(ConfigSupplierContact configSupplierContact)
    {
        return toAjax(configSupplierContactService.setDefaultContact(configSupplierContact));
    }

    /**
     * 获取默认联系人
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
    **/
    @PostMapping("/getDefaultContact")
    @ResponseBody
    public AjaxResult getDefaultContact(ConfigSupplierContact configSupplierContact)
    {
        return AjaxResult.success(configSupplierContactService.getDefaultContact(configSupplierContact));
    }



}
