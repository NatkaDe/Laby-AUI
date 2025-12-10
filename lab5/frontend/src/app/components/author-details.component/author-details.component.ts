import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { AuthorService } from '../../services/author.service';
import { PaintingService } from '../../services/painting.service';
import { Author } from '../../models/author';
import { Painting } from '../../models/painting';

@Component({
  selector: 'app-author-details',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './author-details.component.html',
  styleUrl: './author-details.component.css'
})
export class AuthorDetailsComponent implements OnInit {

  author: Author | undefined;
  paintings: Painting[] = [];
  authorId: number = 0;

  constructor(
    private route: ActivatedRoute,
    private authorService: AuthorService,
    private paintingService: PaintingService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.authorId = Number(idParam);

    if (this.authorId) {
      this.loadData();
    }
  }

  loadData(): void {
    this.authorService.getAuthor(this.authorId).subscribe({
      next: (data) => {
        this.author = data;
        this.cdr.detectChanges();
      },
      error: () => {}
    });

    this.paintingService.getPaintingsByAuthor(this.authorId).subscribe({
      next: (data) => {
        this.paintings = data;
        this.cdr.detectChanges();
      },
      error: () => {}
    });
  }

  deletePainting(paintingId: string): void {
    if(!confirm('Czy na pewno chcesz usunąć ten obraz?')) return;

    this.paintingService.deletePainting(this.authorId, paintingId).subscribe({
      next: () => {
        this.loadData();
      },
      error: (err) => {
        alert('Nie udało się usunąć obrazu.');
      }
    });
  }
}
