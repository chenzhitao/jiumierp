package com.ruoyi.project.purchase.purchaseReturn.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.FormulaUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.config.configSupplier.mapper.ConfigSupplierMapper;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseDelivery.mapper.PurchaseDeliveryMapper;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain.PurchaseDeliveryMaterials;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.mapper.PurchaseDeliveryMaterialsMapper;
import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
import com.ruoyi.project.purchase.purchaseOrderMaterials.domain.PurchaseOrderMaterials;
import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturnExport;
import com.ruoyi.project.purchase.purchaseReturnMaterials.domain.PurchaseReturnMaterials;
import com.ruoyi.project.purchase.purchaseReturnMaterials.mapper.PurchaseReturnMaterialsMapper;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.purchase.purchaseReturn.mapper.PurchaseReturnMapper;
import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturn;
import com.ruoyi.project.purchase.purchaseReturn.service.IPurchaseReturnService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 采购退货Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Service
public class PurchaseReturnServiceImpl implements IPurchaseReturnService 
{
    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;
    @Autowired
    private PurchaseReturnMaterialsMapper purchaseReturnMaterialsMapper;
    @Autowired
    private PurchaseDeliveryMaterialsMapper purchaseDeliveryMaterialsMapper;
    @Autowired
    private PurchaseDeliveryMapper purchaseDeliveryMapper;
    @Autowired
    private ConfigSupplierMapper configSupplierMapper;
    @Autowired
    private IWarehouseRecordService warehouseRecordService;

    /**
     * 查询采购退货
     * 
     * @param id 采购退货ID
     * @return 采购退货
     */
    @Override
    public PurchaseReturn selectPurchaseReturnById(Long id)
    {
        return purchaseReturnMapper.selectPurchaseReturnById(id);
    }

    /**
     * 查询采购退货列表
     * 
     * @param purchaseReturn 采购退货
     * @return 采购退货
     */
    @Override
    public List<PurchaseReturn> selectPurchaseReturnList(PurchaseReturn purchaseReturn)
    {
        return purchaseReturnMapper.selectPurchaseReturnList(purchaseReturn);
    }
    @Override
    public List<PurchaseReturnExport> selectPurchaseReturnExportList(PurchaseReturn purchaseReturn)
    {
        return purchaseReturnMapper.selectPurchaseReturnExportList(purchaseReturn);
    }

    /**
     * 新增采购退货
     * 
     * @param purchaseReturn 采购退货
     * @return 结果
     */
    @Override
    public int insertPurchaseReturn(PurchaseReturn purchaseReturn)
    {
        purchaseReturn.setCreateBy(ShiroUtils.getSysUser().getUserName());
        purchaseReturn.setCreateTime(DateUtils.getNowDate());
        purchaseReturn.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseReturn.setUpdateTime(DateUtils.getNowDate());
        if(null==purchaseReturn.getReturnDate()){
            purchaseReturn.setReturnDate(DateUtils.getNowDate());
        }
        return purchaseReturnMapper.insertPurchaseReturn(purchaseReturn);
    }

    /**
     * 修改采购退货
     * 
     * @param purchaseReturn 采购退货
     * @return 结果
     */
    @Override
    public int updatePurchaseReturn(PurchaseReturn purchaseReturn)
    {
        purchaseReturn.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseReturn.setUpdateTime(DateUtils.getNowDate());
        warehouseChange(purchaseReturn.getRzyValue1(),purchaseReturn.getId());
        return purchaseReturnMapper.updatePurchaseReturn(purchaseReturn);
    }

