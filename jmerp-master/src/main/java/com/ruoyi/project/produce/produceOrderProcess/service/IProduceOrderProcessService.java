package com.ruoyi.project.produce.produceOrderProcess.service;

import java.util.List;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;

/**
 * 施工工序Service接口
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public interface IProduceOrderProcessService 
{
    /**
     * 查询施工工序
     * 
     * @param id 施工工序ID
     * @return 施工工序
     */
    public ProduceOrderProcess selectProduceOrderProcessById(Long id);

    /**
     * 查询施工工序列表
     * 
     * @param produceOrderProcess 施工工序
     * @return 施工工序集合
     */
    public List<ProduceOrderProcess> selectProduceOrderProcessList(ProduceOrderProcess produceOrderProcess);

    /**
     * 新增施工工序
     * 
     * @param produceOrderProcess 施工工序
     * @return 结果
     */
    public int insertProduceOrderProcess(ProduceOrderProcess produceOrderProcess);

    /**
     * 修改施工工序
     * 
     * @param produceOrderProcess 施工工序
     * @return 结果
     */
    public int updateProduceOrderProcess(ProduceOrderProcess produceOrderProcess);

    /**
     * 批量删除施工工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceOrderProcessByIds(String ids);

    /**
     * 删除施工工序信息
     * 
     * @param id 施工工序ID
     * @return 结果
     */
    public int deleteProduceOrderProcessById(Long id);

    /**
     * 找到关联外发的所有工序
     *
     * @return 结果
     */
    public List<Long> findFullProduceOrderProcessIds(String ids);
}
