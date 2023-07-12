package com.ruoyi.project.sale.saleReturn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 退货单对象 sale_return
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class SaleReturnExport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    @Excel(name = "状态", dictType = "workflow_status")
    private String status;
    private String address;

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
    @Excel(name = "税率", taxRate = "tax")
    private BigDecimal taxRate;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    /** 退货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnDate;

    /** ID */
    private Long saleCheckingId;

    /** 退货单 */
    //@Excel(name = "退货单")
    private String saleReturnSN;
    private Long saleReturnId;

    /** 送货单 */
    @Excel(name = "送货单")
    private String saleDeliverySN;
    private Long saleDeliveryId;

    /** 送货产品 */
    //@Excel(name = "送货产品")
    private Long saleDeliveryProductId;

    /** 销售订单 */
    @Excel(name = "销售订单")
    private String saleOrderSN;
    private Long saleOrderId;

    /** 销售产品 */
    //@Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 送货排程 */
    //@Excel(name = "送货排程")
    private Long saleOrderDeliveryId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 退货类型 */
    @Excel(name = "退货类型", dictType = "return_type")
    private String returnType;

    /** 退货折扣(%) */
    @Excel(name = "退货折扣(%)")
    private String returnRate;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;
    private Integer deliveryQty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseName;
    private Long warehouseId;

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
    public void setReturnDate(Date returnDate)
    {
        this.returnDate = returnDate;
    }

    public Date getReturnDate()
    {
        return returnDate;
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
            .append("returnDate", getReturnDate())
            .toString();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Long getSaleCheckingId() {
        return saleCheckingId;
    }

    public void setSaleCheckingId(Long saleCheckingId) {
        this.saleCheckingId = saleCheckingId;
    }

    public String getSaleReturnSN() {
        return saleReturnSN;
    }

    public void setSaleReturnSN(String saleReturnSN) {
        this.saleReturnSN = saleReturnSN;
    }

    public Long getSaleReturnId() {
        return saleReturnId;
    }

    public void setSaleReturnId(Long saleReturnId) {
        this.saleReturnId = saleReturnId;
    }

    public String getSaleDeliverySN() {
        return saleDeliverySN;
    }

    public void setSaleDeliverySN(String saleDeliverySN) {
        this.saleDeliverySN = saleDeliverySN;
    }

    public Long getSaleDeliveryId() {
        return saleDeliveryId;
    }

    public void setSaleDeliveryId(Long saleDeliveryId) {
        this.saleDeliveryId = saleDeliveryId;
    }

    public Long getSaleDeliveryProductId() {
        return saleDeliveryProductId;
    }

    public void setSaleDeliveryProductId(Long saleDeliveryProductId) {
        this.saleDeliveryProductId = saleDeliveryProductId;
    }

    public String getSaleOrderSN() {
        return saleOrderSN;
    }

    public void setSaleOrderSN(String saleOrderSN) {
        this.saleOrderSN = saleOrderSN;
    }

    public Long getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Long saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public Long getSaleOrderProductId() {
        return saleOrderProductId;
    }

    public void setSaleOrderProductId(Long saleOrderProductId) {
        this.saleOrderProductId = saleOrderProductId;
    }

    public Long getSaleOrderDeliveryId() {
        return saleOrderDeliveryId;
    }

    public void setSaleOrderDeliveryId(Long saleOrderDeliveryId) {
        this.saleOrderDeliveryId = saleOrderDeliveryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(String returnRate) {
        this.returnRate = returnRate;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(Integer deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}
