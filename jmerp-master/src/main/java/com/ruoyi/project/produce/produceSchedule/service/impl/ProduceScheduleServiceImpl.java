package com.ruoyi.project.produce.produceSchedule.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.produce.produceOrderMaterials.domain.ProduceOrderMaterials;
import com.ruoyi.project.produce.produceOrderMaterials.mapper.ProduceOrderMaterialsMapper;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.mapper.ProduceOrderProcessMapper;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceScheduleExport;
import com.ruoyi.project.produce.produceScheduleProcess.domain.ProduceScheduleProcess;
import com.ruoyi.project.produce.produceScheduleProcess.mapper.ProduceScheduleProcessMapper;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.produce.produceSchedule.mapper.ProduceScheduleMapper;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import com.ruoyi.project.produce.produceSchedule.service.IProduceScheduleService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 生产排程Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Service
public class ProduceScheduleServiceImpl implements IProduceScheduleService 
{
    @Autowired
    private ProduceScheduleMapper produceScheduleMapper;
    @Autowired
    private ProduceScheduleProcessMapper produceScheduleProcessMapper;
    @Autowired
    private ProduceOrderProcessMapper produceOrderProcessMapper;
    @Autowired
    private ProduceOrderMaterialsMapper produceOrderMaterialsMapper;

    /**
     * 查询生产排程
     * 
     * @param id 生产排程ID
     * @return 生产排程
     */
    @Override
    public ProduceSchedule selectProduceScheduleById(Long id)
    {
        return produceScheduleMapper.selectProduceScheduleById(id);
    }

    /**
     * 查询生产排程列表
     * 
     * @param produceSchedule 生产排程
     * @return 生产排程
     */
    @Override
    public List<ProduceSchedule> selectProduceScheduleList(ProduceSchedule produceSchedule)
    {
        return produceScheduleMapper.selectProduceScheduleList(produceSchedule);
    }
    @Override
    public List<ProduceScheduleExport> selectProduceScheduleExportList(ProduceSchedule produceSchedule)
    {
        List<ProduceScheduleExport> orglist = produceScheduleMapper.selectProduceScheduleExportList(produceSchedule);
        //补充材料
        for (int i=0;i<orglist.size();i++){
            ProduceScheduleExport pseVO = orglist.get(i);
            ProduceOrderMaterials produceOrderMaterials = new ProduceOrderMaterials();
            produceOrderMaterials.setProduceOrderId(pseVO.getProduceOrderId());
            List<ProduceOrderMaterials> pomList = produceOrderMaterialsMapper.selectProduceOrderMaterialsList(produceOrderMaterials);
            if(pomList.size()!=0){
                String materialsNames = "";
                String materialsSize = "";
                for (int j=0;j<pomList.size();j++){
                    ProduceOrderMaterials pomVO = pomList.get(j);
                    if(!"".equals(materialsNames)){
                        materialsNames += ", ";
                    }
                    materialsNames += pomVO.getMaterialsName();
                    if(!"".equals(materialsSize)){
                        materialsSize += ", ";
                    }
                    materialsSize += pomVO.getSizeLong()+"*"+pomVO.getSizeWidth();
                }
                pseVO.setMaterialsName(materialsNames);
                pseVO.setMaterialsSize(materialsSize);
            }
        }
        return orglist;
    }

    /**
     * 新增生产排程
     * 
     * @param produceSchedule 生产排程
     * @return 结果
     */
    @Override
    public int insertProduceSchedule(ProduceSchedule produceSchedule)
    {
        produceSchedule.setCreateBy(ShiroUtils.getSysUser().getUserName());
        produceSchedule.setCreateTime(DateUtils.getNowDate());
        produceSchedule.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        produceSchedule.setUpdateTime(DateUtils.getNowDate());
        return produceScheduleMapper.insertProduceSchedule(produceSchedule);
    }

    /**
     * 修改生产排程
     * 
     * @param produceSchedule 生产排程
     * @return 结果
     */
    @Override
    public int updateProduceSchedule(ProduceSchedule produceSchedule)
    {
        produceSchedule.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        produceSchedule.setUpdateTime(DateUtils.getNowDate());
        changeStatus(produceSchedule);
        return produceScheduleMapper.updateProduceSchedule(produceSchedule);
    }

    /**
     * 删除生产排程对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProduceScheduleByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            ProduceSchedule param = new ProduceSchedule();
            param.setId(Long.parseLong(arr[i]));
            param.setStatus("delete");
            result = produceScheduleMapper.updateProduceSchedule(param);
            //删除行
            produceScheduleMapper.deleteProduceScheduleProcessById(Long.parseLong(arr[i]));
        }
        return result;
        //return produceScheduleMapper.deleteProduceScheduleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产排程信息
     * 
     * @param id 生产排程ID
     * @return 结果
     */
    @Override
    public int deleteProduceScheduleById(Long id)
    {
        int result = 0;
        ProduceSchedule param = new ProduceSchedule();
        param.setId(id);
        param.setStatus("delete");
        result = produceScheduleMapper.updateProduceSchedule(param);
        //删除行
        produceScheduleMapper.deleteProduceScheduleProcessById(id);
        return result;
        //return produceScheduleMapper.deleteProduceScheduleById(id);
    }

