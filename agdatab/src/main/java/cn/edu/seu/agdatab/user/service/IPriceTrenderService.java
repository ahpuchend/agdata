package cn.edu.seu.agdatab.user.service;

import cn.edu.seu.agdatab.user.entity.DateAvgMaxMin;
import cn.edu.seu.agdatab.user.entity.PriceTrender;
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
public interface IPriceTrenderService extends IService<PriceTrender> {
    List<DateAvgMaxMin> getPriceTrenderIservice(String provincename,
                                                String marketname,
                                                String type,
                                                String variety,
                                                String productmonth);
}
