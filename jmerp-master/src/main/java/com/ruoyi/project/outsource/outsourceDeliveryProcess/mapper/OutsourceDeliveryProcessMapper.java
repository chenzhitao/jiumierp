package com.ruoyi.project.outsource.outsourceDeliveryProcess.mapper;

import java.util.List;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.domain.OutsourceDeliveryProcess;

/**
 * 外发到货工序Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface OutsourceDeliveryProcessMapper 
{
    /**
     * 查询外发到货工序
     * 
     * @param id 外发到货工序ID
     * @return 外发到货工序
     */
    public OutsourceDeliveryProcess selectOutsourceDeliveryProcessById(Long id);

    /**
     * 查询外发到货工序列表
     * 
     * @param outsourceDeliveryProcess 外发到货工序
     * @return 外发到货工序集合
     */
    public List<OutsourceDeliveryProcess> selectOutsourceDeliveryProcessList(OutsourceDeliveryProcess outsourceDeliveryProcess);

    /**
     * 新增外发到货工序
     * 
     * @param outsourceDeliveryProcess 外发到货工序
     * @return 结果
     */
    public int insertOutsourceDeliveryProcess(OutsourceDeliveryProcess outsourceDeliveryProcess);

    /**
     * 修改外发到货工序
     * 
     * @param outsourceDeliveryProcess 外发到货工序
     * @return 结果
     */
    public int updateOutsourceDeliveryProcess(OutsourceDeliveryProcess outsourceDeliveryProcess);

    /**
     * 删除外发到货工序
     * 
     * @param id 外发到货工序ID
     * @return 结果
     */
    public int deleteOutsourceDeliveryProcessById(Long id);

    /**
     * 批量删除外发到货工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceDeliveryProcessByIds(String[] ids);
}
