/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.19 : Database - lblog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lblog`;

/*Table structure for table `blog_article` */

DROP TABLE IF EXISTS `blog_article`;

CREATE TABLE `blog_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `content` longtext,
  `createtime` varchar(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `blog_article` */

insert  into `blog_article`(`id`,`userid`,`title`,`description`,`content`,`createtime`,`status`) values 
(32,1,'Nginx和内网穿透','Nginx (engine x) 是一个高性能的HTTP 和反向代理 的web服务器，同时也提供了IMAP/POP3/SMTP服务。\n','<p>1.1如何启动？</p><p>很简单</p><p>（1）双击nginx.exe&nbsp;文件，出现黑框一闪而过，表示已启动成功</p><p>（2）在当前目录打开cmd 输入 nginx.exe 回车&nbsp; 然后运行成功，可以关闭cmd窗口</p><p>问题：</p><p>启动多次会有什么情况？&nbsp; 会有多个nginx启动，可以使用 tasklist /fi \"imagename eq nginx.exe\"&nbsp;&nbsp; 查看 。tasklist&nbsp; 是windows的进程管理命令，详细可以百度查看。<img src=\"http://img.lightr.cn/2021/12/20211217093448171.png?imageMogr2/auto-orient/thumbnail/900x600%3E/format/webp/blur/1x0/quality/75|watermark/2/text/QOS5mOmjjuWwj-agiA==/font/5a6L5L2T/fontsize/260/fill/IzAwMDAwMA==/dissolve/51/gravity/SouthEast/dx/10/dy/10\" alt=\"image\" contenteditable=\"false\"><img class=\"ProseMirror-separator\"><br class=\"ProseMirror-trailingBreak\"></p><p>1.2 如何退出</p><p>（1）打开任务管理器，找到 nginx进程&nbsp;选择结束任务即可。</p><p>（2）使用命令&nbsp;nginx -s stop&nbsp; 或者 nginx -s quit&nbsp; ，仅限有一个nginx进程的情况</p><p>（3）使用tasklist命令&nbsp;taskkill /f /t /im nginx.exe&nbsp; ，可以结束多个&nbsp;nginx进程</p><p><br class=\"ProseMirror-trailingBreak\"></p>','2022-01-31',1),
(33,1,'手把手DES加密解密详解','DES（Data Encryption Standard）是迄今为止世界上最为广泛使用和流行的一种分组密码算法','<p>DES介绍</p><p>DES（Data Encryption Standard）是迄今为止世界上最为广泛使用和流行的一种分组密码算法，它的分组长度为64比特，密钥长度为56比特，它是由美国IBM公司研制的，是早期的称作Lucifer密码的一种发展和修改。 每隔五年由美国国家保密局（NSA）作出评估，并重新批准它是否继续作为联邦加密标准。最后一次评估是在1994年1月，美国已决定1998年12月以后将不再使用DES。 DES是第一代公开的、完全说明细节的商业级现代算法，并被世界公认。</p><p>DES加密基于Feistel结构实现，其轮结构如下图所示：</p><p>【来源：https://www.lightr.cn/archives/1332.html，转载请注明】</p>','2022-01-31',1),
(34,1,'LSPosed框架安装','LSPosed框架 基于Edxposed 开发的一个新的框架，改善Edxposed的一些缺点，其主要好处','<p>aaa</p>','2022-01-31',1);

/*Table structure for table `blog_user` */

DROP TABLE IF EXISTS `blog_user`;

CREATE TABLE `blog_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(60) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(60) DEFAULT NULL COMMENT '盐值',
  `state` tinyint(1) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `blog_user` */

insert  into `blog_user`(`id`,`name`,`password`,`salt`,`state`) values 
(1,'lightr','6b43f6ba439e8c31b2d8fb16272c216f','!@#',1),
(2,'test','ac31718990c9ff3be8a2854b5cde5b28','!@',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
