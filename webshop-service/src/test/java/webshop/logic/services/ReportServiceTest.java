package webshop.logic.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webshop.logic.interfaces.IReportService;
import webshop.persistence.interfaces.IReportRepository;
import webshop.service.models.*;

import javax.ws.rs.core.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportServiceTest {

    @Context
    static IReportRepository repository;
    static IReportService service;

    @BeforeAll
    static void setUpReportServiceMock()throws Exception{
        repository = mock(IReportRepository.class);
        service = new ReportService(repository);

        List<Report> reports = new ArrayList<>();

        Retailer retailer = new Retailer(1,new Account("john", "john@outlook.com","1234", UserRole.RETAILER, LocalDate.parse("2020-05-30")), new Image("testUrl"));
        Customer customer = new Customer(1, new Account("peter", "password","peter@outlook.com",  UserRole.CUSTOMER, LocalDate.parse("2016-08-16")),
                new Address("GB", "Street", 7),
                new Image("testUrl"));
        Review review = new Review(4.5, "review body", LocalDate.parse("2018-10-02"), customer,new Product(1, "tv", 200.0, "description tv", LocalDate.parse("2020-01-08"),retailer,2.5, new Image("testUrl")));
        review.setId(1);

        reports.add(new Report(review, retailer));
        reports.get(0).setId(1);

        when(repository.getReportById(anyInt())).thenReturn(reports.get(0));
        when(repository.getReportsByRetailer(anyInt())).thenReturn(reports);
        when(repository.reportReview(any(Report.class))).thenReturn(reports.get(0));
    }

    @Test
    void getReportByIdShouldSucceed() throws Exception {
        //arrange

        //act
        Report report = service.getReportById(1);
        //assert
        assertEquals(1, report.getId());
    }

    @Test
    void getReportByIdWithNegativeIdShouldNotSucceed(){
        //arrange

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.getReportById(-1));
    }

    @Test
    void getAllReportsForRetailerShouldSucceed() throws Exception{
        //arrange

        //act
        List<Report> reports = service.getAllReportsForRetailer(1);
        //assert
        assertEquals(1, reports.get(0).getId());
    }

    @Test
    void getAllReportsForRetailerWithNegativeIdShouldNotSucceed() {
        //arrange

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.getAllReportsForRetailer(-3));
    }

    @Test
    void reportReviewShouldSucceed()throws Exception {
        //arrange
        Retailer retailer = new Retailer();
        retailer.setId(1);
        Review review = new Review();
        review.setId(1);
        Report report = new Report(review, retailer);
        //act
        Report newReport = service.reportReview(report);
        //assert
        assertEquals(1, newReport.getId());
    }

    @Test
    void reportReviewWithNegativeRetailerIdShouldNotSucceed(){
        //arrange
        Retailer retailer = new Retailer();
        retailer.setId(-4);
        Review review = new Review();
        review.setId(1);
        Report report = new Report(review, retailer);
        //act
        //assert
        assertThrows(Exception.class, () ->
                service.reportReview(report));
    }

    @Test
    void reportReviewWithNegativeReviewIdShouldNotSucceed(){
        //arrange
        Retailer retailer = new Retailer();
        retailer.setId(1);
        Review review = new Review();
        review.setId(-4);
        Report report = new Report(review, retailer);
        //act
        //assert
        assertThrows(Exception.class, () ->
                service.reportReview(report));
    }

    @Test
    void reportReviewWithNegativeReviewIdAndNegativeRetailerIdShouldNotSucceed(){
        //arrange
        Retailer retailer = new Retailer();
        retailer.setId(-2);
        Review review = new Review();
        review.setId(-4);
        Report report = new Report(review, retailer);
        //act
        //assert
        assertThrows(Exception.class, () ->
                service.reportReview(report));
    }

    @Test
    void removeReportWithNegativeIdShouldNotWork() {
        //arrange

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.removeReport(-2));
    }

    @Test
    void dismissReportWithNegativeIdShouldNotSucceed() {
        //arrange

        //act

        //assert
        assertThrows(Exception.class, () ->
                service.dismissReport(-3));
    }
}
