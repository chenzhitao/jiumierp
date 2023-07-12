package com.ruoyi.project.config.configBoard.controller;

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
import com.ruoyi.project.config.configBoard.domain.ConfigBoard;
import com.ruoyi.project.config.configBoard.service.IConfigBoardService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 板材配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configBoard")
public class ConfigBoardController extends BaseController
{
    private String prefix = "config/configBoard";

    @Autowired
    private IConfigBoardService configBoardService;

    @RequiresPermissions("config:configBoard:view")
    @GetMapping()
    public String configBoard()
    {
        return prefix + "/configBoard";
    }

    /**
     * 查询板材配置列表
     */
    @RequiresPermissions("config:configBoard:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigBoard configBoard)
    {
        startPage();
        List<ConfigBoard> list = configBoardService.selectConfigBoardList(configBoard);
        return getDataTable(list);
    }

    /**
     * 导出板材配置列表
     */
    @RequiresPermissions("config:configBoard:export")
    @Log(title = "板材配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigBoard configBoard)
    {
        List<ConfigBoard> list = configBoardService.selectConfigBoardList(configBoard);
        ExcelUtil<ConfigBoard> util = new ExcelUtil<ConfigBoard>(ConfigBoard.class);
        return util.exportExcel(list, "板材配置");
    }

    /**
     * 新增板材配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存板材配置
     */
    @RequiresPermissions("config:configBoard:add")
    @Log(title = "板材配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigBoard configBoard)
    {
        return toAjax(configBoardService.insertConfigBoard(configBoard));
    }

    /**
     * 修改板材配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigBoard configBoard = configBoardService.selectConfigBoardById(id);
        mmap.put("configBoard", configBoard);
        return prefix + "/edit";
    }

    /**
     * 修改保存板材配置
     */
    @RequiresPermissions("config:configBoard:edit")
    @Log(title = "板材配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigBoard configBoard)
    {
        return toAjax(configBoardService.updateConfigBoard(configBoard));
    }

    /**
     * 删除板材配置
     */
    @RequiresPermissions("config:configBoard:remove")
    @Log(title = "板材配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configBoardService.deleteConfigBoardByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "板材配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configBoard:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigBoard> util = new ExcelUtil<ConfigBoard>(ConfigBoard.class);
        List<ConfigBoard> configBoardList = util.importExcel(file.getInputStream());
        String message = configBoardService.importConfigBoard(configBoardList, updateSupport);
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
        ExcelUtil<ConfigBoard> util = new ExcelUtil<ConfigBoard>(ConfigBoard.class);
        return util.importTemplateExcel("板材配置");
    }
}
