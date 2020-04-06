package cn.edu.seu.agdatab.user.controller;


import cn.edu.seu.agdatab.user.entity.TypeCompare;
import cn.edu.seu.agdatab.user.entity.VarAvgDate;
import cn.edu.seu.agdatab.user.entity.VarietyAvgPrice;
import cn.edu.seu.agdatab.user.service.ITypeCompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
public class TypeCompareController {
    @Autowired
    private ITypeCompareService iTypeCompareService;
    @RequestMapping(value ="/tcsbmarkets" ,method = {RequestMethod.GET})
    public List<String> showMarkets(String provincename){
        List<String> marketList = iTypeCompareService.getMarketsIservice(provincename);
        return marketList;
    }
    @RequestMapping(value ="/tcsbtypes" ,method = {RequestMethod.GET})
    public List<String> showTypes(String provincename,String marketname){
        List<String> typeList = iTypeCompareService.getTypesIservice(provincename,marketname);
        return typeList;
    }
    @RequestMapping(value = "/tcsbvarieties",method = {RequestMethod.GET})
    public  List<String> showVarieties(String provincename,String marketname,String type){
        return iTypeCompareService.getVarietiesIservice(provincename, marketname, type);
    }
    @RequestMapping(value = "/typecompare",method = {RequestMethod.GET})
    List<VarietyAvgPrice>  showDetailQuery(String provincename,String marketname,String type){
        List<VarietyAvgPrice> varietyAvgPriceList = iTypeCompareService.getDetailQuery(provincename, marketname, type);
        return varietyAvgPriceList;
    }
}
