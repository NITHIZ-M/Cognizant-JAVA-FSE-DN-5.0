package com.cognizant.ormlearn.model;

import jakarta.persistence.*;
import java.math.BigDecimal;   // <-- import
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qu_id")
    private int id;

    @Column(name = "qu_text")
    private String text;

    @Column(name = "qu_score")
    private BigDecimal score;    // changed from Double

    @OneToMany(mappedBy = "question")
    private Set<Options> options;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public BigDecimal getScore() { return score; }      // return BigDecimal
    public void setScore(BigDecimal score) { this.score = score; }
    public Set<Options> getOptions() { return options; }
    public void setOptions(Set<Options> options) { this.options = options; }

    @Override
    public String toString() {
        return "Question{id=" + id + ", text='" + text + "', score=" + score + "}";
    }
}