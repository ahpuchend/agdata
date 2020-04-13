package cn.edu.seu.agdatab.user.service;

import cn.edu.seu.agdatab.user.entity.DateAvg;
import cn.edu.seu.agdatab.user.entity.PricePrediction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author group6
 * @since 2020-03-31
 */
public interface IPricePredictionService extends IService<PricePrediction> {
    List<DateAvg> pricePredictionIservice(String provincename,
                                          String marketname,
                                          String type,
                                          String variety) throws ParseException;
}
