package com.atguigu.java.ai.langchain4j.service;

import com.atguigu.java.ai.langchain4j.entity.Coach;
import com.atguigu.java.ai.langchain4j.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CourseService extends IService<Course> {
    List<Course> getCoursesByOr(Course course);
}
