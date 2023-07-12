package com.ruoyi.project.config.configQuotationTemplateProcess.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 报价工艺卡工序对象 config_quotation_template_process
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigQuotationTemplateProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 报价工艺卡 */
    @Excel(name = "报价工艺卡")
    private String quotationTemplateName;
    private Long quotationTemplateId;

    /** 工序 */
    @Excel(name = "工序")
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
    private String equipmentName;
    private Long equipmentId;

    /** 损耗率(%) */
    @Excel(name = "损耗率(%)")
    private BigDecimal lossRate;

    /** 固定损耗数 */
    @Excel(name = "固定损耗数")
    private Integer lossQty;

    /** 固定损耗数 */
    @Excel(name = "倍率计算", dictType = "sys_yes_no")
    private String isTimeCount;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer processOrder;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("quotationTemplateId", getQuotationTemplateId())
            .append("processId", getProcessId())
            .append("formulaId", getFormulaId())
            .append("times", getTimes())
            .append("price", getPrice())
            .append("equipmentId", getEquipmentId())
            .append("lossRate", getLossRate())
            .append("lossQty", getLossQty())
            .toString();
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

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getProcessOrder() {
        return processOrder;
    }

    public void setProcessOrder(Integer processOrder) {
        this.processOrder = processOrder;
    }

    public String getQuotationTemplateName() {
        return quotationTemplateName;
    }

    public void setQuotationTemplateName(String quotationTemplateName) {
        this.quotationTemplateName = quotationTemplateName;
    }

    public String getIsTimeCount() {
        return isTimeCount;
    }

    public void setIsTimeCount(String isTimeCount) {
        this.isTimeCount = isTimeCount;
    }
}
