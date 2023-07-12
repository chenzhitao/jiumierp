package com.ruoyi.project.produce.produceOrder.mapper;

import java.util.List;
import com.ruoyi.project.produce.produceOrder.domain.ProduceOrder;

/**
 * 施工单Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-30
 */
public interface ProduceOrderMapper 
{
    /**
     * 查询施工单
     * 
     * @param id 施工单ID
     * @return 施工单
     */
    public ProduceOrder selectProduceOrderById(Long id);
    public ProduceOrder selectProduceOrderPartById(Long id);

    /**
     * 查询施工单列表
     * 
     * @param produceOrder 施工单
     * @return 施工单集合
     */
    public List<ProduceOrder> selectProduceOrderList(ProduceOrder produceOrder);
    public List<ProduceOrder> selectProduceOrderPartList(ProduceOrder produceOrder);
    public String selectProduceOrderIsInbound(ProduceOrder produceOrder);

    /**
     * 新增施工单
     * 
     * @param produceOrder 施工单
     * @return 结果
     */
    public int insertProduceOrder(ProduceOrder produceOrder);
    public int insertProduceOrderPart(ProduceOrder produceOrder);

    /**
     * 修改施工单
     * 
     * @param produceOrder 施工单
     * @return 结果
     */
    public int updateProduceOrder(ProduceOrder produceOrder);
    public int updateProduceOrderPart(ProduceOrder produceOrder);

    /**
     * 删除施工单
     * 
     * @param id 施工单ID
     * @return 结果
     */
    public int deleteProduceOrderById(Long id);
    public int deleteProduceOrderProcessById(Long id);
    public int deleteProduceOrderMaterialsById(Long id);
    public int deleteProduceOrderPartsByIds(String[] ids);
    public int deleteProduceOrderPartByPartId(Long id);

    /**
     * 批量删除施工单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceOrderByIds(String[] ids);

    public List<ProduceOrder> selectProduceOrderPartListByPartIds(String[] ids);
}
