package com.ruoyi.project.config.configCutterDie.mapper;

import java.util.List;
import com.ruoyi.project.config.configCutterDie.domain.ConfigCutterDie;

/**
 * 刀模配置Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigCutterDieMapper 
{
    /**
     * 查询刀模配置
     * 
     * @param id 刀模配置ID
     * @return 刀模配置
     */
    public ConfigCutterDie selectConfigCutterDieById(Long id);

    /**
     * 查询刀模配置列表
     * 
     * @param configCutterDie 刀模配置
     * @return 刀模配置集合
     */
    public List<ConfigCutterDie> selectConfigCutterDieList(ConfigCutterDie configCutterDie);

    /**
     * 新增刀模配置
     * 
     * @param configCutterDie 刀模配置
     * @return 结果
     */
    public int insertConfigCutterDie(ConfigCutterDie configCutterDie);

    /**
     * 修改刀模配置
     * 
     * @param configCutterDie 刀模配置
     * @return 结果
     */
    public int updateConfigCutterDie(ConfigCutterDie configCutterDie);

    /**
     * 删除刀模配置
     * 
     * @param id 刀模配置ID
     * @return 结果
     */
    public int deleteConfigCutterDieById(Long id);

    /**
     * 批量删除刀模配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigCutterDieByIds(String[] ids);
}
