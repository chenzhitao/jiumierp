package com.ruoyi.project.sale.saleQuotationProduct.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configCustomer.mapper.ConfigCustomerMapper;
import com.ruoyi.project.config.configCustomer.service.impl.ConfigCustomerServiceImpl;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.config.configProduct.mapper.ConfigProductMapper;
import com.ruoyi.project.config.configProduct.service.IConfigProductService;
import com.ruoyi.project.config.configQuotationTemplateMaterials.domain.ConfigQuotationTemplateMaterials;
import com.ruoyi.project.config.configQuotationTemplateMaterials.mapper.ConfigQuotationTemplateMaterialsMapper;
import com.ruoyi.project.config.configQuotationTemplateProcess.domain.ConfigQuotationTemplateProcess;
import com.ruoyi.project.config.configQuotationTemplateProcess.mapper.ConfigQuotationTemplateProcessMapper;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import com.ruoyi.project.sale.saleQuotation.domain.SaleQuotation;
import com.ruoyi.project.sale.saleQuotation.mapper.SaleQuotationMapper;
import com.ruoyi.project.sale.saleQuotationMaterials.domain.SaleQuotationMaterials;
import com.ruoyi.project.sale.saleQuotationMaterials.mapper.SaleQuotationMaterialsMapper;
import com.ruoyi.project.sale.saleQuotationProcess.domain.SaleQuotationProcess;
import com.ruoyi.project.sale.saleQuotationProcess.mapper.SaleQuotationProcessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.sale.saleQuotationProduct.mapper.SaleQuotationProductMapper;
import com.ruoyi.project.sale.saleQuotationProduct.domain.SaleQuotationProduct;
import com.ruoyi.project.sale.saleQuotationProduct.service.ISaleQuotationProductService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 报价产品Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
@Service
public class SaleQuotationProductServiceImpl implements ISaleQuotationProductService 
{
    private static final Logger log = LoggerFactory.getLogger(SaleQuotationProductServiceImpl.class);

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    @Autowired
    private SaleQuotationProductMapper saleQuotationProductMapper;

    @Autowired
    private SaleQuotationProcessMapper saleQuotationProcessMapper;

    @Autowired
    private SaleQuotationMaterialsMapper saleQuotationMaterialsMapper;

    @Autowired
    private ConfigProductMapper configProductMapper;

    @Autowired
    private SaleQuotationMapper saleQuotationMapper;

    @Autowired
    private ConfigQuotationTemplateProcessMapper configQuotationTemplateProcessMapper;

    @Autowired
    private ConfigQuotationTemplateMaterialsMapper configQuotationTemplateMaterialsMapper;

    @Autowired
    private IConfigProductService configProductService;

    /**
     * 查询报价产品
     * 
     * @param id 报价产品ID
     * @return 报价产品
     */
    @Override
    public SaleQuotationProduct selectSaleQuotationProductById(Long id)
    {
        return saleQuotationProductMapper.selectSaleQuotationProductById(id);
    }

    /**
     * 查询报价产品列表
     * 
     * @param saleQuotationProduct 报价产品
     * @return 报价产品
     */
    @Override
    public List<SaleQuotationProduct> selectSaleQuotationProductList(SaleQuotationProduct saleQuotationProduct)
    {
        return saleQuotationProductMapper.selectSaleQuotationProductList(saleQuotationProduct);
    }

    /**
     * 查询选择
     * @Author 方舟
     * @Date 2021/4/27 9:29:03
    **/
    @Override
    public List<SaleQuotationProduct> selectMultProductList(SaleQuotationProduct saleQuotationProduct)
    {
        saleQuotationProduct.setRzyUserId(ShiroUtils.getUserId());
        return saleQuotationProductMapper.selectMultProductList(saleQuotationProduct);
    }

    /**
     * 新增报价产品
     * 
     * @param saleQuotationProduct 报价产品
     * @return 结果
     */
    @Override
    public int insertSaleQuotationProduct(SaleQuotationProduct saleQuotationProduct)
    {
        return saleQuotationProductMapper.insertSaleQuotationProduct(saleQuotationProduct);
    }

