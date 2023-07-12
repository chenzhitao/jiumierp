package com.ruoyi.project.config.configQuotationTemplateProcess.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configQuotationTemplateProcess.mapper.ConfigQuotationTemplateProcessMapper;
import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;
import com.ruoyi.project.config.configQuotationTemplateProcess.service.IConfigQuotationTemplateProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 报价工艺卡工序Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigQuotationTemplateProcessServiceImpl implements IConfigQuotationTemplateProcessService 
{
    @Autowired
    private ConfigQuotationTemplateProcessMapper configQuotationTemplateProcessMapper;

    /**
     * 查询报价工艺卡工序
     * 
     * @param id 报价工艺卡工序ID
     * @return 报价工艺卡工序
     */
    @Override
    public ConfigQuotationTemplateProcess selectConfigQuotationTemplateProcessById(Long id)
    {
        return configQuotationTemplateProcessMapper.selectConfigQuotationTemplateProcessById(id);
    }

    /**
     * 查询报价工艺卡工序列表
     * 
     * @param configQuotationTemplateProcess 报价工艺卡工序
     * @return 报价工艺卡工序
     */
    @Override
    public List<ConfigQuotationTemplateProcess> selectConfigQuotationTemplateProcessList(ConfigQuotationTemplateProcess configQuotationTemplateProcess)
    {
        return configQuotationTemplateProcessMapper.selectConfigQuotationTemplateProcessList(configQuotationTemplateProcess);
    }

    /**
     * 新增报价工艺卡工序
     * 
     * @param configQuotationTemplateProcess 报价工艺卡工序
     * @return 结果
     */
    @Override
    public int insertConfigQuotationTemplateProcess(ConfigQuotationTemplateProcess configQuotationTemplateProcess)
    {
        return configQuotationTemplateProcessMapper.insertConfigQuotationTemplateProcess(configQuotationTemplateProcess);
    }

    /**
     * 修改报价工艺卡工序
     * 
     * @param configQuotationTemplateProcess 报价工艺卡工序
     * @return 结果
     */
    @Override
    public int updateConfigQuotationTemplateProcess(ConfigQuotationTemplateProcess configQuotationTemplateProcess)
    {
        return configQuotationTemplateProcessMapper.updateConfigQuotationTemplateProcess(configQuotationTemplateProcess);
    }

    /**
     * 删除报价工艺卡工序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigQuotationTemplateProcessByIds(String ids)
    {
        return configQuotationTemplateProcessMapper.deleteConfigQuotationTemplateProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报价工艺卡工序信息
     * 
     * @param id 报价工艺卡工序ID
     * @return 结果
     */
    @Override
    public int deleteConfigQuotationTemplateProcessById(Long id)
    {
        return configQuotationTemplateProcessMapper.deleteConfigQuotationTemplateProcessById(id);
    }
}
