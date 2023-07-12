package com.ruoyi.project.config.configCorrugated.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configCorrugated.domain.ConfigCorrugated;
import com.ruoyi.project.config.configCutterDie.service.impl.ConfigCutterDieServiceImpl;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configCorrugated.mapper.ConfigCorrugatedMapper;
import com.ruoyi.project.config.configCorrugated.service.IConfigCorrugatedService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 楞型配置Service业务层处理
 *
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigCorrugatedServiceImpl implements IConfigCorrugatedService
{

    private static final Logger log = LoggerFactory.getLogger(ConfigCorrugatedServiceImpl.class);

    @Autowired
    private ConfigCorrugatedMapper configCorrugatedMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询楞型配置
     *
     * @param id 楞型配置ID
     * @return 楞型配置
     */
    @Override
    public ConfigCorrugated selectConfigCorrugatedById(Long id)
    {
        return configCorrugatedMapper.selectConfigCorrugatedById(id);
    }

    /**
     * 查询楞型配置列表
     *
     * @param configCorrugated 楞型配置
     * @return 楞型配置
     */
    @Override
    public List<ConfigCorrugated> selectConfigCorrugatedList(ConfigCorrugated configCorrugated)
    {
        return configCorrugatedMapper.selectConfigCorrugatedList(configCorrugated);
    }

    /**
     * 新增楞型配置
     *
     * @param configCorrugated 楞型配置
     * @return 结果
     */
    @Override
    public int insertConfigCorrugated(ConfigCorrugated configCorrugated)
    {
        configCorrugated.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configCorrugated.setCreateTime(DateUtils.getNowDate());
        configCorrugated.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configCorrugated.setUpdateTime(DateUtils.getNowDate());
        return configCorrugatedMapper.insertConfigCorrugated(configCorrugated);
    }

    /**
     * 修改楞型配置
     *
     * @param configCorrugated 楞型配置
     * @return 结果
     */
    @Override
    public int updateConfigCorrugated(ConfigCorrugated configCorrugated)
    {
        configCorrugated.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configCorrugated.setUpdateTime(DateUtils.getNowDate());
        return configCorrugatedMapper.updateConfigCorrugated(configCorrugated);
    }

    /**
     * 删除楞型配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigCorrugatedByIds(String ids)
    {
        return configCorrugatedMapper.deleteConfigCorrugatedByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除楞型配置信息
     *
     * @param id 楞型配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigCorrugatedById(Long id)
    {
        return configCorrugatedMapper.deleteConfigCorrugatedById(id);
    }

    /**
     * 导入数据
     *
     * @param configCorrugatedList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigCorrugated(List<ConfigCorrugated> configCorrugatedList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configCorrugatedList) || configCorrugatedList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigCorrugated configCorrugated : configCorrugatedList){
            boolean checkFlag = false;
            //空字符串处理
            configCorrugated = (ConfigCorrugated) EntityUtils.nullStringToNull(configCorrugated);
            try{
                //名称必填
                if(StringUtils.isEmpty(configCorrugated.getCorrugatedName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 楞型名称必填");
                }

                //成功
                if(!checkFlag){
                    this.insertConfigCorrugated(configCorrugated);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configCorrugated.getCorrugatedName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、楞型 " + configCorrugated.getCorrugatedName() + " 导入失败：";
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