    /**
     * 新增返回ID
     * @Author 方舟
     * @Date 2021/5/14 14:17:39
     **/
    @Override
    public ProduceSchedule createProduceSchedule(ProduceSchedule produceSchedule){
        produceSchedule.setCreateBy(ShiroUtils.getSysUser().getUserName());
        produceSchedule.setCreateTime(DateUtils.getNowDate());
        produceSchedule.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        produceSchedule.setUpdateTime(DateUtils.getNowDate());
        produceScheduleMapper.insertProduceSchedule(produceSchedule);
        return produceSchedule;
    }

    /**
     * 审批状态
     * @Author 方舟
     * @Date 2021/5/15 11:17:57
    **/
    private void changeStatus(ProduceSchedule produceSchedule){
        String editType = produceSchedule.getRzyValue1();
        if(!StringUtils.isEmpty(editType)){
            if("approve".equals(editType)){
                produceSchedule.setProduceStatus("submit");
                setStatusByHeaderId(produceSchedule.getId(),null,"submit");
            }else if("unapprove".equals(editType)){
                produceSchedule.setProduceStatus("draft");
                setStatusByHeaderId(produceSchedule.getId(),null,"draft");
            }
        }
    }

    /**
     * 完工
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    @Override
    public ProduceSchedule workDone(ProduceSchedule produceSchedule){
        if(null!=produceSchedule.getId()){
            produceSchedule.setProduceStatus("schedule");
            updateProduceSchedule(produceSchedule);
            setStatusByHeaderId(produceSchedule.getId(),"submit","schedule");
        }
        return produceSchedule;
    }

    /**
     * 报产
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    @Override
    public ProduceSchedule workReport(ProduceSchedule produceSchedule){
        if(null!=produceSchedule.getId()){
            produceSchedule.setProduceStatus("report");
            updateProduceSchedule(produceSchedule);
            setStatusByHeaderId(produceSchedule.getId(),"","report");
        }
        return produceSchedule;
    }

    /**
     * 入库
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    @Override
    public ProduceSchedule workInbound(ProduceSchedule produceSchedule){
        if(null!=produceSchedule.getId()){
            produceSchedule.setProduceStatus("warehouse");
            updateProduceSchedule(produceSchedule);
            setStatusByHeaderId(produceSchedule.getId(),"","warehouse");
        }
        return produceSchedule;
    }

    private void setStatusByHeaderId(Long produceScheduleId,String orgStatus,String status){
        ProduceScheduleProcess produceScheduleProcess = new ProduceScheduleProcess();
        produceScheduleProcess.setProduceScheduleId(produceScheduleId);
        List<ProduceScheduleProcess> produceScheduleProcessList = produceScheduleProcessMapper.selectProduceScheduleProcessList(produceScheduleProcess);
        for (int i=0;i<produceScheduleProcessList.size();i++){
            ProduceScheduleProcess processVO = produceScheduleProcessList.get(i);
            if(StringUtils.isEmpty(orgStatus)||orgStatus.equals(produceScheduleProcessList.get(i).getProduceStatus())){
                processVO.setProduceStatus(status);
                if("report".equals(status)){
                    processVO.setQty(processVO.getProduceQty());
                }
                if("warehouse".equals(status)){
                    processVO.setQty(processVO.getProduceQty());
                    processVO.setProduceStatus(status);
                    setupProduceOrder(processVO);
                }
                produceScheduleProcessMapper.updateProduceScheduleProcess(processVO);
            }
        }
    }

    //入库更新施工单状态
    private void setupProduceOrder(ProduceScheduleProcess produceScheduleProcess){
        Long id = produceScheduleProcess.getProduceOrderProcessId();
        ProduceOrderProcess produceOrderProcess = new ProduceOrderProcess();
        produceOrderProcess.setId(id);
        if(null!=id){
            produceOrderProcess.setProduceStatus(produceScheduleProcess.getProduceStatus());
            produceOrderProcessMapper.updateProduceOrderProcess(produceOrderProcess);
        }
    }


    /**
     * 入库
     * @Author 方舟
     * @Date 2021/5/17 12:33:22
     **/
    @Override
    public ProduceSchedule batchReport(ProduceSchedule produceSchedule){
        String[] arr = Convert.toStrArray(produceSchedule.getIds());
        for (int i=0;i<arr.length;i++){
            Long id = Long.parseLong(arr[i].split("_")[0]);
            Integer qty = Integer.parseInt(arr[i].split("_")[1]);
            ProduceScheduleProcess produceScheduleProcess = new ProduceScheduleProcess();
            produceScheduleProcess.setId(id);
            produceScheduleProcess.setQty(qty);
            produceScheduleProcess.setProduceStatus("warehouse");
            produceScheduleProcessMapper.updateProduceScheduleProcess(produceScheduleProcess);
        }
        return produceSchedule;
    }

}