    /**
     * 删除采购退货对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseReturnByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            PurchaseReturn param = purchaseReturnMapper.selectPurchaseReturnById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = purchaseReturnMapper.deletePurchaseReturnById(Long.parseLong(arr[i]));
                purchaseReturnMapper.deletePurchaseReturnMaterialsById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = purchaseReturnMapper.updatePurchaseReturn(param);
            }
        }
        return result;
        //return purchaseReturnMapper.deletePurchaseReturnByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购退货信息
     * 
     * @param id 采购退货ID
     * @return 结果
     */
    @Override
    public int deletePurchaseReturnById(Long id)
    {
        int result = 0;
        PurchaseReturn param = purchaseReturnMapper.selectPurchaseReturnById(id);
        if("draft".equals(param.getStatus())){
            result = purchaseReturnMapper.deletePurchaseReturnById(id);
            purchaseReturnMapper.deletePurchaseReturnMaterialsById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = purchaseReturnMapper.updatePurchaseReturn(param);
        }
        return result;
        //return purchaseReturnMapper.deletePurchaseReturnById(id);
    }



    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public PurchaseReturn createPurchaseReturn(PurchaseReturn purchaseReturn){
        String[] arr = Convert.toStrArray(purchaseReturn.getIds());
        Long headerId = 0L;
        BigDecimal taxRate = new BigDecimal(0);
        for (int i=0;i<arr.length;i++){
            Long materialsLineId = Long.parseLong(arr[i]);
            PurchaseDeliveryMaterials purchaseDeliveryMaterials = purchaseDeliveryMaterialsMapper.selectPurchaseDeliveryMaterialsById(materialsLineId);
            PurchaseDelivery purchaseDelivery = purchaseDeliveryMapper.selectPurchaseDeliveryById(purchaseDeliveryMaterials.getPurchaseDeliveryId());
            ConfigSupplier configSupplier = configSupplierMapper.selectConfigSupplierById(purchaseDelivery.getSupplierId());
            if(!StringUtils.isEmpty(purchaseReturn.getRzyValue2())&&"A".equals(purchaseReturn.getRzyValue2())){
                headerId = purchaseReturn.getId();
                taxRate = purchaseDelivery.getTaxRate();
            }else{
                if(i==0){
                    //insert头
                    PurchaseReturn headerVO = new PurchaseReturn();
                    BeanUtils.copyProperties(purchaseDelivery, headerVO);
                    headerVO.setReturnDate(DateUtils.getNowDate());
                    headerVO.setStatus("draft");
                    headerVO.setAmount(new BigDecimal(0));
                    insertPurchaseReturn(headerVO);
                    headerId = headerVO.getId();
                }
            }
            //insert行
            PurchaseReturnMaterials purchaseReturnMaterials = new PurchaseReturnMaterials();
            BeanUtils.copyProperties(purchaseDeliveryMaterials, purchaseReturnMaterials);
            purchaseReturnMaterials.setPurchaseDeliveryMaterialsId(materialsLineId);
            purchaseReturnMaterials.setPurchaseReturnId(headerId);
            purchaseReturnMaterials.setReturnDate(DateUtils.getNowDate());
            purchaseReturnMaterials.setReturnType("THKK");//退货扣款
            purchaseReturnMaterials.setReturnQty(0);
            purchaseReturnMaterials.setTaxRate(taxRate);
            purchaseReturnMaterials.setAmount(new BigDecimal(0));
            if(null!=configSupplier){
                purchaseReturnMaterials.setAddress(configSupplier.getAddress());
            }
            purchaseReturnMaterialsMapper.insertPurchaseReturnMaterials(purchaseReturnMaterials);
        }
        purchaseReturn.setId(headerId);
        return purchaseReturn;
    }

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @Override
    public PurchaseReturn calculatorAmount(PurchaseReturn purchaseReturn){
        PurchaseReturnMaterials materialsParams = new PurchaseReturnMaterials();
        materialsParams.setPurchaseReturnId(purchaseReturn.getId());
        List<PurchaseReturnMaterials> materialsList = purchaseReturnMaterialsMapper.selectPurchaseReturnMaterialsList(materialsParams);
        BigDecimal amount = new BigDecimal(0);
        for (int i=0;i<materialsList.size();i++){
            PurchaseReturnMaterials materialsVO = materialsList.get(i);
            //BigDecimal materialsAmount = FormulaUtils.calculatorReturnAmount(materialsVO.getReturnType(),materialsVO.getReturnQty(),materialsVO.getReturnRate(),materialsVO.getPrice());
            amount = amount.add(materialsVO.getAmount());
            //materialsVO.setAmount(materialsAmount);
            //purchaseReturnMaterialsMapper.updatePurchaseReturnMaterials(materialsVO);
        }
        purchaseReturn.setAmount(amount);
        updatePurchaseReturn(purchaseReturn);
        return purchaseReturn;
    }

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 16:45:19
     **/
    private void warehouseChange(String editType,Long purchaseReturnId){
        if(!StringUtils.isEmpty(editType)){
            PurchaseReturn purchaseReturn = purchaseReturnMapper.selectPurchaseReturnById(purchaseReturnId);
            PurchaseReturnMaterials purchaseReturnMaterials = new PurchaseReturnMaterials();
            purchaseReturnMaterials.setPurchaseReturnId(purchaseReturnId);
            List<PurchaseReturnMaterials> list = purchaseReturnMaterialsMapper.selectPurchaseReturnMaterialsList(purchaseReturnMaterials);
            BigDecimal amount = new BigDecimal(0);
            for (int i=0;i<list.size();i++){
                PurchaseReturnMaterials materials = list.get(i);
                String returnType = materials.getReturnType();
                //只有退货补数才有货物库存数量变化,不涉及金额
                if("THBS".equals(returnType)){
                    PurchaseDeliveryMaterials purchaseDeliveryMaterials = purchaseDeliveryMaterialsMapper.selectPurchaseDeliveryMaterialsById(materials.getPurchaseDeliveryMaterialsId());
                    String remark = purchaseDeliveryMaterials.getRemark();
                    if(null==remark){
                        remark = "";
                    }
                    Integer returnQty = materials.getReturnQty();
                    if("approve".equals(editType)){
                        //对应的到货减去数量
                        purchaseDeliveryMaterials.setQty(purchaseDeliveryMaterials.getQty()-returnQty);
                        purchaseDeliveryMaterials.setReturnQty(materials.getReturnQty()+returnQty);
                        purchaseDeliveryMaterials.setRemark(remark+",退货补数"+returnQty);
                        //退货出库
                        warehouseRecordService.materialsInbound("CGTH",materials.getWarehouseId(),(0-materials.getQty()),materials.getMaterialsId(),"");
                    }else if("unapprove".equals(editType)){
                        purchaseDeliveryMaterials.setQty(purchaseDeliveryMaterials.getQty()+returnQty);
                        purchaseDeliveryMaterials.setReturnQty(materials.getReturnQty()-returnQty);
                        //反审入库
                        warehouseRecordService.materialsInbound("CGTH",materials.getWarehouseId(),materials.getQty(),materials.getMaterialsId(),"反审退回");
                    }
                    purchaseDeliveryMaterialsMapper.updatePurchaseDeliveryMaterials(purchaseDeliveryMaterials);
                }
            }
        }
    }
}
