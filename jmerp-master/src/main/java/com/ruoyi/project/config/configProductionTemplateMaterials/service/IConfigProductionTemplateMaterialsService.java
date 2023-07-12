package com.ruoyi.project.config.configProductionTemplateMaterials.service;

import java.util.List;
import com.ruoyi.project.config.configProductionTemplateMaterials.domain.ConfigProductionTemplateMaterials;

/**
 * 生产工艺卡材料Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigProductionTemplateMaterialsService 
{
    /**
     * 查询生产工艺卡材料
     * 
     * @param id 生产工艺卡材料ID
     * @return 生产工艺卡材料
     */
    public ConfigProductionTemplateMaterials selectConfigProductionTemplateMaterialsById(Long id);

    /**
     * 查询生产工艺卡材料列表
     * 
     * @param configProductionTemplateMaterials 生产工艺卡材料
     * @return 生产工艺卡材料集合
     */
    public List<ConfigProductionTemplateMaterials> selectConfigProductionTemplateMaterialsList(ConfigProductionTemplateMaterials configProductionTemplateMaterials);

    /**
     * 新增生产工艺卡材料
     * 
     * @param configProductionTemplateMaterials 生产工艺卡材料
     * @return 结果
     */
    public int insertConfigProductionTemplateMaterials(ConfigProductionTemplateMaterials configProductionTemplateMaterials);

    /**
     * 修改生产工艺卡材料
     * 
     * @param configProductionTemplateMaterials 生产工艺卡材料
     * @return 结果
     */
    public int updateConfigProductionTemplateMaterials(ConfigProductionTemplateMaterials configProductionTemplateMaterials);

    /**
     * 批量删除生产工艺卡材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigProductionTemplateMaterialsByIds(String ids);

    /**
     * 删除生产工艺卡材料信息
     * 
     * @param id 生产工艺卡材料ID
     * @return 结果
     */
    public int deleteConfigProductionTemplateMaterialsById(Long id);
}
