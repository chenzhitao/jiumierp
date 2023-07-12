package com.ruoyi.project.config.configSupplierContact.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 供应商联系人Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigSupplierContactService 
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
     * 批量删除供应商联系人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigSupplierContactByIds(String ids);

    /**
     * 删除供应商联系人信息
     * 
     * @param id 供应商联系人ID
     * @return 结果
     */
    public int deleteConfigSupplierContactById(Long id);

    /**
     * 设为默认
     * @Author 方舟
     * @Date 2021/4/14 15:04:29
     **/
    public int setDefaultContact(ConfigSupplierContact configSupplierContact);

    /**
     * 获取默认联系人
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    public ConfigSupplierContact getDefaultContact(ConfigSupplierContact configSupplierContact);
}
