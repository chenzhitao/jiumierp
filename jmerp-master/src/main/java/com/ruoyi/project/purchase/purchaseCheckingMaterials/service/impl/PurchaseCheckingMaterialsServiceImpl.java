package com.ruoyi.project.purchase.purchaseCheckingMaterials.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.purchase.purchaseCheckingMaterials.mapper.PurchaseCheckingMaterialsMapper;
import com.ruoyi.project.purchase.purchaseCheckingMaterials.domain.PurchaseCheckingMaterials;
import com.ruoyi.project.purchase.purchaseCheckingMaterials.service.IPurchaseCheckingMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 采购对账材料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Service
public class PurchaseCheckingMaterialsServiceImpl implements IPurchaseCheckingMaterialsService 
{
    @Autowired
    private PurchaseCheckingMaterialsMapper purchaseCheckingMaterialsMapper;

    /**
     * 查询采购对账材料
     * 
     * @param id 采购对账材料ID
     * @return 采购对账材料
     */
    @Override
    public PurchaseCheckingMaterials selectPurchaseCheckingMaterialsById(Long id)
    {
        return purchaseCheckingMaterialsMapper.selectPurchaseCheckingMaterialsById(id);
    }

    /**
     * 查询采购对账材料列表
     * 
     * @param purchaseCheckingMaterials 采购对账材料
     * @return 采购对账材料
     */
    @Override
    public List<PurchaseCheckingMaterials> selectPurchaseCheckingMaterialsList(PurchaseCheckingMaterials purchaseCheckingMaterials)
    {
        return purchaseCheckingMaterialsMapper.selectPurchaseCheckingMaterialsList(purchaseCheckingMaterials);
    }

    /**
     * 新增采购对账材料
     * 
     * @param purchaseCheckingMaterials 采购对账材料
     * @return 结果
     */
    @Override
    public int insertPurchaseCheckingMaterials(PurchaseCheckingMaterials purchaseCheckingMaterials)
    {
        return purchaseCheckingMaterialsMapper.insertPurchaseCheckingMaterials(purchaseCheckingMaterials);
    }

    /**
     * 修改采购对账材料
     * 
     * @param purchaseCheckingMaterials 采购对账材料
     * @return 结果
     */
    @Override
    public int updatePurchaseCheckingMaterials(PurchaseCheckingMaterials purchaseCheckingMaterials)
    {
        return purchaseCheckingMaterialsMapper.updatePurchaseCheckingMaterials(purchaseCheckingMaterials);
    }

    /**
     * 删除采购对账材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseCheckingMaterialsByIds(String ids)
    {
        return purchaseCheckingMaterialsMapper.deletePurchaseCheckingMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购对账材料信息
     * 
     * @param id 采购对账材料ID
     * @return 结果
     */
    @Override
    public int deletePurchaseCheckingMaterialsById(Long id)
    {
        return purchaseCheckingMaterialsMapper.deletePurchaseCheckingMaterialsById(id);
    }
}
