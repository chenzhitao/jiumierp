package com.ruoyi.project.config.configTeamMember.service.impl;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.config.configTeam.domain.ConfigTeam;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configTeamMember.mapper.ConfigTeamMemberMapper;
import com.ruoyi.project.config.configTeamMember.domain.ConfigTeamMember;
import com.ruoyi.project.config.configTeamMember.service.IConfigTeamMemberService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 班组组员Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigTeamMemberServiceImpl implements IConfigTeamMemberService 
{
    @Autowired
    private ConfigTeamMemberMapper configTeamMemberMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询班组组员
     * 
     * @param id 班组组员ID
     * @return 班组组员
     */
    @Override
    public ConfigTeamMember selectConfigTeamMemberById(Long id)
    {
        return configTeamMemberMapper.selectConfigTeamMemberById(id);
    }

    /**
     * 查询班组组员列表
     * 
     * @param configTeamMember 班组组员
     * @return 班组组员
     */
    @Override
    public List<ConfigTeamMember> selectConfigTeamMemberList(ConfigTeamMember configTeamMember)
    {
        return configTeamMemberMapper.selectConfigTeamMemberList(configTeamMember);
    }

    /**
     * 新增班组组员
     * 
     * @param configTeamMember 班组组员
     * @return 结果
     */
    @Override
    public int insertConfigTeamMember(ConfigTeamMember configTeamMember)
    {
        return configTeamMemberMapper.insertConfigTeamMember(configTeamMember);
    }

    /**
     * 修改班组组员
     * 
     * @param configTeamMember 班组组员
     * @return 结果
     */
    @Override
    public int updateConfigTeamMember(ConfigTeamMember configTeamMember)
    {
        return configTeamMemberMapper.updateConfigTeamMember(configTeamMember);
    }

    /**
     * 删除班组组员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigTeamMemberByIds(String ids)
    {
        return configTeamMemberMapper.deleteConfigTeamMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除班组组员信息
     * 
     * @param id 班组组员ID
     * @return 结果
     */
    @Override
    public int deleteConfigTeamMemberById(Long id)
    {
        return configTeamMemberMapper.deleteConfigTeamMemberById(id);
    }

    /**
     * 导入组员
     * @Author 方舟
     * @Date 2021/4/17 20:16:53
     **/
    @Override
    public void importMemberByTeam(ConfigTeam configTeam){
        if(!StringUtils.isEmpty(configTeam.getMembers())){
            String[] employeeArr = configTeam.getMembers().split(",");
            for (int i=0;i<employeeArr.length;i++){
                String employeeName = employeeArr[i];
                Long employeeId = rzyCommonMapper.findIdByName(employeeName,"config_employee","employee_name","id");
                if(null!=employeeId){
                    ConfigTeamMember configTeamMember = new ConfigTeamMember();
                    configTeamMember.setTeamId(configTeam.getId());
                    configTeamMember.setEmployeeId(employeeId);
                    configTeamMemberMapper.insertConfigTeamMember(configTeamMember);
                }
            }
        }
    }
}
