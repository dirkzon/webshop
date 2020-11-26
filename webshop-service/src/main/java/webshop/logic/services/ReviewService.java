package webshop.logic.services;

import webshop.logic.interfaces.IReviewService;
import webshop.persistence.interfaces.IReviewRepository;

import javax.inject.Inject;

public class ReviewService implements IReviewService {

    @Inject
    private IReviewRepository repository;

    @Override
    public void DeleteReviewById(String id) {
        repository.DeleteReviewById(id);
    }
}
