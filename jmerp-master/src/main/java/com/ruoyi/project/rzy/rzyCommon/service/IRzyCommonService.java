package com.ruoyi.project.rzy.rzyCommon.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.rzy.rzyCommon.domain.RzyCommon;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 虚拟公共Service接口
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public interface IRzyCommonService 
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
     * 批量删除虚拟公共
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRzyCommonByIds(String ids);

    /**
     * 删除虚拟公共信息
     * 
     * @param strValue1 虚拟公共ID
     * @return 结果
     */
    public int deleteRzyCommonById(String strValue1);

    /**
     * 获取安全吗
     * @Author 方舟
     * @Date 2021/5/7 14:58:14
    **/
    public String selectSecurityCode();

    /**
     * 输入注册码
     * @Author 方舟
     * @Date 2021/5/8 9:51:17
     **/
    public RzyCommon inputCode(RzyCommon rzyCommon);

    /**
     * 系统信息
     * @Author 方舟
     * @Date 2021/7/19 9:57:09
     **/
    public List<RzyCommon> selectSysInfo();

    /**
     * 首页看板数据
     * @Author 方舟
     * @Date 2021/5/8 9:51:17
     **/
    public List<RzyCommon> mainBoardData(RzyCommon rzyCommon);

    /**
     * 获取文档明细
     * @Author 方舟
     * @Date 2021/5/8 9:51:17
     **/
    public RzyCommon getDocumentsDetail(RzyCommon rzyCommon);

    /**
     * 纸张尺寸
     * @Author 方舟
     * @Date 2021/6/24 10:57:21
    **/
    public List<RzyCommon> getFullPaperSizeList(RzyCommon rzyCommon);

    /**
     * 清理N天前日志
     */
    public void clearLogs(Integer loginDays, Integer operDays);

}
