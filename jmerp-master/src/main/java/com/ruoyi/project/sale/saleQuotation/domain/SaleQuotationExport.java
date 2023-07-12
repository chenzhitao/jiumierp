package com.ruoyi.project.sale.saleQuotation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 报价单对象 sale_quotation
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class SaleQuotationExport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private BigDecimal price;

    /** 状态 */
    @Excel(name = "状态", dictType = "workflow_status")
    private String status;

    /** 审批人 */
    private String approver;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

    /** 审批类型 */
    private String approvalType;

    /** 单号 */
    @Excel(name = "单号")
    private String serialNumber;

    /** 客户 */
    //@Excel(name = "客户")
    private Long customerId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 报价日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报价日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date quotationDate;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 联系人手机 */
    @Excel(name = "联系人手机")
    private String cellPhone;

    /** 税率 */
    @Excel(name = "税率", taxRate = "tax")
    private BigDecimal taxRate;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    /** 利润率 */
    @Excel(name = "利润率", suffix = "%")
    private BigDecimal profitRate;

    /** 报价员 */
    @Excel(name = "报价员")
    private String quoterName;
    private Long quoterId;

    /*****************************************************/
    /** 产品名称 */
    @Excel(name = "产品")
    private String productName;

    @Excel(name = "数量")
    private Integer qty;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setApprover(String approver)
    {
        this.approver = approver;
    }

    public String getApprover()
    {
        return approver;
    }
    public void setApprovalTime(Date approvalTime)
    {
        this.approvalTime = approvalTime;
    }

    public Date getApprovalTime()
    {
        return approvalTime;
    }
    public void setApprovalType(String approvalType)
    {
        this.approvalType = approvalType;
    }

    public String getApprovalType()
    {
        return approvalType;
    }
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerName()
    {
        return customerName;
    }
    public void setQuotationDate(Date quotationDate)
    {
        this.quotationDate = quotationDate;
    }

    public Date getQuotationDate()
    {
        return quotationDate;
    }
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return contact;
    }
    public void setCellPhone(String cellPhone)
    {
        this.cellPhone = cellPhone;
    }

    public String getCellPhone()
    {
        return cellPhone;
    }
    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate()
    {
        return taxRate;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setProfitRate(BigDecimal profitRate)
    {
        this.profitRate = profitRate;
    }

    public BigDecimal getProfitRate()
    {
        return profitRate;
    }
    public void setQuoterId(Long quoterId)
    {
        this.quoterId = quoterId;
    }

    public Long getQuoterId()
    {
        return quoterId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("approver", getApprover())
            .append("approvalTime", getApprovalTime())
            .append("approvalType", getApprovalType())
            .append("serialNumber", getSerialNumber())
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("quotationDate", getQuotationDate())
            .append("contact", getContact())
            .append("cellPhone", getCellPhone())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("profitRate", getProfitRate())
            .append("quoterId", getQuoterId())
            .toString();
    }

    public String getQuoterName() {
        return quoterName;
    }

    public void setQuoterName(String quoterName) {
        this.quoterName = quoterName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQty() {
        return qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
