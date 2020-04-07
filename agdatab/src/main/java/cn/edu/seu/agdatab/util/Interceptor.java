package cn.edu.seu.agdatab.util;

import cn.edu.seu.agdatab.admin.entity.User;
import com.fasterxml.jackson.databind.DatabindContext;
import org.apache.catalina.Session;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@CrossOrigin
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Boolean flag = true;
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if(null == user){
            System.out.println("由于用户未登录,拦截");
            flag = false;
            response.sendRedirect("/userlogin.html");
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}
