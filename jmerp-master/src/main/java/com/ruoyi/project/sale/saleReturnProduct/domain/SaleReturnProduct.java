package com.ruoyi.project.sale.saleReturnProduct.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 退货产品对象 sale_return_product
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class SaleReturnProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private Long saleCheckingId;

    /** 退货单 */
    @Excel(name = "退货单")
    private String saleReturnSN;
    private Long saleReturnId;

    /** 送货单 */
    @Excel(name = "送货单")
    private String saleDeliverySN;
    private Long saleDeliveryId;

    /** 送货产品 */
    @Excel(name = "送货产品")
    private Long saleDeliveryProductId;

    /** 销售订单 */
    @Excel(name = "销售订单")
    private String saleOrderSN;
    private Long saleOrderId;

    /** 销售产品 */
    @Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 送货排程 */
    @Excel(name = "送货排程")
    private Long saleOrderDeliveryId;

    /** 客户 */
    @Excel(name = "客户")
    private String customerName;
    private Long customerId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 退货类型 */
    @Excel(name = "退货类型")
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

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal taxRate;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseName;
    private Long warehouseId;

    /** 退货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnDate;

    /** 送货地址 */
    @Excel(name = "送货地址")
    private String address;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSaleReturnId(Long saleReturnId)
    {
        this.saleReturnId = saleReturnId;
    }

    public Long getSaleReturnId()
    {
        return saleReturnId;
    }
    public void setSaleDeliveryId(Long saleDeliveryId)
    {
        this.saleDeliveryId = saleDeliveryId;
    }

    public Long getSaleDeliveryId()
    {
        return saleDeliveryId;
    }
    public void setSaleDeliveryProductId(Long saleDeliveryProductId)
    {
        this.saleDeliveryProductId = saleDeliveryProductId;
    }

    public Long getSaleDeliveryProductId()
    {
        return saleDeliveryProductId;
    }
    public void setSaleOrderId(Long saleOrderId)
    {
        this.saleOrderId = saleOrderId;
    }

    public Long getSaleOrderId()
    {
        return saleOrderId;
    }
    public void setSaleOrderProductId(Long saleOrderProductId)
    {
        this.saleOrderProductId = saleOrderProductId;
    }

    public Long getSaleOrderProductId()
    {
        return saleOrderProductId;
    }
    public void setSaleOrderDeliveryId(Long saleOrderDeliveryId)
    {
        this.saleOrderDeliveryId = saleOrderDeliveryId;
    }

    public Long getSaleOrderDeliveryId()
    {
        return saleOrderDeliveryId;
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
    public void setReturnType(String returnType)
    {
        this.returnType = returnType;
    }

    public String getReturnType()
    {
        return returnType;
    }
    public void setReturnRate(String returnRate)
    {
        this.returnRate = returnRate;
    }

    public String getReturnRate()
    {
        return returnRate;
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
    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }
    public void setReturnDate(Date returnDate)
    {
        this.returnDate = returnDate;
    }

    public Date getReturnDate()
    {
        return returnDate;
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
            .append("remark", getRemark())
            .append("saleReturnId", getSaleReturnId())
            .append("saleDeliveryId", getSaleDeliveryId())
            .append("saleDeliveryProductId", getSaleDeliveryProductId())
            .append("saleOrderId", getSaleOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("saleOrderDeliveryId", getSaleOrderDeliveryId())
            .append("customerId", getCustomerId())
            .append("productId", getProductId())
            .append("returnType", getReturnType())
            .append("returnRate", getReturnRate())
            .append("qty", getQty())
            .append("price", getPrice())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("warehouseId", getWarehouseId())
            .append("returnDate", getReturnDate())
            .append("address", getAddress())
            .toString();
    }

    public String getSaleReturnSN() {
        return saleReturnSN;
    }

    public void setSaleReturnSN(String saleReturnSN) {
        this.saleReturnSN = saleReturnSN;
    }

    public String getSaleDeliverySN() {
        return saleDeliverySN;
    }

    public void setSaleDeliverySN(String saleDeliverySN) {
        this.saleDeliverySN = saleDeliverySN;
    }

    public String getSaleOrderSN() {
        return saleOrderSN;
    }

    public void setSaleOrderSN(String saleOrderSN) {
        this.saleOrderSN = saleOrderSN;
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

    public Integer getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(Integer deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public Long getSaleCheckingId() {
        return saleCheckingId;
    }

    public void setSaleCheckingId(Long saleCheckingId) {
        this.saleCheckingId = saleCheckingId;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