    /**
     * 修改报价产品
     * 
     * @param saleQuotationProduct 报价产品
     * @return 结果
     */
    @Override
    public int updateSaleQuotationProduct(SaleQuotationProduct saleQuotationProduct)
    {
        return saleQuotationProductMapper.updateSaleQuotationProduct(saleQuotationProduct);
    }

    /**
     * 删除报价产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationProductByIds(String ids)
    {
        //删除关联工序和材料
        saleQuotationProcessMapper.deleteSaleQuotationProcessByProductIds(Convert.toStrArray(ids));
        saleQuotationMaterialsMapper.deleteSaleQuotationMaterialsByProductIds(Convert.toStrArray(ids));
        return saleQuotationProductMapper.deleteSaleQuotationProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报价产品信息
     * 
     * @param id 报价产品ID
     * @return 结果
     */
    @Override
    public int deleteSaleQuotationProductById(Long id)
    {
        //删除关联工序和材料
        saleQuotationProcessMapper.deleteSaleQuotationProcessByProductId(id);
        saleQuotationMaterialsMapper.deleteSaleQuotationMaterialsByProductId(id);
        return saleQuotationProductMapper.deleteSaleQuotationProductById(id);
    }

    /**
     * 添加已有产品
     * @Author 方舟
     * @Date 2021/4/22 13:20:47
     **/
    @Override
    public SaleQuotationProduct createSaleQuotationProduct(SaleQuotationProduct saleQuotationProduct){
        //查询多个产品
        ConfigProduct configProduct = new ConfigProduct();
        configProduct.setIds(saleQuotationProduct.getIds());
        List<ConfigProduct> configProductList = new ArrayList<ConfigProduct>();
        if(!StringUtils.isEmpty(saleQuotationProduct.getIds())){
            configProductList = configProductMapper.selectConfigProductList(configProduct);
        }
        //报价单头表
        SaleQuotation saleQuotation = saleQuotationMapper.selectSaleQuotationById(saleQuotationProduct.getSaleQuotationId());
        for (int i=0;i<configProductList.size();i++){
            //添加报价产品
            saveSaleQuotationProductByProduct(configProductList.get(i),saleQuotation);
        }
        return saleQuotationProduct;
    }

    /**
     * 已有Product创建报价产品
     * @Author 方舟
     * @Date 2021/4/23 10:26:50
    **/
    private void saveSaleQuotationProductByProduct(ConfigProduct configProduct,SaleQuotation saleQuotation){
        //添加报价产品
        SaleQuotationProduct saveSaleQuotationProduct = new SaleQuotationProduct();
        saveSaleQuotationProduct.setCustomerId(saleQuotation.getCustomerId());
        saveSaleQuotationProduct.setSaleQuotationId(saleQuotation.getId());
        saveSaleQuotationProduct.setQuotationTemplateId(configProduct.getQuotationTemplateId());
        saveSaleQuotationProduct.setProductionTemplateId(configProduct.getProductionTemplateId());
        saveSaleQuotationProduct.setProductId(configProduct.getId());
        insertSaleQuotationProduct(saveSaleQuotationProduct);
        Long saleQuotationProductId = saveSaleQuotationProduct.getId();
        //看看有没有工艺卡
        Long quotationTemplateId = configProduct.getQuotationTemplateId();
        if(null!=quotationTemplateId){
            //如果发现了工艺卡,就把工艺卡工序复制过来
            insertProcessAndMaterialsByQuotationTemplateId(quotationTemplateId,saleQuotationProductId,saleQuotation);
        }
        //end
    }

