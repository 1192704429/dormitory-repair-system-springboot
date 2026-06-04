# 基于 SpringBoot + Vue2 + Uniapp 的宿舍报修系统设计与实现

> 本文围绕高校宿舍生活中的报修管理需求，设计并实现了一套基于 SpringBoot 3、Vue2、ElementUI 与 Uniapp 小程序的宿舍报修系统，支持管理员、宿管、维修人员与学生四个角色的协同工作。

## 一、项目背景

该系统主要应用于高校宿舍管理场景，解决传统报修流程中信息不透明、任务分配低效、材料管理混乱等问题。通过数字化手段，实现学生在线提交报修申请、宿管审核与派单、维修人员接单处理、管理员统一监管与数据统计分析的全流程闭环管理，提升宿舍维修响应速度与服务质量。

## 二、功能模块介绍

系统分为 PC 管理后台与 Uniapp 小程序两个端。PC 管理后台供管理员、宿管、维修人员使用，小程序供学生使用。

### 角色与所属端

| 角色 | 所属端 |
| :--- | :--- |
| 管理员 | PC 管理后台 |
| 宿管 | PC 管理后台 |
| 维修人员 | PC 管理后台 |
| 学生 | Uniapp 小程序 |

### 功能模块图


![功能模块图](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/azhou_promotion/img/module_structure/func_module_069e1e6f431445bb80b7e3fe918871f9.png)


## 三、技术栈说明

**前端：**
Vue 2 + ElementUI（PC 管理后台）、Uniapp（学生小程序）

**后端：**
Java 语言、SpringBoot 3

**数据库与中间件：**
MySQL

## 四、核心模块介绍

### ###### PC 管理后台

#### 管理员

| 功能模块 | 功能描述 |
| :--- | :--- |
| 管理员登录 | 默认账号密码登录系统 |
| 内部人员管理 | 管理宿管、维修人员基础信息 |
| 学生信息管理 | 管理从小程序登录的学生信息 |
| 系统公告信息管理 | 发布公告，所有角色均可查看 |
| 宿舍楼栋管理 | 添加楼栋信息，并为楼栋分配宿管人员 |
| 数据统计分析 | 从不同维度进行图表分析展示 |
| 维修记录管理 | 查看系统中所有维修记录 |
| 维修材料管理 | 对维修材料进行入库等基本操作 |
| 维修材料审核 | 审核维修人员申请的维修材料 |
| 个人信息管理 | 修改个人基础信息与密码 |

##### 界面展示

登录页：管理员输入信息登录宿舍报修管理平台

![登录页：管理员输入信息登录宿舍报修管理平台](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/8341dedd-6503-429b-8730-560784df90ea.png)

首页：展示系统各维度业务数据统计图表

![首页：展示系统各维度业务数据统计图表](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/502bb3e2-5998-4896-8849-0f2abf45fa96.png)

内部人员管理页：管理系统内各类角色人员的基础信息

![内部人员管理页：管理系统内各类角色人员的基础信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/48f868c6-8953-4da3-80b6-fa77bfc16290.png)

宿舍楼栋管理页：查看并管理系统内所有宿舍楼栋信息

![宿舍楼栋管理页：查看并管理系统内所有宿舍楼栋信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/06887f57-8f97-415e-8e1d-57e148726401.png)

宿舍楼栋管理页：查看宿舍楼栋列表及对应楼栋的宿舍信息

![宿舍楼栋管理页：查看宿舍楼栋列表及对应楼栋的宿舍信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/472e1bda-fda6-44d0-81c2-492e83a2b641.png)

学生信息管理页：管理员查询并管理系统内的学生信息

![学生信息管理页：管理员查询并管理系统内的学生信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/37a251cb-c835-4eac-802d-85438a03f115.png)

系统公告管理页：管理员查询维护系统内的公告通知

![系统公告管理页：管理员查询维护系统内的公告通知](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/9bc1de43-be72-43aa-8f7a-014421523830.png)

维修材料管理页：查看并管理系统内各类维修材料信息

![维修材料管理页：查看并管理系统内各类维修材料信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/30a04316-5799-4c89-815c-4afc49863261.png)

