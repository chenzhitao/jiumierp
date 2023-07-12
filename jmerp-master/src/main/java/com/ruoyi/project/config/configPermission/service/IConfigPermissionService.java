package com.ruoyi.project.config.configPermission.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configPermission.domain.ConfigPermission;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 数据权限Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigPermissionService 
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
     * 批量删除数据权限
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigPermissionByIds(String ids);

    /**
     * 删除数据权限信息
     * 
     * @param id 数据权限ID
     * @return 结果
     */
    public int deleteConfigPermissionById(Long id);

    /**
     *
     * @Author 方舟
     * @Date 2021/4/14 21:41:17
     **/
    public int saveConfigPermission(ConfigPermission configPermission);
}
