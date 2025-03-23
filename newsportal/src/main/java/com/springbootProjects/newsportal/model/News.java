package com.springbootProjects.newsportal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String authorName;
    private String content;


    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd, HH:MM")
    private Date datePublished;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public News(){};

    public News(String title, String authorName, Author author, String content) {
        this.title = title;

        this.content = content;
        this.author = author;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "title: " + title +  ", content: " + content;
    }
}