维修材料审核页：审核维修人员提交的维修材料申请

![维修材料审核页：审核维修人员提交的维修材料申请](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/14f92b41-7bbe-4fca-8c47-00ef25f9f094.png)

维修记录管理页：查看系统所有报修记录，支持按条件查询

![维修记录管理页：查看系统所有报修记录，支持按条件查询](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/cbd3871e-402d-4cf6-8f54-77f548b92723.png)


#### 宿管

| 功能模块 | 功能描述 |
| :--- | :--- |
| 宿管登录 | 使用账号密码登录系统 |
| 楼栋维修任务管理 | 查看学生提交的维修任务，审核后系统自动分配维修人员 |
| 楼栋宿舍信息管理 | 为本楼栋添加宿舍信息，并为宿舍添加学生信息（含人员、账号信息） |
| 个人信息管理 | 修改个人基础信息与密码 |
| 系统通知信息 | 查看系统公告与通知 |

##### 界面展示

首页：查看管辖范围报修与宿舍的统计概览

![首页：查看管辖范围报修与宿舍的统计概览](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/b16392eb-2fbd-4eb5-8b27-5d33e3f62a1f.png)

宿舍信息管理页：查看楼栋宿舍，管理宿舍内的学生信息

![宿舍信息管理页：查看楼栋宿舍，管理宿舍内的学生信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/beb1ccc4-f432-4d1e-80c6-dbf8613303fe.png)

楼栋宿舍管理页：宿管管理本楼栋下的所有宿舍信息

![楼栋宿舍管理页：宿管管理本楼栋下的所有宿舍信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/75085e7c-0b1f-461d-8419-10e5d415017d.png)

报修单审核管理：审核学生报修单，通过后自动派单给维修人员

![报修单审核管理：审核学生报修单，通过后自动派单给维修人员](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/366a299b-1502-42ac-8723-b97abae1652e.png)

报修记录管理页：查看本人管理楼栋下的所有宿舍报修记录

![报修记录管理页：查看本人管理楼栋下的所有宿舍报修记录](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/c3a3c0dd-9ef2-4b7e-88ed-2022cfd8c6e8.png)

系统通知列表页：查看系统发送的各类通知信息

![系统通知列表页：查看系统发送的各类通知信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/0dd08c2e-80dc-4936-8df7-c61db59275db.png)

系统通知页：查看系统通知列表，浏览通知详情内容

![系统通知页：查看系统通知列表，浏览通知详情内容](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/cc60607a-7c3c-42fb-8eec-cd39e9d8e919.png)


#### 维修人员

| 功能模块 | 功能描述 |
| :--- | :--- |
| 维修人员登录 | 使用账号密码登录系统 |
| 报修单处理 | 查看个人报修单，更新进度（已接收、材料申请中、已完成等），添加维修记录及维修材料，状态同步更新 |
| 维修记录查看 | 查看历史维修记录明细 |
| 维修材料申请 | 针对维修任务申请材料，待管理员审核 |
| 个人信息管理 | 修改个人基础信息与密码 |
| 系统通知信息查看 | 查看系统公告与通知 |

##### 界面展示

首页：展示各类维修相关业务的统计数据

![首页：展示各类维修相关业务的统计数据](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/f02b5eed-5b58-4248-872d-2e143086fee6.png)

报修单处理页：查看分配给自己的报修单，处理报修任务

![报修单处理页：查看分配给自己的报修单，处理报修任务](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/df813607-ada4-4a08-8a5c-ab5bd025e1f0.png)

维修材料申请列表页：查看本人的维修材料申请记录与状态

![维修材料申请列表页：查看本人的维修材料申请记录与状态](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/82ac4f6f-b85f-4812-802b-0a92d63c6fdf.png)

材料申请管理页：查看维修材料申请列表及申请详情

![材料申请管理页：查看维修材料申请列表及申请详情](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/f81b24a3-503b-424a-8134-474fc0f7a2bb.png)


### ###### Uniapp 小程序

#### 学生

