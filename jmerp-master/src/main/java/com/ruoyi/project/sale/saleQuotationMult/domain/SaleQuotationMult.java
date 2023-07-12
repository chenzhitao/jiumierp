package com.ruoyi.project.sale.saleQuotationMult.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 多数量报价对象 sale_quotation_mult
 * 
 * @author fangzhou
 * @date 2021-04-21
 */
public class SaleQuotationMult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /***************************************/

    @Excel(name = "客户名称")
    private String customerName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报价日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date quotationDate;

    /***************************************/

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 联系人手机 */
    @Excel(name = "联系人手机")
    private String cellPhone;

    /** 报价单 */
    @Excel(name = "报价单")
    private String saleQuotationSN;
    private Long saleQuotationId;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal taxRate;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /*******************************************/



    @Excel(name = "利润率")
    private BigDecimal profitRate;

    @Excel(name = "报价员")
    private String quoterName;

    @Excel(name = "产品")
    private String productName;

    private String calculateLog;

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
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("saleQuotationId", getSaleQuotationId())
            .append("qty", getQty())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("price", getPrice())
            .toString();
    }

    public String getCalculateLog() {
        return calculateLog;
    }

    public void setCalculateLog(String calculateLog) {
        this.calculateLog = calculateLog;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getQuotationDate() {
        return quotationDate;
    }

    public void setQuotationDate(Date quotationDate) {
        this.quotationDate = quotationDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getSaleQuotationSN() {
        return saleQuotationSN;
    }

    public void setSaleQuotationSN(String saleQuotationSN) {
        this.saleQuotationSN = saleQuotationSN;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public String getQuoterName() {
        return quoterName;
    }

    public void setQuoterName(String quoterName) {
        this.quoterName = quoterName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
