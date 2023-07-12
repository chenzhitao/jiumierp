package com.ruoyi.project.sale.saleOrder.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrderExport;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
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
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrder.service.ISaleOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 销售订单Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleOrder")
public class SaleOrderController extends BaseController
{
    private String prefix = "sale/saleOrder";

    @Autowired
    private ISaleOrderService saleOrderService;

    @RequiresPermissions("sale:saleOrder:view")
    @GetMapping()
    public String saleOrder()
    {
        return prefix + "/saleOrder";
    }

    /**
     * 查询销售订单列表
     */
    @RequiresPermissions("sale:saleOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleOrder saleOrder)
    {
        startPage();
        List<SaleOrder> list = saleOrderService.selectSaleOrderList(saleOrder);
        return getDataTable(list);
    }

    /**
     * 导出销售订单列表
     */
    @RequiresPermissions("sale:saleOrder:export")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleOrder saleOrder)
    {
        List<SaleOrderExport> list = saleOrderService.selectSaleOrderExportList(saleOrder);
        ExcelUtil<SaleOrderExport> util = new ExcelUtil<SaleOrderExport>(SaleOrderExport.class);
        return util.exportExcel(list, "销售订单");
    }

    /**
     * 新增销售订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存销售订单
     */
    @RequiresPermissions("sale:saleOrder:add")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleOrder saleOrder)
    {
        return toAjax(saleOrderService.insertSaleOrder(saleOrder));
    }

    /**
     * 修改销售订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleOrder saleOrder = new SaleOrder();
        if(id.equals(-1L)){
            saleOrder.setId(id);
            saleOrder.setDeliveryDate(DateUtils.getNowDate());
        }else{
            saleOrder = saleOrderService.selectSaleOrderById(id);
        }
        mmap.put("saleOrder", saleOrder);
        //return prefix + "/edit";
        return prefix + "/saleOrderDetail";
    }

    /**
     * 查询对象
     * @Author 方舟
     * @Date 2021/8/13 17:25:39
    **/
    @PostMapping("/single")
    @ResponseBody
    public AjaxResult single(SaleOrder saleOrder)
    {
        SaleOrder resultVO = new SaleOrder();
        resultVO = saleOrderService.selectSaleOrderById(saleOrder.getId());
        return AjaxResult.success(resultVO);
    }

    /**
     * 修改保存销售订单
     */
    @RequiresPermissions("sale:saleOrder:edit")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleOrder saleOrder)
    {
        if(!StringUtils.isEmpty(saleOrder.getRzyValue1())&&"approve".equals(saleOrder.getRzyValue1())){
            saleOrder.setApprover(ShiroUtils.getSysUser().getUserName());
            saleOrder.setApprovalTime(DateUtils.getNowDate());
        }
        return toAjax(saleOrderService.updateSaleOrder(saleOrder));
    }

    /**
     * 删除销售订单
     */
    @RequiresPermissions("sale:saleOrder:remove")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleOrderService.deleteSaleOrderByIds(ids));
    }

    /**
     * 选择客户创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("sale:saleOrder:add")
    @Log(title = "创建销售订单", businessType = BusinessType.INSERT)
    @PostMapping("/createSaleOrder")
    @ResponseBody
    public AjaxResult createSaleOrder(SaleOrder saleOrder)
    {
        SaleOrder result = saleOrderService.createSaleOrder(saleOrder);
        return AjaxResult.success("创建销售订单成功",result);
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "销售订单", businessType = BusinessType.IMPORT)
    @RequiresPermissions("sale:saleOrderMult:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SaleOrderProduct> util = new ExcelUtil<SaleOrderProduct>(SaleOrderProduct.class);
        List<SaleOrderProduct> saleOrderProductList = util.importExcel(file.getInputStream());
        String message = saleOrderService.importSaleOrder(saleOrderProductList, updateSupport);
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
        ExcelUtil<SaleOrderProduct> util = new ExcelUtil<SaleOrderProduct>(SaleOrderProduct.class);
        return util.importTemplateExcel("快速开单");
    }

    /**
     * 汇总金额
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @PostMapping("/countAmount")
    @ResponseBody
    public AjaxResult countAmount(SaleOrder saleOrder)
    {
        SaleOrder result = saleOrderService.countAmount(saleOrder);
        return AjaxResult.success("计算成功",result);
    }

    /**
     * 复制
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @PostMapping("/copySaleOrder")
    @ResponseBody
    public AjaxResult copySaleOrder(SaleOrder saleOrder)
    {
        SaleOrder result = saleOrderService.copySaleOrder(saleOrder);
        return AjaxResult.success("复制成功",result);
    }


    /**
     * 复制
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @PostMapping("/batchApprove")
    @ResponseBody
    public AjaxResult batchApprove(SaleOrder saleOrder)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(saleOrder.getIds());
        for (int i=0;i<arr.length;i++){
            SaleOrder saveVO = new SaleOrder();
            Long id = Long.parseLong(arr[i]);
            saveVO.setId(id);
            saveVO.setStatus("normal");
            saveVO.setRzyValue1("approve");
            saveVO.setApprover(ShiroUtils.getSysUser().getUserName());
            saveVO.setApprovalTime(DateUtils.getNowDate());
            result = saleOrderService.updateSaleOrder(saveVO);
        }
        return AjaxResult.success("审批成功",saleOrder);
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder = saleOrderService.selectSaleOrderById(id);
        mmap.put("saleOrder", saleOrder);
        return prefix + "/print";
    }
}
