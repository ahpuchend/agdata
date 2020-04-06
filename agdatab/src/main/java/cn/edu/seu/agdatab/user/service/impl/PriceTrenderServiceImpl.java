package cn.edu.seu.agdatab.user.service.impl;

import cn.edu.seu.agdatab.user.entity.DateAvgMaxMin;
import cn.edu.seu.agdatab.user.entity.PriceTrender;
import cn.edu.seu.agdatab.user.mapper.PriceTrenderMapper;
import cn.edu.seu.agdatab.user.service.IPriceTrenderService;
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
public class PriceTrenderServiceImpl extends ServiceImpl<PriceTrenderMapper, PriceTrender> implements IPriceTrenderService {
    @Resource
    private PriceTrenderMapper priceTrenderMapper;
    @Override
    public List<DateAvgMaxMin> getPriceTrenderIservice(String provincename,
                                                       String marketname, String type,
                                                       String variety, String productmonth) {
        return  priceTrenderMapper.getDateAvgMaxMinMapper(provincename, marketname, type, variety, productmonth);
    }
}