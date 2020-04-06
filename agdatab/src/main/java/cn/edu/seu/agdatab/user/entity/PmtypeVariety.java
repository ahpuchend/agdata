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
 * @since 2020-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dw_t_pmtype_variety")
public class PmtypeVariety implements Serializable {

    private static final long serialVersionUID = 1L;

    private String provincename;

    @TableField("marketName")
    private String marketName;

    public String getProvincename() {
        return provincename;
    }

    public String getMarketName() {
        return marketName;
    }

    public String getType() {
        return type;
    }

    public String getVariety() {
        return variety;
    }

    private String type;

    private String variety;

}
