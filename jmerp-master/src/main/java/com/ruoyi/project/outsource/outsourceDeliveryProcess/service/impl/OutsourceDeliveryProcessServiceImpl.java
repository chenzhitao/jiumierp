package com.ruoyi.project.outsource.outsourceDeliveryProcess.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.mapper.OutsourceDeliveryProcessMapper;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.domain.OutsourceDeliveryProcess;
import com.ruoyi.project.outsource.outsourceDeliveryProcess.service.IOutsourceDeliveryProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外发到货工序Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class OutsourceDeliveryProcessServiceImpl implements IOutsourceDeliveryProcessService 
{
    @Autowired
    private OutsourceDeliveryProcessMapper outsourceDeliveryProcessMapper;

    /**
     * 查询外发到货工序
     * 
     * @param id 外发到货工序ID
     * @return 外发到货工序
     */
    @Override
    public OutsourceDeliveryProcess selectOutsourceDeliveryProcessById(Long id)
    {
        return outsourceDeliveryProcessMapper.selectOutsourceDeliveryProcessById(id);
    }

    /**
     * 查询外发到货工序列表
     * 
     * @param outsourceDeliveryProcess 外发到货工序
     * @return 外发到货工序
     */
    @Override
    public List<OutsourceDeliveryProcess> selectOutsourceDeliveryProcessList(OutsourceDeliveryProcess outsourceDeliveryProcess)
    {
        if(null!=outsourceDeliveryProcess.getSupplierId()&&outsourceDeliveryProcess.getSupplierId().equals(-1L)){
            outsourceDeliveryProcess.setSupplierId(null);
        }
        return outsourceDeliveryProcessMapper.selectOutsourceDeliveryProcessList(outsourceDeliveryProcess);
    }

    /**
     * 新增外发到货工序
     * 
     * @param outsourceDeliveryProcess 外发到货工序
     * @return 结果
     */
    @Override
    public int insertOutsourceDeliveryProcess(OutsourceDeliveryProcess outsourceDeliveryProcess)
    {
        return outsourceDeliveryProcessMapper.insertOutsourceDeliveryProcess(outsourceDeliveryProcess);
    }

    /**
     * 修改外发到货工序
     * 
     * @param outsourceDeliveryProcess 外发到货工序
     * @return 结果
     */
    @Override
    public int updateOutsourceDeliveryProcess(OutsourceDeliveryProcess outsourceDeliveryProcess)
    {
        return outsourceDeliveryProcessMapper.updateOutsourceDeliveryProcess(outsourceDeliveryProcess);
    }

    /**
     * 删除外发到货工序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceDeliveryProcessByIds(String ids)
    {
        return outsourceDeliveryProcessMapper.deleteOutsourceDeliveryProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除外发到货工序信息
     * 
     * @param id 外发到货工序ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceDeliveryProcessById(Long id)
    {
        return outsourceDeliveryProcessMapper.deleteOutsourceDeliveryProcessById(id);
    }
}
