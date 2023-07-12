package com.ruoyi.project.config.configProductionTemplate.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configProductionTemplate.domain.ConfigProductionTemplate;
import com.ruoyi.project.config.configProductionTemplateMaterials.domain.ConfigProductionTemplateMaterials;
import com.ruoyi.project.config.configProductionTemplateProcess.domain.ConfigProductionTemplateProcess;
import com.ruoyi.project.config.configQuotationTemplateMaterials.domain.ConfigQuotationTemplateMaterials;
import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 生产工艺卡Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigProductionTemplateService 
{
    /**
     * 查询生产工艺卡
     * 
     * @param id 生产工艺卡ID
     * @return 生产工艺卡
     */
    public ConfigProductionTemplate selectConfigProductionTemplateById(Long id);

    /**
     * 查询生产工艺卡列表
     * 
     * @param configProductionTemplate 生产工艺卡
     * @return 生产工艺卡集合
     */
    public List<ConfigProductionTemplate> selectConfigProductionTemplateList(ConfigProductionTemplate configProductionTemplate);

    /**
     * 新增生产工艺卡
     * 
     * @param configProductionTemplate 生产工艺卡
     * @return 结果
     */
    public int insertConfigProductionTemplate(ConfigProductionTemplate configProductionTemplate);

    /**
     * 修改生产工艺卡
     * 
     * @param configProductionTemplate 生产工艺卡
     * @return 结果
     */
    public int updateConfigProductionTemplate(ConfigProductionTemplate configProductionTemplate);

    /**
     * 批量删除生产工艺卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigProductionTemplateByIds(String ids);

    /**
     * 删除生产工艺卡信息
     * 
     * @param id 生产工艺卡ID
     * @return 结果
     */
    public int deleteConfigProductionTemplateById(Long id);

    /**
     * 导入数据
     *
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigProductionTemplate(List<ConfigProductionTemplateProcess> processList, List<ConfigProductionTemplateMaterials> materialsList, Boolean isUpdateSupport);

    /**
     * 批量加工序
     * @Author 方舟
     * @Date 2021/8/22 21:53:34
     **/
    public ConfigProductionTemplate addProcessBatch(ConfigProductionTemplate configProductionTemplate);
}
