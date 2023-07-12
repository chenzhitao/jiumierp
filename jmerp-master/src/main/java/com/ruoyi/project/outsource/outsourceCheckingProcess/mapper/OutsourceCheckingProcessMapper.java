package com.ruoyi.project.outsource.outsourceCheckingProcess.mapper;

import java.util.List;
import com.ruoyi.project.outsource.outsourceCheckingProcess.domain.OutsourceCheckingProcess;

/**
 * 外发对账工序Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface OutsourceCheckingProcessMapper 
{
    /**
     * 查询外发对账工序
     * 
     * @param id 外发对账工序ID
     * @return 外发对账工序
     */
    public OutsourceCheckingProcess selectOutsourceCheckingProcessById(Long id);

    /**
     * 查询外发对账工序列表
     * 
     * @param outsourceCheckingProcess 外发对账工序
     * @return 外发对账工序集合
     */
    public List<OutsourceCheckingProcess> selectOutsourceCheckingProcessList(OutsourceCheckingProcess outsourceCheckingProcess);

    /**
     * 新增外发对账工序
     * 
     * @param outsourceCheckingProcess 外发对账工序
     * @return 结果
     */
    public int insertOutsourceCheckingProcess(OutsourceCheckingProcess outsourceCheckingProcess);

    /**
     * 修改外发对账工序
     * 
     * @param outsourceCheckingProcess 外发对账工序
     * @return 结果
     */
    public int updateOutsourceCheckingProcess(OutsourceCheckingProcess outsourceCheckingProcess);

    /**
     * 删除外发对账工序
     * 
     * @param id 外发对账工序ID
     * @return 结果
     */
    public int deleteOutsourceCheckingProcessById(Long id);

    /**
     * 批量删除外发对账工序
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceCheckingProcessByIds(String[] ids);
}
