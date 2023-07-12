package com.ruoyi.project.outsource.outsourceReturnProcess.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 外发退货工序对象 outsource_return_process
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public class OutsourceReturnProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private Long outsourceCheckingId;
    private Long produceOrderProcessId;
    private Long supplierId;
    private String supplierName;
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
    @Excel(name = "外发到货工序")
    private Long outsourceDeliveryProcessId;

    /** 外发加工工序 */
    @Excel(name = "外发加工工序")
    private Long outsourceOrderProcessId;

    /** 外发加工 */
    @Excel(name = "外发加工")
    private String outsourceOrderSN;
    private Long outsourceOrderId;

    /** 销售产品 */
    @Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 销售订单 */
    @Excel(name = "销售订单")
    private Long saleOrderId;

    /** 施工单 */
    @Excel(name = "施工单")
    private String produceOrderSN;
    private Long produceOrderId;

    /** 施工产品 */
    @Excel(name = "施工产品")
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
    @Excel(name = "生产工艺卡")
    private Long productionTemplateId;

    /** 生产工艺卡工序 */
    @Excel(name = "生产工艺卡工序")
    private Long productionTemplateProcessId;

    /** 外发类型 */
    @Excel(name = "外发类型")
    private String outsourceType;

    /** 计价方式 */
    @Excel(name = "计价方式")
    private String outsourceValuationType;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 退货类型 */
    @Excel(name = "退货类型")
    private String returnType;

    /** 退货折扣(%) */
    @Excel(name = "退货折扣(%)")
    private BigDecimal returnRate;

    /** 税率 */
    @Excel(name = "税率")
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
    public void setOutsourceReturnId(Long outsourceReturnId)
    {
        this.outsourceReturnId = outsourceReturnId;
    }

    public Long getOutsourceReturnId()
    {
        return outsourceReturnId;
    }
    public void setOutsourceDeliveryId(Long outsourceDeliveryId)
    {
        this.outsourceDeliveryId = outsourceDeliveryId;
    }

    public Long getOutsourceDeliveryId()
    {
        return outsourceDeliveryId;
    }
    public void setOutsourceDeliveryProcessId(Long outsourceDeliveryProcessId)
    {
        this.outsourceDeliveryProcessId = outsourceDeliveryProcessId;
    }

    public Long getOutsourceDeliveryProcessId()
    {
        return outsourceDeliveryProcessId;
    }
    public void setOutsourceOrderProcessId(Long outsourceOrderProcessId)
    {
        this.outsourceOrderProcessId = outsourceOrderProcessId;
    }

    public Long getOutsourceOrderProcessId()
    {
        return outsourceOrderProcessId;
    }
    public void setOutsourceOrderId(Long outsourceOrderId)
    {
        this.outsourceOrderId = outsourceOrderId;
    }

    public Long getOutsourceOrderId()
    {
        return outsourceOrderId;
    }
    public void setSaleOrderProductId(Long saleOrderProductId)
    {
        this.saleOrderProductId = saleOrderProductId;
    }

    public Long getSaleOrderProductId()
    {
        return saleOrderProductId;
    }
    public void setSaleOrderId(Long saleOrderId)
    {
        this.saleOrderId = saleOrderId;
    }

    public Long getSaleOrderId()
    {
        return saleOrderId;
    }
    public void setProduceOrderId(Long produceOrderId)
    {
        this.produceOrderId = produceOrderId;
    }

    public Long getProduceOrderId()
    {
        return produceOrderId;
    }
    public void setProduceOrderProductId(Long produceOrderProductId)
    {
        this.produceOrderProductId = produceOrderProductId;
    }

    public Long getProduceOrderProductId()
    {
        return produceOrderProductId;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setProcessId(Long processId)
    {
        this.processId = processId;
    }

    public Long getProcessId()
    {
        return processId;
    }
    public void setProductionTemplateId(Long productionTemplateId)
    {
        this.productionTemplateId = productionTemplateId;
    }

    public Long getProductionTemplateId()
    {
        return productionTemplateId;
    }
    public void setProductionTemplateProcessId(Long productionTemplateProcessId)
    {
        this.productionTemplateProcessId = productionTemplateProcessId;
    }

    public Long getProductionTemplateProcessId()
    {
        return productionTemplateProcessId;
    }
    public void setOutsourceType(String outsourceType)
    {
        this.outsourceType = outsourceType;
    }

    public String getOutsourceType()
    {
        return outsourceType;
    }
    public void setOutsourceValuationType(String outsourceValuationType)
    {
        this.outsourceValuationType = outsourceValuationType;
    }

    public String getOutsourceValuationType()
    {
        return outsourceValuationType;
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
    public void setReturnType(String returnType)
    {
        this.returnType = returnType;
    }

    public String getReturnType()
    {
        return returnType;
    }
    public void setReturnRate(BigDecimal returnRate)
    {
        this.returnRate = returnRate;
    }

    public BigDecimal getReturnRate()
    {
        return returnRate;
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
            .append("remark", getRemark())
            .append("outsourceReturnId", getOutsourceReturnId())
            .append("outsourceDeliveryId", getOutsourceDeliveryId())
            .append("outsourceDeliveryProcessId", getOutsourceDeliveryProcessId())
            .append("outsourceOrderProcessId", getOutsourceOrderProcessId())
            .append("outsourceOrderId", getOutsourceOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("saleOrderId", getSaleOrderId())
            .append("produceOrderId", getProduceOrderId())
            .append("produceOrderProductId", getProduceOrderProductId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("productionTemplateId", getProductionTemplateId())
            .append("productionTemplateProcessId", getProductionTemplateProcessId())
            .append("outsourceType", getOutsourceType())
            .append("outsourceValuationType", getOutsourceValuationType())
            .append("qty", getQty())
            .append("price", getPrice())
            .append("returnType", getReturnType())
            .append("returnRate", getReturnRate())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("returnDate", getReturnDate())
            .toString();
    }

    public Long getProduceOrderProcessId() {
        return produceOrderProcessId;
    }

    public void setProduceOrderProcessId(Long produceOrderProcessId) {
        this.produceOrderProcessId = produceOrderProcessId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getOutsourceReturnSN() {
        return outsourceReturnSN;
    }

    public void setOutsourceReturnSN(String outsourceReturnSN) {
        this.outsourceReturnSN = outsourceReturnSN;
    }

    public String getOutsourceDeliverySN() {
        return outsourceDeliverySN;
    }

    public void setOutsourceDeliverySN(String outsourceDeliverySN) {
        this.outsourceDeliverySN = outsourceDeliverySN;
    }

    public String getOutsourceOrderSN() {
        return outsourceOrderSN;
    }

    public void setOutsourceOrderSN(String outsourceOrderSN) {
        this.outsourceOrderSN = outsourceOrderSN;
    }

    public String getProduceOrderSN() {
        return produceOrderSN;
    }

    public void setProduceOrderSN(String produceOrderSN) {
        this.produceOrderSN = produceOrderSN;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProcessName() {
        return processName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(Integer deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Long getOutsourceCheckingId() {
        return outsourceCheckingId;
    }

    public void setOutsourceCheckingId(Long outsourceCheckingId) {
        this.outsourceCheckingId = outsourceCheckingId;
    }
}
