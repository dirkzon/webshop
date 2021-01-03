package webshop.service.models;

public class BrowseVars {

    public BrowseVars(){}

    public BrowseVars(int minPrice, int maxPrice, String query, double minRating) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.query = query;
        this.minRating = minRating;
    }

    private int minPrice;
    private int maxPrice;
    private String query;
    private double minRating;

    public boolean isValid(){
        if(minRating < 0)return false;
        if (minPrice >= 0 && maxPrice >= 0 ){
            if(minPrice == maxPrice){
                return true;
            }else{
                return minPrice < maxPrice;
            }
        }
        return false;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }
}
