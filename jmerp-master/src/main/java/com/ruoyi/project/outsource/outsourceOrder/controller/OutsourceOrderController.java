package com.ruoyi.project.outsource.outsourceOrder.controller;

import java.util.List;

import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.config.configSupplierContact.service.IConfigSupplierContactService;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrderExport;
import com.ruoyi.project.purchase.purchaseChecking.domain.PurchaseChecking;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
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
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrder;
import com.ruoyi.project.outsource.outsourceOrder.service.IOutsourceOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 外发加工Controller
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Controller
@RequestMapping("/outsource/outsourceOrder")
public class OutsourceOrderController extends BaseController {
    private String prefix = "outsource/outsourceOrder";

    @Autowired
    private IOutsourceOrderService outsourceOrderService;
    @Autowired
    private IConfigSupplierContactService configSupplierContactService;

    @RequiresPermissions("outsource:outsourceOrder:view")
    @GetMapping()
    public String outsourceOrder() {
        return prefix + "/outsourceOrder";
    }

    /**
     * 查询外发加工列表
     */
    @RequiresPermissions("outsource:outsourceOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OutsourceOrder outsourceOrder) {
        startPage();
        List<OutsourceOrder> list = outsourceOrderService.selectOutsourceOrderList(outsourceOrder);
        return getDataTable(list);
    }

    /**
     * 导出外发加工列表
     */
    @RequiresPermissions("outsource:outsourceOrder:export")
    @Log(title = "外发加工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OutsourceOrder outsourceOrder) {
        List<OutsourceOrderExport> list = outsourceOrderService.selectOutsourceOrderExportList(outsourceOrder);
        ExcelUtil<OutsourceOrderExport> util = new ExcelUtil<OutsourceOrderExport>(OutsourceOrderExport.class);
        return util.exportExcel(list, "外发加工");
    }

    /**
     * 新增外发加工
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存外发加工
     */
    @RequiresPermissions("outsource:outsourceOrder:add")
    @Log(title = "外发加工", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OutsourceOrder outsourceOrder) {
        return toAjax(outsourceOrderService.insertOutsourceOrder(outsourceOrder));
    }

    /**
     * 修改外发加工
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        OutsourceOrder outsourceOrder = outsourceOrderService.selectOutsourceOrderById(id);
        mmap.put("outsourceOrder", outsourceOrder);
        return prefix + "/outsourceOrderDetail";
    }

    /**
     * 修改保存外发加工
     */
    @RequiresPermissions("outsource:outsourceOrder:edit")
    @Log(title = "外发加工", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OutsourceOrder outsourceOrder) {
        return toAjax(outsourceOrderService.updateOutsourceOrder(outsourceOrder));
    }

    /**
     * 删除外发加工
     */
    @RequiresPermissions("outsource:outsourceOrder:remove")
    @Log(title = "外发加工", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(outsourceOrderService.deleteOutsourceOrderByIds(ids));
    }

    /**
     * 创建外发加工
     */
    @RequiresPermissions("outsource:outsourceOrder:add")
    @Log(title = "创建外发加工", businessType = BusinessType.INSERT)
    @PostMapping("/createOutsource")
    @ResponseBody
    public AjaxResult createOutsource(OutsourceOrder outsourceOrder) {
        return AjaxResult.success(outsourceOrderService.createOutsource(outsourceOrder));
    }

    /**
     * 获取默认联系人
     *
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @PostMapping("/getDefaultContact")
    @ResponseBody
    public AjaxResult getDefaultContact(ConfigSupplierContact configSupplierContact) {
        return AjaxResult.success(configSupplierContactService.getDefaultContact(configSupplierContact));
    }

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @PostMapping("/countAmount")
    @ResponseBody
    public AjaxResult countAmount(OutsourceOrder outsourceOrder) {
        OutsourceOrder result = outsourceOrderService.countAmount(outsourceOrder);
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
        OutsourceOrder outsourceOrder = new OutsourceOrder();
        outsourceOrder = outsourceOrderService.selectOutsourceOrderById(id);
        mmap.put("outsourceOrder", outsourceOrder);
        return prefix + "/print";
    }
}