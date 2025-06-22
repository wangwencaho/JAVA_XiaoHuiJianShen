package com.atguigu.java.ai.langchain4j.service.impl;

import com.atguigu.java.ai.langchain4j.entity.Coach;
import com.atguigu.java.ai.langchain4j.mapper.CoachMapper;
import com.atguigu.java.ai.langchain4j.service.CoachService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl extends ServiceImpl<CoachMapper, Coach> implements CoachService {

    /**
     * 根据传入的Coach对象条件，查询对应的教练信息是否存在
     *
     * @param coach 封装了查询条件（如教练姓名、性别、日期、时间、id等）的Coach对象
     * @return 查询到的Coach对象，如果不存在则返回null
     */
    @Autowired
    private CoachMapper coachMapper; // 假设存在CoachMapper接口

    @Override
    public List<Coach> getCoachesByOr(Coach queryCoach) {
        QueryWrapper<Coach> wrapper = new QueryWrapper<>();

        // 构建OR条件查询
        wrapper.like(queryCoach.getName() != null, "name", queryCoach.getName())
                .or(queryCoach.getGender() != null)
                .like(queryCoach.getGender() != null, "gender", queryCoach.getGender());
        return coachMapper.selectList(wrapper); // 直接返回列表，无需强制转换
    }
}