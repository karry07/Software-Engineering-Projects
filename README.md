# 在线简历投递平台
作者：高铠炜 李凯玄 王天宇 赵新苑

## 项目介绍

本项目是基于前端vue，后端springboot，数据库mysql开发的一套前后端与前后台均分离的在线简历投递平台项目，也是南开大学2024学年软件工程课程的课设项目之一。下面分别来介绍所用到的技术栈和整体的功能介绍。

### 技术栈

后端技术栈：

- SpringBoot
- Mybatis
- Mysql

前端技术栈：

- Vue
- ElementUI
- axios


其中，使用的jdk版本为1.8版本,node.js版本为16.17.0版本,maven版本为3.9.6版本。

### 功能介绍

本系统分为系统管理员，求职者，企业HR三个角色，具体的功能可在使用过程中自行感受。下面大概陈述一下我们的基本功能。

1. 企业：发布招聘信息、审核收到的应聘信息、企业信息管理、评论公开的求职者、发布论坛帖子等等
1. 求职者：根据招聘信息投递简历和应聘信息、管理自己的应聘信息、简历辅助生成和简历模板的推荐、发布论坛帖子，评论招聘信息、公开自己的求职信息给企业等等
1. 管理员：首页广告和新闻的管理、用户和招应聘信息的管理以及整个网站的其他功能的管理（简单来说，管理每一个数据库表）

以上是我们项目的基本功能，具体的创新功能在期末报告里呈现，也可以自行体验项目


### 登陆访问页面
springboot使用8082端口，vue使用8081端口

1. 前台访问地址（html）

  http://127.0.0.1:8082/resumeDelivery/front/pages/login/login.html
  
  登陆用户包括求职者与企业。
  
  求职者用户：lisi     密码：1

  求职者用户：zhangsan    密码：123456

  企业用户：tengxun    密码：1
  
2. 后台访问地址（纯vue）

http://127.0.0.1:8081

登陆用户包括求职者、企业和管理员。

管理员用户：admin     密码：123456

求职者用户：lisi     密码：1

企业用户：tengxun    密码：1


## 代码框架

关于在线简历投递平台的代码框架，下面大致给出前后端的框架介绍：

* 前端框架

```
recruit-web/
├── vue.config.js      
├── package.json         # 项目配置文件
├── babel.config.js
├── src/                 # 源代码目录
│   ├── main.js          # Vue 实例化入口文件，也是整个应用的入口
│   ├── App.vue          # Vue 根组件，也是整个应用的入口
│   ├── assets/          # 静态资源文件夹
│   ├── components/      # 公共组件
│   ├── router/          # 路由
│   ├── utils/           # 各种小工具和帮助函数
│   └── views/           # 视图层组件（前端后台项目核心部分）
├── public/              # 公共资源目录
│   ├── index.html       # html模板（单页面应用）
│   └── favicon.ico      # 网页图标
├── dist/
└── node_modules/        # 项目依赖的 node 模块
```

* 后端框架

```
recruit-sys/
├── README.md          # 后端文件readme
├── resumeDelivery.iml
├── pom.xml            # maven配置文件
├── src/               # 源代码目录
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── SpringbootSchemaApplication.java
│   │   │   │   ├── annotation/        # 自定义注解 登录 验证
│   │   │   │   ├── config/            # 配置类
│   │   │   │   ├── controller/        # 控制层
│   │   │   │   ├── dao/               # 数据持久层 操作层
│   │   │   │   ├── entity/            # 实体层（对应数据库表格）
│   │   │   │   ├── interceptor/       # 拦截器
│   │   │   │   ├── service/           # 服务层
│   │   │   │   ├── utils/             # 工具类
│   │   ├── resources/                 # 资源配置目录
│   │   │   ├── application.yml        #应用配置文件
│   │   │   ├── static/
│   │   │   │   ├── upload/            # 图片文件
│   │   │   ├── mapper/                # Dao
│   │   │   ├── front/
│   │   │   │   ├── front/             # 前台界面代码（前台核心部分）
│   └── test/
│       ├── java/
│       │   ├── com/
│       │   │   ├── SpringbootSchemaApplicationTests.java
└── target/
└── db/
```


