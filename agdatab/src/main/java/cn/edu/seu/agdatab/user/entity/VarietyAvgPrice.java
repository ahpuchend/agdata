package cn.edu.seu.agdatab.user.entity;

import java.util.ArrayList;
import java.util.List;

public class VarietyAvgPrice {
    String variety;
    List<Double> avgPriceList = new ArrayList<Double>();
    List<String> productDataList = new ArrayList<String>();

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public void setAvgPriceList(Double avgPrice) {
        this.avgPriceList.add(avgPrice);
    }

    public void setProductDataList(String productData) {
        this.productDataList.add(productData);
    }

    public String getVariety() {
        return variety;
    }

    public List<Double> getAvgPriceList() {
        return avgPriceList;
    }

    public List<String> getProductDataList() {
        return productDataList;
    }
}
