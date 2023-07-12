package com.ruoyi.project.config.configPaperFormula.mapper;

import java.util.List;
import com.ruoyi.project.config.configPaperFormula.domain.ConfigPaperFormula;

/**
 * 纸张配方Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigPaperFormulaMapper 
{
    /**
     * 查询纸张配方
     * 
     * @param id 纸张配方ID
     * @return 纸张配方
     */
    public ConfigPaperFormula selectConfigPaperFormulaById(Long id);

    /**
     * 查询纸张配方列表
     * 
     * @param configPaperFormula 纸张配方
     * @return 纸张配方集合
     */
    public List<ConfigPaperFormula> selectConfigPaperFormulaList(ConfigPaperFormula configPaperFormula);

    /**
     * 新增纸张配方
     * 
     * @param configPaperFormula 纸张配方
     * @return 结果
     */
    public int insertConfigPaperFormula(ConfigPaperFormula configPaperFormula);

    /**
     * 修改纸张配方
     * 
     * @param configPaperFormula 纸张配方
     * @return 结果
     */
    public int updateConfigPaperFormula(ConfigPaperFormula configPaperFormula);

    /**
     * 删除纸张配方
     * 
     * @param id 纸张配方ID
     * @return 结果
     */
    public int deleteConfigPaperFormulaById(Long id);

    /**
     * 批量删除纸张配方
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigPaperFormulaByIds(String[] ids);
}
