package cn.edu.seu.agdatab.user.mapper;

import cn.edu.seu.agdatab.user.entity.TypeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import cn.edu.seu.agdatab.user.entity.RecordLine;
import cn.edu.seu.agdatab.user.entity.RecordRadio;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author group6
 * @since 2020-03-28
 */
public interface TypeRecordMapper extends BaseMapper<TypeRecord> {
    @Select("select date_format(crawltime,'%Y-%m-%d') crawltime, count(type) crawlSum " +
            " from dw_t_type_record " +
            "where provincename = ${provincename} and " +
            "marketname = ${marketname} and type = ${type} " +
            "group by  date_format(crawltime,'%Y-%m-%d') order by date_format(crawltime,'%Y-%m-%d') desc limit 7 ;")
    List<RecordLine> typeforline(String provincename, String marketname, String type);

    @Select("select type,count(type) typenum from dw_t_type_record group by type")
    List<RecordRadio> typeforradio();

    @Select("select distinct(marketname) from dw_t_type_record where provincename = ${provincename};")
    List<String> getMarkets(String provincename);

    @Select("select distinct(type) from dw_t_type_record " +
            "where provincename = ${provincename} and marketname = ${marketname};")
    List<String> getTypes(String provincename,String marketname);

}
