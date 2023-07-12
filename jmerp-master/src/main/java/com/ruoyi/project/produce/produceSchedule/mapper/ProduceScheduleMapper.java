package com.ruoyi.project.produce.produceSchedule.mapper;

import java.util.List;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceScheduleExport;

/**
 * 生产排程Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public interface ProduceScheduleMapper 
{
    /**
     * 查询生产排程
     * 
     * @param id 生产排程ID
     * @return 生产排程
     */
    public ProduceSchedule selectProduceScheduleById(Long id);

    /**
     * 查询生产排程列表
     * 
     * @param produceSchedule 生产排程
     * @return 生产排程集合
     */
    public List<ProduceSchedule> selectProduceScheduleList(ProduceSchedule produceSchedule);
    public List<ProduceScheduleExport> selectProduceScheduleExportList(ProduceSchedule produceSchedule);

    /**
     * 新增生产排程
     * 
     * @param produceSchedule 生产排程
     * @return 结果
     */
    public int insertProduceSchedule(ProduceSchedule produceSchedule);

    /**
     * 修改生产排程
     * 
     * @param produceSchedule 生产排程
     * @return 结果
     */
    public int updateProduceSchedule(ProduceSchedule produceSchedule);
    public int deleteProduceScheduleProcessById(Long id);

    /**
     * 删除生产排程
     * 
     * @param id 生产排程ID
     * @return 结果
     */
    public int deleteProduceScheduleById(Long id);

    /**
     * 批量删除生产排程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceScheduleByIds(String[] ids);
}
