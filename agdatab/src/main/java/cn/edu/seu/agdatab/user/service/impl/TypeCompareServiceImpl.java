package cn.edu.seu.agdatab.user.service.impl;

import cn.edu.seu.agdatab.user.entity.TypeCompare;
import cn.edu.seu.agdatab.user.entity.VarAvgDate;
import cn.edu.seu.agdatab.user.entity.VarietyAvgPrice;
import cn.edu.seu.agdatab.user.mapper.TypeCompareMapper;
import cn.edu.seu.agdatab.user.service.ITypeCompareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author group6
 * @since 2020-03-31
 */
@Service
public class TypeCompareServiceImpl extends ServiceImpl<TypeCompareMapper, TypeCompare> implements ITypeCompareService {
    @Resource
    private TypeCompareMapper typeCompareMapper;
    @Override
    public List<String> getMarketsIservice(String provincename) {
        List<String> markets = typeCompareMapper.getMarketsMapper(provincename);
        return markets;
    }
    @Override
    public List<String> getTypesIservice(String provincename, String marketname) {
        List<String> types = typeCompareMapper.getTypesMapper(provincename,marketname);
        return types;
    }

    @Override
    public List<String> getVarietiesIservice(String provincename, String marketname, String type) {
        return typeCompareMapper.getVarietiesMapper(provincename, marketname, type);
    }

    @Override
    public List<VarietyAvgPrice> getDetailQuery(String provincename, String marketname, String type) {
       List<VarAvgDate> varAvgDateList =  typeCompareMapper.getDetailQuery(provincename, marketname, type);
       Set<String> distinctVariety = new HashSet<String>();
       List<VarietyAvgPrice> varietyAvgPriceList = new ArrayList<VarietyAvgPrice>();
        for (VarAvgDate vad: varAvgDateList) {
            distinctVariety.add(vad.getVariety());
        }
        for(String s: distinctVariety){
            VarietyAvgPrice varietyAvgPrice =  new VarietyAvgPrice();
            varietyAvgPrice.setVariety(s);
            for(int i = 0; i < varAvgDateList.size(); ++i){
                VarAvgDate varAvgDate = varAvgDateList.get(i);
                if(varAvgDate.getVariety().equals(s)){
                    varietyAvgPrice.setAvgPriceList(varAvgDate.getAvgPrice());
                    varietyAvgPrice.setProductDataList(varAvgDate.getProductDate());
                }
            }
            varietyAvgPriceList.add(varietyAvgPrice);
        }
       return varietyAvgPriceList;
    }

}
