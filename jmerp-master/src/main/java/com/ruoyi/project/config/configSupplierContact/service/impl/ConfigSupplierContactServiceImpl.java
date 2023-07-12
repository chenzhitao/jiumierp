package com.ruoyi.project.config.configSupplierContact.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configSupplierContact.mapper.ConfigSupplierContactMapper;
import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.config.configSupplierContact.service.IConfigSupplierContactService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 供应商联系人Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigSupplierContactServiceImpl implements IConfigSupplierContactService 
{
    @Autowired
    private ConfigSupplierContactMapper configSupplierContactMapper;

    /**
     * 查询供应商联系人
     * 
     * @param id 供应商联系人ID
     * @return 供应商联系人
     */
    @Override
    public ConfigSupplierContact selectConfigSupplierContactById(Long id)
    {
        return configSupplierContactMapper.selectConfigSupplierContactById(id);
    }

    /**
     * 查询供应商联系人列表
     * 
     * @param configSupplierContact 供应商联系人
     * @return 供应商联系人
     */
    @Override
    public List<ConfigSupplierContact> selectConfigSupplierContactList(ConfigSupplierContact configSupplierContact)
    {
        return configSupplierContactMapper.selectConfigSupplierContactList(configSupplierContact);
    }

    /**
     * 新增供应商联系人
     * 
     * @param configSupplierContact 供应商联系人
     * @return 结果
     */
    @Override
    public int insertConfigSupplierContact(ConfigSupplierContact configSupplierContact)
    {
        return configSupplierContactMapper.insertConfigSupplierContact(configSupplierContact);
    }

    /**
     * 修改供应商联系人
     * 
     * @param configSupplierContact 供应商联系人
     * @return 结果
     */
    @Override
    public int updateConfigSupplierContact(ConfigSupplierContact configSupplierContact)
    {
        return configSupplierContactMapper.updateConfigSupplierContact(configSupplierContact);
    }

    /**
     * 删除供应商联系人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigSupplierContactByIds(String ids)
    {
        return configSupplierContactMapper.deleteConfigSupplierContactByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除供应商联系人信息
     * 
     * @param id 供应商联系人ID
     * @return 结果
     */
    @Override
    public int deleteConfigSupplierContactById(Long id)
    {
        return configSupplierContactMapper.deleteConfigSupplierContactById(id);
    }

    /**
     * 设为默认
     * @Author 方舟
     * @Date 2021/4/14 15:04:29
     **/
    @Override
    public int setDefaultContact(ConfigSupplierContact configSupplierContact){
        configSupplierContactMapper.setAllContactN(configSupplierContact);
        return configSupplierContactMapper.setDefaultContact(configSupplierContact);
    }

    /**
     * 获取默认联系人
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @Override
    public ConfigSupplierContact getDefaultContact(ConfigSupplierContact configSupplierContact){
        ConfigSupplierContact resultVO = new ConfigSupplierContact();
        List<ConfigSupplierContact> list = configSupplierContactMapper.selectConfigSupplierContactList(configSupplierContact);
        if(list.size()==1){
            resultVO = list.get(0);
        }
        return resultVO;
    }
}
