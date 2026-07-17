package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import java.math.BigDecimal;   // <-- import
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "attempt")
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "at_us_id")
    private User user;

    @Column(name = "at_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "at_score")
    private BigDecimal score;    // changed from Double

    @OneToMany(mappedBy = "attempt")
    private Set<AttemptQuestion> attemptQuestions;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public BigDecimal getScore() { return score; }      // return BigDecimal
    public void setScore(BigDecimal score) { this.score = score; }
    public Set<AttemptQuestion> getAttemptQuestions() { return attemptQuestions; }
    public void setAttemptQuestions(Set<AttemptQuestion> attemptQuestions) { this.attemptQuestions = attemptQuestions; }

    @Override
    public String toString() {
        return "Attempt{id=" + id + ", date=" + date + ", score=" + score + "}";
    }
}