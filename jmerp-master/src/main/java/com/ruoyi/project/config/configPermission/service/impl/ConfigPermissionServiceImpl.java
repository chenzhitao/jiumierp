package com.ruoyi.project.config.configPermission.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configPermission.mapper.ConfigPermissionMapper;
import com.ruoyi.project.config.configPermission.domain.ConfigPermission;
import com.ruoyi.project.config.configPermission.service.IConfigPermissionService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 数据权限Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigPermissionServiceImpl implements IConfigPermissionService 
{
    @Autowired
    private ConfigPermissionMapper configPermissionMapper;

    /**
     * 查询数据权限
     * 
     * @param id 数据权限ID
     * @return 数据权限
     */
    @Override
    public ConfigPermission selectConfigPermissionById(Long id)
    {
        return configPermissionMapper.selectConfigPermissionById(id);
    }

    /**
     * 查询数据权限列表
     * 
     * @param configPermission 数据权限
     * @return 数据权限
     */
    @Override
    public List<ConfigPermission> selectConfigPermissionList(ConfigPermission configPermission)
    {
        List<ConfigPermission> list = new ArrayList<ConfigPermission>();
        if(!StringUtils.isEmpty(configPermission.getRzyValue1())&&"user".equals(configPermission.getRzyValue1())){
            list = configPermissionMapper.selectConfigUserPermissionList(configPermission);
        }else{
            list = configPermissionMapper.selectConfigPermissionList(configPermission);
        }
        return list;
    }

    /**
     * 新增数据权限
     * 
     * @param configPermission 数据权限
     * @return 结果
     */
    @Override
    public int insertConfigPermission(ConfigPermission configPermission)
    {
        configPermission.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configPermission.setCreateTime(DateUtils.getNowDate());
        configPermission.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configPermission.setUpdateTime(DateUtils.getNowDate());
        return configPermissionMapper.insertConfigPermission(configPermission);
    }

    /**
     * 修改数据权限
     * 
     * @param configPermission 数据权限
     * @return 结果
     */
    @Override
    public int updateConfigPermission(ConfigPermission configPermission)
    {
        configPermission.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configPermission.setUpdateTime(DateUtils.getNowDate());
        return configPermissionMapper.updateConfigPermission(configPermission);
    }

    /**
     * 删除数据权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigPermissionByIds(String ids)
    {
        return configPermissionMapper.deleteConfigPermissionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除数据权限信息
     * 
     * @param id 数据权限ID
     * @return 结果
     */
    @Override
    public int deleteConfigPermissionById(Long id)
    {
        return configPermissionMapper.deleteConfigPermissionById(id);
    }

    /**
     *
     * @Author 方舟
     * @Date 2021/4/14 21:41:17
     **/
    @Override
    public int saveConfigPermission(ConfigPermission configPermission){
        int result = configPermissionMapper.deleteConfigPermissionByUserId(configPermission);
        List<Long> customerIdList = new ArrayList<Long>();
        List<Long> supplierIdList = new ArrayList<Long>();
        if(StringUtils.isEmpty(configPermission.getCustomerIds())){
            //configPermission.setCustomerRight(null);
        }else{
            String[] customers = Convert.toStrArray(configPermission.getCustomerIds());
            List<Long> list = new ArrayList<Long>();
            for(int i=0;i<customers.length;i++){
                list.add(Long.parseLong(customers[i]));
            }
            customerIdList = list;
            //configPermission.setCustomerRight(list);
        }
        if(StringUtils.isEmpty(configPermission.getSupplierIds())){
            //configPermission.setSupplierRight(null);
        }else{
            String[] suppliers = Convert.toStrArray(configPermission.getSupplierIds());
            List<Long> list = new ArrayList<Long>();
            for(int i=0;i<suppliers.length;i++){
                list.add(Long.parseLong(suppliers[i]));
            }
            supplierIdList = list;
            //configPermission.setSupplierRight(list);
        }
        List<ConfigPermission> insertList = new ArrayList<ConfigPermission>();
        if(null!=customerIdList){
            for (int i=0;i<customerIdList.size();i++){
                ConfigPermission vo = setConfigPermissionVO(configPermission,customerIdList.get(i),null);
                insertList.add(vo);
            }
        }
        if(null!=supplierIdList){
            for (int i=0;i<supplierIdList.size();i++){
                ConfigPermission vo = setConfigPermissionVO(configPermission,null,supplierIdList.get(i));
                insertList.add(vo);
            }
        }
        for (int i=0;i<insertList.size();i++){
            configPermissionMapper.insertConfigPermission(insertList.get(i));
        }
        return result;
    }

    //组装VO
    private ConfigPermission setConfigPermissionVO(ConfigPermission configPermission,Long customerId,Long supplierId){
        ConfigPermission rightVO = new ConfigPermission();
        rightVO.setUserId(configPermission.getUserId());
        rightVO.setCustomerId(customerId);
        rightVO.setSupplierId(supplierId);
        rightVO.setCreateBy(ShiroUtils.getSysUser().getUserName());
        rightVO.setCreateTime(DateUtils.getNowDate());
        rightVO.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        rightVO.setUpdateTime(DateUtils.getNowDate());
        return rightVO;
    }
}
