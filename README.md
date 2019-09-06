### 项目简介
- Ecommerce各个服务的共享模型，主要包含一些公共基类和值对象;
- 该项目不应该包含与Web框架(比如Spring)相关的的设施;
- 聚合根对象不应该出现在该项目中，而应该出现在各个服务中。

Ecommerce项目包括：

|代码库|用途|地址|
| --- | --- | --- |
|ecommerce-order-service|Order服务|[https://github.com/e-commerce-sample/ecommerce-order-service](https://github.com/e-commerce-sample/ecommerce-order-service)|
|ecommerce-product-service|Product服务|[https://github.com/e-commerce-sample/ecommerce-product-service](https://github.com/e-commerce-sample/ecommerce-product-service)|
|ecommerce-inventory-service|Inventory服务|[https://github.com/e-commerce-sample/ecommerce-inventory-service](https://github.com/e-commerce-sample/ecommerce-inventory-service)|
|ecommerce-shared-model|共享模型，不含Spring|[https://github.com/e-commerce-sample/ecommerce-shared-model](https://github.com/e-commerce-sample/ecommerce-shared-model)|
|ecommerce-spring-common|Spring共享基础配置|[https://github.com/e-commerce-sample/ecommerce-spring-common](https://github.com/e-commerce-sample/ecommerce-spring-common)|
|ecommerce-devops|基础设施|[https://github.com/e-commerce-sample/ecommerce-devops](https://github.com/e-commerce-sample/ecommerce-devops)|

# 技术选型
Spring Boot、Gradle、MySQL、Junit 5、Rest Assured、Docker、RabbitMQ/Kafka


### 命令行用法：

|命令|用途|
| --- | --- |
|`./idea.sh`|生成IntelliJ工程文件|
|`./local-build.sh`|本地构建|
|`./publish.sh`|发布到mymavenrepo.com仓库，发布新版本时需要修改`gradle.properties`文件中的`version`变量|

- 本项目使用`mymavenrepo.com`作为maven发布仓库:[网站地址](https://mymavenrepo.com/app/repos/F0lRvilYH123TUeMr5GN/)
- 发布时使用的URL: https://mymavenrepo.com/repo/Cd07WrKAtJ9Kq7PBaTuf/
- 依赖时使用的URL: https://mymavenrepo.com/repo/2w5k9sU2AsKfaYehyqno/
