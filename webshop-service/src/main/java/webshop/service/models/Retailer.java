package webshop.service.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "retailers")
public class Retailer {

    public Retailer(){}

    public Retailer(int id, Account account, Image avatar) {
        this.id = id;
        this.account = account;
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
            foreignKey=@ForeignKey(name = "retailer_account_id"))
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "retailer_avatar_id"))
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

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }
}
