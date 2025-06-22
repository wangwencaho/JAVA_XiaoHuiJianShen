一、项目概述
    JavaAI 是基于 Spring Boot 3.2.6 开发的人工智能应用，整合 LangChain4j、MyBatis-Plus、Knife4j 等技术，支持 OpenAI、通义千问等多模态大
语言模型，具备文档解析、向量存储、智能问答等功能，可快速搭建 AI 相关服务。
二、技术栈


| 类别&#xA;     | 技术 / 工具&#xA;       | 版本&#xA;          | 作用&#xA;           |
| ----------- | ------------------ | ---------------- | ----------------- |
| 基础框架&#xA;   | Spring Boot&#xA;   | 3.2.6&#xA;       | 快速构建 Web 应用&#xA;  |
| 大语言模型&#xA;  | LangChain4j&#xA;   | 1.0.0-beta3&#xA; | 对接各类大语言模型&#xA;    |
| API 文档&#xA; | Knife4j&#xA;       | 4.3.0&#xA;       | 生成和管理 API 文档&#xA; |
| 持久层&#xA;    | MyBatis-Plus&#xA;  | 3.5.11&#xA;      | 简化数据库操作&#xA;      |
| 数据库&#xA;    | MySQL&#xA;         | 8.0+&#xA;        | 存储结构化数据&#xA;      |
| 数据库&#xA;    | MongoDB&#xA;       | 6.0+&#xA;        | 存储非结构化数据&#xA;     |
| 向量存储&#xA;   | Pinecone&#xA;      | -&#xA;           | 存储和检索向量数据&#xA;    |
| 文档解析&#xA;   | Apache PDFBox&#xA; | -&#xA;           | 解析 PDF 文档&#xA;    |

三、功能特性

1.  **多模型集成**：无缝对接 OpenAI、通义千问等主流大语言模型，满足不同场景需求。


2.  **文档处理**：支持 PDF 文档解析，并将内容向量化，便于后续检索和分析。


3.  **智能问答**：基于 RAG（检索增强生成）技术，实现高效的知识库问答。


4.  **流式响应**：支持大语言模型流式输出，实时返回回答内容。


5.  **数据持久化**：同时支持 MySQL 和 MongoDB，灵活存储不同类型数据。


四、环境准备



### （一）必备软件&#xA;



1.  **JDK**：安装 JDK 17 及以上版本，配置 `JAVA_HOME` 环境变量。例如在 Linux/macOS 系统中，可在 `~/.bashrc` 或 `~/.bash_profile` 文件中添加 `export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64` （路径需根据实际安装情况修改），然后执行 `source ~/.bashrc` 或 `source ~/.bash_profile` 使配置生效。


2.  **Maven**：安装 Maven 3.8 及以上版本，配置 `MAVEN_HOME` 环境变量，并将 `$MAVEN_HOME/bin` 添加到系统 `PATH` 中。


3.  **MySQL**：安装 MySQL 8.0 及以上版本，创建名为 `guiguxiaozhi` 的数据库，字符集设置为 `UTF-8`。


4.  **MongoDB**：安装 MongoDB 6.0 及以上版本，启动 MongoDB 服务。


### （二）可选配置&#xA;

若需使用 Pinecone 向量存储，需注册 Pinecone 账号，获取 API 密钥和环境信息。


五、配置说明



项目核心配置文件为 `src/main/resources/application.properties`，按以下说明修改配置：




```
\# 服务器端口，可根据需求修改


server.port=8080

\# OpenAI/通义千问 API 配置


\# 通义千问 API 基础地址


langchain4j.open-ai.chat-model.base-url=https://dashscope.aliyuncs.com/compatible-mode/v1

\# 通义千问 API 密钥，替换为你的实际密钥


langchain4j.open-ai.chat-model.api-key=YOUR\_TONGYI\_API\_KEY

\# 使用的模型名称，如 deepseek-v3


langchain4j.open-ai.chat-model.model-name=deepseek-v3


\# 通义千问其他模型配置


langchain4j.community.dashscope.chat-model.api-key=YOUR\_TONGYI\_API\_KEY

langchain4j.community.dashscope.chat-model.model-name=qwen-max


\# MongoDB 配置


spring.data.mongodb.uri=mongodb://localhost:27017/chat\_memory\_db

\# MySQL 配置


spring.datasource.url=jdbc:mysql://localhost:3306/guiguxiaozhi?useUnicode=true\&characterEncoding=UTF-8\&serverTimezone=Asia/Shanghai\&useSSL=false

spring.datasource.username=root


spring.datasource.password=123456

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

\# MyBatis-Plus 日志配置，便于调试 SQL

mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

六、项目结构





```
JavaAI/


