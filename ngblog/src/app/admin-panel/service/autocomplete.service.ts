import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Author} from '../../model/author';
import {Authority} from '../../model/authority';
import {Label} from '../model/label';

@Injectable()
export class AutocompleteService {
  authorsStartWithUrl = 'http://localhost:8080/authors?username=';
  authoritiesStartWithUrl = 'http://localhost:8080/authorities?authority=';
  labelsSearchByNameUrl = 'http://localhost:8080/labels?name=';

  constructor(private http: HttpClient) {
  }

  getAuthors(query: string): Observable<Author[]> {
    const currentUser = localStorage.getItem('currentUser');
    const httpOptions = {
      headers: new HttpHeaders({'Authorization': currentUser})
    };

    return this.http.get<Author[]>(this.authorsStartWithUrl + query, httpOptions);
  }

  getAuthorities(query: string): Observable<Authority[]> {
    const currentUser = localStorage.getItem('currentUser');
    const httpOptions = {
      headers: new HttpHeaders({'Authorization': currentUser})
    };

    return this.http.get<Authority[]>(this.authoritiesStartWithUrl + query, httpOptions);
  }

  getLabels(name: string): Observable<Label[]> {
    const currentUser = localStorage.getItem('currentUser');
    const httpOptions = {
      headers: new HttpHeaders({'Authorization': currentUser})
    };
    return this.http.get<Label[]>(this.labelsSearchByNameUrl + name, httpOptions);

  }
}
