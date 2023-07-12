package com.ruoyi.project.config.configProductionTemplateMaterials.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configProductionTemplateMaterials.mapper.ConfigProductionTemplateMaterialsMapper;
import com.ruoyi.project.config.configProductionTemplateMaterials.domain.ConfigProductionTemplateMaterials;
import com.ruoyi.project.config.configProductionTemplateMaterials.service.IConfigProductionTemplateMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 生产工艺卡材料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigProductionTemplateMaterialsServiceImpl implements IConfigProductionTemplateMaterialsService 
{
    @Autowired
    private ConfigProductionTemplateMaterialsMapper configProductionTemplateMaterialsMapper;

    /**
     * 查询生产工艺卡材料
     * 
     * @param id 生产工艺卡材料ID
     * @return 生产工艺卡材料
     */
    @Override
    public ConfigProductionTemplateMaterials selectConfigProductionTemplateMaterialsById(Long id)
    {
        return configProductionTemplateMaterialsMapper.selectConfigProductionTemplateMaterialsById(id);
    }

    /**
     * 查询生产工艺卡材料列表
     * 
     * @param configProductionTemplateMaterials 生产工艺卡材料
     * @return 生产工艺卡材料
     */
    @Override
    public List<ConfigProductionTemplateMaterials> selectConfigProductionTemplateMaterialsList(ConfigProductionTemplateMaterials configProductionTemplateMaterials)
    {
        return configProductionTemplateMaterialsMapper.selectConfigProductionTemplateMaterialsList(configProductionTemplateMaterials);
    }

    /**
     * 新增生产工艺卡材料
     * 
     * @param configProductionTemplateMaterials 生产工艺卡材料
     * @return 结果
     */
    @Override
    public int insertConfigProductionTemplateMaterials(ConfigProductionTemplateMaterials configProductionTemplateMaterials)
    {
        return configProductionTemplateMaterialsMapper.insertConfigProductionTemplateMaterials(configProductionTemplateMaterials);
    }

    /**
     * 修改生产工艺卡材料
     * 
     * @param configProductionTemplateMaterials 生产工艺卡材料
     * @return 结果
     */
    @Override
    public int updateConfigProductionTemplateMaterials(ConfigProductionTemplateMaterials configProductionTemplateMaterials)
    {
        return configProductionTemplateMaterialsMapper.updateConfigProductionTemplateMaterials(configProductionTemplateMaterials);
    }

    /**
     * 删除生产工艺卡材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigProductionTemplateMaterialsByIds(String ids)
    {
        return configProductionTemplateMaterialsMapper.deleteConfigProductionTemplateMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产工艺卡材料信息
     * 
     * @param id 生产工艺卡材料ID
     * @return 结果
     */
    @Override
    public int deleteConfigProductionTemplateMaterialsById(Long id)
    {
        return configProductionTemplateMaterialsMapper.deleteConfigProductionTemplateMaterialsById(id);
    }
}
