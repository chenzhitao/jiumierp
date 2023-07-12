package com.ruoyi.project.config.configFormula.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.FormulaUtils;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configFormula.domain.ConfigFormulaSimulation;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
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
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;
import com.ruoyi.project.config.configFormula.service.IConfigFormulaService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公式配置Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configFormula")
public class ConfigFormulaController extends BaseController
{
    private String prefix = "config/configFormula";

    @Autowired
    private IConfigFormulaService configFormulaService;

    @RequiresPermissions("config:configFormula:view")
    @GetMapping()
    public String configFormula()
    {
        return prefix + "/configFormula";
    }

    /**
     * 查询公式配置列表
     */
    @RequiresPermissions("config:configFormula:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigFormula configFormula)
    {
        startPage();
        List<ConfigFormula> list = configFormulaService.selectConfigFormulaList(configFormula);
        return getDataTable(list);
    }

    /**
     * 导出公式配置列表
     */
    @RequiresPermissions("config:configFormula:export")
    @Log(title = "公式配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigFormula configFormula)
    {
        List<ConfigFormula> list = configFormulaService.selectConfigFormulaList(configFormula);
        ExcelUtil<ConfigFormula> util = new ExcelUtil<ConfigFormula>(ConfigFormula.class);
        return util.exportExcel(list, "公式配置");
    }

    /**
     * 新增公式配置
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        ConfigFormula configFormula = new ConfigFormula();
        configFormula.setIsCustom("N");
        mmap.put("configFormula", configFormula);
        return prefix + "/add";
    }

    /**
     * 新增保存公式配置
     */
    @RequiresPermissions("config:configFormula:add")
    @Log(title = "公式配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigFormula configFormula)
    {
        return toAjax(configFormulaService.insertConfigFormula(configFormula));
    }

    /**
     * 修改公式配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigFormula configFormula = configFormulaService.selectConfigFormulaById(id);
        mmap.put("configFormula", configFormula);
        return prefix + "/edit";
    }

    /**
     * 修改保存公式配置
     */
    @RequiresPermissions("config:configFormula:edit")
    @Log(title = "公式配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigFormula configFormula)
    {
        return toAjax(configFormulaService.updateConfigFormula(configFormula));
    }

    /**
     * 删除公式配置
     */
    @RequiresPermissions("config:configFormula:remove")
    @Log(title = "公式配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configFormulaService.deleteConfigFormulaByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "公式配置", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configFormula:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigFormula> util = new ExcelUtil<ConfigFormula>(ConfigFormula.class);
        List<ConfigFormula> configFormulaList = util.importExcel(file.getInputStream());
        String message = configFormulaService.importConfigFormula(configFormulaList, updateSupport);
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
        ExcelUtil<ConfigFormula> util = new ExcelUtil<ConfigFormula>(ConfigFormula.class);
        return util.importTemplateExcel("公式配置");
    }

    /**
     * 打开模拟计算
     * @Author 方舟
     * @Date 2021/4/15 11:49:56
    **/
    @GetMapping("/simulation/{id}")
    public String simulationDialog(@PathVariable("id") Long id, ModelMap mmap) throws Exception
    {
        ConfigFormula configFormula = configFormulaService.selectConfigFormulaById(id);
        mmap.put("configFormula", configFormula);
        return prefix + "/simulation";
    }

    /**
     * 模拟计算
     * @Author 方舟
     * @Date 2021/4/15 12:35:20
    **/
    @PostMapping("/simulation")
    @ResponseBody
    public AjaxResult simulation(ConfigFormulaSimulation configFormulaSimulation) throws Exception
    {
        ConfigFormula configFormula = configFormulaService.selectConfigFormulaById(configFormulaSimulation.getId());
        if(configFormulaSimulation.getFormulaType().equals("product_area")){
            //面积计算
            ConfigProduct configProduct = new ConfigProduct();
            configProduct.setSizeLong(configFormulaSimulation.getProductLong());
            configProduct.setSizeWidth(configFormulaSimulation.getProductWidth());
            configProduct.setSizeHeight(configFormulaSimulation.getProductHeight());
            //展长
            Integer openLong = FormulaUtils.calculateForOpenSize("L", configFormula.getOpenLongFormula(),configFormula,configProduct);
            //展宽
            Integer openWidth = FormulaUtils.calculateForOpenSize("W", configFormula.getOpenWidthFormula(),configFormula,configProduct);
            //面积
            Double area = FormulaUtils.calculateAreaWithOpen(openLong,openWidth);
            configFormulaSimulation.setOpenLong(openLong);
            configFormulaSimulation.setOpenWidth(openWidth);
            configFormulaSimulation.setArea(area);
        }
        if(configFormulaSimulation.getFormulaType().equals("process_price")){
            //价格计算
            ConfigFormula calculateResult = FormulaUtils.calculateProcessPrice(configFormulaSimulation.getProcessQty(),configFormula);
            configFormulaSimulation.setProcessPrice(calculateResult.getAmount());
            configFormulaSimulation.setCalculateLog(calculateResult.getCalculateLog());
        }
        if(configFormulaSimulation.getFormulaType().equals("materials_qty")){
            //材料数量计算
            Integer qty = configFormulaSimulation.getProcessQty();
            Integer resultQty = FormulaUtils.calculateMaterialsLossQty(qty,configFormula).getOutQty();
            configFormulaSimulation.setResultQty(resultQty);
        }
        return AjaxResult.success("计算完成", configFormulaSimulation);
    }
}
