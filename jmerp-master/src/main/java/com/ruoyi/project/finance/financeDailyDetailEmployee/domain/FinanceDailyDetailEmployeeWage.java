package com.ruoyi.project.finance.financeDailyDetailEmployee.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 排程员工对象 finance_daily_detail_employee
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public class FinanceDailyDetailEmployeeWage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private String status;

    @Excel(name = "员工")
    private String employeeName;
    private Long employeeId;

    @Excel(name = "本次金额")
    private BigDecimal thisAmount;

    @Excel(name = "本次单价")
    private BigDecimal thisPrice;

    @Excel(name = "班组")
    private String teamName;
    private Long teamId;

    @Excel(name = "生产排程")
    private String produceScheduleSN;
    private Long produceScheduleId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dailyDate;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "工作开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "工作结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date endTime;

    @Excel(name = "工作时间(H)")
    private BigDecimal workTime;

    /** 数量 */
    @Excel(name = "工作数量")
    private Integer qty;

    /** 单价 */
    @Excel(name = "总单价")
    private BigDecimal price;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    @Excel(name = "工序")
    private String processName;
    private Long processId;

    @Excel(name = "工作内容")
    private String jobContent;

    @Excel(name = "计价类型", dictType = "finance_valuation_type")
    private String valuationType;


    /** 财务日报 */
    //@Excel(name = "财务日报")
    private String financeDailySN;
    private Long financeDailyId;

    /** 排程明细 */
    //@Excel(name = "排程明细")
    private Long financeDailyDetailId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getThisAmount() {
        return thisAmount;
    }

    public void setThisAmount(BigDecimal thisAmount) {
        this.thisAmount = thisAmount;
    }

    public BigDecimal getThisPrice() {
        return thisPrice;
    }

    public void setThisPrice(BigDecimal thisPrice) {
        this.thisPrice = thisPrice;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getProduceScheduleSN() {
        return produceScheduleSN;
    }

    public void setProduceScheduleSN(String produceScheduleSN) {
        this.produceScheduleSN = produceScheduleSN;
    }

    public Long getProduceScheduleId() {
        return produceScheduleId;
    }

    public void setProduceScheduleId(Long produceScheduleId) {
        this.produceScheduleId = produceScheduleId;
    }

    public Date getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(Date dailyDate) {
        this.dailyDate = dailyDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getWorkTime() {
        return workTime;
    }

    public void setWorkTime(BigDecimal workTime) {
        this.workTime = workTime;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public String getValuationType() {
        return valuationType;
    }

    public void setValuationType(String valuationType) {
        this.valuationType = valuationType;
    }

    public Long getFinanceDailyId() {
        return financeDailyId;
    }

    public void setFinanceDailyId(Long financeDailyId) {
        this.financeDailyId = financeDailyId;
    }

    public Long getFinanceDailyDetailId() {
        return financeDailyDetailId;
    }

    public String getFinanceDailySN() {
        return financeDailySN;
    }

    public void setFinanceDailySN(String financeDailySN) {
        this.financeDailySN = financeDailySN;
    }

    public void setFinanceDailyDetailId(Long financeDailyDetailId) {
        this.financeDailyDetailId = financeDailyDetailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
