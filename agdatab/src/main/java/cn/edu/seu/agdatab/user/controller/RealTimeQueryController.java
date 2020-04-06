package cn.edu.seu.agdatab.user.controller;


import cn.edu.seu.agdatab.user.entity.RealTimeQuery;
import cn.edu.seu.agdatab.user.service.IRealTimeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author group6
 * @since 2020-03-31
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class RealTimeQueryController {
    @Autowired
    private IRealTimeQueryService iRealTimeQueryService;
    @RequestMapping(value = "/realtimequery" ,method = {RequestMethod.GET})
    public List<String> showStatics(){
        List<RealTimeQuery> realTimeQueryOne = iRealTimeQueryService.showStatics();
        RealTimeQuery realTimeQuery = realTimeQueryOne.get(0);
        List<String> statics = new ArrayList<String>();
        statics.add(String.valueOf(realTimeQuery.getMarketsum()));
        statics.add(String.valueOf(realTimeQuery.getTypesum()));
        statics.add(String.valueOf(realTimeQuery.getVarietysum()));
        statics.add(String.valueOf(realTimeQuery.getCountsum()));
        statics.add(String.valueOf(realTimeQuery.getDailycount()));
        statics.add(realTimeQuery.getCrawlTime());
        return statics;
    }
}
