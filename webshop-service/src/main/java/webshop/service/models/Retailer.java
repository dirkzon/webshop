package webshop.service.models;

import javax.persistence.*;

@Entity
@Table(name = "retailers")
public class Retailer extends AbstractUser {

    @Id
    @Column(name = "retailer_id")
    private String id;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "url")
    private String url;

    public String getId() { return id; }
    public Double getRating() { return rating; }
    public String getUrl() { return url; }

    public void setId(String id) { this.id = id; }
    public void setRating(Double rating) { this.rating = rating; }
    public void setUrl(String url) { this.url = url; }
}
