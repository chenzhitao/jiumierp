package com.ruoyi.project.config.configProcess.mapper;

import java.util.List;
import com.ruoyi.project.config.configProcess.domain.ConfigProcess;

/**
 * 工序配置Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface ConfigProcessMapper 
{
    /**
     * 查询工序配置
     * 
     * @param id 工序配置ID
     * @return 工序配置
     */
    public ConfigProcess selectConfigProcessById(Long id);

    /**
     * 查询工序配置列表
     * 
     * @param configProcess 工序配置
     * @return 工序配置集合
     */
    public List<ConfigProcess> selectConfigProcessList(ConfigProcess configProcess);

    /**
     * 新增工序配置
     * 
     * @param configProcess 工序配置
     * @return 结果
     */
    public int insertConfigProcess(ConfigProcess configProcess);

    /**
     * 修改工序配置
     * 
     * @param configProcess 工序配置
     * @return 结果
     */
    public int updateConfigProcess(ConfigProcess configProcess);

    /**
     * 删除工序配置
     * 
     * @param id 工序配置ID
     * @return 结果
     */
    public int deleteConfigProcessById(Long id);

    /**
     * 批量删除工序配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigProcessByIds(String[] ids);
}
