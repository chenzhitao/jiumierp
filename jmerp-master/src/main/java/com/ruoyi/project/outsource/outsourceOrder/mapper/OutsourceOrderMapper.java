package com.ruoyi.project.outsource.outsourceOrder.mapper;

import java.util.List;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrder;
import com.ruoyi.project.outsource.outsourceOrder.domain.OutsourceOrderExport;

/**
 * 外发加工Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface OutsourceOrderMapper 
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
     * 删除外发加工
     * 
     * @param id 外发加工ID
     * @return 结果
     */
    public int deleteOutsourceOrderById(Long id);
    public int deleteOutsourceOrderProcessById(Long id);
    public int deleteOutsourceOrderMaterialsById(Long id);

    /**
     * 批量删除外发加工
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceOrderByIds(String[] ids);
}
