package com.ruoyi.project.outsource.outsourceReturn.controller;

import java.util.List;

import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDelivery;
import com.ruoyi.project.outsource.outsourceReturn.domain.OutsourceReturnExport;
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
import com.ruoyi.project.outsource.outsourceReturn.domain.OutsourceReturn;
import com.ruoyi.project.outsource.outsourceReturn.service.IOutsourceReturnService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 外发退货Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/outsource/outsourceReturn")
public class OutsourceReturnController extends BaseController
{
    private String prefix = "outsource/outsourceReturn";

    @Autowired
    private IOutsourceReturnService outsourceReturnService;

    @RequiresPermissions("outsource:outsourceReturn:view")
    @GetMapping()
    public String outsourceReturn()
    {
        return prefix + "/outsourceReturn";
    }

    /**
     * 查询外发退货列表
     */
    @RequiresPermissions("outsource:outsourceReturn:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutsourceReturn outsourceReturn)
    {
        startPage();
        List<OutsourceReturn> list = outsourceReturnService.selectOutsourceReturnList(outsourceReturn);
        return getDataTable(list);
    }

    /**
     * 导出外发退货列表
     */
    @RequiresPermissions("outsource:outsourceReturn:export")
    @Log(title = "外发退货", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutsourceReturn outsourceReturn)
    {
        List<OutsourceReturnExport> list = outsourceReturnService.selectOutsourceReturnExportList(outsourceReturn);
        ExcelUtil<OutsourceReturnExport> util = new ExcelUtil<OutsourceReturnExport>(OutsourceReturnExport.class);
        return util.exportExcel(list, "外发退货");
    }

    /**
     * 新增外发退货
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存外发退货
     */
    @RequiresPermissions("outsource:outsourceReturn:add")
    @Log(title = "外发退货", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OutsourceReturn outsourceReturn)
    {
        return toAjax(outsourceReturnService.insertOutsourceReturn(outsourceReturn));
    }

    /**
     * 修改外发退货
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceReturn outsourceReturn = outsourceReturnService.selectOutsourceReturnById(id);
        mmap.put("outsourceReturn", outsourceReturn);
        return prefix + "/outsourceReturnDetail";
    }

    /**
     * 修改保存外发退货
     */
    @RequiresPermissions("outsource:outsourceReturn:edit")
    @Log(title = "外发退货", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OutsourceReturn outsourceReturn)
    {
        return toAjax(outsourceReturnService.updateOutsourceReturn(outsourceReturn));
    }

    /**
     * 删除外发退货
     */
    @RequiresPermissions("outsource:outsourceReturn:remove")
    @Log(title = "外发退货", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(outsourceReturnService.deleteOutsourceReturnByIds(ids));
    }

    /**
     * 创建外发到货
     */
    @RequiresPermissions("outsource:outsourceReturn:add")
    @Log(title = "创建外发到货", businessType = BusinessType.INSERT)
    @PostMapping("/createOutsourceReturn")
    @ResponseBody
    public AjaxResult createOutsourceReturn(OutsourceReturn outsourceReturn){
        return AjaxResult.success(outsourceReturnService.createOutsourceReturn(outsourceReturn));
    }

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @PostMapping("/countAmount")
    @ResponseBody
    public AjaxResult countAmount(OutsourceReturn outsourceReturn) {
        OutsourceReturn result = outsourceReturnService.countAmount(outsourceReturn);
        return AjaxResult.success("计算成功", result);
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceReturn outsourceReturn = new OutsourceReturn();
        outsourceReturn = outsourceReturnService.selectOutsourceReturnById(id);
        mmap.put("outsourceReturn", outsourceReturn);
        return prefix + "/print";
    }
}
