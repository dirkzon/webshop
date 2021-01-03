package webshop.persistence.interfaces;

import webshop.service.models.Report;

import java.util.List;

public interface IReportRepository {
    public Report getReportById(int id)throws Exception;
    public List<Report> getReportsByRetailer(int id)throws Exception;
    public Report reportReview(Report report)throws Exception;
    public void removeReview(Report report)throws Exception;
    public void dismissReport(Report report)throws Exception;
}
