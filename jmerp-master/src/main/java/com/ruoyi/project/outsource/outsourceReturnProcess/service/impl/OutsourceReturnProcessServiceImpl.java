package com.ruoyi.project.outsource.outsourceReturnProcess.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.outsource.outsourceReturnProcess.mapper.OutsourceReturnProcessMapper;
import com.ruoyi.project.outsource.outsourceReturnProcess.domain.OutsourceReturnProcess;
import com.ruoyi.project.outsource.outsourceReturnProcess.service.IOutsourceReturnProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外发退货工序Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class OutsourceReturnProcessServiceImpl implements IOutsourceReturnProcessService 
{
    @Autowired
    private OutsourceReturnProcessMapper outsourceReturnProcessMapper;

    /**
     * 查询外发退货工序
     * 
     * @param id 外发退货工序ID
     * @return 外发退货工序
     */
    @Override
    public OutsourceReturnProcess selectOutsourceReturnProcessById(Long id)
    {
        return outsourceReturnProcessMapper.selectOutsourceReturnProcessById(id);
    }

    /**
     * 查询外发退货工序列表
     * 
     * @param outsourceReturnProcess 外发退货工序
     * @return 外发退货工序
     */
    @Override
    public List<OutsourceReturnProcess> selectOutsourceReturnProcessList(OutsourceReturnProcess outsourceReturnProcess)
    {
        return outsourceReturnProcessMapper.selectOutsourceReturnProcessList(outsourceReturnProcess);
    }

    /**
     * 新增外发退货工序
     * 
     * @param outsourceReturnProcess 外发退货工序
     * @return 结果
     */
    @Override
    public int insertOutsourceReturnProcess(OutsourceReturnProcess outsourceReturnProcess)
    {
        return outsourceReturnProcessMapper.insertOutsourceReturnProcess(outsourceReturnProcess);
    }

    /**
     * 修改外发退货工序
     * 
     * @param outsourceReturnProcess 外发退货工序
     * @return 结果
     */
    @Override
    public int updateOutsourceReturnProcess(OutsourceReturnProcess outsourceReturnProcess)
    {
        return outsourceReturnProcessMapper.updateOutsourceReturnProcess(outsourceReturnProcess);
    }

    /**
     * 删除外发退货工序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceReturnProcessByIds(String ids)
    {
        return outsourceReturnProcessMapper.deleteOutsourceReturnProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除外发退货工序信息
     * 
     * @param id 外发退货工序ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceReturnProcessById(Long id)
    {
        return outsourceReturnProcessMapper.deleteOutsourceReturnProcessById(id);
    }
}
