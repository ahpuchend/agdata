package cn.edu.seu.agdatab.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("dw_t_regional_market")
public class RegionalMarket implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String provincename;

    private String type;

    private String variety;

    private Double avgprice;

    private String productdate;


}
