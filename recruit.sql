/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 8.0.25 : Database - recruit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`recruit` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `recruit`;

/*Table structure for table `application` */

DROP TABLE IF EXISTS `application`;

CREATE TABLE `application` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '应聘信息表ID',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加表的时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次更新时间',
  `companynumber` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业编号',
  `companyname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业名称',
  `personincharge` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人',
  `contactphone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `position` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '招聘岗位',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `fullname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `resume` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简历',
  `ifaudit` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '否' COMMENT '是否审核',
  `auditresponse` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '审核回复',
  `status` enum('submitted','audited','interviewing','rejected','accepted') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'submitted' COMMENT '应聘当前状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1717071238060 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='Application information 应聘信息表';

/*Data for the table `application` */

insert  into `application`(`id`,`addtime`,`updatetime`,`companynumber`,`companyname`,`personincharge`,`contactphone`,`position`,`username`,`fullname`,`phone`,`resume`,`ifaudit`,`auditresponse`,`status`) values 
(1717060465537,'2024-05-30 17:14:24','2024-05-31 18:25:41','alibaba','阿里巴巴','蔡崇信','17737865523','网页设计与制作','zhangsan','张三','13512345678','http://localhost:8082/resumeDelivery/upload/1717060462780.pdf','否','谢谢您的支持，期待下次您再次选择我们公司。','audited'),
(1717060507979,'2024-05-30 17:15:07','2024-06-18 18:16:57','tengxun','腾讯','老马','13632146547','市场总监','lisi','李四','18866661234','http://localhost:8082/resumeDelivery/upload/1717060506299.pdf','是','你的简历很优秀','audited'),
(1717071238059,'2024-05-30 20:13:57','2024-05-31 20:38:25','bilibili','哔哩哔哩','陈睿','15358739737','财务分析','wangwu','王五','13323566498','http://localhost:8082/resumeDelivery/upload/1717071233096.pdf','否',NULL,'submitted');

/*Table structure for table `companyinfo` */

DROP TABLE IF EXISTS `companyinfo`;

CREATE TABLE `companyinfo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `companynumber` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业编号',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `companyname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业名称',
  `personincharge` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人',
  `contactphone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `companyemail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业邮箱',
  `companyintroduction` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '企业介绍',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `qiyebianhao` (`companynumber`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1717053043159 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='企业信息';

/*Data for the table `companyinfo` */

insert  into `companyinfo`(`id`,`addtime`,`companynumber`,`password`,`companyname`,`personincharge`,`contactphone`,`companyemail`,`companyintroduction`) values 
(1596200834701,'2024-05-27 21:07:14','tengxun','1','腾讯','老马','13632146547','13632146547@qq.com','QQ微信都是我家的'),
(1716553444334,'2024-05-24 20:24:04','bilibili','123456','哔哩哔哩','陈睿','15358739737','3179642912@qq.com','bilibili干杯！'),
(1717053043158,'2024-05-30 15:10:43','alibaba','123456','阿里巴巴','蔡崇信','17737865523','xiaomubiao@qq.com','实现一个小目标！');

/*Table structure for table `config` */

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='配置文件';

/*Data for the table `config` */

insert  into `config`(`id`,`name`,`value`) values 
(1,'首页图片1','http://localhost:8082/resumeDelivery/upload/1718774146675.png'),
(2,'首页图片2','http://localhost:8082/resumeDelivery/upload/1718774091853.jpg'),
(3,'首页图片3','http://localhost:8082/resumeDelivery/upload/1718774155055.jpg');

/*Table structure for table `educationinfo` */

DROP TABLE IF EXISTS `educationinfo`;

CREATE TABLE `educationinfo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `education` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历',
  `institution` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教育机构',
  `fieldofstudy` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专业领域',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1596203473682 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='Education Information';

/*Data for the table `educationinfo` */

insert  into `educationinfo`(`id`,`addtime`,`education`,`institution`,`fieldofstudy`) values 
(1596203425441,'2020-07-31 21:50:24','博士','南开大学','计算机科学'),
(1596203441455,'2020-07-31 21:50:40','硕士','南开大学','计算机科学'),
(1596203466812,'2020-07-31 21:51:06','本科','南开大学','计算机科学'),
(1596203473681,'2020-07-31 21:51:13','专科','南开大学','计算机科学');

/*Table structure for table `employment` */

DROP TABLE IF EXISTS `employment`;

CREATE TABLE `employment` (
  `id` bigint NOT NULL COMMENT '招聘ID',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `companynumber` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业编号',
  `companyname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业名称',
  `personincharge` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '负责人',
  `contactphone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `position` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '招聘岗位',
  `recruitnum` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '招聘人数',
  `jobcategory` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位类别',
  `education` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历',
  `picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片',
  `demand` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '岗位要求',
  `duty` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '工作职责',
  `thumbsupnum` int DEFAULT '0' COMMENT '赞',
  `crazilynum` int DEFAULT '0' COMMENT '踩',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='employment 招聘信息';

/*Data for the table `employment` */

insert  into `employment`(`id`,`addtime`,`companynumber`,`companyname`,`personincharge`,`contactphone`,`position`,`recruitnum`,`jobcategory`,`education`,`picture`,`demand`,`duty`,`thumbsupnum`,`crazilynum`) values 
(1596371998272,'2020-08-02 20:39:57','tengxun','腾讯','老马','13632146547','图形学研究员（AI动画方向）','20','计算机/互联网类','本科','http://localhost:8082/resumeDelivery/upload/1717054626846.jpg','1.计算机图形学专业方向，对动画、AI、动捕等有一定了解；\n2.具有较强的探索精神和编程实现能力；\n3.熟悉相关领域的研究动态和最新成果；\n4.有一定的动捕/动画经验者优先。','<p><span style=\"color: rgb(51, 51, 51);\">负责动画方面一些技术的探索和测试，比如人体动画生成和编辑、语音/音乐驱动人体动画等。</span></p>',2,1),
(1713363863719,'2024-04-17 22:24:22','tengxun','腾讯','老马','13632146547','市场总监','1','经营管理类','硕士','http://localhost:8082/resumeDelivery/upload/1717054366719.jpg','1.市场营销、新闻传播、传媒等相关专业，硕士及以上学历；\n2.五年以上互联网公司产品推广经验；\n3.有较强的市场感知能力，擅长数据挖掘及分析；','<p>	1、组织编制年度营销计划及营销费用、内部利润指标等计划；</p><p>	2、有权向总经理提出营销部、办事处、各营销点的业务经理（主任）、副经理人选；</p><p>	3、组织研究、拟定公司营销、市场开发方面的发展规划；</p><p>	4、组织拟订营销业务管理的各种规定、制度和内部机构设置；</p><p>	5、负责组织在编制范围内对所属部门的营销业务人员进行聘用、考核、调配、晋升、惩罚和解聘；</p>',0,0),
(1717052610474,'2024-05-30 15:03:29','bilibili','哔哩哔哩','陈睿','15358739737','财务分析','2','财务类','硕士','http://localhost:8082/resumeDelivery/upload/1717052607322.png','1、分析能力： 具备逻辑思考和分析问题的能力，能够识别和解决财务挑战。\n2、沟通能力： 能够将复杂的财务数据以简单明了的方式呈现给非财务人员，并向管理层提供有关业务决策的建议。\n3、决策能力： 能够参与业务决策，提供基于财务分析的建议，并评估不同决策对公司的影响。\n4、技术技能： 熟练使用电子表格软件、财务分析工具和数据库管理系统。\n5、风险管理： 能够评估并管理财务风险，制定风险管理策略。\n6、团队合作： 能够与其他部门和团队合作，共同解决公司的财务问题。','<p>1.提供决策支持</p><p>2.吸引投资者和债权人</p><p>3.监控经营绩效</p><p>4.规划预算和财务目标</p>',0,0),
(1717053292214,'2024-05-30 15:14:51','alibaba','阿里巴巴','蔡崇信','17737865523','网页设计与制作','5','计算机/互联网类','本科','http://localhost:8082/resumeDelivery/upload/1717053184862.jpg','1.基本掌握DW.JavaScript.PS和html等设计软件，对图片修改和视觉效果有认识；\n2.会基本的代码编写；\n3.具有良好的网页及平面设计能力，对于网页的制作开发及色彩的搭配有自己独到的间接和体会，思维活跃，有创意；','<p><span style=\"color: rgb(81, 88, 109);\">1.负责网站.页面内容的设计与编辑制作与发布；</span></p><p><span style=\"color: rgb(81, 88, 109);\">2.提升页面响应速度与访问体验；</span></p><p><span style=\"color: rgb(81, 88, 109);\">3.根据产品需求，分析实现页面优化效果；</span></p>',0,0);

/*Table structure for table `forum` */

DROP TABLE IF EXISTS `forum`;

CREATE TABLE `forum` (
  `id` bigint NOT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `userid` bigint NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `thumbsupnum` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `forum` */

insert  into `forum`(`id`,`addtime`,`userid`,`title`,`content`,`thumbsupnum`) values 
(1718605547637,'2024-06-17 14:25:47',1596372388838,'大学生要学软件工程','软件工程是一门必须的课，培养学生代码能力',115),
(1718772198389,'2024-06-19 12:43:17',1596372388838,'华为遥遥领先','我们唯一的民族企业，大家都去这里工作，为国家添砖加瓦。\n\n缺点就是虽然工资高，但是太累人了，不能吃苦的同学慎重！',252),
(1718775402920,'2024-06-19 13:36:42',1596200834701,'腾讯是一家人文关怀的大厂','我们是一家关心员工的大厂企业，向来以员工为本，欢迎应聘我们！',35);

/*Table structure for table `friendly_links` */

DROP TABLE IF EXISTS `friendly_links`;

CREATE TABLE `friendly_links` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `webname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '网站名称',
  `logo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'logo',
  `website` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '网址',
  `webinfo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '网站介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1717079731598 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='友情链接';

/*Data for the table `friendly_links` */

insert  into `friendly_links`(`id`,`addtime`,`webname`,`logo`,`website`,`webinfo`) values 
(1717073652798,'2024-05-30 20:54:11','南开大学计算机学院网','http://localhost:8082/resumeDelivery/upload/1717073616459.png','https://cc.nankai.edu.cn/','<p>这是南开大学计算机学院的官网介绍</p><p>这是南开大学的计算机的学院，不是学金融的学院</p>'),
(1717073772457,'2024-05-30 20:56:11','360安全网','http://localhost:8082/resumeDelivery/upload/1717073708559.jpg','https://www.360.com/','<p>拥有360全家桶，<span class=\"ql-size-large\">电脑不再“卡顿”</span></p><p><span class=\"ql-size-large\">360还有</span><span class=\"ql-size-large ql-font-serif\">哪吒汽车也推荐购买</span><span class=\"ql-size-large ql-font-monospace\"><span class=\"ql-cursor\">﻿</span></span></p>'),
(1717079731597,'2024-05-30 22:35:31','新浪微博','http://localhost:8082/resumeDelivery/upload/1717079714980.jpg','www.weibo.com','<p>微博热搜</p>');

/*Table structure for table `generaluser` */

DROP TABLE IF EXISTS `generaluser`;

CREATE TABLE `generaluser` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `fullname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `gender` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `yonghuming` (`username`) USING BTREE,
  KEY `fullname` (`fullname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1717061638907 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户';

/*Data for the table `generaluser` */

insert  into `generaluser`(`id`,`addtime`,`username`,`fullname`,`password`,`gender`,`phone`,`email`) values 
(1596200777877,'2020-07-31 21:06:17','zhangsan','张三','123456','男','13512345678','13512345678@qq.com'),
(1596372388838,'2020-08-02 20:46:28','lisi','李四','1','女','18866661234','18866661234@qq.com'),
(1717061638906,'2024-05-30 17:33:58','wangwu','王五','123456','男','13323566498','13323566498@qq.com');

/*Table structure for table `interview` */

DROP TABLE IF EXISTS `interview`;

CREATE TABLE `interview` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '面试id',
  `applicationid` bigint DEFAULT NULL COMMENT '应聘表id',
  `interviewtime` timestamp NOT NULL COMMENT '面试时间',
  `interviewer` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '面试官',
  `location` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '面试地点',
  `status` enum('scheduled','completed','cancelled') CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'scheduled' COMMENT '面试状态',
  `feedback` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '面试反馈',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_interview_application` (`applicationid`) USING BTREE,
  CONSTRAINT `fk_interview_application` FOREIGN KEY (`applicationid`) REFERENCES `application` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

/*Data for the table `interview` */

insert  into `interview`(`id`,`applicationid`,`interviewtime`,`interviewer`,`location`,`status`,`feedback`) values 
(21,NULL,'2024-05-20 20:59:50','Huateng Ma','online','cancelled',NULL);

/*Table structure for table `job_category` */

DROP TABLE IF EXISTS `job_category`;

CREATE TABLE `job_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `jobcategory` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1717079378580 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='岗位分类';

/*Data for the table `job_category` */

insert  into `job_category`(`id`,`addtime`,`jobcategory`) values 
(1596203279032,'2020-07-31 21:47:58','经营管理类'),
(1596203306345,'2020-07-31 21:48:25','公关/市场营销类'),
(1596203316886,'2020-07-31 21:48:35','贸易/销售/业务类'),
(1717051649372,'2024-05-30 14:47:28','财务类'),
(1717051678931,'2024-05-30 14:47:58','行政/人力资源管理类'),
(1717051716443,'2024-05-30 14:48:35','文职类'),
(1717051724809,'2024-05-30 14:48:44','客户服务类'),
(1717051738141,'2024-05-30 14:48:57','计算机/互联网类'),
(1717051747757,'2024-05-30 14:49:07','工厂类'),
(1717051755597,'2024-05-30 14:49:15','电子/通讯类'),
(1717051762353,'2024-05-30 14:49:22','机器类'),
(1717051785453,'2024-05-30 14:49:45','规划/建筑/建材类'),
(1717051797736,'2024-05-30 14:49:57','房地产/物业管理类'),
(1717051811440,'2024-05-30 14:50:10','金融/经济类'),
(1717051817870,'2024-05-30 14:50:16','设计类'),
(1717051826432,'2024-05-30 14:50:26','法律类'),
(1717051838687,'2024-05-30 14:50:37','酒店/餐饮类'),
(1717051852064,'2024-05-30 14:50:51','物流/交通运输类'),
(1717051882568,'2024-05-30 14:51:21','商场类'),
(1717051899990,'2024-05-30 14:51:39','电气/电力类'),
(1717051912768,'2024-05-30 14:51:52','咨询/顾问类'),
(1717051922462,'2024-05-30 14:52:01','化工/生物类'),
(1717051947394,'2024-05-30 14:52:26','文化/教育/体育/艺术类'),
(1717051965754,'2024-05-30 14:52:45','医疗卫生/护理/保健类'),
(1717051982054,'2024-05-30 14:53:01','新闻/出版/传媒类'),
(1717051991990,'2024-05-30 14:53:11','公共服务类'),
(1717052025506,'2024-05-30 14:53:44','印刷/染织类'),
(1717052034875,'2024-05-30 14:53:54','技工类'),
(1717052046907,'2024-05-30 14:54:06','其他专业类');

/*Table structure for table `job_post_comments` */

DROP TABLE IF EXISTS `job_post_comments`;

CREATE TABLE `job_post_comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `refid` bigint NOT NULL COMMENT '关联表ID',
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `userid` bigint NOT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1718775339352 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='招聘信息评论表';

/*Data for the table `job_post_comments` */

insert  into `job_post_comments`(`id`,`addtime`,`refid`,`content`,`userid`,`username`) values 
(1718774754920,'2024-06-19 13:25:54',1596372486541,'我真的很优秀，请大企业向我看过来！',1596372388838,NULL),
(1718775211679,'2024-06-19 13:33:31',1596285238983,'我能吃苦，请各位优先考虑一下我',1596200777877,NULL),
(1718775278811,'2024-06-19 13:34:38',1596372486541,'这位求职者整体素质都很优秀，但是不符合我们要求的学位要求，其他企业可以考虑一下',1596200834701,'腾讯'),
(1718775339351,'2024-06-19 13:35:39',1717071210058,'考虑一下我们企业吗',1596200834701,'腾讯');

/*Table structure for table `job_seeker_comments` */

DROP TABLE IF EXISTS `job_seeker_comments`;

CREATE TABLE `job_seeker_comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `refid` bigint DEFAULT NULL COMMENT '关联表ID',
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `userid` bigint DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `用户名` (`username`) USING BTREE,
  KEY `用户id` (`userid`) USING BTREE,
  KEY `被评论的id` (`refid`) USING BTREE,
  CONSTRAINT `用户ID` FOREIGN KEY (`userid`) REFERENCES `generaluser` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `被评论的id` FOREIGN KEY (`refid`) REFERENCES `employment` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1718775184076 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='求职者信息评论表';

/*Data for the table `job_seeker_comments` */

insert  into `job_seeker_comments`(`id`,`addtime`,`refid`,`content`,`userid`,`username`) values 
(1718774811019,'2024-06-19 13:26:50',1713363863719,'这个岗位听说要求挺高的，大家慎选！',1596372388838,'李四'),
(1718774860505,'2024-06-19 13:27:40',1717053292214,'刚从这个岗位离职，个人感觉氛围很好，就是加班压力有点大',1596372388838,'李四'),
(1718774986784,'2024-06-19 13:29:46',1717053292214,'我熟练掌握Web设计，可以考虑一下我吗',1717061638906,'王五'),
(1718775037312,'2024-06-19 13:30:37',1717052610474,'有了解这个公司的吗，可以说一下这个岗位的不好的地方吗',1717061638906,'王五'),
(1718775184075,'2024-06-19 13:33:03',1596371998272,'我研究的方向就是图形学，请问贵企业的对bg有要求吗',1596200777877,'张三');

/*Table structure for table `jobseekerinfo` */

DROP TABLE IF EXISTS `jobseekerinfo`;

CREATE TABLE `jobseekerinfo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `fullname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `gender` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '照片',
  `education` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历',
  `jobcategory` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位类别',
  `resume` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简历',
  `workexperience` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '工作经历',
  `personalinfo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '个人基本情况',
  `thumbsupnum` int DEFAULT '0' COMMENT '赞',
  `crazilynum` int DEFAULT '0' COMMENT '踩',
  `birthdate` date DEFAULT NULL COMMENT '出生日期',
  `age` int DEFAULT '0' COMMENT '年龄',
  `birthplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '籍贯',
  `ethnicity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '民族',
  `height` double NOT NULL DEFAULT '0' COMMENT '身高',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `politicalstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '政治面貌',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE,
  KEY `fullname` (`fullname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1717071210059 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='求职者信息';

/*Data for the table `jobseekerinfo` */

insert  into `jobseekerinfo`(`id`,`addtime`,`username`,`fullname`,`gender`,`phone`,`photo`,`education`,`jobcategory`,`resume`,`workexperience`,`personalinfo`,`thumbsupnum`,`crazilynum`,`birthdate`,`age`,`birthplace`,`ethnicity`,`height`,`email`,`politicalstatus`) values 
(1596285238983,'2020-08-01 20:33:58','zhangsan','张三','男','13512345678','http://localhost:8082/resumeDelivery/upload/1717054128435.png','硕士','计算机/互联网类','http://localhost:8082/resumeDelivery/upload/1717059359473.pdf','两次实习经验，包您满意。','<p>有良好的个人能力，能吃苦，肯干。</p>',0,0,'2001-05-24',23,'浙江省杭州市','汉',166,'zhangsan@163.com','群众'),
(1596372486541,'2020-08-02 20:48:05','lisi','李四','女','18866661234','http://localhost:8082/resumeDelivery/upload/1717059471220.jpg','本科','管理岗位','http://localhost:8082/resumeDelivery/upload/1717059483357.pdf','2017年07月-2019年02月，在某某二号当公司管理主管\n2019年02月-2024年05月，在某某公司当ceo助理','<p>具有良好的沟通能力，善于执行并拥有团队合作精神，可以承受高强度工作</p>',1,0,'1999-07-23',24,'广东省广州市','汉',163,'lisi@163.com','共青团员'),
(1717071210058,'2024-05-30 20:13:30','wangwu','王五','男','13323566498','http://localhost:8082/resumeDelivery/upload/1717070117243.jpg','博士','财务类','http://localhost:8082/resumeDelivery/upload/1717071206775.pdf','2016年02月-2021年06月于某某公司就职统计会计\n2021年06月-至今于某某二号公司担任财务首席财务官','<p>具有丰富的税务专业知识，且熟练运用各种办公软件</p><p>具有良好的英语能力，且一直保持学习心态</p><p>并具备管理思维模式，具备法律意识、法律知识和合同意识</p>',0,0,'2000-03-29',24,'河北省石家庄市','满',178,'wangwu@163.com','共青团员');

/*Table structure for table `messages` */

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `userid` bigint NOT NULL COMMENT '留言人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1718775794865 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='留言板';

/*Data for the table `messages` */

insert  into `messages`(`id`,`addtime`,`content`,`userid`) values 
(1718775597450,'2024-06-19 13:39:57','特别好用，前后台都有，页面也没有很花里胡哨',1596200834701),
(1718775768023,'2024-06-19 13:42:47','网站很好用，希望贵工作室出新产品',1596200777877),
(1718775794864,'2024-06-19 13:43:14','希望网站成为国货之光！！',1596372388838);

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1718777652487 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='新闻资讯';

/*Data for the table `news` */

insert  into `news`(`id`,`addtime`,`title`,`picture`,`content`) values 
(1717218878137,'2024-06-01 13:14:37','京东启动2024年大规模校园招聘，为应聘者提供广阔发展平台','http://localhost:8082/resumeDelivery/upload/1717062371819.png','<p>近日，京东集团宣布正式启动2024年度校园招聘计划，为广大应聘者提供超万个就业岗位，旨在吸引和培养更多优秀的人才，共同推动京东业务的快速发展。</p><p><br></p><p>据了解，此次京东校园招聘计划将面向全球各大高校的应届毕业生，提供包括技术、运营、市场、物流等多个领域的岗位。为了吸引更多优秀的人才，京东在招聘过程中注重应聘者的综合素质和实际能力，除了基本的学历和专业要求外，更看重应聘者的创新思维、团队协作能力以及解决实际问题的能力。</p><p><br></p><p>京东作为一家领先的电商企业，一直致力于为员工提供良好的工作环境和广阔的发展空间。此次校园招聘计划也不例外，京东将为应聘者提供完善的培训体系、丰厚的薪酬待遇以及广阔的职业发展机会。同时，京东还注重员工的职业成长和个人发展，为员工提供多元化的晋升通道和丰富的职业发展资源。</p><p><br></p><p>对于应聘者而言，加入京东不仅意味着能够参与到一家领先企业的业务发展中，更能够接触到最前沿的技术和商业趋势，不断提升自己的能力和素质。此外，京东还为员工提供了丰富的福利待遇，包括五险一金、带薪年假、节日福利等，让员工在工作的同时享受到更多的生活品质。</p><p><br></p><p>京东此次启动的2024年校园招聘计划，无疑为广大应聘者提供了一个难得的机会。如果你对电商行业充满热情，希望在一个充满活力和创新精神的企业中发展自己的事业，那么不妨关注京东的招聘信息，加入这个大家庭，共同创造更加美好的未来。</p>'),
(1717220822381,'2024-06-01 13:47:01','天美工作室群启动新一轮人才招募，寻找游戏行业的精英','http://localhost:8082/resumeDelivery/upload/1717054626846.jpg','<p><br></p><p>近日，腾讯旗下知名游戏工作室——天美工作室群宣布启动新一轮的人才招募计划，旨在寻找和吸引更多在游戏设计、开发、运营等领域具有卓越能力和创新思维的精英加入。</p><p><br></p><p>天美工作室群作为腾讯游戏的重要组成部分，一直以其出色的游戏品质和创新能力在游戏行业享有盛誉。此次招募计划将针对游戏策划、美术设计、程序开发、游戏运营等多个岗位进行招聘，旨在进一步扩充团队实力，推动工作室的持续发展。</p><p><br></p><p>据了解，天美工作室群在招聘过程中将注重应聘者的实际能力和潜力，同时也会考察应聘者的团队协作精神和创新思维。他们希望招募到的人才不仅能够独立完成工作任务，还能够与团队成员紧密合作，共同打造出更加出色的游戏产品。</p><p><br></p><p>对于应聘者而言，加入天美工作室群将意味着能够参与到一流游戏项目的开发中，与业界顶尖的专家和团队共同工作，学习和成长。同时，天美工作室群也将为应聘者提供具有竞争力的薪酬待遇和完善的福利待遇，让每一位员工都能够享受到舒适的工作环境和发展空间。</p><p><br></p><p><br></p><p>此次天美工作室群的人才招募计划已经吸引了众多游戏行业人才的关注。如果你对游戏行业充满热情，具备出色的专业技能和创新能力，那么不妨关注天美的招聘信息，或许你将成为下一个加入这个精英团队的人才。</p>'),
(1718777552575,'2024-06-19 14:12:32','科技巨头加码招聘，新一轮人才争夺战启动','http://localhost:8082/resumeDelivery/upload/1718777530911.jpeg','<p>随着科技的飞速发展和市场竞争的加剧，全球知名科技巨头近日纷纷宣布加大招聘力度，旨在吸引和留住更多优秀人才，以推动公司的持续创新和业务发展。</p><p><br></p><p>据了解，谷歌、亚马逊、苹果、微软等公司均发布了新的招聘计划，涵盖人工智能、云计算、软件工程、数据分析等多个领域。这些岗位不仅提供极具竞争力的薪资待遇，还包括丰富的职业发展机会和福利待遇，如股票期权、健康保险、带薪休假等。</p><p><br></p><p>其中，谷歌表示将在全球范围内招聘数万名新员工，以满足其快速增长的业务需求。亚马逊则宣布在云计算和物流领域增加数千个岗位，以支持其不断扩张的全球业务。苹果也在其最新的招聘公告中提到了对软件开发、硬件设计等方面人才的需求。</p><p><br></p><p>业内专家分析认为，科技公司的招聘热潮反映了当前科技行业的繁荣和发展趋势。随着人工智能、大数据、云计算等技术的广泛应用，科技公司对于人才的需求量也在不断增加。同时，科技公司也意识到人才是企业发展的核心动力，因此加大了对人才的投入和吸引力度。</p><p><br></p><p>对于求职者来说，这无疑是一个好消息。随着科技公司的招聘力度加大，将有更多的机会和选择。然而，专家也提醒求职者，在选择岗位和公司时，除了关注薪资待遇和福利待遇外，还应该关注公司的文化和发展前景，以及岗位与个人兴趣和职业规划的匹配度。</p><p><br></p><p>同时，随着科技公司的招聘热潮，其他行业也可能受到影响。一些传统行业可能会面临人才流失的压力，因此需要加快转型升级，提高自身吸引力。而对于新兴行业来说，科技公司的招聘热潮将为其提供更多的发展机遇和挑战。</p><p><br></p><p>总的来说，科技公司的招聘热潮将为整个科技行业带来积极的影响，同时也将为求职者提供更多的选择和机会。</p>'),
(1718777652486,'2024-06-19 14:14:12','本地科技企业掀起新一轮招聘热潮，数百岗位虚位以待','http://localhost:8082/resumeDelivery/upload/1718777632577.jpeg','<p>随着本地科技产业的快速发展，多家知名企业纷纷加大招聘力度，以填补迅速增长的业务需求。此次招聘热潮不仅为求职者提供了丰富的岗位选择，也预示着本地科技产业的蓬勃生机。</p><p><br></p><p>据悉，此次招聘活动涵盖了软件开发、数据分析、产品经理、市场营销等多个领域，数百个岗位虚位以待。其中，不少企业特别强调了对应聘者创新能力和团队协作精神的重视，希望能够吸引更多具备潜力的年轻人才。</p><p><br></p><p>本地一家知名互联网企业的人力资源经理表示：“随着公司业务的不断扩张，我们急需大量优秀人才加入我们的团队。此次招聘将为我们注入新的活力，推动公司的持续发展。”</p><p><br></p><p>对于求职者来说，这无疑是一个难得的机会。随着科技产业的快速发展，相关岗位的需求也在不断增加。此次招聘活动不仅提供了丰富的岗位选择，还为求职者提供了与优秀企业直接沟通的机会，有助于他们更好地了解行业趋势和职业发展前景。</p><p><br></p><p>除了本地企业外，一些跨国科技公司也在积极招聘本地人才。他们看中了本地人才的专业技能和创新能力，希望通过引进本地人才来推动公司的全球化发展。</p><p><br></p><p>专家指出，科技产业的快速发展不仅为求职者提供了更多的就业机会，也推动了本地经济的持续增长。随着越来越多优秀人才的加入，本地科技产业有望在未来迎来更加繁荣的发展。</p><p><br></p><p>总的来说，本地科技企业的招聘热潮为求职者提供了丰富的岗位选择，也为本地经济的发展注入了新的动力。对于有志于在科技领域发展的年轻人来说，这是一个值得关注和把握的机会。</p>');

/*Table structure for table `storeup` */

DROP TABLE IF EXISTS `storeup`;

CREATE TABLE `storeup` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `userid` bigint NOT NULL COMMENT '用户id',
  `refid` bigint DEFAULT NULL COMMENT '收藏id',
  `tablename` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表名',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏名称',
  `picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1718774965214 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='收藏表';

/*Data for the table `storeup` */

insert  into `storeup`(`id`,`addtime`,`userid`,`refid`,`tablename`,`name`,`picture`) values 
(1709010832717,'2024-02-27 13:13:52',1596200777877,1596371998272,'employment','Java工程师','http://localhost:8082/resumeDelivery/upload/1717077016926.jpg'),
(1718514551593,'2024-06-16 13:09:11',1596372388838,1713363863719,'employment','市场总监','http://localhost:8082/resumeDelivery/upload/1717054366719.jpg'),
(1718605291640,'2024-06-17 14:21:31',1596372388838,1717053292214,'employment','网页设计与制作','http://localhost:8082/resumeDelivery/upload/1717053184862.jpg'),
(1718774952358,'2024-06-19 13:29:11',1717061638906,1717053292214,'employment','网页设计与制作','http://localhost:8082/resumeDelivery/upload/1717053184862.jpg'),
(1718774965213,'2024-06-19 13:29:24',1717061638906,1596371998272,'employment','图形学研究员（AI动画方向）','http://localhost:8082/resumeDelivery/upload/1717054626846.jpg');

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userid` bigint NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='token表';

/*Data for the table `token` */

insert  into `token`(`id`,`userid`,`username`,`tablename`,`role`,`token`,`addtime`,`expiratedtime`) values 
(1,1,'admin','users','管理员','7yrmwihdv6zdleojftg5cfkjwnq41mtq','2020-08-04 22:23:10','2024-06-19 16:14:27'),
(2,1596200834701,'tengxun','companyinfo','企业信息','ubbvqaqzu7ob3j9aw9dedxvpjwz7pnhp','2020-08-02 20:37:15','2024-06-19 16:33:22'),
(3,1596372388838,'lisi','generaluser','用户','4zb9w54k21l4wp6ulh4wd55dia1rub75','2024-05-30 13:19:34','2024-06-19 16:19:43'),
(4,1717053043158,'alibaba','companyinfo','企业信息','d8dxhtrswc6p39at99ha88rbf1san28e','2024-05-30 15:10:58','2024-05-31 20:13:00'),
(5,1596200777877,'zhangsan','generaluser','用户','4c5hp013pmxfh95gsyjlypggl0u084py','2020-07-31 21:07:23','2024-06-19 14:42:23'),
(6,1716553444334,'bilibili','companyinfo','企业信息','o451weyda5qx7o9k6r1hb253v5g1krks','2024-05-24 20:24:12','2024-05-30 16:05:26'),
(7,1717061638906,'wangwu','generaluser','用户','7ikt3lyowlplevvosygfonfnccw86ree','2024-05-30 17:34:11','2024-06-19 14:28:12');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`role`,`addtime`) values 
(1,'admin','123456','管理员','2020-07-26 11:10:39');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
