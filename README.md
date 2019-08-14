# live-streaming
Spring cloud实现直播搭建

├─ live-common                                      //基础工具
├─ live-config                                      //配置中心服务
│  │     └─ resources
│  │        └─ config                               //本地配置
│  │           ├─ dev                               //开发环境
│  │           │  ├─ application.yml
│  │           │  ├─ gateway-server-dev.yml
│  │           │  ├─ ...
│  │           ├─ prod
│  │           │  └─ ...
│  │           └─ test
│  │              └─ ...
├─ live-data                                        //数据模型
│     ├─ bean
│     ├─ cache
│     ├─ config
│     ├─ entity
│     ├─ repository
│     └─ service
├─ live-dependencies                                //公共依赖包
│  └─ pom.xml
├─ live-gateway                                     //网关服务
│  │  ├─ controller                                 //全局错误处理
│  │  │  └─ HandleErrorController.java
│  │  └─ filter                                     //网关过滤器
│  │     ├─ AccessLogFilter.java
│  │     ├─ HandleZuulExceptionFilter.java
│  │     └─ LoginFilter.java
├─ live-registry                                    //注册中心服务
├─ live-auth                                        //鉴权服务
├─ live-servants                                    //API模块
│  │  ├─ user
│  │  ├─ video
│  │  └─...
├─ live-user                                        //用户服务
└─ live-video                                       //视频直播服务
