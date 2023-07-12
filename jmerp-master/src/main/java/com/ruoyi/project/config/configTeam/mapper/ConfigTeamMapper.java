package com.ruoyi.project.config.configTeam.mapper;

import java.util.List;
import com.ruoyi.project.config.configTeam.domain.ConfigTeam;

/**
 * 班组配置Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigTeamMapper 
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
     * 删除班组配置
     * 
     * @param id 班组配置ID
     * @return 结果
     */
    public int deleteConfigTeamById(Long id);
    public int deleteConfigTeamMemberById(Long id);

    /**
     * 批量删除班组配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigTeamByIds(String[] ids);
    public int deleteConfigTeamMemberByIds(String[] ids);
}
