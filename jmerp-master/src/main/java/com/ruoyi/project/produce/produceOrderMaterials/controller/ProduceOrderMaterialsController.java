package com.ruoyi.project.produce.produceOrderMaterials.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceOrder.service.IProduceOrderService;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
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
import com.ruoyi.project.produce.produceOrderMaterials.domain.ProduceOrderMaterials;
import com.ruoyi.project.produce.produceOrderMaterials.service.IProduceOrderMaterialsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 施工材料Controller
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Controller
@RequestMapping("/produce/produceOrderMaterials")
public class ProduceOrderMaterialsController extends BaseController
{
    private String prefix = "produce/produceOrderMaterials";

    @Autowired
    private IProduceOrderMaterialsService produceOrderMaterialsService;

    @Autowired
    private IProduceOrderService produceOrderService;

    @RequiresPermissions("produce:produceOrderMaterials:view")
    @GetMapping()
    public String produceOrderMaterials()
    {
        return prefix + "/produceOrderMaterials";
    }

    /**
     * 查询施工材料列表
     */
    @RequiresPermissions("produce:produceOrderMaterials:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProduceOrderMaterials produceOrderMaterials)
    {
        startPage();
        List<ProduceOrderMaterials> list = produceOrderMaterialsService.selectProduceOrderMaterialsList(produceOrderMaterials);
        return getDataTable(list);
    }
    @PostMapping("/selectList")
    @ResponseBody
    public AjaxResult selectList(ProduceOrderMaterials produceOrderMaterials)
    {
        List<ProduceOrderMaterials> list = produceOrderMaterialsService.selectProduceOrderMaterialsList(produceOrderMaterials);
        return AjaxResult.success(list);
    }

    /**
     * 导出施工材料列表
     */
    @RequiresPermissions("produce:produceOrderMaterials:export")
    @Log(title = "施工材料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProduceOrderMaterials produceOrderMaterials)
    {
        List<ProduceOrderMaterials> list = produceOrderMaterialsService.selectProduceOrderMaterialsList(produceOrderMaterials);
        ExcelUtil<ProduceOrderMaterials> util = new ExcelUtil<ProduceOrderMaterials>(ProduceOrderMaterials.class);
        return util.exportExcel(list, "produceOrderMaterials");
    }

    /**
     * 新增施工材料
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrder ProduceOrder = produceOrderService.selectProduceOrderById(id);
        ProduceOrderMaterials produceOrderMaterials = new ProduceOrderMaterials();
        BeanUtils.copyProperties(ProduceOrder, produceOrderMaterials);
        produceOrderMaterials.setId(-1L);
        produceOrderMaterials.setProduceOrderId(id);
        produceOrderMaterials.setIsGetProcessQty("Y");
        produceOrderMaterials.setProcessSizeLong(0);
        produceOrderMaterials.setProcessSizeWidth(0);
        produceOrderMaterials.setSizeLong(0);
        produceOrderMaterials.setSizeWidth(0);
        produceOrderMaterials.setSizeLongIn(0F);
        produceOrderMaterials.setSizeLongIn(0F);
        produceOrderMaterials.setLongSystem("metric");
        produceOrderMaterials.setQty(0);
        produceOrderMaterials.setMaterialsId(-1L);
        produceOrderMaterials.setBaseRate(new BigDecimal(1));
        produceOrderMaterials.setLossRate(new BigDecimal(0));
        produceOrderMaterials.setLossQty(0);
        mmap.put("produceOrderMaterials", produceOrderMaterials);
        return prefix + "/edit";
    }

    /**
     * 新增保存施工材料
     */
    @RequiresPermissions("produce:produceOrderMaterials:add")
    @Log(title = "施工材料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProduceOrderMaterials produceOrderMaterials)
    {
        return toAjax(produceOrderMaterialsService.insertProduceOrderMaterials(produceOrderMaterials));
    }

    /**
     * 修改施工材料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrderMaterials produceOrderMaterials = produceOrderMaterialsService.selectProduceOrderMaterialsById(id);
        mmap.put("produceOrderMaterials", produceOrderMaterials);
        return prefix + "/edit";
    }

    /**
     * 修改保存施工材料
     */
    @RequiresPermissions("produce:produceOrderMaterials:edit")
    @Log(title = "施工材料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProduceOrderMaterials produceOrderMaterials)
    {
        return toAjax(produceOrderMaterialsService.updateProduceOrderMaterials(produceOrderMaterials));
    }

    /**
     * 删除施工材料
     */
    @RequiresPermissions("produce:produceOrderMaterials:remove")
    @Log(title = "施工材料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(produceOrderMaterialsService.deleteProduceOrderMaterialsByIds(ids));
    }

    /**
     * 选择
     */
    @GetMapping("/selectProduceOrderMaterials")
    public String selectProduceOrderMaterials()
    {
        return prefix + "/selectProduceOrderMaterials";
    }

}
