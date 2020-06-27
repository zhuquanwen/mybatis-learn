package com.learn.zqw.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/6/27 12:58
 * @since jdk1.8
 */
@Slf4j
@Intercepts(@Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class TestPlugin implements Interceptor {
    private Properties properties;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object paramter = args[1];
        String sqlid = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(paramter);
        String sql = boundSql.getSql();
        log.debug(sql);
        long start = System.currentTimeMillis();

        Object proceed = invocation.proceed();
        long end = System.currentTimeMillis();
        log.debug("耗时：" + (end - start) + "ms");
        return proceed;

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
