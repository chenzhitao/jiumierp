package com.ruoyi.project.config.configSupplierContact.mapper;

import java.util.List;
import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;

/**
 * 供应商联系人Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigSupplierContactMapper 
{
    /**
     * 查询供应商联系人
     * 
     * @param id 供应商联系人ID
     * @return 供应商联系人
     */
    public ConfigSupplierContact selectConfigSupplierContactById(Long id);

    /**
     * 查询供应商联系人列表
     * 
     * @param configSupplierContact 供应商联系人
     * @return 供应商联系人集合
     */
    public List<ConfigSupplierContact> selectConfigSupplierContactList(ConfigSupplierContact configSupplierContact);

    /**
     * 新增供应商联系人
     * 
     * @param configSupplierContact 供应商联系人
     * @return 结果
     */
    public int insertConfigSupplierContact(ConfigSupplierContact configSupplierContact);

    /**
     * 修改供应商联系人
     * 
     * @param configSupplierContact 供应商联系人
     * @return 结果
     */
    public int updateConfigSupplierContact(ConfigSupplierContact configSupplierContact);

    /**
     * 删除供应商联系人
     * 
     * @param id 供应商联系人ID
     * @return 结果
     */
    public int deleteConfigSupplierContactById(Long id);

    /**
     * 批量删除供应商联系人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigSupplierContactByIds(String[] ids);

    /**
     * 设为默认
     * @Author 方舟
     * @Date 2021/4/14 15:04:29
     **/
    public int setAllContactN(ConfigSupplierContact configSupplierContact);
    public int setDefaultContact(ConfigSupplierContact configSupplierContact);
}
