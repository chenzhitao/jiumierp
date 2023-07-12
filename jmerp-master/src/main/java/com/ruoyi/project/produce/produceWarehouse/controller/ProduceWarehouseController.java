package com.ruoyi.project.produce.produceWarehouse.controller;

import java.util.List;

import com.ruoyi.project.produce.produceReport.domain.ProduceReport;
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
import com.ruoyi.project.produce.produceWarehouse.domain.ProduceWarehouse;
import com.ruoyi.project.produce.produceWarehouse.service.IProduceWarehouseService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 生产入库Controller
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Controller
@RequestMapping("/produce/produceWarehouse")
public class ProduceWarehouseController extends BaseController
{
    private String prefix = "produce/produceWarehouse";

    @Autowired
    private IProduceWarehouseService produceWarehouseService;

    @RequiresPermissions("produce:produceWarehouse:view")
    @GetMapping()
    public String produceWarehouse()
    {
        return prefix + "/produceWarehouse";
    }

    /**
     * 查询生产入库列表
     */
    @RequiresPermissions("produce:produceWarehouse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProduceWarehouse produceWarehouse)
    {
        startPage();
        List<ProduceWarehouse> list = produceWarehouseService.selectProduceWarehouseList(produceWarehouse);
        return getDataTable(list);
    }

    /**
     * 导出生产入库列表
     */
    @RequiresPermissions("produce:produceWarehouse:export")
    @Log(title = "生产入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProduceWarehouse produceWarehouse)
    {
        List<ProduceWarehouse> list = produceWarehouseService.selectProduceWarehouseList(produceWarehouse);
        ExcelUtil<ProduceWarehouse> util = new ExcelUtil<ProduceWarehouse>(ProduceWarehouse.class);
        return util.exportExcel(list, "produceWarehouse");
    }

    /**
     * 新增生产入库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生产入库
     */
    @RequiresPermissions("produce:produceWarehouse:add")
    @Log(title = "生产入库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProduceWarehouse produceWarehouse)
    {
        return toAjax(produceWarehouseService.insertProduceWarehouse(produceWarehouse));
    }

    /**
     * 修改生产入库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceWarehouse produceWarehouse = produceWarehouseService.selectProduceWarehouseById(id);
        mmap.put("produceWarehouse", produceWarehouse);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产入库
     */
    @RequiresPermissions("produce:produceWarehouse:edit")
    @Log(title = "生产入库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProduceWarehouse produceWarehouse)
    {
        return toAjax(produceWarehouseService.updateProduceWarehouse(produceWarehouse));
    }

    /**
     * 删除生产入库
     */
    @RequiresPermissions("produce:produceWarehouse:remove")
    @Log(title = "生产入库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(produceWarehouseService.deleteProduceWarehouseByIds(ids));
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceWarehouse produceWarehouse = new ProduceWarehouse();
        produceWarehouse = produceWarehouseService.selectProduceWarehouseById(id);
        mmap.put("produceWarehouse", produceWarehouse);
        return prefix + "/print";
    }
}
