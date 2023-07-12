package com.ruoyi.project.config.configQuotationTemplate.mapper;

import java.util.List;
import com.ruoyi.project.config.configQuotationTemplate.domain.ConfigQuotationTemplate;

/**
 * 报价工艺卡Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigQuotationTemplateMapper 
{
    /**
     * 查询报价工艺卡
     * 
     * @param id 报价工艺卡ID
     * @return 报价工艺卡
     */
    public ConfigQuotationTemplate selectConfigQuotationTemplateById(Long id);

    /**
     * 查询报价工艺卡列表
     * 
     * @param configQuotationTemplate 报价工艺卡
     * @return 报价工艺卡集合
     */
    public List<ConfigQuotationTemplate> selectConfigQuotationTemplateList(ConfigQuotationTemplate configQuotationTemplate);

    /**
     * 新增报价工艺卡
     * 
     * @param configQuotationTemplate 报价工艺卡
     * @return 结果
     */
    public int insertConfigQuotationTemplate(ConfigQuotationTemplate configQuotationTemplate);

    /**
     * 修改报价工艺卡
     * 
     * @param configQuotationTemplate 报价工艺卡
     * @return 结果
     */
    public int updateConfigQuotationTemplate(ConfigQuotationTemplate configQuotationTemplate);

    /**
     * 删除报价工艺卡
     * 
     * @param id 报价工艺卡ID
     * @return 结果
     */
    public int deleteConfigQuotationTemplateById(Long id);
    public int deleteConfigQuotationTemplateProcessById(Long id);
    public int deleteConfigQuotationTemplateMaterialsById(Long id);

    /**
     * 批量删除报价工艺卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigQuotationTemplateByIds(String[] ids);
    public int deleteConfigQuotationTemplateProcessByIds(String[] ids);
    public int deleteConfigQuotationTemplateMaterialsByIds(String[] ids);
}
