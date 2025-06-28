import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class BookmarkService {
  private apiUrl = 'http://localhost:8080/api/bookmarks';

  constructor(private http: HttpClient) { }

  getBookmarks(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  addBookmark(article: any): Observable<any> {
    return this.http.post(this.apiUrl, {
      articleId: article.url,
      title: article.title,
      url: article.url,
      description: article.description,
      urlToImage: article.urlToImage
    }, { responseType: 'text' });
  }

  deleteBookmark(articleId: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}?articleId=${encodeURIComponent(articleId)}`, { responseType: 'text' });
  }
}
// This service manages bookmarks for articles. It provides methods to get, add, and remove bookmarks.
// It communicates with the backend API to perform these operations and returns observables for handling the responses.