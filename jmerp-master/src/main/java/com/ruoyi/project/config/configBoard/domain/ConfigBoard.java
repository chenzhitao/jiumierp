package com.ruoyi.project.config.configBoard.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 板材配置对象 config_board
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigBoard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 板材名称 */
    @Excel(name = "板材名称")
    private String boardName;

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseName;
    private Long warehouseId;

    /** 货架号 */
    @Excel(name = "货架号")
    private String shelvesNo;

    /** 刀模规格 */
    @Excel(name = "刀模规格")
    private String boardSize;

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
    public void setBoardName(String boardName)
    {
        this.boardName = boardName;
    }

    public String getBoardName()
    {
        return boardName;
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
    public void setBoardSize(String boardSize)
    {
        this.boardSize = boardSize;
    }

    public String getBoardSize()
    {
        return boardSize;
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
            .append("boardName", getBoardName())
            .append("warehouseId", getWarehouseId())
            .append("shelvesNo", getShelvesNo())
            .append("boardSize", getBoardSize())
            .append("customerId", getCustomerId())
            .append("productId", getProductId())
            .toString();
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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
}
