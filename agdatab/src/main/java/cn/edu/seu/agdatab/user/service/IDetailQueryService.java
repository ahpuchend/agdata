package cn.edu.seu.agdatab.user.service;

import cn.edu.seu.agdatab.user.entity.DateAvg;
import cn.edu.seu.agdatab.user.entity.DetailQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author group6
 * @since 2020-04-04
 */
public interface IDetailQueryService extends IService<DetailQuery> {
    //selects
    List<String> getMarketIService(String provincename);
    List<String> getTypeIService(String provincename,String marketname);
    List<String> getVarietyIService(String provincename,String marketname,String type);

    //bar
    List<DateAvg> getDateAvgIService(String provincename,
                                     String marketname,
                                     String type,
                                     String variety);




}
