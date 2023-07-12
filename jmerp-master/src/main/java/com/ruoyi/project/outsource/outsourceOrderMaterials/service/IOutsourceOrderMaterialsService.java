package com.ruoyi.project.outsource.outsourceOrderMaterials.service;

import java.util.List;
import com.ruoyi.project.outsource.outsourceOrderMaterials.domain.OutsourceOrderMaterials;

/**
 * 外发加工带料Service接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface IOutsourceOrderMaterialsService 
{
    /**
     * 查询外发加工带料
     * 
     * @param id 外发加工带料ID
     * @return 外发加工带料
     */
    public OutsourceOrderMaterials selectOutsourceOrderMaterialsById(Long id);

    /**
     * 查询外发加工带料列表
     * 
     * @param outsourceOrderMaterials 外发加工带料
     * @return 外发加工带料集合
     */
    public List<OutsourceOrderMaterials> selectOutsourceOrderMaterialsList(OutsourceOrderMaterials outsourceOrderMaterials);

    /**
     * 新增外发加工带料
     * 
     * @param outsourceOrderMaterials 外发加工带料
     * @return 结果
     */
    public int insertOutsourceOrderMaterials(OutsourceOrderMaterials outsourceOrderMaterials);

    /**
     * 修改外发加工带料
     * 
     * @param outsourceOrderMaterials 外发加工带料
     * @return 结果
     */
    public int updateOutsourceOrderMaterials(OutsourceOrderMaterials outsourceOrderMaterials);

    /**
     * 批量删除外发加工带料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceOrderMaterialsByIds(String ids);

    /**
     * 删除外发加工带料信息
     * 
     * @param id 外发加工带料ID
     * @return 结果
     */
    public int deleteOutsourceOrderMaterialsById(Long id);
}
