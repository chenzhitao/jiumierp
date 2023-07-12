package com.ruoyi.project.finance.financeDailyDetail.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.project.config.configTeam.domain.ConfigTeam;
import com.ruoyi.project.config.configTeam.mapper.ConfigTeamMapper;
import com.ruoyi.project.config.configTeamMember.domain.ConfigTeamMember;
import com.ruoyi.project.config.configTeamMember.mapper.ConfigTeamMemberMapper;
import com.ruoyi.project.finance.financeDaily.domain.FinanceDaily;
import com.ruoyi.project.finance.financeDailyDetailEmployee.domain.FinanceDailyDetailEmployee;
import com.ruoyi.project.finance.financeDailyDetailEmployee.mapper.FinanceDailyDetailEmployeeMapper;
import com.ruoyi.project.produce.produceSchedule.domain.ProduceSchedule;
import com.ruoyi.project.produce.produceSchedule.mapper.ProduceScheduleMapper;
import com.ruoyi.project.produce.produceScheduleProcess.domain.ProduceScheduleProcess;
import com.ruoyi.project.produce.produceScheduleProcess.mapper.ProduceScheduleProcessMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.finance.financeDailyDetail.mapper.FinanceDailyDetailMapper;
import com.ruoyi.project.finance.financeDailyDetail.domain.FinanceDailyDetail;
import com.ruoyi.project.finance.financeDailyDetail.service.IFinanceDailyDetailService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 排程明细Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class FinanceDailyDetailServiceImpl implements IFinanceDailyDetailService 
{
    @Autowired
    private FinanceDailyDetailMapper financeDailyDetailMapper;
    @Autowired
    private ProduceScheduleProcessMapper produceScheduleProcessMapper;
    @Autowired
    private ProduceScheduleMapper produceScheduleMapper;
    @Autowired
    private ConfigTeamMapper configTeamMapper;
    @Autowired
    private ConfigTeamMemberMapper configTeamMemberMapper;
    @Autowired
    private FinanceDailyDetailEmployeeMapper financeDailyDetailEmployeeMapper;

    /**
     * 查询排程明细
     * 
     * @param id 排程明细ID
     * @return 排程明细
     */
    @Override
    public FinanceDailyDetail selectFinanceDailyDetailById(Long id)
    {
        return financeDailyDetailMapper.selectFinanceDailyDetailById(id);
    }

    /**
     * 查询排程明细列表
     * 
     * @param financeDailyDetail 排程明细
     * @return 排程明细
     */
    @Override
    public List<FinanceDailyDetail> selectFinanceDailyDetailList(FinanceDailyDetail financeDailyDetail)
    {
        return financeDailyDetailMapper.selectFinanceDailyDetailList(financeDailyDetail);
    }

    /**
     * 新增排程明细
     * 
     * @param financeDailyDetail 排程明细
     * @return 结果
     */
    @Override
    public int insertFinanceDailyDetail(FinanceDailyDetail financeDailyDetail)
    {
        return financeDailyDetailMapper.insertFinanceDailyDetail(financeDailyDetail);
    }

    /**
     * 修改排程明细
     * 
     * @param financeDailyDetail 排程明细
     * @return 结果
     */
    @Override
    public int updateFinanceDailyDetail(FinanceDailyDetail financeDailyDetail)
    {
        return financeDailyDetailMapper.updateFinanceDailyDetail(financeDailyDetail);
    }

    /**
     * 删除排程明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFinanceDailyDetailByIds(String ids)
    {
        return financeDailyDetailMapper.deleteFinanceDailyDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除排程明细信息
     * 
     * @param id 排程明细ID
     * @return 结果
     */
    @Override
    public int deleteFinanceDailyDetailById(Long id)
    {
        return financeDailyDetailMapper.deleteFinanceDailyDetailById(id);
    }

    /**
     * 创建日报班组
     * @Author 方舟
     * @Date 2021/5/25 12:13:55
     **/
    @Override
    public FinanceDailyDetail createFinanceDailyTeam(FinanceDailyDetail financeDailyDetail){
        String[] ids = Convert.toStrArray(financeDailyDetail.getIds());
        for (int i=0;i<ids.length;i++){
            Long produceScheduleProcessId = Long.parseLong(ids[i]);
            ProduceScheduleProcess produceScheduleProcess = produceScheduleProcessMapper.selectProduceScheduleProcessById(produceScheduleProcessId);
            ProduceSchedule produceSchedule = produceScheduleMapper.selectProduceScheduleById(produceScheduleProcess.getProduceScheduleId());
            FinanceDailyDetail saveVO = new FinanceDailyDetail();
            BeanUtils.copyProperties(produceSchedule, saveVO);
            BeanUtils.copyProperties(produceScheduleProcess, saveVO);
            saveVO.setFinanceDailyId(financeDailyDetail.getFinanceDailyId());
            saveVO.setWorkTime(new BigDecimal(0));
            insertFinanceDailyDetail(saveVO);
            Long detailTeamId = saveVO.getId();
            ConfigTeam configTeam = configTeamMapper.selectConfigTeamById(produceSchedule.getTeamId());
            ConfigTeamMember paramVO = new ConfigTeamMember();
            paramVO.setTeamId(produceSchedule.getTeamId());
            List<ConfigTeamMember> memberList = configTeamMemberMapper.selectConfigTeamMemberList(paramVO);
            for (int j=0;j<memberList.size();j++){
                ConfigTeamMember memberVO = memberList.get(j);
                FinanceDailyDetailEmployee employeeVO = new FinanceDailyDetailEmployee();
                employeeVO.setEmployeeId(memberVO.getEmployeeId());
                employeeVO.setEmployeePrice(new BigDecimal(0));
                employeeVO.setEmployeeScale(new BigDecimal(0));
                employeeVO.setFinanceDailyId(financeDailyDetail.getFinanceDailyId());
                employeeVO.setFinanceDailyDetailId(detailTeamId);
                employeeVO.setProcessOrder(j+1);
                financeDailyDetailEmployeeMapper.insertFinanceDailyDetailEmployee(employeeVO);
            }
        }
        return financeDailyDetail;
    }
}
