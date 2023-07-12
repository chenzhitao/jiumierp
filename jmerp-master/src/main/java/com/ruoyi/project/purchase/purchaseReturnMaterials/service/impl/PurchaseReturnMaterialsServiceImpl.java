package com.ruoyi.project.purchase.purchaseReturnMaterials.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.purchase.purchaseReturnMaterials.mapper.PurchaseReturnMaterialsMapper;
import com.ruoyi.project.purchase.purchaseReturnMaterials.domain.PurchaseReturnMaterials;
import com.ruoyi.project.purchase.purchaseReturnMaterials.service.IPurchaseReturnMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 采购退货材料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Service
public class PurchaseReturnMaterialsServiceImpl implements IPurchaseReturnMaterialsService 
{
    @Autowired
    private PurchaseReturnMaterialsMapper purchaseReturnMaterialsMapper;

    /**
     * 查询采购退货材料
     * 
     * @param id 采购退货材料ID
     * @return 采购退货材料
     */
    @Override
    public PurchaseReturnMaterials selectPurchaseReturnMaterialsById(Long id)
    {
        return purchaseReturnMaterialsMapper.selectPurchaseReturnMaterialsById(id);
    }

    /**
     * 查询采购退货材料列表
     * 
     * @param purchaseReturnMaterials 采购退货材料
     * @return 采购退货材料
     */
    @Override
    public List<PurchaseReturnMaterials> selectPurchaseReturnMaterialsList(PurchaseReturnMaterials purchaseReturnMaterials)
    {
        return purchaseReturnMaterialsMapper.selectPurchaseReturnMaterialsList(purchaseReturnMaterials);
    }

    /**
     * 新增采购退货材料
     * 
     * @param purchaseReturnMaterials 采购退货材料
     * @return 结果
     */
    @Override
    public int insertPurchaseReturnMaterials(PurchaseReturnMaterials purchaseReturnMaterials)
    {
        return purchaseReturnMaterialsMapper.insertPurchaseReturnMaterials(purchaseReturnMaterials);
    }

    /**
     * 修改采购退货材料
     * 
     * @param purchaseReturnMaterials 采购退货材料
     * @return 结果
     */
    @Override
    public int updatePurchaseReturnMaterials(PurchaseReturnMaterials purchaseReturnMaterials)
    {
        return purchaseReturnMaterialsMapper.updatePurchaseReturnMaterials(purchaseReturnMaterials);
    }

    /**
     * 删除采购退货材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseReturnMaterialsByIds(String ids)
    {
        return purchaseReturnMaterialsMapper.deletePurchaseReturnMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购退货材料信息
     * 
     * @param id 采购退货材料ID
     * @return 结果
     */
    @Override
    public int deletePurchaseReturnMaterialsById(Long id)
    {
        return purchaseReturnMaterialsMapper.deletePurchaseReturnMaterialsById(id);
    }
}
