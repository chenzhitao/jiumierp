package com.ruoyi.project.config.configQuotationTemplate.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 报价工艺卡对象 config_quotation_template
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigQuotationTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 工艺卡名称 */
    @Excel(name = "工艺卡名称")
    private String templateName;

    /** 工艺卡要求 */
    @Excel(name = "工艺卡要求")
    private String requirements;

    private String processArr;
    private String materialsArr;

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
    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getTemplateName()
    {
        return templateName;
    }
    public void setRequirements(String requirements)
    {
        this.requirements = requirements;
    }

    public String getRequirements()
    {
        return requirements;
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
            .append("templateName", getTemplateName())
            .append("requirements", getRequirements())
            .toString();
    }

    public String getProcessArr() {
        return processArr;
    }

    public void setProcessArr(String processArr) {
        this.processArr = processArr;
    }

    public String getMaterialsArr() {
        return materialsArr;
    }

    public void setMaterialsArr(String materialsArr) {
        this.materialsArr = materialsArr;
    }
}
