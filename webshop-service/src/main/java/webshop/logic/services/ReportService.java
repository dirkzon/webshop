package webshop.logic.services;

import webshop.logic.interfaces.IReportService;
import webshop.persistence.interfaces.IReportRepository;
import webshop.service.models.Report;

import javax.inject.Inject;
import java.util.List;

public class ReportService implements IReportService {

    private final IReportRepository repository;

    @Inject
    public ReportService(IReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public Report getReportById(int id) {
        if (id < 0) throw new IllegalArgumentException("Invalid id");
        Report report = repository.getReportById(id);
        report.getReview().getCustomer().setReviews(null);
        report.getReview().getProduct().setReviews(null);
        return report;
    }

    @Override
    public List<Report> getAllReportsForRetailer(int id) {
        if (id < 0) throw new IllegalArgumentException("Id not valid");
        List<Report> reports = repository.getReportsByRetailer(id);
        for (Report report : reports) {
            report.getReview().getCustomer().setReviews(null);
            report.getReview().getProduct().setReviews(null);
            report.getReview().setReports(null);
        }
        return reports;
    }

    @Override
    public Report reportReview(Report report) {
        if (report.getReview().getId() < 0 || report.getRetailer().getId() < 0)
            throw new IllegalArgumentException("Report not valid");
        Report newReport = repository.reportReview(report);
        newReport.getReview().getCustomer().setReviews(null);
        newReport.getReview().getProduct().setReviews(null);
        return newReport;
    }

    @Override
    public void removeReport(int id) {
        if (id < 0) throw new IllegalArgumentException("Id not valid");
        Report report = repository.getReportById(id);
        repository.removeReview(report);
    }

    @Override
    public void dismissReport(int id) {
        if (id < 0) throw new IllegalArgumentException("Id not valid");
        Report report = repository.getReportById(id);
        repository.dismissReport(report);
    }
}
