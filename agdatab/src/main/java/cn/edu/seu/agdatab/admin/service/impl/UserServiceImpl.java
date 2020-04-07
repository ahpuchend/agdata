package cn.edu.seu.agdatab.admin.service.impl;

import cn.edu.seu.agdatab.admin.entity.User;
import cn.edu.seu.agdatab.admin.mapper.UserMapper;
import cn.edu.seu.agdatab.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuliang
 * @since 2020-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
