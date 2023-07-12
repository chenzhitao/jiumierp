package com.ruoyi.project.purchase.purchaseDelivery.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDeliveryExport;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain.PurchaseDeliveryMaterials;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.mapper.PurchaseDeliveryMaterialsMapper;
import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
import com.ruoyi.project.purchase.purchaseOrder.mapper.PurchaseOrderMapper;
import com.ruoyi.project.purchase.purchaseOrderMaterials.domain.PurchaseOrderMaterials;
import com.ruoyi.project.purchase.purchaseOrderMaterials.mapper.PurchaseOrderMaterialsMapper;
import com.ruoyi.project.purchase.purchaseRequest.domain.PurchaseRequest;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrderMaterials.domain.SaleOrderMaterials;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.purchase.purchaseDelivery.mapper.PurchaseDeliveryMapper;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseDelivery.service.IPurchaseDeliveryService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 采购到货Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Service
public class PurchaseDeliveryServiceImpl implements IPurchaseDeliveryService 
{
    @Autowired
    private PurchaseDeliveryMapper purchaseDeliveryMapper;
    @Autowired
    private PurchaseDeliveryMaterialsMapper purchaseDeliveryMaterialsMapper;
    @Autowired
    private PurchaseOrderMaterialsMapper purchaseOrderMaterialsMapper;
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private IWarehouseRecordService warehouseRecordService;

    /**
     * 查询采购到货
     * 
     * @param id 采购到货ID
     * @return 采购到货
     */
    @Override
    public PurchaseDelivery selectPurchaseDeliveryById(Long id)
    {
        return purchaseDeliveryMapper.selectPurchaseDeliveryById(id);
    }

    /**
     * 查询采购到货列表
     * 
     * @param purchaseDelivery 采购到货
     * @return 采购到货
     */
    @Override
    public List<PurchaseDelivery> selectPurchaseDeliveryList(PurchaseDelivery purchaseDelivery)
    {
        return purchaseDeliveryMapper.selectPurchaseDeliveryList(purchaseDelivery);
    }
    @Override
    public List<PurchaseDeliveryExport> selectPurchaseDeliveryExportList(PurchaseDelivery purchaseDelivery)
    {
        return purchaseDeliveryMapper.selectPurchaseDeliveryExportList(purchaseDelivery);
    }

    /**
     * 新增采购到货
     * 
     * @param purchaseDelivery 采购到货
     * @return 结果
     */
    @Override
    public int insertPurchaseDelivery(PurchaseDelivery purchaseDelivery)
    {
        purchaseDelivery.setCreateBy(ShiroUtils.getSysUser().getUserName());
        purchaseDelivery.setCreateTime(DateUtils.getNowDate());
        purchaseDelivery.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseDelivery.setUpdateTime(DateUtils.getNowDate());
        if(null==purchaseDelivery.getDeliveryDate()){
            purchaseDelivery.setDeliveryDate(DateUtils.getNowDate());
        }
        return purchaseDeliveryMapper.insertPurchaseDelivery(purchaseDelivery);
    }

    /**
     * 修改采购到货
     * 
     * @param purchaseDelivery 采购到货
     * @return 结果
     */
    @Override
    public int updatePurchaseDelivery(PurchaseDelivery purchaseDelivery)
    {
        purchaseDelivery.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseDelivery.setUpdateTime(DateUtils.getNowDate());
        //客户带料库
        warehouseChange(purchaseDelivery.getRzyValue1(),purchaseDelivery.getId());
        return purchaseDeliveryMapper.updatePurchaseDelivery(purchaseDelivery);
    }

