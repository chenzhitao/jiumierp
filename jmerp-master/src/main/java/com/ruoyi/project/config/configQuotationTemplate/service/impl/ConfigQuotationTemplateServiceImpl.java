package com.ruoyi.project.config.configQuotationTemplate.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configEmployee.service.impl.ConfigEmployeeServiceImpl;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.config.configQuotationTemplateMaterials.domain.ConfigQuotationTemplateMaterials;
import com.ruoyi.project.config.configQuotationTemplateMaterials.mapper.ConfigQuotationTemplateMaterialsMapper;
import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;
import com.ruoyi.project.config.configQuotationTemplateProcess.mapper.ConfigQuotationTemplateProcessMapper;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configQuotationTemplate.mapper.ConfigQuotationTemplateMapper;
import com.ruoyi.project.config.configQuotationTemplate.domain.ConfigQuotationTemplate;
import com.ruoyi.project.config.configQuotationTemplate.service.IConfigQuotationTemplateService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 报价工艺卡Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigQuotationTemplateServiceImpl implements IConfigQuotationTemplateService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigQuotationTemplateServiceImpl.class);

    @Autowired
    private ConfigQuotationTemplateMapper configQuotationTemplateMapper;

    @Autowired
    private ConfigQuotationTemplateProcessMapper configQuotationTemplateProcessMapper;

    @Autowired
    private ConfigQuotationTemplateMaterialsMapper configQuotationTemplateMaterialsMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询报价工艺卡
     * 
     * @param id 报价工艺卡ID
     * @return 报价工艺卡
     */
    @Override
    public ConfigQuotationTemplate selectConfigQuotationTemplateById(Long id)
    {
        return configQuotationTemplateMapper.selectConfigQuotationTemplateById(id);
    }

    /**
     * 查询报价工艺卡列表
     * 
     * @param configQuotationTemplate 报价工艺卡
     * @return 报价工艺卡
     */
    @Override
    public List<ConfigQuotationTemplate> selectConfigQuotationTemplateList(ConfigQuotationTemplate configQuotationTemplate)
    {
        return configQuotationTemplateMapper.selectConfigQuotationTemplateList(configQuotationTemplate);
    }

    /**
     * 新增报价工艺卡
     * 
     * @param configQuotationTemplate 报价工艺卡
     * @return 结果
     */
    @Override
    public int insertConfigQuotationTemplate(ConfigQuotationTemplate configQuotationTemplate)
    {
        configQuotationTemplate.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configQuotationTemplate.setCreateTime(DateUtils.getNowDate());
        configQuotationTemplate.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configQuotationTemplate.setUpdateTime(DateUtils.getNowDate());
        return configQuotationTemplateMapper.insertConfigQuotationTemplate(configQuotationTemplate);
    }

    /**
     * 修改报价工艺卡
     * 
     * @param configQuotationTemplate 报价工艺卡
     * @return 结果
     */
    @Override
    public int updateConfigQuotationTemplate(ConfigQuotationTemplate configQuotationTemplate)
    {
        configQuotationTemplate.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configQuotationTemplate.setUpdateTime(DateUtils.getNowDate());
        return configQuotationTemplateMapper.updateConfigQuotationTemplate(configQuotationTemplate);
    }

    /**
     * 删除报价工艺卡对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigQuotationTemplateByIds(String ids)
    {
        configQuotationTemplateMapper.deleteConfigQuotationTemplateProcessByIds(Convert.toStrArray(ids));
        configQuotationTemplateMapper.deleteConfigQuotationTemplateMaterialsByIds(Convert.toStrArray(ids));
        return configQuotationTemplateMapper.deleteConfigQuotationTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报价工艺卡信息
     * 
     * @param id 报价工艺卡ID
     * @return 结果
     */
    @Override
    public int deleteConfigQuotationTemplateById(Long id)
    {
        configQuotationTemplateMapper.deleteConfigQuotationTemplateProcessById(id);
        configQuotationTemplateMapper.deleteConfigQuotationTemplateMaterialsById(id);
        return configQuotationTemplateMapper.deleteConfigQuotationTemplateById(id);
    }

    /**
     * 导入数据
     *
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigQuotationTemplate(List<ConfigQuotationTemplateProcess> processList, List<ConfigQuotationTemplateMaterials> materialsList, Boolean isUpdateSupport){
        boolean processEmpty = StringUtils.isNull(processList) || processList.size() == 0;
        boolean materialsEmpty = StringUtils.isNull(materialsList) || materialsList.size() == 0;
        if (processEmpty&&materialsEmpty){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        //工序
        for (ConfigQuotationTemplateProcess configQuotationTemplateProcess : processList){
            boolean checkFlag = false;
            //空字符串处理
            configQuotationTemplateProcess = (ConfigQuotationTemplateProcess) EntityUtils.nullStringToNull(configQuotationTemplateProcess);
            try{
                //姓名必填
                if(StringUtils.isEmpty(configQuotationTemplateProcess.getQuotationTemplateName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 报价工艺卡必填");
                }
                if(StringUtils.isEmpty(configQuotationTemplateProcess.getProcessName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 工序必填");
                }
                //转换id
                if(!StringUtils.isEmpty(configQuotationTemplateProcess.getProcessName())){
                    configQuotationTemplateProcess.setProcessId(rzyCommonMapper.findIdByName(configQuotationTemplateProcess.getProcessName(),"config_process","process_name","id"));
                }
                if(!StringUtils.isEmpty(configQuotationTemplateProcess.getFormulaName())){
                    configQuotationTemplateProcess.setFormulaId(rzyCommonMapper.findIdByName(configQuotationTemplateProcess.getFormulaName(),"config_formula","formula_name","id"));
                }
                if(!StringUtils.isEmpty(configQuotationTemplateProcess.getEquipmentName())){
                    configQuotationTemplateProcess.setEquipmentId(rzyCommonMapper.findIdByName(configQuotationTemplateProcess.getEquipmentName(),"config_equipment","equipment_name","id"));
                }
                //工序必须有效
                if(null==configQuotationTemplateProcess.getProcessId()){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 工序无效");
                }
                //主表id
                if(!StringUtils.isEmpty(configQuotationTemplateProcess.getQuotationTemplateName())){
                    Long tid = rzyCommonMapper.findIdByName(configQuotationTemplateProcess.getQuotationTemplateName(),"config_quotation_template","template_name","id");
                    //如果没有找到,创建一个
                    if(null==tid){
                        ConfigQuotationTemplate configQuotationTemplate = new ConfigQuotationTemplate();
                        configQuotationTemplate.setTemplateName(configQuotationTemplateProcess.getQuotationTemplateName());
                        insertConfigQuotationTemplate(configQuotationTemplate);
                        tid = configQuotationTemplate.getId();
                    }
                    configQuotationTemplateProcess.setQuotationTemplateId(tid);
                }
                //成功
                if(!checkFlag){
                    configQuotationTemplateProcess.setCreateBy(ShiroUtils.getSysUser().getUserName());
                    configQuotationTemplateProcess.setCreateTime(DateUtils.getNowDate());
                    configQuotationTemplateProcess.setUpdateBy(ShiroUtils.getSysUser().getUserName());
                    configQuotationTemplateProcess.setUpdateTime(DateUtils.getNowDate());
                    configQuotationTemplateProcessMapper.insertConfigQuotationTemplateProcess(configQuotationTemplateProcess);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configQuotationTemplateProcess.getQuotationTemplateName()+"-"+configQuotationTemplateProcess.getProcessName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、工艺卡工序 " + configQuotationTemplateProcess.getQuotationTemplateName()+"-"+configQuotationTemplateProcess.getProcessName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        //材料
        for (ConfigQuotationTemplateMaterials configQuotationTemplateMaterials : materialsList){
            boolean checkFlag = false;
            //空字符串处理
            configQuotationTemplateMaterials = (ConfigQuotationTemplateMaterials) EntityUtils.nullStringToNull(configQuotationTemplateMaterials);
            try{
                //姓名必填
                if(StringUtils.isEmpty(configQuotationTemplateMaterials.getQuotationTemplateName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 报价工艺卡必填");
                }else{
                    Integer count = rzyCommonMapper.countRepeat(configQuotationTemplateMaterials.getQuotationTemplateName(),"config_quotation_template","template_name");
                    if(count>0){
                        checkFlag = true;
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、 报价工艺卡重复");
                    }
                }
                if(StringUtils.isEmpty(configQuotationTemplateMaterials.getMaterialsName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 材料必填");
                }
                //转换id
                if(!StringUtils.isEmpty(configQuotationTemplateMaterials.getMaterialsName())){
                    configQuotationTemplateMaterials.setMaterialsId(rzyCommonMapper.findIdByName(configQuotationTemplateMaterials.getMaterialsName(),"config_materials","materials_name","id"));
                }
                if(!StringUtils.isEmpty(configQuotationTemplateMaterials.getFormulaName())){
                    configQuotationTemplateMaterials.setFormulaId(rzyCommonMapper.findIdByName(configQuotationTemplateMaterials.getFormulaName(),"config_formula","formula_name","id"));
                }
                if(!StringUtils.isEmpty(configQuotationTemplateMaterials.getProcessName())){
                    configQuotationTemplateMaterials.setProcessId(rzyCommonMapper.findIdByName(configQuotationTemplateMaterials.getProcessName(),"config_process","process_name","id"));
                }
                //工序必须有效
                if(null==configQuotationTemplateMaterials.getMaterialsId()){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 材料无效");
                }
                //主表id
                if(!StringUtils.isEmpty(configQuotationTemplateMaterials.getQuotationTemplateName())){
                    Long tid = rzyCommonMapper.findIdByName(configQuotationTemplateMaterials.getQuotationTemplateName(),"config_quotation_template","template_name","id");
                    //如果没有找到,创建一个
                    if(null==tid){
                        ConfigQuotationTemplate configQuotationTemplate = new ConfigQuotationTemplate();
                        configQuotationTemplate.setTemplateName(configQuotationTemplateMaterials.getQuotationTemplateName());
                        insertConfigQuotationTemplate(configQuotationTemplate);
                        tid = configQuotationTemplate.getId();
                    }
                    configQuotationTemplateMaterials.setQuotationTemplateId(tid);
                }
                //成功
                if(!checkFlag){
                    configQuotationTemplateMaterials.setCreateBy(ShiroUtils.getSysUser().getUserName());
                    configQuotationTemplateMaterials.setCreateTime(DateUtils.getNowDate());
                    configQuotationTemplateMaterials.setUpdateBy(ShiroUtils.getSysUser().getUserName());
                    configQuotationTemplateMaterials.setUpdateTime(DateUtils.getNowDate());
                    configQuotationTemplateMaterialsMapper.insertConfigQuotationTemplateMaterials(configQuotationTemplateMaterials);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configQuotationTemplateMaterials.getQuotationTemplateName()+"-"+configQuotationTemplateMaterials.getMaterialsName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、工艺卡材料 " + configQuotationTemplateMaterials.getQuotationTemplateName()+"-"+configQuotationTemplateMaterials.getMaterialsName() + " 导入失败：";
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
     * 批量加工序
     * @Author 方舟
     * @Date 2021/8/22 21:53:34
     **/
    @Override
    public ConfigQuotationTemplate addProcessBatch(ConfigQuotationTemplate configQuotationTemplate)
    {
        String[] arr = configQuotationTemplate.getIds().split(",");
        ConfigQuotationTemplateProcess param = new ConfigQuotationTemplateProcess();
        param.setQuotationTemplateId(configQuotationTemplate.getId());
        List<ConfigQuotationTemplateProcess> list = configQuotationTemplateProcessMapper.selectConfigQuotationTemplateProcessList(param);
        for (int i=0;i<arr.length;i++){
            Long processId = Long.parseLong(arr[i]);
            ConfigQuotationTemplateProcess processVO = new ConfigQuotationTemplateProcess();
            processVO.setQuotationTemplateId(configQuotationTemplate.getId());
            processVO.setProcessId(processId);
            processVO.setProcessOrder(list.size()+i+1);
            processVO.setIsTimeCount("Y");
            configQuotationTemplateProcessMapper.insertConfigQuotationTemplateProcess(processVO);
        }
        return configQuotationTemplate;
    }
}
