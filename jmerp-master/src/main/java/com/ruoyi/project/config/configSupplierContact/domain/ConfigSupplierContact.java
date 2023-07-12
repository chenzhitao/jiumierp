package com.ruoyi.project.config.configSupplierContact.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 供应商联系人对象 config_supplier_contact
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigSupplierContact extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 供应商 */
    @Excel(name = "供应商")
    private Long supplierId;

    /** 默认联系人 */
    @Excel(name = "默认联系人")
    private String isDefault;

    /** 联系人名称 */
    @Excel(name = "联系人名称")
    private String contactName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String cellPhone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** QQ号 */
    @Excel(name = "QQ号")
    private String qqNo;

    /** 微信 */
    @Excel(name = "微信")
    private String wechat;

    /** 开票单位 */
    @Excel(name = "开票单位")
    private String invoiceUnit;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

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
    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }
    public void setIsDefault(String isDefault)
    {
        this.isDefault = isDefault;
    }

    public String getIsDefault()
    {
        return isDefault;
    }
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactName()
    {
        return contactName;
    }
    public void setCellPhone(String cellPhone)
    {
        this.cellPhone = cellPhone;
    }

    public String getCellPhone()
    {
        return cellPhone;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setQqNo(String qqNo)
    {
        this.qqNo = qqNo;
    }

    public String getQqNo()
    {
        return qqNo;
    }
    public void setWechat(String wechat)
    {
        this.wechat = wechat;
    }

    public String getWechat()
    {
        return wechat;
    }
    public void setInvoiceUnit(String invoiceUnit)
    {
        this.invoiceUnit = invoiceUnit;
    }

    public String getInvoiceUnit()
    {
        return invoiceUnit;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("supplierId", getSupplierId())
            .append("isDefault", getIsDefault())
            .append("contactName", getContactName())
            .append("cellPhone", getCellPhone())
            .append("email", getEmail())
            .append("qqNo", getQqNo())
            .append("wechat", getWechat())
            .append("invoiceUnit", getInvoiceUnit())
            .append("address", getAddress())
            .toString();
    }
}
