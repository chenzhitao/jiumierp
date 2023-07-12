package com.ruoyi.project.sale.saleOrder.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 销售订单对象 sale_order
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class SaleOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
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
    @Excel(name = "客户")
    private String customerName;
    private Long customerId;

    /** 销售员 */
    @Excel(name = "销售员")
    private String salesmanName;
    private Long salesmanId;

    /** 付款方式 */
    @Excel(name = "付款方式", dictType = "payment_type")
    private String paymentType;

    /** 结款方式 */
    @Excel(name = "结款方式", dictType = "settlement_type")
    private String settlementType;

    /** 交货方式 */
    @Excel(name = "交货方式", dictType = "delivery_type")
    private String deliveryType;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String contactPhone;

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal taxRate;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    /** 订金 */
    @Excel(name = "订金")
    private BigDecimal deposit;

    /** 送货地址 */
    @Excel(name = "送货地址")
    private String address;

    /*****************************************************/

    /** 交货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

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
    public void setSalesmanId(Long salesmanId)
    {
        this.salesmanId = salesmanId;
    }

    public Long getSalesmanId()
    {
        return salesmanId;
    }
    public void setPaymentType(String paymentType)
    {
        this.paymentType = paymentType;
    }

    public String getPaymentType()
    {
        return paymentType;
    }
    public void setSettlementType(String settlementType)
    {
        this.settlementType = settlementType;
    }

    public String getSettlementType()
    {
        return settlementType;
    }
    public void setDeliveryType(String deliveryType)
    {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryType()
    {
        return deliveryType;
    }
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getContact()
    {
        return contact;
    }
    public void setContactPhone(String contactPhone)
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone()
    {
        return contactPhone;
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
    public void setDeposit(BigDecimal deposit)
    {
        this.deposit = deposit;
    }

    public BigDecimal getDeposit()
    {
        return deposit;
    }
    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
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
            .append("salesmanId", getSalesmanId())
            .append("paymentType", getPaymentType())
            .append("settlementType", getSettlementType())
            .append("deliveryType", getDeliveryType())
            .append("contact", getContact())
            .append("contactPhone", getContactPhone())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("deposit", getDeposit())
            .append("deliveryDate", getDeliveryDate())
            .append("address", getAddress())
            .toString();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

}
