package com.ruoyi.project.finance.financeStandard.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.EntityUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configFormula.service.impl.ConfigFormulaServiceImpl;
import com.ruoyi.project.config.configSupplier.domain.ConfigSupplier;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.finance.financeStandard.mapper.FinanceStandardMapper;
import com.ruoyi.project.finance.financeStandard.domain.FinanceStandard;
import com.ruoyi.project.finance.financeStandard.service.IFinanceStandardService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 计费标准Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class FinanceStandardServiceImpl implements IFinanceStandardService 
{
    private static final Logger log = LoggerFactory.getLogger(FinanceStandardServiceImpl.class);
    @Autowired
    private FinanceStandardMapper financeStandardMapper;
    @Autowired
    private RzyCommonMapper rzyCommonMapper;
    /**
     * 查询计费标准
     * 
     * @param id 计费标准ID
     * @return 计费标准
     */
    @Override
    public FinanceStandard selectFinanceStandardById(Long id)
    {
        return financeStandardMapper.selectFinanceStandardById(id);
    }

    /**
     * 查询计费标准列表
     * 
     * @param financeStandard 计费标准
     * @return 计费标准
     */
    @Override
    public List<FinanceStandard> selectFinanceStandardList(FinanceStandard financeStandard)
    {
        return financeStandardMapper.selectFinanceStandardList(financeStandard);
    }

    /**
     * 新增计费标准
     * 
     * @param financeStandard 计费标准
     * @return 结果
     */
    @Override
    public int insertFinanceStandard(FinanceStandard financeStandard)
    {
        financeStandard.setCreateBy(ShiroUtils.getSysUser().getUserName());
        financeStandard.setCreateTime(DateUtils.getNowDate());
        financeStandard.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        financeStandard.setUpdateTime(DateUtils.getNowDate());
        return financeStandardMapper.insertFinanceStandard(financeStandard);
    }

    /**
     * 修改计费标准
     * 
     * @param financeStandard 计费标准
     * @return 结果
     */
    @Override
    public int updateFinanceStandard(FinanceStandard financeStandard)
    {
        financeStandard.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        financeStandard.setUpdateTime(DateUtils.getNowDate());
        return financeStandardMapper.updateFinanceStandard(financeStandard);
    }

    /**
     * 删除计费标准对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFinanceStandardByIds(String ids)
    {
        return financeStandardMapper.deleteFinanceStandardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除计费标准信息
     * 
     * @param id 计费标准ID
     * @return 结果
     */
    @Override
    public int deleteFinanceStandardById(Long id)
    {
        return financeStandardMapper.deleteFinanceStandardById(id);
    }

    /**
     * 导入数据
     *
     * @param financeStandardList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importFinanceStandard(List<FinanceStandard> financeStandardList, Boolean isUpdateSupport){
        if (StringUtils.isNull(financeStandardList) || financeStandardList.size() == 0){
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (FinanceStandard financeStandard : financeStandardList){
            boolean checkFlag = false;
            //空字符串处理
            financeStandard = (FinanceStandard) EntityUtils.nullStringToNull(financeStandard);
            try{
                //姓名必填
                if(StringUtils.isEmpty(financeStandard.getProcessName())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 工序必填");
                }else{
                    financeStandard.setProcessId(rzyCommonMapper.findIdByName(financeStandard.getProcessName(),"config_process","process_name","id"));
                    if(null==financeStandard.getProcessId()){
                        checkFlag = true;
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、 工序必须有效");
                    }
                }
                //工作内容
                if(StringUtils.isEmpty(financeStandard.getJobContent())){
                    checkFlag = true;
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、 工作内容必填");
                }

                //税率转换
                if(!StringUtils.isEmpty(financeStandard.getProductName())){
                    financeStandard.setProductId(rzyCommonMapper.findIdByName(financeStandard.getProductName(),"config_product","product_name","id"));
                }

                //成功
                if(!checkFlag){
                    this.insertFinanceStandard(financeStandard);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、 " + financeStandard.getJobContent() + " 导入成功");
                }

            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + "、供应商 " + financeStandard.getJobContent() + " 导入失败：";
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
