package com.ruoyi.project.purchase.purchaseDelivery.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 采购到货对象 purchase_delivery
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public class PurchaseDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approver;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

    /** 审批类型 */
    @Excel(name = "审批类型")
    private String approvalType;

    /** 单号 */
    @Excel(name = "单号")
    private String serialNumber;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;
    private Long supplierId;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 手机号 */
    @Excel(name = "手机号")
    private String cellPhone;

    /** 采购员 */
    @Excel(name = "采购员")
    private String buyerName;
    private Long buyerId;

    /** 到货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 订单类型 */
    @Excel(name = "订单类型")
    private String purchaseOrderType;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String paymentType;

    /** 结款方式 */
    @Excel(name = "结款方式")
    private String settlementType;

    /** 交货方式 */
    @Excel(name = "交货方式")
    private String deliveryType;

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal taxRate;

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
    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
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
    public void setBuyerId(Long buyerId)
    {
        this.buyerId = buyerId;
    }

    public Long getBuyerId()
    {
        return buyerId;
    }
    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }
    public void setPurchaseOrderType(String purchaseOrderType)
    {
        this.purchaseOrderType = purchaseOrderType;
    }

    public String getPurchaseOrderType()
    {
        return purchaseOrderType;
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
            .append("supplierId", getSupplierId())
            .append("contact", getContact())
            .append("cellPhone", getCellPhone())
            .append("buyerId", getBuyerId())
            .append("deliveryDate", getDeliveryDate())
            .append("purchaseOrderType", getPurchaseOrderType())
            .append("paymentType", getPaymentType())
            .append("settlementType", getSettlementType())
            .append("deliveryType", getDeliveryType())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .toString();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
