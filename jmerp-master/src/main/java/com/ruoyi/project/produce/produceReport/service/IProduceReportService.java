package com.ruoyi.project.produce.produceReport.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.produce.produceReport.domain.ProduceReport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 产量上报Service接口
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public interface IProduceReportService 
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
     * 批量删除产量上报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceReportByIds(String ids);

    /**
     * 删除产量上报信息
     * 
     * @param id 产量上报ID
     * @return 结果
     */
    public int deleteProduceReportById(Long id);

    /**
     * 报产
     * @Author 方舟
     * @Date 2021/5/17 10:53:28
     **/
    public ProduceReport reportWork(ProduceReport produceReport);
    /**
     * 退回排程
     * @Author 方舟
     * @Date 2021/5/17 10:53:28
     **/
    public ProduceReport toSchedule(ProduceReport produceReport);
    /**
     * 入库
     * @Author 方舟
     * @Date 2021/5/17 10:53:28
     **/
    public ProduceReport inbound(ProduceReport produceReport);
}
