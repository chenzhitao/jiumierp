package com.ruoyi.project.config.configProduct.service;

import java.util.List;

import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;

/**
 * 产品配置Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigProductService 
{
    /**
     * 查询产品配置
     * 
     * @param id 产品配置ID
     * @return 产品配置
     */
    public ConfigProduct selectConfigProductById(Long id);

    /**
     * 查询产品配置列表
     * 
     * @param configProduct 产品配置
     * @return 产品配置集合
     */
    public List<ConfigProduct> selectConfigProductList(ConfigProduct configProduct);

    /**
     * 新增产品配置
     * 
     * @param configProduct 产品配置
     * @return 结果
     */
    public int insertConfigProduct(ConfigProduct configProduct);

    /**
     * 修改产品配置
     * 
     * @param configProduct 产品配置
     * @return 结果
     */
    public int updateConfigProduct(ConfigProduct configProduct);

    /**
     * 批量删除产品配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigProductByIds(String ids);

    /**
     * 删除产品配置信息
     * 
     * @param id 产品配置ID
     * @return 结果
     */
    public int deleteConfigProductById(Long id);

    /**
     * 导入数据
     *
     * @param configProductList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigProduct(List<ConfigProduct> configProductList, Boolean isUpdateSupport);
}
