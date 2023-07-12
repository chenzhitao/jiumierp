package com.ruoyi.project.purchase.purchaseDeliveryMaterials.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configMaterials.mapper.ConfigMaterialsMapper;
import com.ruoyi.project.purchase.purchaseOrderMaterials.domain.PurchaseOrderMaterials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.mapper.PurchaseDeliveryMaterialsMapper;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain.PurchaseDeliveryMaterials;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.service.IPurchaseDeliveryMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 采购到货材料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Service
public class PurchaseDeliveryMaterialsServiceImpl implements IPurchaseDeliveryMaterialsService 
{
    @Autowired
    private PurchaseDeliveryMaterialsMapper purchaseDeliveryMaterialsMapper;
    @Autowired
    private ConfigMaterialsMapper configMaterialsMapper;

    /**
     * 查询采购到货材料
     * 
     * @param id 采购到货材料ID
     * @return 采购到货材料
     */
    @Override
    public PurchaseDeliveryMaterials selectPurchaseDeliveryMaterialsById(Long id)
    {
        return purchaseDeliveryMaterialsMapper.selectPurchaseDeliveryMaterialsById(id);
    }

    /**
     * 查询采购到货材料列表
     * 
     * @param purchaseDeliveryMaterials 采购到货材料
     * @return 采购到货材料
     */
    @Override
    public List<PurchaseDeliveryMaterials> selectPurchaseDeliveryMaterialsList(PurchaseDeliveryMaterials purchaseDeliveryMaterials)
    {
        return purchaseDeliveryMaterialsMapper.selectPurchaseDeliveryMaterialsList(purchaseDeliveryMaterials);
    }

    /**
     * 新增采购到货材料
     * 
     * @param purchaseDeliveryMaterials 采购到货材料
     * @return 结果
     */
    @Override
    public int insertPurchaseDeliveryMaterials(PurchaseDeliveryMaterials purchaseDeliveryMaterials)
    {
        purchaseDeliveryMaterials = setMaterialsInfo(purchaseDeliveryMaterials);
        return purchaseDeliveryMaterialsMapper.insertPurchaseDeliveryMaterials(purchaseDeliveryMaterials);
    }

    /**
     * 修改采购到货材料
     * 
     * @param purchaseDeliveryMaterials 采购到货材料
     * @return 结果
     */
    @Override
    public int updatePurchaseDeliveryMaterials(PurchaseDeliveryMaterials purchaseDeliveryMaterials)
    {
        purchaseDeliveryMaterials = setMaterialsInfo(purchaseDeliveryMaterials);
        return purchaseDeliveryMaterialsMapper.updatePurchaseDeliveryMaterials(purchaseDeliveryMaterials);
    }

    /**
     * 删除采购到货材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseDeliveryMaterialsByIds(String ids)
    {
        return purchaseDeliveryMaterialsMapper.deletePurchaseDeliveryMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购到货材料信息
     * 
     * @param id 采购到货材料ID
     * @return 结果
     */
    @Override
    public int deletePurchaseDeliveryMaterialsById(Long id)
    {
        return purchaseDeliveryMaterialsMapper.deletePurchaseDeliveryMaterialsById(id);
    }

    /**
     * 获取材料基础信息
     * @Author 方舟
     * @Date 2021/5/11 13:29:34
     **/
    private PurchaseDeliveryMaterials setMaterialsInfo(PurchaseDeliveryMaterials purchaseDeliveryMaterials){
        ConfigMaterials configMaterials = configMaterialsMapper.selectConfigMaterialsById(purchaseDeliveryMaterials.getMaterialsId());
        purchaseDeliveryMaterials.setSizeLong(configMaterials.getSizeLong());
        purchaseDeliveryMaterials.setSizeWidth(configMaterials.getSizeWidth());
        /*BigDecimal amount = purchaseDeliveryMaterials.getPrice().multiply(new BigDecimal(purchaseDeliveryMaterials.getQty()));
        purchaseDeliveryMaterials.setAmount(amount);*/
        return purchaseDeliveryMaterials;
    }
}
