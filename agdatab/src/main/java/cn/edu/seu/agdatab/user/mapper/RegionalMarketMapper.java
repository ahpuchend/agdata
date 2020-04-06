package cn.edu.seu.agdatab.user.mapper;

import cn.edu.seu.agdatab.user.entity.ProvincePrice;
import cn.edu.seu.agdatab.user.entity.RegionalMarket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author group6
 * @since 2020-03-31
 */
public interface RegionalMarketMapper extends BaseMapper<RegionalMarket> {
    @Select(" select provincename,  round(sum(avgprice)/count(avgprice),2) avgprice  from dw_t_regional_market " +
            "where type=${type} and variety=${variety} group by provincename;")
    List<ProvincePrice> getPnameAvgPri(String type, String variety);

    @Select("select distinct(variety) from dw_t_regional_market where type =${type};")
    List<String> getVarities(String type);

}
