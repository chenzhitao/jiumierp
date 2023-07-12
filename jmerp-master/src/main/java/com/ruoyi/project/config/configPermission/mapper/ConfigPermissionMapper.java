package com.ruoyi.project.config.configPermission.mapper;

import java.util.List;
import com.ruoyi.project.config.configPermission.domain.ConfigPermission;

/**
 * 数据权限Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigPermissionMapper 
{
    /**
     * 查询数据权限
     * 
     * @param id 数据权限ID
     * @return 数据权限
     */
    public ConfigPermission selectConfigPermissionById(Long id);

    /**
     * 查询数据权限列表
     * 
     * @param configPermission 数据权限
     * @return 数据权限集合
     */
    public List<ConfigPermission> selectConfigPermissionList(ConfigPermission configPermission);
    public List<ConfigPermission> selectConfigUserPermissionList(ConfigPermission configPermission);

    /**
     * 新增数据权限
     * 
     * @param configPermission 数据权限
     * @return 结果
     */
    public int insertConfigPermission(ConfigPermission configPermission);

    /**
     * 修改数据权限
     * 
     * @param configPermission 数据权限
     * @return 结果
     */
    public int updateConfigPermission(ConfigPermission configPermission);

    /**
     * 删除数据权限
     * 
     * @param id 数据权限ID
     * @return 结果
     */
    public int deleteConfigPermissionById(Long id);

    /**
     * 批量删除数据权限
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigPermissionByIds(String[] ids);

    /**
     * 删除用户所有权限
     * @Author 方舟
     * @Date 2021/4/14 21:43:26
    **/
    public int deleteConfigPermissionByUserId(ConfigPermission configPermission);
}
