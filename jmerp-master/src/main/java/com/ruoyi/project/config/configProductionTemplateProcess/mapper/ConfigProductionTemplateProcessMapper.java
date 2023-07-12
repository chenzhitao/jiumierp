package com.ruoyi.project.config.configProductionTemplateProcess.mapper;

import java.util.List;
import com.ruoyi.project.config.configProductionTemplateProcess.domain.ConfigProductionTemplateProcess;

/**
 * 生产工艺卡工序Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigProductionTemplateProcessMapper 
{
    /**
     * 查询生产工艺卡工序
     * 
     * @param id 生产工艺卡工序ID
     * @return 生产工艺卡工序
     */
    public ConfigProductionTemplateProcess selectConfigProductionTemplateProcessById(Long id);

    /**
     * 查询生产工艺卡工序列表
     * 
     * @param configProductionTemplateProcess 生产工艺卡工序
     * @return 生产工艺卡工序集合
     */
    public List<ConfigProductionTemplateProcess> selectConfigProductionTemplateProcessList(ConfigProductionTemplateProcess configProductionTemplateProcess);

    /**
     * 新增生产工艺卡工序
     * 
     * @param configProductionTemplateProcess 生产工艺卡工序
     * @return 结果
     */
    public int insertConfigProductionTemplateProcess(ConfigProductionTemplateProcess configProductionTemplateProcess);

    /**
     * 修改生产工艺卡工序
     * 
     * @param configProductionTemplateProcess 生产工艺卡工序
     * @return 结果
     */
    public int updateConfigProductionTemplateProcess(ConfigProductionTemplateProcess configProductionTemplateProcess);

    /**
     * 删除生产工艺卡工序
     * 
     * @param id 生产工艺卡工序ID
     * @return 结果
     */
    public int deleteConfigProductionTemplateProcessById(Long id);

    /**
     * 批量删除生产工艺卡工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigProductionTemplateProcessByIds(String[] ids);
}
