package com.ruoyi.project.config.configQuotationTemplateProcess.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;
import com.ruoyi.project.config.configQuotationTemplateProcess.service.IConfigQuotationTemplateProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 报价工艺卡工序Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configQuotationTemplateProcess")
public class ConfigQuotationTemplateProcessController extends BaseController
{
    private String prefix = "config/configQuotationTemplateProcess";

    @Autowired
    private IConfigQuotationTemplateProcessService configQuotationTemplateProcessService;

    @RequiresPermissions("config:configQuotationTemplateProcess:view")
    @GetMapping()
    public String configQuotationTemplateProcess()
    {
        return prefix + "/configQuotationTemplateProcess";
    }

    /**
     * 查询报价工艺卡工序列表
     */
    @RequiresPermissions("config:configQuotationTemplateProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigQuotationTemplateProcess configQuotationTemplateProcess)
    {
        startPage();
        List<ConfigQuotationTemplateProcess> list = configQuotationTemplateProcessService.selectConfigQuotationTemplateProcessList(configQuotationTemplateProcess);
        return getDataTable(list);
    }

    /**
     * 导出报价工艺卡工序列表
     */
    @RequiresPermissions("config:configQuotationTemplateProcess:export")
    @Log(title = "报价工艺卡工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigQuotationTemplateProcess configQuotationTemplateProcess)
    {
        List<ConfigQuotationTemplateProcess> list = configQuotationTemplateProcessService.selectConfigQuotationTemplateProcessList(configQuotationTemplateProcess);
        ExcelUtil<ConfigQuotationTemplateProcess> util = new ExcelUtil<ConfigQuotationTemplateProcess>(ConfigQuotationTemplateProcess.class);
        return util.exportExcel(list, "报价工艺卡工序");
    }

    /**
     * 新增报价工艺卡工序
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigQuotationTemplateProcess configQuotationTemplateProcess = new ConfigQuotationTemplateProcess();
        configQuotationTemplateProcess.setQuotationTemplateId(id);
        List<ConfigQuotationTemplateProcess> list = configQuotationTemplateProcessService.selectConfigQuotationTemplateProcessList(configQuotationTemplateProcess);
        configQuotationTemplateProcess.setProcessOrder(list.size()+1);
        configQuotationTemplateProcess.setIsTimeCount("Y");
        mmap.put("configQuotationTemplateProcess", configQuotationTemplateProcess);
        return prefix + "/add";
    }

    /**
     * 新增保存报价工艺卡工序
     */
    @RequiresPermissions("config:configQuotationTemplateProcess:add")
    @Log(title = "报价工艺卡工序", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigQuotationTemplateProcess configQuotationTemplateProcess)
    {
        return toAjax(configQuotationTemplateProcessService.insertConfigQuotationTemplateProcess(configQuotationTemplateProcess));
    }

    /**
     * 修改报价工艺卡工序
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigQuotationTemplateProcess configQuotationTemplateProcess = configQuotationTemplateProcessService.selectConfigQuotationTemplateProcessById(id);
        mmap.put("configQuotationTemplateProcess", configQuotationTemplateProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存报价工艺卡工序
     */
    @RequiresPermissions("config:configQuotationTemplateProcess:edit")
    @Log(title = "报价工艺卡工序", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigQuotationTemplateProcess configQuotationTemplateProcess)
    {
        return toAjax(configQuotationTemplateProcessService.updateConfigQuotationTemplateProcess(configQuotationTemplateProcess));
    }

    /**
     * 删除报价工艺卡工序
     */
    @RequiresPermissions("config:configQuotationTemplateProcess:remove")
    @Log(title = "报价工艺卡工序", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configQuotationTemplateProcessService.deleteConfigQuotationTemplateProcessByIds(ids));
    }

    /**
     * 更新顺序
     * @Author 方舟
     * @Date 2021/4/18 18:43:35
    **/
    @PostMapping( "/saveProcessOrder")
    @ResponseBody
    public AjaxResult saveProcessOrder(ConfigQuotationTemplateProcess configQuotationTemplateProcess)
    {
        int result = 0;
        String[] arr = configQuotationTemplateProcess.getRzyValue1().split(",");
        for (int i=0;i<arr.length;i++){
            String[] arr2 = arr[i].split("_");
            ConfigQuotationTemplateProcess cobj = new ConfigQuotationTemplateProcess();
            cobj.setId(Long.parseLong(arr2[0]));
            cobj.setProcessOrder(Integer.parseInt(arr2[1]));
            result = configQuotationTemplateProcessService.updateConfigQuotationTemplateProcess(cobj);
        }
        return toAjax(result);
    }
}
