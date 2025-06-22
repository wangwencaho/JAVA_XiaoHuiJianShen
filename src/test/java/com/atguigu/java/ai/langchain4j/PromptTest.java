package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testSystemMessage() {
        String answer = separateChatAssistant.chat(3,"核心怎么训练");
        System.out.println(answer);
    }


    @Autowired
    private SeparateChatAssistant separateChatAssistant1;
    @Test
    public void testV() {
        String answer1 = separateChatAssistant1.chat1(10,"我是环环");
        System.out.println(answer1);
        String answer2 = separateChatAssistant1.chat1(10,"我是谁");
        System.out.println(answer2);
    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;

    @Test

    public void testMemory() {
        String answer = memoryChatAssistant.chat("雨天做什么训练合适");
        System.out.println(answer);
        String answer2 = memoryChatAssistant.chat("那就力量训练吧");
        System.out.println(answer2);
        String answer3 = memoryChatAssistant.chat("感谢");
        System.out.println(answer3);
    }

    @Test
    public void testUserInfo() {
        String answer = separateChatAssistant.chat3(5, "我是谁，我多大了", "翠花", 18);
        System.out.println(answer);
    }
}



