package com.ruoyi.project.purchase.purchaseOrder.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configMaterials.mapper.ConfigMaterialsMapper;
import com.ruoyi.project.config.configMaterials.service.IConfigMaterialsService;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.purchase.purchaseOrderMaterials.domain.PurchaseOrderMaterials;
import com.ruoyi.project.purchase.purchaseOrderMaterials.mapper.PurchaseOrderMaterialsMapper;
import com.ruoyi.project.purchase.purchaseRequest.domain.PurchaseRequest;
import com.ruoyi.project.purchase.purchaseRequest.mapper.PurchaseRequestMapper;
import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturn;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.purchase.purchaseOrder.mapper.PurchaseOrderMapper;
import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
import com.ruoyi.project.purchase.purchaseOrder.service.IPurchaseOrderService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 采购订单Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService 
{
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private PurchaseOrderMaterialsMapper purchaseOrderMaterialsMapper;
    @Autowired
    private PurchaseRequestMapper purchaseRequestMapper;
    @Autowired
    private ConfigMaterialsMapper configMaterialsMapper;
    @Autowired
    private IConfigMaterialsService configMaterialsService;

    /**
     * 查询采购订单
     * 
     * @param id 采购订单ID
     * @return 采购订单
     */
    @Override
    public PurchaseOrder selectPurchaseOrderById(Long id)
    {
        return purchaseOrderMapper.selectPurchaseOrderById(id);
    }

    /**
     * 查询采购订单列表
     * 
     * @param purchaseOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<PurchaseOrder> selectPurchaseOrderList(PurchaseOrder purchaseOrder)
    {
        return purchaseOrderMapper.selectPurchaseOrderList(purchaseOrder);
    }

    /**
     * 新增采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int insertPurchaseOrder(PurchaseOrder purchaseOrder)
    {
        purchaseOrder.setCreateBy(ShiroUtils.getSysUser().getUserName());
        purchaseOrder.setCreateTime(DateUtils.getNowDate());
        purchaseOrder.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseOrder.setUpdateTime(DateUtils.getNowDate());
        if(null==purchaseOrder.getDeliveryDate()){
            purchaseOrder.setDeliveryDate(DateUtils.getNowDate());
        }
        return purchaseOrderMapper.insertPurchaseOrder(purchaseOrder);
    }

    /**
     * 修改采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int updatePurchaseOrder(PurchaseOrder purchaseOrder)
    {
        purchaseOrder.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseOrder.setUpdateTime(DateUtils.getNowDate());
        return purchaseOrderMapper.updatePurchaseOrder(purchaseOrder);
    }

    /**
     * 删除采购订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            PurchaseOrder param = purchaseOrderMapper.selectPurchaseOrderById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = purchaseOrderMapper.deletePurchaseOrderById(Long.parseLong(arr[i]));
                purchaseOrderMapper.deletePurchaseOrderMaterialsById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = purchaseOrderMapper.updatePurchaseOrder(param);
            }
        }
        return result;
        //return purchaseOrderMapper.deletePurchaseOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购订单信息
     * 
     * @param id 采购订单ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderById(Long id)
    {
        int result = 0;
        PurchaseOrder param = purchaseOrderMapper.selectPurchaseOrderById(id);
        if("draft".equals(param.getStatus())){
            result = purchaseOrderMapper.deletePurchaseOrderById(id);
            purchaseOrderMapper.deletePurchaseOrderMaterialsById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = purchaseOrderMapper.updatePurchaseOrder(param);
        }
        return result;
        //return purchaseOrderMapper.deletePurchaseOrderById(id);
    }

    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder){
        String[] arr = Convert.toStrArray(purchaseOrder.getIds());
        Long headerId = 0L;
        for (int i=0;i<arr.length;i++){
            Long requestId = Long.parseLong(arr[i]);
            PurchaseRequest purchaseRequest = purchaseRequestMapper.selectPurchaseRequestById(requestId);
            if(i==0){
                //insert头
                PurchaseOrder headerVO = new PurchaseOrder();
                BeanUtils.copyProperties(purchaseRequest, headerVO);
                headerVO.setDeliveryDate(DateUtils.getNowDate());
                headerVO.setStatus("draft");
                headerVO.setPurchaseOrderType("PT");
                insertPurchaseOrder(headerVO);
                headerId = headerVO.getId();
            }
            //insert行
            PurchaseOrderMaterials purchaseOrderMaterials = new PurchaseOrderMaterials();
            BeanUtils.copyProperties(purchaseRequest, purchaseOrderMaterials);
            ConfigMaterials configMaterials = configMaterialsMapper.selectConfigMaterialsById(purchaseRequest.getMaterialsId());
            purchaseOrderMaterials.setPurchaseRequestId(requestId);
            purchaseOrderMaterials.setPurchaseOrderId(headerId);
            if(null!=configMaterials){
                purchaseOrderMaterials.setPrice(configMaterials.getPurchasePrice());
                BigDecimal amount = configMaterials.getPurchasePrice().multiply(new BigDecimal(purchaseRequest.getQty()));
                purchaseOrderMaterials.setAmount(amount);
            }
            purchaseOrderMaterialsMapper.insertPurchaseOrderMaterials(purchaseOrderMaterials);
        }
        purchaseOrder.setId(headerId);
        return purchaseOrder;
    }

    /**
     * 算钱
     * @Author 方舟
     * @Date 2021/5/11 12:13:04
     **/
    @Override
    public PurchaseOrder calculatorAmount(PurchaseOrder purchaseOrder){
        PurchaseOrderMaterials materialsParams = new PurchaseOrderMaterials();
        materialsParams.setPurchaseOrderId(purchaseOrder.getId());
        List<PurchaseOrderMaterials> materialsList = purchaseOrderMaterialsMapper.selectPurchaseOrderMaterialsList(materialsParams);
        BigDecimal amount = new BigDecimal(0);
        for (int i=0;i<materialsList.size();i++){
            PurchaseOrderMaterials materialsVO = materialsList.get(i);
            //BigDecimal materialsAmount = materialsVO.getPrice().multiply(new BigDecimal(materialsVO.getQty()));
            BigDecimal materialsAmount = materialsVO.getAmount();
            amount = amount.add(materialsAmount);
            //materialsVO.setAmount(materialsAmount);
            //purchaseOrderMaterialsMapper.updatePurchaseOrderMaterials(materialsVO);
        }
        purchaseOrder.setAmount(amount);
        updatePurchaseOrder(purchaseOrder);
        return purchaseOrder;
    }

}
