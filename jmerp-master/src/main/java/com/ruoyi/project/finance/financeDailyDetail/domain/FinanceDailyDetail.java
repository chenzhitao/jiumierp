package com.ruoyi.project.finance.financeDailyDetail.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 排程明细对象 finance_daily_detail
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public class FinanceDailyDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private String isSplit;

    /** 财务日报 */
    @Excel(name = "财务日报")
    private String financeDailySN;
    private Long financeDailyId;

    /** 施工单 */
    @Excel(name = "施工单")
    private String produceOrderSN;
    private Long produceOrderId;

    /** 生产排程 */
    @Excel(name = "生产排程")
    private String produceScheduleSN;
    private Long produceScheduleId;

    /** 班组 */
    @Excel(name = "班组")
    private String teamName;
    private Long teamId;

    /** 计费标准 */
    @Excel(name = "计费标准")
    private String financeStandardName;
    private Long financeStandardId;

    /** 工序 */
    @Excel(name = "工序")
    private String processName;
    private Long processId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 工作内容 */
    @Excel(name = "工作内容")
    private String jobContent;

    /** 分配方式 */
    @Excel(name = "分配方式")
    private String allocationType;

    /** 计价类型 */
    @Excel(name = "计价类型")
    private String valuationType;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date endTime;

    /** 工作时间(H) */
    @Excel(name = "工作时间(H)")
    private BigDecimal workTime;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setFinanceDailyId(Long financeDailyId)
    {
        this.financeDailyId = financeDailyId;
    }

    public Long getFinanceDailyId()
    {
        return financeDailyId;
    }
    public void setProduceOrderId(Long produceOrderId)
    {
        this.produceOrderId = produceOrderId;
    }

    public Long getProduceOrderId()
    {
        return produceOrderId;
    }
    public void setProduceScheduleId(Long produceScheduleId)
    {
        this.produceScheduleId = produceScheduleId;
    }

    public Long getProduceScheduleId()
    {
        return produceScheduleId;
    }
    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
    }
    public void setFinanceStandardId(Long financeStandardId)
    {
        this.financeStandardId = financeStandardId;
    }

    public Long getFinanceStandardId()
    {
        return financeStandardId;
    }
    public void setProcessId(Long processId)
    {
        this.processId = processId;
    }

    public Long getProcessId()
    {
        return processId;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setJobContent(String jobContent)
    {
        this.jobContent = jobContent;
    }

    public String getJobContent()
    {
        return jobContent;
    }
    public void setAllocationType(String allocationType)
    {
        this.allocationType = allocationType;
    }

    public String getAllocationType()
    {
        return allocationType;
    }
    public void setValuationType(String valuationType)
    {
        this.valuationType = valuationType;
    }

    public String getValuationType()
    {
        return valuationType;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setWorkTime(BigDecimal workTime)
    {
        this.workTime = workTime;
    }

    public BigDecimal getWorkTime()
    {
        return workTime;
    }
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("financeDailyId", getFinanceDailyId())
            .append("produceOrderId", getProduceOrderId())
            .append("produceScheduleId", getProduceScheduleId())
            .append("teamId", getTeamId())
            .append("financeStandardId", getFinanceStandardId())
            .append("processId", getProcessId())
            .append("productId", getProductId())
            .append("jobContent", getJobContent())
            .append("allocationType", getAllocationType())
            .append("valuationType", getValuationType())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("workTime", getWorkTime())
            .append("qty", getQty())
            .append("price", getPrice())
            .append("amount", getAmount())
            .toString();
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
    }

    public String getProduceScheduleSN() {
        return produceScheduleSN;
    }

    public void setProduceScheduleSN(String produceScheduleSN) {
        this.produceScheduleSN = produceScheduleSN;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProductName() {
        return productName;
    }

    public String getFinanceDailySN() {
        return financeDailySN;
    }

    public void setFinanceDailySN(String financeDailySN) {
        this.financeDailySN = financeDailySN;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFinanceStandardName() {
        return financeStandardName;
    }

    public String getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(String isSplit) {
        this.isSplit = isSplit;
    }

    public void setFinanceStandardName(String financeStandardName) {
        this.financeStandardName = financeStandardName;
    }
}
