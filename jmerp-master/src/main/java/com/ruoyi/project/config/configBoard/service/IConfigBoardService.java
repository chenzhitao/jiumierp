package com.ruoyi.project.config.configBoard.service;

import java.util.List;
import com.ruoyi.project.config.configBoard.domain.ConfigBoard;
import com.ruoyi.project.config.configCutterDie.domain.ConfigCutterDie;

/**
 * 板材配置Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigBoardService 
{
    /**
     * 查询板材配置
     * 
     * @param id 板材配置ID
     * @return 板材配置
     */
    public ConfigBoard selectConfigBoardById(Long id);

    /**
     * 查询板材配置列表
     * 
     * @param configBoard 板材配置
     * @return 板材配置集合
     */
    public List<ConfigBoard> selectConfigBoardList(ConfigBoard configBoard);

    /**
     * 新增板材配置
     * 
     * @param configBoard 板材配置
     * @return 结果
     */
    public int insertConfigBoard(ConfigBoard configBoard);

    /**
     * 修改板材配置
     * 
     * @param configBoard 板材配置
     * @return 结果
     */
    public int updateConfigBoard(ConfigBoard configBoard);

    /**
     * 批量删除板材配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigBoardByIds(String ids);

    /**
     * 删除板材配置信息
     * 
     * @param id 板材配置ID
     * @return 结果
     */
    public int deleteConfigBoardById(Long id);

    /**
     * 导入数据
     *
     * @param configBoardList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigBoard(List<ConfigBoard> configBoardList, Boolean isUpdateSupport);
}
