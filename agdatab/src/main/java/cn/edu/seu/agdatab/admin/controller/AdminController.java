package cn.edu.seu.agdatab.admin.controller;


import cn.edu.seu.agdatab.admin.entity.Admin;
import cn.edu.seu.agdatab.admin.entity.User;
import cn.edu.seu.agdatab.admin.mapper.UserMapper;
import cn.edu.seu.agdatab.admin.service.IAdminService;
import cn.edu.seu.agdatab.admin.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService iAdminService;
    private IUserService iUserService;
    @Resource
    private UserMapper userMapper;

    public AdminController(IUserService iUserService) {
        this.iUserService = iUserService;
    }
    @RequestMapping(value = "/userlist/{currentPage}/{pagesize}",method = {RequestMethod.GET})
    public JSONObject query(@PathVariable Integer currentPage, @PathVariable Integer pagesize){
        JSONObject res=new JSONObject();
        try{
            Page<User> page=new Page<User>(currentPage,pagesize) ;
            IPage<User> iPage=userMapper.selectPage(page,null);
            System.out.println("current page:"+iPage.getCurrent());
            System.out.println("page size:"+iPage.getSize());
            System.out.println("total record:"+iPage.getTotal());
            System.out.println("total page:"+iPage.getPages());
            List<User> users=iPage.getRecords();
            JSONArray jsonArray = JSONArray.fromObject(users);
            res.put("code","200");
            res.put("data",jsonArray);
            res.put("num",iPage.getTotal());
            res.put("totalPage",iPage.getPages());
            return res;
        }catch(Exception e){
            res.put("code",400);
            return res;
        }

    }

    @RequestMapping(value = "/login",method = {RequestMethod.GET})
    public JSONObject checkLogin(Admin admin, HttpServletRequest req){
        JSONObject result=new JSONObject();
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<Admin>();
        queryWrapper.eq("adminId",admin.getAdminId()).eq("password",admin.getPassword());
        HttpSession session=req.getSession();
        if(iAdminService.getOne(queryWrapper)!=null){
            session.setAttribute("user",admin);
            result.put("success",true);
            result.put("msg","验证成功");
        }else {
            result.put("success",false);
            result.put("msg","用户名或密码错误");
        }
        return result;
    }
//    @RequestMapping(value = "/add",method = {RequestMethod.GET})
//    public JSONObject addUser(User user){
//        System.out.println(user.toString());
//        JSONObject re=new JSONObject();
//        if(!(user.getUserId() instanceof String)
//                ||user.getPassword()==null
//                || user.getUserId().contains(" ")
//                ||user.getPassword().contains(" ")){
//            re.put("success",false);
//            re.put("msg","请输入合法的用户名和密码");
//            return re;
//        }
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("userId",user.getUserId());
//        User u=iUserService.getOne(queryWrapper);
//        if(u!=null){
//            re.put("success",false);
//            re.put("msg","已有此用户");
//        } else {
//            iUserService.save(user);
//            re.put("success",true);
//            re.put("msg","添加成功！");
//        }
//        return re;
//    }
    @RequestMapping(value = "/del",method = {RequestMethod.GET})
    public JSONObject delUser( @RequestParam(value = "userId",required = true) String userId)throws Exception{
        System.out.println(userId);
        JSONObject re=new JSONObject();
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.eq("userId",userId);
        if( iUserService.remove(queryWrapper)){
            re.put("success",true);
            re.put("msg","删除成功");
        }else{
            re.put("success",false);
            re.put("msg","删除失败");
        }
        return re;
    }
}


