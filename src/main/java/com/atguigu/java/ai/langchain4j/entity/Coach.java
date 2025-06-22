package com.atguigu.java.ai.langchain4j.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    @TableId(type = IdType.AUTO)
    private Integer id;  // 表中id类型是int，这里用Integer匹配，也可根据实际情况用int
    private String name;  // 对应表中的coach_name字段
    private String gender;
    private String introduction;
}