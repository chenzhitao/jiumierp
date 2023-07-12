package com.ruoyi.project.produce.produceReport.mapper;

import java.util.List;
import com.ruoyi.project.produce.produceReport.domain.ProduceReport;

/**
 * 产量上报Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public interface ProduceReportMapper 
{
    /**
     * 查询产量上报
     * 
     * @param id 产量上报ID
     * @return 产量上报
     */
    public ProduceReport selectProduceReportById(Long id);

    /**
     * 查询产量上报列表
     * 
     * @param produceReport 产量上报
     * @return 产量上报集合
     */
    public List<ProduceReport> selectProduceReportList(ProduceReport produceReport);

    /**
     * 新增产量上报
     * 
     * @param produceReport 产量上报
     * @return 结果
     */
    public int insertProduceReport(ProduceReport produceReport);

    /**
     * 修改产量上报
     * 
     * @param produceReport 产量上报
     * @return 结果
     */
    public int updateProduceReport(ProduceReport produceReport);

    /**
     * 删除产量上报
     * 
     * @param id 产量上报ID
     * @return 结果
     */
    public int deleteProduceReportById(Long id);

    /**
     * 批量删除产量上报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceReportByIds(String[] ids);
}
