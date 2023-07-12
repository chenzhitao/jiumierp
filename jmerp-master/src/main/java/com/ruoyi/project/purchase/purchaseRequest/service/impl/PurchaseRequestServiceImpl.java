package com.ruoyi.project.purchase.purchaseRequest.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.AddressUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configMaterials.service.IConfigMaterialsService;
import com.ruoyi.project.config.configMaterials.service.impl.ConfigMaterialsServiceImpl;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import com.ruoyi.project.produce.produceOrderMaterials.domain.ProduceOrderMaterials;
import com.ruoyi.project.produce.produceOrderMaterials.mapper.ProduceOrderMaterialsMapper;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.purchase.purchaseRequest.mapper.PurchaseRequestMapper;
import com.ruoyi.project.purchase.purchaseRequest.domain.PurchaseRequest;
import com.ruoyi.project.purchase.purchaseRequest.service.IPurchaseRequestService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 采购申请Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
@Service
public class PurchaseRequestServiceImpl implements IPurchaseRequestService 
{
    private static final Logger log = LoggerFactory.getLogger(PurchaseRequestServiceImpl.class);

    @Autowired
    private PurchaseRequestMapper purchaseRequestMapper;
    @Autowired
    private ProduceOrderMaterialsMapper produceOrderMaterialsMapper;
    @Autowired
    private IConfigMaterialsService configMaterialsService;
    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询采购申请
     * 
     * @param id 采购申请ID
     * @return 采购申请
     */
    @Override
    public PurchaseRequest selectPurchaseRequestById(Long id)
    {
        return purchaseRequestMapper.selectPurchaseRequestById(id);
    }

    /**
     * 查询采购申请列表
     * 
     * @param purchaseRequest 采购申请
     * @return 采购申请
     */
    @Override
    public List<PurchaseRequest> selectPurchaseRequestList(PurchaseRequest purchaseRequest)
    {
        return purchaseRequestMapper.selectPurchaseRequestList(purchaseRequest);
    }

    /**
     * 新增采购申请
     * 
     * @param purchaseRequest 采购申请
     * @return 结果
     */
    @Override
    public int insertPurchaseRequest(PurchaseRequest purchaseRequest)
    {
        purchaseRequest.setCreateBy(ShiroUtils.getSysUser().getUserName());
        purchaseRequest.setCreateTime(DateUtils.getNowDate());
        purchaseRequest.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseRequest.setUpdateTime(DateUtils.getNowDate());
        return purchaseRequestMapper.insertPurchaseRequest(purchaseRequest);
    }

    /**
     * 修改采购申请
     * 
     * @param purchaseRequest 采购申请
     * @return 结果
     */
    @Override
    public int updatePurchaseRequest(PurchaseRequest purchaseRequest)
    {
        purchaseRequest.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        purchaseRequest.setUpdateTime(DateUtils.getNowDate());
        int result = 0;
        if(!StringUtils.isEmpty(purchaseRequest.getRzyValue1())&&"approveBatch".equals(purchaseRequest.getRzyValue1())){
            String[] ids = Convert.toStrArray(purchaseRequest.getIds());
            for (int i=0;i<ids.length;i++){
                PurchaseRequest params = new PurchaseRequest();
                Long id = Long.parseLong(ids[i]);
                params = purchaseRequest;
                params.setId(id);
                result = purchaseRequestMapper.updatePurchaseRequest(params);
            }
        }else{
            result = purchaseRequestMapper.updatePurchaseRequest(purchaseRequest);
        }
        return result;
    }

    /**
     * 删除采购申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchaseRequestByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            PurchaseRequest param = purchaseRequestMapper.selectPurchaseRequestById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = purchaseRequestMapper.deletePurchaseRequestById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = purchaseRequestMapper.updatePurchaseRequest(param);
            }
        }
        return result;
        //return purchaseRequestMapper.deletePurchaseRequestByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购申请信息
     * 
     * @param id 采购申请ID
     * @return 结果
     */
    @Override
    public int deletePurchaseRequestById(Long id)
    {
        int result = 0;
        PurchaseRequest param = purchaseRequestMapper.selectPurchaseRequestById(id);
        if("draft".equals(param.getStatus())){
            result = purchaseRequestMapper.deletePurchaseRequestById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = purchaseRequestMapper.updatePurchaseRequest(param);
        }
        return result;
        //return purchaseRequestMapper.deletePurchaseRequestById(id);
    }

    /**
     * 选择施工材料创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public PurchaseRequest createPurchaseRequest(PurchaseRequest purchaseRequest){
        String[] ids = Convert.toStrArray(purchaseRequest.getIds());
        for (int i=0;i<ids.length;i++){
            Long id = Long.parseLong(ids[i]);
            PurchaseRequest saveVO = new PurchaseRequest();
            ProduceOrderMaterials produceOrderMaterials = produceOrderMaterialsMapper.selectProduceOrderMaterialsById(id);
            ConfigMaterials paramVO = new ConfigMaterials();
            paramVO.setId(produceOrderMaterials.getMaterialsId());
            ConfigMaterials configMaterials = configMaterialsService.getMaterialsBaseInfo(paramVO);
            BeanUtils.copyProperties(produceOrderMaterials, saveVO);
            BeanUtils.copyProperties(configMaterials, saveVO);
            saveVO.setProduceOrderMaterialsId(id);
            saveVO.setContact(configMaterials.getContactName());
            saveVO.setStatus("draft");
            saveVO.setDeliveryDate(DateUtils.getNowDate());
            insertPurchaseRequest(saveVO);
        }
        return purchaseRequest;
    }

    /**
     * 导入用户数据
     *
     * @param purchaseRequestList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importPurchaseRequest(List<PurchaseRequest> purchaseRequestList, Boolean isUpdateSupport){
        if (StringUtils.isNull(purchaseRequestList) || purchaseRequestList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (PurchaseRequest purchaseRequest : purchaseRequestList){
            boolean checkFlag = false;
            try{
                //材料必填
                if(StringUtils.isEmpty(purchaseRequest.getMaterialsName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 材料必填");
                }
                //数量必填
                if(null==purchaseRequest.getQty()){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 数量必填");
                }

                //转换部门id
                if(!StringUtils.isEmpty(purchaseRequest.getSupplierName())){
                    purchaseRequest.setSupplierId(rzyCommonMapper.findIdByName(purchaseRequest.getSupplierName(),"config_supplier","supplier_name","id"));
                }
                if(!StringUtils.isEmpty(purchaseRequest.getMaterialsName())){
                    purchaseRequest.setMaterialsId(rzyCommonMapper.findIdByName(purchaseRequest.getMaterialsName(),"config_materials","materials_name","id"));
                }
                if(null==purchaseRequest.getMaterialsId()){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 材料无效");
                }else{
                    ConfigMaterials configMaterials = configMaterialsService.selectConfigMaterialsById(purchaseRequest.getMaterialsId());
                    purchaseRequest.setSizeLong(configMaterials.getSizeLong());
                    purchaseRequest.setSizeWidth(configMaterials.getSizeWidth());
                }
                //成功
                if(!checkFlag){
                    this.insertPurchaseRequest(purchaseRequest);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + purchaseRequest.getMaterialsName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、员工 " + purchaseRequest.getMaterialsName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0){
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
