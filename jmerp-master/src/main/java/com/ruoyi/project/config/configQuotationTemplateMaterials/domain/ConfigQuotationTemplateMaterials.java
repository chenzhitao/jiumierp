package com.ruoyi.project.config.configQuotationTemplateMaterials.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 报价工艺卡材料对象 config_quotation_template_materials
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigQuotationTemplateMaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 报价工艺卡 */
    @Excel(name = "报价工艺卡")
    private String quotationTemplateName;
    private Long quotationTemplateId;

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;
    private Long materialsId;

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

    /** 取工序数量 */
    @Excel(name = "取工序数量", dictType = "sys_yes_no")
    private String isGetProcessQty;

    /** 损耗率(%) */
    @Excel(name = "损耗率(%)")
    private BigDecimal lossRate;

    /** 固定损耗数 */
    @Excel(name = "固定损耗数")
    private Integer lossQty;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setQuotationTemplateId(Long quotationTemplateId)
    {
        this.quotationTemplateId = quotationTemplateId;
    }

    public Long getQuotationTemplateId()
    {
        return quotationTemplateId;
    }
    public void setMaterialsId(Long materialsId)
    {
        this.materialsId = materialsId;
    }

    public Long getMaterialsId()
    {
        return materialsId;
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
    public void setIsGetProcessQty(String isGetProcessQty)
    {
        this.isGetProcessQty = isGetProcessQty;
    }

    public String getIsGetProcessQty()
    {
        return isGetProcessQty;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("quotationTemplateId", getQuotationTemplateId())
            .append("materialsId", getMaterialsId())
            .append("processId", getProcessId())
            .append("formulaId", getFormulaId())
            .append("times", getTimes())
            .append("price", getPrice())
            .append("isGetProcessQty", getIsGetProcessQty())
            .append("lossRate", getLossRate())
            .append("lossQty", getLossQty())
            .toString();
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
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

    public String getQuotationTemplateName() {
        return quotationTemplateName;
    }

    public void setQuotationTemplateName(String quotationTemplateName) {
        this.quotationTemplateName = quotationTemplateName;
    }
}
