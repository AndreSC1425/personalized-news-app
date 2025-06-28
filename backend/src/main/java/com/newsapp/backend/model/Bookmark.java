package com.newsapp.backend.model;

import jakarta.persistence.*;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String articleId; // Unique ID from news source

    @Column(length = 1000)
    private String title;

    @Column(length = 2000)
    private String url;

    @Column(length = 5000)
    private String description;

    @Column(length = 2000)
    private String urlToImage; 

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUrlToImage() { return urlToImage; }
    public void setUrlToImage(String urlToImage) { this.urlToImage = urlToImage; }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

