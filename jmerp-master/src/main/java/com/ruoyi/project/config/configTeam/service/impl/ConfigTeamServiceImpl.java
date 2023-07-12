package com.ruoyi.project.config.configTeam.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configBoard.domain.ConfigBoard;
import com.ruoyi.project.config.configBoard.service.impl.ConfigBoardServiceImpl;
import com.ruoyi.project.config.configTeamMember.service.IConfigTeamMemberService;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configTeam.mapper.ConfigTeamMapper;
import com.ruoyi.project.config.configTeam.domain.ConfigTeam;
import com.ruoyi.project.config.configTeam.service.IConfigTeamService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 班组配置Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigTeamServiceImpl implements IConfigTeamService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigTeamServiceImpl.class);

    @Autowired
    private ConfigTeamMapper configTeamMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    @Autowired
    private IConfigTeamMemberService configTeamMemberService;

    /**
     * 查询班组配置
     * 
     * @param id 班组配置ID
     * @return 班组配置
     */
    @Override
    public ConfigTeam selectConfigTeamById(Long id)
    {
        return configTeamMapper.selectConfigTeamById(id);
    }

    /**
     * 查询班组配置列表
     * 
     * @param configTeam 班组配置
     * @return 班组配置
     */
    @Override
    public List<ConfigTeam> selectConfigTeamList(ConfigTeam configTeam)
    {
        return configTeamMapper.selectConfigTeamList(configTeam);
    }

    /**
     * 新增班组配置
     * 
     * @param configTeam 班组配置
     * @return 结果
     */
    @Override
    public int insertConfigTeam(ConfigTeam configTeam)
    {
        configTeam.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configTeam.setCreateTime(DateUtils.getNowDate());
        configTeam.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configTeam.setUpdateTime(DateUtils.getNowDate());
        return configTeamMapper.insertConfigTeam(configTeam);
    }

    /**
     * 修改班组配置
     * 
     * @param configTeam 班组配置
     * @return 结果
     */
    @Override
    public int updateConfigTeam(ConfigTeam configTeam)
    {
        configTeam.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configTeam.setUpdateTime(DateUtils.getNowDate());
        return configTeamMapper.updateConfigTeam(configTeam);
    }

    /**
     * 删除班组配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigTeamByIds(String ids)
    {
        configTeamMapper.deleteConfigTeamMemberByIds(Convert.toStrArray(ids));
        return configTeamMapper.deleteConfigTeamByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除班组配置信息
     * 
     * @param id 班组配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigTeamById(Long id)
    {
        configTeamMapper.deleteConfigTeamMemberById(id);
        return configTeamMapper.deleteConfigTeamById(id);
    }

    /**
     * 导入数据
     *
     * @param configTeamList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigTeam(List<ConfigTeam> configTeamList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configTeamList) || configTeamList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigTeam configTeam : configTeamList){
            boolean checkFlag = false;
            //空字符串处理
            configTeam = (ConfigTeam) EntityUtils.nullStringToNull(configTeam);
            try{
                //名称必填
                if(StringUtils.isEmpty(configTeam.getTeamName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 班组名称必填");
                }
                //id转换
                if(!StringUtils.isEmpty(configTeam.getEquipmentName())){
                    configTeam.setEquipmentId(rzyCommonMapper.findIdByName(configTeam.getEquipmentName(),"config_equipment","equipment_name","id"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigTeam(configTeam);
                    configTeamMemberService.importMemberByTeam(configTeam);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configTeam.getTeamName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、班组 " + configTeam.getTeamName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0){
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
