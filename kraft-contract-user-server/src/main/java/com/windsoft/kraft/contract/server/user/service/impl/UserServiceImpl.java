package com.windsoft.kraft.contract.server.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.common.utils.RandomUtils;
import com.windsoft.kraft.contract.mybatis.domain.User;
import com.windsoft.kraft.contract.mybatis.query.BaseQuery;
import com.windsoft.kraft.contract.mybatis.service.impl.BaseServiceImpl;
import com.windsoft.kraft.contract.server.user.dto.UserListDto;
import com.windsoft.kraft.contract.server.user.mapper.UserMapper;
import com.windsoft.kraft.contract.server.user.query.UserInfoQuery;
import com.windsoft.kraft.contract.server.user.query.UserQuery;
import com.windsoft.kraft.contract.server.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    @Override
    public JsonResult getList(BaseQuery query) {
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        UserQuery userQuery = (UserQuery) query;

        //用户名
        if (!StringUtils.isEmpty(userQuery.getUsername())) {
            criteria.andLike("username","%"+userQuery.getUsername()+"%");
        }
        //电话号码
        if (!StringUtils.isEmpty(userQuery.getTelephone())) {
            criteria.andLike("telephone","%"+userQuery.getTelephone()+"%");
        }
        //电子邮箱
        if (!StringUtils.isEmpty(userQuery.getEmail())) {
            criteria.andLike("email","%"+userQuery.getEmail()+"%");
        }
        PageHelper.startPage(query.getPage(),query.getLimit());
        List<User> userList = baseMapper.selectByCondition(condition);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);

        return JsonResult.success(pageInfo);
    }

    @Override
    public JsonResult add(User entity) {
        if (entity != null){
            entity.setPassword(CommonUtils.password(entity.getPassword()));
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            baseMapper.insert(entity);
            return JsonResult.success("新建成功");
        }
        return JsonResult.error("实体为空");
    }

    @Override
    public JsonResult resetPasswordByIds(Long[] ids) {
        if (ids.length > 0) {
            List<User> userList = baseMapper.selectByIds(CommonUtils.longArrayToString(ids,","));
            userList.forEach(user -> {
                user.setPassword(CommonUtils.password(RandomUtils.getPassword()));
                baseMapper.updateByPrimaryKey(user);
            });
            return JsonResult.success();
        }
        return JsonResult.error();
    }

    @Override
    public JsonResult searchMember(UserInfoQuery query) {
        PageHelper.startPage(query.getPage(),query.getLimit());
        List<UserListDto> dtos = baseMapper.selectUserAuth(query);
        PageInfo<UserListDto> pageInfo = new PageInfo<UserListDto>(dtos);
        return JsonResult.success(pageInfo);
    }


}
