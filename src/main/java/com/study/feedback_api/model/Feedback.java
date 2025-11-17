package com.study.feedback_api.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "feedback")
@EntityListeners(AuditingEntityListener.class)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ContactType contactType;

    @Column(nullable = false, length = 1000)
    private String message;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Feedback() {}

    /**
     * Use this constructor to create a new Feedback instance
     * @param name user's name (optional)
     * @param email user's email (optional)
     * @param contactType contact type (mandatory)
     * @param message feedback message (mandatory)
     */
    public Feedback(String name, String email, ContactType contactType, String message) {
        this.name = name;
        this.email = email;
        this.contactType = contactType;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
