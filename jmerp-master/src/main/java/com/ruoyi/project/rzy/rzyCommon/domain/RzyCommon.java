package com.ruoyi.project.rzy.rzyCommon.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 虚拟公共对象 rzy_common
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class RzyCommon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字符串1 */
    @Excel(name = "字符串1")
    private String strValue1;

    /** 字符串2 */
    @Excel(name = "字符串2")
    private String strValue2;

    /** 字符串3 */
    @Excel(name = "字符串3")
    private String strValue3;

    /** 字符串4 */
    @Excel(name = "字符串4")
    private String strValue4;

    /** 字符串5 */
    @Excel(name = "字符串5")
    private String strValue5;

    /** 字符串6 */
    @Excel(name = "字符串6")
    private String strValue6;

    /** 字符串7 */
    @Excel(name = "字符串7")
    private String strValue7;

    /** 字符串8 */
    @Excel(name = "字符串8")
    private String strValue8;

    /** 字符串9 */
    @Excel(name = "字符串9")
    private String strValue9;

    /** 数值1 */
    @Excel(name = "数值1")
    private Integer intValue1;

    /** 数值2 */
    @Excel(name = "数值2")
    private Integer intValue2;

    /** 数值3 */
    @Excel(name = "数值3")
    private Integer intValue3;

    /** 数值4 */
    @Excel(name = "数值4")
    private Integer intValue4;

    /** 数值5 */
    @Excel(name = "数值5")
    private Integer intValue5;

    /** 数值6 */
    @Excel(name = "数值6")
    private Integer intValue6;

    /** 数值7 */
    @Excel(name = "数值7")
    private Integer intValue7;

    /** 数值8 */
    @Excel(name = "数值8")
    private Integer intValue8;

    /** 数值9 */
    @Excel(name = "数值9")
    private Integer intValue9;

    /** 浮点数1 */
    @Excel(name = "浮点数1")
    private BigDecimal doubleValue1;

    /** 浮点数2 */
    @Excel(name = "浮点数2")
    private BigDecimal doubleValue2;

    /** 浮点数3 */
    @Excel(name = "浮点数3")
    private BigDecimal doubleValue3;

    /** 浮点数4 */
    @Excel(name = "浮点数4")
    private BigDecimal doubleValue4;

    /** 浮点数5 */
    @Excel(name = "浮点数5")
    private BigDecimal doubleValue5;

    /** 日期1 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期1", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateValue1;

    /** 日期2 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期2", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateValue2;

    /** 日期3 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期3", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateValue3;

    /** 日期4 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期4", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateValue4;

    /** 日期5 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期5", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateValue5;

    public void setStrValue1(String strValue1)
    {
        this.strValue1 = strValue1;
    }

    public String getStrValue1()
    {
        return strValue1;
    }
    public void setStrValue2(String strValue2)
    {
        this.strValue2 = strValue2;
    }

    public String getStrValue2()
    {
        return strValue2;
    }
    public void setStrValue3(String strValue3)
    {
        this.strValue3 = strValue3;
    }

    public String getStrValue3()
    {
        return strValue3;
    }
    public void setStrValue4(String strValue4)
    {
        this.strValue4 = strValue4;
    }

    public String getStrValue4()
    {
        return strValue4;
    }
    public void setStrValue5(String strValue5)
    {
        this.strValue5 = strValue5;
    }

    public String getStrValue5()
    {
        return strValue5;
    }
    public void setStrValue6(String strValue6)
    {
        this.strValue6 = strValue6;
    }

    public String getStrValue6()
    {
        return strValue6;
    }
    public void setStrValue7(String strValue7)
    {
        this.strValue7 = strValue7;
    }

    public String getStrValue7()
    {
        return strValue7;
    }
    public void setStrValue8(String strValue8)
    {
        this.strValue8 = strValue8;
    }

    public String getStrValue8()
    {
        return strValue8;
    }
    public void setStrValue9(String strValue9)
    {
        this.strValue9 = strValue9;
    }

    public String getStrValue9()
    {
        return strValue9;
    }
    public void setIntValue1(Integer intValue1)
    {
        this.intValue1 = intValue1;
    }

    public Integer getIntValue1()
    {
        return intValue1;
    }
    public void setIntValue2(Integer intValue2)
    {
        this.intValue2 = intValue2;
    }

    public Integer getIntValue2()
    {
        return intValue2;
    }
    public void setIntValue3(Integer intValue3)
    {
        this.intValue3 = intValue3;
    }

    public Integer getIntValue3()
    {
        return intValue3;
    }
    public void setIntValue4(Integer intValue4)
    {
        this.intValue4 = intValue4;
    }

    public Integer getIntValue4()
    {
        return intValue4;
    }
    public void setIntValue5(Integer intValue5)
    {
        this.intValue5 = intValue5;
    }

    public Integer getIntValue5()
    {
        return intValue5;
    }
    public void setIntValue6(Integer intValue6)
    {
        this.intValue6 = intValue6;
    }

    public Integer getIntValue6()
    {
        return intValue6;
    }
    public void setIntValue7(Integer intValue7)
    {
        this.intValue7 = intValue7;
    }

    public Integer getIntValue7()
    {
        return intValue7;
    }
    public void setIntValue8(Integer intValue8)
    {
        this.intValue8 = intValue8;
    }

    public Integer getIntValue8()
    {
        return intValue8;
    }
    public void setIntValue9(Integer intValue9)
    {
        this.intValue9 = intValue9;
    }

    public Integer getIntValue9()
    {
        return intValue9;
    }
    public void setDoubleValue1(BigDecimal doubleValue1)
    {
        this.doubleValue1 = doubleValue1;
    }

    public BigDecimal getDoubleValue1()
    {
        return doubleValue1;
    }
    public void setDoubleValue2(BigDecimal doubleValue2)
    {
        this.doubleValue2 = doubleValue2;
    }

    public BigDecimal getDoubleValue2()
    {
        return doubleValue2;
    }
    public void setDoubleValue3(BigDecimal doubleValue3)
    {
        this.doubleValue3 = doubleValue3;
    }

    public BigDecimal getDoubleValue3()
    {
        return doubleValue3;
    }
    public void setDoubleValue4(BigDecimal doubleValue4)
    {
        this.doubleValue4 = doubleValue4;
    }

    public BigDecimal getDoubleValue4()
    {
        return doubleValue4;
    }
    public void setDoubleValue5(BigDecimal doubleValue5)
    {
        this.doubleValue5 = doubleValue5;
    }

    public BigDecimal getDoubleValue5()
    {
        return doubleValue5;
    }
    public void setDateValue1(Date dateValue1)
    {
        this.dateValue1 = dateValue1;
    }

    public Date getDateValue1()
    {
        return dateValue1;
    }
    public void setDateValue2(Date dateValue2)
    {
        this.dateValue2 = dateValue2;
    }

    public Date getDateValue2()
    {
        return dateValue2;
    }
    public void setDateValue3(Date dateValue3)
    {
        this.dateValue3 = dateValue3;
    }

    public Date getDateValue3()
    {
        return dateValue3;
    }
    public void setDateValue4(Date dateValue4)
    {
        this.dateValue4 = dateValue4;
    }

    public Date getDateValue4()
    {
        return dateValue4;
    }
    public void setDateValue5(Date dateValue5)
    {
        this.dateValue5 = dateValue5;
    }

    public Date getDateValue5()
    {
        return dateValue5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("strValue1", getStrValue1())
            .append("strValue2", getStrValue2())
            .append("strValue3", getStrValue3())
            .append("strValue4", getStrValue4())
            .append("strValue5", getStrValue5())
            .append("strValue6", getStrValue6())
            .append("strValue7", getStrValue7())
            .append("strValue8", getStrValue8())
            .append("strValue9", getStrValue9())
            .append("intValue1", getIntValue1())
            .append("intValue2", getIntValue2())
            .append("intValue3", getIntValue3())
            .append("intValue4", getIntValue4())
            .append("intValue5", getIntValue5())
            .append("intValue6", getIntValue6())
            .append("intValue7", getIntValue7())
            .append("intValue8", getIntValue8())
            .append("intValue9", getIntValue9())
            .append("doubleValue1", getDoubleValue1())
            .append("doubleValue2", getDoubleValue2())
            .append("doubleValue3", getDoubleValue3())
            .append("doubleValue4", getDoubleValue4())
            .append("doubleValue5", getDoubleValue5())
            .append("dateValue1", getDateValue1())
            .append("dateValue2", getDateValue2())
            .append("dateValue3", getDateValue3())
            .append("dateValue4", getDateValue4())
            .append("dateValue5", getDateValue5())
            .toString();
    }
}
