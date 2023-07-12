package com.ruoyi.project.config.configFormula.service;

import java.util.List;

import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;

/**
 * 公式配置Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigFormulaService 
{
    /**
     * 查询公式配置
     * 
     * @param id 公式配置ID
     * @return 公式配置
     */
    public ConfigFormula selectConfigFormulaById(Long id);

    /**
     * 查询公式配置列表
     * 
     * @param configFormula 公式配置
     * @return 公式配置集合
     */
    public List<ConfigFormula> selectConfigFormulaList(ConfigFormula configFormula);

    /**
     * 新增公式配置
     * 
     * @param configFormula 公式配置
     * @return 结果
     */
    public int insertConfigFormula(ConfigFormula configFormula);

    /**
     * 修改公式配置
     * 
     * @param configFormula 公式配置
     * @return 结果
     */
    public int updateConfigFormula(ConfigFormula configFormula);

    /**
     * 批量删除公式配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigFormulaByIds(String ids);

    /**
     * 删除公式配置信息
     * 
     * @param id 公式配置ID
     * @return 结果
     */
    public int deleteConfigFormulaById(Long id);

    /**
     * 导入数据
     *
     * @param configFormulaList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigFormula(List<ConfigFormula> configFormulaList, Boolean isUpdateSupport);
}
