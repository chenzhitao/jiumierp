package com.ruoyi.project.config.configCutterDie.service;

import java.util.List;

import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configCutterDie.domain.ConfigCutterDie;

/**
 * 刀模配置Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigCutterDieService 
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
     * 批量删除刀模配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigCutterDieByIds(String ids);

    /**
     * 删除刀模配置信息
     * 
     * @param id 刀模配置ID
     * @return 结果
     */
    public int deleteConfigCutterDieById(Long id);

    /**
     * 导入数据
     *
     * @param configCutterDieList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigCutterDie(List<ConfigCutterDie> configCutterDieList, Boolean isUpdateSupport);
}
