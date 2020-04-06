package cn.edu.seu.agdatab.user.service.impl;

import cn.edu.seu.agdatab.user.entity.Cloud;
import cn.edu.seu.agdatab.user.mapper.CloudMapper;
import cn.edu.seu.agdatab.user.service.ICloudService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author group6
 * @since 2020-03-31
 */
@Service
public class CloudServiceImpl extends ServiceImpl<CloudMapper, Cloud> implements ICloudService {
    @Resource
    private CloudMapper cloudMapper;
    @Override
    public List<String> showTypeCloud() {
        List<String> listType = cloudMapper.getTop10Type();
        for(String cloud:listType){
            System.out.println(cloud);
        }
        return listType;
    }
}
