# 变强之路先从一个自研RPC开始吧~

## 0.1.0 版本计划

+ 动态代理Stub
+ 简单的序列化+反序列化
+ 完成服务器端+客户端之间的通讯
+ 最后完成一个mvp版本能run起来
+ 架构图如下，下面会由下到上介绍模块功能

![avatar](https://github.com/evancyz/MarkDownPhotos/blob/master/triple/Triple-Structure.jpg?raw=true)

+ <code>Serilizer</code> 序列化器，负责网络层传输的数据的序列化/反序列化
+ <code>Net</code> 网络层，负责将定义网络传输领对象和定义传输协议（socket、netty等）
+ <code>RegisterCenter</code> 注册中心，负责服务的注册与发现
+ <code>Proxy</code> 提供一套代理方案给客户端使用，主要功能是服务发现，收发网络请求
+ <code>Server</code> 服务提供者，主要功能是，服务注册，监听服务，调用本地方法，返回调用结果

