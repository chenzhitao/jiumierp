package com.ruoyi.project.warehouse.warehouseRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 出入库记录对象 warehouse_record
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class WarehouseRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseName;
    private Long warehouseId;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;
    private Long materialsId;

    /** 记录来源 */
    @Excel(name = "记录来源", dictType = "record_type")
    private String recordSource;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    private Date recordTime;

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
    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setMaterialsId(Long materialsId)
    {
        this.materialsId = materialsId;
    }

    public Long getMaterialsId()
    {
        return materialsId;
    }
    public void setRecordSource(String recordSource)
    {
        this.recordSource = recordSource;
    }

    public String getRecordSource()
    {
        return recordSource;
    }
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
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
            .append("warehouseId", getWarehouseId())
            .append("productId", getProductId())
            .append("materialsId", getMaterialsId())
            .append("recordSource", getRecordSource())
            .append("qty", getQty())
            .toString();
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
