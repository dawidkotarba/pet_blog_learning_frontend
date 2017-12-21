import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Author} from '../../model/author';

@Injectable()
export class AutocompleteService {
  labelStartWithUrl = 'http://localhost:8080/authors?username=';

  constructor(private http: HttpClient) {
  }

  getAuthors(query): Observable<Author[]> {
    const currentUser = localStorage.getItem('currentUser');
    const httpOptions = {
      headers: new HttpHeaders({'Authorization': currentUser})
    };

    return this.http.get<Author[]>(this.labelStartWithUrl + query, httpOptions);
  }
}
