package com.ruoyi.project.config.configTax.service;

import java.util.List;
import com.ruoyi.project.config.configTax.domain.ConfigTax;

/**
 * 税率Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigTaxService 
{
    /**
     * 查询税率
     * 
     * @param id 税率ID
     * @return 税率
     */
    public ConfigTax selectConfigTaxById(Long id);

    /**
     * 查询税率列表
     * 
     * @param configTax 税率
     * @return 税率集合
     */
    public List<ConfigTax> selectConfigTaxList(ConfigTax configTax);

    /**
     * 新增税率
     * 
     * @param configTax 税率
     * @return 结果
     */
    public int insertConfigTax(ConfigTax configTax);

    /**
     * 修改税率
     * 
     * @param configTax 税率
     * @return 结果
     */
    public int updateConfigTax(ConfigTax configTax);

    /**
     * 批量删除税率
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigTaxByIds(String ids);

    /**
     * 删除税率信息
     * 
     * @param id 税率ID
     * @return 结果
     */
    public int deleteConfigTaxById(Long id);
}
