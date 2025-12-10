import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Author } from '../models/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private apiUrl = 'http://localhost:8091/authors';

  constructor(private http: HttpClient) { }

  getAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(this.apiUrl);
  }

  deleteAuthor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getAuthor(id: number): Observable<any> {
    return this.http.get<any>(`http://localhost:8091/authors/${id}`);
  }

  createAuthor(author: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, author);
  }

  updateAuthor(id: number, author: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, author);
  }
}
