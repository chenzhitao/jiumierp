package com.ruoyi.project.outsource.outsourceOrder.service;

import java.util.List;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrder;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrderExport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 外发加工Service接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface IOutsourceOrderService 
{
    /**
     * 查询外发加工
     * 
     * @param id 外发加工ID
     * @return 外发加工
     */
    public OutsourceOrder selectOutsourceOrderById(Long id);

    /**
     * 查询外发加工列表
     * 
     * @param outsourceOrder 外发加工
     * @return 外发加工集合
     */
    public List<OutsourceOrder> selectOutsourceOrderList(OutsourceOrder outsourceOrder);
    public List<OutsourceOrderExport> selectOutsourceOrderExportList(OutsourceOrder outsourceOrder);

    /**
     * 新增外发加工
     * 
     * @param outsourceOrder 外发加工
     * @return 结果
     */
    public int insertOutsourceOrder(OutsourceOrder outsourceOrder);

    /**
     * 修改外发加工
     * 
     * @param outsourceOrder 外发加工
     * @return 结果
     */
    public int updateOutsourceOrder(OutsourceOrder outsourceOrder);

    /**
     * 批量删除外发加工
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceOrderByIds(String ids);

    /**
     * 删除外发加工信息
     * 
     * @param id 外发加工ID
     * @return 结果
     */
    public int deleteOutsourceOrderById(Long id);

    /**
     * 创建外发加工
     */
    public OutsourceOrder createOutsource(OutsourceOrder outsourceOrder);

    /**
     * 汇总金额
     * @Author 方舟
     * @Date 2021/4/13 20:49:58
     **/
    public OutsourceOrder countAmount(OutsourceOrder outsourceOrder);
}
