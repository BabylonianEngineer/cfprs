# cfprs
Cost File Parsing and Retrieval System（造价文件解析及检索系统_后端）
# 造价文件解析及检索系统（CFPRS）简介
    解析Excel造价文件并存储到MySQL中，然后将待检索的数据实时同步到Elasticsearch，设计合适的索引结构，通过网页向用户提供造价数据检索服务，并可视化造价数据的详细内容。
# 架构说明
    CFPRS系统分为三个模块，包括文件解析模块、数据检索模块和信息可视化模块:
        文件解析模块:
            设计了造价数据解析实体层级模型，使用Apache POI读取造价文件信息，将每条信息解析成MySQL实体对象，然后保存到MySQL数据库。利用MySQL对事务的支持，实现整个造价文件解析操作的原子化。解析完成后的待检索造价数据利用canal实现从MySQL到Elasticsearch的实时增量同步。
        数据检索模块
            设计了搜索框检索和高级检索两种功能，满足用户不同的检索需求，并实现了关键词的自动补全提示、搜索结果高亮、过滤、排序和分页。
        信息可视化模块:
            除了显示造价数据详细信息之外，还设计了两种可视化形式，即项目结构树形图可视化和各层级子工程费用柱状图可视化，以帮助用户更好地分析造价数据。
# 运行环境
    名称      	    版本
    MySQL	            8.0.22
    Elasticsearch	    7.15.2
    Kibana	            7.15.2
    canal deployer	    1.1.5
    canal adapter	    1.1.5-alpha-2
    JDK	            1.8
# 启动步骤
### 后端：cfprs项目启动    
    启动MySQL
    启动Elasticsearch、启动Kibana
    启动Canal服务：
        先启动canal.deployer服务
        再启动canal.adapter服务
        日志查看：Canal安装目录下的 /canal.adapter-1.1.5-SNAPSHOT文件夹中，查看配置yml与同步日志logs
    cfprs项目启动：
        首次启动：
            修改application.yml中的相关配置
            IDEA点击运行:
                MySQL将自动创建表、Elasticsearch将自动创建索引。
            初始化Elasticsearch索引映射：
                运行src\test\java\com\hf\cfprs\service\ESServiceTest.java 中的initMapping()方法，将在索引中插入一条默认数据，以完成索引完整映射的生成。
        非首次启动：(MySQL、Elasticsearch中已有相关数据)
            IDEA点击运行
### 前端：cfprs_web项目启动
    nmp install 下载依赖包
    npm run serve

