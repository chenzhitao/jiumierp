package com.ruoyi.project.config.configTeamMember.mapper;

import java.util.List;
import com.ruoyi.project.config.configTeamMember.domain.ConfigTeamMember;

/**
 * 班组组员Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigTeamMemberMapper 
{
    /**
     * 查询班组组员
     * 
     * @param id 班组组员ID
     * @return 班组组员
     */
    public ConfigTeamMember selectConfigTeamMemberById(Long id);

    /**
     * 查询班组组员列表
     * 
     * @param configTeamMember 班组组员
     * @return 班组组员集合
     */
    public List<ConfigTeamMember> selectConfigTeamMemberList(ConfigTeamMember configTeamMember);

    /**
     * 新增班组组员
     * 
     * @param configTeamMember 班组组员
     * @return 结果
     */
    public int insertConfigTeamMember(ConfigTeamMember configTeamMember);

    /**
     * 修改班组组员
     * 
     * @param configTeamMember 班组组员
     * @return 结果
     */
    public int updateConfigTeamMember(ConfigTeamMember configTeamMember);

    /**
     * 删除班组组员
     * 
     * @param id 班组组员ID
     * @return 结果
     */
    public int deleteConfigTeamMemberById(Long id);

    /**
     * 批量删除班组组员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigTeamMemberByIds(String[] ids);
}
