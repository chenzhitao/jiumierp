package com.ruoyi.framework.web.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String rzyValue1;
    private String rzyValue2;
    private String ids;
    private String rzyUpdate = "";
    private String rzyCustomer = "";
    private Long rzyUserId;

    /** 搜索值 */
    private String searchValue;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 请求参数 */
    private Map<String, Object> params;

    public String getSearchValue()
    {
        return searchValue;
    }

    public void setSearchValue(String searchValue)
    {
        this.searchValue = searchValue;
    }

    public String getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<String, Object>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }

    public String getRzyValue1() {
        return rzyValue1;
    }

    public void setRzyValue1(String rzyValue1) {
        this.rzyValue1 = rzyValue1;
    }

    public String getRzyValue2() {
        return rzyValue2;
    }

    public void setRzyValue2(String rzyValue2) {
        this.rzyValue2 = rzyValue2;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Long getRzyUserId() {
        return rzyUserId;
    }

    public void setRzyUserId(Long rzyUserId) {
        this.rzyUserId = rzyUserId;
    }

    public String getRzyUpdate() {
        return rzyUpdate;
    }

    public void setRzyUpdate(String rzyUpdate) {
        this.rzyUpdate = rzyUpdate;
    }

    public String getRzyCustomer() {
        return rzyCustomer;
    }

    public void setRzyCustomer(String rzyCustomer) {
        this.rzyCustomer = rzyCustomer;
    }
}
