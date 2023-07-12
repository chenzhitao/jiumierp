package com.ruoyi.project.sale.saleQuotationProduct.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 报价产品对象 sale_quotation_product
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class SaleQuotationProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private Long saleOrderProductId;
    private String serialNumber;
    private Integer qty;
    private Long saleOrderId;
    private BigDecimal amount;

    /** 报价单 */
    //@Excel(name = "报价单")
    private Long saleQuotationId;

    /** 客户 */
    //@Excel(name = "客户")
    private String customerName;
    private Long customerId;

    /** 产品 */
    @Excel(name = "产品名称")
    private String productName;
    private Long productId;

    @Excel(name = "产品规格(长)")
    private Integer sizeLong;

    @Excel(name = "产品规格(宽)")
    private Integer sizeWidth;

    @Excel(name = "产品规格(高)")
    private Integer sizeHeight;

    /** 报价工艺卡 */
    @Excel(name = "报价工艺卡")
    private String quotationTemplateName;
    private Long quotationTemplateId;
    private String quotationTemplateProcessArr;
    private String quotationTemplateMaterialsArr;

    /** 生产工艺卡 */
    @Excel(name = "生产工艺卡")
    private String productionTemplateName;
    private Long productionTemplateId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSaleQuotationId(Long saleQuotationId)
    {
        this.saleQuotationId = saleQuotationId;
    }

    public Long getSaleQuotationId()
    {
        return saleQuotationId;
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
    public void setQuotationTemplateId(Long quotationTemplateId)
    {
        this.quotationTemplateId = quotationTemplateId;
    }

    public Long getQuotationTemplateId()
    {
        return quotationTemplateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("saleQuotationId", getSaleQuotationId())
            .append("customerId", getCustomerId())
            .append("productId", getProductId())
            .append("quotationTemplateId", getQuotationTemplateId())
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

    public String getQuotationTemplateName() {
        return quotationTemplateName;
    }

    public void setQuotationTemplateName(String quotationTemplateName) {
        this.quotationTemplateName = quotationTemplateName;
    }

    public String getProductionTemplateName() {
        return productionTemplateName;
    }

    public void setProductionTemplateName(String productionTemplateName) {
        this.productionTemplateName = productionTemplateName;
    }

    public Long getProductionTemplateId() {
        return productionTemplateId;
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

    public String getQuotationTemplateProcessArr() {
        return quotationTemplateProcessArr;
    }

    public void setQuotationTemplateProcessArr(String quotationTemplateProcessArr) {
        this.quotationTemplateProcessArr = quotationTemplateProcessArr;
    }

    public String getQuotationTemplateMaterialsArr() {
        return quotationTemplateMaterialsArr;
    }

    public void setQuotationTemplateMaterialsArr(String quotationTemplateMaterialsArr) {
        this.quotationTemplateMaterialsArr = quotationTemplateMaterialsArr;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Long getSaleOrderProductId() {
        return saleOrderProductId;
    }

    public void setSaleOrderProductId(Long saleOrderProductId) {
        this.saleOrderProductId = saleOrderProductId;
    }

    public void setProductionTemplateId(Long productionTemplateId) {
        this.productionTemplateId = productionTemplateId;
    }

    public Long getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(Long saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
