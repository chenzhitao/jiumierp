package com.ruoyi.project.config.configCustomer.mapper;

import java.util.List;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;

/**
 * 客户信息Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigCustomerMapper 
{
    /**
     * 查询客户信息
     * 
     * @param id 客户信息ID
     * @return 客户信息
     */
    public ConfigCustomer selectConfigCustomerById(Long id);

    /**
     * 查询客户信息列表
     * 
     * @param configCustomer 客户信息
     * @return 客户信息集合
     */
    public List<ConfigCustomer> selectConfigCustomerList(ConfigCustomer configCustomer);

    /**
     * 新增客户信息
     * 
     * @param configCustomer 客户信息
     * @return 结果
     */
    public int insertConfigCustomer(ConfigCustomer configCustomer);

    /**
     * 修改客户信息
     * 
     * @param configCustomer 客户信息
     * @return 结果
     */
    public int updateConfigCustomer(ConfigCustomer configCustomer);

    /**
     * 删除客户信息
     * 
     * @param id 客户信息ID
     * @return 结果
     */
    public int deleteConfigCustomerById(Long id);

    /**
     * 批量删除客户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigCustomerByIds(String[] ids);
}
