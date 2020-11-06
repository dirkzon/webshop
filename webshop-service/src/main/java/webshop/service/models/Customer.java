package webshop.service.models;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer extends AbstractUser {

    @Id
    @Column(name = "customer_id")
    private String id;

    @OneToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "customer_address_id"))
    private Address address;

    public String getId(){return id;}
    public Address getAddress(){return address;}

    public void setId(String id){this.id = id;}
    public void setAddress(Address address){this.address = address;}
}
