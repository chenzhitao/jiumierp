package com.ruoyi.project.report.reportCommon.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.report.reportCommon.domain.ReportProductionFinancial;
import com.ruoyi.project.report.reportCommon.domain.ReportPurchaseIncoming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.report.reportCommon.mapper.ReportCommonMapper;
import com.ruoyi.project.report.reportCommon.domain.ReportCommon;
import com.ruoyi.project.report.reportCommon.service.IReportCommonService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 报表公共Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-06-04
 */
@Service
public class ReportCommonServiceImpl implements IReportCommonService 
{
    @Autowired
    private ReportCommonMapper reportCommonMapper;

    /**
     * 查询报表公共
     * 
     * @param id 报表公共ID
     * @return 报表公共
     */
    @Override
    public ReportCommon selectReportCommonById(Long id)
    {
        return reportCommonMapper.selectReportCommonById(id);
    }

    /**
     * 查询报表公共列表
     * 
     * @param reportCommon 报表公共
     * @return 报表公共
     */
    @Override
    public List<ReportCommon> selectReportCommonList(ReportCommon reportCommon)
    {
        return reportCommonMapper.selectReportCommonList(reportCommon);
    }

    /**
     * 新增报表公共
     * 
     * @param reportCommon 报表公共
     * @return 结果
     */
    @Override
    public int insertReportCommon(ReportCommon reportCommon)
    {
        reportCommon.setCreateTime(DateUtils.getNowDate());
        return reportCommonMapper.insertReportCommon(reportCommon);
    }

    /**
     * 修改报表公共
     * 
     * @param reportCommon 报表公共
     * @return 结果
     */
    @Override
    public int updateReportCommon(ReportCommon reportCommon)
    {
        reportCommon.setUpdateTime(DateUtils.getNowDate());
        return reportCommonMapper.updateReportCommon(reportCommon);
    }

    /**
     * 删除报表公共对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteReportCommonByIds(String ids)
    {
        return reportCommonMapper.deleteReportCommonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报表公共信息
     * 
     * @param id 报表公共ID
     * @return 结果
     */
    @Override
    public int deleteReportCommonById(Long id)
    {
        return reportCommonMapper.deleteReportCommonById(id);
    }

    /**
     * 来料报表
     * @Author 方舟
     * @Date 2021/6/4 12:26:56
     **/
    @Override
    public List<ReportPurchaseIncoming> purchaseIncomingList(ReportCommon reportCommon){
        List<ReportPurchaseIncoming> list = reportCommonMapper.purchaseIncomingList(reportCommon);
        return list;
    }
    @Override
    public List<ReportPurchaseIncoming> purchaseIncomingQtyList(ReportCommon reportCommon){
        List<ReportPurchaseIncoming> list = reportCommonMapper.purchaseIncomingQtyList(reportCommon);
        return list;
    }

    /**
     * 生产财务报表
     * @Author 方舟
     * @Date 2021/6/4 12:26:56
     **/
    @Override
    public List<ReportProductionFinancial> productionFinancialList(ReportCommon reportCommon){
        List<ReportProductionFinancial> list = reportCommonMapper.productionFinancialList(reportCommon);
        Long tempPoId = -1L;
        for (int i=0;i<list.size();i++){
            if(list.get(i).getProduceOrderId().equals(tempPoId)){
                list.get(i).setExtraAmount(new BigDecimal(0));
            }else{
                tempPoId = list.get(i).getProduceOrderId();
            }
        }
        return list;
    }
}
