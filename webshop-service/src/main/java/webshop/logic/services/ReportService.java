package webshop.logic.services;

import webshop.logic.interfaces.IReportService;
import webshop.persistence.interfaces.IReportRepository;
import webshop.service.models.Report;

import javax.inject.Inject;
import java.util.List;

public class ReportService implements IReportService {

    private final IReportRepository repository;

    @Inject
    public ReportService(IReportRepository repository){
        this.repository = repository;
    }

    @Override
    public Report getReportById(int id)throws Exception{
        try{
            if(id < 0) throw new Exception("Invalid id");
            Report report = repository.getReportById(id);
            report.getReview().getCustomer().setReviews(null);
            report.getReview().getProduct().setReviews(null);
            return report;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Report> getAllReportsForRetailer(int id)throws Exception{
        if(id < 0) throw new Exception("Id not valid");
        try{
            List<Report> reports = repository.getReportsByRetailer(id);
            for(Report report : reports){
                report.getReview().getCustomer().setReviews(null);
                report.getReview().getProduct().setReviews(null);
            }
            return reports;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Report reportReview(Report report)throws Exception{
        try{
            Report newReport = repository.reportReview(report);
            newReport.getReview().getCustomer().setReviews(null);
            newReport.getReview().getProduct().setReviews(null);
            return newReport;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void removeReport(int id)throws Exception{
        if(id < 0) throw new Exception("Id not valid");
        try{
            Report report = repository.getReportById(id);
            repository.removeReview(report);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void dismissReport(int id)throws Exception{
        if(id < 0) throw new Exception("Id not valid");
        try{
            Report report = repository.getReportById(id);
            repository.dismissReport(report);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
