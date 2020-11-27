package webshop.service.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    private String id;

    @Column(name = "name")
    private String name;


    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "product_category_id"))
    private Category category;

    @Column(name = "description")
    private String description;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImage> images;

    @Column(name = "created")
    private LocalDate created;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "retailer_id", foreignKey = @ForeignKey(name = "product_retailer_id"))
    private Retailer retailer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductReview> reviews;

    @Column(name = "rating")
    private Double rating;

    @Transient
    private boolean reviewed;

    @Transient
    private boolean canEdit;

    @Column(name = "url")
    private String url;

    @Transient
    private List<ProductProperty> productProperties;

    public String getName(){return name;}
    public String getId(){return id;}
    public Double getPrice(){return price;}
    public Category getCategory(){return category;}
    public String getDescription(){return description;}
    public List<ProductImage> getImages(){return images;}
    public LocalDate getCreated(){return created;}
    public Retailer getRetailer(){return retailer;}
    public List<ProductReview> getReviews(){return reviews;}
    public Double getRating(){return rating;}
    public Boolean getReviewed(){return reviewed;}
    public String getUrl(){return url;}
    public List<ProductProperty> getProductProperties(){return productProperties;}
    public boolean canEdit(){return canEdit;}

    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }
    public void setPrice(Double price) { this.price = price; }
    public void setCategory(Category category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setImages(List<ProductImage> images) { this.images = images; }
    public void setCreated(LocalDate created) { this.created = created; }
    public void setRetailer(Retailer retailer) { this.retailer = retailer; }
    public void setReviews(List<ProductReview> reviews) { this.reviews = reviews; }
    public void setRating(Double rating) { this.rating = rating; }
    public void setReviewed(boolean reviewed) { this.reviewed = reviewed; }
    public void setUrl(String url) { this.url = url; }
    public void setProductProperties(List<ProductProperty> productProperties) { this.productProperties = productProperties; }
    public void setCanEdit(boolean canEdit){this.canEdit = canEdit;}
}
