package com.ruoyi.project.finance.financeStandard.mapper;

import java.util.List;
import com.ruoyi.project.finance.financeStandard.domain.FinanceStandard;

/**
 * 计费标准Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface FinanceStandardMapper 
{
    /**
     * 查询计费标准
     * 
     * @param id 计费标准ID
     * @return 计费标准
     */
    public FinanceStandard selectFinanceStandardById(Long id);

    /**
     * 查询计费标准列表
     * 
     * @param financeStandard 计费标准
     * @return 计费标准集合
     */
    public List<FinanceStandard> selectFinanceStandardList(FinanceStandard financeStandard);

    /**
     * 新增计费标准
     * 
     * @param financeStandard 计费标准
     * @return 结果
     */
    public int insertFinanceStandard(FinanceStandard financeStandard);

    /**
     * 修改计费标准
     * 
     * @param financeStandard 计费标准
     * @return 结果
     */
    public int updateFinanceStandard(FinanceStandard financeStandard);

    /**
     * 删除计费标准
     * 
     * @param id 计费标准ID
     * @return 结果
     */
    public int deleteFinanceStandardById(Long id);

    /**
     * 批量删除计费标准
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceStandardByIds(String[] ids);
}
