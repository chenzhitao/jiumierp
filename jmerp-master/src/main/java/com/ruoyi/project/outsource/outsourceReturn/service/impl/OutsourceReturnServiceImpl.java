package com.ruoyi.project.outsource.outsourceReturn.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDelivery;
import com.ruoyi.project.outsource.outsourceDelivery.mapper.OutsourceDeliveryMapper;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.domain.OutsourceDeliveryProcess;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.mapper.OutsourceDeliveryProcessMapper;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrder;
import com.ruoyi.project.outsource.outsourceOrderProcess.domain.OutsourceOrderProcess;
import com.ruoyi.project.outsource.outsourceReturn.domain.OutsourceReturnExport;
import com.ruoyi.project.outsource.outsourceReturnProcess.domain.OutsourceReturnProcess;
import com.ruoyi.project.outsource.outsourceReturnProcess.mapper.OutsourceReturnProcessMapper;
import com.ruoyi.project.outsource.outsourceReturnProcess.service.IOutsourceReturnProcessService;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.mapper.ProduceOrderProcessMapper;
import com.ruoyi.project.purchase.purchaseReturnMaterials.domain.PurchaseReturnMaterials;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.outsource.outsourceReturn.mapper.OutsourceReturnMapper;
import com.ruoyi.project.outsource.outsourceReturn.domain.OutsourceReturn;
import com.ruoyi.project.outsource.outsourceReturn.service.IOutsourceReturnService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外发退货Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class OutsourceReturnServiceImpl implements IOutsourceReturnService 
{
    @Autowired
    private OutsourceReturnMapper outsourceReturnMapper;
    @Autowired
    private OutsourceReturnProcessMapper outsourceReturnProcessMapper;
    @Autowired
    private OutsourceDeliveryProcessMapper outsourceDeliveryProcessMapper;
    @Autowired
    private OutsourceDeliveryMapper outsourceDeliveryMapper;
    @Autowired
    private ProduceOrderProcessMapper produceOrderProcessMapper;
    @Autowired
    private IOutsourceReturnProcessService outsourceReturnProcessService;

    /**
     * 查询外发退货
     * 
     * @param id 外发退货ID
     * @return 外发退货
     */
    @Override
    public OutsourceReturn selectOutsourceReturnById(Long id)
    {
        return outsourceReturnMapper.selectOutsourceReturnById(id);
    }

    /**
     * 查询外发退货列表
     * 
     * @param outsourceReturn 外发退货
     * @return 外发退货
     */
    @Override
    public List<OutsourceReturn> selectOutsourceReturnList(OutsourceReturn outsourceReturn)
    {
        return outsourceReturnMapper.selectOutsourceReturnList(outsourceReturn);
    }
    @Override
    public List<OutsourceReturnExport> selectOutsourceReturnExportList(OutsourceReturn outsourceReturn)
    {
        return outsourceReturnMapper.selectOutsourceReturnExportList(outsourceReturn);
    }

    /**
     * 新增外发退货
     * 
     * @param outsourceReturn 外发退货
     * @return 结果
     */
    @Override
    public int insertOutsourceReturn(OutsourceReturn outsourceReturn)
    {
        outsourceReturn.setCreateBy(ShiroUtils.getSysUser().getUserName());
        outsourceReturn.setCreateTime(DateUtils.getNowDate());
        outsourceReturn.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        outsourceReturn.setUpdateTime(DateUtils.getNowDate());
        return outsourceReturnMapper.insertOutsourceReturn(outsourceReturn);
    }

    /**
     * 修改外发退货
     * 
     * @param outsourceReturn 外发退货
     * @return 结果
     */
    @Override
    public int updateOutsourceReturn(OutsourceReturn outsourceReturn)
    {
        outsourceReturn.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        outsourceReturn.setUpdateTime(DateUtils.getNowDate());
        changeStatus(outsourceReturn);
        return outsourceReturnMapper.updateOutsourceReturn(outsourceReturn);
    }

    /**
     * 删除外发退货对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceReturnByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            OutsourceReturn param = outsourceReturnMapper.selectOutsourceReturnById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = outsourceReturnMapper.deleteOutsourceReturnById(Long.parseLong(arr[i]));
                outsourceReturnMapper.deleteOutsourceReturnProcessById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = outsourceReturnMapper.updateOutsourceReturn(param);
            }
        }
        return result;
        //return outsourceReturnMapper.deleteOutsourceReturnByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除外发退货信息
     * 
     * @param id 外发退货ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceReturnById(Long id)
    {
        int result = 0;
        OutsourceReturn param = outsourceReturnMapper.selectOutsourceReturnById(id);
        if("draft".equals(param.getStatus())){
            result = outsourceReturnMapper.deleteOutsourceReturnById(id);
            outsourceReturnMapper.deleteOutsourceReturnProcessById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = outsourceReturnMapper.updateOutsourceReturn(param);
        }
        return result;
        //return outsourceReturnMapper.deleteOutsourceReturnById(id);
    }

    /**
     * 创建外发到货
     */
    @Override
    public OutsourceReturn createOutsourceReturn(OutsourceReturn outsourceReturn){
        String[] ids = Convert.toStrArray(outsourceReturn.getIds());
        Long headerId = 0L;
        BigDecimal taxRate = new BigDecimal(0);
        for (int i=0;i<ids.length;i++){
            Long outsourceDeliveryProcessId = Long.parseLong(ids[i]);
            OutsourceDeliveryProcess outsourceDeliveryProcess = outsourceDeliveryProcessMapper.selectOutsourceDeliveryProcessById(outsourceDeliveryProcessId);
            OutsourceDelivery outsourceDelivery = outsourceDeliveryMapper.selectOutsourceDeliveryById(outsourceDeliveryProcess.getOutsourceDeliveryId());
            if(!StringUtils.isEmpty(outsourceReturn.getRzyValue2())&&"A".equals(outsourceReturn.getRzyValue2())){
                headerId = outsourceReturn.getId();
                taxRate = outsourceReturn.getTaxRate();
            }else if(i==0){
                OutsourceReturn headerVO = new OutsourceReturn();
                BeanUtils.copyProperties(outsourceDelivery, headerVO);
                headerVO.setStatus("draft");
                headerVO.setAmount(new BigDecimal(0));
                headerVO.setReturnDate(DateUtils.getNowDate());
                insertOutsourceReturn(headerVO);
                headerId = headerVO.getId();
            }
            OutsourceReturnProcess outsourceReturnProcess = new OutsourceReturnProcess();
            BeanUtils.copyProperties(outsourceDeliveryProcess, outsourceReturnProcess);
            outsourceReturnProcess.setOutsourceReturnId(headerId);
            outsourceReturnProcess.setOutsourceOrderProcessId(outsourceDeliveryProcessId);
            outsourceReturnProcess.setSupplierId(outsourceDelivery.getSupplierId());
            outsourceReturnProcess.setQty(0);
            outsourceReturnProcess.setAmount(new BigDecimal(0));
            outsourceReturnProcess.setReturnType("THKK");
            outsourceReturnProcess.setTaxRate(taxRate);
            outsourceReturnProcess.setOutsourceDeliveryProcessId(outsourceDeliveryProcess.getId());
            outsourceReturnProcess.setReturnDate(DateUtils.getNowDate());
            outsourceReturnProcessMapper.insertOutsourceReturnProcess(outsourceReturnProcess);
        }
        outsourceReturn.setId(headerId);
        return outsourceReturn;
    }

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @Override
    public OutsourceReturn countAmount(OutsourceReturn outsourceReturn){
        OutsourceReturnProcess outsourceReturnProcess = new OutsourceReturnProcess();
        outsourceReturnProcess.setOutsourceReturnId(outsourceReturn.getId());
        BigDecimal amount = new BigDecimal(0);
        List<OutsourceReturnProcess> list = outsourceReturnProcessService.selectOutsourceReturnProcessList(outsourceReturnProcess);
        for (int i=0;i<list.size();i++){
            amount = amount.add(list.get(i).getAmount());
        }
        outsourceReturn.setAmount(amount);
        outsourceReturnMapper.updateOutsourceReturn(outsourceReturn);
        return outsourceReturn;
    }

    /**
     * 申请时处理
     * @Author 方舟
     * @Date 2021/5/21 10:37:00
    **/
    private void changeStatus(OutsourceReturn outsourceReturn){
        String editType = outsourceReturn.getRzyValue1();
        OutsourceReturnProcess outsourceReturnProcess = new OutsourceReturnProcess();
        outsourceReturnProcess.setOutsourceReturnId(outsourceReturn.getId());
        List<OutsourceReturnProcess> list = outsourceReturnProcessMapper.selectOutsourceReturnProcessList(outsourceReturnProcess);
        for (int i=0;i<list.size();i++){
            OutsourceReturnProcess processVO = list.get(i);
            OutsourceDeliveryProcess odpVO = outsourceDeliveryProcessMapper.selectOutsourceDeliveryProcessById(processVO.getOutsourceDeliveryProcessId());
            ProduceOrderProcess popVO = produceOrderProcessMapper.selectProduceOrderProcessById(processVO.getProduceOrderProcessId());
            if(!StringUtils.isEmpty(editType)){
                //只有退货补数才有货物库存数量变化,不涉及金额
                if("THBS".equals(processVO.getReturnType())){
                    Integer minusQty = 0;
                    if("approve".equals(editType)){
                        //对应的到货-数量
                        minusQty = odpVO.getQty() - processVO.getQty();
                    }else if("unapprove".equals(editType)){
                        //对应的到货+数量
                        minusQty = odpVO.getQty() + processVO.getQty();
                    }
                    odpVO.setQty(minusQty);
                    outsourceDeliveryProcessMapper.updateOutsourceDeliveryProcess(odpVO);
                    //关联的施工单工序不能算完成了
                    popVO.setProduceStatus("submit");
                    produceOrderProcessMapper.updateProduceOrderProcess(popVO);
                }

            }
        }

    }
}
