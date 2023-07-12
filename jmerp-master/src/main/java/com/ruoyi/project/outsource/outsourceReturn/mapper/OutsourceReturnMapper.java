package com.ruoyi.project.outsource.outsourceReturn.mapper;

import java.util.List;
import com.ruoyi.project.outsource.outsourceReturn.domain.OutsourceReturn;
import com.ruoyi.project.outsource.outsourceReturn.domain.OutsourceReturnExport;

/**
 * 外发退货Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface OutsourceReturnMapper 
{
    /**
     * 查询外发退货
     * 
     * @param id 外发退货ID
     * @return 外发退货
     */
    public OutsourceReturn selectOutsourceReturnById(Long id);

    /**
     * 查询外发退货列表
     * 
     * @param outsourceReturn 外发退货
     * @return 外发退货集合
     */
    public List<OutsourceReturn> selectOutsourceReturnList(OutsourceReturn outsourceReturn);
    public List<OutsourceReturnExport> selectOutsourceReturnExportList(OutsourceReturn outsourceReturn);

    /**
     * 新增外发退货
     * 
     * @param outsourceReturn 外发退货
     * @return 结果
     */
    public int insertOutsourceReturn(OutsourceReturn outsourceReturn);

    /**
     * 修改外发退货
     * 
     * @param outsourceReturn 外发退货
     * @return 结果
     */
    public int updateOutsourceReturn(OutsourceReturn outsourceReturn);

    /**
     * 删除外发退货
     * 
     * @param id 外发退货ID
     * @return 结果
     */
    public int deleteOutsourceReturnById(Long id);
    public int deleteOutsourceReturnProcessById(Long id);

    /**
     * 批量删除外发退货
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceReturnByIds(String[] ids);
}
