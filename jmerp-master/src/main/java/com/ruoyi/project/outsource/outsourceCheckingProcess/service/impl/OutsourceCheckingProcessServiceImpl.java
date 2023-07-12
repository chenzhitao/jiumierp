package com.ruoyi.project.outsource.outsourceCheckingProcess.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.outsource.outsourceCheckingProcess.mapper.OutsourceCheckingProcessMapper;
import com.ruoyi.project.outsource.outsourceCheckingProcess.domain.OutsourceCheckingProcess;
import com.ruoyi.project.outsource.outsourceCheckingProcess.service.IOutsourceCheckingProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外发对账工序Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class OutsourceCheckingProcessServiceImpl implements IOutsourceCheckingProcessService 
{
    @Autowired
    private OutsourceCheckingProcessMapper outsourceCheckingProcessMapper;

    /**
     * 查询外发对账工序
     * 
     * @param id 外发对账工序ID
     * @return 外发对账工序
     */
    @Override
    public OutsourceCheckingProcess selectOutsourceCheckingProcessById(Long id)
    {
        return outsourceCheckingProcessMapper.selectOutsourceCheckingProcessById(id);
    }

    /**
     * 查询外发对账工序列表
     * 
     * @param outsourceCheckingProcess 外发对账工序
     * @return 外发对账工序
     */
    @Override
    public List<OutsourceCheckingProcess> selectOutsourceCheckingProcessList(OutsourceCheckingProcess outsourceCheckingProcess)
    {
        return outsourceCheckingProcessMapper.selectOutsourceCheckingProcessList(outsourceCheckingProcess);
    }

    /**
     * 新增外发对账工序
     * 
     * @param outsourceCheckingProcess 外发对账工序
     * @return 结果
     */
    @Override
    public int insertOutsourceCheckingProcess(OutsourceCheckingProcess outsourceCheckingProcess)
    {
        return outsourceCheckingProcessMapper.insertOutsourceCheckingProcess(outsourceCheckingProcess);
    }

    /**
     * 修改外发对账工序
     * 
     * @param outsourceCheckingProcess 外发对账工序
     * @return 结果
     */
    @Override
    public int updateOutsourceCheckingProcess(OutsourceCheckingProcess outsourceCheckingProcess)
    {
        return outsourceCheckingProcessMapper.updateOutsourceCheckingProcess(outsourceCheckingProcess);
    }

    /**
     * 删除外发对账工序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceCheckingProcessByIds(String ids)
    {
        return outsourceCheckingProcessMapper.deleteOutsourceCheckingProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除外发对账工序信息
     * 
     * @param id 外发对账工序ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceCheckingProcessById(Long id)
    {
        return outsourceCheckingProcessMapper.deleteOutsourceCheckingProcessById(id);
    }
}
