package com.atguigu.java.ai.langchain4j.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String classname;
    private Integer courseid;
    private String schedule;
    private String time;
    private Integer capacity;
}