package cn.edu.seu.agdatab.user.mapper;

import cn.edu.seu.agdatab.user.entity.DateAvg;
import cn.edu.seu.agdatab.user.entity.PricePrediction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @author group6
 * @since 2020-03-31
 */
public interface PricePredictionMapper extends BaseMapper<PricePrediction> {
    @Select("select productdate,avgprice " +
            "from  dw_t_price_prediction " +
            "where provincename = ${provincename} and marketname = ${marketname} " +
            "and type = ${type} and variety = ${variety}" +
            "order by str_to_date(productdate,'%Y-%m-%d') desc limit 5;")
    List<DateAvg> getPricePridictio(String provincename,
                                    String marketname,
                                    String type,
                                    String variety);
}
