package cn.edu.seu.agdatab.user.entity;

public class DateAvg {
    String productDate;
    Double avgPrice;

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getProductDate() {
        return productDate;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }
}
