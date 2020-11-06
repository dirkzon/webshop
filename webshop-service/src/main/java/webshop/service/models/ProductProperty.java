package webshop.service.models;

import javax.persistence.*;

@Entity
@Table(name = "product_properties")
@SecondaryTable(name = "properties")
public class ProductProperty {

    @Id
    @Column(name = "product_property_id")
    private String id;

    @Column(name = "name", table = "properties")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "unit", table = "properties")
    private String unit;

    public String getId(){return id;}
    public String getName(){return name;}
    public String getValue(){return value;}
    public String getUnit(){return unit;}

    public void setId(String id) {this.id = id;}
    public void setName(String name) { this.name = name; }
    public void setValue(String value) { this.value = value; }
    public void setUnit(String unit) { this.unit = unit; }
}
