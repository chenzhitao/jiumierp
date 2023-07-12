package com.ruoyi.project.outsource.outsourceReturnProcess.mapper;

import java.util.List;
import com.ruoyi.project.outsource.outsourceReturnProcess.domain.OutsourceReturnProcess;

/**
 * 外发退货工序Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface OutsourceReturnProcessMapper 
{
    /**
     * 查询外发退货工序
     * 
     * @param id 外发退货工序ID
     * @return 外发退货工序
     */
    public OutsourceReturnProcess selectOutsourceReturnProcessById(Long id);

    /**
     * 查询外发退货工序列表
     * 
     * @param outsourceReturnProcess 外发退货工序
     * @return 外发退货工序集合
     */
    public List<OutsourceReturnProcess> selectOutsourceReturnProcessList(OutsourceReturnProcess outsourceReturnProcess);

    /**
     * 新增外发退货工序
     * 
     * @param outsourceReturnProcess 外发退货工序
     * @return 结果
     */
    public int insertOutsourceReturnProcess(OutsourceReturnProcess outsourceReturnProcess);

    /**
     * 修改外发退货工序
     * 
     * @param outsourceReturnProcess 外发退货工序
     * @return 结果
     */
    public int updateOutsourceReturnProcess(OutsourceReturnProcess outsourceReturnProcess);

    /**
     * 删除外发退货工序
     * 
     * @param id 外发退货工序ID
     * @return 结果
     */
    public int deleteOutsourceReturnProcessById(Long id);

    /**
     * 批量删除外发退货工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceReturnProcessByIds(String[] ids);
}
