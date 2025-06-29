package com.newsapp.backend.controller;

import com.newsapp.backend.model.Bookmark;
import com.newsapp.backend.service.BookmarkService;
import com.newsapp.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://personalized-news-o1rrtvjou-andresc1425s-projects.vercel.app", allowCredentials = "true")
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<?> addBookmark(@RequestBody Bookmark bookmark, @RequestHeader("Authorization") String token) {
        String username = JwtUtil.extractUsername(token.substring(7));
        bookmarkService.addBookmark(username, bookmark);
        return ResponseEntity.ok("Bookmark added");
    }

    @GetMapping
    public List<Bookmark> getBookmarks(@RequestHeader("Authorization") String token) {
        String username = JwtUtil.extractUsername(token.substring(7));
        return bookmarkService.getBookmarks(username);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBookmark(@RequestParam("articleId") String articleId, @RequestHeader("Authorization") String token) {
        String username = JwtUtil.extractUsername(token.substring(7));
        bookmarkService.deleteBookmark(username, articleId);
        return ResponseEntity.ok("Bookmark removed");
    }
}

