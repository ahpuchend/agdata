package cn.edu.seu.agdatab;

import cn.edu.seu.agdatab.util.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebSecurity
@MapperScan("cn.edu.seu.agdatab.*.mapper")
public class AgdatabApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(AgdatabApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration ir=registry.addInterceptor(new Interceptor());
        //若用户或者管理员没有登录,拦截所有请求
        ir.addPathPatterns("/user/realtimequery",
                "/user/showcloud",
                "/user/dqsbmarkets",
                "/user/dqsbtypes",
                "/user/dqsbvariety",
                "/user/detailquery",
                "/user/pricetrender",
                "/user/rmselectbox",
                "/user/regionalmarket",
                "/user/typecompare",
                "/user/tcsbtypes",
                "/user/tcsbmarkets",
                "/user/tcsbvarieties",
                "/user/selectboxs",
                "/user/recordradio"
                );
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/html/userlogin.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
