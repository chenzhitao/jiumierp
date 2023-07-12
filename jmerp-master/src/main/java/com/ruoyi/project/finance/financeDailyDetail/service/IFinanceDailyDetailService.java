package com.ruoyi.project.finance.financeDailyDetail.service;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.finance.financeDailyDetail.domain.FinanceDailyDetail;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 排程明细Service接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface IFinanceDailyDetailService 
{
    /**
     * 查询排程明细
     * 
     * @param id 排程明细ID
     * @return 排程明细
     */
    public FinanceDailyDetail selectFinanceDailyDetailById(Long id);

    /**
     * 查询排程明细列表
     * 
     * @param financeDailyDetail 排程明细
     * @return 排程明细集合
     */
    public List<FinanceDailyDetail> selectFinanceDailyDetailList(FinanceDailyDetail financeDailyDetail);

    /**
     * 新增排程明细
     * 
     * @param financeDailyDetail 排程明细
     * @return 结果
     */
    public int insertFinanceDailyDetail(FinanceDailyDetail financeDailyDetail);

    /**
     * 修改排程明细
     * 
     * @param financeDailyDetail 排程明细
     * @return 结果
     */
    public int updateFinanceDailyDetail(FinanceDailyDetail financeDailyDetail);

    /**
     * 批量删除排程明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceDailyDetailByIds(String ids);

    /**
     * 删除排程明细信息
     * 
     * @param id 排程明细ID
     * @return 结果
     */
    public int deleteFinanceDailyDetailById(Long id);

    /**
     * 创建日报班组
     * @Author 方舟
     * @Date 2021/5/25 12:13:55
     **/
    public FinanceDailyDetail createFinanceDailyTeam(FinanceDailyDetail financeDailyDetail);
}
