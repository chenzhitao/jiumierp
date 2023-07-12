package com.ruoyi.project.rzy.rzyCommon.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.rzy.rzyCommon.domain.RzyCommon;
import com.ruoyi.project.rzy.rzyCommon.service.IRzyCommonService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 虚拟公共Controller
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
@Controller
@RequestMapping("/rzy/rzyCommon")
public class RzyCommonController extends BaseController
{
    private String prefix = "rzy/rzyCommon";

    @Autowired
    private IRzyCommonService rzyCommonService;

    @RequiresPermissions("rzy:rzyCommon:view")
    @GetMapping()
    public String rzyCommon()
    {
        return prefix + "/rzyCommon";
    }

    /**
     * 查询虚拟公共列表
     */
    @RequiresPermissions("rzy:rzyCommon:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RzyCommon rzyCommon)
    {
        startPage();
        List<RzyCommon> list = rzyCommonService.selectRzyCommonList(rzyCommon);
        return getDataTable(list);
    }

    /**
     * 导出虚拟公共列表
     */
    @RequiresPermissions("rzy:rzyCommon:export")
    @Log(title = "虚拟公共", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RzyCommon rzyCommon)
    {
        List<RzyCommon> list = rzyCommonService.selectRzyCommonList(rzyCommon);
        ExcelUtil<RzyCommon> util = new ExcelUtil<RzyCommon>(RzyCommon.class);
        return util.exportExcel(list, "rzyCommon");
    }

    /**
     * 新增虚拟公共
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存虚拟公共
     */
    @RequiresPermissions("rzy:rzyCommon:add")
    @Log(title = "虚拟公共", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RzyCommon rzyCommon)
    {
        return toAjax(rzyCommonService.insertRzyCommon(rzyCommon));
    }

    /**
     * 修改虚拟公共
     */
    @GetMapping("/edit/{strValue1}")
    public String edit(@PathVariable("strValue1") String strValue1, ModelMap mmap)
    {
        RzyCommon rzyCommon = rzyCommonService.selectRzyCommonById(strValue1);
        mmap.put("rzyCommon", rzyCommon);
        return prefix + "/edit";
    }

    /**
     * 修改保存虚拟公共
     */
    @RequiresPermissions("rzy:rzyCommon:edit")
    @Log(title = "虚拟公共", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RzyCommon rzyCommon)
    {
        return toAjax(rzyCommonService.updateRzyCommon(rzyCommon));
    }

    /**
     * 删除虚拟公共
     */
    @RequiresPermissions("rzy:rzyCommon:remove")
    @Log(title = "虚拟公共", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rzyCommonService.deleteRzyCommonByIds(ids));
    }

    /**
     * 下载模板
     * @Author 方舟
     * @Date 2021/3/30 0030 10:02:09
     **/
    @RequestMapping("/rzyImportTemplate/{filename}")
    public void doLoad(@PathVariable("filename") String filename, HttpServletRequest request, HttpServletResponse response){
        String[] realName = {"报价工艺卡模板","生产工艺卡模板"};
        String[] codeName = {"quotation_template","production_template"};
        String fname = filename;
        for (int i=0;i<codeName.length;i++){
            if(codeName[i].equals(filename)){
                fname = realName[i];
            }
        }
        String filepath = "src/main/resources/static/file/"+fname+".xlsx";
        try {
            // 清空输出流
            response.reset();
            String resultFileName = fname + ".xlsx";
            resultFileName = URLEncoder.encode(resultFileName,"UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + resultFileName);// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            //FileUtils.writeBytes(filepath, response.getOutputStream());
            DataInputStream in = new DataInputStream(new FileInputStream(new File(filepath)));
            //输出流
            OutputStream out = response.getOutputStream();
            //输出文件
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            // 清空输出流
            response.reset();
        }
    }

    /**
     * 输入注册码
     * @Author 方舟
     * @Date 2021/5/8 9:51:17
    **/
    @PostMapping("/inputCode")
    @ResponseBody
    public AjaxResult inputCode(RzyCommon rzyCommon)
    {
        return AjaxResult.success(rzyCommonService.inputCode(rzyCommon));
    }

    /**
     * 查看系统信息
     * @Author 方舟
     * @Date 2021/7/19 9:58:03
    **/
    @PostMapping("/selectSysInfo")
    @ResponseBody
    public AjaxResult selectSysInfo()
    {
        return AjaxResult.success(rzyCommonService.selectSysInfo());
    }

    /**
     * 首页看板数据
     * @Author 方舟
     * @Date 2021/5/8 9:51:17
    **/
    @PostMapping("/mainBoardData")
    @ResponseBody
    public AjaxResult mainBoardData(RzyCommon rzyCommon)
    {
        return AjaxResult.success(rzyCommonService.mainBoardData(rzyCommon));
    }

    /**
     * 打开扫码页面
     */
    @GetMapping("/scan")
    public String scan()
    {
        return "common/scan";
    }

    /**
     * 打开系统信息页面
     */
    @GetMapping("/sysInfo")
    public String sysInfo()
    {
        return "common/sysInfo";
    }

    /**
     * 获取文档明细
     * @Author 方舟
     * @Date 2021/5/8 9:51:17
     **/
    @PostMapping("/getDocumentsDetail")
    @ResponseBody
    public AjaxResult getDocumentsDetail(RzyCommon rzyCommon)
    {
        return AjaxResult.success(rzyCommonService.getDocumentsDetail(rzyCommon));
    }


    /**
     * 纸张尺寸
     * @Author 方舟
     * @Date 2021/6/24 10:57:35
    **/
    @PostMapping("/getFullPaperSizeList")
    @ResponseBody
    public TableDataInfo getFullPaperSizeList(RzyCommon rzyCommon)
    {
        startPage();
        List<RzyCommon> list = rzyCommonService.getFullPaperSizeList(rzyCommon);
        return getDataTable(list);
    }

    /**
     * 获取当前登录用户的姓名
     * @Author 方舟
     * @Date 2021/8/13 17:51:17
    **/
    @PostMapping("/getCurrentUser")
    @ResponseBody
    public AjaxResult getCurrentUser()
    {
        String user = ShiroUtils.getSysUser().getUserName();
        return AjaxResult.success(user);
    }
}
