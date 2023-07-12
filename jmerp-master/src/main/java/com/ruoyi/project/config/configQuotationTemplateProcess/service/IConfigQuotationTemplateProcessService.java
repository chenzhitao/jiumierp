package com.ruoyi.project.config.configQuotationTemplateProcess.service;

import java.util.List;
import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;

/**
 * 报价工艺卡工序Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigQuotationTemplateProcessService 
{
    /**
     * 查询报价工艺卡工序
     * 
     * @param id 报价工艺卡工序ID
     * @return 报价工艺卡工序
     */
    public ConfigQuotationTemplateProcess selectConfigQuotationTemplateProcessById(Long id);

    /**
     * 查询报价工艺卡工序列表
     * 
     * @param configQuotationTemplateProcess 报价工艺卡工序
     * @return 报价工艺卡工序集合
     */
    public List<ConfigQuotationTemplateProcess> selectConfigQuotationTemplateProcessList(ConfigQuotationTemplateProcess configQuotationTemplateProcess);

    /**
     * 新增报价工艺卡工序
     * 
     * @param configQuotationTemplateProcess 报价工艺卡工序
     * @return 结果
     */
    public int insertConfigQuotationTemplateProcess(ConfigQuotationTemplateProcess configQuotationTemplateProcess);

    /**
     * 修改报价工艺卡工序
     * 
     * @param configQuotationTemplateProcess 报价工艺卡工序
     * @return 结果
     */
    public int updateConfigQuotationTemplateProcess(ConfigQuotationTemplateProcess configQuotationTemplateProcess);

    /**
     * 批量删除报价工艺卡工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigQuotationTemplateProcessByIds(String ids);

    /**
     * 删除报价工艺卡工序信息
     * 
     * @param id 报价工艺卡工序ID
     * @return 结果
     */
    public int deleteConfigQuotationTemplateProcessById(Long id);
}
