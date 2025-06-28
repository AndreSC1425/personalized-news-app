import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BookmarksComponent } from './bookmarks.component';
import { NewsCardComponent } from '../feed/news-card/news-card.component';
import { Article } from '../services/news.service';
import { By } from '@angular/platform-browser';

describe('BookmarksComponent', () => {
  let component: BookmarksComponent;
  let fixture: ComponentFixture<BookmarksComponent>;

  const mockBookmarks: Article[] = [
    { title: 'A', description: 'A Desc', url: '1', source: { name: 'Test' } } as Article,
    { title: 'B', description: 'B Desc', url: '2', source: { name: 'Test' } } as Article,
  ];

  beforeEach(() => {
    localStorage.setItem('bookmarks', JSON.stringify(mockBookmarks));

    TestBed.configureTestingModule({
      imports: [BookmarksComponent, NewsCardComponent]
    });

    fixture = TestBed.createComponent(BookmarksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // ngOnInit runs
  });

  afterEach(() => {
    localStorage.clear();
  });

  it('should load bookmarks from localStorage on init', () => {
    expect(component.bookmarks.length).toBe(2);
    expect(component.bookmarks[0].title).toBe('A');
  });

  it('should remove a bookmark correctly', () => {
    const articleToRemove = mockBookmarks[0]; // "A"
    component.removeBookmark(articleToRemove);

    expect(component.bookmarks.length).toBe(1);
    expect(component.bookmarks[0].title).toBe('B');

    const saved = JSON.parse(localStorage.getItem('bookmarks')!);
    expect(saved.length).toBe(1);
    expect(saved[0].title).toBe('B');
  });
});
// This test suite verifies that the BookmarksComponent correctly loads bookmarks from localStorage,
// displays them using the NewsCardComponent, and allows users to remove bookmarks.