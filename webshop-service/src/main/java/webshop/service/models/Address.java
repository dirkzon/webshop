package webshop.service.models;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name = "address_id")
    private String id;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private int houseNr;

    @Column(name = "house_number_addition")
    private String houseNrAddition;

    public String getId(){return id;}
    public String getCountry(){return country;}
    public String getPostalCode(){return postalCode;}
    public String getStreet(){return street;}
    public int getHouseNr() {return houseNr;}
    public String getHouseNrAddition() {return houseNrAddition;}

    public void setId(String id) { this.id = id; }
    public void setCountry(String country) { this.country = country; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public void setStreet(String street) { this.street = street; }
    public void setHouseNr(int houseNr) { this.houseNr = houseNr; }
    public void setHouseNrAddition(String houseNrAddition) { this.houseNrAddition = houseNrAddition; }
}
