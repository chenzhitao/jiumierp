package com.ruoyi.project.produce.produceOrder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.FormulaUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configEquipment.domain.ConfigEquipment;
import com.ruoyi.project.config.configQuotationTemplate.domain.ConfigQuotationTemplate;
import com.ruoyi.project.produce.produceOrderMaterials.domain.ProduceOrderMaterials;
import com.ruoyi.project.produce.produceOrderMaterials.service.IProduceOrderMaterialsService;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.service.IProduceOrderProcessService;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import com.ruoyi.project.rzy.rzyCommon.domain.RzyCommon;
import com.ruoyi.project.rzy.rzyCommon.service.IRzyCommonService;
import com.ruoyi.project.sale.saleChecking.domain.SaleChecking;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
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
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceOrder.service.IProduceOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 施工单Controller
 * 
 * @author fangzhou
 * @date 2021-04-30
 */
@Controller
@RequestMapping("/produce/produceOrder")
public class ProduceOrderController extends BaseController
{
    private String prefix = "produce/produceOrder";

    @Autowired
    private IProduceOrderService produceOrderService;
    @Autowired
    private IProduceOrderProcessService produceOrderProcessService;
    @Autowired
    private IProduceOrderMaterialsService produceOrderMaterialsService;
    @Autowired
    private IRzyCommonService rzyCommonService;

    @RequiresPermissions("produce:produceOrder:view")
    @GetMapping()
    public String produceOrder()
    {
        return prefix + "/produceOrder";
    }

