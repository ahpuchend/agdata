package cn.edu.seu.agdatab.admin.controller;

import cn.edu.seu.agdatab.admin.entity.User;
import cn.edu.seu.agdatab.admin.service.IUserService;
import cn.edu.seu.agdatab.util.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuliang
 * @since 2020-03-25
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Resource
    //注入bcryct加密
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/login",method = {RequestMethod.GET})
    public JSONObject checkLogin(User user, HttpServletRequest req){
        JSONObject result=new JSONObject();
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.eq("userId",user.getUserId());
        HttpSession session=req.getSession(true);
        if(iUserService.getOne(queryWrapper)!=null && bCryptPasswordEncoder.matches(user.getPassword().trim(),
                iUserService.getOne(queryWrapper).getPassword().trim()) ){
            session.setAttribute("user",user);
            System.out.println(user.getUserId() +" 已登录");
            result.put("success",true);
            result.put("msg","验证成功");
        }else {
            System.out.println("登录fail");
            result.put("success",false);
            result.put("msg","用户名或密码错误");
        }
        return result;
    }

    //用于用户登出
    @RequestMapping(value = "/logout",method = {RequestMethod.GET})
    public JSONObject checkOut(HttpServletRequest req){
        JSONObject result=new JSONObject();
        HttpSession session=req.getSession();
        session.setAttribute("user",null);
        result.put("success",true);
        result.put("msg","您已登出");
        return result;
    }

    //用于用户注册
    @RequestMapping(value = "/register",method = {RequestMethod.GET})
    public JSONObject addUser(User user){
        JSONObject result=new JSONObject();
//        if(!(user.getPassword() instanceof String)||!(user.getUserId() instanceof String)){
//            result.put("success",false);
//            result.put("msg","用户名和密码不能为空！");
//            return result;
//        }
//        if(user.getUserId().contains(" ")||user.getPassword().contains(" ")){
//            result.put("success",false);
//            result.put("msg","用户名和密码不能包含空格！");
//            return result;
//        }
//        System.out.println(user.getUserId());
//        System.out.println(user.getPassword());
        if(!Validator.isUsername(user.getUserId().trim()) && !Validator.isPassword(user.getPassword().trim())){
            result.put("success",false);
            result.put("msg","用户名或密码不合法");
            return result;
        }
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",user.getUserId().trim());
        if(iUserService.getOne(queryWrapper)!=null){
            result.put("success",false);
            result.put("msg","此用户已被注册过请重新注册");
        }else {
            //可以注册新用户,密码入库时加密
           user.setPassword(bCryptPasswordEncoder.encode(user.getPassword().trim()));
//            System.out.printf("-----------  " + user.getPassword() + " ---------------------");
            iUserService.save(user);
            System.out.println("用户已被成功添加");
            result.put("success",true);
            result.put("msg","用户已成功注册！");
        }
        return result;

    }
}




















