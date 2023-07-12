package com.ruoyi.project.produce.produceOrderProcess.service.impl;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.produce.produceOrderProcess.mapper.ProduceOrderProcessMapper;
import com.ruoyi.project.produce.produceOrderProcess.domain.ProduceOrderProcess;
import com.ruoyi.project.produce.produceOrderProcess.service.IProduceOrderProcessService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 施工工序Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Service
public class ProduceOrderProcessServiceImpl implements IProduceOrderProcessService 
{
    @Autowired
    private ProduceOrderProcessMapper produceOrderProcessMapper;

    /**
     * 查询施工工序
     * 
     * @param id 施工工序ID
     * @return 施工工序
     */
    @Override
    public ProduceOrderProcess selectProduceOrderProcessById(Long id)
    {
        return produceOrderProcessMapper.selectProduceOrderProcessById(id);
    }

    /**
     * 查询施工工序列表
     * 
     * @param produceOrderProcess 施工工序
     * @return 施工工序
     */
    @Override
    public List<ProduceOrderProcess> selectProduceOrderProcessList(ProduceOrderProcess produceOrderProcess)
    {
        if(!StringUtils.isEmpty(produceOrderProcess.getRzyValue1())&&"schedule".equals(produceOrderProcess.getRzyValue1())) {
            return produceOrderProcessMapper.selectProduceOrderProcessListForSchedule(produceOrderProcess);
        }else if(!StringUtils.isEmpty(produceOrderProcess.getRzyValue1())&&"outsource".equals(produceOrderProcess.getRzyValue1())){
            return produceOrderProcessMapper.selectProduceOrderProcessListForOutsource(produceOrderProcess);
        }else{
            return produceOrderProcessMapper.selectProduceOrderProcessList(produceOrderProcess);
        }
    }

    /**
     * 新增施工工序
     * 
     * @param produceOrderProcess 施工工序
     * @return 结果
     */
    @Override
    public int insertProduceOrderProcess(ProduceOrderProcess produceOrderProcess)
    {
        return produceOrderProcessMapper.insertProduceOrderProcess(produceOrderProcess);
    }

    /**
     * 修改施工工序
     * 
     * @param produceOrderProcess 施工工序
     * @return 结果
     */
    @Override
    public int updateProduceOrderProcess(ProduceOrderProcess produceOrderProcess)
    {
        return produceOrderProcessMapper.updateProduceOrderProcess(produceOrderProcess);
    }

    /**
     * 删除施工工序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProduceOrderProcessByIds(String ids)
    {
        return produceOrderProcessMapper.deleteProduceOrderProcessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除施工工序信息
     * 
     * @param id 施工工序ID
     * @return 结果
     */
    @Override
    public int deleteProduceOrderProcessById(Long id)
    {
        return produceOrderProcessMapper.deleteProduceOrderProcessById(id);
    }


    /**
     * 找到关联外发的所有工序
     *
     * @return 结果
     */
    @Override
    public List<Long> findFullProduceOrderProcessIds(String ids)
    {
        return produceOrderProcessMapper.findFullProduceOrderProcessIds(Convert.toStrArray(ids));
    }
}
