package webshop.service.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    public Image(){}

    public Image(String url, int height, int width) {
        this.url = url;
        this.height = height;
        this.width = width;
    }

    @Id
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
