import { Component, OnInit } from '@angular/core';
import { CommonModule }      from '@angular/common';
import { Router }            from '@angular/router';
import { NewsService, Article } from '../../services/news.service';
import { NewsCardComponent }    from '../news-card/news-card.component';

@Component({
  selector: 'app-news-list',
  standalone: true,
  imports: [CommonModule, NewsCardComponent],
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.css']
})
export class NewsListComponent implements OnInit {
  articles: Article[] = [];
  loading = true;

  constructor(
    private newsService: NewsService,
    private router: Router
  ) {}

  ngOnInit() {
    // 1) Retrieve topics from localStorage
    const saved = localStorage.getItem('userTopics');
    const topics: string[] = saved ? JSON.parse(saved) : [];
    if (!topics.length) {
      // 2) If none, go back to interests
      this.router.navigate(['/interests']);
      return;
    }

    // 3) Fetch news and update state
    this.newsService.getNews(topics).subscribe({
      next: resp => {
        this.articles = resp.articles;
        this.loading = false;
      },
      error: err => {
        console.error('News fetch failed', err);
        this.loading = false;
      }
    });
  }
}
// This component fetches and displays news articles based on user-selected topics.
// It retrieves the topics from localStorage, fetches the news using the NewsService,
// and updates the component state with the retrieved articles.