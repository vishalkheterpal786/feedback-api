package com.study.feedback_api.repository;

import com.study.feedback_api.model.Feedback;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FeedbackDao {

    @PersistenceContext
    private EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(FeedbackDao.class);
    public Feedback save(Feedback feedback) {
        logger.info("Adding new feedback for {} ({})", feedback.getContactType(), feedback.getMessage());
        em.persist(feedback);
        return feedback;
    }

    public List<Feedback> findAll() {
        List<Feedback> feedbacks = em.createQuery("SELECT f FROM Feedback f", Feedback.class)
                .getResultList();
        logger.info("Feedback list size :{}", feedbacks.size());
        return feedbacks;
    }

    public List<Feedback> findAllSorted(String direction) {
        logger.info("Fetching feedback sorted by date: {}", direction);
        String order = direction.equalsIgnoreCase("desc") ? "DESC" : "ASC";
        return em.createQuery(
                "SELECT f FROM Feedback f ORDER BY f.createdAt " + order,
                Feedback.class
        ).getResultList();
    }

}
