package com.ruoyi.project.config.configProductionTemplateProcess.controller;

import java.util.List;

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
import com.ruoyi.project.config.configProductionTemplateProcess.domain.ConfigProductionTemplateProcess;
import com.ruoyi.project.config.configProductionTemplateProcess.service.IConfigProductionTemplateProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 生产工艺卡工序Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configProductionTemplateProcess")
public class ConfigProductionTemplateProcessController extends BaseController
{
    private String prefix = "config/configProductionTemplateProcess";

    @Autowired
    private IConfigProductionTemplateProcessService configProductionTemplateProcessService;

    @RequiresPermissions("config:configProductionTemplateProcess:view")
    @GetMapping()
    public String configProductionTemplateProcess()
    {
        return prefix + "/configProductionTemplateProcess";
    }

    /**
     * 查询生产工艺卡工序列表
     */
    @RequiresPermissions("config:configProductionTemplateProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigProductionTemplateProcess configProductionTemplateProcess)
    {
        startPage();
        List<ConfigProductionTemplateProcess> list = configProductionTemplateProcessService.selectConfigProductionTemplateProcessList(configProductionTemplateProcess);
        return getDataTable(list);
    }

    /**
     * 导出生产工艺卡工序列表
     */
    @RequiresPermissions("config:configProductionTemplateProcess:export")
    @Log(title = "生产工艺卡工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigProductionTemplateProcess configProductionTemplateProcess)
    {
        List<ConfigProductionTemplateProcess> list = configProductionTemplateProcessService.selectConfigProductionTemplateProcessList(configProductionTemplateProcess);
        ExcelUtil<ConfigProductionTemplateProcess> util = new ExcelUtil<ConfigProductionTemplateProcess>(ConfigProductionTemplateProcess.class);
        return util.exportExcel(list, "生产工艺卡工序");
    }

    /**
     * 新增生产工艺卡工序
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigProductionTemplateProcess configProductionTemplateProcess = new ConfigProductionTemplateProcess();
        configProductionTemplateProcess.setProductionTemplateId(id);
        List<ConfigProductionTemplateProcess> list = configProductionTemplateProcessService.selectConfigProductionTemplateProcessList(configProductionTemplateProcess);
        configProductionTemplateProcess.setProcessOrder(list.size()+1);
        configProductionTemplateProcess.setIsOutsource("N");
        configProductionTemplateProcess.setIsWithMaterials("N");
        configProductionTemplateProcess.setIsTimeCount("Y");
        mmap.put("configProductionTemplateProcess", configProductionTemplateProcess);
        return prefix + "/add";
    }

    /**
     * 新增保存生产工艺卡工序
     */
    @RequiresPermissions("config:configProductionTemplateProcess:add")
    @Log(title = "生产工艺卡工序", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigProductionTemplateProcess configProductionTemplateProcess)
    {
        return toAjax(configProductionTemplateProcessService.insertConfigProductionTemplateProcess(configProductionTemplateProcess));
    }

    /**
     * 修改生产工艺卡工序
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigProductionTemplateProcess configProductionTemplateProcess = configProductionTemplateProcessService.selectConfigProductionTemplateProcessById(id);
        mmap.put("configProductionTemplateProcess", configProductionTemplateProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产工艺卡工序
     */
    @RequiresPermissions("config:configProductionTemplateProcess:edit")
    @Log(title = "生产工艺卡工序", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigProductionTemplateProcess configProductionTemplateProcess)
    {
        return toAjax(configProductionTemplateProcessService.updateConfigProductionTemplateProcess(configProductionTemplateProcess));
    }

    /**
     * 删除生产工艺卡工序
     */
    @RequiresPermissions("config:configProductionTemplateProcess:remove")
    @Log(title = "生产工艺卡工序", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configProductionTemplateProcessService.deleteConfigProductionTemplateProcessByIds(ids));
    }

    /**
     * 更新顺序
     * @Author 方舟
     * @Date 2021/4/18 18:43:35
     **/
    @PostMapping( "/saveProcessOrder")
    @ResponseBody
    public AjaxResult saveProcessOrder(ConfigProductionTemplateProcess configProductionTemplateProcess)
    {
        int result = 0;
        String[] arr = configProductionTemplateProcess.getRzyValue1().split(",");
        for (int i=0;i<arr.length;i++){
            String[] arr2 = arr[i].split("_");
            ConfigProductionTemplateProcess cobj = new ConfigProductionTemplateProcess();
            cobj.setId(Long.parseLong(arr2[0]));
            cobj.setProcessOrder(Integer.parseInt(arr2[1]));
            result = configProductionTemplateProcessService.updateConfigProductionTemplateProcess(cobj);
        }
        return toAjax(result);
    }
}
