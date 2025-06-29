import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private loginUrl = 'https://personalized-news-app.onrender.com/api/auth/login'; // change if needed

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string) {
    return this.http.post<{ token: string }>(this.loginUrl, { email, password }).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  register(email: string, password: string) {
return this.http.post(
'http://localhost:8080/api/auth/register',
{ email, password },
{ responseType: 'text' }
);
}

  // This method registers a new user by sending a POST request to the backend with the username and password.
  // It returns an observable that can be subscribed to for handling the response.
}
// This service handles user authentication. It provides methods to log in, log out, check if the user is logged in, and retrieve the authentication token.
// The `login` method sends a POST request to the backend with the username and password, and if successful, stores the token in localStorage.
