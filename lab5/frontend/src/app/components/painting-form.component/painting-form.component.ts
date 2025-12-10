import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PaintingService } from '../../services/painting.service';
import { Painting } from '../../models/painting';

@Component({
  selector: 'app-painting-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './painting-form.component.html'
})
export class PaintingFormComponent implements OnInit {

  painting: Painting = { uuid: '', title: '', year: 0, authorId: 0 };

  isEditMode: boolean = false;
  authorId: number = 0;
  paintingId: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private paintingService: PaintingService
  ) {}

  ngOnInit(): void {
    const authorIdParam = this.route.snapshot.paramMap.get('authorId');
    this.authorId = Number(authorIdParam);
    this.painting.authorId = this.authorId;

    const paintingIdParam = this.route.snapshot.paramMap.get('paintingId');

    if (paintingIdParam) {
      this.isEditMode = true;
      this.paintingId = String(paintingIdParam);

      this.paintingService.getPainting(this.authorId, this.paintingId).subscribe({
        next: (data) => {
          this.painting = data;
        },
        error: (err) => console.error('Błąd pobierania danych do edycji:', err)
      });
    } else {

    }
  }

  onSubmit(): void {
    if (this.isEditMode) {
      this.paintingService.updatePainting(this.authorId, this.paintingId, this.painting).subscribe({
        next: () => {
          this.goBack();
        }
      });
    } else {
      const newPainting = { ...this.painting, id: 0 };

      this.paintingService.createPainting(this.authorId, newPainting).subscribe({
        next: () => {
          this.goBack();
        }
      });
    }
  }

  goBack(): void {
    this.router.navigate(['/authors', this.authorId]);
  }
}
