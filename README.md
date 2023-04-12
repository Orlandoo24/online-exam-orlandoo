# OnlineExam

#### 介绍

OnlineExam是由Spring Cloud Alibaba 与 Vue 构建的极简但功能完备的在线考试系统

#### 软件架构

后端技术栈

- Spring Cloud Gateway

- Spring Cloud Alibaba

- Mybatis Plus

- Zipkin

  

前端技术栈

- Vue
- Element-UI
- Tailwind CSS

#### 使用说明

1.  克隆本项目或在发行版中下载源代码
2.  运行 sql 脚本，并启动 Nacos 与 Zipkin
3.  启动 Exam-Backstage 下的服务
4.  前端项目 npm install 安装所需依赖
5.  运行前端项目即可完成项目启动

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request

#### To-Do

- [x] 后端微服务拆分
- [x] 前端学生端与管理端拆分
- [ ] 前端页面美化及功能优化
- [x] 配置 Spring Cloud Gateway 动态路由
- [ ] 配置 Zipkin 链路追踪
- [ ] 使用 Sharding JDBC 进行主从复制及读写分离
- [ ] 构建 ELK 日志收集系统
- [ ] 用dubbo封装所有接口
- [ ] 将部分考试信息用es存储
