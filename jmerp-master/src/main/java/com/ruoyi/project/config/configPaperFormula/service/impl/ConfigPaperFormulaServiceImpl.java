package com.ruoyi.project.config.configPaperFormula.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configMaterials.mapper.ConfigMaterialsMapper;
import com.ruoyi.project.config.configMaterials.service.impl.ConfigMaterialsServiceImpl;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configPaperFormula.mapper.ConfigPaperFormulaMapper;
import com.ruoyi.project.config.configPaperFormula.domain.ConfigPaperFormula;
import com.ruoyi.project.config.configPaperFormula.service.IConfigPaperFormulaService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 纸张配方Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigPaperFormulaServiceImpl implements IConfigPaperFormulaService 
{
    @Autowired
    private ConfigPaperFormulaMapper configPaperFormulaMapper;

    private static final Logger log = LoggerFactory.getLogger(ConfigPaperFormulaServiceImpl.class);

    @Autowired
    private RzyCommonMapper rzyCommonMapper;


    /**
     * 查询纸张配方
     * 
     * @param id 纸张配方ID
     * @return 纸张配方
     */
    @Override
    public ConfigPaperFormula selectConfigPaperFormulaById(Long id)
    {
        return configPaperFormulaMapper.selectConfigPaperFormulaById(id);
    }

    /**
     * 查询纸张配方列表
     * 
     * @param configPaperFormula 纸张配方
     * @return 纸张配方
     */
    @Override
    public List<ConfigPaperFormula> selectConfigPaperFormulaList(ConfigPaperFormula configPaperFormula)
    {
        return configPaperFormulaMapper.selectConfigPaperFormulaList(configPaperFormula);
    }

    /**
     * 新增纸张配方
     * 
     * @param configPaperFormula 纸张配方
     * @return 结果
     */
    @Override
    public int insertConfigPaperFormula(ConfigPaperFormula configPaperFormula)
    {
        configPaperFormula.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configPaperFormula.setCreateTime(DateUtils.getNowDate());
        configPaperFormula.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configPaperFormula.setUpdateTime(DateUtils.getNowDate());
        return configPaperFormulaMapper.insertConfigPaperFormula(configPaperFormula);
    }

    /**
     * 修改纸张配方
     * 
     * @param configPaperFormula 纸张配方
     * @return 结果
     */
    @Override
    public int updateConfigPaperFormula(ConfigPaperFormula configPaperFormula)
    {
        configPaperFormula.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configPaperFormula.setUpdateTime(DateUtils.getNowDate());
        return configPaperFormulaMapper.updateConfigPaperFormula(configPaperFormula);
    }

    /**
     * 删除纸张配方对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigPaperFormulaByIds(String ids)
    {
        return configPaperFormulaMapper.deleteConfigPaperFormulaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除纸张配方信息
     * 
     * @param id 纸张配方ID
     * @return 结果
     */
    @Override
    public int deleteConfigPaperFormulaById(Long id)
    {
        return configPaperFormulaMapper.deleteConfigPaperFormulaById(id);
    }

    /**
     * 导入数据
     *
     * @param configPaperFormulaList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigPaperFormula(List<ConfigPaperFormula> configPaperFormulaList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configPaperFormulaList) || configPaperFormulaList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigPaperFormula configPaperFormula : configPaperFormulaList){
            boolean checkFlag = false;
            //空字符串处理
            configPaperFormula = (ConfigPaperFormula) EntityUtils.nullStringToNull(configPaperFormula);
            try{
                //姓名必填
                if(StringUtils.isEmpty(configPaperFormula.getPaperFormulaName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 纸张配方必填");
                }
                //id转换
                if(!StringUtils.isEmpty(configPaperFormula.getCorrugatedName())){
                    configPaperFormula.setCorrugatedId(rzyCommonMapper.findIdByName(configPaperFormula.getCorrugatedName(),"config_corrugated","corrugated_name","id"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigPaperFormula(configPaperFormula);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configPaperFormula.getPaperFormulaName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、纸张配方 " + configPaperFormula.getPaperFormulaName() + " 导入失败：";
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
