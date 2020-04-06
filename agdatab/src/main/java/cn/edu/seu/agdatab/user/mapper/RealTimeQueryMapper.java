package cn.edu.seu.agdatab.user.mapper;

import cn.edu.seu.agdatab.user.entity.RealTimeQuery;
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
public interface RealTimeQueryMapper extends BaseMapper<RealTimeQuery> {
@Select("select marketsum,typesum,varietysum,countsum,dailycount,crawltime from dw_t_real_time_query;")
    List<RealTimeQuery> getstatics();
}
