#tool-definition-exception

预定义型异常工具项目。<br>
用于处理业务项目中的异常及抛出问题。<br>

## 版本变更说明
### 2.1.0
从tool-common项目分离出子项目，用于处理业务项目中的异常及抛出问题。<br>


### 用法
1. 在api子项目的spring-api-exception.xml配置文件中，引入spring-definition-exception.xml文件。<br>
2. 在相应的service或webtv子项目中，引入spring-api-exception.xml配置。<br>
3. 在需要抛出异常的spring bean中注入`com.gxws.tool.definition.exception.BaseDefinitionExceptionFactory`对象。<br>
4. 使用instance()方法获取相应的exception对象。

