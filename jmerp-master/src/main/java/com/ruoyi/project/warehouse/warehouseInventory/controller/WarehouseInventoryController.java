package com.ruoyi.project.warehouse.warehouseInventory.controller;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.config.configCorrugated.domain.ConfigCorrugated;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
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
import com.ruoyi.project.warehouse.warehouseInventory.domain.WarehouseInventory;
import com.ruoyi.project.warehouse.warehouseInventory.service.IWarehouseInventoryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 库存统计Controller
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Controller
@RequestMapping("/warehouse/warehouseInventory")
public class WarehouseInventoryController extends BaseController
{
    private String prefix = "warehouse/warehouseInventory";

    @Autowired
    private IWarehouseInventoryService warehouseInventoryService;
    @Autowired
    private IWarehouseRecordService warehouseRecordService;

    @RequiresPermissions("warehouse:warehouseInventory:view")
    @GetMapping()
    public String warehouseInventory()
    {
        return prefix + "/warehouseInventory";
    }

    /**
     * 查询库存统计列表
     */
    @RequiresPermissions("warehouse:warehouseInventory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WarehouseInventory warehouseInventory)
    {
        startPage();
        List<WarehouseInventory> list = warehouseInventoryService.selectWarehouseInventoryList(warehouseInventory);
        /*if(!StringUtils.isEmpty(warehouseInventory.getRzyValue1())&"search".equals(warehouseInventory.getRzyValue1())){
            if(list.size()==0){
                if(null==warehouseInventory.getMaterialsId()){
                    warehouseInventory.setMaterialsId(-1L);
                }
                if(null==warehouseInventory.getProductId()){
                    warehouseInventory.setProductId(-1L);
                }
                if(null==warehouseInventory.getWarehouseId()){
                    warehouseInventory.setWarehouseId(-1L);
                }
                if(null!=warehouseInventory.getProductId()||null!=warehouseInventory.getMaterialsId()){
                    WarehouseInventory emptyVO = warehouseInventoryService.createEmptyWarehouseInventory(warehouseInventory);
                    list.add(emptyVO);
                }
            }
        }*/
        return getDataTable(list);
    }

    /**
     * 导出库存统计列表
     */
    @RequiresPermissions("warehouse:warehouseInventory:export")
    @Log(title = "库存统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WarehouseInventory warehouseInventory)
    {
        List<WarehouseInventory> list = warehouseInventoryService.selectWarehouseInventoryList(warehouseInventory);
        ExcelUtil<WarehouseInventory> util = new ExcelUtil<WarehouseInventory>(WarehouseInventory.class);
        return util.exportExcel(list, "库存统计");
    }

    /**
     * 新增库存统计
     */
    @GetMapping("/add/{type}")
    public String add(@PathVariable("type") String type, ModelMap mmap)
    {
        WarehouseInventory warehouseInventory = new WarehouseInventory();
        warehouseInventory.setGoodsType(type);
        mmap.put("warehouseInventory", warehouseInventory);
        return prefix + "/add";
    }

    /**
     * 新增保存库存统计
     */
    @RequiresPermissions("warehouse:warehouseInventory:add")
    @Log(title = "库存统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WarehouseInventory warehouseInventory)
    {
        int res = warehouseInventoryService.insertWarehouseInventory(warehouseInventory);
        if(null!=warehouseInventory.getProductId()){
            warehouseRecordService.productInbound("SDTZ",warehouseInventory.getWarehouseId(),warehouseInventory.getQty(),warehouseInventory.getProductId(),"");
        }
        if(null!=warehouseInventory.getMaterialsId()){
            warehouseRecordService.materialsInbound("SDTZ",warehouseInventory.getWarehouseId(),warehouseInventory.getQty(),warehouseInventory.getMaterialsId(),"");
        }
        return toAjax(res);
    }

    /**
     * 修改库存统计
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WarehouseInventory warehouseInventory = warehouseInventoryService.selectWarehouseInventoryById(id);
        mmap.put("warehouseInventory", warehouseInventory);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存统计
     */
    @RequiresPermissions("warehouse:warehouseInventory:edit")
    @Log(title = "库存统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WarehouseInventory warehouseInventory)
    {
        WarehouseInventory org = warehouseInventoryService.selectWarehouseInventoryById(warehouseInventory.getId());
        Integer qty = warehouseInventory.getQty() - org.getQty();
        if(null!=warehouseInventory.getProductId()){
            warehouseRecordService.productInbound("SDTZ",warehouseInventory.getWarehouseId(),qty,warehouseInventory.getProductId(),"");
        }
        if(null!=warehouseInventory.getMaterialsId()){
            warehouseRecordService.materialsInbound("SDTZ",warehouseInventory.getWarehouseId(),qty,warehouseInventory.getMaterialsId(),"");
        }
        int res = warehouseInventoryService.updateWarehouseInventory(warehouseInventory);
        return toAjax(res);
    }

    /**
     * 删除库存统计
     */
    @RequiresPermissions("warehouse:warehouseInventory:remove")
    @Log(title = "库存统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(warehouseInventoryService.deleteWarehouseInventoryByIds(ids));
    }



    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/13 20:27:42
     **/
    @Log(title = "库存统计", businessType = BusinessType.IMPORT)
    @RequiresPermissions("config:warehouseInventory:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<WarehouseInventory> util = new ExcelUtil<WarehouseInventory>(WarehouseInventory.class);
        List<WarehouseInventory> warehouseInventoryList = util.importExcel(file.getInputStream());
        String message = warehouseInventoryService.importWarehouseInventory(warehouseInventoryList, updateSupport);
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
        ExcelUtil<WarehouseInventory> util = new ExcelUtil<WarehouseInventory>(WarehouseInventory.class);
        return util.importTemplateExcel("库存统计");
    }


    /**
     * 合并整理
     * @Author 方舟
     * @Date 2021/4/27 17:13:19
     **/
    @Log(title = "库存统计", businessType = BusinessType.UPDATE)
    @PostMapping("/reorganize")
    @ResponseBody
    public AjaxResult reorganize(WarehouseInventory warehouseInventory){
        WarehouseInventory result = warehouseInventoryService.reorganize(warehouseInventory);
        return AjaxResult.success("整理完成",result);
    }

    /**
     * 导入模板
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @GetMapping("/openSetup/{ids}")
    public String openSetup(@PathVariable("ids") String ids, ModelMap mmap)
    {
        WarehouseInventory warehouseInventory = new WarehouseInventory();
        warehouseInventory.setIds(ids);
        mmap.put("warehouseInventory", warehouseInventory);
        return prefix + "/setupWarehouse";
    }

    /**
     * 更换仓库
     * @Author 方舟
     * @Date 2021/4/27 17:13:19
     **/
    @Log(title = "更换仓库", businessType = BusinessType.UPDATE)
    @PostMapping("/moveWarehouse")
    @ResponseBody
    public AjaxResult moveWarehouse(WarehouseInventory warehouseInventory){
        WarehouseInventory result = warehouseInventoryService.moveWarehouse(warehouseInventory);
        return AjaxResult.success("移库完成",result);
    }
}
