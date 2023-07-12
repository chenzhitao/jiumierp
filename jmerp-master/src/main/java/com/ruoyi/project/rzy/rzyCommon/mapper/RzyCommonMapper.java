package com.ruoyi.project.rzy.rzyCommon.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.project.rzy.rzyCommon.domain.RzyCommon;
import org.apache.ibatis.annotations.Param;

/**
 * 虚拟公共Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface RzyCommonMapper 
{
    /**
     * 查询虚拟公共
     * 
     * @param strValue1 虚拟公共ID
     * @return 虚拟公共
     */
    public RzyCommon selectRzyCommonById(String strValue1);

    /**
     * 查询虚拟公共列表
     * 
     * @param rzyCommon 虚拟公共
     * @return 虚拟公共集合
     */
    public List<RzyCommon> selectRzyCommonList(RzyCommon rzyCommon);

    /**
     * 新增虚拟公共
     * 
     * @param rzyCommon 虚拟公共
     * @return 结果
     */
    public int insertRzyCommon(RzyCommon rzyCommon);

    /**
     * 修改虚拟公共
     * 
     * @param rzyCommon 虚拟公共
     * @return 结果
     */
    public int updateRzyCommon(RzyCommon rzyCommon);

    /**
     * 删除虚拟公共
     * 
     * @param strValue1 虚拟公共ID
     * @return 结果
     */
    public int deleteRzyCommonById(String strValue1);

    /**
     * 批量删除虚拟公共
     * 
     * @param strValue1s 需要删除的数据ID
     * @return 结果
     */
    public int deleteRzyCommonByIds(String[] strValue1s);

    /**
     * 根据名称找id(导入)
     * @Author 方舟
     * @Date 2021/4/13 21:11:17
    **/
    public Long findIdByName(@Param("paramValue") String paramValue, @Param("tableName") String tableName, @Param("incolumn") String incolumn, @Param("outcolumn") String outcolumn);
    public BigDecimal findRateByName(@Param("paramValue") String paramValue, @Param("tableName") String tableName, @Param("incolumn") String incolumn, @Param("outcolumn") String outcolumn);
    public int countRepeat(@Param("paramValue") String paramValue, @Param("tableName") String tableName, @Param("incolumn") String incolumn);

    /**
     * 找到数据字典默认值
     * @Author 方舟
     * @Date 2021/4/13 21:11:17
     **/
    public String findDefaultDictValue(@Param("dictType") String dictType);

    /**
     * 获取安全吗
     * @Author 方舟
     * @Date 2021/5/7 14:58:14
     **/
    public String selectSecurityCode();

    /**
     * 获取安全吗
     * @Author 方舟
     * @Date 2021/5/7 14:58:14
     **/
    public int insertCode(@Param("code") String code);
    public int insertDate(@Param("code") String code);
    public List<RzyCommon> selectSysInfo();

    /**
     * 查询看板数据
     * @Author 方舟
     * @Date 2021/6/3 12:11:49
    **/
    public List<RzyCommon> selectMainBoardList(RzyCommon rzyCommon);

    /**
     * 查询单据基本信息
     * @Author 方舟
     * @Date 2021/6/10 10:56:36
    **/
    public RzyCommon selectDocumentById(RzyCommon rzyCommon);
    public RzyCommon selectDocumentBySN(RzyCommon rzyCommon);


    /**
     * 纸张尺寸
     * @Author 方舟
     * @Date 2021/6/24 10:57:21
     **/
    public List<RzyCommon> getFullPaperSizeList(RzyCommon rzyCommon);

    /**
     * 清理N天前日志
     * @param days
     */
    public void clearOperLogs(@Param("days") Integer days);
    public void clearLoginLogs(@Param("days") Integer days);
}
