import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // <--- WAŻNE DO FORMULARZY
import { AuthorService } from '../../services/author.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Author } from '../../models/author';

@Component({
  selector: 'app-author-form',
  standalone: true,
  imports: [CommonModule, FormsModule], // <--- Musi być FormsModule
  templateUrl: './author-form.component.html',
  styleUrl: './author-form.component.css'
})
export class AuthorFormComponent implements OnInit {

  // Pusty obiekt na start
  author: Author = {
    id: 0,
    name: '',
    year_of_birth: 0,
    year_of_death: 0
  };

  isEditMode: boolean = false; // Czy edytujemy, czy dodajemy?

  constructor(
    private authorService: AuthorService,
    public router: Router, // Do przekierowania po zapisie
    private route: ActivatedRoute // Do odczytania ID z paska adresu
  ) {}

  ngOnInit(): void {
    // Sprawdzamy, czy w adresie jest ID (np. /authors/edit/5)
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      // MAMY ID -> TRYB EDYCJI
      this.isEditMode = true;
      this.loadAuthor(Number(id));
    } else {
      // BRAK ID -> TRYB DODAWANIA
      this.isEditMode = false;
    }
  }

  loadAuthor(id: number): void {
    this.authorService.getAuthor(id).subscribe({
      next: (data) => this.author = data,
      error: (err) => console.error('Błąd pobierania autora:', err)
    });
  }

  onSubmit(): void {
    console.log('Zapisuję:', this.author);

    if (this.isEditMode) {
      // AKTUALIZACJA (PUT)
      this.authorService.updateAuthor(this.author.id, this.author).subscribe({
        next: () => this.router.navigate(['/authors']), // Wróć do listy
        error: (err) => alert('Błąd edycji!')
      });
    } else {
      // TWORZENIE (POST)
      this.authorService.createAuthor(this.author).subscribe({
        next: () => this.router.navigate(['/authors']), // Wróć do listy
        error: (err) => alert('Błąd dodawania!')
      });
    }
  }
}
