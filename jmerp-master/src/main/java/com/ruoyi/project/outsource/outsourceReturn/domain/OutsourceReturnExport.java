package com.ruoyi.project.outsource.outsourceReturn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 外发退货对象 outsource_return
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public class OutsourceReturnExport extends BaseEntity
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

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 手机号 */
    @Excel(name = "手机号")
    private String cellPhone;


    private Long outsourceCheckingId;
    private Long produceOrderProcessId;
    private Integer deliveryQty;

    /** 外发退货 */
    @Excel(name = "外发退货")
    private String outsourceReturnSN;
    private Long outsourceReturnId;

    /** 外发到货 */
    @Excel(name = "外发到货")
    private String outsourceDeliverySN;
    private Long outsourceDeliveryId;

    /** 外发到货工序 */
    //@Excel(name = "外发到货工序")
    private Long outsourceDeliveryProcessId;

    /** 外发加工工序 */
    //@Excel(name = "外发加工工序")
    private Long outsourceOrderProcessId;

    /** 外发加工 */
    @Excel(name = "外发加工")
    private String outsourceOrderSN;
    private Long outsourceOrderId;

    /** 销售产品 */
    //@Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 销售订单 */
    //@Excel(name = "销售订单")
    private Long saleOrderId;

    /** 施工单 */
    @Excel(name = "施工单")
    private String produceOrderSN;
    private Long produceOrderId;

    /** 施工产品 */
    //@Excel(name = "施工产品")
    private Long produceOrderProductId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 工序 */
    @Excel(name = "工序")
    private String processName;
    private Long processId;

    /** 生产工艺卡 */
    //@Excel(name = "生产工艺卡")
    private Long productionTemplateId;

    /** 生产工艺卡工序 */
    //@Excel(name = "生产工艺卡工序")
    private Long productionTemplateProcessId;

    /** 外发类型 */
    @Excel(name = "外发类型", dictType = "outsource_type")
    private String outsourceType;

    /** 计价方式 */
    @Excel(name = "计价方式", dictType = "valuation_type")
    private String outsourceValuationType;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 退货类型 */
    @Excel(name = "退货类型", dictType = "return_type")
    private String returnType;

    /** 退货折扣(%) */
    @Excel(name = "退货折扣(%)", suffix = "%")
    private BigDecimal returnRate;

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
            .append("supplierId", getSupplierId())
            .append("contact", getContact())
            .append("cellPhone", getCellPhone())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("returnDate", getReturnDate())
            .toString();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public Long getOutsourceCheckingId() {
        return outsourceCheckingId;
    }

    public void setOutsourceCheckingId(Long outsourceCheckingId) {
        this.outsourceCheckingId = outsourceCheckingId;
    }

    public Long getProduceOrderProcessId() {
        return produceOrderProcessId;
    }

    public void setProduceOrderProcessId(Long produceOrderProcessId) {
        this.produceOrderProcessId = produceOrderProcessId;
    }

    public Integer getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(Integer deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public String getOutsourceReturnSN() {
        return outsourceReturnSN;
    }

    public void setOutsourceReturnSN(String outsourceReturnSN) {
        this.outsourceReturnSN = outsourceReturnSN;
    }

    public Long getOutsourceReturnId() {
        return outsourceReturnId;
    }

    public void setOutsourceReturnId(Long outsourceReturnId) {
        this.outsourceReturnId = outsourceReturnId;
    }

    public String getOutsourceDeliverySN() {
        return outsourceDeliverySN;
    }

    public void setOutsourceDeliverySN(String outsourceDeliverySN) {
        this.outsourceDeliverySN = outsourceDeliverySN;
    }

    public Long getOutsourceDeliveryId() {
        return outsourceDeliveryId;
    }

    public void setOutsourceDeliveryId(Long outsourceDeliveryId) {
        this.outsourceDeliveryId = outsourceDeliveryId;
    }

    public Long getOutsourceDeliveryProcessId() {
        return outsourceDeliveryProcessId;
    }

    public void setOutsourceDeliveryProcessId(Long outsourceDeliveryProcessId) {
        this.outsourceDeliveryProcessId = outsourceDeliveryProcessId;
    }

    public Long getOutsourceOrderProcessId() {
        return outsourceOrderProcessId;
    }

    public void setOutsourceOrderProcessId(Long outsourceOrderProcessId) {
        this.outsourceOrderProcessId = outsourceOrderProcessId;
    }

    public String getOutsourceOrderSN() {
        return outsourceOrderSN;
    }

    public void setOutsourceOrderSN(String outsourceOrderSN) {
        this.outsourceOrderSN = outsourceOrderSN;
    }

    public Long getOutsourceOrderId() {
        return outsourceOrderId;
    }

    public void setOutsourceOrderId(Long outsourceOrderId) {
        this.outsourceOrderId = outsourceOrderId;
    }

    public Long getSaleOrderProductId() {
        return saleOrderProductId;
    }

    public void setSaleOrderProductId(Long saleOrderProductId) {
        this.saleOrderProductId = saleOrderProductId;
    }

    public Long getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Long saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
    }

    public Long getProduceOrderId() {
        return produceOrderId;
    }

    public void setProduceOrderId(Long produceOrderId) {
        this.produceOrderId = produceOrderId;
    }

    public Long getProduceOrderProductId() {
        return produceOrderProductId;
    }

    public void setProduceOrderProductId(Long produceOrderProductId) {
        this.produceOrderProductId = produceOrderProductId;
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

    public Long getProductionTemplateId() {
        return productionTemplateId;
    }

    public void setProductionTemplateId(Long productionTemplateId) {
        this.productionTemplateId = productionTemplateId;
    }

    public Long getProductionTemplateProcessId() {
        return productionTemplateProcessId;
    }

    public void setProductionTemplateProcessId(Long productionTemplateProcessId) {
        this.productionTemplateProcessId = productionTemplateProcessId;
    }

    public String getOutsourceType() {
        return outsourceType;
    }

    public void setOutsourceType(String outsourceType) {
        this.outsourceType = outsourceType;
    }

    public String getOutsourceValuationType() {
        return outsourceValuationType;
    }

    public void setOutsourceValuationType(String outsourceValuationType) {
        this.outsourceValuationType = outsourceValuationType;
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

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public BigDecimal getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(BigDecimal returnRate) {
        this.returnRate = returnRate;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
