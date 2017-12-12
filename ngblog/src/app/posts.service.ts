import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Post} from './model/post';

@Injectable()
export class PostsService {

  allPostsUrl = 'http://localhost:8080/posts';

  constructor(private http: HttpClient) {
  }

  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.allPostsUrl);
  }
}
