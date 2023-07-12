package com.ruoyi.project.config.configCartonShape.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configCartonShape.mapper.ConfigCartonShapeMapper;
import com.ruoyi.project.config.configCartonShape.domain.ConfigCartonShape;
import com.ruoyi.project.config.configCartonShape.service.IConfigCartonShapeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 箱型配置Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigCartonShapeServiceImpl implements IConfigCartonShapeService 
{
    @Autowired
    private ConfigCartonShapeMapper configCartonShapeMapper;

    /**
     * 查询箱型配置
     * 
     * @param id 箱型配置ID
     * @return 箱型配置
     */
    @Override
    public ConfigCartonShape selectConfigCartonShapeById(Long id)
    {
        return configCartonShapeMapper.selectConfigCartonShapeById(id);
    }

    /**
     * 查询箱型配置列表
     * 
     * @param configCartonShape 箱型配置
     * @return 箱型配置
     */
    @Override
    public List<ConfigCartonShape> selectConfigCartonShapeList(ConfigCartonShape configCartonShape)
    {
        return configCartonShapeMapper.selectConfigCartonShapeList(configCartonShape);
    }

    /**
     * 新增箱型配置
     * 
     * @param configCartonShape 箱型配置
     * @return 结果
     */
    @Override
    public int insertConfigCartonShape(ConfigCartonShape configCartonShape)
    {
        configCartonShape.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configCartonShape.setCreateTime(DateUtils.getNowDate());
        configCartonShape.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configCartonShape.setUpdateTime(DateUtils.getNowDate());
        return configCartonShapeMapper.insertConfigCartonShape(configCartonShape);
    }

    /**
     * 修改箱型配置
     * 
     * @param configCartonShape 箱型配置
     * @return 结果
     */
    @Override
    public int updateConfigCartonShape(ConfigCartonShape configCartonShape)
    {
        configCartonShape.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configCartonShape.setUpdateTime(DateUtils.getNowDate());
        return configCartonShapeMapper.updateConfigCartonShape(configCartonShape);
    }

    /**
     * 删除箱型配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigCartonShapeByIds(String ids)
    {
        return configCartonShapeMapper.deleteConfigCartonShapeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除箱型配置信息
     * 
     * @param id 箱型配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigCartonShapeById(Long id)
    {
        return configCartonShapeMapper.deleteConfigCartonShapeById(id);
    }
}
