import { Component }    from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router }       from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-select',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent {
  topics = [
    'Technology',
    'Sports',
    'Finance',
    'Health',
    'Entertainment',
    'Science'
  ];

  selectedTopics: string[] = [];

  constructor(private router: Router) {}

  toggleTopic(topic: string, isChecked: boolean): void {
    if (isChecked) {
      if (!this.selectedTopics.includes(topic)) {
        this.selectedTopics.push(topic);
      }
    } else {
      this.selectedTopics = this.selectedTopics.filter(t => t !== topic);
    }
  }

  onSubmit(): void {
    if (this.selectedTopics.length === 0) {
      alert('Please select at least one topic.');
      return;
    }
    localStorage.setItem('userTopics', JSON.stringify(this.selectedTopics));
    this.router.navigate(['/feed']);
  }
}
