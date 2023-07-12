package com.ruoyi.project.outsource.outsourceOrderProcess.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.outsource.outsourceOrderProcess.mapper.OutsourceOrderProcessMapper;
import com.ruoyi.project.outsource.outsourceOrderProcess.domain.OutsourceOrderProcess;
import com.ruoyi.project.outsource.outsourceOrderProcess.service.IOutsourceOrderProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外发加工工序Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class OutsourceOrderProcessServiceImpl implements IOutsourceOrderProcessService 
{
    @Autowired
    private OutsourceOrderProcessMapper outsourceOrderProcessMapper;

    /**
     * 查询外发加工工序
     * 
     * @param id 外发加工工序ID
     * @return 外发加工工序
     */
    @Override
    public OutsourceOrderProcess selectOutsourceOrderProcessById(Long id)
    {
        return outsourceOrderProcessMapper.selectOutsourceOrderProcessById(id);
    }

    /**
     * 查询外发加工工序列表
     * 
     * @param outsourceOrderProcess 外发加工工序
     * @return 外发加工工序
     */
    @Override
    public List<OutsourceOrderProcess> selectOutsourceOrderProcessList(OutsourceOrderProcess outsourceOrderProcess)
    {
        if(null!=outsourceOrderProcess.getSupplierId()&&outsourceOrderProcess.getSupplierId().equals(-1L)){
            outsourceOrderProcess.setSupplierId(null);
        }
        return outsourceOrderProcessMapper.selectOutsourceOrderProcessList(outsourceOrderProcess);
    }

    /**
     * 新增外发加工工序
     * 
     * @param outsourceOrderProcess 外发加工工序
     * @return 结果
     */
    @Override
    public int insertOutsourceOrderProcess(OutsourceOrderProcess outsourceOrderProcess)
    {
        return outsourceOrderProcessMapper.insertOutsourceOrderProcess(outsourceOrderProcess);
    }

    /**
     * 修改外发加工工序
     * 
     * @param outsourceOrderProcess 外发加工工序
     * @return 结果
     */
    @Override
    public int updateOutsourceOrderProcess(OutsourceOrderProcess outsourceOrderProcess)
    {
        return outsourceOrderProcessMapper.updateOutsourceOrderProcess(outsourceOrderProcess);
    }

    /**
     * 删除外发加工工序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceOrderProcessByIds(String ids)
    {
        return outsourceOrderProcessMapper.deleteOutsourceOrderProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除外发加工工序信息
     * 
     * @param id 外发加工工序ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceOrderProcessById(Long id)
    {
        return outsourceOrderProcessMapper.deleteOutsourceOrderProcessById(id);
    }
}
