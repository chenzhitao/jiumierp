package com.ruoyi.project.config.configSupplier.service;

import java.util.List;

import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;

/**
 * 供应商信息Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigSupplierService 
{
    /**
     * 查询供应商信息
     * 
     * @param id 供应商信息ID
     * @return 供应商信息
     */
    public ConfigSupplier selectConfigSupplierById(Long id);

    /**
     * 查询供应商信息列表
     * 
     * @param configSupplier 供应商信息
     * @return 供应商信息集合
     */
    public List<ConfigSupplier> selectConfigSupplierList(ConfigSupplier configSupplier);

    /**
     * 新增供应商信息
     * 
     * @param configSupplier 供应商信息
     * @return 结果
     */
    public int insertConfigSupplier(ConfigSupplier configSupplier);

    /**
     * 修改供应商信息
     * 
     * @param configSupplier 供应商信息
     * @return 结果
     */
    public int updateConfigSupplier(ConfigSupplier configSupplier);

    /**
     * 批量删除供应商信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigSupplierByIds(String ids);

    /**
     * 删除供应商信息信息
     * 
     * @param id 供应商信息ID
     * @return 结果
     */
    public int deleteConfigSupplierById(Long id);

    /**
     * 导入数据
     *
     * @param configSupplierList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigSupplier(List<ConfigSupplier> configSupplierList, Boolean isUpdateSupport);
}
