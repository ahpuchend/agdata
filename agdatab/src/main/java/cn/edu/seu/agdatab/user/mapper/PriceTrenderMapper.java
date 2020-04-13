package cn.edu.seu.agdatab.user.mapper;

import cn.edu.seu.agdatab.user.entity.DateAvgMaxMin;
import cn.edu.seu.agdatab.user.entity.PriceTrender;
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
public interface PriceTrenderMapper extends BaseMapper<PriceTrender> {
    @Select("select productdate,avgprice,maxprice,minprice " +
            "from dw_t_price_trender " +
            "where provincename = ${provincename} and " +
            "marketname = ${marketname} and " +
            "type = ${type} and variety = ${variety} and " +
            "productmonth = ${productmonth} order by str_to_date(productdate,'%Y-%m-%d') desc;")
    List<DateAvgMaxMin> getDateAvgMaxMinMapper(String provincename,
                                               String marketname,
                                               String type,
                                               String variety,
                                               String productmonth);
}
