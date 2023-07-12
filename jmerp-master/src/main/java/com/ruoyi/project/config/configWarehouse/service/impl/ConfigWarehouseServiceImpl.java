package com.ruoyi.project.config.configWarehouse.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configBoard.domain.ConfigBoard;
import com.ruoyi.project.config.configBoard.service.impl.ConfigBoardServiceImpl;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.config.configWarehouse.mapper.ConfigWarehouseMapper;
import com.ruoyi.project.config.configWarehouse.domain.ConfigWarehouse;
import com.ruoyi.project.config.configWarehouse.service.IConfigWarehouseService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 仓库配置Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class ConfigWarehouseServiceImpl implements IConfigWarehouseService 
{
    private static final Logger log = LoggerFactory.getLogger(ConfigWarehouseServiceImpl.class);

    @Autowired
    private ConfigWarehouseMapper configWarehouseMapper;

    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询仓库配置
     * 
     * @param id 仓库配置ID
     * @return 仓库配置
     */
    @Override
    public ConfigWarehouse selectConfigWarehouseById(Long id)
    {
        return configWarehouseMapper.selectConfigWarehouseById(id);
    }

    /**
     * 查询仓库配置列表
     * 
     * @param configWarehouse 仓库配置
     * @return 仓库配置
     */
    @Override
    public List<ConfigWarehouse> selectConfigWarehouseList(ConfigWarehouse configWarehouse)
    {
        return configWarehouseMapper.selectConfigWarehouseList(configWarehouse);
    }

    /**
     * 新增仓库配置
     * 
     * @param configWarehouse 仓库配置
     * @return 结果
     */
    @Override
    public int insertConfigWarehouse(ConfigWarehouse configWarehouse)
    {
        configWarehouse.setCreateBy(ShiroUtils.getSysUser().getUserName());
        configWarehouse.setCreateTime(DateUtils.getNowDate());
        configWarehouse.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configWarehouse.setUpdateTime(DateUtils.getNowDate());
        return configWarehouseMapper.insertConfigWarehouse(configWarehouse);
    }

    /**
     * 修改仓库配置
     * 
     * @param configWarehouse 仓库配置
     * @return 结果
     */
    @Override
    public int updateConfigWarehouse(ConfigWarehouse configWarehouse)
    {
        configWarehouse.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        configWarehouse.setUpdateTime(DateUtils.getNowDate());
        return configWarehouseMapper.updateConfigWarehouse(configWarehouse);
    }

    /**
     * 删除仓库配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigWarehouseByIds(String ids)
    {
        return configWarehouseMapper.deleteConfigWarehouseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除仓库配置信息
     * 
     * @param id 仓库配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigWarehouseById(Long id)
    {
        return configWarehouseMapper.deleteConfigWarehouseById(id);
    }

    /**
     * 导入数据
     *
     * @param configWarehouseList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importConfigWarehouse(List<ConfigWarehouse> configWarehouseList, Boolean isUpdateSupport){
        if (StringUtils.isNull(configWarehouseList) || configWarehouseList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConfigWarehouse configWarehouse : configWarehouseList){
            boolean checkFlag = false;
            //空字符串处理
            configWarehouse = (ConfigWarehouse) EntityUtils.nullStringToNull(configWarehouse);
            try{
                //名称必填
                if(StringUtils.isEmpty(configWarehouse.getWarehouseName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 板材名称必填");
                }
                //id转换
                //成功
                if(!checkFlag){
                    this.insertConfigWarehouse(configWarehouse);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + configWarehouse.getWarehouseName() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、板材 " + configWarehouse.getWarehouseName() + " 导入失败：";
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
