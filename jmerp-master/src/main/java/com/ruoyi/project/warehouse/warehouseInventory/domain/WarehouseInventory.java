package com.ruoyi.project.warehouse.warehouseInventory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 库存统计对象 warehouse_inventory
 * 
 * @author fangzhou
 * @date 2021-04-20
 */
public class WarehouseInventory extends BaseEntity
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

    /** 物品类型 */
    @Excel(name = "仓库类型", dictType = "warehouse_type")
    private String warehouseType;

    /** 产品 */
    @Excel(name = "产品")
    private String productName;
    private Long productId;

    /** 材料 */
    @Excel(name = "材料")
    private String materialsName;
    private Long materialsId;

    /** 物品名称 */
    @Excel(name = "物品描述")
    private String goodsName;
    private String goodsType;

    /** 数量 */
    @Excel(name = "数量")
    private Integer qty;

    /** 不可用数量 */
    @Excel(name = "不可用数量")
    private Integer unusedQty;

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

    public String getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(String warehouseType) {
        this.warehouseType = warehouseType;
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
    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }

    public Integer getQty()
    {
        return qty;
    }
    public void setUnusedQty(Integer unusedQty)
    {
        this.unusedQty = unusedQty;
    }

    public Integer getUnusedQty()
    {
        return unusedQty;
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
            .append("warehouseType", getWarehouseType())
            .append("productId", getProductId())
            .append("materialsId", getMaterialsId())
            .append("goodsName", getGoodsName())
            .append("qty", getQty())
            .append("unusedQty", getUnusedQty())
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }
}
