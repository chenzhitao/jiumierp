package com.ruoyi.project.sale.saleQuotation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotationExport;
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMult;
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
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
import com.ruoyi.project.sale.saleQuotation.service.ISaleQuotationService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报价单Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/sale/saleQuotation")
public class SaleQuotationController extends BaseController
{
    private String prefix = "sale/saleQuotation";

    @Autowired
    private ISaleQuotationService saleQuotationService;

    @RequiresPermissions("sale:saleQuotation:view")
    @GetMapping()
    public String saleQuotation()
    {
        return prefix + "/saleQuotation";
    }

    /**
     * 查询报价单列表
     */
    @RequiresPermissions("sale:saleQuotation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SaleQuotation saleQuotation)
    {
        startPage();
        if(!StringUtils.isEmpty(saleQuotation.getRzyValue1())||"print".equals(saleQuotation.getRzyValue1())){
            List<SaleQuotationExport> list = saleQuotationService.selectSaleQuotationExportList(saleQuotation);
            return getDataTable(list);
        }else{
            List<SaleQuotation> list = saleQuotationService.selectSaleQuotationList(saleQuotation);
            return getDataTable(list);
        }
    }

    /**
     * 导出报价单列表
     */
    @RequiresPermissions("sale:saleQuotation:export")
    @Log(title = "报价单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SaleQuotation saleQuotation)
    {
        List<SaleQuotationExport> list = saleQuotationService.selectSaleQuotationExportList(saleQuotation);
        ExcelUtil<SaleQuotationExport> util = new ExcelUtil<SaleQuotationExport>(SaleQuotationExport.class);
        return util.exportExcel(list, "报价单");
    }

    /**
     * 新增报价单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存报价单
     */
    @RequiresPermissions("sale:saleQuotation:add")
    @Log(title = "报价单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SaleQuotation saleQuotation)
    {
        return toAjax(saleQuotationService.insertSaleQuotation(saleQuotation));
    }

    /**
     * 修改报价单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleQuotation saleQuotation = new SaleQuotation();
        if(id.equals(-1L)){
            saleQuotation.setId(id);
            saleQuotation.setQuotationDate(new Date());
        }else{
            saleQuotation = saleQuotationService.selectSaleQuotationById(id);
        }
        mmap.put("saleQuotation", saleQuotation);
        //return prefix + "/edit";
        return prefix + "/saleQuotationDetail";
    }

    /**
     * 修改保存报价单
     */
    @RequiresPermissions("sale:saleQuotation:edit")
    @Log(title = "报价单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SaleQuotation saleQuotation)
    {
        if(!StringUtils.isEmpty(saleQuotation.getRzyValue1())&&"approve".equals(saleQuotation.getRzyValue1())){
            saleQuotation.setApprover(ShiroUtils.getSysUser().getUserName());
            saleQuotation.setApprovalTime(DateUtils.getNowDate());
        }
        SaleQuotation result = saleQuotationService.updateSaleQuotation(saleQuotation);
        return AjaxResult.success("操作成功",result);
    }

    /**
     * 删除报价单
     */
    @RequiresPermissions("sale:saleQuotation:remove")
    @Log(title = "报价单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(saleQuotationService.deleteSaleQuotationByIds(ids));
    }

    /**
     * 选择客户创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
    **/
    @RequiresPermissions("sale:saleQuotation:add")
    @Log(title = "创建报价单", businessType = BusinessType.INSERT)
    @PostMapping("/createSaleQuotation")
    @ResponseBody
    public AjaxResult createSaleQuotation(SaleQuotation saleQuotation)
    {
        SaleQuotation result = saleQuotationService.createSaleQuotation(saleQuotation);
        return AjaxResult.success("创建报价单成功",result);
    }

    /**
     * 报价单计算
     * @Author 方舟
     * @Date 2021/4/21 14:22:23
     **/
    @RequiresPermissions("sale:saleQuotationMult:remove")
    @Log(title = "报价单计算", businessType = BusinessType.UPDATE)
    @PostMapping( "/calculator")
    @ResponseBody
    public AjaxResult calculator(SaleQuotation saleQuotation)
    {
        SaleQuotation resultVO = saleQuotationService.calculator(saleQuotation);
        return AjaxResult.success("计算完成",resultVO);
    }


    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "报价单", businessType = BusinessType.IMPORT)
    @RequiresPermissions("sale:saleQuotationMult:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SaleQuotation> util = new ExcelUtil<SaleQuotation>(SaleQuotation.class);
        List<SaleQuotation> saleQuotationList = util.importExcel(file.getInputStream());
        String message = saleQuotationService.importSaleQuotation(saleQuotationList, updateSupport);
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
        ExcelUtil<SaleQuotation> util = new ExcelUtil<SaleQuotation>(SaleQuotation.class);
        return util.importTemplateExcel("报价单");
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
    **/
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        SaleQuotation saleQuotation = new SaleQuotation();
        saleQuotation = saleQuotationService.selectSaleQuotationById(id);
        mmap.put("saleQuotation", saleQuotation);
        return prefix + "/print";
    }

}
