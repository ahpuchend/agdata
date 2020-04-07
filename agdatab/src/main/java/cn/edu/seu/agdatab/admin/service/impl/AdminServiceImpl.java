package cn.edu.seu.agdatab.admin.service.impl;

import cn.edu.seu.agdatab.admin.entity.Admin;
import cn.edu.seu.agdatab.admin.mapper.AdminMapper;
import cn.edu.seu.agdatab.admin.service.IAdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
