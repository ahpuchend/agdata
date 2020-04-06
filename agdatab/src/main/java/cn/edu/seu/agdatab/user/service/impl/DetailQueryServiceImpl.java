package cn.edu.seu.agdatab.user.service.impl;

import cn.edu.seu.agdatab.user.entity.DateAvg;
import cn.edu.seu.agdatab.user.entity.DetailQuery;
import cn.edu.seu.agdatab.user.mapper.DetailQueryMapper;
import cn.edu.seu.agdatab.user.service.IDetailQueryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author group6
 * @since 2020-04-04
 */
@Service
public class DetailQueryServiceImpl extends ServiceImpl<DetailQueryMapper, DetailQuery> implements IDetailQueryService {
        @Resource
        private DetailQueryMapper detailQueryMapper;

        @Override
        public List<String> getMarketIService(String provincename) {
            List<String> markets = detailQueryMapper.getMarkets(provincename);
            return markets;
        }

        @Override
        public List<String> getTypeIService(String provincename, String marketname) {
            List<String> types =detailQueryMapper.getTypes(provincename,marketname);
            return types;
        }

        @Override
        public List<String> getVarietyIService(String provincename, String marketname, String type) {
            List<String > varieties = detailQueryMapper.getVarieties(provincename,marketname,type);
            return varieties;
        }

    @Override
    public List<DateAvg> getDateAvgIService(String provincename, String marketname, String type, String variety) {
        List<DateAvg> dateAvgList = detailQueryMapper.getDateAvg(provincename,marketname,type,variety);
//        format productdate
        for (DateAvg dateAvg: dateAvgList){
            System.out.println(dateAvg.getProductDate());
            System.out.println(dateAvg.getAvgPrice());
        }
        return dateAvgList;
    }

}
