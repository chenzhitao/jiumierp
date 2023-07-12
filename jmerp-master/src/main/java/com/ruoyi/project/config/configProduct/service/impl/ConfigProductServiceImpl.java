package com.ruoyi.project.config.configProduct.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configCustomer.service.impl.ConfigCustomerServiceImpl;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configProduct.mapper.ConfigProductMapper;
import com.ruoyi.project.config.configProduct.domain.ConfigProduct;
import com.ruoyi.project.config.configProduct.service.IConfigProductService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 产品配置Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigProductServiceImpl implements IConfigProductService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigProductServiceImpl.class);
    
    @Autowired
    private ConfigProductMapper configProductMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询产品配置
     * 
     * @param id 产品配置ID
     * @return 产品配置
     */
    @Override
    public ConfigProduct selectConfigProductById(Long id)
    {
        return configProductMapper.selectConfigProductById(id);
    }

    /**
     * 查询产品配置列表
     * 
     * @param configProduct 产品配置
     * @return 产品配置
     */
    @Override
    public List<ConfigProduct> selectConfigProductList(ConfigProduct configProduct)
    {
        return configProductMapper.selectConfigProductList(configProduct);
    }

    /**
     * 新增产品配置
     * 
     * @param configProduct 产品配置
     * @return 结果
     */
    @Override
    public int insertConfigProduct(ConfigProduct configProduct)
    {
        configProduct.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configProduct.setCreateTime(DateUtils.getNowDate());
        configProduct.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configProduct.setUpdateTime(DateUtils.getNowDate());
        return configProductMapper.insertConfigProduct(configProduct);
    }

    /**
     * 修改产品配置
     * 
     * @param configProduct 产品配置
     * @return 结果
     */
    @Override
    public int updateConfigProduct(ConfigProduct configProduct)
    {
        configProduct.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configProduct.setUpdateTime(DateUtils.getNowDate());
        int res = configProductMapper.updateConfigProduct(configProduct);
        if(!StringUtils.isEmpty(configProduct.getProductLevel())&&"F".equals(configProduct.getProductLevel())){
            configProduct.setParentId(configProduct.getId());
            configProduct.setProductParentName(configProduct.getProductName());
            configProductMapper.updateConfigProductPart(configProduct);
        }
        return res;
    }

    /**
     * 删除产品配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigProductByIds(String ids)
    {
        return configProductMapper.deleteConfigProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品配置信息
     * 
     * @param id 产品配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigProductById(Long id)
    {
        return configProductMapper.deleteConfigProductById(id);
    }

    /**
     * 导入数据
     *
     * @param configProductList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigProduct(List<ConfigProduct> configProductList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configProductList) || configProductList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigProduct configProduct : configProductList){
            boolean checkFlag = false;
            //空字符串处理
            configProduct = (ConfigProduct) EntityUtils.nullStringToNull(configProduct);
            try{
                //姓名必填
                if(StringUtils.isEmpty(configProduct.getProductName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 产品名称必填");
                }
                //长宽高必填,产品规格(长)	产品规格(宽)	产品规格(高)
                if(null==configProduct.getSizeLong()){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 产品规格(长)必填");
                }
                if(null==configProduct.getSizeWidth()){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 产品规格(宽)必填");
                }
                if(null==configProduct.getSizeHeight()){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 产品规格(高)必填");
                }
                //转换id
                if(!StringUtils.isEmpty(configProduct.getQuotationTemplateName())){
                    configProduct.setQuotationTemplateId(rzyCommonMapper.findIdByName(configProduct.getQuotationTemplateName(),"config_quotation_template","template_name","id"));
                }
                if(!StringUtils.isEmpty(configProduct.getProductionTemplateName())){
                    configProduct.setProductionTemplateId(rzyCommonMapper.findIdByName(configProduct.getProductionTemplateName(),"config_production_template","template_name","id"));
                }
                if(!StringUtils.isEmpty(configProduct.getCustomerName())){
                    configProduct.setCustomerId(rzyCommonMapper.findIdByName(configProduct.getCustomerName(),"config_customer","customer_name","id"));
                }
                if(!StringUtils.isEmpty(configProduct.getFormulaName())){
                    configProduct.setFormulaId(rzyCommonMapper.findIdByName(configProduct.getFormulaName(),"config_formula","formula_name","id"));
                }
                if(!StringUtils.isEmpty(configProduct.getMaterialsName())){
                    configProduct.setMaterialsId(rzyCommonMapper.findIdByName(configProduct.getMaterialsName(),"config_materials","materials_name","id"));
                }
                if(!StringUtils.isEmpty(configProduct.getCartonShapeName())){
                    configProduct.setCartonShapeId(rzyCommonMapper.findIdByName(configProduct.getCartonShapeName(),"config_carton_shape","carton_shape_name","id"));
                }
                if(!StringUtils.isEmpty(configProduct.getCorrugatedName())){
                    configProduct.setCorrugatedId(rzyCommonMapper.findIdByName(configProduct.getCorrugatedName(),"config_corrugated","corrugated_name","id"));
                }
                if(!StringUtils.isEmpty(configProduct.getPaperFormulaName())){
                    configProduct.setPaperFormulaId(rzyCommonMapper.findIdByName(configProduct.getPaperFormulaName(),"config_paper_formula","paper_formula_name","id"));
                }
                if(!StringUtils.isEmpty(configProduct.getDieWarehouseName())){
                    configProduct.setDieWarehouseId(rzyCommonMapper.findIdByName(configProduct.getDieWarehouseName(),"config_warehouse","warehouse_name","id"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigProduct(configProduct);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configProduct.getProductName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、产品 " + configProduct.getProductName() + " 导入失败：";
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
