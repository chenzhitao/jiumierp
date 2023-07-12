package com.ruoyi.project.config.configProcess.controller;

import java.util.List;

import com.ruoyi.project.config.configCutterDie.domain.ConfigCutterDie;
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
import com.ruoyi.project.config.configProcess.domain.ConfigProcess;
import com.ruoyi.project.config.configProcess.service.IConfigProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工序配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configProcess")
public class ConfigProcessController extends BaseController
{
    private String prefix = "config/configProcess";

    @Autowired
    private IConfigProcessService configProcessService;

    @RequiresPermissions("config:configProcess:view")
    @GetMapping()
    public String configProcess()
    {
        return prefix + "/configProcess";
    }

    /**
     * 查询工序配置列表
     */
    @RequiresPermissions("config:configProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigProcess configProcess)
    {
        startPage();
        List<ConfigProcess> list = configProcessService.selectConfigProcessList(configProcess);
        return getDataTable(list);
    }

    /**
     * 导出工序配置列表
     */
    @RequiresPermissions("config:configProcess:export")
    @Log(title = "工序配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigProcess configProcess)
    {
        List<ConfigProcess> list = configProcessService.selectConfigProcessList(configProcess);
        ExcelUtil<ConfigProcess> util = new ExcelUtil<ConfigProcess>(ConfigProcess.class);
        return util.exportExcel(list, "工序配置");
    }

    /**
     * 新增工序配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工序配置
     */
    @RequiresPermissions("config:configProcess:add")
    @Log(title = "工序配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigProcess configProcess)
    {
        return toAjax(configProcessService.insertConfigProcess(configProcess));
    }

    /**
     * 修改工序配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigProcess configProcess = configProcessService.selectConfigProcessById(id);
        mmap.put("configProcess", configProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存工序配置
     */
    @RequiresPermissions("config:configProcess:edit")
    @Log(title = "工序配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigProcess configProcess)
    {
        return toAjax(configProcessService.updateConfigProcess(configProcess));
    }

    /**
     * 删除工序配置
     */
    @RequiresPermissions("config:configProcess:remove")
    @Log(title = "工序配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configProcessService.deleteConfigProcessByIds(ids));
    }


    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "工序配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configProcess:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigProcess> util = new ExcelUtil<ConfigProcess>(ConfigProcess.class);
        List<ConfigProcess> configProcessList = util.importExcel(file.getInputStream());
        String message = configProcessService.importConfigProcess(configProcessList, updateSupport);
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
        ExcelUtil<ConfigProcess> util = new ExcelUtil<ConfigProcess>(ConfigProcess.class);
        return util.importTemplateExcel("工序配置");
    }


    /**
     * 选择工序配置
     */
    @GetMapping("/selectProcess")
    public String selectProcess()
    {
        return prefix + "/selectConfigProcess";
    }

}
