package cn.edu.seu.agdatab.user.mapper;

import cn.edu.seu.agdatab.user.entity.DateAvg;
import cn.edu.seu.agdatab.user.entity.DetailQuery;
import cn.edu.seu.agdatab.user.entity.PmtypeVariety;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author group6
 * @since 2020-04-04
 */
public interface DetailQueryMapper extends BaseMapper<DetailQuery> {
    //数据查询
    @Select("select productdate,avgprice from dw_t_type_compare " +
            "where provincename = ${provincename} " +
            "and marketname = ${marketname} " +
            "and type = ${type} and variety = ${variety} order by productdate desc;")
    public List<DateAvg> getDateAvg(String provincename, String marketname,
                                    String type, String variety);


    //数据查询页面的下拉框,得到市场
    @Select("select distinct marketname from dw_t_pmtype_variety where provincename = ${provincename};")
    public List<String> getMarkets(String provincename);

    //数据查询页面的下拉框,得到品类
    @Select("select distinct type from dw_t_pmtype_variety where " +
            "provincename = ${provincename} and marketname = ${marketname};")
    public List<String> getTypes(String provincename,String marketname);

    //数据查询页面的下拉框,得到品种
    @Select("select distinct variety from dw_t_pmtype_variety where " +
            "provincename = ${provincename} and marketname = ${marketname} and type = ${type};")
    public List<String> getVarieties(String provincename,String marketname,String type);

}
