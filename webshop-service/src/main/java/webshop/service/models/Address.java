package webshop.service.models;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    public Address(){}

    public Address(String country, String streetName, int houseNumber) {
        this.country = country;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "country")
    private String country;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "house_number")
    private int houseNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
}
