package com.ruoyi.project.config.configProduct.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.FormulaUtils;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;
import com.ruoyi.project.config.configFormula.service.IConfigFormulaService;
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
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.config.configProduct.service.IConfigProductService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configProduct")
public class ConfigProductController extends BaseController
{
    private String prefix = "config/configProduct";

    @Autowired
    private IConfigProductService configProductService;
    @Autowired
    private IConfigFormulaService configFormulaService;

    @RequiresPermissions("config:configProduct:view")
    @GetMapping()
    public String configProduct()
    {
        return prefix + "/configProduct";
    }

    /**
     * 查询产品配置列表
     */
    @RequiresPermissions("config:configProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigProduct configProduct)
    {
        startPage();
        List<ConfigProduct> list = configProductService.selectConfigProductList(configProduct);
        return getDataTable(list);
    }

    @PostMapping("/fulllist")
    @ResponseBody
    public AjaxResult fulllist(ConfigProduct configProduct)
    {
        List<ConfigProduct> list = configProductService.selectConfigProductList(configProduct);
        return AjaxResult.success(list);
    }

    /**
     * 查询产品配置列表
     */
    @RequiresPermissions("config:configProduct:list")
    @PostMapping("/childList")
    @ResponseBody
    public TableDataInfo childList(ConfigProduct configProduct)
    {
        startPage();
        List<ConfigProduct> list = configProductService.selectConfigProductList(configProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品配置列表
     */
    @RequiresPermissions("config:configProduct:export")
    @Log(title = "产品配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigProduct configProduct)
    {
        List<ConfigProduct> list = configProductService.selectConfigProductList(configProduct);
        ExcelUtil<ConfigProduct> util = new ExcelUtil<ConfigProduct>(ConfigProduct.class);
        return util.exportExcel(list, "产品配置");
    }

    /**
     * 新增产品配置
     */
    @GetMapping("/add/{productType}")
    public String add(@PathVariable("productType") String productType, ModelMap mmap)
    {
        ConfigProduct configProduct = new ConfigProduct();
        configProduct.setIsPublic("Y");
        configProduct.setIsBigPanel("N");
        configProduct.setIsButtom("Y");
        configProduct.setIsAquatone("N");
        configProduct.setIsSpecialPaper("N");
        configProduct.setProductType(productType);
        configProduct.setSizeLong(0);
        configProduct.setSizeWidth(0);
        configProduct.setSizeHeight(0);
        mmap.put("configProduct", configProduct);
        return prefix + "/add";
    }

    /**
     * 新增产品配置
     */
    @GetMapping("/addChild/{parentId}/{productType}")
    public String add(@PathVariable("productType") String productType, @PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        ConfigProduct parentProduct = configProductService.selectConfigProductById(parentId);
        ConfigProduct configProduct = new ConfigProduct();
        BeanUtils.copyProperties(parentProduct, configProduct);
        configProduct.setProductType(productType);
        configProduct.setParentId(parentId);
        configProduct.setProductLevel("C");
        configProduct.setProductParentName(parentProduct.getProductName());
        configProduct.setProductName("");
        configProduct.setId(-1L);
        mmap.put("configProduct", configProduct);
        return prefix + "/editChild";
    }

    /**
     * 新增保存产品配置
     */
    @RequiresPermissions("config:configProduct:add")
    @Log(title = "产品配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigProduct configProduct)
    {
        return toAjax(configProductService.insertConfigProduct(configProduct));
    }

    /**
     * 修改产品配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigProduct configProduct = configProductService.selectConfigProductById(id);
        mmap.put("configProduct", configProduct);
        return prefix + "/edit";
    }

    /**
     * 修改产品配置
     */
    @GetMapping("/editChild/{type}/{id}")
    public String editChild(@PathVariable("type") String type, @PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigProduct configProduct = configProductService.selectConfigProductById(id);
        if("add".equals(type)){
            configProduct.setId(-1L);
            configProduct.setProductParentName(configProduct.getProductName());
            configProduct.setParentId(id);
        }
        mmap.put("configProduct", configProduct);
        return prefix + "/editChild";
    }

    /**
     * 修改保存产品配置
     */
    @RequiresPermissions("config:configProduct:edit")
    @Log(title = "产品配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigProduct configProduct)
    {
        return toAjax(configProductService.updateConfigProduct(configProduct));
    }

    /**
     * 删除产品配置
     */
    @RequiresPermissions("config:configProduct:remove")
    @Log(title = "产品配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configProductService.deleteConfigProductByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "产品配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configProduct:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigProduct> util = new ExcelUtil<ConfigProduct>(ConfigProduct.class);
        List<ConfigProduct> configProductList = util.importExcel(file.getInputStream());
        String message = configProductService.importConfigProduct(configProductList, updateSupport);
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
        ExcelUtil<ConfigProduct> util = new ExcelUtil<ConfigProduct>(ConfigProduct.class);
        return util.importTemplateExcel("产品配置");
    }

    /**
     * 计算面积
     * @Author 方舟
     * @Date 2021/4/16 14:34:09
    **/
    @PostMapping("/calculatorArea")
    @ResponseBody
    public AjaxResult calculatorArea(ConfigProduct configProduct) throws Exception
    {
        String message = "计算完成";
        ConfigProduct resultVO = new ConfigProduct();
        ConfigFormula configFormula = configFormulaService.selectConfigFormulaById(configProduct.getFormulaId());
        if(null==configFormula){
            message = "没有找到公式,无法计算";
        }else{
            if(StringUtils.isEmpty(configFormula.getOpenLongFormula())||StringUtils.isEmpty(configFormula.getOpenWidthFormula())){
                message = "【"+configFormula.getFormulaName()+"】没有填写展长展宽计算公式,无法计算";
            }else{
                Integer openLong = FormulaUtils.calculateForOpenSize("L",configFormula.getOpenLongFormula(),configFormula,configProduct);
                Integer openWidth = FormulaUtils.calculateForOpenSize("W",configFormula.getOpenWidthFormula(),configFormula,configProduct);
                Double area = FormulaUtils.calculateAreaWithOpen(openLong,openWidth);
                resultVO.setOpensizeLong(openLong);
                resultVO.setOpensizeWidth(openWidth);
                resultVO.setArea(new BigDecimal(area));
            }
        }
        if(!configProduct.getId().equals(-1)){
            resultVO.setId(configProduct.getId());
            configProductService.updateConfigProduct(resultVO);
        }
        return AjaxResult.success(message,resultVO);
    }

    /**
     * 弹出客户
     * @Author 方舟
     * @Date 2021/4/21 10:03:59
     **/
    @GetMapping("/selectProduct")
    public String selectProduct(ModelMap mmap)
    {
        ConfigProduct configProduct = new ConfigProduct();
        mmap.put("configProduct", configProduct);
        return prefix + "/selectProduct";
    }

    @GetMapping("/selectProduct/{customerId}")
    public String selectProduct(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        ConfigProduct configProduct = new ConfigProduct();
        configProduct.setCustomerId(customerId);
        mmap.put("configProduct", configProduct);
        return prefix + "/selectProduct";
    }

    @PostMapping("/getObjById")
    @ResponseBody
    public AjaxResult getObjById(ConfigProduct configProduct)
    {
        ConfigProduct resultVO = configProductService.selectConfigProductById(configProduct.getId());
        return AjaxResult.success(resultVO);
    }
}
