package com.ruoyi.project.config.configPaperFormula.service;

import java.util.List;

import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configPaperFormula.domain.ConfigPaperFormula;

/**
 * 纸张配方Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigPaperFormulaService 
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
     * 批量删除纸张配方
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigPaperFormulaByIds(String ids);

    /**
     * 删除纸张配方信息
     * 
     * @param id 纸张配方ID
     * @return 结果
     */
    public int deleteConfigPaperFormulaById(Long id);


    /**
     * 导入数据
     *
     * @param configPaperFormulaList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigPaperFormula(List<ConfigPaperFormula> configPaperFormulaList, Boolean isUpdateSupport);
}
