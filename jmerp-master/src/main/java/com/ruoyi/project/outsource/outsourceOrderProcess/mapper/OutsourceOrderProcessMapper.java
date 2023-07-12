package com.ruoyi.project.outsource.outsourceOrderProcess.mapper;

import java.util.List;
import com.ruoyi.project.outsource.outsourceOrderProcess.domain.OutsourceOrderProcess;

/**
 * 外发加工工序Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface OutsourceOrderProcessMapper 
{
    /**
     * 查询外发加工工序
     * 
     * @param id 外发加工工序ID
     * @return 外发加工工序
     */
    public OutsourceOrderProcess selectOutsourceOrderProcessById(Long id);

    /**
     * 查询外发加工工序列表
     * 
     * @param outsourceOrderProcess 外发加工工序
     * @return 外发加工工序集合
     */
    public List<OutsourceOrderProcess> selectOutsourceOrderProcessList(OutsourceOrderProcess outsourceOrderProcess);

    /**
     * 新增外发加工工序
     * 
     * @param outsourceOrderProcess 外发加工工序
     * @return 结果
     */
    public int insertOutsourceOrderProcess(OutsourceOrderProcess outsourceOrderProcess);

    /**
     * 修改外发加工工序
     * 
     * @param outsourceOrderProcess 外发加工工序
     * @return 结果
     */
    public int updateOutsourceOrderProcess(OutsourceOrderProcess outsourceOrderProcess);

    /**
     * 删除外发加工工序
     * 
     * @param id 外发加工工序ID
     * @return 结果
     */
    public int deleteOutsourceOrderProcessById(Long id);

    /**
     * 批量删除外发加工工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceOrderProcessByIds(String[] ids);
}
