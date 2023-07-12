package com.ruoyi.project.config.configQuotationTemplate.controller;

import java.util.List;

import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
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
import com.ruoyi.project.config.configQuotationTemplate.domain.ConfigQuotationTemplate;
import com.ruoyi.project.config.configQuotationTemplate.service.IConfigQuotationTemplateService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报价工艺卡Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configQuotationTemplate")
public class ConfigQuotationTemplateController extends BaseController
{
    private String prefix = "config/configQuotationTemplate";

    @Autowired
    private IConfigQuotationTemplateService configQuotationTemplateService;

    @RequiresPermissions("config:configQuotationTemplate:view")
    @GetMapping()
    public String configQuotationTemplate()
    {
        return prefix + "/configQuotationTemplate";
    }

    /**
     * 查询报价工艺卡列表
     */
    @RequiresPermissions("config:configQuotationTemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigQuotationTemplate configQuotationTemplate)
    {
        startPage();
        List<ConfigQuotationTemplate> list = configQuotationTemplateService.selectConfigQuotationTemplateList(configQuotationTemplate);
        return getDataTable(list);
    }

    /**
     * 导出报价工艺卡列表
     */
    @RequiresPermissions("config:configQuotationTemplate:export")
    @Log(title = "报价工艺卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigQuotationTemplate configQuotationTemplate)
    {
        List<ConfigQuotationTemplate> list = configQuotationTemplateService.selectConfigQuotationTemplateList(configQuotationTemplate);
        ExcelUtil<ConfigQuotationTemplate> util = new ExcelUtil<ConfigQuotationTemplate>(ConfigQuotationTemplate.class);
        return util.exportExcel(list, "configQuotationTemplate");
    }

    /**
     * 新增报价工艺卡
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存报价工艺卡
     */
    @RequiresPermissions("config:configQuotationTemplate:add")
    @Log(title = "报价工艺卡", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigQuotationTemplate configQuotationTemplate)
    {
        return toAjax(configQuotationTemplateService.insertConfigQuotationTemplate(configQuotationTemplate));
    }

    @RequiresPermissions("config:configQuotationTemplate:add")
    @Log(title = "报价工艺卡", businessType = BusinessType.INSERT)
    @PostMapping("/add2")
    @ResponseBody
    public AjaxResult addSave2(ConfigQuotationTemplate configQuotationTemplate)
    {
        configQuotationTemplateService.insertConfigQuotationTemplate(configQuotationTemplate);
        return AjaxResult.success("创建成功",configQuotationTemplate);
    }

    /**
     * 修改报价工艺卡
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigQuotationTemplate configQuotationTemplate = configQuotationTemplateService.selectConfigQuotationTemplateById(id);
        mmap.put("configQuotationTemplate", configQuotationTemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存报价工艺卡
     */
    @RequiresPermissions("config:configQuotationTemplate:edit")
    @Log(title = "报价工艺卡", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigQuotationTemplate configQuotationTemplate)
    {
        return toAjax(configQuotationTemplateService.updateConfigQuotationTemplate(configQuotationTemplate));
    }

    /**
     * 删除报价工艺卡
     */
    @RequiresPermissions("config:configQuotationTemplate:remove")
    @Log(title = "报价工艺卡", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configQuotationTemplateService.deleteConfigQuotationTemplateByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "报价工艺卡", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configQuotationTemplate:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigQuotationTemplateProcess> util1 = new ExcelUtil<ConfigQuotationTemplateProcess>(ConfigQuotationTemplateProcess.class);
        ExcelUtil<ConfigQuotationTemplateMaterials> util2 = new ExcelUtil<ConfigQuotationTemplateMaterials>(ConfigQuotationTemplateMaterials.class);
        List<ConfigQuotationTemplateProcess> processList = util1.importExcel(file.getInputStream(),0);
        List<ConfigQuotationTemplateMaterials> materialsList = util2.importExcel(file.getInputStream(),1);
        String message = configQuotationTemplateService.importConfigQuotationTemplate(processList, materialsList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 批量加工序
     * @Author 方舟
     * @Date 2021/8/22 21:53:34
    **/
    @PostMapping("/addProcessBatch")
    @ResponseBody
    public AjaxResult addProcessBatch(ConfigQuotationTemplate configQuotationTemplate) throws Exception
    {
        ConfigQuotationTemplate result = configQuotationTemplateService.addProcessBatch(configQuotationTemplate);
        return AjaxResult.success(result);
    }

    /**
     * 弹出工艺卡
     * @Author 方舟
     * @Date 2021/4/21 10:03:59
     **/
    @GetMapping("/selectQuotationTemplate")
    public String selectQuotationTemplate()
    {
        return prefix + "/selectQuotationTemplate";
    }
}
