package com.ruoyi.project.outsource.outsourceChecking.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 外发对账对象 outsource_checking
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public class OutsourceChecking extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

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

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;
    private Long supplierId;

    /** 对账日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "对账日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkingDate;

    /** 付款方式 */
    @Excel(name = "付款方式", dictType = "payment_type")
    private String paymentType;

    /** 结款方式 */
    @Excel(name = "结款方式", dictType = "settlement_type")
    private String settlementType;

    /** 税率 */
    @Excel(name = "税率", taxRate = "tax")
    private BigDecimal taxRate;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    /** 到货总金额 */
    @Excel(name = "到货总金额")
    private BigDecimal deliveryAmount;

    /** 退货总金额 */
    @Excel(name = "退货总金额")
    private BigDecimal returnAmount;

    /** 发票号 */
    @Excel(name = "发票号")
    private String invoiceNo;

    /** 开票单位 */
    @Excel(name = "开票单位")
    private String invoiceUnit;

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
    public void setCheckingDate(Date checkingDate)
    {
        this.checkingDate = checkingDate;
    }

    public Date getCheckingDate()
    {
        return checkingDate;
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
    public void setDeliveryAmount(BigDecimal deliveryAmount)
    {
        this.deliveryAmount = deliveryAmount;
    }

    public BigDecimal getDeliveryAmount()
    {
        return deliveryAmount;
    }
    public void setReturnAmount(BigDecimal returnAmount)
    {
        this.returnAmount = returnAmount;
    }

    public BigDecimal getReturnAmount()
    {
        return returnAmount;
    }
    public void setInvoiceNo(String invoiceNo)
    {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceNo()
    {
        return invoiceNo;
    }
    public void setInvoiceUnit(String invoiceUnit)
    {
        this.invoiceUnit = invoiceUnit;
    }

    public String getInvoiceUnit()
    {
        return invoiceUnit;
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
            .append("checkingDate", getCheckingDate())
            .append("paymentType", getPaymentType())
            .append("settlementType", getSettlementType())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("deliveryAmount", getDeliveryAmount())
            .append("returnAmount", getReturnAmount())
            .append("invoiceNo", getInvoiceNo())
            .append("invoiceUnit", getInvoiceUnit())
            .toString();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
