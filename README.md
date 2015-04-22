tool-link-properties
====================

集成spring启动，从配置文件和远程读取相应的配置信息，将配置信息放入application context


版本变更说明
---
> 1
>> 1.0
>>> 1.0.3
>>>> 新添加功能：
>>>> 完善了说明文档README.md。<br>
>>>> 将配置文件log4j2.xml和spring-logging.xml从项目目录转移至tool-logging的包目录。<br>
>>>> 修复bug：
>>>> 无

功能点
---

### 1、项目启动时初始化全局变量
项目全局变量包括：<br>

#### 项目名：
读取来源：项目目录/WEB-INF/web.xml文件，display-name标签。<br>
	
	<web-app>
		<display-name>service-demo</display-name>
	</web-app>
	
默认值：无<br>
键(key)：project.name<br>

#### 项目版本：
读取来源：项目目录/WEB-INF/web.xml文件，context-param标签。<br>

	<web-app>
		<param-name>project.version</param-name>
		<param-value>0.0.1-SNAPSHOT</param-value>
	</web-app>
	
默认值：无<br>
键(key)：project.version<br>

#### 项目运行环境：
读取来源：项目运行web容器(tomcat)启动参数-Dproject.env。<br>

	java -Dproject.env=dev
	
默认值：env_default<br>
键(key)：project.env<br>

#### 项目运行服务器ip
读取来源：项目运行服务器的所有网络接口的IP地址，除127.0.0.1以外，多个ip以","分隔。<br>

默认值：无<br>
键(key)：project.ip<br>

#### 项目运行端口号
读取来源：项目运行web容器(tomcat)启动参数-Dproject.port。<br>

	java -Dproject.port=10000
	
默认值：port_default<br>
键(key)：project.port<br>

### 2、项目启动时读取项目配置
#### 配置读取方式
读取方式分为“配置文件读取”和“远程配置读取”两种。<br>
配置文件读取：配置项会从配置文件classpath:link.properties中读取。<br>
远程配置读取：配置项会从远程配置服务中读取相应项。<br>

#### 配置读取规则
根据读取键值project.env，如果值在["dev","test","real"]中的，都会使用“远程配置读取”的方式；如果不在，会使用“配置文件读取”的方式。

#### 远程配置读取方式
##### zookeeper
读取路径:/link.properties/${project.env}/${project.name}/${key}<br>
以test环境web-tv-demo项目的静态地址stc的值为示例：/link.properties/test/web-tv-demo/stc

##### redis
计划扩展，暂不支持

依赖关系
---

### 1、服务依赖
#### zookeeper
“远程配置读取”方式中需要依赖zookeeper服务。<br>
读取地址为zookeeper.gxwsxx.com:17000。<br>
暂不支持通过配置方式修改zookeeper的连接地址。

### 2、组件依赖
org.springframework spring-web 4.1<br>
com.gxws tool-common 1.0.1<br>
org.apache.curator curator-framework 2.7<br>

使用方式
---

## 1、读取
定义静态变量类、静态字段。<br>
给静态字段添加注解@LinkProperties，value的值为读取配置的key值。<br>
规则一般为静态字段字母由小写改为大写，"."改为"_"。
	
	package com.gxws.service.demo.constant;

	import com.gxws.tool.link.properties.annotation.LinkProperties;
	
	public class Constant {
	
		@LinkProperties(value = "db.url")
		public static String DB_URL;
	
		@LinkProperties(value = "db.username")
		public static String DB_USERNAME;
	
		@LinkProperties(value = "db.password")
		public static String DB_PASSWORD;
	}
	


## 2、使用
以项目名的使用为例：<br>

### 1、spring配置使用项目配置的值：

	<beans>
		<dubbo:application name="${project.name}" />
	</beans>
	
### 2、jsp页面EL表达式使用项目配置的值:

	<html>
		<body>
			${VALUE_PROJECT_NAME}
		</body>
	</html>

### 3、java代码变量使用项目配置的值：

	import com.gxws.tool.common.constant.ProjectConstant;
	
	public class DemoClass {
		public void DemoMethod(){
			system.out.println(ProjectConstant.VALUE_PROJECT_NAME);
			system.out.println(ProjectConstant.get("project.name"));
			system.out.println(ProjectConstant.get(ProjectConstant.NAME_PROJECT_NAME));
		}
	}


