package util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/***
 *
 * 统一省份Id和市场Id
 * 同名的省份,省份Id必须相同；
 * 同一省份下的同一市场,市场ID必须相同
 *
 */

public class ProMarIdUnified  {
    //pair<省份名,省份id>
    static Map<String , UUID> proNameIdMap = new HashMap<String, UUID>();
    static Map<String, UUID> marNameIdMap = new HashMap<String, UUID>();
    /**
     * @param provinceName
     * @return
     */
    public static UUID unifiedProvinceId(String provinceName){
        UUID provinceId = null;
        if(proNameIdMap.get(provinceName) != null){
            provinceId = proNameIdMap.get(provinceName);
        }else{
            provinceId = UUID.randomUUID();
            proNameIdMap.put(provinceName,provinceId);
        }
        return provinceId;
    }

    /**
     * 市场id 由省份名 和 市场名 唯一确定
     * @param provinceName
     * @param marketName
     * @return marketId
     */
    public static UUID unifiedMarketId(String provinceName,String marketName){
        UUID marketId = null;
        if(marNameIdMap.get(marketName) != null &&
                MarketbelongtoProvince.GetProvice(marketName).equals(provinceName) ){
            marketId = marNameIdMap.get(marketName);
        }else{
            marketId = UUID.randomUUID();
            marNameIdMap.put(marketName,marketId);
        }
        return marketId;
    }
}
