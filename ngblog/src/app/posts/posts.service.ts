import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {of} from 'rxjs/observable/of';
import {Pagination} from '../model/pagination';

@Injectable()
export class PostsService {

  paginatedPostsUrl = 'http://localhost:8080/api/posts?';

  constructor(private http: HttpClient) {
  }

  getPaginatedPosts(page?: number, pageSize?: number): Observable<Pagination> {
    return this.http.get<Pagination>(this.paginatedPostsUrl + 'page=' + page + '&size=' + pageSize);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