| 功能模块 | 功能描述 |
| :--- | :--- |
| 学生登录 | 使用账号密码登录小程序 |
| 报修申请 | 提交图文信息报修申请 |
| 报修进度查询 | 查看个人提交的报修单及详细数据 |
| 报修服务评价 | 对已完成的报修单进行评价 |
| 历史报修记录 | 查看所有历史报修记录 |
| 个人信息管理 | 修改个人基础信息与密码 |
| 系统通知信息查看 | 查看系统公告与通知 |

##### 界面展示

登录页：学生输入账号密码，登录宿舍报修系统

![登录页：学生输入账号密码，登录宿舍报修系统](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/2d08535f-ff2b-46bc-8bb7-b026e3fbee7c.png)

通知列表页：查看筛选后系统推送的各类通知

![通知列表页：查看筛选后系统推送的各类通知](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/eb075bd4-ce20-4bc9-8e09-17ddd67c950d.png)

报修记录列表页：查看本人提交的报修申请及当前处理状态

![报修记录列表页：查看本人提交的报修申请及当前处理状态](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/5dac5986-680c-4a61-8c19-03044721920f.png)

报修记录页：查看本人提交的所有报修单及对应状态

![报修记录页：查看本人提交的所有报修单及对应状态](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/f7cd8611-18d3-4693-88ee-beb4c8f3930f.png)

报修详情页：查看个人报修单的详细进度与信息

![报修详情页：查看个人报修单的详细进度与信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/a50e4301-00ca-49f3-8185-d663a6caf4ca.png)

报修申请页：填写报修信息，提交宿舍报修申请

![报修申请页：填写报修信息，提交宿舍报修申请](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/82f64547-25f3-4010-87d8-6946e20376e5.png)

历史报修记录页：查看个人历史报修，可对已完成报修评价

![历史报修记录页：查看个人历史报修，可对已完成报修评价](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/3ed3561f-92ac-42d0-841c-2cce91e2f443.png)

报修评价页：对已完成的报修单进行服务评价

![报修评价页：对已完成的报修单进行服务评价](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/84fdd988-bba5-43be-84b3-de9151c6917f.png)

个人中心页：展示个人信息，提供相关功能入口

![个人中心页：展示个人信息，提供相关功能入口](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/406026b6-2478-42c0-8aa0-2abbc821acb0.png)

个人信息管理页：编辑个人信息，保存修改后的个人资料

![个人信息管理页：编辑个人信息，保存修改后的个人资料](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-05-25/35a2f04f-b02b-41fd-8bac-1bbc05c1c1cc.png)


## 五、主要业务流程

1. 系统默认存在管理员账号，管理员登录后添加楼栋、维修人员、宿管信息，并绑定宿管与楼栋关系。

2. 宿管登录后为本楼栋添加宿舍信息与学生信息，并绑定学生与宿舍关系。

3. 学生通过小程序提交图文报修申请。

4. 宿管在系统中查看并审核学生提交的维修任务。

5. 审核通过后，系统自动将维修任务分配给对应的维修人员。

6. 维修人员登录后查看个人待处理报修单，如需额外材料则提交材料申请。

7. 管理员对维修人员的材料申请进行审核。

8. 维修人员完成维修后，更新报修单状态为已完成并添加维修记录。

9. 学生对已完成的报修单进行评价。

## 六、项目拓展性

- 可接入智能排班系统，根据维修人员技能与空闲时间自动优化派单。
- 支持移动端通知推送（短信、企业微信、App 推送），提升消息触达效率。
- 可扩展费用结算模块，记录维修工时与材料成本，生成财务报表。
- 支持物联网设备联动，如水表、电表异常自动生成报修单。
- 可对接校园一卡通系统，实现学生身份统一认证。

## 代码说明 
本仓库为项目的 **后端核心代码部分** 主要用于展示系统的业务设计思路与后端实现方式 出于学习展示与版权保护考虑 前端实现、完整数据库脚本及部署配置未在本仓库中公开 

##  项目交流 
本项目用于学习展示 如需项目完整功能演示、答辩说明或技术交流 请前往作者 GitHub 主页查看联系方式