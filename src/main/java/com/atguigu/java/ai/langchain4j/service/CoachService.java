package com.atguigu.java.ai.langchain4j.service;

import com.atguigu.java.ai.langchain4j.entity.Coach;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CoachService extends IService<Coach> {
    List<Coach> getCoachesByOr(Coach coach);
}
