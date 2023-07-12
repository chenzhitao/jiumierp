package com.ruoyi.project.outsource.outsourceOrderMaterials.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 外发加工带料对象 outsource_order_materials
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public class OutsourceOrderMaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 外发加工 */
    @Excel(name = "外发加工")
    private Long outsourceOrderId;

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;
    private Long materialsId;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    /** 退回数量 */
    @Excel(name = "退回数量")
    private Integer returnQty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 规格长 */
    @Excel(name = "规格长")
    private Integer sizeLong;

    /** 规格宽 */
    @Excel(name = "规格宽")
    private Integer sizeWidth;

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseName;
    private Long warehouseId;

    /** 带料日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "带料日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOutsourceOrderId(Long outsourceOrderId)
    {
        this.outsourceOrderId = outsourceOrderId;
    }

    public Long getOutsourceOrderId()
    {
        return outsourceOrderId;
    }
    public void setMaterialsId(Long materialsId)
    {
        this.materialsId = materialsId;
    }

    public Long getMaterialsId()
    {
        return materialsId;
    }
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
    }
    public void setReturnQty(Integer returnQty)
    {
        this.returnQty = returnQty;
    }

    public Integer getReturnQty()
    {
        return returnQty;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setSizeLong(Integer sizeLong)
    {
        this.sizeLong = sizeLong;
    }

    public Integer getSizeLong()
    {
        return sizeLong;
    }
    public void setSizeWidth(Integer sizeWidth)
    {
        this.sizeWidth = sizeWidth;
    }

    public Integer getSizeWidth()
    {
        return sizeWidth;
    }
    public void setWarehouseId(Long warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId()
    {
        return warehouseId;
    }
    public void setDeliveryDate(Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("outsourceOrderId", getOutsourceOrderId())
            .append("materialsId", getMaterialsId())
            .append("qty", getQty())
            .append("returnQty", getReturnQty())
            .append("price", getPrice())
            .append("sizeLong", getSizeLong())
            .append("sizeWidth", getSizeWidth())
            .append("warehouseId", getWarehouseId())
            .append("deliveryDate", getDeliveryDate())
            .toString();
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
