import { Component, OnInit, ChangeDetectorRef } from '@angular/core'; // <--- 1. IMPORT
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { PaintingService } from '../../services/painting.service';
import { Painting } from '../../models/painting';

@Component({
  selector: 'app-painting-details',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './painting-details.component.html',
  styleUrl: './painting-details.component.css'
})
export class PaintingDetailsComponent implements OnInit {

  painting: Painting | undefined;
  authorId: number = 0;

  constructor(
    private route: ActivatedRoute,
    private paintingService: PaintingService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const authorIdParam = this.route.snapshot.paramMap.get('authorId');
    this.authorId = Number(authorIdParam);

    const paintingId = this.route.snapshot.paramMap.get('paintingId');

    if (paintingId) {
      this.paintingService.getPainting(this.authorId, paintingId).subscribe({
        next: (data) => {
          this.painting = data;
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error('Błąd pobierania:', err);
        }
      });
    }
  }
}
