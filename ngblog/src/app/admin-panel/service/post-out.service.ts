import {Injectable} from '@angular/core';
import {of} from 'rxjs/observable/of';
import {Observable} from 'rxjs/Observable';
import {catchError} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {PostOut} from '../model/postOut';

@Injectable()
export class PostOutService {

  allPostsUrl = 'http://localhost:8080/api/posts/all';

  constructor(private http: HttpClient) {
  }

  getPosts(): Observable<PostOut[]> {
    return this.http.get<PostOut[]>(this.allPostsUrl).pipe(
      catchError(this.handleError('getPosts', []))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

}
