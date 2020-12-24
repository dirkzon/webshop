package webshop.service.models;

public class BrowseVars {

    public BrowseVars(){}

    public BrowseVars(int minPrice, int maxPrice, String query, double minRating) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.query = query;
        this.minRating = minRating;
    }

    public int minPrice;
    public int maxPrice;
    public String query;
    public double minRating;

    public boolean isValid(){
        if (minPrice >= 0 && maxPrice >= 0){
            if(minPrice == maxPrice){
                return true;
            }else{
                return minPrice < maxPrice;
            }
        }
        return false;
    }
}
