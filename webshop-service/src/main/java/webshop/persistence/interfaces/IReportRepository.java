package webshop.persistence.interfaces;

import webshop.service.models.Report;

import java.util.List;

public interface IReportRepository {
    public Report getReportById(int id);
    public List<Report> getReportsByRetailer(int id);
    public Report reportReview(Report report);
    public void removeReview(Report report);
    public void dismissReport(Report report);
}
