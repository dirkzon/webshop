package webshop.service.models;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractImage {

    @Id
    @Column(name = "image_id")
    private String id;

    @Column(name = "url")
    private String url;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    public String getId(){return id;}
    public String getUrl(){return url;}
    public int getHeight(){return height;}
    public int getWidth(){return width;}

    public void setId(String id) { this.id = id; }
    public void setUrl(String url) { this.url = url; }
    public void setHeight(int height) { this.height = height; }
    public void setWidth(int width) { this.width = width; }
}
