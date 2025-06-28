import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Article } from '../../services/news.service';
import { BookmarkService } from '../../services/bookmark.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-news-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './news-card.component.html',
  styleUrl: './news-card.component.css'
})
export class NewsCardComponent {
  @Input() article!: Article;

  summarizedText: string | null = null;
  loadingSummary = false;
  
  @Input() showRemoveButton: boolean = false;
  @Output() remove = new EventEmitter<Article>();

  constructor(
  private bookmarkService: BookmarkService,
  private http: HttpClient
) {}

  summarizeArticle(): void {
  const textToSend = this.article.description || this.article.title;

  this.loadingSummary = true;
  this.http
    .post('http://localhost:8080/api/ai/summarize', textToSend, { responseType: 'text' })
    .subscribe({
      next: (summary) => {
        this.summarizedText = summary;
        this.loadingSummary = false;
      },
      error: (err) => {
        console.error('AI summary failed:', err);
        this.loadingSummary = false;
      }
    });
}
  // This method sends the article content to the AI service for summarization.

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