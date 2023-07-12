package com.ruoyi.project.config.configTeam.service;

import java.util.List;

import com.ruoyi.project.config.configBoard.domain.ConfigBoard;
import com.ruoyi.project.config.configTeam.domain.ConfigTeam;

/**
 * 班组配置Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigTeamService 
{
    /**
     * 查询班组配置
     * 
     * @param id 班组配置ID
     * @return 班组配置
     */
    public ConfigTeam selectConfigTeamById(Long id);

    /**
     * 查询班组配置列表
     * 
     * @param configTeam 班组配置
     * @return 班组配置集合
     */
    public List<ConfigTeam> selectConfigTeamList(ConfigTeam configTeam);

    /**
     * 新增班组配置
     * 
     * @param configTeam 班组配置
     * @return 结果
     */
    public int insertConfigTeam(ConfigTeam configTeam);

    /**
     * 修改班组配置
     * 
     * @param configTeam 班组配置
     * @return 结果
     */
    public int updateConfigTeam(ConfigTeam configTeam);

    /**
     * 批量删除班组配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigTeamByIds(String ids);

    /**
     * 删除班组配置信息
     * 
     * @param id 班组配置ID
     * @return 结果
     */
    public int deleteConfigTeamById(Long id);

    /**
     * 导入数据
     *
     * @param configTeamList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigTeam(List<ConfigTeam> configTeamList, Boolean isUpdateSupport);
}
