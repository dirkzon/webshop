package webshop.service.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    public Customer(){}

    public Customer(Account account, Address address, Image avatar) {
        this.account = account;
        this.address = address;
        this.avatar = avatar;
    }

    @Id
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "customer_account_id"))
    private Account account;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "customer_address_id"))
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
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
