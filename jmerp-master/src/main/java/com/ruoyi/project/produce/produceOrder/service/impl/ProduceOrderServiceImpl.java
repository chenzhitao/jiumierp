package com.ruoyi.project.produce.produceOrder.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.FormulaUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configEquipment.domain.ConfigEquipment;
import com.ruoyi.project.config.configEquipment.mapper.ConfigEquipmentMapper;
import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configMaterials.mapper.ConfigMaterialsMapper;
import com.ruoyi.project.config.configProcess.domain.ConfigProcess;
import com.ruoyi.project.config.configProcess.mapper.ConfigProcessMapper;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.config.configProduct.mapper.ConfigProductMapper;
import com.ruoyi.project.config.configProductionTemplate.domain.ConfigProductionTemplate;
import com.ruoyi.project.config.configProductionTemplateMaterials.domain.ConfigProductionTemplateMaterials;
import com.ruoyi.project.config.configProductionTemplateMaterials.mapper.ConfigProductionTemplateMaterialsMapper;
import com.ruoyi.project.config.configProductionTemplateProcess.domain.ConfigProductionTemplateProcess;
import com.ruoyi.project.config.configProductionTemplateProcess.mapper.ConfigProductionTemplateProcessMapper;
import com.ruoyi.project.produce.produceOrderMaterials.domain.ProduceOrderMaterials;
import com.ruoyi.project.produce.produceOrderMaterials.mapper.ProduceOrderMaterialsMapper;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.mapper.ProduceOrderProcessMapper;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrderMaterials.domain.SaleOrderMaterials;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleOrderProduct.service.ISaleOrderProductService;
import com.ruoyi.project.sale.saleQuotationMultDetail.domain.SaleQuotationMultDetail;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
import javafx.scene.paint.Material;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.produce.produceOrder.mapper.ProduceOrderMapper;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceOrder.service.IProduceOrderService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 施工单Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-30
 */
@Service
public class ProduceOrderServiceImpl implements IProduceOrderService 
{
    @Autowired
    private ProduceOrderMapper produceOrderMapper;
    @Autowired
    private ProduceOrderProcessMapper produceOrderProcessMapper;
    @Autowired
    private ProduceOrderMaterialsMapper produceOrderMaterialsMapper;
    @Autowired
    private ConfigProductionTemplateMaterialsMapper configProductionTemplateMaterialsMapper;
    @Autowired
    private ConfigProductionTemplateProcessMapper configProductionTemplateProcessMapper;
    @Autowired
    private ConfigProductMapper configProductMapper;
    @Autowired
    private ConfigMaterialsMapper configMaterialsMapper;
    @Autowired
    private ConfigEquipmentMapper configEquipmentMapper;
    @Autowired
    private IWarehouseRecordService warehouseRecordService;
    @Autowired
    private ISaleOrderProductService saleOrderProductService;
    @Autowired
    private ConfigProcessMapper configProcessMapper;

    /**
     * 查询施工单
     * 
     * @param id 施工单ID
     * @return 施工单
     */
    @Override
    public ProduceOrder selectProduceOrderById(Long id)
    {
        return produceOrderMapper.selectProduceOrderById(id);
    }
    @Override
    public ProduceOrder selectProduceOrderPartById(Long id)
    {
        return produceOrderMapper.selectProduceOrderPartById(id);
    }

    /**
     * 查询施工单列表
     * 
     * @param produceOrder 施工单
     * @return 施工单
     */
    @Override
    public List<ProduceOrder> selectProduceOrderList(ProduceOrder produceOrder)
    {
        List<ProduceOrder> list = produceOrderMapper.selectProduceOrderList(produceOrder);
        for (int i=0;i<list.size();i++){
            String isInbound = produceOrderMapper.selectProduceOrderIsInbound(list.get(i));
            list.get(i).setIsInbound(isInbound);
        }
        return list;
    }
    @Override
    public List<ProduceOrder> selectProduceOrderPartList(ProduceOrder produceOrder)
    {
        return produceOrderMapper.selectProduceOrderPartList(produceOrder);
    }

    /**
     * 新增施工单
     * 
     * @param produceOrder 施工单
     * @return 结果
     */
    @Override
    public int insertProduceOrder(ProduceOrder produceOrder)
    {
        produceOrder.setCreateBy(ShiroUtils.getSysUser().getUserName());
        produceOrder.setCreateTime(DateUtils.getNowDate());
        produceOrder.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        produceOrder.setUpdateTime(DateUtils.getNowDate());
        return produceOrderMapper.insertProduceOrder(produceOrder);
    }

