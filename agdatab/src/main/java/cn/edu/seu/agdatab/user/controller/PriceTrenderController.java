package cn.edu.seu.agdatab.user.controller;


import cn.edu.seu.agdatab.user.entity.DateAvgMaxMin;
import cn.edu.seu.agdatab.user.service.IPriceTrenderService;
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
 * @since 2020-03-31
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class PriceTrenderController {
    @Autowired
    private IPriceTrenderService iPriceTrenderService;
    @RequestMapping(value = "/pricetrender",method = {RequestMethod.GET})
    public List<DateAvgMaxMin> showPriceTrender(String provincename,
                                                String marketname,
                                                String type,
                                                String variety, String productmonth){
        return iPriceTrenderService.getPriceTrenderIservice(provincename,
                marketname, type, variety, productmonth);
    }

}
