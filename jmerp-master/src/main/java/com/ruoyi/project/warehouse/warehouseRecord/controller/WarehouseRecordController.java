package com.ruoyi.project.warehouse.warehouseRecord.controller;

import java.util.List;
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
import com.ruoyi.project.warehouse.warehouseRecord.domain.WarehouseRecord;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 出入库记录Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/warehouse/warehouseRecord")
public class WarehouseRecordController extends BaseController
{
    private String prefix = "warehouse/warehouseRecord";

    @Autowired
    private IWarehouseRecordService warehouseRecordService;

    @RequiresPermissions("warehouse:warehouseRecord:view")
    @GetMapping()
    public String warehouseRecord()
    {
        return prefix + "/warehouseRecord";
    }

    /**
     * 查询出入库记录列表
     */
    @RequiresPermissions("warehouse:warehouseRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WarehouseRecord warehouseRecord)
    {
        startPage();
        List<WarehouseRecord> list = warehouseRecordService.selectWarehouseRecordList(warehouseRecord);
        return getDataTable(list);
    }

    /**
     * 导出出入库记录列表
     */
    @RequiresPermissions("warehouse:warehouseRecord:export")
    @Log(title = "出入库记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WarehouseRecord warehouseRecord)
    {
        List<WarehouseRecord> list = warehouseRecordService.selectWarehouseRecordList(warehouseRecord);
        ExcelUtil<WarehouseRecord> util = new ExcelUtil<WarehouseRecord>(WarehouseRecord.class);
        return util.exportExcel(list, "出入库记录");
    }

    /**
     * 新增出入库记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存出入库记录
     */
    @RequiresPermissions("warehouse:warehouseRecord:add")
    @Log(title = "出入库记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WarehouseRecord warehouseRecord)
    {
        return toAjax(warehouseRecordService.insertWarehouseRecord(warehouseRecord));
    }

    /**
     * 修改出入库记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WarehouseRecord warehouseRecord = warehouseRecordService.selectWarehouseRecordById(id);
        mmap.put("warehouseRecord", warehouseRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存出入库记录
     */
    @RequiresPermissions("warehouse:warehouseRecord:edit")
    @Log(title = "出入库记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WarehouseRecord warehouseRecord)
    {
        return toAjax(warehouseRecordService.updateWarehouseRecord(warehouseRecord));
    }

    /**
     * 删除出入库记录
     */
    @RequiresPermissions("warehouse:warehouseRecord:remove")
    @Log(title = "出入库记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(warehouseRecordService.deleteWarehouseRecordByIds(ids));
    }
}
