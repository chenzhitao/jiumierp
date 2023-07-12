package com.ruoyi.project.config.configCutterDie.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 刀模配置对象 config_cutter_die
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigCutterDie extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 刀模名称 */
    @Excel(name = "刀模名称")
    private String cutterDieName;

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseName;
    private Long warehouseId;

    /** 货架号 */
    @Excel(name = "货架号")
    private String shelvesNo;

    /** 刀模规格 */
    @Excel(name = "刀模规格")
    private String cutterDieSize;

    /** 客户 */
    @Excel(name = "客户")
    private String customerName;
    private Long customerId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

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
    public void setCutterDieName(String cutterDieName)
    {
        this.cutterDieName = cutterDieName;
    }

    public String getCutterDieName()
    {
        return cutterDieName;
    }
    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }
    public void setShelvesNo(String shelvesNo)
    {
        this.shelvesNo = shelvesNo;
    }

    public String getShelvesNo()
    {
        return shelvesNo;
    }
    public void setCutterDieSize(String cutterDieSize)
    {
        this.cutterDieSize = cutterDieSize;
    }

    public String getCutterDieSize()
    {
        return cutterDieSize;
    }
    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId()
    {
        return customerId;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
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
            .append("cutterDieName", getCutterDieName())
            .append("warehouseId", getWarehouseId())
            .append("shelvesNo", getShelvesNo())
            .append("cutterDieSize", getCutterDieSize())
            .append("customerId", getCustomerId())
            .append("productId", getProductId())
            .toString();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
