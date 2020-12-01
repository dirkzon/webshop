package webshop.service.models;

public class BrowseVars {

    public BrowseVars(){}

    public BrowseVars(int minPrice, int maxPrice, String query, double targetRating) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.query = query;
        this.targetRating = targetRating;
    }

    public int minPrice;
    public int maxPrice;
    public String query;
    public double targetRating;

    public boolean isValid(){
        return (minPrice >= 0 &&
                maxPrice >= 0 &&
                minPrice < maxPrice &&
                targetRating >= 0 &&
                targetRating < 5 &&
                !query.isEmpty());
    }
}
