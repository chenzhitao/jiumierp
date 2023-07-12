package com.ruoyi.project.config.configProductionTemplate.controller;

import java.util.List;

import com.ruoyi.project.config.configProductionTemplateMaterials.domain.ConfigProductionTemplateMaterials;
import com.ruoyi.project.config.configProductionTemplateProcess.domain.ConfigProductionTemplateProcess;
import com.ruoyi.project.config.configQuotationTemplate.domain.ConfigQuotationTemplate;
import com.ruoyi.project.config.configQuotationTemplateMaterials.domain.ConfigQuotationTemplateMaterials;
import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;
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
import com.ruoyi.project.config.configProductionTemplate.domain.ConfigProductionTemplate;
import com.ruoyi.project.config.configProductionTemplate.service.IConfigProductionTemplateService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 生产工艺卡Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configProductionTemplate")
public class ConfigProductionTemplateController extends BaseController
{
    private String prefix = "config/configProductionTemplate";

    @Autowired
    private IConfigProductionTemplateService configProductionTemplateService;

    @RequiresPermissions("config:configProductionTemplate:view")
    @GetMapping()
    public String configProductionTemplate()
    {
        return prefix + "/configProductionTemplate";
    }

    /**
     * 查询生产工艺卡列表
     */
    @RequiresPermissions("config:configProductionTemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigProductionTemplate configProductionTemplate)
    {
        startPage();
        List<ConfigProductionTemplate> list = configProductionTemplateService.selectConfigProductionTemplateList(configProductionTemplate);
        return getDataTable(list);
    }

    /**
     * 导出生产工艺卡列表
     */
    @RequiresPermissions("config:configProductionTemplate:export")
    @Log(title = "生产工艺卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigProductionTemplate configProductionTemplate)
    {
        List<ConfigProductionTemplate> list = configProductionTemplateService.selectConfigProductionTemplateList(configProductionTemplate);
        ExcelUtil<ConfigProductionTemplate> util = new ExcelUtil<ConfigProductionTemplate>(ConfigProductionTemplate.class);
        return util.exportExcel(list, "configProductionTemplate");
    }

    /**
     * 新增生产工艺卡
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生产工艺卡
     */
    @RequiresPermissions("config:configProductionTemplate:add")
    @Log(title = "生产工艺卡", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigProductionTemplate configProductionTemplate)
    {
        return toAjax(configProductionTemplateService.insertConfigProductionTemplate(configProductionTemplate));
    }

    @RequiresPermissions("config:configProductionTemplate:add")
    @Log(title = "生产工艺卡", businessType = BusinessType.INSERT)
    @PostMapping("/add2")
    @ResponseBody
    public AjaxResult addSave2(ConfigProductionTemplate configProductionTemplate)
    {
        configProductionTemplateService.insertConfigProductionTemplate(configProductionTemplate);
        return AjaxResult.success("创建成功",configProductionTemplate);
    }

    /**
     * 修改生产工艺卡
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigProductionTemplate configProductionTemplate = configProductionTemplateService.selectConfigProductionTemplateById(id);
        mmap.put("configProductionTemplate", configProductionTemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产工艺卡
     */
    @RequiresPermissions("config:configProductionTemplate:edit")
    @Log(title = "生产工艺卡", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigProductionTemplate configProductionTemplate)
    {
        return toAjax(configProductionTemplateService.updateConfigProductionTemplate(configProductionTemplate));
    }

    /**
     * 删除生产工艺卡
     */
    @RequiresPermissions("config:configProductionTemplate:remove")
    @Log(title = "生产工艺卡", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configProductionTemplateService.deleteConfigProductionTemplateByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "报价工艺卡", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configProductionTemplate:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigProductionTemplateProcess> util1 = new ExcelUtil<ConfigProductionTemplateProcess>(ConfigProductionTemplateProcess.class);
        ExcelUtil<ConfigProductionTemplateMaterials> util2 = new ExcelUtil<ConfigProductionTemplateMaterials>(ConfigProductionTemplateMaterials.class);
        List<ConfigProductionTemplateProcess> processList = util1.importExcel(file.getInputStream(),0);
        List<ConfigProductionTemplateMaterials> materialsList = util2.importExcel(file.getInputStream(),1);
        String message = configProductionTemplateService.importConfigProductionTemplate(processList, materialsList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 批量加工序
     * @Author 方舟
     * @Date 2021/8/22 21:53:34
     **/
    @PostMapping("/addProcessBatch")
    @ResponseBody
    public AjaxResult addProcessBatch(ConfigProductionTemplate configProductionTemplate) throws Exception
    {
        ConfigProductionTemplate result = configProductionTemplateService.addProcessBatch(configProductionTemplate);
        return AjaxResult.success(result);
    }
}
