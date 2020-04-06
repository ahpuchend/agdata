package cn.edu.seu.agdatab.user.service;

import cn.edu.seu.agdatab.user.entity.RecordLine;
import cn.edu.seu.agdatab.user.entity.RecordRadio;
import cn.edu.seu.agdatab.user.entity.TypeRecord;
import cn.edu.seu.agdatab.user.entity.TypeRecordSelectBox;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author group6
 * @since 2020-03-28
 */
public interface ITypeRecordService extends IService<TypeRecord> {
     List<RecordLine> showTypeLine(String provincename,
                                   String marketname,
                                   String type);
     List<RecordRadio> showTypeRadio();
     List<TypeRecordSelectBox> sbTypeRecord(String provincename);
}
