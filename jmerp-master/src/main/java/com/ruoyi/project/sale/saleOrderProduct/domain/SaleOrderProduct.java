package com.ruoyi.project.sale.saleOrderProduct.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 销售产品对象 sale_order_product
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class SaleOrderProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private Long saleQuotationProductId;
    private String serialNumber;
    private Integer leftQty;
    private String customerNo;
    private String productFullName;
    private Long supplierTempId;
    private String isOutFlow;
    /** 销售订单 */
    //@Excel(name = "销售订单")
    private Long saleOrderId;

    /** 客户 */
    @Excel(name = "客户")
    private String customerName;
    private Long customerId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    private String productType;
    /** 产品规格(长) */
    @Excel(name = "产品规格(长)")
    private Integer sizeLong;
    /** 产品规格(宽) */
    @Excel(name = "产品规格(宽)")
    private Integer sizeWidth;
    /** 产品规格(高) */
    @Excel(name = "产品规格(高)")
    private Integer sizeHeight;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;
    private Integer sendQty;
    private Integer warehouseQty;

    /** 税率 */
    //@Excel(name = "税率")
    private BigDecimal taxRate;

    /** 总金额 */
    //@Excel(name = "总金额")
    private BigDecimal amount;

    /** 交货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 送货地址 */
    //@Excel(name = "送货地址")
    private String address;

    /** 产品要求 */
    //@Excel(name = "产品要求")
    private String requirements;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSaleOrderId(Long saleOrderId)
    {
        this.saleOrderId = saleOrderId;
    }

    public Long getSaleOrderId()
    {
        return saleOrderId;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
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
    public void setRequirements(String requirements)
    {
        this.requirements = requirements;
    }

    public String getRequirements()
    {
        return requirements;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("saleOrderId", getSaleOrderId())
            .append("customerId", getCustomerId())
            .append("productId", getProductId())
            .append("price", getPrice())
            .append("qty", getQty())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("deliveryDate", getDeliveryDate())
            .append("address", getAddress())
            .append("requirements", getRequirements())
            .toString();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Integer getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(Integer sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public Long getSaleQuotationProductId() {
        return saleQuotationProductId;
    }

    public void setSaleQuotationProductId(Long saleQuotationProductId) {
        this.saleQuotationProductId = saleQuotationProductId;
    }

    public Integer getSendQty() {
        return sendQty;
    }

    public void setSendQty(Integer sendQty) {
        this.sendQty = sendQty;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Integer getWarehouseQty() {
        return warehouseQty;
    }

    public void setWarehouseQty(Integer warehouseQty) {
        this.warehouseQty = warehouseQty;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getLeftQty() {
        return leftQty;
    }

    public void setLeftQty(Integer leftQty) {
        this.leftQty = leftQty;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public String getProductFullName() {
        return productFullName;
    }

    public void setProductFullName(String productFullName) {
        this.productFullName = productFullName;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Long getSupplierTempId() {
        return supplierTempId;
    }

    public void setSupplierTempId(Long supplierTempId) {
        this.supplierTempId = supplierTempId;
    }

    public String getIsOutFlow() {
        return isOutFlow;
    }

    public void setIsOutFlow(String isOutFlow) {
        this.isOutFlow = isOutFlow;
    }
}
