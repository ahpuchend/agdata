package cn.edu.seu.agdatab.user.controller;


import cn.edu.seu.agdatab.user.entity.ProvincePrice;
import cn.edu.seu.agdatab.user.entity.RegionalMarket;
import cn.edu.seu.agdatab.user.service.IRegionalMarketService;
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
public class RegionalMarketController {
    @Autowired
    private IRegionalMarketService iRegionalMarketService;


    @RequestMapping(value = "/rmselectbox", method={RequestMethod.GET})
    public List<String> showType(String type){
        List<String> varities = iRegionalMarketService.showVarieties(type);
        return varities;
    }

    @RequestMapping(value = "/regionalmarket",method = {RequestMethod.GET})
    public List<ProvincePrice> showRegionalMarket(String type,String variety){
        List<ProvincePrice> pplist =  iRegionalMarketService.showRegionalQuotations(
                type,variety);
        return pplist;
    }
}