    /**
     * 删除采购到货对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseDeliveryByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            PurchaseDelivery param = purchaseDeliveryMapper.selectPurchaseDeliveryById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = purchaseDeliveryMapper.deletePurchaseDeliveryById(Long.parseLong(arr[i]));
                purchaseDeliveryMapper.deletePurchaseDeliveryMaterialsById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = purchaseDeliveryMapper.updatePurchaseDelivery(param);
            }
        }
        return result;
        //return purchaseDeliveryMapper.deletePurchaseDeliveryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购到货信息
     * 
     * @param id 采购到货ID
     * @return 结果
     */
    @Override
    public int deletePurchaseDeliveryById(Long id)
    {
        int result = 0;
        PurchaseDelivery param = purchaseDeliveryMapper.selectPurchaseDeliveryById(id);
        if("draft".equals(param.getStatus())){
            result = purchaseDeliveryMapper.deletePurchaseDeliveryById(id);
            purchaseDeliveryMapper.deletePurchaseDeliveryMaterialsById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = purchaseDeliveryMapper.updatePurchaseDelivery(param);
        }
        return result;
        //return purchaseDeliveryMapper.deletePurchaseDeliveryById(id);
    }


    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public PurchaseDelivery createPurchaseDelivery(PurchaseDelivery purchaseDelivery){
        String[] arr = Convert.toStrArray(purchaseDelivery.getIds());
        Long headerId = 0L;
        BigDecimal taxRate = new BigDecimal(0);
        for (int i=0;i<arr.length;i++){
            Long materialsLineId = Long.parseLong(arr[i]);
            PurchaseOrderMaterials purchaseOrderMaterials = purchaseOrderMaterialsMapper.selectPurchaseOrderMaterialsById(materialsLineId);
            PurchaseOrder purchaseOrder = purchaseOrderMapper.selectPurchaseOrderById(purchaseOrderMaterials.getPurchaseOrderId());
            if(!StringUtils.isEmpty(purchaseDelivery.getRzyValue2())&&"A".equals(purchaseDelivery.getRzyValue2())){
                headerId = purchaseDelivery.getId();
                PurchaseDelivery headerVO = purchaseDeliveryMapper.selectPurchaseDeliveryById(headerId);
                taxRate = headerVO.getTaxRate();
            }else{
                if(i==0){
                    //insert头
                    PurchaseDelivery headerVO = new PurchaseDelivery();
                    BeanUtils.copyProperties(purchaseOrder, headerVO);
                    headerVO.setDeliveryDate(DateUtils.getNowDate());
                    headerVO.setStatus("draft");
                    headerVO.setAmount(new BigDecimal(0));
                    insertPurchaseDelivery(headerVO);
                    headerId = headerVO.getId();
                    taxRate = purchaseOrder.getTaxRate();
                }
            }
            //insert行
            PurchaseDeliveryMaterials purchaseDeliveryMaterials = new PurchaseDeliveryMaterials();
            BeanUtils.copyProperties(purchaseOrderMaterials, purchaseDeliveryMaterials);
            purchaseDeliveryMaterials.setPurchaseOrderMaterialsId(materialsLineId);
            purchaseDeliveryMaterials.setPurchaseDeliveryId(headerId);
            purchaseDeliveryMaterials.setQty(purchaseOrderMaterials.getUnDeliveryQty());
            purchaseDeliveryMaterials.setTaxRate(taxRate);
            purchaseDeliveryMaterialsMapper.insertPurchaseDeliveryMaterials(purchaseDeliveryMaterials);
        }
        purchaseDelivery.setId(headerId);
        return purchaseDelivery;
    }


