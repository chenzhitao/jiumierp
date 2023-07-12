package com.ruoyi.project.config.configSupplier.service.impl;

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
import com.ruoyi.project.config.configSupplier.mapper.ConfigSupplierMapper;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.config.configSupplier.service.IConfigSupplierService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 供应商信息Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigSupplierServiceImpl implements IConfigSupplierService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigSupplierServiceImpl.class);

    @Autowired
    private ConfigSupplierMapper configSupplierMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询供应商信息
     * 
     * @param id 供应商信息ID
     * @return 供应商信息
     */
    @Override
    public ConfigSupplier selectConfigSupplierById(Long id)
    {
        return configSupplierMapper.selectConfigSupplierById(id);
    }

    /**
     * 查询供应商信息列表
     * 
     * @param configSupplier 供应商信息
     * @return 供应商信息
     */
    @Override
    public List<ConfigSupplier> selectConfigSupplierList(ConfigSupplier configSupplier)
    {
        return configSupplierMapper.selectConfigSupplierList(configSupplier);
    }

    /**
     * 新增供应商信息
     * 
     * @param configSupplier 供应商信息
     * @return 结果
     */
    @Override
    public int insertConfigSupplier(ConfigSupplier configSupplier)
    {
        configSupplier.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configSupplier.setCreateTime(DateUtils.getNowDate());
        configSupplier.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configSupplier.setUpdateTime(DateUtils.getNowDate());
        return configSupplierMapper.insertConfigSupplier(configSupplier);
    }

    /**
     * 修改供应商信息
     * 
     * @param configSupplier 供应商信息
     * @return 结果
     */
    @Override
    public int updateConfigSupplier(ConfigSupplier configSupplier)
    {
        configSupplier.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configSupplier.setUpdateTime(DateUtils.getNowDate());
        return configSupplierMapper.updateConfigSupplier(configSupplier);
    }

    /**
     * 删除供应商信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigSupplierByIds(String ids)
    {
        configSupplierMapper.deleteConfigSupplierContactByIds(Convert.toStrArray(ids));
        return configSupplierMapper.deleteConfigSupplierByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除供应商信息信息
     * 
     * @param id 供应商信息ID
     * @return 结果
     */
    @Override
    public int deleteConfigSupplierById(Long id)
    {
        configSupplierMapper.deleteConfigSupplierContactById(id);
        return configSupplierMapper.deleteConfigSupplierById(id);
    }

    /**
     * 导入数据
     *
     * @param configSupplierList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigSupplier(List<ConfigSupplier> configSupplierList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configSupplierList) || configSupplierList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigSupplier configSupplier : configSupplierList){
            boolean checkFlag = false;
            //空字符串处理
            configSupplier = (ConfigSupplier) EntityUtils.nullStringToNull(configSupplier);
            try{
                //姓名必填
                if(StringUtils.isEmpty(configSupplier.getSupplierName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 供应商名称必填");
                }
                //字典数据默认值,客户行业
                /*if(StringUtils.isEmpty(configSupplier.getSupplierIndustry())){
                    configSupplier.setSupplierIndustry(rzyCommonMapper.findDefaultDictValue("customer_industry"));
                }*/
                //税率转换
                if(!StringUtils.isEmpty(configSupplier.getTaxName())){
                    configSupplier.setTaxRate(rzyCommonMapper.findRateByName(configSupplier.getTaxName(),"config_tax","tax_name","tax_rate"));
                }
                //采购员转换
                if(!StringUtils.isEmpty(configSupplier.getBuyerName())){
                    Long employeeId = rzyCommonMapper.findIdByName(configSupplier.getBuyerName(),"config_employee","employee_name","id");
                    configSupplier.setBuyerId(employeeId);
                }
                //成功
                if(!checkFlag){
                    this.insertConfigSupplier(configSupplier);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configSupplier.getSupplierName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、供应商 " + configSupplier.getSupplierName() + " 导入失败：";
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
