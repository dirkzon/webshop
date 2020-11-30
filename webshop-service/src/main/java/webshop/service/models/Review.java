package webshop.service.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review {

    public Review(){}

    public Review(double rating, String body, LocalDate created, Customer customer, Product product) {
        this.rating = rating;
        this.body = body;
        this.created = created;
        this.customer = customer;
        this.product = product;
    }

    @Id
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "rating")
    private double rating;

    @Column(name = "body")
    private String body;

    @Column(name = "created")
    private LocalDate created;

    @ManyToOne
    @JoinColumn(name = "customer_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "review_customer_id"))
    private Customer customer;

    //Field must not get serialized, will create infinite recursion
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "prod_id",
            referencedColumnName = "product_id",
            foreignKey=@ForeignKey(name = "review_product_id"))
    @JsonIgnore private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    @JsonIgnore
    public void setProduct(Product product) {
        this.product = product;
    }
}
