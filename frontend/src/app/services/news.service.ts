import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Article {
  title: string;
  description: string;
  url: string;
  urlToImage?: string;
  articleId?: string;
}

interface NewsApiResponse {
  articles: Article[];
}

@Injectable({ providedIn: 'root' })
export class NewsService {
  private apiKey = '5e1d4fd9df264397bedf4b8cbeb5a73b'; 
  private baseUrl = 'https://newsapi.org/v2/everything';

  constructor(private http: HttpClient) {}

  /**
   * Fetches articles matching any of the given topics.
   * Combines them into a single query string using OR.
   */
  getNews(topics: string[]): Observable<NewsApiResponse> {
    const query = topics.join(' OR ');
    const url = `${this.baseUrl}?q=${encodeURIComponent(query)}&apiKey=${this.apiKey}`;
    return this.http.get<NewsApiResponse>(url);
  }
}
// This service fetches news articles based on user-selected topics.
// It constructs a query string that combines all selected topics using "OR".