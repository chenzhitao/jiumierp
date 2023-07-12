package com.ruoyi.project.config.configProductionTemplate.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configProductionTemplate.domain.ConfigProductionTemplate;
import com.ruoyi.project.config.configProductionTemplate.mapper.ConfigProductionTemplateMapper;
import com.ruoyi.project.config.configProductionTemplate.service.impl.ConfigProductionTemplateServiceImpl;
import com.ruoyi.project.config.configProductionTemplateMaterials.domain.ConfigProductionTemplateMaterials;
import com.ruoyi.project.config.configProductionTemplateMaterials.mapper.ConfigProductionTemplateMaterialsMapper;
import com.ruoyi.project.config.configProductionTemplateProcess.domain.ConfigProductionTemplateProcess;
import com.ruoyi.project.config.configProductionTemplateProcess.mapper.ConfigProductionTemplateProcessMapper;
import com.ruoyi.project.config.configQuotationTemplate.domain.ConfigQuotationTemplate;
import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configProductionTemplate.mapper.ConfigProductionTemplateMapper;
import com.ruoyi.project.config.configProductionTemplate.domain.ConfigProductionTemplate;
import com.ruoyi.project.config.configProductionTemplate.service.IConfigProductionTemplateService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 生产工艺卡Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigProductionTemplateServiceImpl implements IConfigProductionTemplateService 
{
    @Autowired
    private ConfigProductionTemplateMapper configProductionTemplateMapper;

    private static final Logger log = LoggerFactory.getLogger(ConfigProductionTemplateServiceImpl.class);

    @Autowired
    private ConfigProductionTemplateProcessMapper configProductionTemplateProcessMapper;

    @Autowired
    private ConfigProductionTemplateMaterialsMapper configProductionTemplateMaterialsMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询生产工艺卡
     * 
     * @param id 生产工艺卡ID
     * @return 生产工艺卡
     */
    @Override
    public ConfigProductionTemplate selectConfigProductionTemplateById(Long id)
    {
        return configProductionTemplateMapper.selectConfigProductionTemplateById(id);
    }

    /**
     * 查询生产工艺卡列表
     * 
     * @param configProductionTemplate 生产工艺卡
     * @return 生产工艺卡
     */
    @Override
    public List<ConfigProductionTemplate> selectConfigProductionTemplateList(ConfigProductionTemplate configProductionTemplate)
    {
        return configProductionTemplateMapper.selectConfigProductionTemplateList(configProductionTemplate);
    }

    /**
     * 新增生产工艺卡
     * 
     * @param configProductionTemplate 生产工艺卡
     * @return 结果
     */
    @Override
    public int insertConfigProductionTemplate(ConfigProductionTemplate configProductionTemplate)
    {
        configProductionTemplate.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configProductionTemplate.setCreateTime(DateUtils.getNowDate());
        configProductionTemplate.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configProductionTemplate.setUpdateTime(DateUtils.getNowDate());
        return configProductionTemplateMapper.insertConfigProductionTemplate(configProductionTemplate);
    }

    /**
     * 修改生产工艺卡
     * 
     * @param configProductionTemplate 生产工艺卡
     * @return 结果
     */
    @Override
    public int updateConfigProductionTemplate(ConfigProductionTemplate configProductionTemplate)
    {
        configProductionTemplate.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configProductionTemplate.setUpdateTime(DateUtils.getNowDate());
        return configProductionTemplateMapper.updateConfigProductionTemplate(configProductionTemplate);
    }

    /**
     * 删除生产工艺卡对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigProductionTemplateByIds(String ids)
    {
        configProductionTemplateMapper.deleteConfigProductionTemplateProcessByIds(Convert.toStrArray(ids));
        configProductionTemplateMapper.deleteConfigProductionTemplateMaterialsByIds(Convert.toStrArray(ids));
        return configProductionTemplateMapper.deleteConfigProductionTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产工艺卡信息
     * 
     * @param id 生产工艺卡ID
     * @return 结果
     */
    @Override
    public int deleteConfigProductionTemplateById(Long id)
    {
        configProductionTemplateMapper.deleteConfigProductionTemplateProcessById(id);
        configProductionTemplateMapper.deleteConfigProductionTemplateMaterialsById(id);
        return configProductionTemplateMapper.deleteConfigProductionTemplateById(id);
    }

    /**
     * 导入数据
     *
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigProductionTemplate(List<ConfigProductionTemplateProcess> processList, List<ConfigProductionTemplateMaterials> materialsList, Boolean isUpdateSupport){
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
        for (ConfigProductionTemplateProcess configProductionTemplateProcess : processList){
            boolean checkFlag = false;
            //空字符串处理
            configProductionTemplateProcess = (ConfigProductionTemplateProcess) EntityUtils.nullStringToNull(configProductionTemplateProcess);
            try{
                //姓名必填
                if(StringUtils.isEmpty(configProductionTemplateProcess.getProductionTemplateName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 生产工艺卡必填");
                }
                if(StringUtils.isEmpty(configProductionTemplateProcess.getProcessName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 工序必填");
                }
                //转换id
                if(!StringUtils.isEmpty(configProductionTemplateProcess.getProcessName())){
                    configProductionTemplateProcess.setProcessId(rzyCommonMapper.findIdByName(configProductionTemplateProcess.getProcessName(),"config_process","process_name","id"));
                }
                if(!StringUtils.isEmpty(configProductionTemplateProcess.getEquipmentName())){
                    configProductionTemplateProcess.setEquipmentId(rzyCommonMapper.findIdByName(configProductionTemplateProcess.getEquipmentName(),"config_equipment","equipment_name","id"));
                }
                //主表id
                if(!StringUtils.isEmpty(configProductionTemplateProcess.getProductionTemplateName())){
                    Long tid = rzyCommonMapper.findIdByName(configProductionTemplateProcess.getProductionTemplateName(),"config_production_template","template_name","id");
                    //如果没有找到,创建一个
                    if(null==tid){
                        ConfigProductionTemplate configProductionTemplate = new ConfigProductionTemplate();
                        configProductionTemplate.setTemplateName(configProductionTemplateProcess.getProductionTemplateName());
                        insertConfigProductionTemplate(configProductionTemplate);
                        tid = configProductionTemplate.getId();
                    }
                    configProductionTemplateProcess.setProductionTemplateId(tid);
                }
                //成功
                if(!checkFlag){
                    configProductionTemplateProcess.setCreateBy(ShiroUtils.getSysUser().getUserName());
                    configProductionTemplateProcess.setCreateTime(DateUtils.getNowDate());
                    configProductionTemplateProcess.setUpdateBy(ShiroUtils.getSysUser().getUserName());
                    configProductionTemplateProcess.setUpdateTime(DateUtils.getNowDate());
                    configProductionTemplateProcessMapper.insertConfigProductionTemplateProcess(configProductionTemplateProcess);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configProductionTemplateProcess.getProductionTemplateName()+"-"+configProductionTemplateProcess.getProcessName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、工艺卡工序 " + configProductionTemplateProcess.getProductionTemplateName()+"-"+configProductionTemplateProcess.getProcessName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        //材料
        for (ConfigProductionTemplateMaterials configProductionTemplateMaterials : materialsList){
            boolean checkFlag = false;
            //空字符串处理
            configProductionTemplateMaterials = (ConfigProductionTemplateMaterials) EntityUtils.nullStringToNull(configProductionTemplateMaterials);
            try{
                //姓名必填
                if(StringUtils.isEmpty(configProductionTemplateMaterials.getProductionTemplateName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 生产工艺卡必填");
                }
                if(StringUtils.isEmpty(configProductionTemplateMaterials.getMaterialsName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 材料必填");
                }
                //转换id
                if(!StringUtils.isEmpty(configProductionTemplateMaterials.getMaterialsName())){
                    configProductionTemplateMaterials.setMaterialsId(rzyCommonMapper.findIdByName(configProductionTemplateMaterials.getMaterialsName(),"config_materials","materials_name","id"));
                }
                if(!StringUtils.isEmpty(configProductionTemplateMaterials.getProcessName())){
                    configProductionTemplateMaterials.setProcessId(rzyCommonMapper.findIdByName(configProductionTemplateMaterials.getProcessName(),"config_process","process_name","id"));
                }
                if(!StringUtils.isEmpty(configProductionTemplateMaterials.getCutterDieName())){
                    configProductionTemplateMaterials.setCutterDieId(rzyCommonMapper.findIdByName(configProductionTemplateMaterials.getCutterDieName(),"config_cutter_die","cutter_die_name","id"));
                }
                if(!StringUtils.isEmpty(configProductionTemplateMaterials.getBoardName())){
                    configProductionTemplateMaterials.setBoardId(rzyCommonMapper.findIdByName(configProductionTemplateMaterials.getBoardName(),"config_board","board_name","id"));
                }
                //主表id
                if(!StringUtils.isEmpty(configProductionTemplateMaterials.getProductionTemplateName())){
                    Long tid = rzyCommonMapper.findIdByName(configProductionTemplateMaterials.getProductionTemplateName(),"config_production_template","template_name","id");
                    //如果没有找到,创建一个
                    if(null==tid){
                        ConfigProductionTemplate configProductionTemplate = new ConfigProductionTemplate();
                        configProductionTemplate.setTemplateName(configProductionTemplateMaterials.getProductionTemplateName());
                        insertConfigProductionTemplate(configProductionTemplate);
                        tid = configProductionTemplate.getId();
                    }
                    configProductionTemplateMaterials.setProductionTemplateId(tid);
                }
                //成功
                if(!checkFlag){
                    configProductionTemplateMaterials.setCreateBy(ShiroUtils.getSysUser().getUserName());
                    configProductionTemplateMaterials.setCreateTime(DateUtils.getNowDate());
                    configProductionTemplateMaterials.setUpdateBy(ShiroUtils.getSysUser().getUserName());
                    configProductionTemplateMaterials.setUpdateTime(DateUtils.getNowDate());
                    configProductionTemplateMaterialsMapper.insertConfigProductionTemplateMaterials(configProductionTemplateMaterials);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configProductionTemplateMaterials.getProductionTemplateName()+"-"+configProductionTemplateMaterials.getMaterialsName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、工艺卡材料 " + configProductionTemplateMaterials.getProductionTemplateName()+"-"+configProductionTemplateMaterials.getMaterialsName() + " 导入失败：";
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
    public ConfigProductionTemplate addProcessBatch(ConfigProductionTemplate configProductionTemplate)
    {
        String[] arr = configProductionTemplate.getIds().split(",");
        ConfigProductionTemplateProcess param = new ConfigProductionTemplateProcess();
        param.setProductionTemplateId(configProductionTemplate.getId());
        List<ConfigProductionTemplateProcess> list = configProductionTemplateProcessMapper.selectConfigProductionTemplateProcessList(param);
        for (int i=0;i<arr.length;i++){
            Long processId = Long.parseLong(arr[i]);
            ConfigProductionTemplateProcess processVO = new ConfigProductionTemplateProcess();
            processVO.setProductionTemplateId(configProductionTemplate.getId());
            processVO.setProcessId(processId);
            processVO.setProcessOrder(list.size()+i+1);
            processVO.setIsTimeCount("Y");
            processVO.setValuationType("price");
            configProductionTemplateProcessMapper.insertConfigProductionTemplateProcess(processVO);
        }
        return configProductionTemplate;
    }
}
