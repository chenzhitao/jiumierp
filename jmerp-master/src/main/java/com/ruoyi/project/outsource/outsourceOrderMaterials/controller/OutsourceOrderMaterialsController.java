package com.ruoyi.project.outsource.outsourceOrderMaterials.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.config.configProductionTemplateMaterials.domain.ConfigProductionTemplateMaterials;
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
import com.ruoyi.project.outsource.outsourceOrderMaterials.domain.OutsourceOrderMaterials;
import com.ruoyi.project.outsource.outsourceOrderMaterials.service.IOutsourceOrderMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 外发加工带料Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/outsource/outsourceOrderMaterials")
public class OutsourceOrderMaterialsController extends BaseController
{
    private String prefix = "outsource/outsourceOrderMaterials";

    @Autowired
    private IOutsourceOrderMaterialsService outsourceOrderMaterialsService;

    @RequiresPermissions("outsource:outsourceOrderMaterials:view")
    @GetMapping()
    public String outsourceOrderMaterials()
    {
        return prefix + "/outsourceOrderMaterials";
    }

    /**
     * 查询外发加工带料列表
     */
    @RequiresPermissions("outsource:outsourceOrderMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutsourceOrderMaterials outsourceOrderMaterials)
    {
        startPage();
        List<OutsourceOrderMaterials> list = outsourceOrderMaterialsService.selectOutsourceOrderMaterialsList(outsourceOrderMaterials);
        return getDataTable(list);
    }

    /**
     * 导出外发加工带料列表
     */
    @RequiresPermissions("outsource:outsourceOrderMaterials:export")
    @Log(title = "外发加工带料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutsourceOrderMaterials outsourceOrderMaterials)
    {
        List<OutsourceOrderMaterials> list = outsourceOrderMaterialsService.selectOutsourceOrderMaterialsList(outsourceOrderMaterials);
        ExcelUtil<OutsourceOrderMaterials> util = new ExcelUtil<OutsourceOrderMaterials>(OutsourceOrderMaterials.class);
        return util.exportExcel(list, "外发加工带料数据");
    }

    /**
     * 新增外发加工带料
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceOrderMaterials outsourceOrderMaterials = new OutsourceOrderMaterials();
        outsourceOrderMaterials.setId(-1L);
        outsourceOrderMaterials.setQty(0);
        outsourceOrderMaterials.setOutsourceOrderId(id);
        outsourceOrderMaterials.setDeliveryDate(DateUtils.getNowDate());
        mmap.put("outsourceOrderMaterials", outsourceOrderMaterials);
        return prefix + "/edit";
    }

    /**
     * 新增保存外发加工带料
     */
    @RequiresPermissions("outsource:outsourceOrderMaterials:add")
    @Log(title = "外发加工带料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OutsourceOrderMaterials outsourceOrderMaterials)
    {
        return toAjax(outsourceOrderMaterialsService.insertOutsourceOrderMaterials(outsourceOrderMaterials));
    }

    /**
     * 修改外发加工带料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceOrderMaterials outsourceOrderMaterials = outsourceOrderMaterialsService.selectOutsourceOrderMaterialsById(id);
        mmap.put("outsourceOrderMaterials", outsourceOrderMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存外发加工带料
     */
    @RequiresPermissions("outsource:outsourceOrderMaterials:edit")
    @Log(title = "外发加工带料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OutsourceOrderMaterials outsourceOrderMaterials)
    {
        return toAjax(outsourceOrderMaterialsService.updateOutsourceOrderMaterials(outsourceOrderMaterials));
    }

    /**
     * 删除外发加工带料
     */
    @RequiresPermissions("outsource:outsourceOrderMaterials:remove")
    @Log(title = "外发加工带料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(outsourceOrderMaterialsService.deleteOutsourceOrderMaterialsByIds(ids));
    }
}
