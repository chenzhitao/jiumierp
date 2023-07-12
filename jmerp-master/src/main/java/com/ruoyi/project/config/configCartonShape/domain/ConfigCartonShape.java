package com.ruoyi.project.config.configCartonShape.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 箱型配置对象 config_carton_shape
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigCartonShape extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 箱型名称 */
    @Excel(name = "箱型名称")
    private String cartonShapeName;

    /** 箱型图片 */
    @Excel(name = "箱型图片")
    private String cartonShapeImg;

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
    public void setCartonShapeName(String cartonShapeName)
    {
        this.cartonShapeName = cartonShapeName;
    }

    public String getCartonShapeName()
    {
        return cartonShapeName;
    }
    public void setCartonShapeImg(String cartonShapeImg)
    {
        this.cartonShapeImg = cartonShapeImg;
    }

    public String getCartonShapeImg()
    {
        return cartonShapeImg;
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
            .append("cartonShapeName", getCartonShapeName())
            .append("cartonShapeImg", getCartonShapeImg())
            .toString();
    }
}
