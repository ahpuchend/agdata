package cn.edu.seu.agdatab.user.service;

import cn.edu.seu.agdatab.user.entity.TypeCompare;
import cn.edu.seu.agdatab.user.entity.VarAvgDate;
import cn.edu.seu.agdatab.user.entity.VarietyAvgPrice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author group6
 * @since 2020-03-31
 */
public interface ITypeCompareService extends IService<TypeCompare> {
    List<String> getMarketsIservice(String provincename);
    List<String> getTypesIservice(String provincename, String marketname);
    List<String> getVarietiesIservice(String provincename,String marketname,
                                      String type);
    List<VarietyAvgPrice> getDetailQuery(String provincename, String marketname, String type);
}