├── src/


│   ├── main/


│   │   ├── java/


│   │   │   └── com/


│   │   │       └── atguigu/


│   │   │           └── java/


│   │   │               └── ai/


│   │   │                   ├── config/          # 配置类，如数据源、模型连接配置


│   │   │                   ├── controller/      # 控制器层，处理 HTTP 请求


│   │   │                   ├── entity/          # 实体类，对应数据库表结构


│   │   │                   ├── mapper/          # 数据库映射，定义 SQL 操作


│   │   │                   ├── service/         # 服务层，实现业务逻辑


│   │   │                   └── tools/           # 工具类，提供通用功能


│   │   └── resources/


│   │       ├── mapper/          # MyBatis SQL 映射文件


│   │       ├── static/          # 静态资源，如 CSS、JS 文件


│   │       ├── templates/       # 模板文件


│   │       └── application.properties # 核心配置文件


│   └── test/                    # 测试代码，包含单元测试和集成测试


└── pom.xml                      # Maven 配置文件，管理项目依赖和构建
```

七、快速启动



### （一）克隆项目&#xA;

在命令行执行以下命令，将项目克隆到本地：


```
git clone https://github.com/your-repo/JavaAI.git

cd JavaAI
```

### （二）构建项目&#xA;

使用 Maven 命令构建项目，下载所需依赖并打包：


```
mvn clean package
```

构建过程中若出现依赖下载失败，可尝试清理 Maven 本地仓库（默认路径为 `~/.m2/repository` ），然后重新构建。


### （三）运行项目&#xA;

执行以下命令启动项目：

```
java -jar target/JavaAI-1.0-SNAPSHOT.jar
```

项目启动成功后，控制台会输出启动日志。若启动失败，可根据日志信息排查问题，如端口被占用、配置错误等。


### （四）访问 API 文档&#xA;

打开浏览器，访问 `http://localhost:8080/doc.html` ，即可查看项目 API 文档，通过文档可测试和调用接口。


八、API 使用示例

### （一）智能问答接口&#xA;

使用 `POST` 方法访问 `/api/chat/ask` ，请求体为 JSON 格式：


```
{


&#x20; "question": "请介绍一下 Spring Boot 的特性",


&#x20; "context": "用户正在学习 Java 开发"

}
```
接口会根据问题和上下文，调用大语言模型生成回答。

### （二）文档上传接口&#xA;

使用 `POST` 方法访问 `/api/document/upload` ，请求头设置 `Content-Type: multipart/form-data` ，选择要上传的 PDF 文件提交请求，即可完成文档上传和解析。

九、数据库初始化


项目采用 Flyway 进行数据库迁移，启动项目时会自动执行 `src/main/resources/db/migration` 目录下的 SQL 脚本，创建所需表结构。若需手动初始化数据库，可在 MySQL 客户端执行对应 SQL 脚本。

十、常见问题

### （一）API 密钥配置错误&#xA;
确保 `application.properties` 中的 `${TONGYI_API_KEY}` 替换为正确的 API 密钥，也可通过系统环境变量设置 `TONGYI_API_KEY` 。

### （二）依赖冲突&#xA;
若构建时出现依赖冲突，使用 `mvn dependency:tree` 命令查看依赖树，找出冲突依赖并排除或调整版本。


### （三）性能问题&#xA;

可通过调整以下参数优化性能：

```
\# 调整线程池核心线程数
spring.task.execution.pool.core-size=10
\# 调整线程池最大线程数
spring.task.execution.pool.max-size=20
\# 调整 HikariCP 连接池最大连接数
spring.datasource.hikari.maximum-pool-size=10
\# 调整 HikariCP 连接池最小空闲连接数
spring.datasource.hikari.minimum-idle=5
```
十一、贡献指南
1.  Fork 本仓库到自己的 GitHub 账号。
2.  克隆 Fork 后的仓库到本地：`git clone ``https://github.com/your-username/JavaAI.git` 。
3.  创建特性分支：`git checkout -b feature/your-feature` 。
4.  开发完成后，提交代码：`git commit -m 'Add some feature'` 。
5.  推送分支到远程仓库：`git push origin feature/your-feature` 。
6.  在原仓库创建 Pull Request，描述清楚功能或修改内容。


