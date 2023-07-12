package com.ruoyi.project.config.configCorrugated.mapper;

import java.util.List;
import com.ruoyi.project.config.configCorrugated.domain.ConfigCorrugated;

/**
 * 楞型配置Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigCorrugatedMapper 
{
    /**
     * 查询楞型配置
     * 
     * @param id 楞型配置ID
     * @return 楞型配置
     */
    public ConfigCorrugated selectConfigCorrugatedById(Long id);

    /**
     * 查询楞型配置列表
     * 
     * @param configCorrugated 楞型配置
     * @return 楞型配置集合
     */
    public List<ConfigCorrugated> selectConfigCorrugatedList(ConfigCorrugated configCorrugated);

    /**
     * 新增楞型配置
     * 
     * @param configCorrugated 楞型配置
     * @return 结果
     */
    public int insertConfigCorrugated(ConfigCorrugated configCorrugated);

    /**
     * 修改楞型配置
     * 
     * @param configCorrugated 楞型配置
     * @return 结果
     */
    public int updateConfigCorrugated(ConfigCorrugated configCorrugated);

    /**
     * 删除楞型配置
     * 
     * @param id 楞型配置ID
     * @return 结果
     */
    public int deleteConfigCorrugatedById(Long id);

    /**
     * 批量删除楞型配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigCorrugatedByIds(String[] ids);
}
