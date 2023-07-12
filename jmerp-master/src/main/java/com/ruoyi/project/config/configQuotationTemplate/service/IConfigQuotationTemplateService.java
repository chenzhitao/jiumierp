package com.ruoyi.project.config.configQuotationTemplate.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.config.configQuotationTemplate.domain.ConfigQuotationTemplate;
import com.ruoyi.project.config.configQuotationTemplateMaterials.domain.ConfigQuotationTemplateMaterials;
import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 报价工艺卡Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigQuotationTemplateService 
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
     * 批量删除报价工艺卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigQuotationTemplateByIds(String ids);

    /**
     * 删除报价工艺卡信息
     * 
     * @param id 报价工艺卡ID
     * @return 结果
     */
    public int deleteConfigQuotationTemplateById(Long id);

    /**
     * 导入数据
     *
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigQuotationTemplate(List<ConfigQuotationTemplateProcess> processList, List<ConfigQuotationTemplateMaterials> materialsList, Boolean isUpdateSupport);

    /**
     * 批量加工序
     * @Author 方舟
     * @Date 2021/8/22 21:53:34
     **/
    public ConfigQuotationTemplate addProcessBatch(ConfigQuotationTemplate configQuotationTemplate);
}
