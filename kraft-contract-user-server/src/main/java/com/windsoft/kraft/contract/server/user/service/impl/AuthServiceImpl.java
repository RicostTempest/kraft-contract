package com.windsoft.kraft.contract.server.user.service.impl;

import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Student;
import com.windsoft.kraft.contract.mybatis.domain.Teacher;
import com.windsoft.kraft.contract.mybatis.domain.UserAuthentication;
import com.windsoft.kraft.contract.server.user.dto.AuthAccountDto;
import com.windsoft.kraft.contract.server.user.entity.AuthEntity;
import com.windsoft.kraft.contract.mybatis.entity.InfoEntity;
import com.windsoft.kraft.contract.server.user.mapper.StudentMapper;
import com.windsoft.kraft.contract.server.user.mapper.TeacherMapper;
import com.windsoft.kraft.contract.server.user.mapper.UserAuthenticationMapper;
import com.windsoft.kraft.contract.server.user.service.AuthService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private UserAuthenticationMapper userAuthenticationMapper;

    @Override
    public JsonResult authUser(AuthEntity entity, Long id) {
        InfoEntity infoEntity = null;
        if (ObjectUtils.allNotNull(entity)){
            if (entity.getIsStudent() == 1){
                Condition condition = new Condition(Student.class);
                Example.Criteria criteria = condition.createCriteria();
                criteria.andEqualTo("number",entity.getNumber());
                infoEntity = (InfoEntity) studentMapper.selectOneByExample(condition);
            }else {
                Condition condition = new Condition(Teacher.class);
                Example.Criteria criteria = condition.createCriteria();
                criteria.andEqualTo("number",entity.getNumber());
                infoEntity = (InfoEntity) teacherMapper.selectOneByExample(condition);
            }
        }else {
            return JsonResult.error("数据错误");
        }
        if (infoEntity != null){
            Condition condition = new Condition(UserAuthentication.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("userId",id);
            UserAuthentication userAuthentication = userAuthenticationMapper.selectOneByExample(condition);
            if (userAuthentication!=null){
                return JsonResult.error("账号已被认证");
            }
            if (CommonUtils.password(entity.getPassword()).equals(infoEntity.getPassword())){
                userAuthentication = new UserAuthentication();
                userAuthentication.setUserId(id);
                userAuthentication.setAuthenticationId(infoEntity.getId());
                userAuthentication.setIsStudent(entity.getIsStudent());
                userAuthentication.setCreateTime(new Date());
                userAuthentication.setUpdateTime(new Date());
                userAuthenticationMapper.insert(userAuthentication);
                return JsonResult.success(entity);
            }
            return JsonResult.error("密码错误");
        }
        return JsonResult.error("账号错误");
    }

    @Override
    public JsonResult getAccount(Long id) {
        AuthAccountDto accountDto = new AuthAccountDto();
        Condition condition = new Condition(UserAuthentication.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userId",id);
        UserAuthentication authentication = userAuthenticationMapper.selectOneByExample(condition);
        if (authentication != null) {
            Student student = studentMapper.selectByPrimaryKey(authentication.getAuthenticationId());
            accountDto.setAccount(student.getNumber());
            accountDto.setIsStudent(authentication.getIsStudent());
            accountDto.setId(authentication.getId());
            return JsonResult.success(accountDto);
        }
        return JsonResult.success(accountDto);
    }

    @Override
    public JsonResult freeBand(Long id) {
        if (userAuthenticationMapper.deleteByPrimaryKey(id) != 0){
            return JsonResult.success("解绑成功");
        }
        return JsonResult.error("解绑失败");
    }


}
