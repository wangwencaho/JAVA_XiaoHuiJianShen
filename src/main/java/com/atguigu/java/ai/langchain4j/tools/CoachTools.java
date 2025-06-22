package com.atguigu.java.ai.langchain4j.tools;

import com.atguigu.java.ai.langchain4j.entity.Coach;
import com.atguigu.java.ai.langchain4j.service.CoachService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoachTools {

    @Autowired
    private CoachService coachService;

    @Tool(name = "添加教练", value = "根据参数添加新教练信息，返回添加成功或失败信息")
    public String addCoach(Coach coach) {
        // 防止大模型幻觉设置了id
        coach.setId(null);
        if (coachService.save(coach)) {
            return "教练添加成功";
        } else {
            return "教练添加失败";
        }
    }

    @Tool(name = "删除教练", value = "根据教练ID删除对应教练信息，返回删除结果")
    public String deleteCoach(
            @P(value = "教练ID") Long coachId
    ) {
        if (coachService.removeById(coachId)) {
            return "教练删除成功";
        } else {
            return "教练删除失败，可能ID不存在";
        }
    }

    @Tool(name = "查询教练", value = "根据条件查询教练信息，返回教练列表或空结果")
    public String queryCoach(
            @P(value = "教练姓名", required = false) String name,
            @P(value = "性别", required = false) String gender,
            @P(value = "教练信息", required = false) String introduction
    ) {
        Coach queryCoach = new Coach();
        queryCoach.setName(name);
        queryCoach.setGender(gender);
        queryCoach.setIntroduction(introduction);

        // 直接调用方法获取列表，不再需要 Collections.singletonList 包装
        List<Coach> coaches = coachService.getCoachesByOr(queryCoach);

        if (coaches != null && !coaches.isEmpty()) {
            StringBuilder result = new StringBuilder("找到以下教练：\n");
            for (Coach coach : coaches) {
                result.append("教练：").append(coach.getName())
                        .append("，性别：").append(coach.getGender())
                        .append("，训练类型：").append(coach.getIntroduction())
                        .append("\n");
            }
            return result.toString();
        } else {
            return "未找到符合条件的教练";
        }
    }

    @Tool(name = "更新教练信息", value = "根据教练id更新教练信息，返回更新结果")
    public String updateCoach(Coach coach) {
        if (coach.getId() == null) {
            return "更新失败：缺少教练ID";
        }

        if (coachService.updateById(coach)) {
            return "教练信息更新成功";
        } else {
            return "教练信息更新失败，可能ID不存在";
        }
    }
}