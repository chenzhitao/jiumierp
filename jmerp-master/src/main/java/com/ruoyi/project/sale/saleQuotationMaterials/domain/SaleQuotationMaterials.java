package com.ruoyi.project.sale.saleQuotationMaterials.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 报价材料对象 sale_quotation_materials
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class SaleQuotationMaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 报价单 */
    @Excel(name = "材料")
    private String materialsName;
    private Long materialsId;

    /** 报价单 */
    @Excel(name = "报价单")
    private Long saleQuotationId;

    /** 客户 */
    @Excel(name = "客户")
    private Long customerId;

    /** 报价产品 */
    @Excel(name = "报价产品")
    private Long saleQuotationProductId;

    /** 报价工艺卡 */
    @Excel(name = "报价工艺卡")
    private Long quotationTemplateId;

    /** 工艺卡材料 */
    @Excel(name = "工艺卡材料")
    private Long quotationTemplateMaterialsId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 关联工序 */
    @Excel(name = "关联工序")
    private String processName;
    private Long processId;

    /** 计算公式 */
    @Excel(name = "计算公式")
    private String formulaName;
    private Long formulaId;

    /** 倍率 */
    @Excel(name = "倍率")
    private BigDecimal times;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 设备 */
    @Excel(name = "设备")
    private Long equipmentId;

    /** 损耗率(%) */
    @Excel(name = "损耗率(%)")
    private BigDecimal lossRate;

    /** 固定损耗数 */
    @Excel(name = "固定损耗数")
    private Integer lossQty;

    /** 单个尺寸长 */
    @Excel(name = "单个尺寸长")
    private Integer singleSizeLong;

    /** 单个尺寸宽 */
    @Excel(name = "单个尺寸宽")
    private Integer singleSizeWidth;

    /** 开料尺寸长 */
    @Excel(name = "开料尺寸长")
    private Integer cuttingSizeLong;

    /** 开料尺寸宽 */
    @Excel(name = "开料尺寸宽")
    private Integer cuttingSizeWidth;
    private String isGetProcessQty;


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
    public void setSaleQuotationProductId(Long saleQuotationProductId)
    {
        this.saleQuotationProductId = saleQuotationProductId;
    }

    public Long getSaleQuotationProductId()
    {
        return saleQuotationProductId;
    }
    public void setQuotationTemplateId(Long quotationTemplateId)
    {
        this.quotationTemplateId = quotationTemplateId;
    }

    public Long getQuotationTemplateId()
    {
        return quotationTemplateId;
    }
    public void setQuotationTemplateMaterialsId(Long quotationTemplateMaterialsId)
    {
        this.quotationTemplateMaterialsId = quotationTemplateMaterialsId;
    }

    public Long getQuotationTemplateMaterialsId()
    {
        return quotationTemplateMaterialsId;
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
    public void setFormulaId(Long formulaId)
    {
        this.formulaId = formulaId;
    }

    public Long getFormulaId()
    {
        return formulaId;
    }
    public void setTimes(BigDecimal times)
    {
        this.times = times;
    }

    public BigDecimal getTimes()
    {
        return times;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setLossRate(BigDecimal lossRate)
    {
        this.lossRate = lossRate;
    }

    public BigDecimal getLossRate()
    {
        return lossRate;
    }
    public void setLossQty(Integer lossQty)
    {
        this.lossQty = lossQty;
    }

    public Integer getLossQty()
    {
        return lossQty;
    }
    public void setSingleSizeLong(Integer singleSizeLong)
    {
        this.singleSizeLong = singleSizeLong;
    }

    public Integer getSingleSizeLong()
    {
        return singleSizeLong;
    }
    public void setSingleSizeWidth(Integer singleSizeWidth)
    {
        this.singleSizeWidth = singleSizeWidth;
    }

    public Integer getSingleSizeWidth()
    {
        return singleSizeWidth;
    }
    public void setCuttingSizeLong(Integer cuttingSizeLong)
    {
        this.cuttingSizeLong = cuttingSizeLong;
    }

    public Integer getCuttingSizeLong()
    {
        return cuttingSizeLong;
    }
    public void setCuttingSizeWidth(Integer cuttingSizeWidth)
    {
        this.cuttingSizeWidth = cuttingSizeWidth;
    }

    public Integer getCuttingSizeWidth()
    {
        return cuttingSizeWidth;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("saleQuotationId", getSaleQuotationId())
            .append("customerId", getCustomerId())
            .append("saleQuotationProductId", getSaleQuotationProductId())
            .append("quotationTemplateId", getQuotationTemplateId())
            .append("quotationTemplateMaterialsId", getQuotationTemplateMaterialsId())
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("formulaId", getFormulaId())
            .append("times", getTimes())
            .append("price", getPrice())
            .append("equipmentId", getEquipmentId())
            .append("lossRate", getLossRate())
            .append("lossQty", getLossQty())
            .append("singleSizeLong", getSingleSizeLong())
            .append("singleSizeWidth", getSingleSizeWidth())
            .append("cuttingSizeLong", getCuttingSizeLong())
            .append("cuttingSizeWidth", getCuttingSizeWidth())
            .toString();
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

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getIsGetProcessQty() {
        return isGetProcessQty;
    }

    public void setIsGetProcessQty(String isGetProcessQty) {
        this.isGetProcessQty = isGetProcessQty;
    }
}
