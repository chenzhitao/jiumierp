package com.ruoyi.project.config.configCustomer.controller;

import java.util.List;

import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
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
import com.ruoyi.project.config.configCustomer.service.IConfigCustomerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 客户信息Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/config/configCustomer")
public class ConfigCustomerController extends BaseController
{
    private String prefix = "config/configCustomer";

    @Autowired
    private IConfigCustomerService configCustomerService;

    @RequiresPermissions("config:configCustomer:view")
    @GetMapping()
    public String configCustomer()
    {
        return prefix + "/configCustomer";
    }

    /**
     * 查询客户信息列表
     */
    @RequiresPermissions("config:configCustomer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ConfigCustomer configCustomer)
    {
        startPage();
        List<ConfigCustomer> list = configCustomerService.selectConfigCustomerList(configCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户信息列表
     */
    @RequiresPermissions("config:configCustomer:export")
    @Log(title = "客户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ConfigCustomer configCustomer)
    {
        List<ConfigCustomer> list = configCustomerService.selectConfigCustomerList(configCustomer);
        ExcelUtil<ConfigCustomer> util = new ExcelUtil<ConfigCustomer>(ConfigCustomer.class);
        return util.exportExcel(list, "客户信息");
    }

    /**
     * 新增客户信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户信息
     */
    @RequiresPermissions("config:configCustomer:add")
    @Log(title = "客户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ConfigCustomer configCustomer)
    {
        return toAjax(configCustomerService.insertConfigCustomer(configCustomer));
    }

    /**
     * 修改客户信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ConfigCustomer configCustomer = configCustomerService.selectConfigCustomerById(id);
        mmap.put("configCustomer", configCustomer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户信息
     */
    @RequiresPermissions("config:configCustomer:edit")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ConfigCustomer configCustomer)
    {
        return toAjax(configCustomerService.updateConfigCustomer(configCustomer));
    }

    /**
     * 删除客户信息
     */
    @RequiresPermissions("config:configCustomer:remove")
    @Log(title = "客户信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(configCustomerService.deleteConfigCustomerByIds(ids));
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "客户信息", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:configCustomer:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConfigCustomer> util = new ExcelUtil<ConfigCustomer>(ConfigCustomer.class);
        List<ConfigCustomer> configCustomerList = util.importExcel(file.getInputStream());
        String message = configCustomerService.importConfigCustomer(configCustomerList, updateSupport);
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
        ExcelUtil<ConfigCustomer> util = new ExcelUtil<ConfigCustomer>(ConfigCustomer.class);
        return util.importTemplateExcel("客户信息");
    }

    /**
     * 弹出客户
     * @Author 方舟
     * @Date 2021/4/21 10:03:59
    **/
    @GetMapping("/selectCustomer")
    public String selectCustomer()
    {
        return prefix + "/selectCustomer";
    }
}
