package com.ruoyi.project.produce.produceOrderProcess.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceOrder.service.IProduceOrderService;
import com.ruoyi.project.sale.saleOrderDelivery.domain.SaleOrderDelivery;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleQuotationProcess.domain.SaleQuotationProcess;
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
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.service.IProduceOrderProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 施工工序Controller
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Controller
@RequestMapping("/produce/produceOrderProcess")
public class ProduceOrderProcessController extends BaseController
{
    private String prefix = "produce/produceOrderProcess";

    @Autowired
    private IProduceOrderProcessService produceOrderProcessService;

    @Autowired
    private IProduceOrderService produceOrderService;

    @RequiresPermissions("produce:produceOrderProcess:view")
    @GetMapping()
    public String produceOrderProcess()
    {
        return prefix + "/produceOrderProcess";
    }

    /**
     * 查询施工工序列表
     */
    @RequiresPermissions("produce:produceOrderProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProduceOrderProcess produceOrderProcess)
    {
        startPage();
        List<ProduceOrderProcess> list = produceOrderProcessService.selectProduceOrderProcessList(produceOrderProcess);
        return getDataTable(list);
    }

    @PostMapping("/selectlist")
    @ResponseBody
    public AjaxResult selectlist(ProduceOrderProcess produceOrderProcess)
    {
        List<ProduceOrderProcess> list = produceOrderProcessService.selectProduceOrderProcessList(produceOrderProcess);
        return AjaxResult.success(list);
    }

    /**
     * 导出施工工序列表
     */
    @RequiresPermissions("produce:produceOrderProcess:export")
    @Log(title = "施工工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProduceOrderProcess produceOrderProcess)
    {
        List<ProduceOrderProcess> list = produceOrderProcessService.selectProduceOrderProcessList(produceOrderProcess);
        ExcelUtil<ProduceOrderProcess> util = new ExcelUtil<ProduceOrderProcess>(ProduceOrderProcess.class);
        return util.exportExcel(list, "produceOrderProcess");
    }

    /**
     * 新增施工工序
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrder ProduceOrder = produceOrderService.selectProduceOrderById(id);
        ProduceOrderProcess produceOrderProcess = new ProduceOrderProcess();
        produceOrderProcess.setProduceOrderId(id);
        List<ProduceOrderProcess> list = produceOrderProcessService.selectProduceOrderProcessList(produceOrderProcess);
        BeanUtils.copyProperties(ProduceOrder, produceOrderProcess);
        produceOrderProcess.setId(-1L);
        produceOrderProcess.setProduceOrderId(id);
        produceOrderProcess.setProcessOrder(list.size()+1);
        produceOrderProcess.setIsIncount("Y");
        produceOrderProcess.setIsPrint("Y");
        produceOrderProcess.setIsOutsource("N");
        produceOrderProcess.setIsWithMaterials("N");
        produceOrderProcess.setChargeAmount(new BigDecimal(0));
        mmap.put("produceOrderProcess", produceOrderProcess);
        return prefix + "/edit";
    }

    /**
     * 新增保存施工工序
     */
    @RequiresPermissions("produce:produceOrderProcess:add")
    @Log(title = "施工工序", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProduceOrderProcess produceOrderProcess)
    {
        return toAjax(produceOrderProcessService.insertProduceOrderProcess(produceOrderProcess));
    }

    /**
     * 修改施工工序
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrderProcess produceOrderProcess = produceOrderProcessService.selectProduceOrderProcessById(id);
        mmap.put("produceOrderProcess", produceOrderProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存施工工序
     */
    @RequiresPermissions("produce:produceOrderProcess:edit")
    @Log(title = "施工工序", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProduceOrderProcess produceOrderProcess)
    {
        return toAjax(produceOrderProcessService.updateProduceOrderProcess(produceOrderProcess));
    }

    /**
     * 删除施工工序
     */
    @RequiresPermissions("produce:produceOrderProcess:remove")
    @Log(title = "施工工序", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(produceOrderProcessService.deleteProduceOrderProcessByIds(ids));
    }

    /**
     * 更新顺序
     * @Author 方舟
     * @Date 2021/4/18 18:43:35
     **/
    @PostMapping( "/saveProcessOrder")
    @ResponseBody
    public AjaxResult saveProcessOrder(ProduceOrderProcess produceOrderProcess)
    {
        int result = 0;
        String[] arr = produceOrderProcess.getRzyValue1().split(",");
        for (int i=0;i<arr.length;i++){
            String[] arr2 = arr[i].split("_");
            ProduceOrderProcess cobj = new ProduceOrderProcess();
            cobj.setId(Long.parseLong(arr2[0]));
            cobj.setProcessOrder(Integer.parseInt(arr2[1]));
            result = produceOrderProcessService.updateProduceOrderProcess(cobj);
        }
        return toAjax(result);
    }

    /**
     * 选择工序
     * @Author 方舟
     * @Date 2021/5/14 15:18:00
    **/
    @GetMapping("/selectProduceOrderProcess")
    public String selectProduceOrderProcess()
    {
        return prefix + "/selectProduceOrderProcess";
    }

    /**
     * 选择工序
     * @Author 方舟
     * @Date 2021/5/14 15:18:00
    **/
    @GetMapping("/selectProduceOrderProcessOS/{supplierId}")
    public String selectProduceOrderProcessOS(@PathVariable("supplierId") Long supplierId, ModelMap mmap)
    {
        ProduceOrderProcess produceOrderProcess = new ProduceOrderProcess();
        produceOrderProcess.setSupplierTempId(supplierId);
        mmap.put("produceOrderProcess", produceOrderProcess);
        return prefix + "/selectProduceOrderProcessOS";
    }



}
