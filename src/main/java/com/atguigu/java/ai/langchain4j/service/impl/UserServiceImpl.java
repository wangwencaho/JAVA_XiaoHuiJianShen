package com.atguigu.java.ai.langchain4j.service.impl;

import com.atguigu.java.ai.langchain4j.entity.User;
import com.atguigu.java.ai.langchain4j.mapper.UserMapper;
import com.atguigu.java.ai.langchain4j.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsersByOr(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 构建OR条件查询
        wrapper.like(user.getId() != null, "id", user.getId())
                .or(user.getName() != null)
                .like(user.getName() != null, "name", user.getName())
                .or(user.getGender() != null)
                .like(user.getGender() != null, "gender", user.getGender())
                .or(user.getIsMember() != null)
                .like(user.getIsMember() != null, "isMember", user.getIsMember())
                .or(user.getAge() != null)
                .like(user.getAge() != null, "age", user.getAge())
                .or(user.getLevel() != null)
                .like(user.getLevel() != null, "level", user.getLevel());

        return userMapper.selectList(wrapper);
    }
}
