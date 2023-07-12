package com.ruoyi.project.config.configQuotationTemplateMaterials.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configQuotationTemplateMaterials.mapper.ConfigQuotationTemplateMaterialsMapper;
import com.ruoyi.project.config.configQuotationTemplateMaterials.domain.ConfigQuotationTemplateMaterials;
import com.ruoyi.project.config.configQuotationTemplateMaterials.service.IConfigQuotationTemplateMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 报价工艺卡材料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigQuotationTemplateMaterialsServiceImpl implements IConfigQuotationTemplateMaterialsService 
{
    @Autowired
    private ConfigQuotationTemplateMaterialsMapper configQuotationTemplateMaterialsMapper;

    /**
     * 查询报价工艺卡材料
     * 
     * @param id 报价工艺卡材料ID
     * @return 报价工艺卡材料
     */
    @Override
    public ConfigQuotationTemplateMaterials selectConfigQuotationTemplateMaterialsById(Long id)
    {
        return configQuotationTemplateMaterialsMapper.selectConfigQuotationTemplateMaterialsById(id);
    }

    /**
     * 查询报价工艺卡材料列表
     * 
     * @param configQuotationTemplateMaterials 报价工艺卡材料
     * @return 报价工艺卡材料
     */
    @Override
    public List<ConfigQuotationTemplateMaterials> selectConfigQuotationTemplateMaterialsList(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials)
    {
        return configQuotationTemplateMaterialsMapper.selectConfigQuotationTemplateMaterialsList(configQuotationTemplateMaterials);
    }

    /**
     * 新增报价工艺卡材料
     * 
     * @param configQuotationTemplateMaterials 报价工艺卡材料
     * @return 结果
     */
    @Override
    public int insertConfigQuotationTemplateMaterials(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials)
    {
        return configQuotationTemplateMaterialsMapper.insertConfigQuotationTemplateMaterials(configQuotationTemplateMaterials);
    }

    /**
     * 修改报价工艺卡材料
     * 
     * @param configQuotationTemplateMaterials 报价工艺卡材料
     * @return 结果
     */
    @Override
    public int updateConfigQuotationTemplateMaterials(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials)
    {
        return configQuotationTemplateMaterialsMapper.updateConfigQuotationTemplateMaterials(configQuotationTemplateMaterials);
    }

    /**
     * 删除报价工艺卡材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigQuotationTemplateMaterialsByIds(String ids)
    {
        return configQuotationTemplateMaterialsMapper.deleteConfigQuotationTemplateMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报价工艺卡材料信息
     * 
     * @param id 报价工艺卡材料ID
     * @return 结果
     */
    @Override
    public int deleteConfigQuotationTemplateMaterialsById(Long id)
    {
        return configQuotationTemplateMaterialsMapper.deleteConfigQuotationTemplateMaterialsById(id);
    }
}
