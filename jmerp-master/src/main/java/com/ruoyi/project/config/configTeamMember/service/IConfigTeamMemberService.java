package com.ruoyi.project.config.configTeamMember.service;

import java.util.List;

import com.ruoyi.project.config.configTeam.domain.ConfigTeam;
import com.ruoyi.project.config.configTeamMember.domain.ConfigTeamMember;

/**
 * 班组组员Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigTeamMemberService 
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
     * 批量删除班组组员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigTeamMemberByIds(String ids);

    /**
     * 删除班组组员信息
     * 
     * @param id 班组组员ID
     * @return 结果
     */
    public int deleteConfigTeamMemberById(Long id);

    /**
     * 导入组员
     * @Author 方舟
     * @Date 2021/4/17 20:16:53
    **/
    public void importMemberByTeam(ConfigTeam configTeam);
}
