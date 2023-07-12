package com.ruoyi.project.config.configCartonShape.service;

import java.util.List;
import com.ruoyi.project.config.configCartonShape.domain.ConfigCartonShape;

/**
 * 箱型配置Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigCartonShapeService 
{
    /**
     * 查询箱型配置
     * 
     * @param id 箱型配置ID
     * @return 箱型配置
     */
    public ConfigCartonShape selectConfigCartonShapeById(Long id);

    /**
     * 查询箱型配置列表
     * 
     * @param configCartonShape 箱型配置
     * @return 箱型配置集合
     */
    public List<ConfigCartonShape> selectConfigCartonShapeList(ConfigCartonShape configCartonShape);

    /**
     * 新增箱型配置
     * 
     * @param configCartonShape 箱型配置
     * @return 结果
     */
    public int insertConfigCartonShape(ConfigCartonShape configCartonShape);

    /**
     * 修改箱型配置
     * 
     * @param configCartonShape 箱型配置
     * @return 结果
     */
    public int updateConfigCartonShape(ConfigCartonShape configCartonShape);

    /**
     * 批量删除箱型配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigCartonShapeByIds(String ids);

    /**
     * 删除箱型配置信息
     * 
     * @param id 箱型配置ID
     * @return 结果
     */
    public int deleteConfigCartonShapeById(Long id);
}
