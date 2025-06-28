package com.newsapp.backend.test;

import com.newsapp.backend.model.Bookmark;
import com.newsapp.backend.model.User;
import com.newsapp.backend.repository.BookmarkRepository;
import com.newsapp.backend.repository.UserRepository;
import com.newsapp.backend.service.BookmarkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookmarkServiceTest {

    @Autowired
    private BookmarkService bookmarkService;
    @Autowired private UserRepository userRepository;
    @Autowired private BookmarkRepository bookmarkRepository;

    @BeforeEach
    public void setup() {
        bookmarkRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);
    }

    @Test
    public void testAddAndGetBookmarks() {
        String email = "test@example.com";
        Bookmark b = new Bookmark();
        b.setArticleId("https://sample.com");
        b.setTitle("Sample");
        b.setUrl("https://sample.com");

        bookmarkService.addBookmark(email, b);

        List<Bookmark> bookmarks = bookmarkService.getBookmarks(email);
        assertEquals(1, bookmarks.size());
        assertEquals("Sample", bookmarks.get(0).getTitle());
    }
}
