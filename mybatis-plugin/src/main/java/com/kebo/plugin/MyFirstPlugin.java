package com.kebo.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * @description:
 * @Author: kb
 * @Date: 2021-02-04 13:42
 */
@Intercepts(
        {
                @Signature(type = StatementHandler.class,
                        method = "parameterize",args = java.sql.Statement.class)
        })
public class MyFirstPlugin implements Interceptor {

    /**
     * 拦截目标对象方法的执行
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin...intercept"+invocation.getMethod());
        //动态的改变一下sql运行的参数，以前1号学生，实际查询5号学生
        Object target = invocation.getTarget();
//        System.out.println("当前拦截到的对象"+invocation.getTarget());

        //拿到StatementHandler==>ParameterHandler==>parameterObject
        //拿到target的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是"+value);
        //修改完sql语句要执行的参数
        metaObject.setValue("parameterHandler.parameterObject",5);
        //放行的方法
        Object proceed = invocation.proceed();

        return proceed;
    }

    /**
     * 包装目标对象，将增强的对象和拦截器包装起来并返回
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin..plugin:mybatis将要包装的对象"+target);
        //我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们的目标对象
        Object wrap = Plugin.wrap(target, this);
        //返回当前target创建的动态代理
        return wrap;
    }

    /**
     * 将插件注册时的property属性
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println(properties);
        System.out.println("插件配置的信息");
    }
}

