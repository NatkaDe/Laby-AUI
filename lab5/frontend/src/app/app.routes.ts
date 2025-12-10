import { Routes } from '@angular/router';
import {AuthorListComponent} from './components/author-list.component/author-list.component';
import { AuthorFormComponent } from './components/author-form.component/author-form.component';
import { AuthorDetailsComponent } from './components/author-details.component/author-details.component';
import { PaintingFormComponent } from './components/painting-form.component/painting-form.component';
import { PaintingDetailsComponent } from './components/painting-details.component/painting-details.component';


export const routes: Routes = [
  { path: 'authors', component: AuthorListComponent }, // zad 1
  { path: 'authors/add', component: AuthorFormComponent }, // zad 2
  { path: 'authors/edit/:id', component: AuthorFormComponent }, // zad 3
  { path: 'authors/:id', component: AuthorDetailsComponent }, // zad 4
  { path: 'authors/:authorId/paintings/add', component: PaintingFormComponent },
  { path: 'authors/:authorId/paintings/:paintingId/edit', component: PaintingFormComponent },
  { path: 'authors/:authorId/paintings/:paintingId', component: PaintingDetailsComponent },

  { path: '', redirectTo: 'authors', pathMatch: 'full' }
];

