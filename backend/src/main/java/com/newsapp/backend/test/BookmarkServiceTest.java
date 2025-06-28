package com.newsapp.backend.test;

@SpringBootTest
@AutoConfigureTestDatabase
public class BookmarkServiceTest {

    @Autowired private BookmarkService bookmarkService;
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

        List<Bookmark> bookmarks = bookmarkService.getBookmarksForUser(email);
        assertEquals(1, bookmarks.size());
        assertEquals("Sample", bookmarks.get(0).getTitle());
    }
}
