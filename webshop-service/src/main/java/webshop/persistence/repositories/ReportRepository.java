package webshop.persistence.repositories;

import webshop.persistence.interfaces.IReportRepository;
import webshop.service.models.Report;
import webshop.service.models.Retailer;
import webshop.service.models.Review;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ReportRepository implements IReportRepository {

    private final EntityManagerFactory emf;

    @Inject
    public ReportRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Report getReportById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Report.class, id);
        }finally{
            em.close();
        }
    }

    @Override
    public List<Report> getReportsByRetailer(int id){
        EntityManager em = emf.createEntityManager();
        try{
            String sql = "SELECT * FROM Reports WHERE retailer_id = :id";
            Query query = em.createNativeQuery(sql, Report.class);
            query.setParameter("id", id);
            return query.getResultList();
        }finally{
            em.close();
        }
    }

    @Override
    public Report reportReview(Report report){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Retailer retailer = em.find(Retailer.class, report.getRetailer().getId());
            Review review = em.find(Review.class, report.getReview().getId());
            Report newReport = new Report(review, retailer);
            em.persist(newReport);
            em.getTransaction().commit();
            return report;
        }finally {
            em.close();
        }
    }

    @Override
    public void removeReview(Report report){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("SELECT * FROM Reports WHERE review_id = :id", Report.class);
            query.setParameter("id", report.getReview().getId());
            List<Report> reports = query.getResultList();
            for(Report reportToRemove : reports){
                reportToRemove.setRetailer(null);
                reportToRemove.getReview().setProduct(null);
                reportToRemove.getReview().setCustomer(null);
                em.remove(em.contains(reportToRemove) ? reportToRemove : em.merge(reportToRemove));
            }
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Override
    public void dismissReport(Report report){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("SELECT * FROM Reports WHERE review_id = :id", Report.class);
            query.setParameter("id", report.getReview().getId());
            List<Report> reports = query.getResultList();
            for(Report reportToRemove : reports) {
                reportToRemove.setRetailer(null);
                reportToRemove.setReview(null);
                em.remove(em.contains(reportToRemove) ? reportToRemove : em.merge(reportToRemove));
            }
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
}