    /**
     * 根据工艺卡创建工序和材料
     * @Author 方舟
     * @Date 2021/4/23 11:55:09
    **/
    private void insertProcessAndMaterialsByQuotationTemplateId(Long quotationTemplateId,Long saleQuotationProductId,SaleQuotation saleQuotation){
        SaleQuotationProduct saleQuotationProduct = saleQuotationProductMapper.selectSaleQuotationProductById(saleQuotationProductId);
        ConfigQuotationTemplateProcess configQuotationTemplateProcess = new ConfigQuotationTemplateProcess();
        configQuotationTemplateProcess.setQuotationTemplateId(quotationTemplateId);
        List<ConfigQuotationTemplateProcess> cqtProcessList = configQuotationTemplateProcessMapper.selectConfigQuotationTemplateProcessList(configQuotationTemplateProcess);
        for (int j=0;j<cqtProcessList.size();j++){
            SaleQuotationProcess saleQuotationProcess = new SaleQuotationProcess();
            BeanUtils.copyProperties(cqtProcessList.get(j), saleQuotationProcess);
            saleQuotationProcess.setSaleQuotationId(saleQuotation.getId());
            saleQuotationProcess.setSaleQuotationProductId(saleQuotationProductId);
            saleQuotationProcess.setQuotationTemplateProcessId(cqtProcessList.get(j).getId());
            saleQuotationProcess.setCustomerId(saleQuotation.getCustomerId());
            saleQuotationProcess.setProductId(saleQuotationProduct.getProductId());
            saleQuotationProcessMapper.insertSaleQuotationProcess(saleQuotationProcess);
        }
        //如果发现了工艺卡,就把工艺卡材料复制过来
        ConfigQuotationTemplateMaterials configQuotationTemplateMaterials = new ConfigQuotationTemplateMaterials();
        configQuotationTemplateMaterials.setQuotationTemplateId(quotationTemplateId);
        List<ConfigQuotationTemplateMaterials> cqtMaterialsList = configQuotationTemplateMaterialsMapper.selectConfigQuotationTemplateMaterialsList(configQuotationTemplateMaterials);
        for (int j=0;j<cqtMaterialsList.size();j++){
            SaleQuotationMaterials saleQuotationMaterials = new SaleQuotationMaterials();
            BeanUtils.copyProperties(cqtMaterialsList.get(j), saleQuotationMaterials);
            saleQuotationMaterials.setSaleQuotationId(saleQuotation.getId());
            saleQuotationMaterials.setSaleQuotationProductId(saleQuotationProductId);
            saleQuotationMaterials.setQuotationTemplateMaterialsId(cqtMaterialsList.get(j).getId());
            saleQuotationMaterials.setCustomerId(saleQuotation.getCustomerId());
            saleQuotationMaterials.setProductId(saleQuotationProduct.getProductId());
            saleQuotationMaterialsMapper.insertSaleQuotationMaterials(saleQuotationMaterials);
        }
    }

    /**
     * 新建产品
     * @Author 方舟
     * @Date 2021/4/23 10:15:27
     **/
    @Override
    public SaleQuotationProduct createProduct(SaleQuotationProduct saleQuotationProduct){
        //准备产品数据保存
        ConfigProduct configProduct = new ConfigProduct();
        BeanUtils.copyProperties(saleQuotationProduct, configProduct);
        configProduct.setIsPublic("N");
        configProduct.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configProduct.setCreateTime(DateUtils.getNowDate());
        configProduct.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configProduct.setUpdateTime(DateUtils.getNowDate());
        configProduct.setProductType(saleQuotationProduct.getRzyValue2());
        configProductMapper.insertConfigProduct(configProduct);
        //保存完了,获取ID
        Long productId = configProduct.getId();
        configProduct = configProductMapper.selectConfigProductById(productId);
        //剩下的按选产品的进行
        SaleQuotation saleQuotation = saleQuotationMapper.selectSaleQuotationById(saleQuotationProduct.getSaleQuotationId());
        saveSaleQuotationProductByProduct(configProduct,saleQuotation);
        return saleQuotationProduct;
    }

