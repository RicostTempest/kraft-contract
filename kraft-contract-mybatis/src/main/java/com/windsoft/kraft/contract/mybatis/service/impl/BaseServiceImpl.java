package com.windsoft.kraft.contract.mybatis.service.impl;


import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import com.windsoft.kraft.contract.mybatis.mapper.BaseMapper;
import com.windsoft.kraft.contract.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Date;

public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected M baseMapper;

    @Override
    public JsonResult info(Long id) {
        T  entity = baseMapper.selectByPrimaryKey(id);
        if (entity != null){
            return JsonResult.success(entity);
        }
        return JsonResult.error("未查找到数据");
    }

    @Override
    public JsonResult add(T entity) {
        if (!ObjectUtils.isEmpty(entity)){
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            baseMapper.insert(entity);
            return JsonResult.success(entity);
        }
        return JsonResult.error("对象为空");
    }

    @Override
    public JsonResult update(T entity) {
        if (!ObjectUtils.isEmpty(entity)){
            entity.setCreateTime(new Date());
            baseMapper.updateByPrimaryKey(entity);
            return JsonResult.success();
        }
        return JsonResult.error("对象为空");
    }

    @Override
    public JsonResult delete(T entity) {
        if (!ObjectUtils.isEmpty(entity)){
            baseMapper.delete(entity);
            return JsonResult.success();
        }
        return JsonResult.error("对象为空");
    }

    @Override
    public JsonResult deleteById(Long id) {
        if (!ObjectUtils.isEmpty(id)){
            baseMapper.deleteByPrimaryKey(id);
            return JsonResult.success();
        }
        return JsonResult.error("对象为空");
    }

    @Override
    public JsonResult deleteByIds(Long[] ids) {
        if (ids.length>0){
            baseMapper.deleteByIds(CommonUtils.longArrayToString(ids,","));
            return JsonResult.success();
        }
        return JsonResult.error("输入数组为空");
    }
}
