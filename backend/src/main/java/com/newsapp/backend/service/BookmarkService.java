package com.newsapp.backend.service;

import com.newsapp.backend.model.Bookmark;
import com.newsapp.backend.model.User;
import com.newsapp.backend.repository.BookmarkRepository;
import com.newsapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepo;

    @Autowired
    private UserRepository userRepo;

    public void addBookmark(String email, Bookmark bookmark) {
        User user = userRepo.findByEmail(email).orElseThrow();
        Optional<Bookmark> existing = bookmarkRepo.findByUserAndArticleId(user, bookmark.getArticleId());

        if (existing.isEmpty()) {
            bookmark.setUser(user);
            bookmarkRepo.save(bookmark);
        }
    }

    public List<Bookmark> getBookmarks(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        return bookmarkRepo.findByUser(user);
    }

    public void deleteBookmark(String email, String articleId) {
        User user = userRepo.findByEmail(email).orElseThrow();
        bookmarkRepo.findByUserAndArticleId(user, articleId)
                .ifPresent(bookmarkRepo::delete);
    }
}

