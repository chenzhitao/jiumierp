package com.ruoyi.project.produce.produceReport.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.produce.produceOrder.mapper.ProduceOrderMapper;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.mapper.ProduceOrderProcessMapper;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import com.ruoyi.project.produce.produceSchedule.mapper.ProduceScheduleMapper;
import com.ruoyi.project.produce.produceScheduleProcess.domain.ProduceScheduleProcess;
import com.ruoyi.project.produce.produceScheduleProcess.mapper.ProduceScheduleProcessMapper;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.warehouse.warehouseInventory.domain.WarehouseInventory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.produce.produceReport.mapper.ProduceReportMapper;
import com.ruoyi.project.produce.produceReport.domain.ProduceReport;
import com.ruoyi.project.produce.produceReport.service.IProduceReportService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 产量上报Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Service
public class ProduceReportServiceImpl implements IProduceReportService 
{
    @Autowired
    private ProduceReportMapper produceReportMapper;
    @Autowired
    private ProduceScheduleProcessMapper produceScheduleProcessMapper;
    @Autowired
    private ProduceScheduleMapper produceScheduleMapper;
    @Autowired
    private ProduceOrderProcessMapper produceOrderProcessMapper;
    @Autowired
    private ProduceOrderMapper produceOrderMapper;

    /**
     * 查询产量上报
     * 
     * @param id 产量上报ID
     * @return 产量上报
     */
    @Override
    public ProduceReport selectProduceReportById(Long id)
    {
        return produceReportMapper.selectProduceReportById(id);
    }

    /**
     * 查询产量上报列表
     * 
     * @param produceReport 产量上报
     * @return 产量上报
     */
    @Override
    public List<ProduceReport> selectProduceReportList(ProduceReport produceReport)
    {
        return produceReportMapper.selectProduceReportList(produceReport);
    }

    /**
     * 新增产量上报
     * 
     * @param produceReport 产量上报
     * @return 结果
     */
    @Override
    public int insertProduceReport(ProduceReport produceReport)
    {
        produceReport.setCreateBy(ShiroUtils.getSysUser().getUserName());
        produceReport.setCreateTime(DateUtils.getNowDate());
        produceReport.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        produceReport.setUpdateTime(DateUtils.getNowDate());
        return produceReportMapper.insertProduceReport(produceReport);
    }

    /**
     * 修改产量上报
     * 
     * @param produceReport 产量上报
     * @return 结果
     */
    @Override
    public int updateProduceReport(ProduceReport produceReport)
    {
        produceReport.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        produceReport.setUpdateTime(DateUtils.getNowDate());
        return produceReportMapper.updateProduceReport(produceReport);
    }

    /**
     * 删除产量上报对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProduceReportByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            ProduceReport param = new ProduceReport();
            param.setId(Long.parseLong(arr[i]));
            param.setStatus("delete");
            result = produceReportMapper.updateProduceReport(param);
        }
        return result;
        //return produceReportMapper.deleteProduceReportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产量上报信息
     * 
     * @param id 产量上报ID
     * @return 结果
     */
    @Override
    public int deleteProduceReportById(Long id)
    {
        int result = 0;
        ProduceReport param = new ProduceReport();
        param.setId(id);
        param.setStatus("delete");
        result = produceReportMapper.updateProduceReport(param);
        return result;
        //return produceReportMapper.deleteProduceReportById(id);
    }

    /**
     * 报产
     * @Author 方舟
     * @Date 2021/5/17 10:53:28
     **/
    @Override
    public ProduceReport reportWork(ProduceReport produceReport){
        String[] ids = Convert.toStrArray(produceReport.getIds());
        for (int i=0;i<ids.length;i++){
            Long id = Long.parseLong(ids[i]);
            ProduceScheduleProcess produceScheduleProcess = produceScheduleProcessMapper.selectProduceScheduleProcessById(id);
            if(null!=produceScheduleProcess){
                Integer minusQty = produceReport.getQty();
                if(produceReport.getQty().equals(-1)){
                    minusQty = produceScheduleProcess.getProduceQty() - produceScheduleProcess.getQty();
                }
                if(minusQty<0){
                    minusQty = 0;
                }
                Integer saveQty = minusQty + produceScheduleProcess.getQty();
                produceScheduleProcess.setQty(saveQty);
                if(!StringUtils.isEmpty(produceReport.getRzyValue2())&&"warehouse".equals(produceReport.getRzyValue2())){
                    produceScheduleProcess.setProduceStatus("warehouse");
                }else{
                    if(saveQty>=produceScheduleProcess.getProduceQty()){
                        if(!StringUtils.isEmpty(produceReport.getRzyValue1())&&produceReport.getRzyValue1().equals("warehouse")){
                            produceScheduleProcess.setProduceStatus("warehouse");
                        }else{
                            produceScheduleProcess.setProduceStatus("report");
                        }
                    }else{
                        produceScheduleProcess.setProduceStatus("schedule");
                    }
                }
                produceScheduleProcessMapper.updateProduceScheduleProcess(produceScheduleProcess);
                //报产记录
                ProduceReport saveVO = new ProduceReport();
                BeanUtils.copyProperties(produceScheduleProcess, saveVO);
                saveVO.setProduceScheduleProcessId(id);
                saveVO.setStatus("normal");
                saveVO.setReportDate(DateUtils.getNowDate());
                saveVO.setQty(minusQty);
                insertProduceReport(saveVO);
            }
        }
        return produceReport;
    }

