package com.ruoyi.project.purchase.purchaseChecking.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.FormulaUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.config.configSupplier.mapper.ConfigSupplierMapper;
import com.ruoyi.project.purchase.purchaseCheckingMaterials.domain.PurchaseCheckingMaterials;
import com.ruoyi.project.purchase.purchaseCheckingMaterials.mapper.PurchaseCheckingMaterialsMapper;
import com.ruoyi.project.purchase.purchaseDelivery.domain.PurchaseDelivery;
import com.ruoyi.project.purchase.purchaseDelivery.mapper.PurchaseDeliveryMapper;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.domain.PurchaseDeliveryMaterials;
import com.ruoyi.project.purchase.purchaseDeliveryMaterials.mapper.PurchaseDeliveryMaterialsMapper;
import com.ruoyi.project.purchase.purchaseOrder.domain.PurchaseOrder;
import com.ruoyi.project.purchase.purchaseReturn.domain.PurchaseReturn;
import com.ruoyi.project.purchase.purchaseReturnMaterials.domain.PurchaseReturnMaterials;
import com.ruoyi.project.purchase.purchaseReturnMaterials.mapper.PurchaseReturnMaterialsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.purchase.purchaseChecking.mapper.PurchaseCheckingMapper;
import com.ruoyi.project.purchase.purchaseChecking.domain.PurchaseChecking;
import com.ruoyi.project.purchase.purchaseChecking.service.IPurchaseCheckingService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 采购对账Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Service
public class PurchaseCheckingServiceImpl implements IPurchaseCheckingService 
{
    @Autowired
    private PurchaseCheckingMapper purchaseCheckingMapper;
    @Autowired
    private PurchaseCheckingMaterialsMapper purchaseCheckingMaterialsMapper;
    @Autowired
    private PurchaseDeliveryMaterialsMapper purchaseDeliveryMaterialsMapper;
    @Autowired
    private PurchaseReturnMaterialsMapper purchaseReturnMaterialsMapper;
    @Autowired
    private PurchaseDeliveryMapper purchaseDeliveryMapper;
    @Autowired
    private ConfigSupplierMapper configSupplierMapper;

    /**
     * 查询采购对账
     * 
     * @param id 采购对账ID
     * @return 采购对账
     */
    @Override
    public PurchaseChecking selectPurchaseCheckingById(Long id)
    {
        return purchaseCheckingMapper.selectPurchaseCheckingById(id);
    }

    /**
     * 查询采购对账列表
     * 
     * @param purchaseChecking 采购对账
     * @return 采购对账
     */
    @Override
    public List<PurchaseChecking> selectPurchaseCheckingList(PurchaseChecking purchaseChecking)
    {
        return purchaseCheckingMapper.selectPurchaseCheckingList(purchaseChecking);
    }

    /**
     * 新增采购对账
     * 
     * @param purchaseChecking 采购对账
     * @return 结果
     */
    @Override
    public int insertPurchaseChecking(PurchaseChecking purchaseChecking)
    {
        purchaseChecking.setCreateBy(ShiroUtils.getSysUser().getUserName());
        purchaseChecking.setCreateTime(DateUtils.getNowDate());
        purchaseChecking.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseChecking.setUpdateTime(DateUtils.getNowDate());
        if(null==purchaseChecking.getCheckingDate()){
            purchaseChecking.setCheckingDate(DateUtils.getNowDate());
        }
        return purchaseCheckingMapper.insertPurchaseChecking(purchaseChecking);
    }

    /**
     * 修改采购对账
     * 
     * @param purchaseChecking 采购对账
     * @return 结果
     */
    @Override
    public int updatePurchaseChecking(PurchaseChecking purchaseChecking)
    {
        purchaseChecking.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseChecking.setUpdateTime(DateUtils.getNowDate());
        return purchaseCheckingMapper.updatePurchaseChecking(purchaseChecking);
    }

    /**
     * 删除采购对账对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseCheckingByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            PurchaseChecking param = purchaseCheckingMapper.selectPurchaseCheckingById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = purchaseCheckingMapper.deletePurchaseCheckingById(Long.parseLong(arr[i]));
                purchaseCheckingMapper.deletePurchaseCheckingMaterialsById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = purchaseCheckingMapper.updatePurchaseChecking(param);
            }
        }
        return result;
        //return purchaseCheckingMapper.deletePurchaseCheckingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购对账信息
     * 
     * @param id 采购对账ID
     * @return 结果
     */
    @Override
    public int deletePurchaseCheckingById(Long id)
    {
        int result = 0;
        PurchaseChecking param = purchaseCheckingMapper.selectPurchaseCheckingById(id);
        if("draft".equals(param.getStatus())){
            result = purchaseCheckingMapper.deletePurchaseCheckingById(id);
            purchaseCheckingMapper.deletePurchaseCheckingMaterialsById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = purchaseCheckingMapper.updatePurchaseChecking(param);
        }
        return result;
        //return purchaseCheckingMapper.deletePurchaseCheckingById(id);
    }


