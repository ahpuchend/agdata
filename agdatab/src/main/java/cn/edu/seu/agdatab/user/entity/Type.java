package cn.edu.seu.agdatab.user.entity;

import java.util.ArrayList;
import java.util.List;

public class Type {
    String typeName;
    List<String> varieties =new ArrayList<String>();

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setVarieties(List<String> varieties) {
        this.varieties = varieties;
    }

    public String getTypeName() {
        return typeName;
    }

    public List<String> getVarieties() {
        return varieties;
    }
}
