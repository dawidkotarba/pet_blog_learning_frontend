import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {of} from 'rxjs/observable/of';
import {Pagination} from '../model/pagination';
import {catchError} from 'rxjs/operators';

@Injectable()
export class PostsService {

  paginatedPostsUrl = 'http://localhost:8080/api/posts?';

  constructor(private http: HttpClient) {
  }

  getPaginatedPosts(page?: number, pageSize?: number): Observable<Pagination> {
    return this.http.get<Pagination>(this.paginatedPostsUrl + 'page=' + page + '&size=' + pageSize).pipe(
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
