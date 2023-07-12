package com.ruoyi.project.purchase.purchaseOrderMaterials.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configMaterials.mapper.ConfigMaterialsMapper;
import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
import com.ruoyi.project.purchase.purchaseOrder.mapper.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.purchase.purchaseOrderMaterials.mapper.PurchaseOrderMaterialsMapper;
import com.ruoyi.project.purchase.purchaseOrderMaterials.domain.PurchaseOrderMaterials;
import com.ruoyi.project.purchase.purchaseOrderMaterials.service.IPurchaseOrderMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 采购订单材料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Service
public class PurchaseOrderMaterialsServiceImpl implements IPurchaseOrderMaterialsService 
{
    @Autowired
    private PurchaseOrderMaterialsMapper purchaseOrderMaterialsMapper;
    @Autowired
    private ConfigMaterialsMapper configMaterialsMapper;
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    /**
     * 查询采购订单材料
     * 
     * @param id 采购订单材料ID
     * @return 采购订单材料
     */
    @Override
    public PurchaseOrderMaterials selectPurchaseOrderMaterialsById(Long id)
    {
        return purchaseOrderMaterialsMapper.selectPurchaseOrderMaterialsById(id);
    }

    /**
     * 查询采购订单材料列表
     * 
     * @param purchaseOrderMaterials 采购订单材料
     * @return 采购订单材料
     */
    @Override
    public List<PurchaseOrderMaterials> selectPurchaseOrderMaterialsList(PurchaseOrderMaterials purchaseOrderMaterials)
    {
        return purchaseOrderMaterialsMapper.selectPurchaseOrderMaterialsList(purchaseOrderMaterials);
    }
    @Override
    public List<PurchaseOrderMaterials> selectPurchaseOrderMaterialsList(PurchaseOrder purchaseOrder)
    {
        List<PurchaseOrder> list = purchaseOrderMapper.selectPurchaseOrderList(purchaseOrder);
        if(list.size()==0){
            return new ArrayList<PurchaseOrderMaterials>();
        }
        String ids = "";
        for (int i=0;i<list.size();i++){
            if(i==0){
                ids = list.get(i).getId().toString();
            }else{
                ids += "," + list.get(i).getId();
            }
        }
        PurchaseOrderMaterials purchaseOrderMaterials = new PurchaseOrderMaterials();
        purchaseOrderMaterials.setIds(ids);
        return purchaseOrderMaterialsMapper.selectPurchaseOrderMaterialsList(purchaseOrderMaterials);
    }

    /**
     * 新增采购订单材料
     * 
     * @param purchaseOrderMaterials 采购订单材料
     * @return 结果
     */
    @Override
    public int insertPurchaseOrderMaterials(PurchaseOrderMaterials purchaseOrderMaterials)
    {
        purchaseOrderMaterials = setMaterialsInfo(purchaseOrderMaterials);
        return purchaseOrderMaterialsMapper.insertPurchaseOrderMaterials(purchaseOrderMaterials);
    }

    /**
     * 修改采购订单材料
     * 
     * @param purchaseOrderMaterials 采购订单材料
     * @return 结果
     */
    @Override
    public int updatePurchaseOrderMaterials(PurchaseOrderMaterials purchaseOrderMaterials)
    {
        purchaseOrderMaterials = setMaterialsInfo(purchaseOrderMaterials);
        return purchaseOrderMaterialsMapper.updatePurchaseOrderMaterials(purchaseOrderMaterials);
    }

    /**
     * 删除采购订单材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderMaterialsByIds(String ids)
    {
        return purchaseOrderMaterialsMapper.deletePurchaseOrderMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购订单材料信息
     * 
     * @param id 采购订单材料ID
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderMaterialsById(Long id)
    {
        return purchaseOrderMaterialsMapper.deletePurchaseOrderMaterialsById(id);
    }


    /**
     * 获取材料基础信息
     * @Author 方舟
     * @Date 2021/5/11 13:29:34
     **/
    private PurchaseOrderMaterials setMaterialsInfo(PurchaseOrderMaterials purchaseOrderMaterials){
        ConfigMaterials configMaterials = configMaterialsMapper.selectConfigMaterialsById(purchaseOrderMaterials.getMaterialsId());
        purchaseOrderMaterials.setSizeLong(configMaterials.getSizeLong());
        purchaseOrderMaterials.setSizeWidth(configMaterials.getSizeWidth());
        // BigDecimal amount = purchaseOrderMaterials.getPrice().multiply(new BigDecimal(purchaseOrderMaterials.getQty()));
        // purchaseOrderMaterials.setAmount(amount);
        return purchaseOrderMaterials;
    }
}