    /**
     * 查询施工单列表
     */
    @RequiresPermissions("produce:produceOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProduceOrder produceOrder)
    {
        startPage();
        List<ProduceOrder> list = produceOrderService.selectProduceOrderList(produceOrder);
        return getDataTable(list);
    }
    /**
     * 查询施工单列表
     */
    @RequiresPermissions("produce:produceOrder:list")
    @PostMapping("/partlist")
    @ResponseBody
    public AjaxResult partlist(ProduceOrder produceOrder)
    {
        List<ProduceOrder> list = produceOrderService.selectProduceOrderPartList(produceOrder);
        return AjaxResult.success(list);
    }
    @PostMapping("/getPartById/{id}")
    @ResponseBody
    public AjaxResult getPartById(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrder part = produceOrderService.selectProduceOrderPartById(id);
        return AjaxResult.success(part);
    }

    /**
     * 导出施工单列表
     */
    @RequiresPermissions("produce:produceOrder:export")
    @Log(title = "施工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProduceOrder produceOrder)
    {
        List<ProduceOrder> list = produceOrderService.selectProduceOrderList(produceOrder);
        ExcelUtil<ProduceOrder> util = new ExcelUtil<ProduceOrder>(ProduceOrder.class);
        return util.exportExcel(list, "施工单");
    }

    /**
     * 新增施工单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存施工单
     */
    @RequiresPermissions("produce:produceOrder:add")
    @Log(title = "施工单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProduceOrder produceOrder)
    {
        return toAjax(produceOrderService.insertProduceOrder(produceOrder));
    }

    /**
     * 修改施工单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrder produceOrder = produceOrderService.selectProduceOrderById(id);
        mmap.put("produceOrder", produceOrder);
        return prefix + "/produceOrderDetail";
    }

    /**
     * 修改施工单
     */
    @GetMapping("/getPart/{id}")
    public AjaxResult getPart(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrder produceOrder = produceOrderService.selectProduceOrderPartById(id);
        return AjaxResult.success(produceOrder);
    }

    /**
     * 修改保存施工单
     */
    @RequiresPermissions("produce:produceOrder:edit")
    @Log(title = "施工单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProduceOrder produceOrder)
    {
        return toAjax(produceOrderService.updateProduceOrder(produceOrder));
    }

    @PostMapping("/updatePart")
    @ResponseBody
    public AjaxResult updatePart(ProduceOrder produceOrder)
    {
        return toAjax(produceOrderService.updateProduceOrderPart(produceOrder));
    }


    /**
     * 删除施工单
     */
    @RequiresPermissions("produce:produceOrder:remove")
    @Log(title = "施工单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(produceOrderService.deleteProduceOrderByIds(ids));
    }

    /**
     * 加载工艺卡里的工序和材料
     * @Author 方舟
     * @Date 2021/5/5 9:44:33
    **/
    @PostMapping("/loadProcessMaterials")
    @ResponseBody
    public AjaxResult loadProcessMaterials(ProduceOrder produceOrder)
    {
        ProduceOrder resultVO = produceOrderService.loadProcessMaterials(produceOrder);
        return AjaxResult.success("更新成功",resultVO);
    }

    /**
     * 加载工艺卡里的工序和材料
     * @Author 方舟
     * @Date 2021/5/5 9:44:33
    **/
    @PostMapping("/areaCalculator")
    @ResponseBody
    public AjaxResult areaCalculator(ProduceOrder produceOrder)
    {
        ProduceOrder resultVO = produceOrderService.areaCalculator(produceOrder);
        return AjaxResult.success("加载成功",resultVO);
    }

    /**
     * 拼版页面
     * @Author 方舟
     * @Date 2021/5/5 11:13:25
    **/
    @GetMapping("/openPuzzleTest/{id}")
    public String openPuzzleTest(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrder produceOrder = produceOrderService.selectProduceOrderById(id);
        mmap.put("produceOrder", produceOrder);
        return prefix + "/puzzleTest";
    }

    /**
     * 查询施工单列表
     */
    @PostMapping("/puzzleList")
    @ResponseBody
    public TableDataInfo puzzleList(ProduceOrder produceOrder)
    {
        produceOrder = produceOrderService.selectProduceOrderById(produceOrder.getId());
        List<RzyCommon> list = FormulaUtils.puzzleCount(produceOrder.getWorkLong(),produceOrder.getWorkWidth(),produceOrder.getOpensizeLong(),produceOrder.getOpensizeWidth());
        List<ProduceOrder> resultList = new ArrayList<ProduceOrder>();
        Integer maxQty = 0;
        for (int i=0;i<list.size();i++){
            ProduceOrder po = new ProduceOrder();
            po.setRemark(list.get(i).getStrValue1());
            po.setPuzzleQty(list.get(i).getIntValue1());
            po.setId(-1L-i);
            if(list.get(i).getIntValue1()>maxQty){
                maxQty = list.get(i).getIntValue1();
            }
            resultList.add(po);
        }
        for (int i=0;i<resultList.size();i++){
            if(maxQty.equals(resultList.get(i).getPuzzleQty())){
                resultList.get(i).setRzyValue1("Y");
            }
        }
        return getDataTable(resultList);
    }

    /**
     * 计算工序投入产出数
     * @Author 方舟
     * @Date 2021/5/6 10:31:30
    **/
    @PostMapping("/inoutCount")
    @ResponseBody
    public AjaxResult inoutCount(ProduceOrder produceOrder){
        String type = produceOrder.getRzyValue1();
        //先保存数量
        produceOrderService.updateProduceOrder(produceOrder);
        //再计算
        ProduceOrderProcess popVO = new ProduceOrderProcess();
        ProduceOrderMaterials pomVO = new ProduceOrderMaterials();
        popVO.setProduceOrderId(produceOrder.getId());
        pomVO.setProduceOrderId(produceOrder.getId());
        //准备数据
        ProduceOrder paramVO = produceOrderService.selectProduceOrderById(produceOrder.getId());
        List<ProduceOrderProcess> processList = produceOrderProcessService.selectProduceOrderProcessList(popVO);
        List<ProduceOrderMaterials> materialsList = produceOrderMaterialsService.selectProduceOrderMaterialsList(pomVO);
        if(!StringUtils.isEmpty(type)){
            if(type.equals("number")){
                //3计算投入产出
                produceOrderService.inoutCountIO(paramVO,processList,paramVO.getQty());
                //4材料数量计算
                produceOrderService.inoutCountCLQ(paramVO,processList,paramVO.getQty());
            }
            if(type.equals("size")){
                //1,计算印刷开料,拼版
                produceOrderService.inoutCountYS(paramVO,processList,materialsList);
                //2.切纸开料倍率计算
                produceOrderService.inoutCountQZ(paramVO,processList,materialsList);
                //2.5 模切尺寸计算
                produceOrderService.inoutCountMQ(paramVO,processList,materialsList);
            }
        }
        return AjaxResult.success("计算完成",paramVO);
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @RequiresPermissions("produce:produceOrder:add")
    @Log(title = "创建施工单", businessType = BusinessType.INSERT)
    @PostMapping("/createProduceOrder")
    @ResponseBody
    public AjaxResult createProduceOrder(ProduceOrder produceOrder)
    {
        ProduceOrder result = produceOrderService.createProduceOrder(produceOrder);
        return AjaxResult.success("创建施工单成功",result);
    }

    /**
     * 打开选择仓库
     * @Author 方舟
     * @Date 2021/5/18 12:14:49
    **/
    @GetMapping("/selectWarehouse/{id}")
    public String selectWarehouse(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrder produceOrder = new ProduceOrder();
        produceOrder.setId(id);
        mmap.put("produceOrder", produceOrder);
        return prefix + "/selectWarehouse";
    }

    /**
     * 产品入库
     * @Author 方舟
     * @Date 2021/5/18 12:13:10
    **/
    @PostMapping("/inbound")
    @ResponseBody
    public AjaxResult inbound(ProduceOrder produceOrder)
    {
        ProduceOrder result = produceOrderService.inbound(produceOrder);
        return AjaxResult.success("产品入库成功",result);
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print2/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrder produceOrder = new ProduceOrder();
        produceOrder = produceOrderService.selectProduceOrderById(id);
        mmap.put("produceOrder", produceOrder);
        return prefix + "/print";
    }

    /**
     * 打印2
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/print/{type}/{id}")
    public String print2(@PathVariable("id") Long id, @PathVariable("type") String type, ModelMap mmap)
    {
        String rzy = "印刷ERP";
        List<RzyCommon> resultList = rzyCommonService.selectSysInfo();
        for(int i=0;i<resultList.size();i++){
            if(!StringUtils.isEmpty(resultList.get(i).getStrValue2())&&"title".equals(resultList.get(i).getStrValue2())){
                rzy = resultList.get(i).getStrValue1();
            }
        }
        ProduceOrder produceOrder = new ProduceOrder();
        produceOrder = produceOrderService.selectProduceOrderById(id);
        ProduceOrderProcess popvo = new ProduceOrderProcess();
        popvo.setProduceOrderId(id);
        List<ProduceOrderProcess> list =  produceOrderProcessService.selectProduceOrderProcessList(popvo);
        String pt = "";
        for (int i=0;i<list.size();i++){
            if(!StringUtils.isEmpty(list.get(i).getProcessName())&&"印刷".equals(list.get(i).getProcessName())){
                if(!pt.equals("")){
                    pt += ";";
                }
                pt += list.get(i).getPrintType();
            }
        }
        produceOrder.setPrintType(pt);
        produceOrder.setRzyCustomer(rzy);
        produceOrder.setRzyValue1(ShiroUtils.getSysUser().getUserName());
        mmap.put("produceOrder", produceOrder);
        if(type.equals("level")){
            return prefix + "/print3";
        }else{
            return prefix + "/print2";
        }
    }

    /**
     * 获取印刷设备
     * @Author 方舟
     * @Date 2021/8/18 19:19:21
    **/
    @PostMapping("/getPrintEquipment")
    @ResponseBody
    public AjaxResult getPrintEquipment(ProduceOrder produceOrder)
    {
        List<ConfigEquipment> result = produceOrderService.getPrintEquipment(produceOrder);
        return AjaxResult.success(result);
    }

    /**
     * 批量加工序
     * @Author 方舟
     * @Date 2021/8/22 21:53:34
     **/
    @PostMapping("/addProcessBatch")
    @ResponseBody
    public AjaxResult addProcessBatch(ProduceOrder produceOrder) throws Exception
    {
        ProduceOrder result = produceOrderService.addProcessBatch(produceOrder);
        return AjaxResult.success(result);
    }

    /**
     * 打印
     * @Author 方舟
     * @Date 2021/6/1 12:38:09
     **/
    @GetMapping("/openCutInput/{id}")
    public String openCutInput(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProduceOrder produceOrder = new ProduceOrder();
        produceOrder = produceOrderService.selectProduceOrderById(id);
        mmap.put("produceOrder", produceOrder);
        return prefix + "/selectCutInput";
    }

    /**
     * 修改保存施工单
     */
    @PostMapping("/saveCutRequire")
    @ResponseBody
    public AjaxResult saveCutRequire(ProduceOrder produceOrder)
    {
        return toAjax(produceOrderService.saveCutRequire(produceOrder));
    }

    /**
     * 工单翻单
     * @param produceOrder
     * @return
     */
    @PostMapping("/copyProduceOrder")
    @ResponseBody
    public AjaxResult copyProduceOrder(ProduceOrder produceOrder){
        ProduceOrder result = produceOrderService.copyProduceOrder(produceOrder);
        return AjaxResult.success("复制成功",result);
    }

    /**
     * 修改停机
     */
    @GetMapping("/extraCostPage/{id}")
    public String pausePage(@PathVariable("id") Long id, ModelMap mmap) {
        ProduceOrder produceOrder = produceOrderService.selectProduceOrderById(id);
        mmap.put("produceOrder", produceOrder);
        return prefix + "/extraCost";
    }
    /**
     * 选择部件
     */
    @GetMapping("/chooseParts/{id}")
    public String chooseParts(@PathVariable("id") Long id, ModelMap mmap) {
        ProduceOrder param = new ProduceOrder();
        param.setProduceOrderId(id);
        List<ProduceOrder> produceOrderList = produceOrderService.selectProduceOrderPartList(param);
        String ids = "";
        String names = "";
        for (int i=0;i<produceOrderList.size();i++){
            if(i!=0){
                ids += ",";
                names += ",";
            }
            ids += produceOrderList.get(i).getId();
            names += produceOrderList.get(i).getProductName();
        }
        ProduceOrder result = new ProduceOrder();
        result.setProduceOrderId(id);
        result.setRzyValue1(ids);
        result.setRzyValue2(names);
        mmap.put("produceOrder", result);
        return prefix + "/markupChooseParts";
    }

    /**
     * 拼版
     * @param produceOrder
     * @return
     */
    @PostMapping("/setMarkup")
    @ResponseBody
    public AjaxResult setMarkup(ProduceOrder produceOrder){
        ProduceOrder result = produceOrderService.setMarkup(produceOrder);
        return AjaxResult.success(result);
    }

    /**
     * 新增部件
     */
    @GetMapping("/showAddPart/{type}/{id}")
    public String showAddPart(@PathVariable("id") Long id, @PathVariable("type") String type, ModelMap mmap) {
        ProduceOrder produceOrder = produceOrderService.selectProduceOrderById(id);
        produceOrder.setProductType(type);
        produceOrder.setId(id);
        produceOrder.setProductParentName(produceOrder.getProductName());
        mmap.put("produceOrder", produceOrder);
        return prefix + "/addPart";
    }

    /**
     * 新增部件
     * @param produceOrder
     * @return
     */
    @PostMapping("/addPart")
    @ResponseBody
    public AjaxResult addPart(ProduceOrder produceOrder){
        ProduceOrder result = produceOrderService.insertProduceOrderPart(produceOrder);
        return AjaxResult.success(result);
    }

    /**
     * 删除部件
     */
    @GetMapping("/showDeletePart/{id}")
    public String showDeletePart(@PathVariable("id") Long id, ModelMap mmap) {
        ProduceOrder param = new ProduceOrder();
        param.setProduceOrderId(id);
        List<ProduceOrder> produceOrderList = produceOrderService.selectProduceOrderPartList(param);
        String ids = "";
        String names = "";
        for (int i=0;i<produceOrderList.size();i++){
            if(i!=0){
                ids += ",";
                names += ",";
            }
            ids += produceOrderList.get(i).getId();
            names += produceOrderList.get(i).getProductName();
        }
        ProduceOrder result = new ProduceOrder();
        result.setProduceOrderId(id);
        result.setRzyValue1(ids);
        result.setRzyValue2(names);
        mmap.put("produceOrder", result);
        return prefix + "/deletePart";
    }

    /**
     * 删除部件
     * @param produceOrder
     * @return
     */
    @PostMapping("/deletePart")
    @ResponseBody
    public AjaxResult deletePart(ProduceOrder produceOrder){
        ProduceOrder result = produceOrderService.deleteProduceOrderPartByPartIds(produceOrder);
        return AjaxResult.success(result);
    }

}
