package com.atguigu.java.ai.langchain4j.assistant;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;



@AiService(wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory"
)
public interface MemoryChatAssistant {
    @UserMessage("你是我的健身教练，请用东北话回答问题，并且添加一些去表情符号{{message}}")
    String chat(@V("message")String userMessage);
}