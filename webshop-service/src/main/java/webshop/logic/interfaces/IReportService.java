package webshop.logic.interfaces;

import webshop.service.models.Report;

import java.util.List;

public interface IReportService {
    public Report getReportById(int id)throws Exception;
    public List<Report> getAllReportsForRetailer(int id)throws Exception;
    public Report reportReview(Report report)throws Exception;
    public void removeReport(int id)throws Exception;
    public void dismissReport(int id)throws Exception;
}
