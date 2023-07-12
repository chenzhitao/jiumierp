package com.ruoyi.project.config.configCorrugated.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 楞型配置对象 config_corrugated
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigCorrugated extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 楞型名称 */
    @Excel(name = "楞型名称")
    private String corrugatedName;

    /** 楞率 */
    @Excel(name = "楞率")
    private BigDecimal corrugatedRate;

    /** 楞高 */
    @Excel(name = "楞高")
    private BigDecimal corrugatedHigh;

    /** 纸板层数 */
    @Excel(name = "纸板层数")
    private Integer boardLevel;

    /** 报价系数 */
    @Excel(name = "报价系数")
    private BigDecimal priceCoefficient;

    /** 装载系数 */
    @Excel(name = "装载系数")
    private BigDecimal loadCoefficient;

    /** 运费系数 */
    @Excel(name = "运费系数")
    private BigDecimal freightCoefficient;

    /** 实际楞序 */
    @Excel(name = "实际楞序")
    private String corrugatedSeq;

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
    public void setCorrugatedName(String corrugatedName)
    {
        this.corrugatedName = corrugatedName;
    }

    public String getCorrugatedName()
    {
        return corrugatedName;
    }
    public void setCorrugatedRate(BigDecimal corrugatedRate)
    {
        this.corrugatedRate = corrugatedRate;
    }

    public BigDecimal getCorrugatedRate()
    {
        return corrugatedRate;
    }
    public void setCorrugatedHigh(BigDecimal corrugatedHigh)
    {
        this.corrugatedHigh = corrugatedHigh;
    }

    public BigDecimal getCorrugatedHigh()
    {
        return corrugatedHigh;
    }
    public void setBoardLevel(Integer boardLevel)
    {
        this.boardLevel = boardLevel;
    }

    public Integer getBoardLevel()
    {
        return boardLevel;
    }
    public void setPriceCoefficient(BigDecimal priceCoefficient)
    {
        this.priceCoefficient = priceCoefficient;
    }

    public BigDecimal getPriceCoefficient()
    {
        return priceCoefficient;
    }
    public void setLoadCoefficient(BigDecimal loadCoefficient)
    {
        this.loadCoefficient = loadCoefficient;
    }

    public BigDecimal getLoadCoefficient()
    {
        return loadCoefficient;
    }
    public void setFreightCoefficient(BigDecimal freightCoefficient)
    {
        this.freightCoefficient = freightCoefficient;
    }

    public BigDecimal getFreightCoefficient()
    {
        return freightCoefficient;
    }
    public void setCorrugatedSeq(String corrugatedSeq)
    {
        this.corrugatedSeq = corrugatedSeq;
    }

    public String getCorrugatedSeq()
    {
        return corrugatedSeq;
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
            .append("corrugatedName", getCorrugatedName())
            .append("corrugatedRate", getCorrugatedRate())
            .append("corrugatedHigh", getCorrugatedHigh())
            .append("boardLevel", getBoardLevel())
            .append("priceCoefficient", getPriceCoefficient())
            .append("loadCoefficient", getLoadCoefficient())
            .append("freightCoefficient", getFreightCoefficient())
            .append("corrugatedSeq", getCorrugatedSeq())
            .toString();
    }
}
