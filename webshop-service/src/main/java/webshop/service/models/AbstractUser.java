package webshop.service.models;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
abstract public class AbstractUser {

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", foreignKey = @ForeignKey(name = "user_image_id"))
    private Image avatar;

    @Column(name = "email")
    private String EMail;

    @Column(name = "joined")
    private LocalDate joined;

    public String getName(){return name;}
    public AbstractImage getAvatar(){return avatar;}
    public String getEMail(){return EMail;}
    public LocalDate getJoined(){return joined;}

    public void setName(String name) { this.name = name; }
    public void setAvatar(Image avatar) { this.avatar = avatar; }
    public void setEMail(String EMail) { this.EMail = EMail; }
    public void setJoined(LocalDate joined) { this.joined = joined; }
}
