package com.ruoyi.project.sale.saleOrderDelivery.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 送货排程对象 sale_order_delivery
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class SaleOrderDelivery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private BigDecimal price;

    /** 销售订单 */
    @Excel(name = "销售订单")
    private String saleOrderSN;
    private Long saleOrderId;

    /** 销售产品 */
    @Excel(name = "销售产品")
    private Long saleOrderProductId;

    /** 客户 */
    @Excel(name = "客户")
    private String customerName;
    private Long customerId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;
    private Integer sendQty;

    /** 已送数量 */
    @Excel(name = "已送数量")
    private Integer deliveryQty;

    /** 送货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "送货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

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
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
    }
    public void setDeliveryQty(Integer deliveryQty)
    {
        this.deliveryQty = deliveryQty;
    }

    public Integer getDeliveryQty()
    {
        return deliveryQty;
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
            .append("remark", getRemark())
            .append("saleOrderId", getSaleOrderId())
            .append("saleOrderProductId", getSaleOrderProductId())
            .append("customerId", getCustomerId())
            .append("productId", getProductId())
            .append("qty", getQty())
            .append("deliveryQty", getDeliveryQty())
            .append("deliveryDate", getDeliveryDate())
            .append("address", getAddress())
            .toString();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSendQty() {
        return sendQty;
    }

    public void setSendQty(Integer sendQty) {
        this.sendQty = sendQty;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
