# tool-self-operation

自运营项目的工具集合

## 1.版本变更说明

### 2.0.0
去除dubbo的maven依赖。<br>

### 2.1.0
修改jdk版本号为1.7<br>
修改springframework版本为4.2.0<br>
添加jsp-api依赖。<br>
修改hibernate validator版本为5.2.1<br>
重新引入dubbo依赖，版本为2.4.10<br>

### 3.0.0
添加rpc模块tool-rpc。<br>
添加工具模块tool-utils。<br>
去除tool-validator模块，相应的验证功能并入tool-rpc。<br>
去除tool-logging大部分功能，相应的参数日志记录功能并入tool-rpc。<br>
去除tool-test模块，还没有设计好测试的方式。<br>
去除tool-common模块，将相应的功能并入tool-link-properties和tool-utils中。<br>
修改com.gxws为com.gxwsxx