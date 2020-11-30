package webshop.service.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    public Customer(){}

    public Customer(int id,Account account, Address address, Image avatar) {
        this.id = id;
        this.account = account;
        this.address = address;
        this.avatar = avatar;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval=true)
    @JoinColumn(name = "account_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "customer_account_id"))
    private Account account;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "customer_address_id"))
    private Address address;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval=true)
    @JoinColumn(name = "avatar_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "customer_avatar_id"))
    private Image avatar;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }
}
