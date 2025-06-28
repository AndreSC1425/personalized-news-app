import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Article } from '../../services/news.service';
import { BookmarkService } from '../../services/bookmark.service';

@Component({
  selector: 'app-news-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './news-card.component.html',
  styleUrl: './news-card.component.css'
})
export class NewsCardComponent {
  @Input() article!: Article;
  @Input() showRemoveButton: boolean = false;
  @Output() remove = new EventEmitter<Article>();

  constructor(private bookmarkService: BookmarkService) { }

  bookmarkArticle(): void {
    this.bookmarkService.addBookmark(this.article).subscribe({
      next: () => alert('Article bookmarked!'),
      error: () => alert('Failed to bookmark article.')
    });
  }

  handleRemove(): void {
    this.remove.emit(this.article);
  }
}
// This component represents a news card that displays an article.