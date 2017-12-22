import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Author} from '../../model/author';
import {Authority} from '../../model/authority';

@Injectable()
export class AutocompleteService {
  authorsStartWithUrl = 'http://localhost:8080/authors?username=';
  authoritiesStartWithUrl = 'http://localhost:8080/authorities?authority=';

  constructor(private http: HttpClient) {
  }

  getAuthors(query): Observable<Author[]> {
    const currentUser = localStorage.getItem('currentUser');
    const httpOptions = {
      headers: new HttpHeaders({'Authorization': currentUser})
    };

    return this.http.get<Author[]>(this.authorsStartWithUrl + query, httpOptions);
  }

  getAuthorities(query): Observable<Authority[]> {
    const currentUser = localStorage.getItem('currentUser');
    const httpOptions = {
      headers: new HttpHeaders({'Authorization': currentUser})
    };

    return this.http.get<Authority[]>(this.authoritiesStartWithUrl + query, httpOptions);
  }
}
