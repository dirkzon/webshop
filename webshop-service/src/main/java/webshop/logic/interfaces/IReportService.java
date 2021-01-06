package webshop.logic.interfaces;

import webshop.service.models.Report;

import java.util.List;

public interface IReportService {
    Report getReportById(int id)throws IllegalArgumentException;
    List<Report> getAllReportsForRetailer(int id)throws IllegalArgumentException;
    Report reportReview(Report report)throws IllegalArgumentException;
    void removeReport(int id)throws IllegalArgumentException;
    void dismissReport(int id)throws IllegalArgumentException;
}
