import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Painting } from '../models/painting';

@Injectable({
  providedIn: 'root'
})
export class PaintingService {

  // BAZOWY ADRES TO AUTORZY (bo tam są obrazy)
  private baseUrl = 'http://localhost:8091/authors';

  constructor(private http: HttpClient) { }

  // 1. POBIERZ LISTĘ (To jest ten kluczowy moment!)
  // Wynik: http://localhost:8091/authors/123/paintings
  getPaintingsByAuthor(authorId: number): Observable<Painting[]> {
    return this.http.get<Painting[]>(`${this.baseUrl}/${authorId}/paintings`);
  }

  // 2. POBIERZ JEDEN OBRAZ
  // Wynik: http://localhost:8091/authors/123/paintings/55
  getPainting(authorId: number, paintingId: string): Observable<Painting> {
    return this.http.get<Painting>(`${this.baseUrl}/${authorId}/paintings/${paintingId}`);
  }

  // 3. DODAJ OBRAZ
  createPainting(authorId: number, painting: Painting): Observable<Painting> {
    return this.http.post<Painting>(`${this.baseUrl}/${authorId}/paintings`, painting);
  }

  // 4. EDYTUJ OBRAZ
  updatePainting(authorId: number, paintingId: string, painting: Painting): Observable<Painting> {
    return this.http.put<Painting>(`${this.baseUrl}/${authorId}/paintings/${paintingId}`, painting);
  }

  // 5. USUŃ OBRAZ
  deletePainting(authorId: number, paintingId: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${authorId}/paintings/${paintingId}`);
  }
}
