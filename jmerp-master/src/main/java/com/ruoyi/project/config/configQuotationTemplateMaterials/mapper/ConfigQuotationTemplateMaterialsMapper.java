package com.ruoyi.project.config.configQuotationTemplateMaterials.mapper;

import java.util.List;
import com.ruoyi.project.config.configQuotationTemplateMaterials.domain.ConfigQuotationTemplateMaterials;

/**
 * 报价工艺卡材料Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigQuotationTemplateMaterialsMapper 
{
    /**
     * 查询报价工艺卡材料
     * 
     * @param id 报价工艺卡材料ID
     * @return 报价工艺卡材料
     */
    public ConfigQuotationTemplateMaterials selectConfigQuotationTemplateMaterialsById(Long id);

    /**
     * 查询报价工艺卡材料列表
     * 
     * @param configQuotationTemplateMaterials 报价工艺卡材料
     * @return 报价工艺卡材料集合
     */
    public List<ConfigQuotationTemplateMaterials> selectConfigQuotationTemplateMaterialsList(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials);

    /**
     * 新增报价工艺卡材料
     * 
     * @param configQuotationTemplateMaterials 报价工艺卡材料
     * @return 结果
     */
    public int insertConfigQuotationTemplateMaterials(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials);

    /**
     * 修改报价工艺卡材料
     * 
     * @param configQuotationTemplateMaterials 报价工艺卡材料
     * @return 结果
     */
    public int updateConfigQuotationTemplateMaterials(ConfigQuotationTemplateMaterials configQuotationTemplateMaterials);

    /**
     * 删除报价工艺卡材料
     * 
     * @param id 报价工艺卡材料ID
     * @return 结果
     */
    public int deleteConfigQuotationTemplateMaterialsById(Long id);

    /**
     * 批量删除报价工艺卡材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigQuotationTemplateMaterialsByIds(String[] ids);
}
