package com.ruoyi.project.config.configMaterials.mapper;

import java.util.List;
import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;

/**
 * 材料配置Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigMaterialsMapper 
{
    /**
     * 查询材料配置
     * 
     * @param id 材料配置ID
     * @return 材料配置
     */
    public ConfigMaterials selectConfigMaterialsById(Long id);

    /**
     * 查询材料配置列表
     * 
     * @param configMaterials 材料配置
     * @return 材料配置集合
     */
    public List<ConfigMaterials> selectConfigMaterialsList(ConfigMaterials configMaterials);

    /**
     * 新增材料配置
     * 
     * @param configMaterials 材料配置
     * @return 结果
     */
    public int insertConfigMaterials(ConfigMaterials configMaterials);

    /**
     * 修改材料配置
     * 
     * @param configMaterials 材料配置
     * @return 结果
     */
    public int updateConfigMaterials(ConfigMaterials configMaterials);

    /**
     * 删除材料配置
     * 
     * @param id 材料配置ID
     * @return 结果
     */
    public int deleteConfigMaterialsById(Long id);

    /**
     * 批量删除材料配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigMaterialsByIds(String[] ids);
}
