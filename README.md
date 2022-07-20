# Community
Community 是牛客网讨论区实战项目，用于巩固 Web 开发所学的知识。
## 架构

- 编程语言：Java 1.8 + TypeScript
- 前端框架： Vue 3.2 + Pinia
- UI 组件：Element Plus
- 后端框架：Spring Boot 2.5.6 
- 持久层架构：MyBatis-Plus 3.3.2 + MySQL 5.7 + Redis 5.0
- 安全框架：Spring Security
- 加密库：Jasypt

## 在线体验
- admin/admin

## 演示图
![image](https://github.com/hachikuji894/resource-repository/blob/main/images/4256ec4ac8f6a748512c5932a558199.png)
![image](https://github.com/hachikuji894/resource-repository/blob/main/images/5f6b14be41f5acf28832afa1f9f98aa.png)

## 特性
- 登录功能：使用 Spring Security 实现用户的登录登出并进行权访问限控制。使用 Redis 进行缓存控制登录验证码和其是否失效。
- 注册功能：通过设置邮箱发送激活码到指定邮箱，访问指定链接激活用户状态。
- 分页功能：使用清量级分页插件 [PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)。