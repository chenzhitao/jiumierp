package com.ruoyi.project.produce.produceOrderMaterials.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.config.configMaterials.domain.ConfigMaterials;
import com.ruoyi.project.config.configMaterials.mapper.ConfigMaterialsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.produce.produceOrderMaterials.mapper.ProduceOrderMaterialsMapper;
import com.ruoyi.project.produce.produceOrderMaterials.domain.ProduceOrderMaterials;
import com.ruoyi.project.produce.produceOrderMaterials.service.IProduceOrderMaterialsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 施工材料Service业务层处理
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
@Service
public class ProduceOrderMaterialsServiceImpl implements IProduceOrderMaterialsService 
{
    @Autowired
    private ProduceOrderMaterialsMapper produceOrderMaterialsMapper;
    @Autowired
    private ConfigMaterialsMapper configMaterialsMapper;

    /**
     * 查询施工材料
     * 
     * @param id 施工材料ID
     * @return 施工材料
     */
    @Override
    public ProduceOrderMaterials selectProduceOrderMaterialsById(Long id)
    {
        return produceOrderMaterialsMapper.selectProduceOrderMaterialsById(id);
    }

    /**
     * 查询施工材料列表
     * 
     * @param produceOrderMaterials 施工材料
     * @return 施工材料
     */
    @Override
    public List<ProduceOrderMaterials> selectProduceOrderMaterialsList(ProduceOrderMaterials produceOrderMaterials)
    {
        List<ProduceOrderMaterials> list = new ArrayList<ProduceOrderMaterials>();
        if(!StringUtils.isEmpty(produceOrderMaterials.getRzyValue1())&&"purchase".equals(produceOrderMaterials.getRzyValue1())){
            list = produceOrderMaterialsMapper.selectProduceOrderMaterialsListForPurchase(produceOrderMaterials);
        }else if(!StringUtils.isEmpty(produceOrderMaterials.getRzyValue1())&&"order".equals(produceOrderMaterials.getRzyValue1())){
            list = produceOrderMaterialsMapper.selectProduceOrderMaterialsListByOrderId(produceOrderMaterials);
        }else{
            list = produceOrderMaterialsMapper.selectProduceOrderMaterialsList(produceOrderMaterials);
        }
        return list;
    }

    /**
     * 新增施工材料
     * 
     * @param produceOrderMaterials 施工材料
     * @return 结果
     */
    @Override
    public int insertProduceOrderMaterials(ProduceOrderMaterials produceOrderMaterials)
    {
        //新建材料
        ConfigMaterials materials = new ConfigMaterials();
        materials.setCreateBy(ShiroUtils.getSysUser().getUserName());
        materials.setCreateTime(DateUtils.getNowDate());
        materials.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        materials.setUpdateTime(DateUtils.getNowDate());
        materials.setMaterialsName(produceOrderMaterials.getMaterialsName());
        materials.setMaterialsType(produceOrderMaterials.getMaterialsType());
        materials.setSizeLong(produceOrderMaterials.getSizeLong());
        materials.setSizeWidth(produceOrderMaterials.getSizeWidth());
        materials.setMainMaterialsType("QT");
        configMaterialsMapper.insertConfigMaterials(materials);
        produceOrderMaterials.setMaterialsId(materials.getId());
        return produceOrderMaterialsMapper.insertProduceOrderMaterials(produceOrderMaterials);
    }

    /**
     * 修改施工材料
     * 
     * @param produceOrderMaterials 施工材料
     * @return 结果
     */
    @Override
    public int updateProduceOrderMaterials(ProduceOrderMaterials produceOrderMaterials)
    {
        if(null!=produceOrderMaterials.getMaterialsId()){
            //更新材料
            ConfigMaterials materials = new ConfigMaterials();
            materials.setUpdateBy(ShiroUtils.getSysUser().getUserName());
            materials.setUpdateTime(DateUtils.getNowDate());
            materials.setId(produceOrderMaterials.getMaterialsId());
            materials.setMainMaterialsType(produceOrderMaterials.getMaterialsType());
            materials.setSizeLong(produceOrderMaterials.getSizeLong());
            materials.setSizeWidth(produceOrderMaterials.getSizeWidth());
            materials.setMaterialsName(produceOrderMaterials.getMaterialsName());
            configMaterialsMapper.updateConfigMaterials(materials);
        }
        return produceOrderMaterialsMapper.updateProduceOrderMaterials(produceOrderMaterials);
    }

    /**
     * 删除施工材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProduceOrderMaterialsByIds(String ids)
    {
        return produceOrderMaterialsMapper.deleteProduceOrderMaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除施工材料信息
     * 
     * @param id 施工材料ID
     * @return 结果
     */
    @Override
    public int deleteProduceOrderMaterialsById(Long id)
    {
        return produceOrderMaterialsMapper.deleteProduceOrderMaterialsById(id);
    }
}
