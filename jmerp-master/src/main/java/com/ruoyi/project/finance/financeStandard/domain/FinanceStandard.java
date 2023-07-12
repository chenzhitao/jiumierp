package com.ruoyi.project.finance.financeStandard.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 计费标准对象 finance_standard
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public class FinanceStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 工序 */
    @Excel(name = "工序")
    private String processName;
    private Long processId;

    /** 工作内容 */
    @Excel(name = "工作内容")
    private String jobContent;

    /** 计价类型 */
    @Excel(name = "计价类型", dictType = "finance_valuation_type")
    private String valuationType;

    /** 计算方式 */
    @Excel(name = "计算方式", dictType = "calculate_type")
    private String calculateType;

    /** 分配方式 */
    @Excel(name = "分配方式", dictType = "allocation_type")
    private String allocationType;

    /** 定量 */
    @Excel(name = "定量")
    private Integer equalQty;

    /** 折算定量 */
    @Excel(name = "折算定量")
    private Integer convertQty;

    /** 定量单价 */
    @Excel(name = "定量单价")
    private BigDecimal equalPrice;

    /** 超产单价 */
    @Excel(name = "超产单价")
    private BigDecimal abovePrice;

    /** 未超产单价 */
    @Excel(name = "未超产单价")
    private BigDecimal belowPrice;

    /** 分配比例(%) */
    @Excel(name = "分配比例(%)")
    private String scaleTeam;

    /** 分配单价 */
    @Excel(name = "分配单价")
    private String priceTeam;

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
    public void setJobContent(String jobContent)
    {
        this.jobContent = jobContent;
    }

    public String getJobContent()
    {
        return jobContent;
    }
    public void setValuationType(String valuationType)
    {
        this.valuationType = valuationType;
    }

    public String getValuationType()
    {
        return valuationType;
    }
    public void setCalculateType(String calculateType)
    {
        this.calculateType = calculateType;
    }

    public String getCalculateType()
    {
        return calculateType;
    }
    public void setAllocationType(String allocationType)
    {
        this.allocationType = allocationType;
    }

    public String getAllocationType()
    {
        return allocationType;
    }
    public void setEqualQty(Integer equalQty)
    {
        this.equalQty = equalQty;
    }

    public Integer getEqualQty()
    {
        return equalQty;
    }
    public void setConvertQty(Integer convertQty)
    {
        this.convertQty = convertQty;
    }

    public Integer getConvertQty()
    {
        return convertQty;
    }
    public void setEqualPrice(BigDecimal equalPrice)
    {
        this.equalPrice = equalPrice;
    }

    public BigDecimal getEqualPrice()
    {
        return equalPrice;
    }
    public void setAbovePrice(BigDecimal abovePrice)
    {
        this.abovePrice = abovePrice;
    }

    public BigDecimal getAbovePrice()
    {
        return abovePrice;
    }
    public void setBelowPrice(BigDecimal belowPrice)
    {
        this.belowPrice = belowPrice;
    }

    public BigDecimal getBelowPrice()
    {
        return belowPrice;
    }
    public void setScaleTeam(String scaleTeam)
    {
        this.scaleTeam = scaleTeam;
    }

    public String getScaleTeam()
    {
        return scaleTeam;
    }
    public void setPriceTeam(String priceTeam)
    {
        this.priceTeam = priceTeam;
    }

    public String getPriceTeam()
    {
        return priceTeam;
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
            .append("productId", getProductId())
            .append("processId", getProcessId())
            .append("jobContent", getJobContent())
            .append("valuationType", getValuationType())
            .append("calculateType", getCalculateType())
            .append("allocationType", getAllocationType())
            .append("equalQty", getEqualQty())
            .append("convertQty", getConvertQty())
            .append("equalPrice", getEqualPrice())
            .append("abovePrice", getAbovePrice())
            .append("belowPrice", getBelowPrice())
            .append("scaleTeam", getScaleTeam())
            .append("priceTeam", getPriceTeam())
            .toString();
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
}
