package com.ruoyi.project.outsource.outsourceChecking.mapper;

import java.util.List;
import com.ruoyi.project.outsource.outsourceChecking.domain.OutsourceChecking;

/**
 * 外发对账Mapper接口
 * 
 * @author fangzhou
 * @date 2021-05-11
 */
public interface OutsourceCheckingMapper 
{
    /**
     * 查询外发对账
     * 
     * @param id 外发对账ID
     * @return 外发对账
     */
    public OutsourceChecking selectOutsourceCheckingById(Long id);

    /**
     * 查询外发对账列表
     * 
     * @param outsourceChecking 外发对账
     * @return 外发对账集合
     */
    public List<OutsourceChecking> selectOutsourceCheckingList(OutsourceChecking outsourceChecking);

    /**
     * 新增外发对账
     * 
     * @param outsourceChecking 外发对账
     * @return 结果
     */
    public int insertOutsourceChecking(OutsourceChecking outsourceChecking);

    /**
     * 修改外发对账
     * 
     * @param outsourceChecking 外发对账
     * @return 结果
     */
    public int updateOutsourceChecking(OutsourceChecking outsourceChecking);

    /**
     * 删除外发对账
     * 
     * @param id 外发对账ID
     * @return 结果
     */
    public int deleteOutsourceCheckingById(Long id);
    public int deleteOutsourceCheckingProcessById(Long id);

    /**
     * 批量删除外发对账
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOutsourceCheckingByIds(String[] ids);
}