## 设计过程
### 数据库设计
2024.5.15之前设计完项目的数据库表结构，具体的分工如下：

| 小组成员 |        具体分工任务         |
| -------- | -------------------------- |
| 王天宇   | 用户信息类                  |
| 高铠炜   | 求职信息类                  |
| 李凯玄   | 评论表、岗位、友情链接等设计 |
| 赵新苑   | 新闻、留言、帖子设计         |

* 具体的数据库内容详见mysql_readme.md文件   
### 后端设计
2024.5.24之前完成后端Springboot框架搭建。以下是具体的分工和流程：

#### 项目分工

##### 1. 高铠炜
- **职责**：总体协调和整合，确保项目进度和质量，作为主要负责人担任一些配置模块的设计和开发，主要负责控制层和服务层开发，同时协调整个后端框架的总体设计。
- **具体任务**：
  - **SpringbootSchemaApplication.java**: 负责项目主应用程序的配置和启动。
  - **application.yml**: 负责应用配置文件的编写和维护。
  - **controller/ 控制层**: 负责API接口的设计和实现，处理前端请求。
  - **service/ 服务层**: 负责业务逻辑的实现，服务于控制层和数据持久层之间的逻辑处理。

##### 2. 李凯玄
- **职责**：负责数据持久层和工具类、自定义注解等开发。
- **具体任务**：
  - **dao/ 数据持久层**: 负责与数据库交互的DAO层的设计和实现。
  - **annotation/ 自定义注解**: 负责自定义注解的设计和实现，主要用于登录和验证功能。
  - **utils/ 工具类**: 负责编写常用的工具类函数，支持其他模块的开发。
  - **resources/static/upload**: 负责图片文件上传相关的处理。


##### 3. 王天宇
- **职责**：负责服务层的设计和开发，协作控制层负责人完成控制层的设计，单独负责请求拦截器和配置类的设计，并在后续前端开发工作中负责后端的所有bug处理。
- **具体任务**：
  - **service/ 服务层**: 负责业务逻辑的实现，服务于控制层和数据持久层之间的逻辑处理。
  - **interceptor/ 拦截器**: 负责请求拦截器的设计和实现，确保请求的安全性和合法性。
  - **config/ 配置类**: 负责应用的配置文件和全局设置。

