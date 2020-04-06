package cn.edu.seu.agdatab.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author group6
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dw_t_real_time_query")
public class RealTimeQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer marketsum;

    private Integer typesum;

    private Integer varietysum;

    private Long countsum;

    private Integer dailycount;

    @TableField("crawlTime")
    private String crawlTime;


    public Integer getMarketsum() {
        return marketsum;
    }

    public Integer getTypesum() {
        return typesum;
    }

    public Integer getVarietysum() {
        return varietysum;
    }

    public Long getCountsum() {
        return countsum;
    }

    public Integer getDailycount() {
        return dailycount;
    }

    public String getCrawlTime() {
        return crawlTime;
    }
}
