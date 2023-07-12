package com.ruoyi.project.config.configCustomer.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.project.config.configEmployee.domain.ConfigEmployee;
import com.ruoyi.project.config.configEmployee.service.impl.ConfigEmployeeServiceImpl;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configCustomer.mapper.ConfigCustomerMapper;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configCustomer.service.IConfigCustomerService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 客户信息Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigCustomerServiceImpl implements IConfigCustomerService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigCustomerServiceImpl.class);

    @Autowired
    private ConfigCustomerMapper configCustomerMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询客户信息
     * 
     * @param id 客户信息ID
     * @return 客户信息
     */
    @Override
    public ConfigCustomer selectConfigCustomerById(Long id)
    {
        return configCustomerMapper.selectConfigCustomerById(id);
    }

    /**
     * 查询客户信息列表
     * 
     * @param configCustomer 客户信息
     * @return 客户信息
     */
    @Override
    public List<ConfigCustomer> selectConfigCustomerList(ConfigCustomer configCustomer)
    {
        return configCustomerMapper.selectConfigCustomerList(configCustomer);
    }

    /**
     * 新增客户信息
     * 
     * @param configCustomer 客户信息
     * @return 结果
     */
    @Override
    public int insertConfigCustomer(ConfigCustomer configCustomer)
    {
        configCustomer.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configCustomer.setCreateTime(DateUtils.getNowDate());
        configCustomer.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configCustomer.setUpdateTime(DateUtils.getNowDate());
        return configCustomerMapper.insertConfigCustomer(configCustomer);
    }

    /**
     * 修改客户信息
     * 
     * @param configCustomer 客户信息
     * @return 结果
     */
    @Override
    public int updateConfigCustomer(ConfigCustomer configCustomer)
    {
        configCustomer.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configCustomer.setUpdateTime(DateUtils.getNowDate());
        return configCustomerMapper.updateConfigCustomer(configCustomer);
    }

    /**
     * 删除客户信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigCustomerByIds(String ids)
    {
        return configCustomerMapper.deleteConfigCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息信息
     * 
     * @param id 客户信息ID
     * @return 结果
     */
    @Override
    public int deleteConfigCustomerById(Long id)
    {
        return configCustomerMapper.deleteConfigCustomerById(id);
    }

    /**
     * 导入数据
     *
     * @param configCustomerList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigCustomer(List<ConfigCustomer> configCustomerList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configCustomerList) || configCustomerList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigCustomer configCustomer : configCustomerList){
            boolean checkFlag = false;
            //空字符串处理
            configCustomer = (ConfigCustomer) EntityUtils.nullStringToNull(configCustomer);
            try{
                //姓名必填
                if(StringUtils.isEmpty(configCustomer.getCustomerName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 客户名称必填");
                }
                //字典数据默认值,客户行业
                /*if(StringUtils.isEmpty(configCustomer.getCustomerIndustry())){
                    configCustomer.setCustomerIndustry(rzyCommonMapper.findDefaultDictValue("customer_industry"));
                }*/
                //税率转换
                if(!StringUtils.isEmpty(configCustomer.getTaxName())){
                    configCustomer.setTaxRate(rzyCommonMapper.findRateByName(configCustomer.getTaxName(),"config_tax","tax_name","tax_rate"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigCustomer(configCustomer);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configCustomer.getCustomerName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、客户 " + configCustomer.getCustomerName() + " 导入失败：";
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
