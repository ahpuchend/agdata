package cn.edu.seu.agdatab.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration        //标记该类是一个配置类
@EnableTransactionManagement //开启事务管理
@MapperScan("cn.edu.seu.agdatab.*.mapper") //加载mapper接口所在包
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
