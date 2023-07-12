package com.ruoyi.project.config.configMaterials.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configCustomer.mapper.ConfigCustomerMapper;
import com.ruoyi.project.config.configCustomer.service.impl.ConfigCustomerServiceImpl;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.config.configSupplier.mapper.ConfigSupplierMapper;
import com.ruoyi.project.config.configSupplierContact.domain.ConfigSupplierContact;
import com.ruoyi.project.config.configSupplierContact.mapper.ConfigSupplierContactMapper;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configMaterials.mapper.ConfigMaterialsMapper;
import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configMaterials.service.IConfigMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 材料配置Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigMaterialsServiceImpl implements IConfigMaterialsService 
{
    @Autowired
    private ConfigMaterialsMapper configMaterialsMapper;

    private static final Logger log = LoggerFactory.getLogger(ConfigMaterialsServiceImpl.class);

    @Autowired
    private RzyCommonMapper rzyCommonMapper;
    @Autowired
    private ConfigSupplierContactMapper configSupplierContactMapper;
    @Autowired
    private ConfigSupplierMapper configSupplierMapper;

    /**
     * 查询材料配置
     * 
     * @param id 材料配置ID
     * @return 材料配置
     */
    @Override
    public ConfigMaterials selectConfigMaterialsById(Long id)
    {
        return configMaterialsMapper.selectConfigMaterialsById(id);
    }

    /**
     * 查询材料配置列表
     * 
     * @param configMaterials 材料配置
     * @return 材料配置
     */
    @Override
    public List<ConfigMaterials> selectConfigMaterialsList(ConfigMaterials configMaterials)
    {
        return configMaterialsMapper.selectConfigMaterialsList(configMaterials);
    }

    /**
     * 新增材料配置
     * 
     * @param configMaterials 材料配置
     * @return 结果
     */
    @Override
    public int insertConfigMaterials(ConfigMaterials configMaterials)
    {
        configMaterials.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configMaterials.setCreateTime(DateUtils.getNowDate());
        configMaterials.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configMaterials.setUpdateTime(DateUtils.getNowDate());
        return configMaterialsMapper.insertConfigMaterials(configMaterials);
    }

    /**
     * 修改材料配置
     * 
     * @param configMaterials 材料配置
     * @return 结果
     */
    @Override
    public int updateConfigMaterials(ConfigMaterials configMaterials)
    {
        configMaterials.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configMaterials.setUpdateTime(DateUtils.getNowDate());
        return configMaterialsMapper.updateConfigMaterials(configMaterials);
    }

    /**
     * 删除材料配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigMaterialsByIds(String ids)
    {
        return configMaterialsMapper.deleteConfigMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除材料配置信息
     * 
     * @param id 材料配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigMaterialsById(Long id)
    {
        return configMaterialsMapper.deleteConfigMaterialsById(id);
    }

    /**
     * 导入数据
     *
     * @param configMaterialsList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigMaterials(List<ConfigMaterials> configMaterialsList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configMaterialsList) || configMaterialsList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigMaterials configMaterials : configMaterialsList){
            boolean checkFlag = false;
            //空字符串处理
            configMaterials = (ConfigMaterials) EntityUtils.nullStringToNull(configMaterials);
            try{
                //姓名必填
                if(StringUtils.isEmpty(configMaterials.getMaterialsName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 材料名称必填");
                }
                //id转换
                if(!StringUtils.isEmpty(configMaterials.getSupplierName())){
                    configMaterials.setSupplierId(rzyCommonMapper.findIdByName(configMaterials.getSupplierName(),"config_supplier","supplier_name","id"));
                }
                if(!StringUtils.isEmpty(configMaterials.getPaperFormulaName())){
                    configMaterials.setPaperFormulaId(rzyCommonMapper.findIdByName(configMaterials.getPaperFormulaName(),"config_paper_formula","paper_formula_name","id"));
                }
                if(!StringUtils.isEmpty(configMaterials.getCorrugatedName())){
                    configMaterials.setCorrugatedId(rzyCommonMapper.findIdByName(configMaterials.getCorrugatedName(),"config_corrugated","corrugated_name","id"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigMaterials(configMaterials);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configMaterials.getMaterialsName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、材料 " + configMaterials.getMaterialsName() + " 导入失败：";
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
     * 根据ID查找完整信息
     * @Author 方舟
     * @Date 2021/5/10 12:03:50
     **/
    @Override
    public ConfigMaterials getMaterialsBaseInfo(ConfigMaterials configMaterials){
        ConfigMaterials resultVO = configMaterialsMapper.selectConfigMaterialsById(configMaterials.getId());
        if(null!=resultVO&&null!=resultVO.getSupplierId()){
            ConfigSupplier supplier = configSupplierMapper.selectConfigSupplierById(resultVO.getSupplierId());
            resultVO.setSupplierName(supplier.getSupplierName());
            if(null!=supplier){
                ConfigSupplierContact contactParams = new ConfigSupplierContact();
                contactParams.setSupplierId(resultVO.getSupplierId());
                contactParams.setIsDefault("Y");
                List<ConfigSupplierContact> list = configSupplierContactMapper.selectConfigSupplierContactList(contactParams);
                if(list.size()==1){
                    ConfigSupplierContact contact = list.get(0);
                    resultVO.setContactName(contact.getContactName());
                    resultVO.setCellPhone(contact.getCellPhone());
                }
            }
        }
        return resultVO;
    }
}
