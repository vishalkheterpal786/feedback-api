package com.study.feedback_api.repository;

import com.study.feedback_api.model.Feedback;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FeedbackDao {

    @PersistenceContext
    private EntityManager em;

    public Feedback save(Feedback feedback) {
        em.persist(feedback);
        return feedback;
    }

    public List<Feedback> findAll() {
        return em.createQuery("SELECT f FROM Feedback f", Feedback.class)
                .getResultList();
    }

    public List<Feedback> findAllSorted(String direction) {
        String order = direction.equalsIgnoreCase("desc") ? "DESC" : "ASC";
        return em.createQuery(
                "SELECT f FROM Feedback f ORDER BY f.createdAt " + order,
                Feedback.class
        ).getResultList();
    }

}
