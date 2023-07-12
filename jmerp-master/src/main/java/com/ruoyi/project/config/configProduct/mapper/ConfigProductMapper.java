package com.ruoyi.project.config.configProduct.mapper;

import java.util.List;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;

/**
 * 产品配置Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigProductMapper 
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
    public int updateConfigProductPart(ConfigProduct configProduct);

    /**
     * 删除产品配置
     * 
     * @param id 产品配置ID
     * @return 结果
     */
    public int deleteConfigProductById(Long id);
    public int deleteConfigProductByParentId(Long parentId);

    /**
     * 批量删除产品配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigProductByIds(String[] ids);
    public int deleteConfigProductByParentIds(String[] parentIds);
}
