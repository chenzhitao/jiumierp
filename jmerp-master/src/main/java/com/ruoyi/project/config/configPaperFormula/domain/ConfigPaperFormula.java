package com.ruoyi.project.config.configPaperFormula.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 纸张配方对象 config_paper_formula
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigPaperFormula extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 配方名称 */
    @Excel(name = "配方名称")
    private String paperFormulaName;

    /** 客户材质 */
    @Excel(name = "客户材质")
    private String customerMaterial;

    /** 楞型 */
    @Excel(name = "楞型")
    private String corrugatedName;
    private Long corrugatedId;

    /** 面纸 */
    @Excel(name = "面纸")
    private String surfacePaper;

    /** 底纸 */
    @Excel(name = "底纸")
    private String bottomPaper;

    /** 芯纸 */
    @Excel(name = "芯纸")
    private String corePaper;

    /** 楞纸 */
    @Excel(name = "楞纸")
    private String corrugatedPaper;

    /** 原纸成本 */
    @Excel(name = "原纸成本")
    private BigDecimal orgPaperPrice;

    /** 纸箱单价 */
    @Excel(name = "纸箱单价")
    private BigDecimal cartonPrice;

    /** 纸板单价 */
    @Excel(name = "纸板单价")
    private BigDecimal cardboardPrice;

    /** 平方重量(g) */
    @Excel(name = "平方重量(g)")
    private Integer squareWeight;

    /** 边压强度 */
    @Excel(name = "边压强度")
    private String edgeStrength;

    /** 耐破强度 */
    @Excel(name = "耐破强度")
    private String burstStrength;

    /** 含水率(%) */
    @Excel(name = "含水率(%)")
    private BigDecimal waterRate;

    /** 挺度 */
    @Excel(name = "挺度")
    private String stiffness;

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
    public void setPaperFormulaName(String paperFormulaName)
    {
        this.paperFormulaName = paperFormulaName;
    }

    public String getPaperFormulaName()
    {
        return paperFormulaName;
    }
    public void setCustomerMaterial(String customerMaterial)
    {
        this.customerMaterial = customerMaterial;
    }

    public String getCustomerMaterial()
    {
        return customerMaterial;
    }
    public void setCorrugatedId(Long corrugatedId)
    {
        this.corrugatedId = corrugatedId;
    }

    public Long getCorrugatedId()
    {
        return corrugatedId;
    }
    public void setSurfacePaper(String surfacePaper)
    {
        this.surfacePaper = surfacePaper;
    }

    public String getSurfacePaper()
    {
        return surfacePaper;
    }
    public void setBottomPaper(String bottomPaper)
    {
        this.bottomPaper = bottomPaper;
    }

    public String getBottomPaper()
    {
        return bottomPaper;
    }
    public void setCorePaper(String corePaper)
    {
        this.corePaper = corePaper;
    }

    public String getCorePaper()
    {
        return corePaper;
    }
    public void setCorrugatedPaper(String corrugatedPaper)
    {
        this.corrugatedPaper = corrugatedPaper;
    }

    public String getCorrugatedPaper()
    {
        return corrugatedPaper;
    }
    public void setOrgPaperPrice(BigDecimal orgPaperPrice)
    {
        this.orgPaperPrice = orgPaperPrice;
    }

    public BigDecimal getOrgPaperPrice()
    {
        return orgPaperPrice;
    }
    public void setCartonPrice(BigDecimal cartonPrice)
    {
        this.cartonPrice = cartonPrice;
    }

    public BigDecimal getCartonPrice()
    {
        return cartonPrice;
    }
    public void setCardboardPrice(BigDecimal cardboardPrice)
    {
        this.cardboardPrice = cardboardPrice;
    }

    public BigDecimal getCardboardPrice()
    {
        return cardboardPrice;
    }
    public void setSquareWeight(Integer squareWeight)
    {
        this.squareWeight = squareWeight;
    }

    public Integer getSquareWeight()
    {
        return squareWeight;
    }
    public void setEdgeStrength(String edgeStrength)
    {
        this.edgeStrength = edgeStrength;
    }

    public String getEdgeStrength()
    {
        return edgeStrength;
    }
    public void setBurstStrength(String burstStrength)
    {
        this.burstStrength = burstStrength;
    }

    public String getBurstStrength()
    {
        return burstStrength;
    }
    public void setWaterRate(BigDecimal waterRate)
    {
        this.waterRate = waterRate;
    }

    public BigDecimal getWaterRate()
    {
        return waterRate;
    }
    public void setStiffness(String stiffness)
    {
        this.stiffness = stiffness;
    }

    public String getStiffness()
    {
        return stiffness;
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
            .append("paperFormulaName", getPaperFormulaName())
            .append("customerMaterial", getCustomerMaterial())
            .append("corrugatedId", getCorrugatedId())
            .append("surfacePaper", getSurfacePaper())
            .append("bottomPaper", getBottomPaper())
            .append("corePaper", getCorePaper())
            .append("corrugatedPaper", getCorrugatedPaper())
            .append("orgPaperPrice", getOrgPaperPrice())
            .append("cartonPrice", getCartonPrice())
            .append("cardboardPrice", getCardboardPrice())
            .append("squareWeight", getSquareWeight())
            .append("edgeStrength", getEdgeStrength())
            .append("burstStrength", getBurstStrength())
            .append("waterRate", getWaterRate())
            .append("stiffness", getStiffness())
            .toString();
    }

    public String getCorrugatedName() {
        return corrugatedName;
    }

    public void setCorrugatedName(String corrugatedName) {
        this.corrugatedName = corrugatedName;
    }
}
