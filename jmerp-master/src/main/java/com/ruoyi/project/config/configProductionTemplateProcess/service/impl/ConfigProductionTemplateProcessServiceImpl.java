package com.ruoyi.project.config.configProductionTemplateProcess.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configProductionTemplateProcess.mapper.ConfigProductionTemplateProcessMapper;
import com.ruoyi.project.config.configProductionTemplateProcess.domain.ConfigProductionTemplateProcess;
import com.ruoyi.project.config.configProductionTemplateProcess.service.IConfigProductionTemplateProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 生产工艺卡工序Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigProductionTemplateProcessServiceImpl implements IConfigProductionTemplateProcessService 
{
    @Autowired
    private ConfigProductionTemplateProcessMapper configProductionTemplateProcessMapper;

    /**
     * 查询生产工艺卡工序
     * 
     * @param id 生产工艺卡工序ID
     * @return 生产工艺卡工序
     */
    @Override
    public ConfigProductionTemplateProcess selectConfigProductionTemplateProcessById(Long id)
    {
        return configProductionTemplateProcessMapper.selectConfigProductionTemplateProcessById(id);
    }

    /**
     * 查询生产工艺卡工序列表
     * 
     * @param configProductionTemplateProcess 生产工艺卡工序
     * @return 生产工艺卡工序
     */
    @Override
    public List<ConfigProductionTemplateProcess> selectConfigProductionTemplateProcessList(ConfigProductionTemplateProcess configProductionTemplateProcess)
    {
        return configProductionTemplateProcessMapper.selectConfigProductionTemplateProcessList(configProductionTemplateProcess);
    }

    /**
     * 新增生产工艺卡工序
     * 
     * @param configProductionTemplateProcess 生产工艺卡工序
     * @return 结果
     */
    @Override
    public int insertConfigProductionTemplateProcess(ConfigProductionTemplateProcess configProductionTemplateProcess)
    {
        return configProductionTemplateProcessMapper.insertConfigProductionTemplateProcess(configProductionTemplateProcess);
    }

    /**
     * 修改生产工艺卡工序
     * 
     * @param configProductionTemplateProcess 生产工艺卡工序
     * @return 结果
     */
    @Override
    public int updateConfigProductionTemplateProcess(ConfigProductionTemplateProcess configProductionTemplateProcess)
    {
        return configProductionTemplateProcessMapper.updateConfigProductionTemplateProcess(configProductionTemplateProcess);
    }

    /**
     * 删除生产工艺卡工序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigProductionTemplateProcessByIds(String ids)
    {
        return configProductionTemplateProcessMapper.deleteConfigProductionTemplateProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产工艺卡工序信息
     * 
     * @param id 生产工艺卡工序ID
     * @return 结果
     */
    @Override
    public int deleteConfigProductionTemplateProcessById(Long id)
    {
        return configProductionTemplateProcessMapper.deleteConfigProductionTemplateProcessById(id);
    }
}
