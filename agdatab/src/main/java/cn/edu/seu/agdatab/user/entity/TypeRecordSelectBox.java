package cn.edu.seu.agdatab.user.entity;

import java.util.ArrayList;
import java.util.List;

public class TypeRecordSelectBox {
    String marketname;
    List<String> listtype = new ArrayList<String>();

    public void setMarketname(String marketname) {
        this.marketname = marketname;
    }
    public void setListtype(List<String> listtype) {
        this.listtype = listtype;
    }

    public String getMarketname() {
        return marketname;
    }

    public List<String> getListtype() {
        return listtype;
    }
}
