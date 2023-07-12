package com.ruoyi.project.finance.financeDaily.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.finance.financeDaily.domain.FinanceDailyExport;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.finance.financeDaily.mapper.FinanceDailyMapper;
import com.ruoyi.project.finance.financeDaily.domain.FinanceDaily;
import com.ruoyi.project.finance.financeDaily.service.IFinanceDailyService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 生产日报Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class FinanceDailyServiceImpl implements IFinanceDailyService 
{
    @Autowired
    private FinanceDailyMapper financeDailyMapper;

    /**
     * 查询生产日报
     * 
     * @param id 生产日报ID
     * @return 生产日报
     */
    @Override
    public FinanceDaily selectFinanceDailyById(Long id)
    {
        return financeDailyMapper.selectFinanceDailyById(id);
    }

    /**
     * 查询生产日报列表
     * 
     * @param financeDaily 生产日报
     * @return 生产日报
     */
    @Override
    public List<FinanceDaily> selectFinanceDailyList(FinanceDaily financeDaily)
    {
        return financeDailyMapper.selectFinanceDailyList(financeDaily);
    }
    @Override
    public List<FinanceDailyExport> selectFinanceDailyExportList(FinanceDaily financeDaily)
    {
        return financeDailyMapper.selectFinanceDailyExportList(financeDaily);
    }

    /**
     * 新增生产日报
     * 
     * @param financeDaily 生产日报
     * @return 结果
     */
    @Override
    public FinanceDaily insertFinanceDaily(FinanceDaily financeDaily)
    {
        financeDaily.setCreateBy(ShiroUtils.getSysUser().getUserName());
        financeDaily.setCreateTime(DateUtils.getNowDate());
        financeDaily.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        financeDaily.setUpdateTime(DateUtils.getNowDate());
        financeDailyMapper.insertFinanceDaily(financeDaily);
        return financeDaily;
    }

    /**
     * 修改生产日报
     * 
     * @param financeDaily 生产日报
     * @return 结果
     */
    @Override
    public int updateFinanceDaily(FinanceDaily financeDaily)
    {
        financeDaily.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        financeDaily.setUpdateTime(DateUtils.getNowDate());
        return financeDailyMapper.updateFinanceDaily(financeDaily);
    }

    /**
     * 删除生产日报对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFinanceDailyByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            FinanceDaily param = financeDailyMapper.selectFinanceDailyById(Long.parseLong(arr[i]));
            if("draft".equals(param.getStatus())){
                result = financeDailyMapper.deleteFinanceDailyById(Long.parseLong(arr[i]));
                financeDailyMapper.deleteFinanceDailyDetailById(Long.parseLong(arr[i]));
                financeDailyMapper.deleteFinanceDailyEmployeeById(Long.parseLong(arr[i]));
            }else{
                param.setId(Long.parseLong(arr[i]));
                param.setStatus("delete");
                result = financeDailyMapper.updateFinanceDaily(param);
            }
        }
        return result;
        //return financeDailyMapper.deleteFinanceDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产日报信息
     * 
     * @param id 生产日报ID
     * @return 结果
     */
    @Override
    public int deleteFinanceDailyById(Long id)
    {
        int result = 0;
        FinanceDaily param = financeDailyMapper.selectFinanceDailyById(id);
        if("draft".equals(param.getStatus())){
            result = financeDailyMapper.deleteFinanceDailyById(id);
            financeDailyMapper.deleteFinanceDailyDetailById(id);
            financeDailyMapper.deleteFinanceDailyEmployeeById(id);
        }else{
            param.setId(id);
            param.setStatus("delete");
            result = financeDailyMapper.updateFinanceDaily(param);
        }
        return result;
        //return financeDailyMapper.deleteFinanceDailyById(id);
    }
}
