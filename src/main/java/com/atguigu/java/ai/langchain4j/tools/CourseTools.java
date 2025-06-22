package com.atguigu.java.ai.langchain4j.tools;

import com.atguigu.java.ai.langchain4j.entity.Course;
import com.atguigu.java.ai.langchain4j.service.CourseService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseTools {

    @Autowired
    private CourseService courseService;

    @Tool(name = "添加课程", value = "根据参数添加新课程信息，返回添加成功或失败信息")
    public String addCourse(Course course) {
        // 防止大模型幻觉设置了id
        course.setId(null);
        if (courseService.save(course)) {
            return "课程添加成功";
        } else {
            return "课程添加失败";
        }
    }

    @Tool(name = "删除课程", value = "根据课程ID删除对应课程信息，返回删除结果")
    public String deleteCourse(
            @P(value = "课程ID") Integer courseId
    ) {
        if (courseService.removeById(courseId)) {
            return "课程删除成功";
        } else {
            return "课程删除失败，可能ID不存在";
        }
    }

    @Tool(name = "查询课程", value = "根据条件查询课程信息，返回课程列表或空结果")
    public String queryCourse(
            @P(value = "课程名称", required = false) String classname,
            @P(value = "课程编号", required = false) Integer courseid,
            @P(value = "上课安排", required = false) String schedule,
            @P(value = "上课时间", required = false) String time
    ) {
        Course queryCourse = new Course();
        queryCourse.setClassname(classname);
        queryCourse.setCourseid(courseid);
        queryCourse.setSchedule(schedule);
        queryCourse.setTime(time);

        List<Course> courses = courseService.getCoursesByOr(queryCourse);

        if (courses != null && !courses.isEmpty()) {
            StringBuilder result = new StringBuilder("找到以下课程：\n");
            for (Course course : courses) {
                result.append("课程名称：").append(course.getClassname())
                        .append("，课程编号：").append(course.getCourseid())
                        .append("，上课安排：").append(course.getSchedule())
                        .append("，上课时间：").append(course.getTime())
                        .append("，容量：").append(course.getCapacity())
                        .append("\n");
            }
            return result.toString();
        } else {
            return "未找到符合条件的课程";
        }
    }

    @Tool(name = "更新课程信息", value = "根据课程id更新课程信息，返回更新结果")
    public String updateCourse(Course course) {
        if (course.getId() == null) {
            return "更新失败：缺少课程ID";
        }

        if (courseService.updateById(course)) {
            return "课程信息更新成功";
        } else {
            return "课程信息更新失败，可能ID不存在";
        }
    }
}