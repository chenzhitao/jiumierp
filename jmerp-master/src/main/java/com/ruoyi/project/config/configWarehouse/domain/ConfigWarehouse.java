package com.ruoyi.project.config.configWarehouse.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 仓库配置对象 config_warehouse
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigWarehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String warehouseName;

    /** 仓库类型 */
    @Excel(name = "仓库类型", dictType = "warehouse_type")
    private String warehouseType;

    /** 地址 */
    @Excel(name = "地址")
    private String warehouseAddress;

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
    public void setWarehouseName(String warehouseName)
    {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseName()
    {
        return warehouseName;
    }
    public void setWarehouseType(String warehouseType)
    {
        this.warehouseType = warehouseType;
    }

    public String getWarehouseType()
    {
        return warehouseType;
    }
    public void setWarehouseAddress(String warehouseAddress)
    {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseAddress()
    {
        return warehouseAddress;
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
            .append("warehouseName", getWarehouseName())
            .append("warehouseType", getWarehouseType())
            .append("warehouseAddress", getWarehouseAddress())
            .toString();
    }
}
