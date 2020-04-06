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
 * @since 2020-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_type_record")
public class TypeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String provincename;

    private String marketname;

    private String type;

    private String crawltime;

    public String getProvincename() {
        return provincename;
    }

    public String getMarketname() {
        return marketname;
    }

    public String getType() {
        return type;
    }
}
