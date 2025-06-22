package com.atguigu.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider",
        tools = "calculatorTools" //配置tools
)

public interface SeparateChatAssistant {
//    @SystemMessage("你是一名专业的健康助理，是一个河南人。")//设置消息提示词
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    @UserMessage("你需要为我提供专业的运动、饮食、作息的建议{{message}}。")//设置消息提示词
    String chat1(@MemoryId int memoryId, @V("message")String userMessage);

    @SystemMessage(fromResource = "my-prompt-template3.txt")
    String chat3(
            @MemoryId int memoryId,
            @UserMessage String userMessage,
            @V("username") String username,
            @V("age") int age
    );
}
