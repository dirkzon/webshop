package webshop.logic.interfaces;

import webshop.service.models.Report;

import java.util.List;

public interface IReportService {
    Report getReportById(int id);
    List<Report> getAllReportsForRetailer(int id);
    Report reportReview(Report report);
    void removeReport(int id);
    void dismissReport(int id);
}
