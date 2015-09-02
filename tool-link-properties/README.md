tool-link-properties
====================

mail list:朱伟亮 \<zhuwl120820@gxwsxx.com>

初始化项目“全局变量”。<br>
从配置文件或远程读取项目“自定义变量”。<br>
将“全局变量”和“自定义变量”的配置信息放入spring启动配置和application context。<br>


## 1. 版本变更说明
### 1.0.3
完善了说明文档README.md。<br>
将配置文件log4j2.xml和spring-logging.xml从项目目录转移至tool-logging的包目录。<br>
更改“自定义变量”使用“远程配置读取”方式的规则，["dev","test","real"]远程读取，其他则配置文件读取。<br>
更改“全局变量”jsp获取方式。<br>
读取tool-common中的ProjectConstant.onlineEnvSet用于区别线上环境。<br>
 
### 1.1.0
增加servlet context path为系统默认变量。调用方式为${project.contextPath}<br>
调整LinkPropertiesCore类的配置读取方式。<br>
调整maven依赖配置，去除parent配置，改为使用原始配置。<br>
加入了输出一些logger debug信息。<br>
调整了project.name和project.version的读取方式，改为读取maven信息。<br>
spring framework版本修改为4.1.7.RELEASE。<br>

### 1.1.1
增加了一个servlet context path系统变量别名。调用方式为${ctx}。<br>
修复了项目启动时没有servlet context的空指针异常。<br>

### 1.2.0
修改注释信息，优化代码。<br>
修改异常类的结构，代码优化。<br>
修改配置读取策略，将从系统变量读取环境和读取策略。不再从环境变量判断读取策略。<br>
修改maven依赖，tool-common版本修改为1.2.0-RELEASE。<br>
<br>
添加servlet context属性名称，可以通过注解中指定的属性名称，读取相应的信息。<br>
添加依赖testng测试框架，版本为6.9.6<br>
添加依赖spring-test测试框架。<br>

### 2.0.0
修改项目结构为tool-self-operation项目的子模块。<br>

### 2.1.0
修改ProjectConstant的数据存储方式，由静态单例模式，修改为以对象方式存储于spring context和servlet context中。<br>



## 2. 功能点
### 2.1. 项目启动时初始化“全局变量”配置
项目全局变量包括：<br>

#### 2.1.1. 项目名：
读取来源：项目目录META-INF/maven/pom.properties文件，artifactId的值。<br>
默认值：name_default<br>
键(key)：project.name<br>

#### 2.1.2. 项目版本：
读取来源：项目目录META-INF/maven/pom.properties文件，version的值。<br>
默认值：version_default<br>
键(key)：project.version<br>

#### 2.1.3. 项目运行环境：
读取来源：项目运行web容器(tomcat)启动参数-Dproject.env。<br>

	java -Dproject.env=dev
	
默认值：env_default<br>
键(key)：project.env<br>

#### 2.1.4. 项目运行服务器ip
读取来源：项目运行服务器的所有网络接口的IP地址，除127.0.0.1以外，多个ip以","分隔。<br>

默认值：ip_default<br>
键(key)：project.ip<br>

#### 2.1.5. 项目运行端口号
读取来源：项目运行web容器(tomcat)启动参数-Dproject.port。<br>

	java -Dproject.port=10000
	
默认值：port_default<br>
键(key)：project.port<br>

### 2.2. 项目启动时读取“自定义变量”配置
#### 2.2.1. 配置读取指定
从项目运行web容器(tomcat)启动参数-Dlink.properties.reader指定。<br>

	java -Dlink.properties.reader=file
	java -Dlink.properties.reader=zookeeper

默认值：file<br>

#### 2.2.2. file配置读取方式
读取项目classes目录下的link.properties文件。<br>
读取路径:${project.env}.${key}=${value}<br>
以test环境的静态地址stc的值为示例：test.stc=value<br>
以dev环境的数据库连接地址db.url的值为示例：dev.db.url<br>

#### 2.2.3. zookeeper配置读取方式
读取路径:/link.properties/${project.env}/${project.name}/${key}<br>
以test环境web-tv-demo项目的静态地址stc的值为示例：/link.properties/test/web-tv-demo/stc
以dev环境service-demo项目的数据库连接地址db.url的值为示例：/link.properties/dev/service-demo/db.url

