import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Painting } from '../models/painting';

@Injectable({
  providedIn: 'root'
})
export class PaintingService {

  //private baseUrl = 'http://localhost:8091/authors';
  //private baseUrl = 'http://gateway:8091/authors';
  private baseUrl: string = '/api/authors';
  constructor(private http: HttpClient) { }

  getPaintingsByAuthor(authorId: number): Observable<Painting[]> {
    return this.http.get<Painting[]>(`${this.baseUrl}/${authorId}/paintings`);
  }

  getPainting(authorId: number, paintingId: string): Observable<Painting> {
    return this.http.get<Painting>(`${this.baseUrl}/${authorId}/paintings/${paintingId}`);
  }

  createPainting(authorId: number, painting: Painting): Observable<Painting> {
    return this.http.post<Painting>(`${this.baseUrl}/${authorId}/paintings`, painting);
  }

  updatePainting(authorId: number, paintingId: string, painting: Painting): Observable<Painting> {
    return this.http.put<Painting>(`${this.baseUrl}/${authorId}/paintings/${paintingId}`, painting);
  }

  deletePainting(authorId: number, paintingId: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${authorId}/paintings/${paintingId}`);
  }
}
