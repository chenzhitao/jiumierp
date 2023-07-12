package com.ruoyi.project.config.configTax.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configTax.mapper.ConfigTaxMapper;
import com.ruoyi.project.config.configTax.domain.ConfigTax;
import com.ruoyi.project.config.configTax.service.IConfigTaxService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 税率Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigTaxServiceImpl implements IConfigTaxService 
{
    @Autowired
    private ConfigTaxMapper configTaxMapper;

    /**
     * 查询税率
     * 
     * @param id 税率ID
     * @return 税率
     */
    @Override
    public ConfigTax selectConfigTaxById(Long id)
    {
        return configTaxMapper.selectConfigTaxById(id);
    }

    /**
     * 查询税率列表
     * 
     * @param configTax 税率
     * @return 税率
     */
    @Override
    public List<ConfigTax> selectConfigTaxList(ConfigTax configTax)
    {
        return configTaxMapper.selectConfigTaxList(configTax);
    }

    /**
     * 新增税率
     * 
     * @param configTax 税率
     * @return 结果
     */
    @Override
    public int insertConfigTax(ConfigTax configTax)
    {
        configTax.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configTax.setCreateTime(DateUtils.getNowDate());
        configTax.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configTax.setUpdateTime(DateUtils.getNowDate());
        return configTaxMapper.insertConfigTax(configTax);
    }

    /**
     * 修改税率
     * 
     * @param configTax 税率
     * @return 结果
     */
    @Override
    public int updateConfigTax(ConfigTax configTax)
    {
        configTax.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configTax.setUpdateTime(DateUtils.getNowDate());
        return configTaxMapper.updateConfigTax(configTax);
    }

    /**
     * 删除税率对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigTaxByIds(String ids)
    {
        return configTaxMapper.deleteConfigTaxByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除税率信息
     * 
     * @param id 税率ID
     * @return 结果
     */
    @Override
    public int deleteConfigTaxById(Long id)
    {
        return configTaxMapper.deleteConfigTaxById(id);
    }
}
