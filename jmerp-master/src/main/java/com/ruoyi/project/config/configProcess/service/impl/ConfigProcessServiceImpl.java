package com.ruoyi.project.config.configProcess.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configCutterDie.domain.ConfigCutterDie;
import com.ruoyi.project.config.configCutterDie.service.impl.ConfigCutterDieServiceImpl;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configProcess.mapper.ConfigProcessMapper;
import com.ruoyi.project.config.configProcess.domain.ConfigProcess;
import com.ruoyi.project.config.configProcess.service.IConfigProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 工序配置Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigProcessServiceImpl implements IConfigProcessService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigProcessServiceImpl.class);

    @Autowired
    private ConfigProcessMapper configProcessMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询工序配置
     * 
     * @param id 工序配置ID
     * @return 工序配置
     */
    @Override
    public ConfigProcess selectConfigProcessById(Long id)
    {
        return configProcessMapper.selectConfigProcessById(id);
    }

    /**
     * 查询工序配置列表
     * 
     * @param configProcess 工序配置
     * @return 工序配置
     */
    @Override
    public List<ConfigProcess> selectConfigProcessList(ConfigProcess configProcess)
    {
        return configProcessMapper.selectConfigProcessList(configProcess);
    }

    /**
     * 新增工序配置
     * 
     * @param configProcess 工序配置
     * @return 结果
     */
    @Override
    public int insertConfigProcess(ConfigProcess configProcess)
    {
        configProcess.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configProcess.setCreateTime(DateUtils.getNowDate());
        configProcess.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configProcess.setUpdateTime(DateUtils.getNowDate());
        return configProcessMapper.insertConfigProcess(configProcess);
    }

    /**
     * 修改工序配置
     * 
     * @param configProcess 工序配置
     * @return 结果
     */
    @Override
    public int updateConfigProcess(ConfigProcess configProcess)
    {
        configProcess.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configProcess.setUpdateTime(DateUtils.getNowDate());
        return configProcessMapper.updateConfigProcess(configProcess);
    }

    /**
     * 删除工序配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigProcessByIds(String ids)
    {
        return configProcessMapper.deleteConfigProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工序配置信息
     * 
     * @param id 工序配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigProcessById(Long id)
    {
        return configProcessMapper.deleteConfigProcessById(id);
    }

    /**
     * 导入数据
     *
     * @param configProcessList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigProcess(List<ConfigProcess> configProcessList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configProcessList) || configProcessList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigProcess configProcess : configProcessList){
            boolean checkFlag = false;
            //空字符串处理
            configProcess = (ConfigProcess) EntityUtils.nullStringToNull(configProcess);
            try{
                //名称必填
                if(StringUtils.isEmpty(configProcess.getProcessName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 工序名称必填");
                }
                //id转换
                if(!StringUtils.isEmpty(configProcess.getEquipmentName())){
                    configProcess.setEquipmentId(rzyCommonMapper.findIdByName(configProcess.getEquipmentName(),"config_equipment","equipment_name","id"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigProcess(configProcess);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configProcess.getProcessName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、工序 " + configProcess.getProcessName() + " 导入失败：";
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
}
