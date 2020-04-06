package cn.edu.seu.agdatab.user.service.impl;

import cn.edu.seu.agdatab.user.entity.RecordLine;
import cn.edu.seu.agdatab.user.entity.RecordRadio;
import cn.edu.seu.agdatab.user.entity.TypeRecord;
import cn.edu.seu.agdatab.user.entity.TypeRecordSelectBox;
import cn.edu.seu.agdatab.user.mapper.TypeRecordMapper;
import cn.edu.seu.agdatab.user.service.ITypeRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author group6
 * @since 2020-03-28
 */
@Service
public class TypeRecordServiceImpl extends ServiceImpl<TypeRecordMapper, TypeRecord> implements ITypeRecordService {

    @Resource
    private TypeRecordMapper typeRecordMapper;

    public List<RecordLine> showTypeLine(String provincename,
                                         String marketname,
                                         String type){
        List<RecordLine> recordLines = typeRecordMapper.typeforline(provincename,marketname,type);
        for (RecordLine tl:recordLines){
            System.out.println(tl.getCrawlTime());
            System.out.println(tl.getCrawlSum());
        }
        return recordLines;
    }

    public List<RecordRadio> showTypeRadio(){
        List<RecordRadio> recordRadios = typeRecordMapper.typeforradio();
        System.out.println(recordRadios);
        for (RecordRadio tr:recordRadios){
            System.out.println(tr.getType());
            System.out.println(tr.getTypenum());
        }
        System.out.println(recordRadios);
        return recordRadios;
    }

    public List<TypeRecordSelectBox> sbTypeRecord(String provincename) {
        List<TypeRecordSelectBox> typeRecordSelectBoxes = new ArrayList<TypeRecordSelectBox>();
        List<String> markets= typeRecordMapper.getMarkets(provincename);
        for (String m: markets){
            String market = '\''+ m+'\'';
            List<String> types = typeRecordMapper.getTypes(provincename,market);
            TypeRecordSelectBox typeRecordSelectBox = new TypeRecordSelectBox();
            typeRecordSelectBox.setMarketname(m);
            typeRecordSelectBox.setListtype(types);
            typeRecordSelectBoxes.add(typeRecordSelectBox);
        }
        return typeRecordSelectBoxes;
    }
}
