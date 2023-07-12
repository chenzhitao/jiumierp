package com.ruoyi.project.produce.produceOrderMaterials.service;

import java.util.List;
import com.ruoyi.project.produce.produceOrderMaterials.domain.ProduceOrderMaterials;

/**
 * 施工材料Service接口
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public interface IProduceOrderMaterialsService 
{
    /**
     * 查询施工材料
     * 
     * @param id 施工材料ID
     * @return 施工材料
     */
    public ProduceOrderMaterials selectProduceOrderMaterialsById(Long id);

    /**
     * 查询施工材料列表
     * 
     * @param produceOrderMaterials 施工材料
     * @return 施工材料集合
     */
    public List<ProduceOrderMaterials> selectProduceOrderMaterialsList(ProduceOrderMaterials produceOrderMaterials);

    /**
     * 新增施工材料
     * 
     * @param produceOrderMaterials 施工材料
     * @return 结果
     */
    public int insertProduceOrderMaterials(ProduceOrderMaterials produceOrderMaterials);

    /**
     * 修改施工材料
     * 
     * @param produceOrderMaterials 施工材料
     * @return 结果
     */
    public int updateProduceOrderMaterials(ProduceOrderMaterials produceOrderMaterials);

    /**
     * 批量删除施工材料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProduceOrderMaterialsByIds(String ids);

    /**
     * 删除施工材料信息
     * 
     * @param id 施工材料ID
     * @return 结果
     */
    public int deleteProduceOrderMaterialsById(Long id);
}
