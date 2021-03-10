package com.windsoft.kraft.contract.mybatis.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.query.BaseQuery;

public interface BaseService<T> {

    /**
     * 根据查询条件获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    JsonResult getList(BaseQuery query);

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     * @return
     */
    JsonResult info(Long id);


    /**
     * 根据实体对象添加记录
     *
     * @param entity 实体对象
     * @return
     */
    JsonResult add(T entity);

    /**
     * 根据实体对象更新记录
     *
     * @param entity 实体对象
     * @return
     */
    JsonResult update(T entity);

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    JsonResult delete(T entity);

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return
     */
    JsonResult deleteById(Long id);

    /**
     * 根据ID删除记录
     *
     * @param ids 记录ID
     * @return
     */
    JsonResult deleteByIds(Long[] ids);
}
