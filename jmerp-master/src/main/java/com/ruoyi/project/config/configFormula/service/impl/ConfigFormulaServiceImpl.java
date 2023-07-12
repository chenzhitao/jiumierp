package com.ruoyi.project.config.configFormula.service.impl;

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
import com.ruoyi.project.config.configFormula.mapper.ConfigFormulaMapper;
import com.ruoyi.project.config.configFormula.domain.ConfigFormula;
import com.ruoyi.project.config.configFormula.service.IConfigFormulaService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 公式配置Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigFormulaServiceImpl implements IConfigFormulaService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigFormulaServiceImpl.class);

    @Autowired
    private ConfigFormulaMapper configFormulaMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询公式配置
     * 
     * @param id 公式配置ID
     * @return 公式配置
     */
    @Override
    public ConfigFormula selectConfigFormulaById(Long id)
    {
        return configFormulaMapper.selectConfigFormulaById(id);
    }

    /**
     * 查询公式配置列表
     * 
     * @param configFormula 公式配置
     * @return 公式配置
     */
    @Override
    public List<ConfigFormula> selectConfigFormulaList(ConfigFormula configFormula)
    {
        return configFormulaMapper.selectConfigFormulaList(configFormula);
    }

    /**
     * 新增公式配置
     * 
     * @param configFormula 公式配置
     * @return 结果
     */
    @Override
    public int insertConfigFormula(ConfigFormula configFormula)
    {
        configFormula.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configFormula.setCreateTime(DateUtils.getNowDate());
        configFormula.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configFormula.setUpdateTime(DateUtils.getNowDate());
        return configFormulaMapper.insertConfigFormula(configFormula);
    }

    /**
     * 修改公式配置
     * 
     * @param configFormula 公式配置
     * @return 结果
     */
    @Override
    public int updateConfigFormula(ConfigFormula configFormula)
    {
        configFormula.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configFormula.setUpdateTime(DateUtils.getNowDate());
        return configFormulaMapper.updateConfigFormula(configFormula);
    }

    /**
     * 删除公式配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigFormulaByIds(String ids)
    {
        return configFormulaMapper.deleteConfigFormulaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除公式配置信息
     * 
     * @param id 公式配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigFormulaById(Long id)
    {
        return configFormulaMapper.deleteConfigFormulaById(id);
    }

    /**
     * 导入数据
     *
     * @param configFormulaList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigFormula(List<ConfigFormula> configFormulaList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configFormulaList) || configFormulaList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigFormula configFormula : configFormulaList){
            boolean checkFlag = false;
            //空字符串处理
            configFormula = (ConfigFormula) EntityUtils.nullStringToNull(configFormula);
            try{
                //名称必填
                if(StringUtils.isEmpty(configFormula.getFormulaName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 公式名称必填");
                }
                //公式功能
                if(StringUtils.isEmpty(configFormula.getFormulaType())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 公式功能必填");
                }

                //成功
                if(!checkFlag){
                    this.insertConfigFormula(configFormula);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configFormula.getFormulaName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、公式 " + configFormula.getFormulaName() + " 导入失败：";
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
