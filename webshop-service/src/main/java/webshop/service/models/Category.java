package webshop.service.models;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "category_id")
    private String id;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", foreignKey = @ForeignKey(name = "category_image_id"))
    private Image image;

    @Column(name = "url")
    private String url;

    public String getId(){return id;}
    public String getName(){return name;}
    public AbstractImage getImage(){return image;}
    public String getUrl(){return url;}

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setImage(Image image) { this.image = image; }
    public void setUrl(String url) { this.url = url; }
}
