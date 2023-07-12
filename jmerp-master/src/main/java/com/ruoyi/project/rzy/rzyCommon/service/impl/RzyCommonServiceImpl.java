package com.ruoyi.project.rzy.rzyCommon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.rzy.DESUtil;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.rzy.rzyCommon.mapper.RzyCommonMapper;
import com.ruoyi.project.rzy.rzyCommon.domain.RzyCommon;
import com.ruoyi.project.rzy.rzyCommon.service.IRzyCommonService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 虚拟公共Service业务层处理
 *
 * @author fangzhou
 * @date 2021-04-13
 */
@Service
public class RzyCommonServiceImpl implements IRzyCommonService
{
    @Autowired
    private RzyCommonMapper rzyCommonMapper;

    /**
     * 查询虚拟公共
     *
     * @param strValue1 虚拟公共ID
     * @return 虚拟公共
     */
    @Override
    public RzyCommon selectRzyCommonById(String strValue1)
    {
        return rzyCommonMapper.selectRzyCommonById(strValue1);
    }

    /**
     * 查询虚拟公共列表
     *
     * @param rzyCommon 虚拟公共
     * @return 虚拟公共
     */
    @Override
    public List<RzyCommon> selectRzyCommonList(RzyCommon rzyCommon)
    {
        return rzyCommonMapper.selectRzyCommonList(rzyCommon);
    }

    /**
     * 新增虚拟公共
     *
     * @param rzyCommon 虚拟公共
     * @return 结果
     */
    @Override
    public int insertRzyCommon(RzyCommon rzyCommon)
    {
        return rzyCommonMapper.insertRzyCommon(rzyCommon);
    }

    /**
     * 修改虚拟公共
     *
     * @param rzyCommon 虚拟公共
     * @return 结果
     */
    @Override
    public int updateRzyCommon(RzyCommon rzyCommon)
    {
        return rzyCommonMapper.updateRzyCommon(rzyCommon);
    }

    /**
     * 删除虚拟公共对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRzyCommonByIds(String ids)
    {
        return rzyCommonMapper.deleteRzyCommonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除虚拟公共信息
     *
     * @param strValue1 虚拟公共ID
     * @return 结果
     */
    @Override
    public int deleteRzyCommonById(String strValue1)
    {
        return rzyCommonMapper.deleteRzyCommonById(strValue1);
    }

    /**
     * 获取安全吗
     * @Author 方舟
     * @Date 2021/5/7 14:58:14
     **/
    @Override
    public String selectSecurityCode(){
        String securityCode = rzyCommonMapper.selectSecurityCode();
        String result = "N";
        String key = "jmerp413";
        String code = "";
        Date yesterday = new Date(new Date().getTime() - (24*3600*1000));
        try{
            code = DESUtil.decrypt(key,securityCode);
        }catch (Exception e){
            result = "E";
        }
        Date codeDate = new Date();
        try {
            codeDate = DateUtils.dateTime("yyyyMMdd",code);
        }catch (Exception e){
            result = "E";
        }
        if(codeDate.after(yesterday)){
            result = "Y";
        }
        Long gap = codeDate.getTime() - yesterday.getTime();
        Long gapDays = gap/(24*3600*1000);
        Integer gapInt = gapDays.intValue();
        CacheUtils.put("SysExpire",gapInt);
        return result;
    }

    /**
     * 输入注册码
     * @Author 方舟
     * @Date 2021/5/8 9:51:17
     **/
    @Override
    public RzyCommon inputCode(RzyCommon rzyCommon){
        String code = rzyCommon.getStrValue1();
        if(null==code){
            code = "";
        }
        if(code.length()==22){
            code += "==";
        }
        String key = "jmerp413";
        try {
            String mcode = DESUtil.decrypt(key,code);
            if(mcode.equals(code)){
                rzyCommon.setStrValue2("输入的注册码无效，请重新输入");
            }else{
                Date codeDate = DateUtils.dateTime("yyyyMMdd",mcode);
                if(new Date().after(codeDate)){
                    String datestr = DateUtils.parseDateToStr("yyyy-MM-dd",codeDate);
                    rzyCommon.setStrValue2("注册码在 "+datestr+" 已过期，请重新输入");
                }else{
                    String datestr = DateUtils.parseDateToStr("yyyy-MM-dd",codeDate);
                    rzyCommon.setStrValue2("注册成功，有效期到 "+datestr);
                    rzyCommonMapper.insertCode(code);
                    rzyCommonMapper.insertDate(datestr);
                }
            }
        }catch (Exception e){
            rzyCommon.setStrValue2("输入的注册码无效，请重新输入");
        }
        return rzyCommon;
    }

    /**
     * 系统信息
     * @Author 方舟
     * @Date 2021/7/19 9:57:09
    **/
    @Override
    public List<RzyCommon> selectSysInfo(){
        List<RzyCommon> resultList = rzyCommonMapper.selectSysInfo();
        return resultList;
    }

    /**
     * 首页看板数据
     * @Author 方舟
     * @Date 2021/5/8 9:51:17
     **/
    @Override
    public List<RzyCommon> mainBoardData(RzyCommon rzyCommon){
        List<RzyCommon> resultList = rzyCommonMapper.selectMainBoardList(rzyCommon);
        return resultList;
    }

    /**
     * 获取文档明细
     * @Author 方舟
     * @Date 2021/5/8 9:51:17
     **/
    @Override
    public RzyCommon getDocumentsDetail(RzyCommon rzyCommon){
        RzyCommon resultVO = new RzyCommon();
        if(!StringUtils.isEmpty(rzyCommon.getStrValue1())){
            if("Q".equals(rzyCommon.getStrValue1())){
                resultVO = rzyCommonMapper.selectDocumentById(rzyCommon);
            }
            if("B".equals(rzyCommon.getStrValue1())){
                resultVO = rzyCommonMapper.selectDocumentBySN(rzyCommon);
            }
        }
        return resultVO;
    }

    /**
     * 纸张尺寸
     * @Author 方舟
     * @Date 2021/6/24 10:57:21
     **/
    @Override
    public List<RzyCommon> getFullPaperSizeList(RzyCommon rzyCommon){
        return rzyCommonMapper.getFullPaperSizeList(rzyCommon);
    }

    /**
     * 清理N天前日志
     */
    @Override
    public void clearLogs(Integer loginDays, Integer operDays){
        rzyCommonMapper.clearOperLogs(operDays);
        rzyCommonMapper.clearLoginLogs(loginDays);
    }
}
