package entity;
public class Price {
    private double maxPrice;
    private double minPrice;
    private double avgPrice;

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getAvgPrice() {
        return avgPrice;
    }
}
