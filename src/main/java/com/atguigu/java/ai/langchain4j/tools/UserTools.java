package com.atguigu.java.ai.langchain4j.tools;

import com.atguigu.java.ai.langchain4j.entity.User;
import com.atguigu.java.ai.langchain4j.service.UserService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTools {

    @Autowired
    private UserService userService;

    @Tool(name = "添加用户", value = "根据参数添加新用户信息，返回添加成功或失败信息")
    public String addUser(User user) {
        // 防止大模型幻觉设置了id
        user.setId(null);
        if (userService.save(user)) {
            return "用户添加成功";
        } else {
            return "用户添加失败";
        }
    }

    @Tool(name = "删除用户", value = "根据用户ID删除对应用户信息，返回删除结果")
    public String deleteUser(
            @P(value = "用户ID") Integer userId
    ) {
        if (userService.removeById(userId)) {
            return "用户删除成功";
        } else {
            return "用户删除失败，可能ID不存在";
        }
    }

    @Tool(name = "查询用户", value = "根据条件查询用户信息，返回用户列表或空结果")
    public String queryUser(
            @P(value = "用户姓名", required = false) String name,
            @P(value = "性别", required = false) String gender,
            @P(value = "是否会员", required = false) Integer isMember,
            @P(value = "年龄", required = false) Integer age,
            @P(value = "到期时间", required = false) String deadtime,
            @P(value = "开始时间", required = false) String starttime
    ) {
        User queryUser = new User();
        queryUser.setName(name);
        queryUser.setGender(gender);
        queryUser.setIsMember(isMember);
        queryUser.setAge(age);
        queryUser.setDeadtime(deadtime);
        queryUser.setStarttime(starttime);

        List<User> users = userService.getUsersByOr(queryUser);

        if (users != null && !users.isEmpty()) {
            StringBuilder result = new StringBuilder("找到以下用户：\n");
            for (User user : users) {
                result.append("用户ID：").append(user.getId())
                        .append("，姓名：").append(user.getName())
                        .append("，性别：").append(user.getGender())
                        .append("，是否会员：").append(user.getIsMember() == 1 ? "是" : "否")
                        .append("，年龄：").append(user.getAge())
                        .append("，到期时间：").append(user.getDeadtime())
                        .append("，开始时间：").append(user.getStarttime())
                        .append("\n");
            }
            return result.toString();
        } else {
            return "未找到符合条件的用户";
        }
    }

    @Tool(name = "更新用户信息", value = "根据用户id更新用户信息，返回更新结果")
    public String updateUser(User user) {
        if (user.getId() == null) {
            return "更新失败：缺少用户ID";
        }

        if (userService.updateById(user)) {
            return "用户信息更新成功";
        } else {
            return "用户信息更新失败，可能ID不存在";
        }
    }
}