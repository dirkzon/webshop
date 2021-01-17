package webshop.logic.services;

import webshop.logic.interfaces.IReportService;
import webshop.persistence.interfaces.IProductRepository;
import webshop.persistence.interfaces.IReportRepository;
import webshop.service.models.Product;
import webshop.service.models.Report;

import javax.inject.Inject;
import java.util.List;

import static webshop.logic.services.Constants.INVALID_ID;

public class ReportService implements IReportService {

    private final IReportRepository reportRepository;
    private final IProductRepository productRepository;

    @Inject
    public ReportService(IReportRepository reportRepository, IProductRepository productRepository) {
        this.reportRepository = reportRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Report getReportById(int id) {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        Report report = reportRepository.getReportById(id);
        report.getReview().getCustomer().setReviews(null);
        report.getReview().getProduct().setReviews(null);
        return report;
    }

    @Override
    public List<Report> getAllReportsForRetailer(int id) {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        List<Report> reports = reportRepository.getReportsByRetailer(id);
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
        Report newReport = reportRepository.reportReview(report);
        newReport.getReview().getCustomer().setReviews(null);
        newReport.getReview().getProduct().setReviews(null);
        return newReport;
    }

    @Override
    public void removeReport(int id) {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        Report report = reportRepository.getReportById(id);
        Product product = report.getReview().getProduct();
        product.removeReview(report.getReview());
        product.calculateRating();
        productRepository.updateProductById(product.getId(), product);
        reportRepository.removeReview(report);
    }

    @Override
    public void dismissReport(int id) {
        if (id < 0) throw new IllegalArgumentException(INVALID_ID);
        Report report = reportRepository.getReportById(id);
        reportRepository.dismissReport(report);
    }
}