    @Override
    public PurchaseDelivery createPurchaseDeliveryByOrderId(PurchaseOrder purchaseOrder){
        PurchaseDelivery purchaseDelivery = new PurchaseDelivery();
        purchaseOrder = purchaseOrderMapper.selectPurchaseOrderById(purchaseOrder.getId());
        PurchaseOrderMaterials purchaseOrderMaterials = new PurchaseOrderMaterials();
        purchaseOrderMaterials.setPurchaseOrderId(purchaseOrder.getId());
        List<PurchaseOrderMaterials> list = purchaseOrderMaterialsMapper.selectPurchaseOrderMaterialsList(purchaseOrderMaterials);
        Long headerId = 0L;
        boolean hasHeader = false;
        for (int i=0;i<list.size();i++){
            if(list.get(i).getUnDeliveryQty()<=0){
                continue;
            }
            if(!hasHeader){
                //insert头
                PurchaseDelivery headerVO = new PurchaseDelivery();
                BeanUtils.copyProperties(purchaseOrder, headerVO);
                headerVO.setDeliveryDate(DateUtils.getNowDate());
                headerVO.setStatus("draft");
                headerVO.setAmount(new BigDecimal(0));
                insertPurchaseDelivery(headerVO);
                headerId = headerVO.getId();
                hasHeader = true;
            }
            //insert行
            PurchaseDeliveryMaterials purchaseDeliveryMaterials = new PurchaseDeliveryMaterials();
            BeanUtils.copyProperties(list.get(i), purchaseDeliveryMaterials);
            purchaseDeliveryMaterials.setPurchaseOrderMaterialsId(list.get(i).getId());
            purchaseDeliveryMaterials.setPurchaseDeliveryId(headerId);
            purchaseDeliveryMaterials.setQty(list.get(i).getUnDeliveryQty());
            purchaseDeliveryMaterials.setTaxRate(purchaseOrder.getTaxRate());
            purchaseDeliveryMaterialsMapper.insertPurchaseDeliveryMaterials(purchaseDeliveryMaterials);
        }
        purchaseDelivery.setId(headerId);
        return purchaseDelivery;
    }

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @Override
    public PurchaseDelivery calculatorAmount(PurchaseDelivery purchaseDelivery){
        PurchaseDeliveryMaterials materialsParams = new PurchaseDeliveryMaterials();
        materialsParams.setPurchaseDeliveryId(purchaseDelivery.getId());
        List<PurchaseDeliveryMaterials> materialsList = purchaseDeliveryMaterialsMapper.selectPurchaseDeliveryMaterialsList(materialsParams);
        BigDecimal amount = new BigDecimal(0);
        for (int i=0;i<materialsList.size();i++){
            PurchaseDeliveryMaterials materialsVO = materialsList.get(i);
            //BigDecimal materialsAmount = materialsVO.getPrice().multiply(new BigDecimal(materialsVO.getQty()));
            amount = amount.add(materialsVO.getAmount());
            //materialsVO.setAmount(materialsAmount);
            //purchaseDeliveryMaterialsMapper.updatePurchaseDeliveryMaterials(materialsVO);
        }
        purchaseDelivery.setAmount(amount);
        updatePurchaseDelivery(purchaseDelivery);
        return purchaseDelivery;
    }

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 16:45:19
     **/
    private void warehouseChange(String editType,Long purchaseDeliveryId){
        if(!StringUtils.isEmpty(editType)){
            PurchaseDelivery purchaseDelivery = purchaseDeliveryMapper.selectPurchaseDeliveryById(purchaseDeliveryId);
            PurchaseDeliveryMaterials purchaseDeliveryMaterials = new PurchaseDeliveryMaterials();
            purchaseDeliveryMaterials.setPurchaseDeliveryId(purchaseDeliveryId);
            List<PurchaseDeliveryMaterials> list = purchaseDeliveryMaterialsMapper.selectPurchaseDeliveryMaterialsList(purchaseDeliveryMaterials);
            for (int i=0;i<list.size();i++){
                PurchaseDeliveryMaterials materials = list.get(i);
                if("approve".equals(editType)){
                    //到货入库
                    warehouseRecordService.materialsInbound("CGDT",materials.getWarehouseId(),materials.getQty(),materials.getMaterialsId(),"");
                }else if("unapprove".equals(editType)){
                    //退货出库
                    warehouseRecordService.materialsInbound("CGDT",materials.getWarehouseId(),(0-materials.getQty()),materials.getMaterialsId(),"反审退回");
                }
            }
        }
    }
}
