package cn.edu.seu.agdatab.user.controller;


import cn.edu.seu.agdatab.user.service.ICloudService;
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
@RestController
@RequestMapping("/user")
@CrossOrigin
public class CloudController {
    @Autowired
    private ICloudService iCloudService;
    @RequestMapping(value = "/showcloud",method = {RequestMethod.GET})
    public List<String> showCloud(){
        List<String >  listTypeCloud =  iCloudService.showTypeCloud();
        return listTypeCloud;
    }

}
