package com.ruoyi.project.outsource.outsourceDelivery.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.config.configSupplierContact.service.IConfigSupplierContactService;
import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDeliveryExport;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.domain.OutsourceDeliveryProcess;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.mapper.OutsourceDeliveryProcessMapper;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.service.IOutsourceDeliveryProcessService;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrder;
import com.ruoyi.project.outsource.outsourceOrder.mapper.OutsourceOrderMapper;
import com.ruoyi.project.outsource.outsourceOrderProcess.domain.OutsourceOrderProcess;
import com.ruoyi.project.outsource.outsourceOrderProcess.mapper.OutsourceOrderProcessMapper;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.mapper.ProduceOrderProcessMapper;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain.PurchaseDeliveryMaterials;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.outsource.outsourceDelivery.mapper.OutsourceDeliveryMapper;
import com.ruoyi.project.outsource.outsourceDelivery.domain.OutsourceDelivery;
import com.ruoyi.project.outsource.outsourceDelivery.service.IOutsourceDeliveryService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外发到货Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class OutsourceDeliveryServiceImpl implements IOutsourceDeliveryService 
{
    @Autowired
    private OutsourceDeliveryMapper outsourceDeliveryMapper;
    @Autowired
    private OutsourceDeliveryProcessMapper outsourceDeliveryProcessMapper;
    @Autowired
    private ProduceOrderProcessMapper produceOrderProcessMapper;
    @Autowired
    private OutsourceOrderProcessMapper outsourceOrderProcessMapper;
    @Autowired
    private OutsourceOrderMapper outsourceOrderMapper;
    @Autowired
    private IConfigSupplierContactService configSupplierContactService;
    @Autowired
    private IOutsourceDeliveryProcessService outsourceDeliveryProcessService;

    /**
     * 查询外发到货
     * 
     * @param id 外发到货ID
     * @return 外发到货
     */
    @Override
    public OutsourceDelivery selectOutsourceDeliveryById(Long id)
    {
        return outsourceDeliveryMapper.selectOutsourceDeliveryById(id);
    }

    /**
     * 查询外发到货列表
     * 
     * @param outsourceDelivery 外发到货
     * @return 外发到货
     */
    @Override
    public List<OutsourceDelivery> selectOutsourceDeliveryList(OutsourceDelivery outsourceDelivery)
    {
        return outsourceDeliveryMapper.selectOutsourceDeliveryList(outsourceDelivery);
    }
    @Override
    public List<OutsourceDeliveryExport> selectOutsourceDeliveryExportList(OutsourceDelivery outsourceDelivery)
    {
        return outsourceDeliveryMapper.selectOutsourceDeliveryExportList(outsourceDelivery);
    }

    /**
     * 新增外发到货
     * 
     * @param outsourceDelivery 外发到货
     * @return 结果
     */
    @Override
    public int insertOutsourceDelivery(OutsourceDelivery outsourceDelivery)
    {
        outsourceDelivery.setCreateBy(ShiroUtils.getSysUser().getUserName());
        outsourceDelivery.setCreateTime(DateUtils.getNowDate());
        outsourceDelivery.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        outsourceDelivery.setUpdateTime(DateUtils.getNowDate());
        return outsourceDeliveryMapper.insertOutsourceDelivery(outsourceDelivery);
    }

    /**
     * 修改外发到货
     * 
     * @param outsourceDelivery 外发到货
     * @return 结果
     */
    @Override
    public int updateOutsourceDelivery(OutsourceDelivery outsourceDelivery)
    {
        outsourceDelivery.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        outsourceDelivery.setUpdateTime(DateUtils.getNowDate());
        //到货后更新施工单工序完工
        statusChange(outsourceDelivery);
        return outsourceDeliveryMapper.updateOutsourceDelivery(outsourceDelivery);
    }

    /**
     * 删除外发到货对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceDeliveryByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            OutsourceDelivery param = outsourceDeliveryMapper.selectOutsourceDeliveryById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = outsourceDeliveryMapper.deleteOutsourceDeliveryById(Long.parseLong(arr[i]));
                outsourceDeliveryMapper.deleteOutsourceDeliveryProcessById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = outsourceDeliveryMapper.updateOutsourceDelivery(param);
            }
        }
        return result;
        //return outsourceDeliveryMapper.deleteOutsourceDeliveryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除外发到货信息
     * 
     * @param id 外发到货ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceDeliveryById(Long id)
    {
        int result = 0;
        OutsourceDelivery param = outsourceDeliveryMapper.selectOutsourceDeliveryById(id);
        if("draft".equals(param.getStatus())){
            result = outsourceDeliveryMapper.deleteOutsourceDeliveryById(id);
            outsourceDeliveryMapper.deleteOutsourceDeliveryProcessById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = outsourceDeliveryMapper.updateOutsourceDelivery(param);
        }
        return result;
        //return outsourceDeliveryMapper.deleteOutsourceDeliveryById(id);
    }

    /**
     * 到货后更新施工单工序完工
     * @Author 方舟
     * @Date 2021/5/19 14:35:48
    **/
    private void statusChange(OutsourceDelivery outsourceDelivery){
        String editType = outsourceDelivery.getRzyValue1();
        if(!StringUtils.isEmpty(editType)){
            String status = "";
            if("approve".equals(editType)){
                status = "warehouse";
            }else if("unapprove".equals(editType)){
                status = "submit";
            }
            if(!StringUtils.isEmpty(status)){
                OutsourceDeliveryProcess params = new OutsourceDeliveryProcess();
                params.setOutsourceDeliveryId(outsourceDelivery.getId());
                List<OutsourceDeliveryProcess> processList = outsourceDeliveryProcessMapper.selectOutsourceDeliveryProcessList(params);
                for (int i=0;i<processList.size();i++){
                    Long produceOrderProcessId = processList.get(i).getProduceOrderProcessId();
                    ProduceOrderProcess produceOrderProcess = new ProduceOrderProcess();
                    produceOrderProcess.setId(produceOrderProcessId);
                    produceOrderProcess.setOutQty(processList.get(i).getQty());
                    produceOrderProcess.setProduceStatus(status);
                    produceOrderProcessMapper.updateProduceOrderProcess(produceOrderProcess);
                }
            }
        }
    }

    /**
     * 创建外发到货
     */
    @Override
    public OutsourceDelivery createOutsourceDelivery(OutsourceDelivery outsourceDelivery){
        String[] ids = Convert.toStrArray(outsourceDelivery.getIds());
        Long headerId = 0L;
        for (int i=0;i<ids.length;i++){
            Long outsourceOrderProcessId = Long.parseLong(ids[i]);
            OutsourceOrderProcess outsourceOrderProcess = outsourceOrderProcessMapper.selectOutsourceOrderProcessById(outsourceOrderProcessId);
            OutsourceOrder outsourceOrder = outsourceOrderMapper.selectOutsourceOrderById(outsourceOrderProcess.getOutsourceOrderId());
            if(!StringUtils.isEmpty(outsourceDelivery.getRzyValue2())&&"A".equals(outsourceDelivery.getRzyValue2())){
                headerId = outsourceDelivery.getId();
            }else if(i==0){
                OutsourceDelivery headerVO = new OutsourceDelivery();
                BeanUtils.copyProperties(outsourceOrder, headerVO);
                headerVO.setStatus("draft");
                headerVO.setAmount(new BigDecimal(0));
                headerVO.setDeliveryDate(DateUtils.getNowDate());
                insertOutsourceDelivery(headerVO);
                headerId = headerVO.getId();
            }
            OutsourceDeliveryProcess outsourceDeliveryProcess = new OutsourceDeliveryProcess();
            BeanUtils.copyProperties(outsourceOrderProcess, outsourceDeliveryProcess);
            outsourceDeliveryProcess.setOutsourceDeliveryId(headerId);
            outsourceDeliveryProcess.setOutsourceOrderProcessId(outsourceOrderProcessId);
            outsourceDeliveryProcess.setSupplierId(outsourceOrder.getSupplierId());
            outsourceDeliveryProcess.setQty(outsourceOrderProcess.getLeftQty());
            outsourceDeliveryProcess.setAmount(outsourceOrderProcess.getPrice().multiply(new BigDecimal(outsourceOrderProcess.getLeftQty())));
            outsourceDeliveryProcessMapper.insertOutsourceDeliveryProcess(outsourceDeliveryProcess);
        }
        outsourceDelivery.setId(headerId);
        return outsourceDelivery;
    }

    /**
     * 汇总金额
     *
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @Override
    public OutsourceDelivery countAmount(OutsourceDelivery outsourceDelivery){
        OutsourceDeliveryProcess outsourceDeliveryProcess = new OutsourceDeliveryProcess();
        outsourceDeliveryProcess.setOutsourceDeliveryId(outsourceDelivery.getId());
        BigDecimal amount = new BigDecimal(0);
        List<OutsourceDeliveryProcess> list = outsourceDeliveryProcessService.selectOutsourceDeliveryProcessList(outsourceDeliveryProcess);
        for (int i=0;i<list.size();i++){
            amount = amount.add(list.get(i).getAmount());
        }
        outsourceDelivery.setAmount(amount);
        outsourceDeliveryMapper.updateOutsourceDelivery(outsourceDelivery);
        return outsourceDelivery;
    }
}
