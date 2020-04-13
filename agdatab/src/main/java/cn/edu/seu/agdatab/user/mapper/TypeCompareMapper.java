package cn.edu.seu.agdatab.user.mapper;

import cn.edu.seu.agdatab.user.entity.DateAvg;
import cn.edu.seu.agdatab.user.entity.PmtypeVariety;
import cn.edu.seu.agdatab.user.entity.TypeCompare;
import cn.edu.seu.agdatab.user.entity.VarAvgDate;
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
public interface TypeCompareMapper extends BaseMapper<TypeCompare> {
    @Select("select distinct marketname from dw_t_pmtype_variety where " +
            "provincename = ${provincename};")
    List<String> getMarketsMapper(String provincename);
    @Select("select distinct type from dw_t_pmtype_variety " +
            "where provincename = ${provincename} and marketname = ${marketname};")
    List<String> getTypesMapper(String provincename,String marketname);

    @Select("select distinct variety from dw_t_pmtype_variety " +
            "where provincename = ${provincename} and marketname = ${marketname} and type = ${type};")
    List<String> getVarietiesMapper(String provincename,String marketname,String type);

    @Select("select variety,avgprice,productdate from dw_t_type_compare " +
            "where provincename = ${provincename} " +
            "and marketname = ${marketname} " +
            "and type = ${type} order by str_to_date(productdate,'%Y-%m-%d')desc;")
    List<VarAvgDate> getDetailQuery(String provincename, String marketname, String type);
}
