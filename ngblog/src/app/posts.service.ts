import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import { of } from 'rxjs/observable/of';
import {catchError, map, tap} from 'rxjs/operators';

import {Post} from './model/post';

@Injectable()
export class PostsService {

  allPostsUrl = 'http://localhost:8080/posts';

  constructor(private http: HttpClient) {
  }

  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.allPostsUrl).pipe(
      catchError(this.handleError('getPosts', []))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
