package com.ruoyi.project.sale.saleQuotationProcess.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.config.configProductionTemplateProcess.domain.ConfigProductionTemplateProcess;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
import com.ruoyi.project.sale.saleQuotationProduct.service.ISaleQuotationProductService;
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
import com.ruoyi.project.sale.saleQuotationProcess.domain.SaleQuotationProcess;
import com.ruoyi.project.sale.saleQuotationProcess.service.ISaleQuotationProcessService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 报价工序Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleQuotationProcess")
public class SaleQuotationProcessController extends BaseController
{
    private String prefix = "sale/saleQuotationProcess";

    @Autowired
    private ISaleQuotationProcessService saleQuotationProcessService;

    @Autowired
    private ISaleQuotationProductService saleQuotationProductService;

    @RequiresPermissions("sale:saleQuotationProcess:view")
    @GetMapping()
    public String saleQuotationProcess()
    {
        return prefix + "/saleQuotationProcess";
    }

    /**
     * 查询报价工序列表
     */
    @RequiresPermissions("sale:saleQuotationProcess:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleQuotationProcess saleQuotationProcess)
    {
        startPage();
        List<SaleQuotationProcess> list = saleQuotationProcessService.selectSaleQuotationProcessList(saleQuotationProcess);
        return getDataTable(list);
    }

    /**
     * 导出报价工序列表
     */
    @RequiresPermissions("sale:saleQuotationProcess:export")
    @Log(title = "报价工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleQuotationProcess saleQuotationProcess)
    {
        List<SaleQuotationProcess> list = saleQuotationProcessService.selectSaleQuotationProcessList(saleQuotationProcess);
        ExcelUtil<SaleQuotationProcess> util = new ExcelUtil<SaleQuotationProcess>(SaleQuotationProcess.class);
        return util.exportExcel(list, "saleQuotationProcess");
    }

    /**
     * 新增报价工序
     */
    @GetMapping("/add/{saleQuotationProductId}")
    public String add(@PathVariable("saleQuotationProductId") Long saleQuotationProductId, ModelMap mmap)
    {
        SaleQuotationProduct saleQuotationProduct = saleQuotationProductService.selectSaleQuotationProductById(saleQuotationProductId);
        SaleQuotationProcess saleQuotationProcess = new SaleQuotationProcess();
        BeanUtils.copyProperties(saleQuotationProduct, saleQuotationProcess);
        saleQuotationProcess.setSaleQuotationProductId(saleQuotationProductId);
        SaleQuotationProcess paramsVO = new SaleQuotationProcess();
        paramsVO.setSaleQuotationId(saleQuotationProduct.getSaleQuotationId());
        List<SaleQuotationProcess> list = saleQuotationProcessService.selectSaleQuotationProcessList(paramsVO);
        saleQuotationProcess.setProcessOrder(list.size()+1);
        saleQuotationProcess.setIsTimeCount("Y");
        saleQuotationProcess.setId(-1L);
        mmap.put("saleQuotationProcess", saleQuotationProcess);
        return prefix + "/edit";
    }

    /**
     * 新增保存报价工序
     */
    @RequiresPermissions("sale:saleQuotationProcess:add")
    @Log(title = "报价工序", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleQuotationProcess saleQuotationProcess)
    {
        return toAjax(saleQuotationProcessService.insertSaleQuotationProcess(saleQuotationProcess));
    }

    /**
     * 修改报价工序
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleQuotationProcess saleQuotationProcess = saleQuotationProcessService.selectSaleQuotationProcessById(id);
        mmap.put("saleQuotationProcess", saleQuotationProcess);
        return prefix + "/edit";
    }

    /**
     * 修改保存报价工序
     */
    @RequiresPermissions("sale:saleQuotationProcess:edit")
    @Log(title = "报价工序", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleQuotationProcess saleQuotationProcess)
    {
        return toAjax(saleQuotationProcessService.updateSaleQuotationProcess(saleQuotationProcess));
    }

    /**
     * 删除报价工序
     */
    @RequiresPermissions("sale:saleQuotationProcess:remove")
    @Log(title = "报价工序", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleQuotationProcessService.deleteSaleQuotationProcessByIds(ids));
    }

    /**
     * 更新顺序
     * @Author 方舟
     * @Date 2021/4/18 18:43:35
     **/
    @PostMapping( "/saveProcessOrder")
    @ResponseBody
    public AjaxResult saveProcessOrder(SaleQuotationProcess saleQuotationProcess)
    {
        int result = 0;
        String[] arr = saleQuotationProcess.getRzyValue1().split(",");
        for (int i=0;i<arr.length;i++){
            String[] arr2 = arr[i].split("_");
            SaleQuotationProcess cobj = new SaleQuotationProcess();
            cobj.setId(Long.parseLong(arr2[0]));
            cobj.setProcessOrder(Integer.parseInt(arr2[1]));
            result = saleQuotationProcessService.updateSaleQuotationProcess(cobj);
        }
        return toAjax(result);
    }


}
