package webshop.service.models;

import com.google.gson.annotations.JsonAdapter;
import webshop.service.gsonExclusionStrategies.ExcludeProduct;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class ProductReview {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "review_id")
    private String id;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "body")
    private String body;

    @Column(name = "created")
    private LocalDate created;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "review_customer_id"))
    private Customer customer;

    @JsonAdapter(ExcludeProduct.class)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "review_product_id"))
    private Product product;

    public String getId(){return id;}
    public Double getRating(){return rating;}
    public String getBody(){return body;}
    public LocalDate getCreated(){return created;}
    public Customer getCustomer(){return customer;}
    public Product getProduct(){return product;}

    public void setId(String id) { this.id = id; }
    public void setRating(Double rating) {this.rating = rating; }
    public void setBody(String body) { this.body = body; }
    public void setCreated(LocalDate created) { this.created = created; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setProduct(Product product){this.product = product;}
}
