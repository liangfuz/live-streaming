# Spring cloud实现直播搭建

目前服务包括注册中心，配置中心，网关服务，鉴权服务，用户服务以及视频直播间服务
注册中心使用eureka，网关使用zuul，鉴权使用shiro，ORM框架采用hibernate（生产推荐mybatis）
直播采用rtmp协议（具体搭建请看我的博客<a href="https://blog.developabc.com/2019/05/29/Video-RTMP.html">Nginx+RTMP搭建流媒体直播服务器</a>

PS:有空逐步集成服务治理zipkin，elk，<a href="https://blog.developabc.com/2019/04/01/docker-install.html">docker打包</a>等
```
├─ live-common                                      //基础工具
├─ live-config                                      //配置中心服务  port:8002
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
│  │  ├─ bean
│  │  ├─ cache
│  │  ├─ config
│  │  ├─ entity
│  │  ├─ repository
│  │  └─ service
├─ live-dependencies                                //公共依赖包
│  └─ pom.xml
├─ live-gateway                                     //网关服务  port:8003
│  │  ├─ controller                                 //全局错误处理
│  │  │  └─ HandleErrorController.java
│  │  └─ filter                                     //网关过滤器
│  │     ├─ AccessLogFilter.java
│  │     ├─ HandleZuulExceptionFilter.java
│  │     └─ LoginFilter.java
├─ live-registry                                    //注册中心服务  port:8001
├─ live-auth                                        //鉴权服务  port:8010
├─ live-servants                                    //API模块
│  │  ├─ user
│  │  ├─ video
│  │  └─...
├─ live-user                                        //用户服务  port:8011
└─ live-video                                       //视频直播服务  port:8012
```

启动步骤，修改数据源配置，redis配置之后按注册中心，配置中心，网关，鉴权，用户以及视频服务启动（先启动注册而后配置中心，之后随便启动其他的）