    /**
     * 更换工艺卡
     * @Author 方舟
     * @Date 2021/4/23 10:14:36
     **/
    @Override
    public SaleQuotationProduct selectSaleQuotationExec(SaleQuotationProduct saleQuotationProduct){
        Long quotationTemplateId = Long.parseLong(saleQuotationProduct.getIds());
        Long saleQuotationProductId = saleQuotationProduct.getId();
        SaleQuotation saleQuotation = saleQuotationMapper.selectSaleQuotationById(saleQuotationProduct.getSaleQuotationId());
        SaleQuotationProduct orgVO = saleQuotationProductMapper.selectSaleQuotationProductById(saleQuotationProductId);
        if(null!=orgVO.getQuotationTemplateId()&&orgVO.getQuotationTemplateId().equals(quotationTemplateId)){
            saleQuotationProduct.setRzyValue1("与原工艺卡相同，未发生改变。");
        }else{
            saleQuotationProduct.setRzyValue1("更换工艺卡成功。");
            //添加新的
            insertProcessAndMaterialsByQuotationTemplateId(quotationTemplateId,saleQuotationProductId,saleQuotation);
            //原来的先删除
            saleQuotationProcessMapper.deleteSaleQuotationProcessByTempId(orgVO.getQuotationTemplateId());
            saleQuotationMaterialsMapper.deleteSaleQuotationMaterialsByTempId(orgVO.getQuotationTemplateId());
            //更新产品上的工艺卡
            SaleQuotationProduct updateVO = new SaleQuotationProduct();
            updateVO.setId(saleQuotationProductId);
            updateVO.setQuotationTemplateId(quotationTemplateId);
            saleQuotationProductMapper.updateSaleQuotationProduct(updateVO);
        }
        return saleQuotationProduct;
    }

    /**
     * 导入数据
     *
     * @param saleQuotationProductList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importSaleQuotationProduct(List<SaleQuotationProduct> saleQuotationProductList, Boolean isUpdateSupport){
        if (StringUtils.isNull(saleQuotationProductList) || saleQuotationProductList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SaleQuotationProduct saleQuotationProduct : saleQuotationProductList){
            boolean checkFlag = false;
            //空字符串处理
            saleQuotationProduct = (SaleQuotationProduct) EntityUtils.nullStringToNull(saleQuotationProduct);
            try{
                //姓名必填
                if(StringUtils.isEmpty(saleQuotationProduct.getProductName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 产品名称必填");
                }else{
                    saleQuotationProduct.setProductId(rzyCommonMapper.findIdByName(saleQuotationProduct.getProductName(),"config_product","product_name","id"));
                }
                if(null==saleQuotationProduct.getProductId()){
                    if(null==saleQuotationProduct.getSizeLong()||null==saleQuotationProduct.getSizeWidth()||null==saleQuotationProduct.getSizeHeight()){
                        checkFlag = true;
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、 新建产品，规格必填");
                    }
                }
                if(StringUtils.isEmpty(saleQuotationProduct.getQuotationTemplateName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 报价工艺卡必填");
                }else{
                    saleQuotationProduct.setQuotationTemplateId(rzyCommonMapper.findIdByName(saleQuotationProduct.getQuotationTemplateName(),"config_quotation_template","template_name","id"));
                }
                if(!StringUtils.isEmpty(saleQuotationProduct.getProductionTemplateName())){
                    saleQuotationProduct.setProductionTemplateId(rzyCommonMapper.findIdByName(saleQuotationProduct.getProductionTemplateName(),"config_production_template","template_name","id"));
                }
                //成功
                if(!checkFlag){
                    importSaleQuotationProduct(saleQuotationProduct);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + saleQuotationProduct.getProductName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、产品 " + saleQuotationProduct.getProductName() + " 导入失败：";
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

    private void importSaleQuotationProduct(SaleQuotationProduct saleQuotationProduct){
        //先创建productId
        //产品
        if(null==saleQuotationProduct.getProductId()){
            //创建一个新产品
            ConfigProduct configProduct = new ConfigProduct();
            configProduct.setProductName(saleQuotationProduct.getProductName());
            configProduct.setSizeLong(saleQuotationProduct.getSizeLong());
            configProduct.setSizeWidth(saleQuotationProduct.getSizeWidth());
            configProduct.setSizeHeight(saleQuotationProduct.getSizeHeight());
            configProduct.setQuotationTemplateId(saleQuotationProduct.getQuotationTemplateId());
            configProduct.setProductionTemplateId(saleQuotationProduct.getProductionTemplateId());
            configProductService.insertConfigProduct(configProduct);
            saleQuotationProduct.setProductId(configProduct.getId());
            saleQuotationProduct.setIds(configProduct.getId().toString());
        }else{
            saleQuotationProduct.setIds(saleQuotationProduct.getProductId().toString());
        }
        createSaleQuotationProduct(saleQuotationProduct);
    }

}
