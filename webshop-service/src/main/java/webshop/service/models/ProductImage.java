package webshop.service.models;

import com.google.gson.annotations.JsonAdapter;
import webshop.service.gsonExclusionStrategies.ExcludeProduct;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage extends AbstractImage {

    @JsonAdapter(ExcludeProduct.class)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "image_product_id"))
    private Product product;

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }
}