    /**
     * 修改施工单
     * 
     * @param produceOrder 施工单
     * @return 结果
     */
    @Override
    public int updateProduceOrder(ProduceOrder produceOrder)
    {
        produceOrder.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        produceOrder.setUpdateTime(DateUtils.getNowDate());
        //生产领料
        warehouseChange(produceOrder.getRzyValue1(),produceOrder.getId());
        //审批后就在生产中
        updateStatus(produceOrder);
        //处理外发
        setupOutsource(produceOrder);
        //审批
        approve(produceOrder);
        return produceOrderMapper.updateProduceOrder(produceOrder);
    }

    @Override
    public int updateProduceOrderPart(ProduceOrder produceOrder)
    {
        return produceOrderMapper.updateProduceOrderPart(produceOrder);
    }

    /**
     * 删除施工单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProduceOrderByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            ProduceOrder param = produceOrderMapper.selectProduceOrderById(Long.parseLong(arr[i]));
            /*if("draft".equals(param.getStatus())){
                result = produceOrderMapper.deleteProduceOrderById(Long.parseLong(arr[i]));
                produceOrderMapper.deleteProduceOrderProcessById(Long.parseLong(arr[i]));
                produceOrderMapper.deleteProduceOrderMaterialsById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = produceOrderMapper.updateProduceOrder(param);
            }*/
            //20211031改不直接删除
            param.setId(Long.parseLong(arr[i]));
            param.setStatus("delete");
            result = produceOrderMapper.updateProduceOrder(param);
        }
        return result;
        //return produceOrderMapper.deleteProduceOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除施工单信息
     * 
     * @param id 施工单ID
     * @return 结果
     */
    @Override
    public int deleteProduceOrderById(Long id)
    {
        int result = 0;
        ProduceOrder param = produceOrderMapper.selectProduceOrderById(id);
        /*if("draft".equals(param.getStatus())){
            result = produceOrderMapper.deleteProduceOrderById(id);
            produceOrderMapper.deleteProduceOrderProcessById(id);
            produceOrderMapper.deleteProduceOrderMaterialsById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = produceOrderMapper.updateProduceOrder(param);
        }*/
        //20211031改不直接删除
        param.setId(id);
        param.setStatus("delete");
        result = produceOrderMapper.updateProduceOrder(param);
        return result;
        //return produceOrderMapper.deleteProduceOrderById(id);
    }


    /**
     * 加载工艺卡里的工序和材料
     * @Author 方舟
     * @Date 2021/5/5 9:44:33
     **/
    @Override
    public ProduceOrder loadProcessMaterials(ProduceOrder produceOrder){
        ProduceOrder resultVO = new ProduceOrder();
        produceOrderMapper.updateProduceOrder(produceOrder);
        //找顺序
        ProduceOrderProcess paramProcess = new ProduceOrderProcess();
        paramProcess.setProduceOrderId(produceOrder.getId());
        List<ProduceOrderProcess> orderlist = produceOrderProcessMapper.selectProduceOrderProcessList(paramProcess);
        Integer processOrder = orderlist.size() + 1;

        setupProcessMaterialsByTemplate(processOrder,produceOrder.getProductionTemplateId(),produceOrder.getId());

        return resultVO;
    }

    /**
     * 根据施工单ID和工艺卡ID添加工序和材料
     * @Author 方舟
     * @Date 2021/5/6 16:53:09
    **/
    private void setupProcessMaterialsByTemplate(Integer processOrder,Long productionTemplateId,Long produceOrderId){
        ProduceOrder produceOrder = produceOrderMapper.selectProduceOrderById(produceOrderId);
        //找到工艺卡工序
        ConfigProductionTemplateProcess configProductionTemplateProcess = new ConfigProductionTemplateProcess();
        configProductionTemplateProcess.setProductionTemplateId(productionTemplateId);
        List<ConfigProductionTemplateProcess> processList = configProductionTemplateProcessMapper.selectConfigProductionTemplateProcessList(configProductionTemplateProcess);
        for (int i=0;i<processList.size();i++){
            ProduceOrderProcess saveProcessVO = new ProduceOrderProcess();
            BeanUtils.copyProperties(produceOrder, saveProcessVO);
            ConfigProductionTemplateProcess processVO = processList.get(i);
            BeanUtils.copyProperties(processVO, saveProcessVO);
            saveProcessVO.setProduceOrderId(produceOrderId);
            saveProcessVO.setProcessOrder(processOrder);
            saveProcessVO.setProductionTemplateProcessId(processVO.getId());
            produceOrderProcessMapper.insertProduceOrderProcess(saveProcessVO);
            processOrder++;
        }
        //复制材料
        ConfigProductionTemplateMaterials configProductionTemplateMaterials = new ConfigProductionTemplateMaterials();
        configProductionTemplateMaterials.setProductionTemplateId(productionTemplateId);
        List<ConfigProductionTemplateMaterials> materialsList = configProductionTemplateMaterialsMapper.selectConfigProductionTemplateMaterialsList(configProductionTemplateMaterials);
        for (int i=0;i<materialsList.size();i++){
            ProduceOrderMaterials saveMaterialsVO = new ProduceOrderMaterials();
            ConfigProductionTemplateMaterials materialsVO = materialsList.get(i);
            BeanUtils.copyProperties(materialsVO, saveMaterialsVO);
            saveMaterialsVO.setProduceOrderId(produceOrderId);
            produceOrderMaterialsMapper.insertProduceOrderMaterials(saveMaterialsVO);
        }
    }


    /**
     * 加载工艺卡里的工序和材料
     * @Author 方舟
     * @Date 2021/5/5 9:44:33
     **/
    @Override
    public ProduceOrder areaCalculator(ProduceOrder produceOrder){
        ProduceOrder resultVO = new ProduceOrder();
        ConfigProduct configProduct = configProductMapper.selectConfigProductById(produceOrder.getProductId());
        if(null!=configProduct){
            //BeanUtils.copyProperties(configProduct, resultVO);
            resultVO.setSizeLong(configProduct.getSizeLong());
            resultVO.setSizeWidth(configProduct.getSizeWidth());
            resultVO.setSizeHeight(configProduct.getSizeHeight());
            resultVO.setOpensizeLong(configProduct.getOpensizeLong());
            resultVO.setOpensizeWidth(configProduct.getOpensizeWidth());
            resultVO.setArea(configProduct.getArea());
            if(configProduct.getArea().intValue() == 0){
                BigDecimal area = new BigDecimal(0);
                area = (new BigDecimal(configProduct.getOpensizeLong()).multiply(new BigDecimal(configProduct.getOpensizeWidth()))).divide(new BigDecimal(1000000),6,BigDecimal.ROUND_UP);
                resultVO.setArea(area);
            }
            resultVO.setId(produceOrder.getId());
            updateProduceOrder(resultVO);
        }
        return resultVO;
    }


    /**
     * 找到关联的材料
     * @Author 方舟
     * @Date 2021/8/21 20:48:29
    **/
    private ConfigMaterials getMaterialsByLinkProcess(List<ProduceOrderMaterials> materialsList,ProduceOrderProcess popVO){
        ConfigMaterials materials = new ConfigMaterials();
        boolean getFlag = false;
        for (int i=0;i<materialsList.size();i++){
            if(null!=materialsList.get(i).getProcessId()&&materialsList.get(i).getProcessId().equals(popVO.getProcessId())){
                materials = configMaterialsMapper.selectConfigMaterialsById(materialsList.get(i).getMaterialsId());
                getFlag = true;
            }
        }
        if(!getFlag){
            materials = null;
        }
        return materials;
    }

    /**
     * 根据倍率,损耗计算投入产出
     * @Author 方舟
     * @Date 2021/8/21 19:22:15
    **/
    private void inoutCountMain(ProduceOrder produceOrder,List<ProduceOrderProcess> processList,Integer realQty){

    }

    /**
     * 根据倍率,损耗计算投入产出
     * @Author 方舟
     * @Date 2021/8/21 19:22:15
    **/
    private void inoutCountMaterials(ProduceOrder produceOrder,List<ProduceOrderProcess> processList,Integer realQty){

    }

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 16:45:19
     **/
    private void warehouseChange(String editType,Long produceOrderId){
        if(!StringUtils.isEmpty(editType)){
            ProduceOrder produceOrder = produceOrderMapper.selectProduceOrderById(produceOrderId);
            ProduceOrderMaterials produceOrderMaterials = new ProduceOrderMaterials();
            produceOrderMaterials.setProduceOrderId(produceOrderId);
            List<ProduceOrderMaterials> list = produceOrderMaterialsMapper.selectProduceOrderMaterialsList(produceOrderMaterials);
            for (int i=0;i<list.size();i++){
                ProduceOrderMaterials materials = list.get(i);
                if("approve".equals(editType)){
                    //客户带料入库
                    warehouseRecordService.materialsInbound("SCLL",materials.getWarehouseId(),(0-materials.getQty()),materials.getMaterialsId(),"");
                }else if("unapprove".equals(editType)){
                    //客户带料出库
                    warehouseRecordService.materialsInbound("SCLL",materials.getWarehouseId(),materials.getQty(),materials.getMaterialsId(),"反审退回");
                }
            }
        }
    }

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 16:45:19
     **/
    private void updateStatus(ProduceOrder produceOrder){
        String editType = produceOrder.getRzyValue1();
        if(!StringUtils.isEmpty(editType)){
            String status = "draft";
            if("approve".equals(editType)){
                status = "submit";
            }else if("unapprove".equals(editType)){
                status = "draft";
            }
            produceOrder.setProduceStatus(status);
            ProduceOrderProcess produceOrderProcess = new ProduceOrderProcess();
            produceOrderProcess.setProduceOrderId(produceOrder.getId());
            produceOrderProcess.setProduceStatus(status);
            produceOrderProcessMapper.setupStatusByProduceOrderId(produceOrderProcess);
        }
    }

    /**
     * 如果是整单外发将工序全部设置外发
     * @Author 方舟
     * @Date 2021/5/18 13:18:18
    **/
    private void setupOutsource(ProduceOrder produceOrder){
        if(!StringUtils.isEmpty(produceOrder.getIsOutsource())&&"Y".equals(produceOrder.getIsOutsource())){
            ProduceOrderProcess paramProcess = new ProduceOrderProcess();
            paramProcess.setProduceOrderId(produceOrder.getId());
            List<ProduceOrderProcess> processList = produceOrderProcessMapper.selectProduceOrderProcessList(paramProcess);
            for (int i=0;i<processList.size();i++){
                ProduceOrderProcess produceOrderProcess = processList.get(i);
                produceOrderProcess.setIsOutsource("Y");
                produceOrderProcessMapper.updateProduceOrderProcess(produceOrderProcess);
            }
        }
    }

    /**
     * 审批
     * @Author 方舟
     * @Date 2021/8/13 17:12:53
    **/
    private void approve(ProduceOrder produceOrder){
        if(null!=produceOrder.getRzyValue1()&&"approve".equals(produceOrder.getRzyValue1())){
            produceOrder.setApprovalTime(DateUtils.getNowDate());
            produceOrder.setApprover(ShiroUtils.getSysUser().getUserName());
        }
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public ProduceOrder createProduceOrder(ProduceOrder produceOrder){
        String[] arr = Convert.toStrArray(produceOrder.getIds());
        Long returnId = -1L;
        for (int i=0;i<arr.length;i++){
            ProduceOrder saveVO = new ProduceOrder();
            Long spid = Long.parseLong(arr[i]);
            SaleOrderProduct saleOrderProduct = saleOrderProductService.selectSaleOrderProductById(spid);
            Long productid = saleOrderProduct.getProductId();
            ConfigProduct productVO = configProductMapper.selectConfigProductById(productid);
            BeanUtils.copyProperties(productVO, saveVO);
            BeanUtils.copyProperties(saleOrderProduct, saveVO);
            saveVO.setCustomerOrder(saleOrderProduct.getCustomerNo());
            saveVO.setProductType(productVO.getProductType());
            saveVO.setSaleOrderProductId(spid);
            saveVO.setIsComplete("N");
            saveVO.setProduceStatus("draft");
            saveVO.setProduceOrderType("normal");
            saveVO.setStatus("draft");
            saveVO.setSizeLong(productVO.getSizeLong());
            saveVO.setSizeWidth(productVO.getSizeWidth());
            saveVO.setSizeHeight(productVO.getSizeHeight());
            //
            saveVO.setProduceDate(DateUtils.getNowDate());
            insertProduceOrder(saveVO);
            returnId = saveVO.getId();
            //如果是多部件，新增子表
            if(productVO.getProductLevel()!=null&&"F".equals(productVO.getProductLevel())){
                ConfigProduct parentProduct = new ConfigProduct();
                parentProduct.setParentId(productVO.getId());
                parentProduct.setProductLevel("C");
                List<ConfigProduct> childProductList = configProductMapper.selectConfigProductList(parentProduct);
                for (int j=0;j<childProductList.size();j++){
                    ProduceOrder childProductProduceOrder = new ProduceOrder();
                    BeanUtils.copyProperties(childProductList.get(j), childProductProduceOrder);
                    childProductProduceOrder.setProduceOrderId(returnId);
                    childProductProduceOrder.setProductParentName(productVO.getProductName());
                    childProductProduceOrder.setProductId(childProductList.get(j).getId());
                    produceOrderMapper.insertProduceOrderPart(childProductProduceOrder);
                }
            }
            //如果有工艺卡,添加工序和材料
            if(null!=productVO.getProductionTemplateId()){
                setupProcessMaterialsByTemplate(1,productVO.getProductionTemplateId(),returnId);
            }
        }
        if(arr.length>1){
            produceOrder.setId(-1L);
        }else{
            produceOrder.setId(returnId);
        }
        return produceOrder;
    }

    /**
     * 产品入库
     * @Author 方舟
     * @Date 2021/5/18 12:13:10
     **/
    @Override
    public ProduceOrder inbound(ProduceOrder produceOrder){
        ProduceOrder orderVO = produceOrderMapper.selectProduceOrderById(produceOrder.getId());
        orderVO.setProduceStatus("warehouse");
        orderVO.setIsComplete("Y");
        updateProduceOrder(orderVO);
        warehouseRecordService.productInbound("SCRK",produceOrder.getWarehouseId(),orderVO.getQty(),orderVO.getProductId(),"");
        return produceOrder;
    }

    /**
     * 获取印刷设备
     * @Author 方舟
     * @Date 2021/8/18 19:19:21
     **/
    @Override
    public List<ConfigEquipment> getPrintEquipment(ProduceOrder produceOrder){
        List<ConfigEquipment> configEquipmentList = configEquipmentMapper.selectConfigEquipmentsByProduceOrderId(produceOrder);
        return configEquipmentList;
    }

    /**
     * 批量加工序
     * @Author 方舟
     * @Date 2021/8/22 21:53:34
     **/
    @Override
    public ProduceOrder addProcessBatch(ProduceOrder produceOrder)
    {
        String[] arr = produceOrder.getIds().split(",");
        ProduceOrderProcess param = new ProduceOrderProcess();
        param.setProduceOrderId(produceOrder.getId());
        produceOrder = produceOrderMapper.selectProduceOrderById(produceOrder.getId());
        List<ProduceOrderProcess> list = produceOrderProcessMapper.selectProduceOrderProcessList(param);
        for (int i=0;i<arr.length;i++){
            Long processId = Long.parseLong(arr[i]);
            ProduceOrderProcess processVO = new ProduceOrderProcess();
            ConfigProcess configProcess = configProcessMapper.selectConfigProcessById(processId);
            BeanUtils.copyProperties(configProcess, processVO);
            BeanUtils.copyProperties(produceOrder, processVO);
            processVO.setProduceOrderId(produceOrder.getId());
            processVO.setProcessId(processId);
            processVO.setProcessOrder(list.size()+i+1);
            processVO.setIsOutsource("N");
            processVO.setIsWithMaterials("N");
            processVO.setIsPrint("Y");
            processVO.setIsIncount("Y");
            processVO.setValuationType("price");
            produceOrderProcessMapper.insertProduceOrderProcess(processVO);
        }
        return produceOrder;
    }

    /**
     * 计算工序投入产出数
     * @Author 方舟
     * @Date 2021/5/6 10:31:30
     **/
    @Override
    public void inoutCountYS(ProduceOrder produceOrder, List<ProduceOrderProcess> processList, List<ProduceOrderMaterials> materialsList) {
        //产品的展开尺寸
        Integer productOpenLong = produceOrder.getOpensizeLong();
        Integer productOpenWidth = produceOrder.getOpensizeWidth();
        boolean pinFlag = true;
        //1.先计算印刷工序,产品展开尺寸
        for(int i=0;i<processList.size();i++){
            if("Y".equals(processList.get(i).getIsIncount())&&"ys".equals(processList.get(i).getProcessType())){
                //需要计算的印刷工序
                if(null!=processList.get(i).getEquipmentId()){
                    //有设备
                    ConfigEquipment equipment = configEquipmentMapper.selectConfigEquipmentById(processList.get(i).getEquipmentId());
                    Map map = FormulaUtils.openMaterialsSizeCalculate(productOpenLong,productOpenWidth,equipment.getMaxWorkLong(),equipment.getMaxWorkWidth());
                    BigDecimal times = new BigDecimal((Integer) map.get("times"));
                    //拼版
                    if(pinFlag){
                        pinFlag = false;
                        //拼版数只填写一次
                        if(times.equals(0)){
                            produceOrder.setPuzzleQty(1);
                        }else{
                            produceOrder.setPuzzleQty(times.intValue());
                        }
                        produceOrderMapper.updateProduceOrder(produceOrder);
                    }
                    //开料尺寸
                    ProduceOrderProcess popVO = new ProduceOrderProcess();
                    popVO.setId(processList.get(i).getId());
                    if(times.equals(0)){
                        popVO.setCuttingSizeLong(0);
                        popVO.setCuttingSizeWidth(0);
                    }else{
                        popVO.setCuttingSizeLong((Integer) map.get("openLong") + produceOrder.getBleedLongLeft() + produceOrder.getBleedLongRight());
                        popVO.setCuttingSizeWidth((Integer) map.get("openWidth") + produceOrder.getBleedWidthLeft() + produceOrder.getBleedWidthRight());
                    }
                    produceOrderProcessMapper.updateProduceOrderProcess(popVO);
                }
            }
        }
    }

    @Override
    public void inoutCountQZ(ProduceOrder produceOrder, List<ProduceOrderProcess> processList, List<ProduceOrderMaterials> materialsList) {
        //2.印刷工序开料尺寸和原材料尺寸进行计算，计算出切纸开料尺寸
        for(int i=0;i<processList.size();i++){
            //找切纸
            if("Y".equals(processList.get(i).getIsIncount())&&processList.get(i).getProcessName().indexOf("切纸")>-1){
                //找下一个印刷工序的开料尺寸
                Integer ysCutSizeLong = 0;
                Integer ysCutSizeWidth = 0;
                for(int a=i;a<processList.size();a++){
                    if("ys".equals(processList.get(a).getProcessType())){
                        ProduceOrderProcess process = produceOrderProcessMapper.selectProduceOrderProcessById(processList.get(a).getId());
                        ysCutSizeLong = process.getCuttingSizeLong();
                        ysCutSizeWidth = process.getCuttingSizeWidth();
                        break;
                    }
                }
                //找关联材料尺寸
                Integer materialsCutSizeLong = 0;
                Integer materialsCutSizeWidth = 0;
                for(int b=0;b<materialsList.size();b++){
                    if(materialsList.get(b).getProcessId().equals(processList.get(i).getProcessId())){
                        ConfigMaterials materials = configMaterialsMapper.selectConfigMaterialsById(materialsList.get(b).getMaterialsId());
                        materialsCutSizeLong = materials.getSizeLong();
                        materialsCutSizeWidth = materials.getSizeWidth();
                        break;
                    }
                }
                //如果都有数据,比较
                if(ysCutSizeLong>0&&ysCutSizeWidth>0&&materialsCutSizeLong>0&&materialsCutSizeWidth>0){
                    Map map = FormulaUtils.openMaterialsSizeCalculate(ysCutSizeLong,ysCutSizeWidth,materialsCutSizeLong,materialsCutSizeWidth);
                    BigDecimal times = new BigDecimal((Integer) map.get("times"));
                    if(!times.equals(0)){
                        ProduceOrderProcess saveVO = new ProduceOrderProcess();
                        saveVO.setId(processList.get(i).getId());
                        saveVO.setTimes(times);
                        saveVO.setCuttingSizeLong((Integer) map.get("openLong"));
                        saveVO.setCuttingSizeWidth((Integer) map.get("openWidth"));
                        produceOrderProcessMapper.updateProduceOrderProcess(saveVO);
                    }
                }
            }
        }
    }

    @Override
    public void inoutCountMQ(ProduceOrder produceOrder, List<ProduceOrderProcess> processList, List<ProduceOrderMaterials> materialsList) {
        //找模切
        for(int i=0;i<processList.size();i++){
            if("Y".equals(processList.get(i).getIsIncount())&&"mq".equals(processList.get(i).getProcessType())){
                //找上面的印刷
                Integer ysCutSizeLong = 0;
                Integer ysCutSizeWidth = 0;
                for(int a=i;a>0;a--){
                    if("Y".equals(processList.get(a).getIsIncount())&&"ys".equals(processList.get(a).getProcessType())){
                        ProduceOrderProcess process = produceOrderProcessMapper.selectProduceOrderProcessById(processList.get(a).getId());
                        ysCutSizeLong = process.getCuttingSizeLong();
                        ysCutSizeWidth = process.getCuttingSizeWidth();
                        break;
                    }
                }
                if(ysCutSizeLong>0&&ysCutSizeWidth>0){
                    //找设备尺寸
                    if(null!=processList.get(i).getEquipmentId()){
                        //有设备
                        ConfigEquipment equipment = configEquipmentMapper.selectConfigEquipmentById(processList.get(i).getEquipmentId());
                        Map map = FormulaUtils.openMaterialsSizeCalculate(ysCutSizeLong,ysCutSizeLong,equipment.getMaxWorkLong(),equipment.getMaxWorkWidth());
                        BigDecimal times = new BigDecimal((Integer) map.get("times"));
                        //如果印刷工序开料尺寸小于模切工序关联的设备上机尺寸，那么模切工序倍率取印刷工序拼版数
                        if(times.intValue()>0){
                            ProduceOrderProcess mqProcess = new ProduceOrderProcess();
                            mqProcess.setId(processList.get(i).getId());
                            mqProcess.setTimes(new BigDecimal(produceOrder.getPuzzleQty()));
                            produceOrderProcessMapper.updateProduceOrderProcess(mqProcess);
                        }else{
                            Integer qzCutSizeLong = 0;
                            Integer qzCutSizeWidth = 0;
                            boolean hasQZ = false;
                            //模切前面的印刷后面的切纸
                            for(int a=i;a>0;a--){
                                if("ys".equals(processList.get(a).getProcessType())){
                                    //后面的切纸
                                    for(int b=a;b<processList.size();b++){
                                        if("Y".equals(processList.get(b).getIsIncount())&&processList.get(b).getProcessName().indexOf("切纸")>-1){
                                            ProduceOrderProcess process = produceOrderProcessMapper.selectProduceOrderProcessById(processList.get(b).getId());
                                            qzCutSizeLong = processList.get(b).getCuttingSizeLong();
                                            qzCutSizeWidth = processList.get(b).getCuttingSizeWidth();
                                            hasQZ = true;
                                        }
                                    }
                                }
                            }
                            if(hasQZ){
                                if(null!=processList.get(i).getEquipmentId()){
                                    ConfigEquipment equipment2 = configEquipmentMapper.selectConfigEquipmentById(processList.get(i).getEquipmentId());
                                    //模切上机尺寸和切纸（裁切）工序开料尺寸进行计算，计算出模切工序的工序倍率；
                                    if(qzCutSizeLong>0&&qzCutSizeWidth>0){
                                        Map map2 = FormulaUtils.openMaterialsSizeCalculate(equipment2.getMaxWorkLong(),equipment2.getMaxWorkWidth(),qzCutSizeLong,qzCutSizeWidth);
                                        BigDecimal times2 = new BigDecimal((Integer) map2.get("times"));
                                        ProduceOrderProcess mqProcess = new ProduceOrderProcess();
                                        mqProcess.setId(processList.get(i).getId());
                                        mqProcess.setTimes(times2);
                                        produceOrderProcessMapper.updateProduceOrderProcess(mqProcess);
                                    }
                                }
                            }else{
                                //模切工序前面印刷工序后面没有切纸（裁切）工序，那么模切工序倍率默认成1
                                ProduceOrderProcess mqProcess = new ProduceOrderProcess();
                                mqProcess.setId(processList.get(i).getId());
                                mqProcess.setTimes(new BigDecimal(1));
                                produceOrderProcessMapper.updateProduceOrderProcess(mqProcess);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void inoutCountIO(ProduceOrder produceOrder, List<ProduceOrderProcess> processList, Integer realQty) {
        Integer inqty = 0;
        Integer outqty = 0;
        int recount = 0;
        for (int i=processList.size()-1;i>=0;i--){
            //准备数据
            ProduceOrderProcess processVO = processList.get(i);
            if("N".equals(processVO.getIsIncount())){
                continue;
            }
            String isIncount = processVO.getIsIncount();
            BigDecimal times = processVO.getTimes();
            Integer lossQty = processVO.getLossQty();
            BigDecimal lossRate = processVO.getLossRate().multiply(new BigDecimal(0.01));
            //计算
            ProduceOrderProcess saveVO = new ProduceOrderProcess();
            saveVO.setId(processVO.getId());
            if(!StringUtils.isEmpty(isIncount)&&"Y".equals(isIncount)){
                if(recount==0){
                    outqty = realQty;
                }else{
                    outqty = inqty;
                }
                SaleQuotationMultDetail calculateVO = FormulaUtils.getInqtyByOutqty(outqty,lossQty,lossRate,times);
                inqty = calculateVO.getInQty();
                saveVO.setInQty(inqty);
                saveVO.setOutQty(outqty);
            }else{
                //不计算的直接放个生产数量
                Integer tempInQty = FormulaUtils.getInqtyByOutqty(realQty,lossQty,lossRate,times).getInQty();
                saveVO.setOutQty(realQty);
                saveVO.setInQty(tempInQty);
            }
            produceOrderProcessMapper.updateProduceOrderProcess(saveVO);
            recount++;
        }
    }

    @Override
    public void inoutCountCLQ(ProduceOrder produceOrder, List<ProduceOrderProcess> processList, Integer realQty) {
        ProduceOrderMaterials paramsMaterials = new ProduceOrderMaterials();
        paramsMaterials.setProduceOrderId(produceOrder.getId());
        List<ProduceOrderMaterials> materialsList = produceOrderMaterialsMapper.selectProduceOrderMaterialsList(paramsMaterials);
        for (int i=0;i<materialsList.size();i++){
            ProduceOrderMaterials materialsVO = materialsList.get(i);
            String isGetProcessQty = materialsVO.getIsGetProcessQty();
            Long processId = materialsVO.getProcessId();
            //基础数量
            Integer mqty = realQty;
            if(!StringUtils.isEmpty(isGetProcessQty)&&"Y".equals(isGetProcessQty)&&null!=processId){
                mqty = 0;
                for (int j=0;j<processList.size();j++){
                    if(processList.get(j).getProcessId().equals(processId)){
                        mqty += processList.get(j).getInQty();
                    }
                }
            }
            //倍率损耗
            BigDecimal lossRate = materialsVO.getLossRate().multiply(new BigDecimal(0.01));
            mqty = new BigDecimal(mqty).multiply(lossRate).multiply(materialsVO.getBaseRate()).setScale(0,BigDecimal.ROUND_UP).intValue() + materialsVO.getLossQty();
            materialsVO.setQty(mqty);
            produceOrderMaterialsMapper.updateProduceOrderMaterials(materialsVO);
        }
    }

    /**
     * 保存开料要求
     * @param produceOrder
     * @return
     */
    @Override
    public int saveCutRequire(ProduceOrder produceOrder){
        Integer res = 0;
        String require = "";
        Long processId = -1L;
        if (!StringUtils.isEmpty(produceOrder.getOpenCutRequire())){
            if("other".equals(produceOrder.getOpenCutRequire())){
                require = produceOrder.getOpenCutRequireOther();
            }else{
                require = produceOrder.getOpenCutRequire();
            }
            ProduceOrderProcess produceOrderProcess = new ProduceOrderProcess();
            produceOrderProcess.setProduceOrderId(produceOrder.getId());
            List<ProduceOrderProcess> processList = produceOrderProcessMapper.selectProduceOrderProcessList(produceOrderProcess);
            for (int i=0;i<processList.size();i++){
                if(processList.get(i).getProcessName().indexOf("切纸")>-1){
                    processId = processList.get(i).getId();
                    break;
                }
            }
            if(processId>-1L){
                ProduceOrderProcess saveVO = new ProduceOrderProcess();
                saveVO.setRequirements(require);
                saveVO.setId(processId);
                produceOrderProcessMapper.updateProduceOrderProcess(saveVO);
            }
        }
        return res;
    }

    /**
     * 根据销售订单翻单
     * @param orgSaleOrderId
     * @param newSaleOrderId
     * @return
     */
    @Override
    public void copyProduceOrderBySaleOrder(Long orgSaleOrderId,Long newSaleOrderId){
        ProduceOrder paramVO = new ProduceOrder();
        paramVO.setSaleOrderId(orgSaleOrderId);
        List<ProduceOrder> orgProduceOrderList = produceOrderMapper.selectProduceOrderList(paramVO);
        for (int i=0;i<orgProduceOrderList.size();i++){
            ProduceOrder orgVO = orgProduceOrderList.get(i);
            copyProduceOrder(orgVO);
        }
    }

    /**
     * 工单翻单
     * @param orgProduceOrder
     * @return
     */
    @Override
    public ProduceOrder copyProduceOrder(ProduceOrder orgProduceOrder){
        //复制头
        Long orgId = orgProduceOrder.getId();
        ProduceOrder headerVO = produceOrderMapper.selectProduceOrderById(orgId);
        headerVO.setIsComplete("N");
        headerVO.setProduceStatus("draft");
        headerVO.setProduceOrderType("normal");
        headerVO.setStatus("draft");
        headerVO.setProduceDate(DateUtils.getNowDate());
        headerVO.setDeliveryDate(DateUtils.getNowDate());
        headerVO.setExtraCost(new BigDecimal(0));
        headerVO.setExtraCostInfo("");
        insertProduceOrder(headerVO);
        Long headerId = headerVO.getId();
        //复制工序
        ProduceOrderProcess orgPopParam = new ProduceOrderProcess();
        orgPopParam.setProduceOrderId(orgId);
        List<ProduceOrderProcess> orgPopList = produceOrderProcessMapper.selectProduceOrderProcessList(orgPopParam);
        for (int i=0;i<orgPopList.size();i++){
            ProduceOrderProcess orgPopVO = orgPopList.get(i);
            orgPopVO.setProduceOrderId(headerId);
            produceOrderProcessMapper.insertProduceOrderProcess(orgPopVO);
        }
        //复制材料
        ProduceOrderMaterials orgPomParam = new ProduceOrderMaterials();
        orgPomParam.setProduceOrderId(orgId);
        List<ProduceOrderMaterials> orgPomList = produceOrderMaterialsMapper.selectProduceOrderMaterialsList(orgPomParam);
        for (int i=0;i<orgPomList.size();i++){
            ProduceOrderMaterials orgPomVO = orgPomList.get(i);
            orgPomVO.setProduceOrderId(headerId);
            produceOrderMaterialsMapper.insertProduceOrderMaterials(orgPomVO);
        }
        return headerVO;
    }

    /**
     * 拼版
     * @param produceOrder
     * @return
     */
    @Override
    public ProduceOrder setMarkup(ProduceOrder produceOrder){
        List<ProduceOrder> list = produceOrderMapper.selectProduceOrderPartListByPartIds(Convert.toStrArray(produceOrder.getIds()));
        ProduceOrder produceOrderPart = new ProduceOrder();
        String partName = "";
        for (int i=0;i<list.size();i++){
            if(i==0){
                BeanUtils.copyProperties(list.get(i), produceOrderPart);
            }else{
                partName += "&";
            }
            partName += list.get(i).getProductName();
        }
        produceOrderPart.setProductName("【拼】"+partName);
        produceOrderMapper.insertProduceOrderPart(produceOrderPart);
        //produceOrderMapper.deleteProduceOrderPartsByIds(Convert.toStrArray(produceOrder.getIds()));
        return produceOrderPart;
    }

    /**
     * 新增部件
     * @param produceOrder
     * @return
     */
    @Override
    public ProduceOrder insertProduceOrderPart(ProduceOrder produceOrder){
        produceOrderMapper.insertProduceOrderPart(produceOrder);
        return produceOrder;
    }

    /**
     * 删除部件
     * @param produceOrder
     * @return
     */
    @Override
    public ProduceOrder deleteProduceOrderPartByPartIds(ProduceOrder produceOrder){
        String[] ids = Convert.toStrArray(produceOrder.getIds());
        for (int i=0;i<ids.length;i++){
            Long id = Long.parseLong(ids[i]);
            produceOrderMapper.deleteProduceOrderPartByPartId(id);
        }
        return produceOrder;
    }

}
