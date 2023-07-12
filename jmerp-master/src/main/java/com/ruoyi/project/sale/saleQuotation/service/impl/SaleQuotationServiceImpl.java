package com.ruoyi.project.sale.saleQuotation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.rzy.FormulaUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configCustomer.mapper.ConfigCustomerMapper;
import com.ruoyi.project.config.configCustomer.service.IConfigCustomerService;
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;
import com.ruoyi.project.config.configFormula.mapper.ConfigFormulaMapper;
import com.ruoyi.project.config.configFormula.service.IConfigFormulaService;
import com.ruoyi.project.config.configFormula.service.impl.ConfigFormulaServiceImpl;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.config.configProduct.service.IConfigProductService;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotationExport;
import com.ruoyi.project.sale.saleQuotationMaterials.domain.SaleQuotationMaterials;
import com.ruoyi.project.sale.saleQuotationMaterials.service.ISaleQuotationMaterialsService;
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMult;
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMultLog;
import com.ruoyi.project.sale.saleQuotationMult.mapper.SaleQuotationMultMapper;
import com.ruoyi.project.sale.saleQuotationMult.service.ISaleQuotationMultService;
import com.ruoyi.project.sale.saleQuotationMultDetail.domain.SaleQuotationMultDetail;
import com.ruoyi.project.sale.saleQuotationMultDetail.mapper.SaleQuotationMultDetailMapper;
import com.ruoyi.project.sale.saleQuotationMultDetail.service.ISaleQuotationMultDetailService;
import com.ruoyi.project.sale.saleQuotationProcess.domain.SaleQuotationProcess;
import com.ruoyi.project.sale.saleQuotationProcess.service.ISaleQuotationProcessService;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
import com.ruoyi.project.sale.saleQuotationProduct.service.ISaleQuotationProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleQuotation.mapper.SaleQuotationMapper;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
import com.ruoyi.project.sale.saleQuotation.service.ISaleQuotationService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 报价单Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleQuotationServiceImpl implements ISaleQuotationService 
{
    private static final Logger log = LoggerFactory.getLogger(SaleQuotationServiceImpl.class);

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    @Autowired
    private SaleQuotationMapper saleQuotationMapper;

    @Autowired
    private ISaleQuotationMultService saleQuotationMultService;

    @Autowired
    private ISaleQuotationProductService saleQuotationProductService;

    @Autowired
    private ISaleQuotationProcessService saleQuotationProcessService;

    @Autowired
    private ISaleQuotationMaterialsService saleQuotationMaterialsService;

    @Autowired
    private ISaleQuotationMultDetailService saleQuotationMultDetailService;

    @Autowired
    private IConfigFormulaService configFormulaService;

    @Autowired
    private IConfigProductService configProductService;

    @Autowired
    private IConfigCustomerService configCustomerService;

    /**
     * 查询报价单
     * 
     * @param id 报价单ID
     * @return 报价单
     */
    @Override
    public SaleQuotation selectSaleQuotationById(Long id)
    {
        return saleQuotationMapper.selectSaleQuotationById(id);
    }

    /**
     * 查询报价单列表
     * 
     * @param saleQuotation 报价单
     * @return 报价单
     */
    @Override
    public List<SaleQuotation> selectSaleQuotationList(SaleQuotation saleQuotation)
    {
        saleQuotation.setRzyUserId(ShiroUtils.getUserId());
        return saleQuotationMapper.selectSaleQuotationList(saleQuotation);
    }

    @Override
    public List<SaleQuotationExport> selectSaleQuotationExportList(SaleQuotation saleQuotation)
    {
        saleQuotation.setRzyUserId(ShiroUtils.getUserId());
        return saleQuotationMapper.selectSaleQuotationExportList(saleQuotation);
    }

    /**
     * 新增报价单
     * 
     * @param saleQuotation 报价单
     * @return 结果
     */
    @Override
    public int insertSaleQuotation(SaleQuotation saleQuotation)
    {
        saleQuotation.setCreateBy(ShiroUtils.getSysUser().getUserName());
        saleQuotation.setCreateTime(DateUtils.getNowDate());
        saleQuotation.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        saleQuotation.setUpdateTime(DateUtils.getNowDate());
        return saleQuotationMapper.insertSaleQuotation(saleQuotation);
    }

    /**
     * 修改报价单
     * 
     * @param saleQuotation 报价单
     * @return 结果
     */
    @Override
    public SaleQuotation updateSaleQuotation(SaleQuotation saleQuotation)
    {
        if(saleQuotation.getId().equals(-1L)){
            ConfigCustomer configCustomer = new ConfigCustomer();
            BeanUtils.copyProperties(saleQuotation, configCustomer);
            configCustomer.setStatus("vaild");
            configCustomerService.insertConfigCustomer(configCustomer);
            Long customerId = configCustomer.getId();
            saleQuotation.setCustomerId(customerId);
            saleQuotation = (SaleQuotation) EntityUtils.nullStringToNull(saleQuotation);
            insertSaleQuotation(saleQuotation);
        }else{
            saleQuotation.setUpdateBy(ShiroUtils.getSysUser().getUserName());
            saleQuotation.setUpdateTime(DateUtils.getNowDate());
            saleQuotationMapper.updateSaleQuotation(saleQuotation);
        }
        return saleQuotation;
    }

    /**
     * 删除报价单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            SaleQuotation saleQuotation = new SaleQuotation();
            saleQuotation.setId(Long.parseLong(arr[i]));
            saleQuotation.setStatus("delete");
            result = saleQuotationMapper.updateSaleQuotation(saleQuotation);
        }
        return result;
    }

    /**
     * 删除报价单信息
     * 
     * @param id 报价单ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationById(Long id)
    {
        int result = 0;
        SaleQuotation saleQuotation = new SaleQuotation();
        saleQuotation.setId(id);
        saleQuotation.setStatus("delete");
        result = saleQuotationMapper.updateSaleQuotation(saleQuotation);
        return result;
    }

    /**
     * 选择客户创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public SaleQuotation createSaleQuotation(SaleQuotation saleQuotation){
        Long customerId = Long.parseLong(saleQuotation.getIds());
        ConfigCustomer configCustomer = configCustomerService.selectConfigCustomerById(customerId);
        SaleQuotation saveVO = new SaleQuotation();
        BeanUtils.copyProperties(configCustomer, saveVO);
        saveVO.setCustomerId(customerId);
        saveVO.setStatus("draft");
        saveVO.setQuotationDate(DateUtils.getNowDate());
        saveVO = (SaleQuotation) EntityUtils.nullStringToNull(saveVO);
        insertSaleQuotation(saveVO);
        return selectSaleQuotationById(saveVO.getId());
    }

    /**
     * 报价单计算
     * @Author 方舟
     * @Date 2021/4/21 14:22:23
     **/
    @Override
    public SaleQuotation calculator(SaleQuotation saleQuotation){
        //先准备投入产出数
        calculatorSetup(saleQuotation);
        //准备数据完成,开始计算
        calculatorExec(saleQuotation);
        return saleQuotation;
    }

    /**
     * 计算前准备数量
     * @Author 方舟
     * @Date 2021/4/21 15:56:17
    **/
    private void calculatorSetup(SaleQuotation saleQuotation){
        //先保存数量和税率
        saleQuotationMultService.saveMultByString(saleQuotation.getId(),saleQuotation.getRzyValue1());
        //循环多数量
        SaleQuotationMult saleQuotationMult = new SaleQuotationMult();
        saleQuotationMult.setSaleQuotationId(saleQuotation.getId());
        List<SaleQuotationMult> multList = saleQuotationMultService.selectSaleQuotationMultList(saleQuotationMult);
        //删除原投入产出数量
        saleQuotationMultDetailService.deleteSaleQuotationMultDetailBySaleQuotationId(saleQuotation.getId());
        for (int i=0;i<multList.size();i++){
            SaleQuotationMult listMult = multList.get(i);
            //循环每个产品
            SaleQuotationProduct productParam = new SaleQuotationProduct();
            productParam.setSaleQuotationId(saleQuotation.getId());
            List<SaleQuotationProduct> productList = saleQuotationProductService.selectSaleQuotationProductList(productParam);
            for (int j=0;j<productList.size();j++){
                SaleQuotationProduct sqProduct = productList.get(j);
                //循环产品下的每个工序
                SaleQuotationProcess processParam = new SaleQuotationProcess();
                processParam.setSaleQuotationProductId(sqProduct.getId());
                List<SaleQuotationProcess> processList = saleQuotationProcessService.selectSaleQuotationProcessList(processParam);
                //创建,计算投入产出数
                saleQuotationMultDetailService.createProcessDetail(listMult,sqProduct,processList);
                //循环产品下的每个材料
                SaleQuotationMaterials materialsParam = new SaleQuotationMaterials();
                materialsParam.setSaleQuotationProductId(sqProduct.getId());
                List<SaleQuotationMaterials> materialsList = saleQuotationMaterialsService.selectSaleQuotationMaterialsList(materialsParam);
                //创建,计算产出数
                saleQuotationMultDetailService.createMaterialsDetail(listMult,sqProduct,materialsList);
            }
        }
    }

    /**
     * 计算开始
     * @Author 方舟
     * @Date 2021/4/21 15:56:17
     **/
    private void calculatorExec(SaleQuotation saleQuotation){
        //列出数量结果
        //StringBuffer sb = new StringBuffer();


        //A循环多数量
        SaleQuotationMult saleQuotationMult = new SaleQuotationMult();
        saleQuotationMult.setSaleQuotationId(saleQuotation.getId());
        List<SaleQuotationMult> multList = saleQuotationMultService.selectSaleQuotationMultList(saleQuotationMult);
        for (int i=0;i<multList.size();i++){
            List<SaleQuotationMultLog> logList = new ArrayList<SaleQuotationMultLog>();
            BigDecimal amount = new BigDecimal(0);
            SaleQuotationMult multVO = multList.get(i);
            //循环产品
            SaleQuotationProduct productParamsVO = new SaleQuotationProduct();
            productParamsVO.setSaleQuotationId(saleQuotation.getId());
            List<SaleQuotationProduct> productList = saleQuotationProductService.selectSaleQuotationProductList(productParamsVO);
            for (int k=0;k<productList.size();k++){
                SaleQuotationProduct productVO = productList.get(k);
                ConfigProduct product = configProductService.selectConfigProductById(productVO.getProductId());
                //B-1.显示工序数量,然后按投入数报价
                SaleQuotationMultDetail multDetailVO = new SaleQuotationMultDetail();
                multDetailVO.setSaleQuotationMultId(multVO.getId());
                multDetailVO.setRzyValue1("process");
                multDetailVO.setSaleQuotationProductId(productVO.getId());
                List<SaleQuotationMultDetail> processDetailList = saleQuotationMultDetailService.selectSaleQuotationMultDetail2List(multDetailVO);
                StringBuffer sb1 = new StringBuffer();
                for (int j=0;j<processDetailList.size();j++){
                    SaleQuotationMultDetail detailVO = processDetailList.get(j);
                    String processName = detailVO.getProcessName();
                    if(null==processName){
                        processName = "?";
                    }
                    sb1.append("工序：【"+processName+"】，投入数是【"+detailVO.getInQty()+"】，产出数是【"+detailVO.getOutQty()+"】;");
                    sb1.append(detailVO.getCalculateLog()+";");
                }
                String productName = "";
                if(processDetailList.size()==0){
                    productName = "<没有找到或已删除的产品或工序>";
                }else{
                    processDetailList.get(0).getProductName();
                }
                setLog(multVO.getId(),"inout","【工序数量】产品："+productName+"",sb1.toString(),-1,new BigDecimal(-1),logList);

                //C.数量计算完成,计算工序价格
                for (int j=0;j<processDetailList.size();j++){
                    SaleQuotationMultDetail detailVO = processDetailList.get(j);
                    String productType = detailVO.getProductType();
                    if("color".equals(productType)){//只有彩印才计算
                        StringBuffer sb2 = new StringBuffer();
                        SaleQuotationProcess saleQuotationProcess = saleQuotationProcessService.selectSaleQuotationProcessById(detailVO.getSaleQuotationProcessId());
                        BigDecimal tempAmount = new BigDecimal(0);
                        if(null!=saleQuotationProcess.getFormulaId()){
                            //有公式
                            ConfigFormula configFormula = configFormulaService.selectConfigFormulaById(saleQuotationProcess.getFormulaId());
                            ConfigFormula resultFormula = FormulaUtils.calculateProcessPrice(detailVO.getInQty(),configFormula);
                            //sb2.append("使用公式：【"+configFormula.getFormulaName()+"】，计算价格;");
                            sb2.append(resultFormula.getCalculateLog());
                            tempAmount = resultFormula.getAmount();
                            amount = amount.add(resultFormula.getAmount());
                        }else{
                            //没有公式
                            sb2.append("没有配置公式,简单算法计算价格;");
                            sb2.append("单价【"+detailVO.getProcessPrice()+"】乘以数量【"+detailVO.getInQty()+"】;");
                            tempAmount = detailVO.getProcessPrice().multiply(new BigDecimal(detailVO.getInQty()));
                            amount = amount.add(tempAmount);
                        }
                        setLog(multVO.getId(),"process","【工序价格】产品："+detailVO.getProductName()+"，工序："+detailVO.getProcessName()+"",sb2.toString(),detailVO.getInQty(),tempAmount,logList);
                    }
                }

                //B-2.显示材料数量,然后按投入数报价,不需要
                multDetailVO.setRzyValue1("materials");
                List<SaleQuotationMultDetail> materialsDetailList = saleQuotationMultDetailService.selectSaleQuotationMultDetail2List(multDetailVO);
                StringBuffer sb3 = new StringBuffer();
                for (int j=0;j<materialsDetailList.size();j++){
                    SaleQuotationMultDetail detailVO = materialsDetailList.get(j);
                    sb3.append("材料：【"+detailVO.getMaterialsName()+"】，根据倍率放数计算得到数量是【"+detailVO.getInQty()+"】;");
                }
                String productName2 = "";
                if(materialsDetailList.size()==0){
                    productName2 = "<没有找到或已删除的产品或材料>";
                }else{
                    productName2 = materialsDetailList.get(0).getProductName();
                }
                setLog(multVO.getId(),"inout","【材料数量】产品："+productName2+"",sb3.toString(),-1,new BigDecimal(-1),logList);

                //D.计算材料价格
                for (int j=0;j<materialsDetailList.size();j++) {
                    StringBuffer sb4 = new StringBuffer();
                    SaleQuotationMultDetail detailVO = materialsDetailList.get(j);
                    BigDecimal tempAmount = new BigDecimal(0);
                    String isAreaCalculate = detailVO.getIsAreaCalculate();
                    sb4.append(detailVO.getCalculateLog());
                    if(!StringUtils.isEmpty(isAreaCalculate)&&"Y".equals(isAreaCalculate)){
                        // 20210720改,产品单个面积
                        //Integer marea = detailVO.getMaterialsSizeLong() * detailVO.getMaterialsSizeWidth();
                        //BigDecimal area = new BigDecimal(marea).divide(new BigDecimal(1000000),6,BigDecimal.ROUND_HALF_UP);
                        //
                        BigDecimal area = product.getArea();
                        if(null==area){
                            area = new BigDecimal(0);
                        }
                        BigDecimal sumarea = area.multiply(new BigDecimal(detailVO.getInQty()));
                        sb4.append("单价【"+detailVO.getMaterialsPrice()+"】，数量【"+detailVO.getInQty()+"】;");
                        sb4.append("按面积计算的材料，当前产品单个面积是【"+area+"】，总面积【"+sumarea+"】;");
                        tempAmount = detailVO.getMaterialsPrice().multiply(sumarea);
                    }else{
                        tempAmount = detailVO.getMaterialsPrice().multiply(new BigDecimal(detailVO.getInQty()));
                        sb4.append("单价【"+detailVO.getMaterialsPrice()+"】乘以数量【"+detailVO.getInQty()+"】;");
                    }
                    setLog(multVO.getId(),"materials","【材料价格】产品："+detailVO.getProductName()+"，材料："+detailVO.getMaterialsName()+"",sb4.toString(),detailVO.getInQty(),tempAmount.setScale(2, BigDecimal.ROUND_HALF_UP),logList);
                    amount = amount.add(tempAmount);
                }
                //保存到SaleQuotationMult表里
                JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(logList));
                String json = jsonArray.toString();
                multVO.setCalculateLog(json);
                multVO.setAmount(amount);
                multVO.setPrice(amount.divide(new BigDecimal(multVO.getQty()),2,BigDecimal.ROUND_HALF_UP));
                saleQuotationMultService.updateSaleQuotationMult(multVO);
            }


        }//end for
    }

    /**
     * 日志
     * @Author 方舟
     * @Date 2021/4/25 9:06:21
    **/
    private void setLog(Long saleQuotationMultId,String logType,String logSubject,String logContent,Integer logQty,BigDecimal logAmount,List<SaleQuotationMultLog> logList){
        SaleQuotationMultLog saleQuotationMultLog = new SaleQuotationMultLog();
        saleQuotationMultLog.setSaleQuotationMultId(saleQuotationMultId);
        saleQuotationMultLog.setLogType(logType);
        saleQuotationMultLog.setLogSubject(logSubject);
        saleQuotationMultLog.setLogContent(logContent);
        saleQuotationMultLog.setLogQty(logQty);
        saleQuotationMultLog.setLogAmount(logAmount);
        logList.add(saleQuotationMultLog);
    }

    /**
     * 导入数据
     *
     * @param saleQuotationList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importSaleQuotation(List<SaleQuotation> saleQuotationList, Boolean isUpdateSupport){
        if (StringUtils.isNull(saleQuotationList) || saleQuotationList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SaleQuotation saleQuotation : saleQuotationList){
            boolean checkFlag = false;
            //空字符串处理
            saleQuotation = (SaleQuotation) EntityUtils.nullStringToNull(saleQuotation);
            try{
                //名称必填
                if(StringUtils.isEmpty(saleQuotation.getCustomerName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 客户名称必填");
                }else{
                    saleQuotation.setCustomerId(rzyCommonMapper.findIdByName(saleQuotation.getCustomerName(),"config_customer","customer_name","id"));
                }
                if(StringUtils.isEmpty(saleQuotation.getProductName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 产品名称必填");
                }else{
                    saleQuotation.setProductId(rzyCommonMapper.findIdByName(saleQuotation.getProductName(),"config_product","product_name","id"));
                }
                if(null==saleQuotation.getProductId()){
                    if(null==saleQuotation.getSizeLong()||null==saleQuotation.getSizeWidth()||null==saleQuotation.getSizeHeight()){
                        checkFlag = true;
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、 新建产品，规格必填");
                    }
                }
                if(StringUtils.isEmpty(saleQuotation.getQuotationTemplateName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 报价工艺卡必填");
                }else{
                    saleQuotation.setQuotationTemplateId(rzyCommonMapper.findIdByName(saleQuotation.getQuotationTemplateName(),"config_quotation_template","template_name","id"));
                }
                if(null==saleQuotation.getQuotationTemplateId()){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 报价工艺卡必须有效");
                }
                //选填
                if(!StringUtils.isEmpty(saleQuotation.getProductionTemplateName())){
                    saleQuotation.setProductionTemplateId(rzyCommonMapper.findIdByName(saleQuotation.getProductionTemplateName(),"config_production_template","template_name","id"));
                }

                if(null==saleQuotation.getQty()){
                    saleQuotation.setQty(0);
                }

                //成功
                if(!checkFlag){
                    importSaleQuotation(saleQuotation);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 客户:" + saleQuotation.getCustomerName() + ",产品:" + saleQuotation.getProductName() + "的报价单 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、客户:" + saleQuotation.getCustomerName() + ",产品:" + saleQuotation.getProductName() + "的报价单 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0){
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/25 11:57:21
    **/
    private void importSaleQuotation(SaleQuotation saleQuotation){
        //产品
        if(null==saleQuotation.getProductId()){
            //创建一个新产品
            ConfigProduct configProduct = new ConfigProduct();
            configProduct.setProductName(saleQuotation.getProductName());
            configProduct.setSizeLong(saleQuotation.getSizeLong());
            configProduct.setSizeWidth(saleQuotation.getSizeWidth());
            configProduct.setSizeHeight(saleQuotation.getSizeHeight());
            configProduct.setQuotationTemplateId(saleQuotation.getQuotationTemplateId());
            configProduct.setProductionTemplateId(saleQuotation.getProductionTemplateId());
            configProductService.insertConfigProduct(configProduct);
            saleQuotation.setProductId(configProduct.getId());
        }
        //客户
        if(null==saleQuotation.getCustomerId()){
            //创建一个新客户
            ConfigCustomer configCustomer = new ConfigCustomer();
            configCustomer.setCustomerName(saleQuotation.getCustomerName());
            configCustomer.setStatus("vaild");
            configCustomerService.insertConfigCustomer(configCustomer);
            saleQuotation.setCustomerId(configCustomer.getId());
        }
        //创建报价单头
        this.insertSaleQuotation(saleQuotation);
        //创建报价单多数量
        SaleQuotationMult saleQuotationMult = new SaleQuotationMult();
        saleQuotationMult.setSaleQuotationId(saleQuotation.getId());
        saleQuotationMult.setQty(saleQuotation.getQty());
        saleQuotationMultService.insertSaleQuotationMult(saleQuotationMult);
        //创建产品工艺卡关联表
        SaleQuotationProduct saleQuotationProduct = new SaleQuotationProduct();
        saleQuotationProduct.setSaleQuotationId(saleQuotation.getId());
        saleQuotationProduct.setProductId(saleQuotation.getProductId());
        saleQuotationProduct.setQuotationTemplateId(saleQuotation.getQuotationTemplateId());
        saleQuotationProduct.setProductionTemplateId(saleQuotation.getProductionTemplateId());
        saleQuotationProductService.insertSaleQuotationProduct(saleQuotationProduct);
        //添加 工序和材料
        saleQuotationProduct.setIds(saleQuotation.getProductId().toString());
        saleQuotationProductService.createSaleQuotationProduct(saleQuotationProduct);
    }
}
