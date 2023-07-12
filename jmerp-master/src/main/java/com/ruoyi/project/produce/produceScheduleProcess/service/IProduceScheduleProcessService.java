package com.ruoyi.project.produce.produceScheduleProcess.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.produce.produceScheduleProcess.domain.ProduceScheduleProcess;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 生产排程工序Service接口
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public interface IProduceScheduleProcessService 
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
     * 批量删除生产排程工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceScheduleProcessByIds(String ids);

    /**
     * 删除生产排程工序信息
     * 
     * @param id 生产排程工序ID
     * @return 结果
     */
    public int deleteProduceScheduleProcessById(Long id);

    /**
     * 添加工序
     * @Author 方舟
     * @Date 2021/5/14 15:33:56
     **/
    public ProduceScheduleProcess createScheduleProcess(ProduceScheduleProcess produceScheduleProcess);

    /**
     * 完工
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    public ProduceScheduleProcess workDone(ProduceScheduleProcess produceScheduleProcess);
    /**
     * 撤回
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    public ProduceScheduleProcess workBack(ProduceScheduleProcess produceScheduleProcess);

    /**
     * N天生产数量
     * @Author 方舟
     * @Date 2021/6/3 9:38:39
     **/
    public List<ProduceScheduleProcess> produceQtyChartData(ProduceScheduleProcess produceScheduleProcess);
}