## 3. 依赖关系

### 3.1. 服务依赖
#### zookeeper
zookeeper配置读取方式中需要依赖zookeeper服务。<br>
读取地址为zookeeper.gxwsxx.com:17000。<br>
暂不支持通过配置方式修改zookeeper的连接地址。

## 4. 使用方式
### 4.1. 引入maven依赖配置
在项目pom.xml文件中加入<br>

	<dependency>
		<groupId>com.gxws</groupId>
		<artifactId>tool-link-properties</artifactId>
		<version>最新版本号</version>
	</dependency>
	
### 4.2. 读取
定义java静态类、静态字段。<br>
给静态字段添加注解@LinkProperties。value的值为读取配置的key值。servletContextAttrNames的值为在ServletContext中读取getAttribute()中的属性名。<br>
规则一般为静态字段字母由小写改为大写，"."改为"_"。
	
	package com.gxws.web.tv.demo.constant;

	import com.gxws.tool.link.properties.annotation.LinkProperties;
	
	public class Constant {
	
		@LinkProperties(servletContextAttrNames="static")
		public static String STC;
		
		@LinkProperties(value = "db.url")
		public static String DB_URL;
	
		@LinkProperties(value = "db.username")
		public static String DB_USERNAME;
	
		@LinkProperties(value = "db.password")
		public static String DB_PASSWORD;
	}
	
在spring配置文件中添加<br>

	<beans>
		<!-- 定义项目系统变量 -->
		<bean id="projectConstant" class="com.gxws.tool.common.constant.ProjectConstant" />
		<!-- 读取配置 -->
		<bean class="com.gxws.tool.link.properties.spring.LinkPropertiesBean">
			<property name="projectConstant" ref="projectConstant" />
			<property name="constantClassnames">
				<list>
					<value>com.gxws.toysearn.service.constant.Constant</value>
				</list>
			</property>
		</bean>
	</beans>
	
value为上一步定义的静态类。<br>

### 4.3. 使用
在使用方面，使用“自定义变量”和“全局变量”的方式基本一致。<br>
#### 4.3.1. 在spring配置文件中使用:
在spring配置文件中引入变量值，使用**配置键名**，即在@LinkProperties(value=key)注解中配置的key，或“系统变量”的key<br>

	<bean id="dataSourceDruid" class="com.alibaba.druid.pool.DruidDataSource"
		init-method="init" destroy-method="close">
		<property name="url" value="${db.url}" />
		<property name="username" value="${db.username}" />
		<property name="password" value="${db.password}" />
	</bean>

	<beans>
		<dubbo:application name="${project.name}" />
	</beans>
	
#### 4.3.2. jsp页面EL表达式使用:
在jsp中使用变量值，在el表达式中使用在@LinkProperties注解中servletContextAttrNames属性的值。<br>
也可以在el表达式中直接使用静态类的字段名。<br>

	<html>
		<body>
			${static}
			${DB_URL}
			${DB_USERNAME}
			${DB_PASSWORD}
		</body>
	</html>

“系统变量”在el表达式中只能使用ProjectConstant类的字段名。<br>
applicationScope指定范围可以省略。<br>
其中contextPath的值也可以通过ctx进行读取。<br>

	<html>
		<body>
			${project.name}
			${project.env}
			${project.version}
			${project.ip}
			${project.port}
			${project.contextPath}
			${ctx}
		</body>
	</html>

#### 4.3.3. java代码变量使用:
“自定义变量”可以直接通过访问静态类的属性来使用。<br>
“系统变量”需要通过spring的依赖注入、spring ApplicationContext、ServletContext对象这三种方式来获取ProjectConstant对象。<br>

	import com.gxws.tool.common.constant.ProjectConstant;
	
	public class DemoClass {
	
		@Autowired
		private ProjectConstant projectConstant;
		
		private ApplicationContext ac;
		private ServletContext sc;
	
		public void DemoMethod(){
			system.out.println("通过依赖注入获取项目名称：" + projectConstant.getName());
			
			system.out.println("通过ApplicationContext获取项目名称" + ac.getBean(ProjectConstant.class).getName());
			
			ProjectConstant pc = (ProjectConstant) servletContext.getAttribute(ProjectConstant.CONTEXT_NAME);
			system.out.println("通过ServletContext获取项目名称" + pc.getName(););
		}
	}

