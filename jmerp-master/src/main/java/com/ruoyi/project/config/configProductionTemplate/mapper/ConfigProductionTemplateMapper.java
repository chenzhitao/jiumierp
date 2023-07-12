package com.ruoyi.project.config.configProductionTemplate.mapper;

import java.util.List;
import com.ruoyi.project.config.configProductionTemplate.domain.ConfigProductionTemplate;

/**
 * 生产工艺卡Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigProductionTemplateMapper 
{
    /**
     * 查询生产工艺卡
     * 
     * @param id 生产工艺卡ID
     * @return 生产工艺卡
     */
    public ConfigProductionTemplate selectConfigProductionTemplateById(Long id);

    /**
     * 查询生产工艺卡列表
     * 
     * @param configProductionTemplate 生产工艺卡
     * @return 生产工艺卡集合
     */
    public List<ConfigProductionTemplate> selectConfigProductionTemplateList(ConfigProductionTemplate configProductionTemplate);

    /**
     * 新增生产工艺卡
     * 
     * @param configProductionTemplate 生产工艺卡
     * @return 结果
     */
    public int insertConfigProductionTemplate(ConfigProductionTemplate configProductionTemplate);

    /**
     * 修改生产工艺卡
     * 
     * @param configProductionTemplate 生产工艺卡
     * @return 结果
     */
    public int updateConfigProductionTemplate(ConfigProductionTemplate configProductionTemplate);

    /**
     * 删除生产工艺卡
     * 
     * @param id 生产工艺卡ID
     * @return 结果
     */
    public int deleteConfigProductionTemplateById(Long id);
    public int deleteConfigProductionTemplateProcessById(Long id);
    public int deleteConfigProductionTemplateMaterialsById(Long id);

    /**
     * 批量删除生产工艺卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigProductionTemplateByIds(String[] ids);
    public int deleteConfigProductionTemplateProcessByIds(String[] ids);
    public int deleteConfigProductionTemplateMaterialsByIds(String[] ids);
}
