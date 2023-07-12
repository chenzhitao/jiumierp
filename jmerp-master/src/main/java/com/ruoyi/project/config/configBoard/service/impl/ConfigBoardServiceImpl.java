package com.ruoyi.project.config.configBoard.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configCutterDie.domain.ConfigCutterDie;
import com.ruoyi.project.config.configCutterDie.mapper.ConfigCutterDieMapper;
import com.ruoyi.project.config.configCutterDie.service.impl.ConfigCutterDieServiceImpl;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configBoard.mapper.ConfigBoardMapper;
import com.ruoyi.project.config.configBoard.domain.ConfigBoard;
import com.ruoyi.project.config.configBoard.service.IConfigBoardService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 板材配置Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigBoardServiceImpl implements IConfigBoardService 
{

    private static final Logger log = LoggerFactory.getLogger(ConfigBoardServiceImpl.class);

    @Autowired
    private ConfigBoardMapper configBoardMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询板材配置
     * 
     * @param id 板材配置ID
     * @return 板材配置
     */
    @Override
    public ConfigBoard selectConfigBoardById(Long id)
    {
        return configBoardMapper.selectConfigBoardById(id);
    }

    /**
     * 查询板材配置列表
     * 
     * @param configBoard 板材配置
     * @return 板材配置
     */
    @Override
    public List<ConfigBoard> selectConfigBoardList(ConfigBoard configBoard)
    {
        return configBoardMapper.selectConfigBoardList(configBoard);
    }

    /**
     * 新增板材配置
     * 
     * @param configBoard 板材配置
     * @return 结果
     */
    @Override
    public int insertConfigBoard(ConfigBoard configBoard)
    {
        configBoard.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configBoard.setCreateTime(DateUtils.getNowDate());
        configBoard.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configBoard.setUpdateTime(DateUtils.getNowDate());
        return configBoardMapper.insertConfigBoard(configBoard);
    }

    /**
     * 修改板材配置
     * 
     * @param configBoard 板材配置
     * @return 结果
     */
    @Override
    public int updateConfigBoard(ConfigBoard configBoard)
    {
        configBoard.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configBoard.setUpdateTime(DateUtils.getNowDate());
        return configBoardMapper.updateConfigBoard(configBoard);
    }

    /**
     * 删除板材配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigBoardByIds(String ids)
    {
        return configBoardMapper.deleteConfigBoardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除板材配置信息
     * 
     * @param id 板材配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigBoardById(Long id)
    {
        return configBoardMapper.deleteConfigBoardById(id);
    }

    /**
     * 导入数据
     *
     * @param configBoardList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigBoard(List<ConfigBoard> configBoardList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configBoardList) || configBoardList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigBoard configBoard : configBoardList){
            boolean checkFlag = false;
            //空字符串处理
            configBoard = (ConfigBoard) EntityUtils.nullStringToNull(configBoard);
            try{
                //名称必填
                if(StringUtils.isEmpty(configBoard.getBoardName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 板材名称必填");
                }
                //id转换
                if(!StringUtils.isEmpty(configBoard.getCustomerName())){
                    configBoard.setCustomerId(rzyCommonMapper.findIdByName(configBoard.getCustomerName(),"config_customer","customer_name","id"));
                }
                if(!StringUtils.isEmpty(configBoard.getWarehouseName())){
                    configBoard.setWarehouseId(rzyCommonMapper.findIdByName(configBoard.getWarehouseName(),"config_warehouse","warehouse_name","id"));
                }
                if(!StringUtils.isEmpty(configBoard.getProductName())){
                    configBoard.setProductId(rzyCommonMapper.findIdByName(configBoard.getProductName(),"config_product","product_name","id"));
                }
                //成功
                if(!checkFlag){
                    this.insertConfigBoard(configBoard);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configBoard.getBoardName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、板材 " + configBoard.getBoardName() + " 导入失败：";
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
