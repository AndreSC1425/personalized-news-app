import { Routes } from '@angular/router';
import { SelectComponent } from './interests/select/select.component';
import { NewsListComponent } from './feed/news-list/news-list.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { authGuard } from './auth/auth.guard';
import { BookmarksComponent } from './bookmarks/bookmarks.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'select', component: SelectComponent, canActivate: [authGuard] },
  { path: 'feed', component: NewsListComponent, canActivate: [authGuard] },
  { path: 'bookmarks', component: BookmarksComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: '/login', pathMatch: 'full' }
];
// This file defines the routes for the application.
// It includes routes for login, registration, interest selection, news feed, bookmarks, and a catch-all for not found pages.