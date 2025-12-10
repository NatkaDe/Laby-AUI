import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { RouterLink } from '@angular/router'; // Import RouterLink

@Component({
  selector: 'app-author-list',
  standalone: true,
  // WAŻNE: RouterLink musi być TUTAJ, wewnątrz tablicy imports
  imports: [CommonModule, RouterLink],
  templateUrl: './author-list.component.html',
  styleUrl: './author-list.component.css'
})
export class AuthorListComponent implements OnInit {

  authors: any[] = [];
  // Ujednoliciłem URL (bez ukośnika na końcu, żeby łatwiej sklejać)
  private apiUrl: string = 'http://localhost:8091/authors';

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.getAuthors();
  }

  getAuthors(): void {
    // Używamy zmiennej this.apiUrl zamiast wpisywać adres ręcznie
    this.http.get<any[]>(this.apiUrl).subscribe({
      next: (data) => {
        if (Array.isArray(data)) {
          this.authors = data;
        } else if ((data as any).content) {
          this.authors = (data as any).content;
        } else {
          this.authors = data;
        }
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('error:', err);
      }
    });
  }

  deleteAuthor(id: number): void {
    if(!confirm('Usunąć ID: ' + id + '?')) return;

    // Używamy this.apiUrl + id
    this.http.delete(`${this.apiUrl}/${id}`).subscribe({
      next: () => {
        this.getAuthors();
      },
      error: (err) => alert('error')
    });
  }
}