    /**
     * 退回排程
     * @Author 方舟
     * @Date 2021/5/17 10:53:28
     **/
    @Override
    public ProduceReport toSchedule(ProduceReport produceReport){
        String[] ids = Convert.toStrArray(produceReport.getIds());
        for (int i=0;i<ids.length;i++){
            Long id = Long.parseLong(ids[i]);
            ProduceScheduleProcess produceScheduleProcess = produceScheduleProcessMapper.selectProduceScheduleProcessById(id);
            if(null!=produceScheduleProcess){
                //报产记录
                ProduceReport saveVO = new ProduceReport();
                BeanUtils.copyProperties(produceScheduleProcess, saveVO);
                saveVO.setQty(0-produceScheduleProcess.getQty());
                saveVO.setProduceScheduleProcessId(id);
                saveVO.setStatus("normal");
                saveVO.setReportDate(DateUtils.getNowDate());
                insertProduceReport(saveVO);
                //
                produceScheduleProcess.setQty(0);
                produceScheduleProcess.setProduceStatus("schedule");
                produceScheduleProcessMapper.updateProduceScheduleProcess(produceScheduleProcess);
            }
        }
        return produceReport;
    }

    /**
     * 入库
     * @Author 方舟
     * @Date 2021/5/17 10:53:28
     **/
    @Override
    public ProduceReport inbound(ProduceReport produceReport){
        String[] ids = Convert.toStrArray(produceReport.getIds());
        for (int i=0;i<ids.length;i++){
            Long id = Long.parseLong(ids[i]);
            ProduceScheduleProcess produceScheduleProcess = produceScheduleProcessMapper.selectProduceScheduleProcessById(id);
            produceScheduleProcess.setProduceStatus("warehouse");
            produceScheduleProcess.setQty(produceScheduleProcess.getProduceQty());
            produceScheduleProcessMapper.updateProduceScheduleProcess(produceScheduleProcess);
            setHeaderInbound(produceScheduleProcess);
            setupProduceOrder(produceScheduleProcess);
        }
        return produceReport;
    }

    //入库更新施工单状态
    private void setupProduceOrder(ProduceScheduleProcess produceScheduleProcess){
        Long id = produceScheduleProcess.getProduceOrderProcessId();
        ProduceOrderProcess produceOrderProcess = new ProduceOrderProcess();
        produceOrderProcess.setId(id);
        produceOrderProcess.setProduceStatus("warehouse");
        produceOrderProcessMapper.updateProduceOrderProcess(produceOrderProcess);
    }

    /**
     * 如果所有的都入库了,头也入库
     * @Author 方舟
     * @Date 2021/5/17 15:24:04
    **/
    private void setHeaderInbound(ProduceScheduleProcess produceScheduleProcess){

        ProduceScheduleProcess paramVO = new ProduceScheduleProcess();
        paramVO.setProduceScheduleId(produceScheduleProcess.getProduceScheduleId());
        List<ProduceScheduleProcess> list = produceScheduleProcessMapper.selectProduceScheduleProcessList(paramVO);
        boolean flag = true;
        for (int i=0;i<list.size();i++){
            if(!"warehouse".equals(list.get(i).getProduceStatus())){
                flag = false;
                break;
            }
        }
        if(flag){
            ProduceSchedule produceSchedule = new ProduceSchedule();
            produceSchedule.setProduceStatus("warehouse");
            produceSchedule.setId(produceScheduleProcess.getProduceScheduleId());
            produceScheduleMapper.updateProduceSchedule(produceSchedule);
        }
    }
}
