package com.atguigu.java.ai.langchain4j.service.impl;
import com.atguigu.java.ai.langchain4j.entity.Course;
import com.atguigu.java.ai.langchain4j.mapper.CourseMapper;
import com.atguigu.java.ai.langchain4j.service.CourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getCoursesByOr(Course course){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        // 构建OR条件查询
        wrapper.like(course.getId() != null, "id", course.getId())
                .or(course.getClassname() != null)
                .like(course.getClassname() != null, "classname", course.getClassname())
                .or(course.getSchedule() != null)
                .like(course.getSchedule() != null, "schedule", course.getSchedule())
                .or(course.getCourseid() != null)
                .like(course.getCourseid() != null, "courseid", course.getCourseid());
        return courseMapper.selectList(wrapper);
    }

}
