import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit(): void {
    if (!this.email || !this.password) {
      this.errorMessage = 'Please enter both email and password.';
      return;
    }

    this.authService.login(this.email, this.password).subscribe({
      next: () => {
        this.router.navigate(['/select']);
      },
      error: () => {
        this.errorMessage = 'Invalid email or password.';
      },
    });
  }
}
// This component handles user login. It uses Angular's FormsModule for two-way data binding.
// The `onSubmit` method validates the input fields and calls the `login` method from the `AuthService`.