package com.ruoyi.project.outsource.outsourceChecking.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.config.configSupplier.mapper.ConfigSupplierMapper;
import com.ruoyi.project.outsource.outsourceCheckingProcess.domain.OutsourceCheckingProcess;
import com.ruoyi.project.outsource.outsourceCheckingProcess.mapper.OutsourceCheckingProcessMapper;
import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDelivery;
import com.ruoyi.project.outsource.outsourceDelivery.mapper.OutsourceDeliveryMapper;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.domain.OutsourceDeliveryProcess;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.mapper.OutsourceDeliveryProcessMapper;
import com.ruoyi.project.outsource.outsourceReturnProcess.domain.OutsourceReturnProcess;
import com.ruoyi.project.outsource.outsourceReturnProcess.mapper.OutsourceReturnProcessMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.outsource.outsourceChecking.mapper.OutsourceCheckingMapper;
import com.ruoyi.project.outsource.outsourceChecking.domain.OutsourceChecking;
import com.ruoyi.project.outsource.outsourceChecking.service.IOutsourceCheckingService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外发对账Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class OutsourceCheckingServiceImpl implements IOutsourceCheckingService 
{
    @Autowired
    private OutsourceCheckingMapper outsourceCheckingMapper;
    @Autowired
    private OutsourceCheckingProcessMapper outsourceCheckingProcessMapper;
    @Autowired
    private OutsourceDeliveryProcessMapper outsourceDeliveryProcessMapper;
    @Autowired
    private OutsourceReturnProcessMapper outsourceReturnProcessMapper;
    @Autowired
    private OutsourceDeliveryMapper outsourceDeliveryMapper;
    @Autowired
    private ConfigSupplierMapper configSupplierMapper;

    /**
     * 查询外发对账
     * 
     * @param id 外发对账ID
     * @return 外发对账
     */
    @Override
    public OutsourceChecking selectOutsourceCheckingById(Long id)
    {
        return outsourceCheckingMapper.selectOutsourceCheckingById(id);
    }

    /**
     * 查询外发对账列表
     * 
     * @param outsourceChecking 外发对账
     * @return 外发对账
     */
    @Override
    public List<OutsourceChecking> selectOutsourceCheckingList(OutsourceChecking outsourceChecking)
    {
        return outsourceCheckingMapper.selectOutsourceCheckingList(outsourceChecking);
    }

    /**
     * 新增外发对账
     * 
     * @param outsourceChecking 外发对账
     * @return 结果
     */
    @Override
    public int insertOutsourceChecking(OutsourceChecking outsourceChecking)
    {
        outsourceChecking.setCreateBy(ShiroUtils.getSysUser().getUserName());
        outsourceChecking.setCreateTime(DateUtils.getNowDate());
        outsourceChecking.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        outsourceChecking.setUpdateTime(DateUtils.getNowDate());
        if(null==outsourceChecking.getCheckingDate()){
            outsourceChecking.setCheckingDate(DateUtils.getNowDate());
        }
        return outsourceCheckingMapper.insertOutsourceChecking(outsourceChecking);
    }

    /**
     * 修改外发对账
     * 
     * @param outsourceChecking 外发对账
     * @return 结果
     */
    @Override
    public int updateOutsourceChecking(OutsourceChecking outsourceChecking)
    {
        outsourceChecking.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        outsourceChecking.setUpdateTime(DateUtils.getNowDate());
        return outsourceCheckingMapper.updateOutsourceChecking(outsourceChecking);
    }

    /**
     * 删除外发对账对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceCheckingByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            OutsourceChecking param = outsourceCheckingMapper.selectOutsourceCheckingById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = outsourceCheckingMapper.deleteOutsourceCheckingById(Long.parseLong(arr[i]));
                outsourceCheckingMapper.deleteOutsourceCheckingProcessById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = outsourceCheckingMapper.updateOutsourceChecking(param);
            }
        }
        return result;
        //return outsourceCheckingMapper.deleteOutsourceCheckingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除外发对账信息
     * 
     * @param id 外发对账ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceCheckingById(Long id)
    {
        int result = 0;
        OutsourceChecking param = outsourceCheckingMapper.selectOutsourceCheckingById(id);
        if("draft".equals(param.getStatus())){
            result = outsourceCheckingMapper.deleteOutsourceCheckingById(id);
            outsourceCheckingMapper.deleteOutsourceCheckingProcessById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = outsourceCheckingMapper.updateOutsourceChecking(param);
        }
        return result;
        //return outsourceCheckingMapper.deleteOutsourceCheckingById(id);
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public OutsourceChecking createOutsourceChecking(OutsourceChecking outsourceChecking){
        String[] arr = Convert.toStrArray(outsourceChecking.getIds());
        Long headerId = 0L;
        BigDecimal taxRate = new BigDecimal(0);
        for (int i=0;i<arr.length;i++){
            Long processLineId = Long.parseLong(arr[i]);
            OutsourceDeliveryProcess outsourceDeliveryProcess = outsourceDeliveryProcessMapper.selectOutsourceDeliveryProcessById(processLineId);
            OutsourceDelivery outsourceDelivery = outsourceDeliveryMapper.selectOutsourceDeliveryById(outsourceDeliveryProcess.getOutsourceDeliveryId());
            ConfigSupplier configSupplier = configSupplierMapper.selectConfigSupplierById(outsourceDelivery.getSupplierId());
            if(!StringUtils.isEmpty(outsourceChecking.getRzyValue2())&&"A".equals(outsourceChecking.getRzyValue2())){
                headerId = outsourceChecking.getId();
                taxRate = outsourceDelivery.getTaxRate();
            }else{
                if(i==0){
                    //insert头
                    OutsourceChecking headerVO = new OutsourceChecking();
                    BeanUtils.copyProperties(outsourceDelivery, headerVO);
                    headerVO.setCheckingDate(DateUtils.getNowDate());
                    headerVO.setStatus("draft");
                    headerVO.setAmount(new BigDecimal(0));
                    insertOutsourceChecking(headerVO);
                    headerId = headerVO.getId();
                }
            }
            //insert行
            OutsourceCheckingProcess outsourceCheckingProcess = new OutsourceCheckingProcess();
            BeanUtils.copyProperties(outsourceDeliveryProcess, outsourceCheckingProcess);
            outsourceCheckingProcess.setOutsourceDeliveryProcessId(processLineId);
            outsourceCheckingProcess.setOutsourceCheckingId(headerId);
            outsourceCheckingProcessMapper.insertOutsourceCheckingProcess(outsourceCheckingProcess);
        }
        outsourceChecking.setId(headerId);
        return calculateAmount(outsourceChecking);
    }


    /**
     * 计算金额
     * @Author 方舟
     * @Date 2021/5/27 14:45:12
     **/
    @Override
    public OutsourceChecking calculateAmount(OutsourceChecking outsourceChecking){
        Long outsourceCheckingId = outsourceChecking.getId();
        BigDecimal deliveryAmount = new BigDecimal(0);
        BigDecimal returnAmount = new BigDecimal(0);
        BigDecimal amount = new BigDecimal(0);
        OutsourceDeliveryProcess paramDeliveryVO = new OutsourceDeliveryProcess();
        paramDeliveryVO.setOutsourceCheckingId(outsourceCheckingId);
        List<OutsourceDeliveryProcess> dmlist = outsourceDeliveryProcessMapper.selectOutsourceDeliveryProcessList(paramDeliveryVO);
        for (int i=0;i<dmlist.size();i++){
            deliveryAmount = deliveryAmount.add(dmlist.get(i).getAmount());
        }
        OutsourceReturnProcess paramReturnVO = new OutsourceReturnProcess();
        paramReturnVO.setOutsourceCheckingId(outsourceCheckingId);
        List<OutsourceReturnProcess> rmlist = outsourceReturnProcessMapper.selectOutsourceReturnProcessList(paramReturnVO);
        for (int i=0;i<rmlist.size();i++){
            returnAmount = returnAmount.add(rmlist.get(i).getAmount());
        }
        amount = deliveryAmount.subtract(returnAmount);
        OutsourceChecking saveVO = new OutsourceChecking();
        saveVO.setId(outsourceCheckingId);
        saveVO.setDeliveryAmount(deliveryAmount);
        saveVO.setReturnAmount(returnAmount);
        saveVO.setAmount(amount);
        updateOutsourceChecking(saveVO);
        return saveVO;
    }
}
