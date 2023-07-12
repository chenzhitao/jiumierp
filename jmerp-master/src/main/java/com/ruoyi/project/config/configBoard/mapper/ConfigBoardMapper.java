package com.ruoyi.project.config.configBoard.mapper;

import java.util.List;
import com.ruoyi.project.config.configBoard.domain.ConfigBoard;

/**
 * 板材配置Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigBoardMapper 
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
     * 删除板材配置
     * 
     * @param id 板材配置ID
     * @return 结果
     */
    public int deleteConfigBoardById(Long id);

    /**
     * 批量删除板材配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigBoardByIds(String[] ids);
}
