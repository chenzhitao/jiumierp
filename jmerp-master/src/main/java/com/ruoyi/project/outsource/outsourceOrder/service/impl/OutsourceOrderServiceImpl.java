package com.ruoyi.project.outsource.outsourceOrder.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.config.configSupplierContact.service.IConfigSupplierContactService;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrderExport;
import com.ruoyi.project.outsource.outsourceOrderMaterials.domain.OutsourceOrderMaterials;
import com.ruoyi.project.outsource.outsourceOrderMaterials.service.IOutsourceOrderMaterialsService;
import com.ruoyi.project.outsource.outsourceOrderProcess.domain.OutsourceOrderProcess;
import com.ruoyi.project.outsource.outsourceOrderProcess.mapper.OutsourceOrderProcessMapper;
import com.ruoyi.project.outsource.outsourceOrderProcess.service.IOutsourceOrderProcessService;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceOrder.mapper.ProduceOrderMapper;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.mapper.ProduceOrderProcessMapper;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrder.mapper.SaleOrderMapper;
import com.ruoyi.project.sale.saleOrderMaterials.domain.SaleOrderMaterials;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleOrderProduct.mapper.SaleOrderProductMapper;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.outsource.outsourceOrder.mapper.OutsourceOrderMapper;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrder;
import com.ruoyi.project.outsource.outsourceOrder.service.IOutsourceOrderService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外发加工Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class OutsourceOrderServiceImpl implements IOutsourceOrderService 
{
    @Autowired
    private OutsourceOrderMapper outsourceOrderMapper;
    @Autowired
    private OutsourceOrderProcessMapper outsourceOrderProcessMapper;
    @Autowired
    private ProduceOrderProcessMapper produceOrderProcessMapper;
    @Autowired
    private SaleOrderProductMapper saleOrderProductMapper;
    @Autowired
    private SaleOrderMapper saleOrderMapper;
    @Autowired
    private ProduceOrderMapper produceOrderMapper;
    @Autowired
    private IConfigSupplierContactService configSupplierContactService;
    @Autowired
    private IOutsourceOrderMaterialsService outsourceOrderMaterialsService;
    @Autowired
    private IWarehouseRecordService warehouseRecordService;
    @Autowired
    private IOutsourceOrderProcessService outsourceOrderProcessService;

    /**
     * 查询外发加工
     * 
     * @param id 外发加工ID
     * @return 外发加工
     */
    @Override
    public OutsourceOrder selectOutsourceOrderById(Long id)
    {
        return outsourceOrderMapper.selectOutsourceOrderById(id);
    }

    /**
     * 查询外发加工列表
     * 
     * @param outsourceOrder 外发加工
     * @return 外发加工
     */
    @Override
    public List<OutsourceOrder> selectOutsourceOrderList(OutsourceOrder outsourceOrder)
    {
        return outsourceOrderMapper.selectOutsourceOrderList(outsourceOrder);
    }
    @Override
    public List<OutsourceOrderExport> selectOutsourceOrderExportList(OutsourceOrder outsourceOrder)
    {
        return outsourceOrderMapper.selectOutsourceOrderExportList(outsourceOrder);
    }

    /**
     * 新增外发加工
     * 
     * @param outsourceOrder 外发加工
     * @return 结果
     */
    @Override
    public int insertOutsourceOrder(OutsourceOrder outsourceOrder)
    {
        outsourceOrder.setCreateBy(ShiroUtils.getSysUser().getUserName());
        outsourceOrder.setCreateTime(DateUtils.getNowDate());
        outsourceOrder.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        outsourceOrder.setUpdateTime(DateUtils.getNowDate());
        return outsourceOrderMapper.insertOutsourceOrder(outsourceOrder);
    }

    /**
     * 修改外发加工
     * 
     * @param outsourceOrder 外发加工
     * @return 结果
     */
    @Override
    public int updateOutsourceOrder(OutsourceOrder outsourceOrder)
    {
        outsourceOrder.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        outsourceOrder.setUpdateTime(DateUtils.getNowDate());
        //带料
        warehouseChange(outsourceOrder.getRzyValue1(),outsourceOrder.getId());
        return outsourceOrderMapper.updateOutsourceOrder(outsourceOrder);
    }

    /**
     * 删除外发加工对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceOrderByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            OutsourceOrder param = outsourceOrderMapper.selectOutsourceOrderById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = outsourceOrderMapper.deleteOutsourceOrderById(Long.parseLong(arr[i]));
                outsourceOrderMapper.deleteOutsourceOrderProcessById(Long.parseLong(arr[i]));
                outsourceOrderMapper.deleteOutsourceOrderMaterialsById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = outsourceOrderMapper.updateOutsourceOrder(param);
            }
        }
        return result;
        //return outsourceOrderMapper.deleteOutsourceOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除外发加工信息
     * 
     * @param id 外发加工ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceOrderById(Long id)
    {
        int result = 0;
        OutsourceOrder param = outsourceOrderMapper.selectOutsourceOrderById(id);
        if("draft".equals(param.getStatus())){
            result = outsourceOrderMapper.deleteOutsourceOrderById(id);
            outsourceOrderMapper.deleteOutsourceOrderProcessById(id);
            outsourceOrderMapper.deleteOutsourceOrderMaterialsById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = outsourceOrderMapper.updateOutsourceOrder(param);
        }
        return result;
        //return outsourceOrderMapper.deleteOutsourceOrderById(id);
    }


    /**
     * 创建外发加工
     */
    @Override
    public OutsourceOrder createOutsource(OutsourceOrder outsourceOrder){
        OutsourceOrder result = new OutsourceOrder();
        if(!StringUtils.isEmpty(outsourceOrder.getOutsourceType())&&"process".equals(outsourceOrder.getOutsourceType())){
            result = createProcessOutsource(outsourceOrder);
        }
        if(!StringUtils.isEmpty(outsourceOrder.getOutsourceType())&&"product".equals(outsourceOrder.getOutsourceType())){
            result = createProductOutsource(outsourceOrder);
        }
        return result;

    }

    private OutsourceOrder createProcessOutsource(OutsourceOrder outsourceOrder){
        List<Long> ids = new ArrayList<Long>();
        ids = produceOrderProcessMapper.findOutsourceProcessList(Convert.toStrArray(outsourceOrder.getIds()));
        Long headerId = 0L;
        OutsourceOrder headerVO = new OutsourceOrder();
        for (int i=0;i<ids.size();i++){
            Long produceOrderProcessId = ids.get(i);
            ProduceOrderProcess produceOrderProcess = produceOrderProcessMapper.selectProduceOrderProcessById(produceOrderProcessId);
            ProduceOrder produceOrder = produceOrderMapper.selectProduceOrderById(produceOrderProcess.getProduceOrderId());
            if(i==0&&"list".equals(outsourceOrder.getRzyValue1())){
                ConfigSupplierContact cscVO = new ConfigSupplierContact();
                cscVO.setIsDefault("Y");
                cscVO.setSupplierId(outsourceOrder.getSupplierId());
                ConfigSupplierContact configSupplierContact = configSupplierContactService.getDefaultContact(cscVO);
                BeanUtils.copyProperties(produceOrder, headerVO);
                headerVO.setStatus("draft");
                headerVO.setAmount(new BigDecimal(0));
                headerVO.setDeliveryDate(DateUtils.getNowDate());
                headerVO.setSupplierId(outsourceOrder.getSupplierId());
                headerVO.setOutsourceType(outsourceOrder.getOutsourceType());
                if(null!=configSupplierContact){
                    headerVO.setContact(configSupplierContact.getContactName());
                    headerVO.setCellPhone(configSupplierContact.getCellPhone());
                }
                insertOutsourceOrder(headerVO);
                headerId = headerVO.getId();
            }else if(i==0&&"detail".equals(outsourceOrder.getRzyValue1())){
                headerVO = outsourceOrderMapper.selectOutsourceOrderById(outsourceOrder.getId());
                headerId = outsourceOrder.getId();
            }
            OutsourceOrderProcess outsourceOrderProcess = new OutsourceOrderProcess();
            BeanUtils.copyProperties(produceOrderProcess, outsourceOrderProcess);
            outsourceOrderProcess.setOutsourceOrderId(headerId);
            outsourceOrderProcess.setOutsourceValuationType(produceOrderProcess.getValuationType());
            outsourceOrderProcess.setQty(produceOrderProcess.getLeftQty());
            outsourceOrderProcess.setPrice(produceOrderProcess.getOutsourcePrice());
            BigDecimal amount = produceOrderProcess.getOutsourcePrice().multiply(new BigDecimal(produceOrderProcess.getLeftQty()));
            outsourceOrderProcess.setAmount(amount);
            outsourceOrderProcess.setSupplierId(headerVO.getSupplierId());
            outsourceOrderProcess.setProduceOrderProcessId(produceOrderProcessId);
            outsourceOrderProcess.setOutsourceType(outsourceOrder.getOutsourceType());
            outsourceOrderProcessMapper.insertOutsourceOrderProcess(outsourceOrderProcess);
        }
        outsourceOrder.setId(headerId);
        return outsourceOrder;
    }

    private OutsourceOrder createProductOutsource(OutsourceOrder outsourceOrder){
        List<Long> ids = new ArrayList<Long>(Convert.toStrArray(outsourceOrder.getIds()).length);
        for (String s : Convert.toStrArray(outsourceOrder.getIds())) {
            ids.add(Long.parseLong(s));
        }
        Long headerId = 0L;
        OutsourceOrder headerVO = new OutsourceOrder();
        for (int i=0;i<ids.size();i++){
            Long saleOrderProductId = ids.get(i);
            SaleOrderProduct saleOrderProduct = saleOrderProductMapper.selectSaleOrderProductById(saleOrderProductId);
            SaleOrder saleOrder = saleOrderMapper.selectSaleOrderById(saleOrderProduct.getSaleOrderId());
            if(i==0&&"list".equals(outsourceOrder.getRzyValue1())){
                ConfigSupplierContact cscVO = new ConfigSupplierContact();
                cscVO.setIsDefault("Y");
                cscVO.setSupplierId(outsourceOrder.getSupplierId());
                ConfigSupplierContact configSupplierContact = configSupplierContactService.getDefaultContact(cscVO);
                BeanUtils.copyProperties(saleOrder, headerVO);
                headerVO.setStatus("draft");
                headerVO.setAmount(new BigDecimal(0));
                headerVO.setDeliveryDate(DateUtils.getNowDate());
                headerVO.setSupplierId(outsourceOrder.getSupplierId());
                headerVO.setOutsourceType(outsourceOrder.getOutsourceType());
                if(null!=configSupplierContact){
                    headerVO.setContact(configSupplierContact.getContactName());
                    headerVO.setCellPhone(configSupplierContact.getCellPhone());
                }
                insertOutsourceOrder(headerVO);
                headerId = headerVO.getId();
            }else if(i==0&&"detail".equals(outsourceOrder.getRzyValue1())){
                headerVO = outsourceOrderMapper.selectOutsourceOrderById(outsourceOrder.getId());
                headerId = outsourceOrder.getId();
            }
            OutsourceOrderProcess outsourceOrderProcess = new OutsourceOrderProcess();
            BeanUtils.copyProperties(saleOrderProduct, outsourceOrderProcess);
            outsourceOrderProcess.setOutsourceOrderId(headerId);
            outsourceOrderProcess.setOutsourceValuationType("price");
            outsourceOrderProcess.setQty(saleOrderProduct.getLeftQty());
            outsourceOrderProcess.setSupplierId(headerVO.getSupplierId());
            outsourceOrderProcess.setSaleOrderProductId(saleOrderProduct.getId());
            outsourceOrderProcess.setOutsourceType(outsourceOrder.getOutsourceType());
            outsourceOrderProcessMapper.insertOutsourceOrderProcess(outsourceOrderProcess);
        }
        outsourceOrder.setId(headerId);
        return outsourceOrder;
    }

    /**
     * 汇总金额
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @Override
    public OutsourceOrder countAmount(OutsourceOrder outsourceOrder){
        OutsourceOrderProcess outsourceOrderProcess = new OutsourceOrderProcess();
        outsourceOrderProcess.setOutsourceOrderId(outsourceOrder.getId());
        BigDecimal amount = new BigDecimal(0);
        List<OutsourceOrderProcess> list = outsourceOrderProcessService.selectOutsourceOrderProcessList(outsourceOrderProcess);
        for (int i=0;i<list.size();i++){
            amount = amount.add(list.get(i).getAmount());
        }
        outsourceOrder.setAmount(amount);
        outsourceOrderMapper.updateOutsourceOrder(outsourceOrder);
        return outsourceOrder;
    }

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 16:45:19
     **/
    private void warehouseChange(String editType,Long outsourceOrderId){
        if(!StringUtils.isEmpty(editType)){
            OutsourceOrder outsourceOrder = outsourceOrderMapper.selectOutsourceOrderById(outsourceOrderId);
            OutsourceOrderMaterials outsourceOrderMaterials = new OutsourceOrderMaterials();
            outsourceOrderMaterials.setOutsourceOrderId(outsourceOrderId);
            List<OutsourceOrderMaterials> list = outsourceOrderMaterialsService.selectOutsourceOrderMaterialsList(outsourceOrderMaterials);
            for (int i=0;i<list.size();i++){
                OutsourceOrderMaterials materials = list.get(i);
                if("approve".equals(editType)){
                    //带料出库
                    warehouseRecordService.materialsInbound("WFDL",materials.getWarehouseId(),(0-materials.getQty()),materials.getMaterialsId(),"");
                }else if("unapprove".equals(editType)){
                    //带料入库
                    warehouseRecordService.materialsInbound("WFDL",materials.getWarehouseId(),materials.getQty(),materials.getMaterialsId(),"反审退回");
                }
            }
        }
    }
}
