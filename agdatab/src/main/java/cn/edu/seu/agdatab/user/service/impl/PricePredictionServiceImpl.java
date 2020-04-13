package cn.edu.seu.agdatab.user.service.impl;

import cn.edu.seu.agdatab.user.entity.DateAvg;
import cn.edu.seu.agdatab.user.entity.PricePrediction;
import cn.edu.seu.agdatab.user.mapper.PricePredictionMapper;
import cn.edu.seu.agdatab.user.service.IPricePredictionService;
import cn.edu.seu.agdatab.util.FormatDate;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author group6
 * @since 2020-03-31
 */
@Service
public class PricePredictionServiceImpl extends ServiceImpl<PricePredictionMapper, PricePrediction> implements IPricePredictionService {
    @Resource
    private PricePredictionMapper pricePredictionMapper;

    @Override
    public List<DateAvg> pricePredictionIservice(String provincename, String marketname, String type, String variety) throws ParseException {
        List<DateAvg> pricePredictionList = pricePredictionMapper.getPricePridictio(provincename,
                marketname, type, variety);

        String productDate = null;
        //从现在时间往后推5天
        for(int i =1;i<=5;++i){
           productDate =  FormatDate.getBeforTime(new Date(),i);

        }
        return pricePredictionList;
    }
}
