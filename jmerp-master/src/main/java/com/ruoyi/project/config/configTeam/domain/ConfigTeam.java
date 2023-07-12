package com.ruoyi.project.config.configTeam.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 班组配置对象 config_team
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 班组名称 */
    @Excel(name = "班组名称")
    private String teamName;

    /** 设备 */
    @Excel(name = "设备")
    private String equipmentName;
    private Long equipmentId;

    /** 标准产量 */
    @Excel(name = "标准产量")
    private Integer standardOutput;

    /** 未脱产单价 */
    @Excel(name = "未脱产单价")
    private BigDecimal lessPrice;

    /** 超产单价 */
    @Excel(name = "超产单价")
    private BigDecimal morePrice;

    @Excel(name = "组员")
    private String members;


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
    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public String getTeamName()
    {
        return teamName;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setStandardOutput(Integer standardOutput)
    {
        this.standardOutput = standardOutput;
    }

    public Integer getStandardOutput()
    {
        return standardOutput;
    }
    public void setLessPrice(BigDecimal lessPrice)
    {
        this.lessPrice = lessPrice;
    }

    public BigDecimal getLessPrice()
    {
        return lessPrice;
    }
    public void setMorePrice(BigDecimal morePrice)
    {
        this.morePrice = morePrice;
    }

    public BigDecimal getMorePrice()
    {
        return morePrice;
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
            .append("teamName", getTeamName())
            .append("equipmentId", getEquipmentId())
            .append("standardOutput", getStandardOutput())
            .append("lessPrice", getLessPrice())
            .append("morePrice", getMorePrice())
            .toString();
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }
}
