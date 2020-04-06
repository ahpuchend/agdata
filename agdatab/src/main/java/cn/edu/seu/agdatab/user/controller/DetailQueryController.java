package cn.edu.seu.agdatab.user.controller;


import cn.edu.seu.agdatab.user.entity.DateAvg;
import cn.edu.seu.agdatab.user.entity.PmtypeVariety;
import cn.edu.seu.agdatab.user.service.IDetailQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author group6
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class DetailQueryController {
    @Autowired
    private IDetailQueryService iDetailQueryService;

    @RequestMapping(value ="/dqsbmarkets" ,method = {RequestMethod.GET})
    public List<String> showMarkets(String provincename){
        List<String> marketList = iDetailQueryService.getMarketIService(provincename);
        return marketList;
    }
    @RequestMapping(value ="/dqsbtypes" ,method = {RequestMethod.GET})
    public List<String> showTypes(String provincename,String marketname){
        List<String> typeList = iDetailQueryService.getTypeIService(provincename,marketname);
        return typeList;
    }
    @RequestMapping(value ="/dqsbvariety" ,method = {RequestMethod.GET})
    public List<String> showVarieties(String provincename,String marketname,String type){
        List<String> varietyList = iDetailQueryService.getVarietyIService(provincename,marketname,type);
        return varietyList;
    }

    @RequestMapping(value = "/detailquery", method = {RequestMethod.GET})
    public List<DateAvg> showDateAvg(String provincename,String marketname,
                                     String type,String variety){
        List<DateAvg> dateAvgList =  iDetailQueryService.getDateAvgIService(provincename, marketname, type, variety);
        return dateAvgList;

    }



}
