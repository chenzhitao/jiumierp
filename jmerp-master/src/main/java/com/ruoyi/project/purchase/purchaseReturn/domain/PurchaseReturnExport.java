package com.ruoyi.project.purchase.purchaseReturn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购退货对象 purchase_return
 * 
 * @author fangzhou
 * @date 2021-05-08
 */
public class PurchaseReturnExport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 审批人 */
    //@Excel(name = "审批人")
    private String approver;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

    /** 审批类型 */
    //@Excel(name = "审批类型")
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

    /** 订单类型 */
    @Excel(name = "订单类型", dictType = "purchase_order_type")
    private String purchaseOrderType;

    /** 付款方式 */
    @Excel(name = "付款方式", dictType = "payment_type")
    private String paymentType;

    /** 结款方式 */
    @Excel(name = "结款方式", dictType = "settlement_type")
    private String settlementType;

    /** 交货方式 */
    @Excel(name = "交货方式", dictType = "delivery_type")
    private String deliveryType;



    private Long purchaseCheckingId;

    /** 采购退货 */
    //@Excel(name = "采购退货")
    private Long purchaseReturnId;

    /** 采购到货 */
    //@Excel(name = "采购到货")
    private Long purchaseDeliveryId;

    /** 采购到货材料 */
    //@Excel(name = "采购到货材料")
    private Long purchaseDeliveryMaterialsId;

    /** 采购订单 */
    //@Excel(name = "采购订单")
    private Long purchaseOrderId;

    /** 施工单 */
    //@Excel(name = "施工单")
    private Long produceOrderId;

    /** 施工材料 */
    //@Excel(name = "施工材料")
    private Long produceOrderMaterialsId;

    /** 采购申请单 */
    //@Excel(name = "采购申请单")
    private Long purchaseRequestId;

    /** 销售订单 */
    //@Excel(name = "销售订单")
    private Long saleOrderId;

    /** 销售产品 */
    //@Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 关联产品 */
    //@Excel(name = "关联产品")
    private Long productId;

    /** 关联工序 */
    //@Excel(name = "关联工序")
    private Long processId;

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;
    private Long materialsId;

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseName;
    private Long warehouseId;

    /** 退货折扣(%) */
    @Excel(name = "退货折扣(%)")
    private BigDecimal returnRate;

    /** 退货类型 */
    @Excel(name = "退货类型", dictType = "return_type")
    private String returnType;

    /** 退货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnDate;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    @Excel(name = "规格")
    private String materialsSize;

    /** 规格长 */
    //@Excel(name = "规格长")
    private Integer sizeLong;

    /** 规格宽 */
    //@Excel(name = "规格宽")
    private Integer sizeWidth;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    /** 退货数量 */
    @Excel(name = "退货数量")
    private Integer returnQty;

    /** 税率 */
    @Excel(name = "税率", taxRate = "tax")
    private BigDecimal taxRate;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    /** 材料要求 */
    @Excel(name = "材料要求")
    private String requirements;

    /** 退货地址 */
    @Excel(name = "退货地址")
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
    public void setReturnDate(Date returnDate)
    {
        this.returnDate = returnDate;
    }

    public Date getReturnDate()
    {
        return returnDate;
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
            .append("returnDate", getReturnDate())
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

    public Long getPurchaseCheckingId() {
        return purchaseCheckingId;
    }

    public void setPurchaseCheckingId(Long purchaseCheckingId) {
        this.purchaseCheckingId = purchaseCheckingId;
    }

    public Long getPurchaseReturnId() {
        return purchaseReturnId;
    }

    public void setPurchaseReturnId(Long purchaseReturnId) {
        this.purchaseReturnId = purchaseReturnId;
    }

    public Long getPurchaseDeliveryId() {
        return purchaseDeliveryId;
    }

    public void setPurchaseDeliveryId(Long purchaseDeliveryId) {
        this.purchaseDeliveryId = purchaseDeliveryId;
    }

    public Long getPurchaseDeliveryMaterialsId() {
        return purchaseDeliveryMaterialsId;
    }

    public void setPurchaseDeliveryMaterialsId(Long purchaseDeliveryMaterialsId) {
        this.purchaseDeliveryMaterialsId = purchaseDeliveryMaterialsId;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getProduceOrderId() {
        return produceOrderId;
    }

    public void setProduceOrderId(Long produceOrderId) {
        this.produceOrderId = produceOrderId;
    }

    public Long getProduceOrderMaterialsId() {
        return produceOrderMaterialsId;
    }

    public void setProduceOrderMaterialsId(Long produceOrderMaterialsId) {
        this.produceOrderMaterialsId = produceOrderMaterialsId;
    }

    public Long getPurchaseRequestId() {
        return purchaseRequestId;
    }

    public void setPurchaseRequestId(Long purchaseRequestId) {
        this.purchaseRequestId = purchaseRequestId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public Long getMaterialsId() {
        return materialsId;
    }

    public void setMaterialsId(Long materialsId) {
        this.materialsId = materialsId;
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

    public BigDecimal getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(BigDecimal returnRate) {
        this.returnRate = returnRate;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMaterialsSize() {
        return materialsSize;
    }

    public void setMaterialsSize(String materialsSize) {
        this.materialsSize = materialsSize;
    }

    public Integer getSizeLong() {
        return sizeLong;
    }

    public void setSizeLong(Integer sizeLong) {
        this.sizeLong = sizeLong;
    }

    public Integer getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(Integer sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Integer returnQty) {
        this.returnQty = returnQty;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
