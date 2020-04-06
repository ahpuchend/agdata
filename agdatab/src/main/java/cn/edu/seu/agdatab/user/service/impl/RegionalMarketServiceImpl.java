package cn.edu.seu.agdatab.user.service.impl;

import cn.edu.seu.agdatab.user.entity.ProvincePrice;
import cn.edu.seu.agdatab.user.entity.RegionalMarket;
import cn.edu.seu.agdatab.user.mapper.RegionalMarketMapper;
import cn.edu.seu.agdatab.user.service.IRegionalMarketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class RegionalMarketServiceImpl extends ServiceImpl<RegionalMarketMapper, RegionalMarket> implements IRegionalMarketService {
    @Resource
    private RegionalMarketMapper regionalMarketMapper;

    public List<ProvincePrice> showRegionalQuotations(String type, String variety){
        List<ProvincePrice> listRq =regionalMarketMapper.getPnameAvgPri(type,variety);
        return listRq;
    }

    @Override
    public List<String> showVarieties(String type) {
        List<String> listVarieties = regionalMarketMapper.getVarities(type);
        return listVarieties;
    }
}
