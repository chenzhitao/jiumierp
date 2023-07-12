package com.ruoyi.project.config.configMaterials.controller;

import java.util.List;

import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.config.configSupplier.service.IConfigSupplierService;
import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.config.configSupplierContact.service.IConfigSupplierContactService;
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
import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configMaterials.service.IConfigMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 材料配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configMaterials")
public class ConfigMaterialsController extends BaseController
{
    private String prefix = "config/configMaterials";

    @Autowired
    private IConfigMaterialsService configMaterialsService;

    @Autowired
    private IConfigSupplierService configSupplierService;

    @Autowired
    private IConfigSupplierContactService configSupplierContactService;

    @RequiresPermissions("config:configMaterials:view")
    @GetMapping()
    public String configMaterials()
    {
        return prefix + "/configMaterials";
    }

    /**
     * 查询材料配置列表
     */
    @RequiresPermissions("config:configMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigMaterials configMaterials)
    {
        startPage();
        List<ConfigMaterials> list = configMaterialsService.selectConfigMaterialsList(configMaterials);
        return getDataTable(list);
    }

    /**
     * 导出材料配置列表
     */
    @RequiresPermissions("config:configMaterials:export")
    @Log(title = "材料配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigMaterials configMaterials)
    {
        List<ConfigMaterials> list = configMaterialsService.selectConfigMaterialsList(configMaterials);
        ExcelUtil<ConfigMaterials> util = new ExcelUtil<ConfigMaterials>(ConfigMaterials.class);
        return util.exportExcel(list, "材料配置");
    }

    /**
     * 新增材料配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存材料配置
     */
    @RequiresPermissions("config:configMaterials:add")
    @Log(title = "材料配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigMaterials configMaterials)
    {
        return toAjax(configMaterialsService.insertConfigMaterials(configMaterials));
    }

    /**
     * 修改材料配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigMaterials configMaterials = configMaterialsService.selectConfigMaterialsById(id);
        mmap.put("configMaterials", configMaterials);
        return prefix + "/edit";
    }

    @PostMapping("/single")
    @ResponseBody
    public AjaxResult single(ConfigMaterials configMaterials)
    {
        ConfigMaterials resVO = configMaterialsService.selectConfigMaterialsById(configMaterials.getId());
        return AjaxResult.success(resVO);
    }

    /**
     * 修改保存材料配置
     */
    @RequiresPermissions("config:configMaterials:edit")
    @Log(title = "材料配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigMaterials configMaterials)
    {
        return toAjax(configMaterialsService.updateConfigMaterials(configMaterials));
    }

    /**
     * 删除材料配置
     */
    @RequiresPermissions("config:configMaterials:remove")
    @Log(title = "材料配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configMaterialsService.deleteConfigMaterialsByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "材料配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configMaterials:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigMaterials> util = new ExcelUtil<ConfigMaterials>(ConfigMaterials.class);
        List<ConfigMaterials> configMaterialsList = util.importExcel(file.getInputStream());
        String message = configMaterialsService.importConfigMaterials(configMaterialsList, updateSupport);
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
        ExcelUtil<ConfigMaterials> util = new ExcelUtil<ConfigMaterials>(ConfigMaterials.class);
        return util.importTemplateExcel("材料配置");
    }

    /**
     * 根据ID查找完整信息
     * @Author 方舟
     * @Date 2021/5/10 12:03:50
    **/
    @PostMapping("/getMaterialsBaseInfo")
    @ResponseBody
    public AjaxResult getMaterialsBaseInfo(ConfigMaterials configMaterials){
        ConfigMaterials resultVO = configMaterialsService.getMaterialsBaseInfo(configMaterials);
        return AjaxResult.success(resultVO);
    }
}
