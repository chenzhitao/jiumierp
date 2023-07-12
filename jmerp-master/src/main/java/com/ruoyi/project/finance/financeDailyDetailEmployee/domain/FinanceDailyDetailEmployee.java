package com.ruoyi.project.finance.financeDailyDetailEmployee.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 排程员工对象 finance_daily_detail_employee
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public class FinanceDailyDetailEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 财务日报 */
    @Excel(name = "财务日报")
    private Long financeDailyId;

    /** 排程明细 */
    @Excel(name = "排程明细")
    private Long financeDailyDetailId;

    /** 组员 */
    @Excel(name = "组员")
    private String employeeName;
    private Long employeeId;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 员工单价 */
    @Excel(name = "员工单价")
    private BigDecimal employeePrice;
    private String employeePriceArr;

    /** 员工比例(%) */
    @Excel(name = "员工比例(%)")
    private BigDecimal employeeScale;
    private String employeeScaleArr;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer processOrder;
    private String processOrderArr;

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
    public void setFinanceDailyDetailId(Long financeDailyDetailId)
    {
        this.financeDailyDetailId = financeDailyDetailId;
    }

    public Long getFinanceDailyDetailId()
    {
        return financeDailyDetailId;
    }
    public void setEmployeeId(Long employeeId)
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId()
    {
        return employeeId;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setEmployeePrice(BigDecimal employeePrice)
    {
        this.employeePrice = employeePrice;
    }

    public BigDecimal getEmployeePrice()
    {
        return employeePrice;
    }
    public void setEmployeeScale(BigDecimal employeeScale)
    {
        this.employeeScale = employeeScale;
    }

    public BigDecimal getEmployeeScale()
    {
        return employeeScale;
    }
    public void setProcessOrder(Integer processOrder)
    {
        this.processOrder = processOrder;
    }

    public Integer getProcessOrder()
    {
        return processOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("financeDailyId", getFinanceDailyId())
            .append("financeDailyDetailId", getFinanceDailyDetailId())
            .append("employeeId", getEmployeeId())
            .append("price", getPrice())
            .append("employeePrice", getEmployeePrice())
            .append("employeeScale", getEmployeeScale())
            .append("processOrder", getProcessOrder())
            .toString();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePriceArr() {
        return employeePriceArr;
    }

    public void setEmployeePriceArr(String employeePriceArr) {
        this.employeePriceArr = employeePriceArr;
    }

    public String getEmployeeScaleArr() {
        return employeeScaleArr;
    }

    public void setEmployeeScaleArr(String employeeScaleArr) {
        this.employeeScaleArr = employeeScaleArr;
    }

    public String getProcessOrderArr() {
        return processOrderArr;
    }

    public void setProcessOrderArr(String processOrderArr) {
        this.processOrderArr = processOrderArr;
    }
}
