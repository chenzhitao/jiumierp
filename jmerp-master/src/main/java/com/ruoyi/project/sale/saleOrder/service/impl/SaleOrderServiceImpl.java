package com.ruoyi.project.sale.saleOrder.service.impl;

import java.awt.color.ICC_ColorSpace;
import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configCustomer.service.IConfigCustomerService;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.config.configProduct.service.IConfigProductService;
import com.ruoyi.project.produce.produceOrder.service.IProduceOrderService;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import com.ruoyi.project.sale.saleDelivery.domain.SaleDelivery;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrderExport;
import com.ruoyi.project.sale.saleOrderDelivery.domain.SaleOrderDelivery;
import com.ruoyi.project.sale.saleOrderDelivery.mapper.SaleOrderDeliveryMapper;
import com.ruoyi.project.sale.saleOrderDelivery.service.ISaleOrderDeliveryService;
import com.ruoyi.project.sale.saleOrderMaterials.domain.SaleOrderMaterials;
import com.ruoyi.project.sale.saleOrderMaterials.mapper.SaleOrderMaterialsMapper;
import com.ruoyi.project.sale.saleOrderMaterials.service.ISaleOrderMaterialsService;
import com.ruoyi.project.sale.saleOrderProduct.domain.SaleOrderProduct;
import com.ruoyi.project.sale.saleOrderProduct.mapper.SaleOrderProductMapper;
import com.ruoyi.project.sale.saleOrderProduct.service.ISaleOrderProductService;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
import com.ruoyi.project.sale.saleQuotation.mapper.SaleQuotationMapper;
import com.ruoyi.project.sale.saleQuotation.service.ISaleQuotationService;
import com.ruoyi.project.sale.saleQuotation.service.impl.SaleQuotationServiceImpl;
import com.ruoyi.project.sale.saleQuotationMult.domain.SaleQuotationMult;
import com.ruoyi.project.sale.saleQuotationMult.mapper.SaleQuotationMultMapper;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
import com.ruoyi.project.sale.saleQuotationProduct.mapper.SaleQuotationProductMapper;
import com.ruoyi.project.warehouse.warehouseRecord.service.IWarehouseRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleOrder.mapper.SaleOrderMapper;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import com.ruoyi.project.sale.saleOrder.service.ISaleOrderService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 销售订单Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleOrderServiceImpl implements ISaleOrderService 
{
    private static final Logger log = LoggerFactory.getLogger(SaleOrderServiceImpl.class);

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Autowired
    private SaleQuotationMapper saleQuotationMapper;

    @Autowired
    private SaleQuotationProductMapper saleQuotationProductMapper;

    @Autowired
    private SaleQuotationMultMapper saleQuotationMultMapper;

    @Autowired
    private IConfigCustomerService configCustomerService;

    @Autowired
    private IConfigProductService configProductService;

    @Autowired
    private ISaleOrderProductService saleOrderProductService;

    @Autowired
    private SaleOrderProductMapper saleOrderProductMapper;

    @Autowired
    private SaleOrderDeliveryMapper saleOrderDeliveryMapper;

    @Autowired
    private SaleOrderMaterialsMapper saleOrderMaterialsMapper;

    @Autowired
    private ISaleOrderMaterialsService saleOrderMaterialsService;

    @Autowired
    private ISaleOrderDeliveryService saleOrderDeliveryService;

    @Autowired
    private IWarehouseRecordService warehouseRecordService;

    @Autowired
    private IProduceOrderService produceOrderService;

    /**
     * 查询销售订单
     * 
     * @param id 销售订单ID
     * @return 销售订单
     */
    @Override
    public SaleOrder selectSaleOrderById(Long id)
    {
        return saleOrderMapper.selectSaleOrderById(id);
    }

    /**
     * 查询销售订单列表
     * 
     * @param saleOrder 销售订单
     * @return 销售订单
     */
    @Override
    public List<SaleOrder> selectSaleOrderList(SaleOrder saleOrder)
    {
        saleOrder.setRzyUserId(ShiroUtils.getUserId());
        return saleOrderMapper.selectSaleOrderList(saleOrder);
    }
    @Override
    public List<SaleOrderExport> selectSaleOrderExportList(SaleOrder saleOrder)
    {
        saleOrder.setRzyUserId(ShiroUtils.getUserId());
        return saleOrderMapper.selectSaleOrderExportList(saleOrder);
    }

    /**
     * 新增销售订单
     * 
     * @param saleOrder 销售订单
     * @return 结果
     */
    @Override
    public int insertSaleOrder(SaleOrder saleOrder)
    {
        saleOrder.setCreateBy(ShiroUtils.getSysUser().getUserName());
        saleOrder.setCreateTime(DateUtils.getNowDate());
        saleOrder.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        saleOrder.setUpdateTime(DateUtils.getNowDate());
        if(null==saleOrder.getDeliveryDate()){
            saleOrder.setDeliveryDate(DateUtils.getNowDate());
        }
        return saleOrderMapper.insertSaleOrder(saleOrder);
    }

    /**
     * 修改销售订单
     * 
     * @param saleOrder 销售订单
     * @return 结果
     */
    @Override
    public int updateSaleOrder(SaleOrder saleOrder)
    {
        saleOrder.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        saleOrder.setUpdateTime(DateUtils.getNowDate());
        //客户带料库
        warehouseChange(saleOrder.getRzyValue1(),saleOrder.getId());
        //未排程的全部排程
        setupUnsendProduct(saleOrder);
        return saleOrderMapper.updateSaleOrder(saleOrder);
    }

    /**
     * 删除销售订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleOrderByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            SaleOrder param = saleOrderMapper.selectSaleOrderById(Long.parseLong(arr[i]));
            /*if("draft".equals(param.getStatus())){
                result = saleOrderMapper.deleteSaleOrderById(Long.parseLong(arr[i]));
                saleOrderMapper.deleteProduceOrderDeliveryById(Long.parseLong(arr[i]));
                saleOrderMapper.deleteProduceOrderMaterialsById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = saleOrderMapper.updateSaleOrder(param);
            }*/
            //不再物理删除
            param.setId(Long.parseLong(arr[i]));
            param.setStatus("delete");
            result = saleOrderMapper.updateSaleOrder(param);
        }
        return result;
        //return saleOrderMapper.deleteSaleOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单ID
     * @return 结果
     */
    @Override
    public int deleteSaleOrderById(Long id)
    {
        int result = 0;
        SaleOrder param = saleOrderMapper.selectSaleOrderById(id);
        /*if("draft".equals(param.getStatus())){
            result = saleOrderMapper.deleteSaleOrderById(id);
            saleOrderMapper.deleteProduceOrderDeliveryById(id);
            saleOrderMapper.deleteProduceOrderMaterialsById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = saleOrderMapper.updateSaleOrder(param);
        }*/
        //不再物理删除
        param.setId(id);
        param.setStatus("delete");
        result = saleOrderMapper.updateSaleOrder(param);
        return result;
        //return saleOrderMapper.deleteSaleOrderById(id);
    }

    /**
     * 选择客户创建
     * @Author 方舟
     * @Date 2021/4/21 10:10:56
     **/
    @Override
    public SaleOrder createSaleOrder(SaleOrder saleOrder){
        String ids = saleOrder.getIds();
        if(ids.indexOf("_")>-1){
            //选报价单创建
            String[] arr = Convert.toStrArray(ids);
            SaleOrder saveVO = new SaleOrder();
            for (int i=0;i<arr.length;i++){
                Long saleQuotationId = Long.parseLong(arr[i].split("_")[0]);
                Long saleQuotationProductId = Long.parseLong(arr[i].split("_")[1]);
                Long saleQuotationMultId = Long.parseLong(arr[i].split("_")[2]);
                if (i==0){
                    if(null==saleOrder.getId()){
                        SaleQuotation saleQuotation = saleQuotationMapper.selectSaleQuotationById(saleQuotationId);
                        Long customerId = saleQuotation.getCustomerId();
                        saveVO = createSaleOrderByCustomerId(customerId);
                    }else{
                        saveVO = saleOrderMapper.selectSaleOrderById(saleOrder.getId());
                    }
                }
                SaleQuotationProduct saleQuotationProduct = saleQuotationProductMapper.selectSaleQuotationProductById(saleQuotationProductId);
                SaleQuotationMult saleQuotationMult = saleQuotationMultMapper.selectSaleQuotationMultById(saleQuotationMultId);
                //创建产品
                SaleOrderProduct lineVO = setupSaleOrderProduct(saveVO,saleQuotationProduct,saleQuotationMult);
                saleOrderProductService.insertSaleOrderProduct(lineVO);
            }
            return saveVO;
        }else{
            //选客户创建
            Long customerId = Long.parseLong(saleOrder.getIds());
            return createSaleOrderByCustomerId(customerId);
        }
    }

    /**
     * 组装销售订单产品
     * @Author 方舟
     * @Date 2021/4/27 11:11:01
    **/
    private SaleOrderProduct setupSaleOrderProduct(SaleOrder saleOrder,SaleQuotationProduct saleQuotationProduct,SaleQuotationMult saleQuotationMult){
        ConfigProduct configProduct = configProductService.selectConfigProductById(saleQuotationProduct.getProductId());
        SaleOrderProduct lineVO = new SaleOrderProduct();
        lineVO.setSaleOrderId(saleOrder.getId());
        lineVO.setCustomerId(saleOrder.getCustomerId());
        lineVO.setProductId(saleQuotationProduct.getProductId());
        lineVO.setTaxRate(saleOrder.getTaxRate());
        lineVO.setDeliveryDate(DateUtils.getNowDate());
        lineVO.setAddress(saleOrder.getAddress());
        lineVO.setSaleQuotationProductId(saleQuotationProduct.getId());
        lineVO.setQty(saleQuotationMult.getQty());
        lineVO.setPrice(configProduct.getPrice());
        lineVO.setAmount(configProduct.getPrice().multiply(new BigDecimal(saleQuotationMult.getQty())));
        return lineVO;
    }

    /**
     * 根据客户ID创建
     * @Author 方舟
     * @Date 2021/4/27 10:34:48
    **/
    private SaleOrder createSaleOrderByCustomerId(Long customerId){
        ConfigCustomer configCustomer = configCustomerService.selectConfigCustomerById(customerId);
        SaleOrder saveVO = new SaleOrder();
        BeanUtils.copyProperties(configCustomer, saveVO);
        saveVO.setCustomerId(customerId);
        saveVO.setStatus("draft");
        saveVO = (SaleOrder) EntityUtils.nullStringToNull(saveVO);
        insertSaleOrder(saveVO);
        return selectSaleOrderById(saveVO.getId());
    }

    /**
     * 导入数据
     *
     * @param saleOrderProductList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importSaleOrder(List<SaleOrderProduct> saleOrderProductList, Boolean isUpdateSupport){
        if (StringUtils.isNull(saleOrderProductList) || saleOrderProductList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SaleOrderProduct saleOrderProduct : saleOrderProductList){
            boolean checkFlag = false;
            //空字符串处理
            saleOrderProduct = (SaleOrderProduct) EntityUtils.nullStringToNull(saleOrderProduct);
            try{
                SaleOrder saleOrder = new SaleOrder();
                //名称必填
                if(StringUtils.isEmpty(saleOrderProduct.getCustomerName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 客户名称必填");
                }else{
                    Long customerId = rzyCommonMapper.findIdByName(saleOrderProduct.getCustomerName(),"config_customer","customer_name","id");
                    if(null!=customerId){
                        ConfigCustomer configCustomer = configCustomerService.selectConfigCustomerById(customerId);
                        BeanUtils.copyProperties(configCustomer, saleOrder);
                        saleOrder.setCustomerId(customerId);
                        saleOrderProduct.setCustomerId(customerId);
                    }
                }
                if(StringUtils.isEmpty(saleOrderProduct.getProductName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 产品名称必填");
                }else{
                    saleOrderProduct.setProductId(rzyCommonMapper.findIdByName(saleOrderProduct.getProductName(),"config_product","product_name","id"));
                }
                if(null==saleOrderProduct.getProductId()){
                    if(null==saleOrderProduct.getSizeLong()||null==saleOrderProduct.getSizeWidth()||null==saleOrderProduct.getSizeHeight()){
                        checkFlag = true;
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、 新建产品，规格必填");
                    }
                }

                //选填
                if(null==saleOrderProduct.getQty()){
                    saleOrderProduct.setQty(0);
                }
                if(null==saleOrderProduct.getSizeLong()){
                    saleOrderProduct.setSizeLong(0);
                }
                if(null==saleOrderProduct.getSizeWidth()){
                    saleOrderProduct.setSizeWidth(0);
                }
                if(null==saleOrderProduct.getSizeHeight()){
                    saleOrderProduct.setSizeHeight(0);
                }
                if(null==saleOrderProduct.getDeliveryDate()){
                    saleOrderProduct.setDeliveryDate(DateUtils.getNowDate());
                    saleOrder.setDeliveryDate(DateUtils.getNowDate());
                }else{
                    saleOrder.setDeliveryDate(saleOrderProduct.getDeliveryDate());
                }
                if(null==saleOrderProduct.getPrice()){
                    saleOrderProduct.setPrice(new BigDecimal(0));
                }

                //成功
                if(!checkFlag){
                    importSaleOrder(saleOrder,saleOrderProduct);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 客户:" + saleOrderProduct.getCustomerName() + ",产品:" + saleOrderProduct.getProductName() + "的销售订单 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、客户:" + saleOrderProduct.getCustomerName() + ",产品:" + saleOrderProduct.getProductName() + "的销售订单 导入失败：";
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

    /**
     * 导入
     * @Author 方舟
     * @Date 2021/4/26 10:04:59
    **/
    private void importSaleOrder(SaleOrder saleOrder,SaleOrderProduct saleOrderProduct){
        //产品
        if(null==saleOrderProduct.getProductId()){
            //创建一个新产品
            ConfigProduct configProduct = new ConfigProduct();
            configProduct.setProductName(saleOrderProduct.getProductName());
            configProduct.setSizeLong(saleOrderProduct.getSizeLong());
            configProduct.setSizeWidth(saleOrderProduct.getSizeWidth());
            configProduct.setSizeHeight(saleOrderProduct.getSizeHeight());
            configProductService.insertConfigProduct(configProduct);
            saleOrderProduct.setProductId(configProduct.getId());
        }
        //客户
        if(null==saleOrder.getCustomerId()){
            //创建一个新客户
            ConfigCustomer configCustomer = new ConfigCustomer();
            configCustomer.setCustomerName(saleOrderProduct.getCustomerName());
            configCustomer.setStatus("vaild");
            configCustomerService.insertConfigCustomer(configCustomer);
            saleOrder.setCustomerId(configCustomer.getId());
        }
        //创建头
        saleOrder.setStatus("draft");
        this.insertSaleOrder(saleOrder);
        //创建行
        saleOrderProduct.setSaleOrderId(saleOrder.getId());
        saleOrderProduct.setCustomerId(saleOrder.getCustomerId());
        saleOrderProduct.setAmount(saleOrderProduct.getPrice().multiply(new BigDecimal(saleOrderProduct.getQty())));
        saleOrderProductService.insertSaleOrderProduct(saleOrderProduct);
    }

    /**
     * 汇总金额
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @Override
    public SaleOrder countAmount(SaleOrder saleOrder){
        SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
        saleOrderProduct.setSaleOrderId(saleOrder.getId());
        BigDecimal amount = new BigDecimal(0);
        List<SaleOrderProduct> list = saleOrderProductService.selectSaleOrderProductList(saleOrderProduct);
        for (int i=0;i<list.size();i++){
            amount = amount.add(list.get(i).getAmount());
        }
        saleOrder.setAmount(amount);
        saleOrderMapper.updateSaleOrder(saleOrder);
        return saleOrder;
    }

    /**
     * 库存变化
     * @Author 方舟
     * @Date 2021/4/27 16:45:19
    **/
    private void warehouseChange(String editType,Long saleOrderId){
        if(!StringUtils.isEmpty(editType)){
            SaleOrder saleOrder = saleOrderMapper.selectSaleOrderById(saleOrderId);
            SaleOrderMaterials saleOrderMaterials = new SaleOrderMaterials();
            saleOrderMaterials.setSaleOrderId(saleOrderId);
            List<SaleOrderMaterials> list = saleOrderMaterialsService.selectSaleOrderMaterialsList(saleOrderMaterials);
            for (int i=0;i<list.size();i++){
                SaleOrderMaterials materials = list.get(i);
                if("approve".equals(editType)){
                    //客户带料入库
                    warehouseRecordService.materialsInbound("KHDL",materials.getWarehouseId(),materials.getQty(),materials.getMaterialsId(),"");
                }else if("unapprove".equals(editType)){
                    //客户带料出库
                    warehouseRecordService.materialsInbound("KHDL",materials.getWarehouseId(),(0-materials.getQty()),materials.getMaterialsId(),"反审退回");
                }
            }
        }
    }

    /**
     * 安排剩余的未排程
     * @Author 方舟
     * @Date 2021/4/28 9:30:05
    **/
    private void setupUnsendProduct(SaleOrder saleOrder){
        String editType = saleOrder.getRzyValue1();
        if(!StringUtils.isEmpty(editType)&&"approve".equals(editType)){
            SaleOrder fullVO = saleOrderMapper.selectSaleOrderById(saleOrder.getId());
            List<SaleOrderProduct> list = saleOrderProductMapper.selectUnsendProduct(saleOrder.getId());
            for (int i=0;i<list.size();i++){
                SaleOrderDelivery saleOrderDelivery = new SaleOrderDelivery();
                SaleOrderProduct saleOrderProduct = saleOrderProductMapper.selectSaleOrderProductById(list.get(i).getId());
                BeanUtils.copyProperties(fullVO, saleOrderDelivery);
                BeanUtils.copyProperties(saleOrderProduct, saleOrderDelivery);
                saleOrderDelivery.setSaleOrderProductId(list.get(i).getId());
                saleOrderDelivery.setQty(list.get(i).getQty());
                saleOrderDeliveryService.insertSaleOrderDelivery(saleOrderDelivery);
            }
        }
    }

    /**
     * 复制
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    @Override
    public SaleOrder copySaleOrder(SaleOrder saleOrder){
        //复制头
        Long orgSaleOrderId = saleOrder.getId();
        SaleOrder headerVO = saleOrderMapper.selectSaleOrderById(orgSaleOrderId);
        headerVO.setStatus("draft");
        headerVO.setDeliveryDate(DateUtils.getNowDate());
        insertSaleOrder(headerVO);
        Long newSaleOrderId = headerVO.getId();
        //复制产品
        SaleOrderProduct productParams = new SaleOrderProduct();
        productParams.setSaleOrderId(orgSaleOrderId);
        List<SaleOrderProduct> productList = saleOrderProductMapper.selectSaleOrderProductList(productParams);
        for (int i=0;i<productList.size();i++){
            SaleOrderProduct saveProductVO = productList.get(i);
            Long orgSaleOrderProductId = productList.get(i).getId();
            saveProductVO.setSaleOrderId(newSaleOrderId);
            saveProductVO.setDeliveryDate(DateUtils.getNowDate());
            saleOrderProductMapper.insertSaleOrderProduct(saveProductVO);
            Long newSaleOrderProductId = saveProductVO.getId();
            //复制排程
            SaleOrderDelivery deliveryParams = new SaleOrderDelivery();
            deliveryParams.setSaleOrderProductId(orgSaleOrderProductId);
            List<SaleOrderDelivery> deliveryList = saleOrderDeliveryMapper.selectSaleOrderDeliveryList(deliveryParams);
            for (int j=0;j<deliveryList.size();j++){
                SaleOrderDelivery saveDeliveryVO = deliveryList.get(j);
                saveDeliveryVO.setSaleOrderId(newSaleOrderId);
                saveDeliveryVO.setSaleOrderProductId(newSaleOrderProductId);
                saveDeliveryVO.setDeliveryDate(DateUtils.getNowDate());
                saleOrderDeliveryMapper.insertSaleOrderDelivery(saveDeliveryVO);
            }
        }
        //复制带料
        SaleOrderMaterials materialsParams = new SaleOrderMaterials();
        materialsParams.setSaleOrderId(orgSaleOrderId);
        List<SaleOrderMaterials> materialsList = saleOrderMaterialsMapper.selectSaleOrderMaterialsList(materialsParams);
        for (int i=0;i<materialsList.size();i++){
            SaleOrderMaterials saveMaterialsVO = materialsList.get(i);
            saveMaterialsVO.setSaleOrderId(newSaleOrderId);
            saveMaterialsVO.setDeliveryDate(DateUtils.getNowDate());
            saleOrderMaterialsMapper.insertSaleOrderMaterials(saveMaterialsVO);
        }
        //20211205 关联翻单施工单
        produceOrderService.copyProduceOrderBySaleOrder(orgSaleOrderId,newSaleOrderId);
        return saleOrder;
    }
}
