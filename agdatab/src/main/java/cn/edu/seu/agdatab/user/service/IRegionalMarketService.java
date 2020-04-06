package cn.edu.seu.agdatab.user.service;

import cn.edu.seu.agdatab.user.entity.ProvincePrice;
import cn.edu.seu.agdatab.user.entity.RegionalMarket;
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
public interface IRegionalMarketService extends IService<RegionalMarket> {
    List<ProvincePrice> showRegionalQuotations(String type, String variety);
    List<String> showVarieties(String type);
}
