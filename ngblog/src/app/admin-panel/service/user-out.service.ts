import {Injectable} from '@angular/core';
import {of} from 'rxjs/observable/of';
import {Observable} from 'rxjs/Observable';
import {catchError} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {UserOut} from '../model/userOut';

@Injectable()
export class UserOutService {

  allUsersUrl = 'http://localhost:8080/api/users/all';

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<UserOut[]> {
    return this.http.get<UserOut[]>(this.allUsersUrl).pipe(
      catchError(this.handleError('getUsers', []))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

}
