package com.ruoyi.project.produce.produceScheduleProcess.mapper;

import java.util.List;
import com.ruoyi.project.produce.produceScheduleProcess.domain.ProduceScheduleProcess;

/**
 * 生产排程工序Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public interface ProduceScheduleProcessMapper 
{
    /**
     * 查询生产排程工序
     * 
     * @param id 生产排程工序ID
     * @return 生产排程工序
     */
    public ProduceScheduleProcess selectProduceScheduleProcessById(Long id);

    /**
     * 查询生产排程工序列表
     * 
     * @param produceScheduleProcess 生产排程工序
     * @return 生产排程工序集合
     */
    public List<ProduceScheduleProcess> selectProduceScheduleProcessList(ProduceScheduleProcess produceScheduleProcess);

    /**
     * 新增生产排程工序
     * 
     * @param produceScheduleProcess 生产排程工序
     * @return 结果
     */
    public int insertProduceScheduleProcess(ProduceScheduleProcess produceScheduleProcess);

    /**
     * 修改生产排程工序
     * 
     * @param produceScheduleProcess 生产排程工序
     * @return 结果
     */
    public int updateProduceScheduleProcess(ProduceScheduleProcess produceScheduleProcess);

    /**
     * 删除生产排程工序
     * 
     * @param id 生产排程工序ID
     * @return 结果
     */
    public int deleteProduceScheduleProcessById(Long id);

    /**
     * 批量删除生产排程工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceScheduleProcessByIds(String[] ids);

    /**
     * N天内数据
     * @Author 方舟
     * @Date 2021/6/3 9:46:55
    **/
    public List<ProduceScheduleProcess> selectProduceQtyList(ProduceScheduleProcess produceScheduleProcess);
}
