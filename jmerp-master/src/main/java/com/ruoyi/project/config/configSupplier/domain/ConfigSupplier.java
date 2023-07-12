package com.ruoyi.project.config.configSupplier.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 供应商信息对象 config_supplier
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigSupplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 供应商类型 */
    @Excel(name = "供应商类型", dictType = "supplier_type")
    private String supplierType;

    /** 税率 */
    @Excel(name = "税率")
    private String taxName;
    private BigDecimal taxRate;

    /** 采购员 */
    @Excel(name = "采购员")
    private String buyerName;
    private Long buyerId;

    /** 付款方式 */
    @Excel(name = "付款方式", dictType = "payment_type")
    private String paymentType;

    /** 结款方式 */
    @Excel(name = "结款方式", dictType = "settlement_type")
    private String settlementType;

    /** 交货方式 */
    @Excel(name = "交货方式", dictType = "delivery_type")
    private String deliveryType;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

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
    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName()
    {
        return supplierName;
    }
    public void setSupplierType(String supplierType)
    {
        this.supplierType = supplierType;
    }

    public String getSupplierType()
    {
        return supplierType;
    }
    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate()
    {
        return taxRate;
    }
    public void setBuyerId(Long buyerId)
    {
        this.buyerId = buyerId;
    }

    public Long getBuyerId()
    {
        return buyerId;
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
            .append("supplierName", getSupplierName())
            .append("supplierType", getSupplierType())
            .append("taxRate", getTaxRate())
            .append("buyerId", getBuyerId())
            .append("paymentType", getPaymentType())
            .append("settlementType", getSettlementType())
            .append("deliveryType", getDeliveryType())
            .append("address", getAddress())
            .toString();
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
