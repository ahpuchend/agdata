package cn.edu.seu.agdatab.user.service.impl;

import cn.edu.seu.agdatab.user.entity.RealTimeQuery;
import cn.edu.seu.agdatab.user.mapper.RealTimeQueryMapper;
import cn.edu.seu.agdatab.user.service.IRealTimeQueryService;
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
public class RealTimeQueryServiceImpl extends ServiceImpl<RealTimeQueryMapper, RealTimeQuery> implements IRealTimeQueryService {
    @Resource
    private RealTimeQueryMapper realTimeQueryMapper;

    @Override
    public List<RealTimeQuery> showStatics() {

        List<RealTimeQuery> staticsOne = realTimeQueryMapper.getstatics();

        return staticsOne;
    }

}
