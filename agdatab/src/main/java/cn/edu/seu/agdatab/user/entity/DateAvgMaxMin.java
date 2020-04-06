package cn.edu.seu.agdatab.user.entity;

public class DateAvgMaxMin {
    String productDate;
    Double avgPrice;
    Double maxPrice;
    Double minPrice;

    public String getProductDate() {
        return productDate;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }
}
