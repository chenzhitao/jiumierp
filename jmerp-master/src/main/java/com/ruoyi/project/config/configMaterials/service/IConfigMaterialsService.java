package com.ruoyi.project.config.configMaterials.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 材料配置Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IConfigMaterialsService 
{
    /**
     * 查询材料配置
     * 
     * @param id 材料配置ID
     * @return 材料配置
     */
    public ConfigMaterials selectConfigMaterialsById(Long id);

    /**
     * 查询材料配置列表
     * 
     * @param configMaterials 材料配置
     * @return 材料配置集合
     */
    public List<ConfigMaterials> selectConfigMaterialsList(ConfigMaterials configMaterials);

    /**
     * 新增材料配置
     * 
     * @param configMaterials 材料配置
     * @return 结果
     */
    public int insertConfigMaterials(ConfigMaterials configMaterials);

    /**
     * 修改材料配置
     * 
     * @param configMaterials 材料配置
     * @return 结果
     */
    public int updateConfigMaterials(ConfigMaterials configMaterials);

    /**
     * 批量删除材料配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigMaterialsByIds(String ids);

    /**
     * 删除材料配置信息
     * 
     * @param id 材料配置ID
     * @return 结果
     */
    public int deleteConfigMaterialsById(Long id);

    /**
     * 导入数据
     *
     * @param configMaterialsList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importConfigMaterials(List<ConfigMaterials> configMaterialsList, Boolean isUpdateSupport);

    /**
     * 根据ID查找完整信息
     * @Author 方舟
     * @Date 2021/5/10 12:03:50
     **/
    public ConfigMaterials getMaterialsBaseInfo(ConfigMaterials configMaterials);
}
