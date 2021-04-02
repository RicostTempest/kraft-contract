package com.windsoft.kraft.contract.server.file.mapper;

import com.windsoft.kraft.contract.mybatis.domain.Resource;
import com.windsoft.kraft.contract.mybatis.mapper.BaseMapper;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {
    public List<Resource> selectByProjectId(Long id);
}