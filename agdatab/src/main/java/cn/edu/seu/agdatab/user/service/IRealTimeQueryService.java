package cn.edu.seu.agdatab.user.service;

import cn.edu.seu.agdatab.user.entity.RealTimeQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author group6
 * @since 2020-03-31
 */
public interface IRealTimeQueryService extends IService<RealTimeQuery> {
    List<RealTimeQuery> showStatics();
}