    /**
     * 选择产品创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public PurchaseChecking createPurchaseChecking(PurchaseChecking purchaseChecking){
        String[] arr = Convert.toStrArray(purchaseChecking.getIds());
        Long headerId = 0L;
        BigDecimal taxRate = new BigDecimal(0);
        for (int i=0;i<arr.length;i++){
            Long materialsLineId = Long.parseLong(arr[i]);
            PurchaseDeliveryMaterials purchaseDeliveryMaterials = purchaseDeliveryMaterialsMapper.selectPurchaseDeliveryMaterialsById(materialsLineId);
            PurchaseDelivery purchaseDelivery = purchaseDeliveryMapper.selectPurchaseDeliveryById(purchaseDeliveryMaterials.getPurchaseDeliveryId());
            ConfigSupplier configSupplier = configSupplierMapper.selectConfigSupplierById(purchaseDelivery.getSupplierId());
            if(!StringUtils.isEmpty(purchaseChecking.getRzyValue2())&&"A".equals(purchaseChecking.getRzyValue2())){
                headerId = purchaseChecking.getId();
                taxRate = purchaseDelivery.getTaxRate();
            }else{
                if(i==0){
                    //insert头
                    PurchaseChecking headerVO = new PurchaseChecking();
                    BeanUtils.copyProperties(purchaseDelivery, headerVO);
                    headerVO.setCheckingDate(DateUtils.getNowDate());
                    headerVO.setStatus("draft");
                    headerVO.setAmount(new BigDecimal(0));
                    insertPurchaseChecking(headerVO);
                    headerId = headerVO.getId();
                }
            }
            //insert行
            PurchaseCheckingMaterials purchaseCheckingMaterials = new PurchaseCheckingMaterials();
            BeanUtils.copyProperties(purchaseDeliveryMaterials, purchaseCheckingMaterials);
            purchaseCheckingMaterials.setPurchaseDeliveryMaterialsId(materialsLineId);
            purchaseCheckingMaterials.setPurchaseCheckingId(headerId);
            purchaseCheckingMaterialsMapper.insertPurchaseCheckingMaterials(purchaseCheckingMaterials);
        }
        purchaseChecking.setId(headerId);
        return calculateAmount(purchaseChecking);
    }


    /**
     * 计算金额
     * @Author 方舟
     * @Date 2021/5/27 14:45:12
    **/
    @Override
    public PurchaseChecking calculateAmount(PurchaseChecking purchaseChecking){
        Long purchaseCheckingId = purchaseChecking.getId();
        BigDecimal deliveryAmount = new BigDecimal(0);
        BigDecimal returnAmount = new BigDecimal(0);
        BigDecimal amount = new BigDecimal(0);
        PurchaseDeliveryMaterials paramDeliveryVO = new PurchaseDeliveryMaterials();
        paramDeliveryVO.setPurchaseCheckingId(purchaseCheckingId);
        List<PurchaseDeliveryMaterials> dmlist = purchaseDeliveryMaterialsMapper.selectPurchaseDeliveryMaterialsList(paramDeliveryVO);
        for (int i=0;i<dmlist.size();i++){
            deliveryAmount = deliveryAmount.add(dmlist.get(i).getAmount());
        }
        PurchaseReturnMaterials paramReturnVO = new PurchaseReturnMaterials();
        paramReturnVO.setPurchaseCheckingId(purchaseCheckingId);
        List<PurchaseReturnMaterials> rmlist = purchaseReturnMaterialsMapper.selectPurchaseReturnMaterialsList(paramReturnVO);
        for (int i=0;i<rmlist.size();i++){
            returnAmount = returnAmount.add(rmlist.get(i).getAmount());
        }
        amount = deliveryAmount.subtract(returnAmount);
        PurchaseChecking saveVO = new PurchaseChecking();
        saveVO.setId(purchaseCheckingId);
        saveVO.setDeliveryAmount(deliveryAmount);
        saveVO.setReturnAmount(returnAmount);
        saveVO.setAmount(amount);
        updatePurchaseChecking(saveVO);
        return saveVO;
    }
}
