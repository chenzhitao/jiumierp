package com.ruoyi.project.config.configCutterDie.service.impl;

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
import com.ruoyi.project.config.configCutterDie.mapper.ConfigCutterDieMapper;
import com.ruoyi.project.config.configCutterDie.domain.ConfigCutterDie;
import com.ruoyi.project.config.configCutterDie.service.IConfigCutterDieService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 刀模配置Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigCutterDieServiceImpl implements IConfigCutterDieService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigCutterDieServiceImpl.class);

    @Autowired
    private ConfigCutterDieMapper configCutterDieMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询刀模配置
     * 
     * @param id 刀模配置ID
     * @return 刀模配置
     */
    @Override
    public ConfigCutterDie selectConfigCutterDieById(Long id)
    {
        return configCutterDieMapper.selectConfigCutterDieById(id);
    }

    /**
     * 查询刀模配置列表
     * 
     * @param configCutterDie 刀模配置
     * @return 刀模配置
     */
    @Override
    public List<ConfigCutterDie> selectConfigCutterDieList(ConfigCutterDie configCutterDie)
    {
        return configCutterDieMapper.selectConfigCutterDieList(configCutterDie);
    }

    /**
     * 新增刀模配置
     * 
     * @param configCutterDie 刀模配置
     * @return 结果
     */
    @Override
    public int insertConfigCutterDie(ConfigCutterDie configCutterDie)
    {
        configCutterDie.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configCutterDie.setCreateTime(DateUtils.getNowDate());
        configCutterDie.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configCutterDie.setUpdateTime(DateUtils.getNowDate());
        return configCutterDieMapper.insertConfigCutterDie(configCutterDie);
    }

    /**
     * 修改刀模配置
     * 
     * @param configCutterDie 刀模配置
     * @return 结果
     */
    @Override
    public int updateConfigCutterDie(ConfigCutterDie configCutterDie)
    {
        configCutterDie.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configCutterDie.setUpdateTime(DateUtils.getNowDate());
        return configCutterDieMapper.updateConfigCutterDie(configCutterDie);
    }

    /**
     * 删除刀模配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigCutterDieByIds(String ids)
    {
        return configCutterDieMapper.deleteConfigCutterDieByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除刀模配置信息
     * 
     * @param id 刀模配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigCutterDieById(Long id)
    {
        return configCutterDieMapper.deleteConfigCutterDieById(id);
    }

    /**
     * 导入数据
     *
     * @param configCutterDieList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigCutterDie(List<ConfigCutterDie> configCutterDieList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configCutterDieList) || configCutterDieList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigCutterDie configCutterDie : configCutterDieList){
            boolean checkFlag = false;
            //空字符串处理
            configCutterDie = (ConfigCutterDie) EntityUtils.nullStringToNull(configCutterDie);
            try{
                //名称必填
                if(StringUtils.isEmpty(configCutterDie.getCutterDieName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 刀模名称必填");
                }
                //id转换
                if(!StringUtils.isEmpty(configCutterDie.getCustomerName())){
                    configCutterDie.setCustomerId(rzyCommonMapper.findIdByName(configCutterDie.getCustomerName(),"config_customer","customer_name","id"));
                }
                if(!StringUtils.isEmpty(configCutterDie.getWarehouseName())){
                    configCutterDie.setWarehouseId(rzyCommonMapper.findIdByName(configCutterDie.getWarehouseName(),"config_warehouse","warehouse_name","id"));
                }
                if(!StringUtils.isEmpty(configCutterDie.getProductName())){
                    configCutterDie.setProductId(rzyCommonMapper.findIdByName(configCutterDie.getProductName(),"config_product","product_name","id"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigCutterDie(configCutterDie);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configCutterDie.getCutterDieName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、刀模 " + configCutterDie.getCutterDieName() + " 导入失败：";
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
