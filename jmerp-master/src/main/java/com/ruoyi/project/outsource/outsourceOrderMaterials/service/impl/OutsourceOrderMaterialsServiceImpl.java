package com.ruoyi.project.outsource.outsourceOrderMaterials.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.outsource.outsourceOrderMaterials.mapper.OutsourceOrderMaterialsMapper;
import com.ruoyi.project.outsource.outsourceOrderMaterials.domain.OutsourceOrderMaterials;
import com.ruoyi.project.outsource.outsourceOrderMaterials.service.IOutsourceOrderMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 外发加工带料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
@Service
public class OutsourceOrderMaterialsServiceImpl implements IOutsourceOrderMaterialsService 
{
    @Autowired
    private OutsourceOrderMaterialsMapper outsourceOrderMaterialsMapper;

    /**
     * 查询外发加工带料
     * 
     * @param id 外发加工带料ID
     * @return 外发加工带料
     */
    @Override
    public OutsourceOrderMaterials selectOutsourceOrderMaterialsById(Long id)
    {
        return outsourceOrderMaterialsMapper.selectOutsourceOrderMaterialsById(id);
    }

    /**
     * 查询外发加工带料列表
     * 
     * @param outsourceOrderMaterials 外发加工带料
     * @return 外发加工带料
     */
    @Override
    public List<OutsourceOrderMaterials> selectOutsourceOrderMaterialsList(OutsourceOrderMaterials outsourceOrderMaterials)
    {
        return outsourceOrderMaterialsMapper.selectOutsourceOrderMaterialsList(outsourceOrderMaterials);
    }

    /**
     * 新增外发加工带料
     * 
     * @param outsourceOrderMaterials 外发加工带料
     * @return 结果
     */
    @Override
    public int insertOutsourceOrderMaterials(OutsourceOrderMaterials outsourceOrderMaterials)
    {
        return outsourceOrderMaterialsMapper.insertOutsourceOrderMaterials(outsourceOrderMaterials);
    }

    /**
     * 修改外发加工带料
     * 
     * @param outsourceOrderMaterials 外发加工带料
     * @return 结果
     */
    @Override
    public int updateOutsourceOrderMaterials(OutsourceOrderMaterials outsourceOrderMaterials)
    {
        return outsourceOrderMaterialsMapper.updateOutsourceOrderMaterials(outsourceOrderMaterials);
    }

    /**
     * 删除外发加工带料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceOrderMaterialsByIds(String ids)
    {
        return outsourceOrderMaterialsMapper.deleteOutsourceOrderMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除外发加工带料信息
     * 
     * @param id 外发加工带料ID
     * @return 结果
     */
    @Override
    public int deleteOutsourceOrderMaterialsById(Long id)
    {
        return outsourceOrderMaterialsMapper.deleteOutsourceOrderMaterialsById(id);
    }
}
