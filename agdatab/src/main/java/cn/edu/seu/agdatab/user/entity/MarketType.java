package cn.edu.seu.agdatab.user.entity;

import java.util.ArrayList;
import java.util.List;

public class MarketType {
    String marketName;
    List<Type> typeList = new ArrayList<Type>();

    public String getMarketName() {
        return marketName;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }
}
