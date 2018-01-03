import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
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
    return this.http.get<Author[]>(this.authorsStartWithUrl + query);
  }

  getAuthorities(query: string): Observable<Authority[]> {
    return this.http.get<Authority[]>(this.authoritiesStartWithUrl + query);
  }

  getLabels(name: string): Observable<Label[]> {
    return this.http.get<Label[]>(this.labelsSearchByNameUrl + name);
  }
}
