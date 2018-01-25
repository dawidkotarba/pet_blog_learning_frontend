import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {of} from 'rxjs/observable/of';
import {Observable} from 'rxjs/Observable';

import {Post} from '../model/post';

@Injectable()
export class PostDetailsService {

  constructor(private http: HttpClient) {
  }

  getPost(id: number): Observable<Post> {
    return this.http.get<Post>('http://localhost:8080/api/posts/' + id).pipe(
      catchError(this.handleError(null))
    );
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

}
