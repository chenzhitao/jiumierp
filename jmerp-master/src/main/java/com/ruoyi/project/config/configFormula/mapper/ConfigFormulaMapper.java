package com.ruoyi.project.config.configFormula.mapper;

import java.util.List;
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;

/**
 * 公式配置Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigFormulaMapper 
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
     * 删除公式配置
     * 
     * @param id 公式配置ID
     * @return 结果
     */
    public int deleteConfigFormulaById(Long id);

    /**
     * 批量删除公式配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigFormulaByIds(String[] ids);
}
