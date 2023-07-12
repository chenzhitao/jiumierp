package com.ruoyi.project.outsource.outsourceChecking.controller;

import java.util.List;

import com.ruoyi.project.outsource.outsourceChecking.domain.OutsourceChecking;
import com.ruoyi.project.outsource.outsourceReturn.domain.OutsourceReturn;
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
import com.ruoyi.project.outsource.outsourceChecking.service.IOutsourceCheckingService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 外发对账Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/outsource/outsourceChecking")
public class OutsourceCheckingController extends BaseController
{
    private String prefix = "outsource/outsourceChecking";

    @Autowired
    private IOutsourceCheckingService outsourceCheckingService;

    @RequiresPermissions("outsource:outsourceChecking:view")
    @GetMapping()
    public String outsourceChecking()
    {
        return prefix + "/outsourceChecking";
    }

    /**
     * 查询外发对账列表
     */
    @RequiresPermissions("outsource:outsourceChecking:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutsourceChecking outsourceChecking)
    {
        startPage();
        List<OutsourceChecking> list = outsourceCheckingService.selectOutsourceCheckingList(outsourceChecking);
        return getDataTable(list);
    }

    /**
     * 导出外发对账列表
     */
    @RequiresPermissions("outsource:outsourceChecking:export")
    @Log(title = "外发对账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutsourceChecking outsourceChecking)
    {
        List<OutsourceChecking> list = outsourceCheckingService.selectOutsourceCheckingList(outsourceChecking);
        ExcelUtil<OutsourceChecking> util = new ExcelUtil<OutsourceChecking>(OutsourceChecking.class);
        return util.exportExcel(list, "外发对账");
    }

    /**
     * 新增外发对账
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存外发对账
     */
    @RequiresPermissions("outsource:outsourceChecking:add")
    @Log(title = "外发对账", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OutsourceChecking outsourceChecking)
    {
        return toAjax(outsourceCheckingService.insertOutsourceChecking(outsourceChecking));
    }

    /**
     * 修改外发对账
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceChecking outsourceChecking = outsourceCheckingService.selectOutsourceCheckingById(id);
        mmap.put("outsourceChecking", outsourceChecking);
        return prefix + "/outsourceCheckingDetail";
    }

    /**
     * 修改保存外发对账
     */
    @RequiresPermissions("outsource:outsourceChecking:edit")
    @Log(title = "外发对账", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OutsourceChecking outsourceChecking)
    {
        return toAjax(outsourceCheckingService.updateOutsourceChecking(outsourceChecking));
    }

    /**
     * 删除外发对账
     */
    @RequiresPermissions("outsource:outsourceChecking:remove")
    @Log(title = "外发对账", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(outsourceCheckingService.deleteOutsourceCheckingByIds(ids));
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("outsource:outsourceChecking:add")
    @Log(title = "创建外发对账", businessType = BusinessType.INSERT)
    @PostMapping("/createOutsourceChecking")
    @ResponseBody
    public AjaxResult createOutsourceChecking(OutsourceChecking outsourceChecking)
    {
        OutsourceChecking result = outsourceCheckingService.createOutsourceChecking(outsourceChecking);
        return AjaxResult.success("创建外发对账成功",result);
    }

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @PostMapping("/calculateAmount")
    @ResponseBody
    public AjaxResult calculateAmount(OutsourceChecking outsourceChecking)
    {
        return AjaxResult.success(outsourceCheckingService.calculateAmount(outsourceChecking));
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        OutsourceChecking outsourceChecking = new OutsourceChecking();
        outsourceChecking = outsourceCheckingService.selectOutsourceCheckingById(id);
        mmap.put("outsourceChecking", outsourceChecking);
        return prefix + "/print";
    }
}
