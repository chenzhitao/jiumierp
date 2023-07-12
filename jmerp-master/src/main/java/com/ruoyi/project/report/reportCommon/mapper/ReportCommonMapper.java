package com.ruoyi.project.report.reportCommon.mapper;

import java.util.List;

import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.report.reportCommon.domain.ReportCommon;
import com.ruoyi.project.report.reportCommon.domain.ReportProductionFinancial;
import com.ruoyi.project.report.reportCommon.domain.ReportPurchaseIncoming;

/**
 * 报表公共Mapper接口
 * 
 * @author fangzhou
 * @date 2021-06-04
 */
public interface ReportCommonMapper 
{
    /**
     * 查询报表公共
     * 
     * @param id 报表公共ID
     * @return 报表公共
     */
    public ReportCommon selectReportCommonById(Long id);

    /**
     * 查询报表公共列表
     * 
     * @param reportCommon 报表公共
     * @return 报表公共集合
     */
    public List<ReportCommon> selectReportCommonList(ReportCommon reportCommon);

    /**
     * 新增报表公共
     * 
     * @param reportCommon 报表公共
     * @return 结果
     */
    public int insertReportCommon(ReportCommon reportCommon);

    /**
     * 修改报表公共
     * 
     * @param reportCommon 报表公共
     * @return 结果
     */
    public int updateReportCommon(ReportCommon reportCommon);

    /**
     * 删除报表公共
     * 
     * @param id 报表公共ID
     * @return 结果
     */
    public int deleteReportCommonById(Long id);

    /**
     * 批量删除报表公共
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReportCommonByIds(String[] ids);


    public List<ReportPurchaseIncoming> purchaseIncomingList(ReportCommon reportCommon);
    public List<ReportPurchaseIncoming> purchaseIncomingQtyList(ReportCommon reportCommon);

    /**
     * 生产财务报表
     * @Author 方舟
     * @Date 2021/6/4 12:26:56
     **/
    public List<ReportProductionFinancial> productionFinancialList(ReportCommon reportCommon);
}
