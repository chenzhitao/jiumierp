package com.ruoyi.project.purchase.purchaseRequest.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
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
import com.ruoyi.project.purchase.purchaseRequest.domain.PurchaseRequest;
import com.ruoyi.project.purchase.purchaseRequest.service.IPurchaseRequestService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采购申请Controller
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Controller
@RequestMapping("/purchase/purchaseRequest")
public class PurchaseRequestController extends BaseController
{
    private String prefix = "purchase/purchaseRequest";

    @Autowired
    private IPurchaseRequestService purchaseRequestService;

    @RequiresPermissions("purchase:purchaseRequest:view")
    @GetMapping()
    public String purchaseRequest()
    {
        return prefix + "/purchaseRequest";
    }

    /**
     * 查询采购申请列表
     */
    @RequiresPermissions("purchase:purchaseRequest:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PurchaseRequest purchaseRequest)
    {
        startPage();
        List<PurchaseRequest> list = purchaseRequestService.selectPurchaseRequestList(purchaseRequest);
        return getDataTable(list);
    }

    /**
     * 导出采购申请列表
     */
    @RequiresPermissions("purchase:purchaseRequest:export")
    @Log(title = "采购申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchaseRequest purchaseRequest)
    {
        List<PurchaseRequest> list = purchaseRequestService.selectPurchaseRequestList(purchaseRequest);
        ExcelUtil<PurchaseRequest> util = new ExcelUtil<PurchaseRequest>(PurchaseRequest.class);
        return util.exportExcel(list, "采购申请");
    }

    /**
     * 新增采购申请
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setId(-1L);
        purchaseRequest.setStatus("draft");
        purchaseRequest.setDeliveryDate(DateUtils.getNowDate());
        mmap.put("purchaseRequest", purchaseRequest);
        return prefix + "/edit";
    }

    /**
     * 新增保存采购申请
     */
    @RequiresPermissions("purchase:purchaseRequest:add")
    @Log(title = "采购申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchaseRequest purchaseRequest)
    {
        return toAjax(purchaseRequestService.insertPurchaseRequest(purchaseRequest));
    }

    /**
     * 修改采购申请
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseRequest purchaseRequest = purchaseRequestService.selectPurchaseRequestById(id);
        mmap.put("purchaseRequest", purchaseRequest);
        return prefix + "/edit";
    }

    /**
     * 修改保存采购申请
     */
    @RequiresPermissions("purchase:purchaseRequest:edit")
    @Log(title = "采购申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchaseRequest purchaseRequest)
    {
        return toAjax(purchaseRequestService.updatePurchaseRequest(purchaseRequest));
    }

    /**
     * 删除采购申请
     */
    @RequiresPermissions("purchase:purchaseRequest:remove")
    @Log(title = "采购申请", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(purchaseRequestService.deletePurchaseRequestByIds(ids));
    }


    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("purchase:purchaseRequest:add")
    @Log(title = "创建采购申请", businessType = BusinessType.INSERT)
    @PostMapping("/createPurchaseRequest")
    @ResponseBody
    public AjaxResult createPurchaseRequest(PurchaseRequest purchaseRequest)
    {
        PurchaseRequest result = purchaseRequestService.createPurchaseRequest(purchaseRequest);
        return AjaxResult.success("创建采购申请成功",result);
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "采购申请", businessType = BusinessType.IMPORT)
    @RequiresPermissions("purchase:purchaseRequest:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<PurchaseRequest> util = new ExcelUtil<PurchaseRequest>(PurchaseRequest.class);
        List<PurchaseRequest> purchaseRequestList = util.importExcel(file.getInputStream());
        String message = purchaseRequestService.importPurchaseRequest(purchaseRequestList, updateSupport);
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
        ExcelUtil<PurchaseRequest> util = new ExcelUtil<PurchaseRequest>(PurchaseRequest.class);
        return util.importTemplateExcel("采购申请");
    }

    /**
     * 选择销售产品
     */
    @GetMapping("/selectPurchaseRequest")
    public String selectPurchaseRequest()
    {
        return prefix + "/selectPurchaseRequest";
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest = purchaseRequestService.selectPurchaseRequestById(id);
        mmap.put("purchaseRequest", purchaseRequest);
        return prefix + "/print";
    }
}
