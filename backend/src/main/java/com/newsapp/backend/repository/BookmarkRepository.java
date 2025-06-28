package com.newsapp.backend.repository;

import com.newsapp.backend.model.Bookmark;
import com.newsapp.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser(User user);
    Optional<Bookmark> findByUserAndArticleId(User user, String articleId);
}

