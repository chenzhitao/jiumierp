package com.ruoyi.project.produce.produceSchedule.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceScheduleExport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 生产排程Service接口
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public interface IProduceScheduleService 
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

    /**
     * 批量删除生产排程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceScheduleByIds(String ids);

    /**
     * 删除生产排程信息
     * 
     * @param id 生产排程ID
     * @return 结果
     */
    public int deleteProduceScheduleById(Long id);

    /**
     * 新增返回ID
     * @Author 方舟
     * @Date 2021/5/14 14:17:39
     **/
    public ProduceSchedule createProduceSchedule(ProduceSchedule produceSchedule);

    /**
     * 完工
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    public ProduceSchedule workDone(ProduceSchedule produceSchedule);
    /**
     * 报产
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    public ProduceSchedule workReport(ProduceSchedule produceSchedule);
    /**
     * 入库
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    public ProduceSchedule workInbound(ProduceSchedule produceSchedule);
    /**
     * 入库
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    public ProduceSchedule batchReport(ProduceSchedule produceSchedule);
}
