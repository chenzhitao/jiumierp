package com.ruoyi.project.sale.saleQuotationMultDetail.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 多数量报价明细对象 sale_quotation_mult_detail
 * 
 * @author fangzhou
 * @date 2021-04-21
 */
public class SaleQuotationMultDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    @Excel(name = "报价工艺卡")
    private Long quotationTemplateId;

    /** 报价单 */
    @Excel(name = "报价单")
    private Long saleQuotationId;

    /** 多数量 */
    @Excel(name = "多数量")
    private Long saleQuotationMultId;

    /** 报价产品 */
    @Excel(name = "报价产品")
    private Long saleQuotationProductId;

    /** 报价工序 */
    @Excel(name = "报价工序")
    private Long saleQuotationProcessId;

    /** 报价材料 */
    @Excel(name = "报价材料")
    private Long saleQuotationMaterialsId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private String productType;
    private Long productId;

    /** 工序 */
    @Excel(name = "工序")
    private String processName;
    private BigDecimal processPrice;
    private Long processId;

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;
    private BigDecimal materialsPrice;
    private Long materialsId;

    /** 投入数 */
    @Excel(name = "投入数")
    private Integer inQty;

    /** 产出数 */
    @Excel(name = "产出数")
    private Integer outQty;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer processOrder;

    private String calculateLog;
    private String isAreaCalculate;
    private Integer materialsSizeLong;
    private Integer materialsSizeWidth;

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
    public void setSaleQuotationMultId(Long saleQuotationMultId)
    {
        this.saleQuotationMultId = saleQuotationMultId;
    }

    public Long getSaleQuotationMultId()
    {
        return saleQuotationMultId;
    }
    public void setSaleQuotationProductId(Long saleQuotationProductId)
    {
        this.saleQuotationProductId = saleQuotationProductId;
    }

    public Long getSaleQuotationProductId()
    {
        return saleQuotationProductId;
    }
    public void setSaleQuotationProcessId(Long saleQuotationProcessId)
    {
        this.saleQuotationProcessId = saleQuotationProcessId;
    }

    public Long getSaleQuotationProcessId()
    {
        return saleQuotationProcessId;
    }
    public void setSaleQuotationMaterialsId(Long saleQuotationMaterialsId)
    {
        this.saleQuotationMaterialsId = saleQuotationMaterialsId;
    }

    public Long getSaleQuotationMaterialsId()
    {
        return saleQuotationMaterialsId;
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
    public void setMaterialsId(Long materialsId)
    {
        this.materialsId = materialsId;
    }

    public Long getMaterialsId()
    {
        return materialsId;
    }
    public void setInQty(Integer inQty)
    {
        this.inQty = inQty;
    }

    public Integer getInQty()
    {
        return inQty;
    }
    public void setOutQty(Integer outQty)
    {
        this.outQty = outQty;
    }

    public Integer getOutQty()
    {
        return outQty;
    }
    public void setProcessOrder(Integer processOrder)
    {
        this.processOrder = processOrder;
    }

    public Integer getProcessOrder()
    {
        return processOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("saleQuotationId", getSaleQuotationId())
            .append("saleQuotationMultId", getSaleQuotationMultId())
            .append("saleQuotationProductId", getSaleQuotationProductId())
            .append("saleQuotationProcessId", getSaleQuotationProcessId())
            .append("saleQuotationMaterialsId", getSaleQuotationMaterialsId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("materialsId", getMaterialsId())
            .append("inQty", getInQty())
            .append("outQty", getOutQty())
            .append("processOrder", getProcessOrder())
            .toString();
    }

    public Long getQuotationTemplateId() {
        return quotationTemplateId;
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

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProcessPrice() {
        return processPrice;
    }

    public void setProcessPrice(BigDecimal processPrice) {
        this.processPrice = processPrice;
    }

    public BigDecimal getMaterialsPrice() {
        return materialsPrice;
    }

    public void setMaterialsPrice(BigDecimal materialsPrice) {
        this.materialsPrice = materialsPrice;
    }

    public String getCalculateLog() {
        return calculateLog;
    }

    public void setCalculateLog(String calculateLog) {
        this.calculateLog = calculateLog;
    }

    public void setQuotationTemplateId(Long quotationTemplateId) {
        this.quotationTemplateId = quotationTemplateId;
    }

    public String getIsAreaCalculate() {
        return isAreaCalculate;
    }

    public void setIsAreaCalculate(String isAreaCalculate) {
        this.isAreaCalculate = isAreaCalculate;
    }

    public Integer getMaterialsSizeLong() {
        return materialsSizeLong;
    }

    public void setMaterialsSizeLong(Integer materialsSizeLong) {
        this.materialsSizeLong = materialsSizeLong;
    }

    public Integer getMaterialsSizeWidth() {
        return materialsSizeWidth;
    }

    public void setMaterialsSizeWidth(Integer materialsSizeWidth) {
        this.materialsSizeWidth = materialsSizeWidth;
    }
}