##### 4. 赵新苑
- **职责**：负责实体层相关的设计和开发，协作开发数据持久层。
- **具体任务**：
  - **entity/ 实体层**: 负责实体类的设计和实现，确保与数据库表格对应。
  - **mapper/**: 和数据持久层负责人合作设计编写数据库操作的mapper文件。

#### 项目流程

##### 设计阶段（2024年5月10日 - 2024年5月15日）
- **5月10日**：项目启动，根据之前完成的作业进行可行性讨论。负责人组织分工讨论，完成分工，并创建仓库提供优秀的框架供组员学习。
- **5月11日 - 5月15日**：各成员根据分工完成数据库设计，确定整体的设计流程，确定使用的代码框架，确定接口和模块之间的交互方式。初步完成主应用程序配置和拦截器实现，完成初步的配置文件。开始开发实体层和数据持久层。

##### 开发阶段（2024年5月16日 - 2024年5月24日）
- **5月16日 - 5月18日**：
开始逐渐实现数据持久层和实体层的基础结构搭建，编写部分DAO和实体类。与此同时，根据完成的部分开始控制层和服务层的基础结构搭建，编写部分控制器和服务类。完全实现工具类和自定义注解的基础，初步测试。
- **5月19日 - 5月22日**：
完成所有DAO、实体类、控制器和服务类的编写，开始实现基本的测试。完善配置、工具类和拦截器功能，并整合其他模块的代码。
- **5月23日 - 5月24日**：
各成员自测自己的模块，修复发现的Bug。负责人协调集成测试，确保各模块之间的无缝衔接。


### 前端后台设计
在期中答辩（6月3日）前完成前端后台的设计，本部分和后端内容同时进行开发。本模块大体上主要根据不同页面来分开设计，属于并行共同工作。

在设计不同页面（5月25日）之前，由赵新苑实现vue.config.js，也就是负责Vue项目配置文件的编写和维护，确立了一些npm依赖。高铠炜负责确立依赖后的vue框架整体的搭建，包括componets和views下面的例如home.vue等的开发和路由设计，保证后台整体的样式设计。王天宇继续负责完善对应的请求拦截和token部分，同时负责部分utils的代码框架，协助处理vue框架里面的一些bug。李凯玄负责assets资源的收集，以及部分utils的内容，协作vue框架的部分设计。

由负责人实现整合完成后，开始长达一周的后端页面的开发。具体负责如下：

|  成员  |                                       负责模块                                        |
| ------ | ------------------------------------------------------------------------------------ |
| 高铠炜 | 整体协调参与所有界面的设计，单独负责个人中心，评论管理、系统管理未单独提及的部分以及登陆注册 |
| 李凯玄 | 单独负责友情链接管理、企业信息管理、部分求职者管理                                        |
| 王天宇 | 单独负责招聘应聘的设计开发，部分求职者管理，以及大部分页面的bug解决                        |
| 赵新苑 | 单独负责管理员信息管理，部分求职者管理，后去设计前台框架不再参与后台设计                    |

在完成这个之后，由李凯玄测试代码，保证代码的可行性，并协调王天宇进行更多bug的处理。高铠炜负责简历导出和腾讯地图模块的创新设计。

### 前台设计
我们在springboot目录下实现了前台设计，本质上还是基于Vue实现的，除了基础的一些界面框架是在期中答辩前完成，其他的均在期中答辩后完成。

6月3号前，高铠炜和赵新苑合作初步设计了前台的大致框架，由赵新苑完成index.html和home.html的设计，并收集部分css以及一些必要的js。高铠炜设计登录注册界面，并和赵新苑合作优化index和home的布局。

6月3号以后，小组集中注意力解决前台的页面设计。同样也是根据不同界面分别单独设计，最后由负责人统一协调负责。下面表格是6月3号以后的负责任务

|  成员  |                                                    负责的基本模块                                                    |
| ------ | ------------------------------------------------------------------------------------------------------------------- |
| 高铠炜 | 整体协调所有界面的设计，单独负责全部的新闻和招聘页面、应聘的部分页面和论坛页面，协调解决一些bug，一些API调用的设计           |
| 李凯玄 | 引入layui和elementui，留言板和友情链接的设计，单独负责全部的个人中心、收藏页面，完成评论模块的设计，后续作相关的报告编写工作 |
| 王天宇 | 负责应聘信息的具体设计，解决其他模块里面所有尚存在的和后端交互的bug，负责大部分的js文件，并实现收藏招聘信息，并作一些创新尝试 |
| 赵新苑 | 完善留言板、友情链接的设计， 单独负责全部的求职者信息的设计，一些add页面的设计                                            |

### 其他工作

源代码部分的其他内容：

|        具体工作         | 负责成员 |
| ---------------------- | -------- |
| 期中答辩人、讲解视频录制 | 高铠炜   |
| 讲解视频剪辑            | 李凯玄   |
| 安装运行说明            | 王天宇   |

开发文档编写部分：

|  成员  |                负责部分                |
| ------ | ------------------------------------- |
| 高铠炜 | UI设计和性能测试、项目管理              |
| 李凯玄 | 需求分析与系统设计（除UI设计）、功能测试 |
| 王天宇 | 用户手册                               |
| 赵新苑 | 项目概述                               |
