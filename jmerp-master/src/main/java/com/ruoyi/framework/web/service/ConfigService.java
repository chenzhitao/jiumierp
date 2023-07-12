package com.ruoyi.framework.web.service;

import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.config.configCustomer.domain.ConfigCustomer;
import com.ruoyi.project.config.configCustomer.service.IConfigCustomerService;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.config.configSupplier.service.IConfigSupplierService;
import com.ruoyi.project.config.configTax.domain.ConfigTax;
import com.ruoyi.project.config.configTax.service.IConfigTaxService;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import com.ruoyi.project.rzy.rzyCommon.service.IRzyCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.config.service.IConfigService;

import java.util.Date;
import java.util.List;

/**
 * RuoYi首创 html调用 thymeleaf 实现参数管理
 * 
 * @author ruoyi
 */
@Service("config")
public class ConfigService
{
    @Autowired
    private IConfigService configService;
    @Autowired
    private IConfigCustomerService configCustomerService;
    @Autowired
    private IConfigSupplierService configSupplierService;
    @Autowired
    private IRzyCommonService rzyCommonService;

    /**
     * 税率
     * @Author 方舟
     * @Date 2021/4/13 15:31:10
    **/
    @Autowired
    private IConfigTaxService configTaxService;

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String getKey(String configKey)
    {
        return configService.selectConfigByKey(configKey);
    }

    /**
     * 税率选择
     * 2021/04/13
     * @return
     */
    public List<ConfigTax> getConfigTaxList(String type){
        ConfigTax tax = new ConfigTax();
        tax.setTaxName(type);
        return configTaxService.selectConfigTaxList(tax);
    }

    /**
     * 客户选择
     * 2021/04/13
     * @return
     */
    public List<ConfigCustomer> getConfigCustomerList(String type){
        ConfigCustomer customer = new ConfigCustomer();
        customer.setCustomerIndustry(type);
        customer.setStatus("vaild");
        return configCustomerService.selectConfigCustomerList(customer);
    }

    /**
     * 供应商选择
     * 2021/04/13
     * @return
     */
    public List<ConfigSupplier> getConfigSupplierList(String type){
        ConfigSupplier supplier = new ConfigSupplier();
        supplier.setSupplierType(type);
        supplier.setStatus("vaild");
        return configSupplierService.selectConfigSupplierList(supplier);
    }

    /**
     * 时间转换
     * @Author 方舟
     * @Date 2021/4/14 13:41:48
    **/
    public String timeFormat(Date date){
        if(date==null){
            date = new Date();
        }
        return DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,date);
    }

    /**
     * 剩余过期时间
     * @Author 方舟
     * @Date 2021/5/8 9:30:16
    **/
    public Integer getSysExpireDays(){
        String securityCode = rzyCommonService.selectSecurityCode();
        String gapCache = CacheUtils.get("SysExpire").toString();
        return Integer.parseInt(gapCache);
    }
}
