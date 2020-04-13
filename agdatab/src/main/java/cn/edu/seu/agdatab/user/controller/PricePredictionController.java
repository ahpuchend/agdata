package cn.edu.seu.agdatab.user.controller;


import cn.edu.seu.agdatab.user.entity.DateAvg;
import cn.edu.seu.agdatab.user.entity.PricePrediction;
import cn.edu.seu.agdatab.user.service.IPricePredictionService;
import cn.edu.seu.agdatab.user.service.impl.PricePredictionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author group6
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/user")
public class PricePredictionController {
    @Autowired
    private IPricePredictionService iPricePredictionService;
    @RequestMapping(value = "/priceprediction",method = {RequestMethod.GET})
    List<DateAvg> showPricePrediction(String provincename, String marketname,
                                      String type, String variety) throws ParseException {
        return iPricePredictionService.pricePredictionIservice(provincename, marketname, type, variety);
    }
}
