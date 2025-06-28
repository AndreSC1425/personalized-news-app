import { Component, OnInit } from '@angular/core';
import { BookmarkService } from '../services/bookmark.service';
import { Article } from '../services/news.service';
import { NewsCardComponent } from '../feed/news-card/news-card.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-bookmarks',
  standalone: true,
  imports: [NewsCardComponent, CommonModule],
  templateUrl: './bookmarks.component.html',
  styleUrl: './bookmarks.component.css'
})
export class BookmarksComponent implements OnInit {
  bookmarks: Article[] = [];

  constructor(private bookmarkService: BookmarkService) {}

  ngOnInit(): void {
    this.bookmarkService.getBookmarks().subscribe({
      next: (data) => (this.bookmarks = data),
      error: (err) => console.error('Error loading bookmarks:', err)
    });
  }

  removeBookmark(article: any): void {
    this.bookmarkService.deleteBookmark(article.articleId).subscribe(() => {
      this.bookmarks = this.bookmarks.filter(a => a.articleId !== article.articleId);
    });
  }
}
// This component manages the user's bookmarked articles.