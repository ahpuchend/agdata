package cn.edu.seu.agdatab.user.entity;

public class VarAvgDate {
    String variety;
    Double avgPrice;
    String productDate;

    public String getVariety() {
        return variety;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }
}
