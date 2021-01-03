package webshop.service.models;

import javax.persistence.*;

@Entity
@Table(name = "reports")
public class Report {

    public Report(){}

    public Report(Review review, Retailer retailer) {
        this.review = review;
        this.retailer = retailer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "review_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "report_review_id"))
    private Review review;

    @OneToOne
    @JoinColumn(name = "retailer_id",
            referencedColumnName = "id",
            foreignKey=@ForeignKey(name = "report_retailer_id"))
    private Retailer retailer;

    public int getId() {return id; }

    public void setId(int id) {this.id = id; }

    public Review getReview() {return review; }

    public void setReview(Review review) {this.review = review; }

    public Retailer getRetailer() {return retailer; }

    public void setRetailer(Retailer retailer) {this.retailer = retailer; }
}
