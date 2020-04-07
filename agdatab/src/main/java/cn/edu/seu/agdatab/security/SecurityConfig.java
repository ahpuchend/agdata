package cn.edu.seu.agdatab.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//                .loginPage("/login")	 					    // 设置登录页面
                .loginProcessingUrl("/html/userlogin.html")	;		    // 自定义的登录接口
//                .defaultSuccessUrl("/html/aindex.html").permitAll()	;	    // 登录成功之后，默认跳转的页面
//                .and().authorizeRequests()					    // 定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/","/user/register","/html/**").permitAll()    // 设置所有人都可以访问登录页面
//                .anyRequest().authenticated() 				    // 任何请求,登录后可以访问
//                .and().csrf().disable(); 					    // 关闭csrf防护
    }
    /*
     * 注入BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
