package com.ruoyi.project.produce.produceScheduleProcess.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceOrder.mapper.ProduceOrderMapper;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.mapper.ProduceOrderProcessMapper;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.produce.produceScheduleProcess.mapper.ProduceScheduleProcessMapper;
import com.ruoyi.project.produce.produceScheduleProcess.domain.ProduceScheduleProcess;
import com.ruoyi.project.produce.produceScheduleProcess.service.IProduceScheduleProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 生产排程工序Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Service
public class ProduceScheduleProcessServiceImpl implements IProduceScheduleProcessService 
{
    @Autowired
    private ProduceScheduleProcessMapper produceScheduleProcessMapper;
    @Autowired
    private ProduceOrderProcessMapper produceOrderProcessMapper;
    @Autowired
    private ProduceOrderMapper produceOrderMapper;

    /**
     * 查询生产排程工序
     * 
     * @param id 生产排程工序ID
     * @return 生产排程工序
     */
    @Override
    public ProduceScheduleProcess selectProduceScheduleProcessById(Long id)
    {
        return produceScheduleProcessMapper.selectProduceScheduleProcessById(id);
    }

    /**
     * 查询生产排程工序列表
     * 
     * @param produceScheduleProcess 生产排程工序
     * @return 生产排程工序
     */
    @Override
    public List<ProduceScheduleProcess> selectProduceScheduleProcessList(ProduceScheduleProcess produceScheduleProcess)
    {
        return produceScheduleProcessMapper.selectProduceScheduleProcessList(produceScheduleProcess);
    }

    /**
     * 新增生产排程工序
     * 
     * @param produceScheduleProcess 生产排程工序
     * @return 结果
     */
    @Override
    public int insertProduceScheduleProcess(ProduceScheduleProcess produceScheduleProcess)
    {
        return produceScheduleProcessMapper.insertProduceScheduleProcess(produceScheduleProcess);
    }

    /**
     * 修改生产排程工序
     * 
     * @param produceScheduleProcess 生产排程工序
     * @return 结果
     */
    @Override
    public int updateProduceScheduleProcess(ProduceScheduleProcess produceScheduleProcess)
    {
        return produceScheduleProcessMapper.updateProduceScheduleProcess(produceScheduleProcess);
    }

    /**
     * 删除生产排程工序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProduceScheduleProcessByIds(String ids)
    {
        return produceScheduleProcessMapper.deleteProduceScheduleProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产排程工序信息
     * 
     * @param id 生产排程工序ID
     * @return 结果
     */
    @Override
    public int deleteProduceScheduleProcessById(Long id)
    {
        return produceScheduleProcessMapper.deleteProduceScheduleProcessById(id);
    }

    /**
     * 添加工序
     * @Author 方舟
     * @Date 2021/5/14 15:33:56
     **/
    @Override
    public ProduceScheduleProcess createScheduleProcess(ProduceScheduleProcess produceScheduleProcess){
        String[] arr = Convert.toStrArray(produceScheduleProcess.getIds());
        for (int i=0;i<arr.length;i++){
            Long id = Long.parseLong(arr[i]);
            ProduceOrderProcess produceOrderProcess = produceOrderProcessMapper.selectProduceOrderProcessById(id);
            ProduceOrder produceOrder = produceOrderMapper.selectProduceOrderById(produceOrderProcess.getProduceOrderId());
            ProduceScheduleProcess saveVO = new ProduceScheduleProcess();
            BeanUtils.copyProperties(produceOrder, saveVO);
            BeanUtils.copyProperties(produceOrderProcess, saveVO);
            saveVO.setProduceOrderProcessId(id);
            saveVO.setProduceScheduleId(produceScheduleProcess.getProduceScheduleId());
            saveVO.setQty(0);
            saveVO.setProduceQty(produceOrderProcess.getOutQty());
            saveVO.setProductId(produceOrder.getProductId());
            insertProduceScheduleProcess(saveVO);
        }
        return produceScheduleProcess;
    }

    /**
     * 完工
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    @Override
    public ProduceScheduleProcess workDone(ProduceScheduleProcess produceScheduleProcess){
        produceScheduleProcess.setProduceStatus("schedule");
        if(null!=produceScheduleProcess.getId()){
            updateProduceScheduleProcess(produceScheduleProcess);
        }
        return produceScheduleProcess;
    }
    /**
     * 撤回
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    @Override
    public ProduceScheduleProcess workBack(ProduceScheduleProcess produceScheduleProcess){
        produceScheduleProcess.setProduceStatus("submit");
        if(null!=produceScheduleProcess.getId()){
            updateProduceScheduleProcess(produceScheduleProcess);
        }
        return produceScheduleProcess;
    }

    /**
     * N天生产数量
     * @Author 方舟
     * @Date 2021/6/3 9:38:39
     **/
    @Override
    public List<ProduceScheduleProcess> produceQtyChartData(ProduceScheduleProcess produceScheduleProcess){
        List<ProduceScheduleProcess> list = new ArrayList<ProduceScheduleProcess>();
        List<ProduceScheduleProcess> produceQtyList = produceScheduleProcessMapper.selectProduceQtyList(produceScheduleProcess);
        Integer days = Integer.parseInt(produceScheduleProcess.getRzyValue1());
        //填充日期
        for (int i=0;i<days;i++){
            ProduceScheduleProcess tempVO = new ProduceScheduleProcess();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.add(Calendar.DATE, i-days);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            //String rzyValue2 = sdf1.format(calendar1.getTime());
            tempVO.setProduceDate(calendar1.getTime());
            tempVO.setProduceQty(0);
            tempVO.setQty(0);
            tempVO.setRzyValue1(sdf1.format(calendar1.getTime()));
            tempVO.setRzyValue2(calendar1.getTime().toString());
            list.add(tempVO);
        }
        //填充数量
        for (int i=0;i<list.size();i++){
            for (int j=0;j<produceQtyList.size();j++){
                if(produceQtyList.get(j).getRzyValue2().equals(list.get(i).getRzyValue1())){
                    list.get(i).setProduceQty(produceQtyList.get(j).getProduceQty());
                    list.get(i).setQty(produceQtyList.get(j).getQty());
                }
            }
        }
        return list;
    }
}
