package com.study.feedback_api.repository;

import com.study.feedback_api.model.ContactType;
import com.study.feedback_api.model.Feedback;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class FeedbackDao {

    @PersistenceContext
    private EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(FeedbackDao.class);
    @Transactional
    public Feedback save(Feedback feedback) {
        logger.info("Adding new feedback for {} ({})", feedback.getContactType(), feedback.getMessage());
        em.persist(feedback);
        return feedback;
    }
    @Transactional(readOnly = true)
    public List<Feedback> findAll() {
        List<Feedback> feedbacks = em.createQuery("SELECT f FROM Feedback f", Feedback.class)
                .getResultList();
        logger.info("Feedback list size :{}", feedbacks.size());
        return feedbacks;
    }
    @Transactional(readOnly = true)
    public List<Feedback> findAllSorted(String direction) {
        logger.info("Fetching feedback sorted by date: {}", direction);
        String order = Optional.ofNullable(direction)
                .filter(d -> d.equalsIgnoreCase("desc"))
                .map(d -> "DESC")
                .orElse("ASC");

        return em.createQuery(
                "SELECT f FROM Feedback f ORDER BY f.createdAt " + order,
                Feedback.class
        ).getResultList();
    }
    @Transactional(readOnly = true)
    public List<Feedback> findByContactType(ContactType contactType) {
        logger.info("Fetching feedback with contactType: {}", contactType);
        return em.createQuery(
                        "SELECT f FROM Feedback f WHERE f.contactType = :contactType",
                        Feedback.class
                )
                .setParameter("contactType", contactType)
                .getResultList();
    }
}
