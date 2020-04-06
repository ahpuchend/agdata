package cn.edu.seu.agdatab.user.mapper;

import cn.edu.seu.agdatab.user.entity.Cloud;
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
public interface CloudMapper extends BaseMapper<Cloud> {
    @Select("select type from dw_t_cloud order by count desc limit 10;")
    List<String> getTop10Type();
}
