package com.ruoyi.project.produce.produceWarehouse.mapper;

import java.util.List;
import com.ruoyi.project.produce.produceWarehouse.domain.ProduceWarehouse;

/**
 * 生产入库Mapper接口
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public interface ProduceWarehouseMapper 
{
    /**
     * 查询生产入库
     * 
     * @param id 生产入库ID
     * @return 生产入库
     */
    public ProduceWarehouse selectProduceWarehouseById(Long id);

    /**
     * 查询生产入库列表
     * 
     * @param produceWarehouse 生产入库
     * @return 生产入库集合
     */
    public List<ProduceWarehouse> selectProduceWarehouseList(ProduceWarehouse produceWarehouse);

    /**
     * 新增生产入库
     * 
     * @param produceWarehouse 生产入库
     * @return 结果
     */
    public int insertProduceWarehouse(ProduceWarehouse produceWarehouse);

    /**
     * 修改生产入库
     * 
     * @param produceWarehouse 生产入库
     * @return 结果
     */
    public int updateProduceWarehouse(ProduceWarehouse produceWarehouse);

    /**
     * 删除生产入库
     * 
     * @param id 生产入库ID
     * @return 结果
     */
    public int deleteProduceWarehouseById(Long id);

    /**
     * 批量删除生产入库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceWarehouseByIds(String[] ids);
}
