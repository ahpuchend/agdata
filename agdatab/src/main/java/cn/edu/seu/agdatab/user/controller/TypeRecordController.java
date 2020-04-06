package cn.edu.seu.agdatab.user.controller;

import cn.edu.seu.agdatab.user.entity.RecordLine;
import cn.edu.seu.agdatab.user.entity.RecordRadio;
import cn.edu.seu.agdatab.user.entity.TypeRecord;
import cn.edu.seu.agdatab.user.entity.TypeRecordSelectBox;
import cn.edu.seu.agdatab.user.service.ITypeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author group6
 * @since 2020-03-28
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class TypeRecordController {
    @Autowired
    private ITypeRecordService iTypeRecordService;

    @RequestMapping(value = "/selectboxs",method = {RequestMethod.GET})
    public List<TypeRecordSelectBox> showSelectBox(String provincename){
        List<TypeRecordSelectBox> selectContents =  iTypeRecordService.sbTypeRecord(provincename);
        return selectContents;
    }

    @RequestMapping(value = "/recordline",method = {RequestMethod.GET})
    public List<RecordLine> recordList(TypeRecord typeRecord){
        List<RecordLine> typeAndTypeRecord = iTypeRecordService.showTypeLine(typeRecord.getProvincename(),
                typeRecord.getMarketname(),
                typeRecord.getType());
        return typeAndTypeRecord;
    }

    @RequestMapping(value = "/recordradio",method = {RequestMethod.GET})
    public  List<RecordRadio> recordRadio(){
        List<RecordRadio> typeAndtypeRadio = iTypeRecordService.showTypeRadio();
        return typeAndtypeRadio;
    }
}
