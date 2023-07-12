package com.ruoyi.project.produce.produceWarehouse.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.sale.saleOrder.domain.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.produce.produceWarehouse.mapper.ProduceWarehouseMapper;
import com.ruoyi.project.produce.produceWarehouse.domain.ProduceWarehouse;
import com.ruoyi.project.produce.produceWarehouse.service.IProduceWarehouseService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 生产入库Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Service
public class ProduceWarehouseServiceImpl implements IProduceWarehouseService 
{
    @Autowired
    private ProduceWarehouseMapper produceWarehouseMapper;

    /**
     * 查询生产入库
     * 
     * @param id 生产入库ID
     * @return 生产入库
     */
    @Override
    public ProduceWarehouse selectProduceWarehouseById(Long id)
    {
        return produceWarehouseMapper.selectProduceWarehouseById(id);
    }

    /**
     * 查询生产入库列表
     * 
     * @param produceWarehouse 生产入库
     * @return 生产入库
     */
    @Override
    public List<ProduceWarehouse> selectProduceWarehouseList(ProduceWarehouse produceWarehouse)
    {
        return produceWarehouseMapper.selectProduceWarehouseList(produceWarehouse);
    }

    /**
     * 新增生产入库
     * 
     * @param produceWarehouse 生产入库
     * @return 结果
     */
    @Override
    public int insertProduceWarehouse(ProduceWarehouse produceWarehouse)
    {
        produceWarehouse.setCreateBy(ShiroUtils.getSysUser().getUserName());
        produceWarehouse.setCreateTime(DateUtils.getNowDate());
        produceWarehouse.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        produceWarehouse.setUpdateTime(DateUtils.getNowDate());
        return produceWarehouseMapper.insertProduceWarehouse(produceWarehouse);
    }

    /**
     * 修改生产入库
     * 
     * @param produceWarehouse 生产入库
     * @return 结果
     */
    @Override
    public int updateProduceWarehouse(ProduceWarehouse produceWarehouse)
    {
        produceWarehouse.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        produceWarehouse.setUpdateTime(DateUtils.getNowDate());
        return produceWarehouseMapper.updateProduceWarehouse(produceWarehouse);
    }

    /**
     * 删除生产入库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProduceWarehouseByIds(String ids)
    {
        int result = 0;
        String[] arr = Convert.toStrArray(ids);
        for (int i=0;i<arr.length;i++){
            ProduceWarehouse param = new ProduceWarehouse();
            param.setId(Long.parseLong(arr[i]));
            param.setStatus("delete");
            result = produceWarehouseMapper.updateProduceWarehouse(param);
        }
        return result;
        //return produceWarehouseMapper.deleteProduceWarehouseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除生产入库信息
     * 
     * @param id 生产入库ID
     * @return 结果
     */
    @Override
    public int deleteProduceWarehouseById(Long id)
    {
        int result = 0;
        ProduceWarehouse param = new ProduceWarehouse();
        param.setId(id);
        param.setStatus("delete");
        result = produceWarehouseMapper.updateProduceWarehouse(param);
        return result;
        //return produceWarehouseMapper.deleteProduceWarehouseById(id);
    }
}
