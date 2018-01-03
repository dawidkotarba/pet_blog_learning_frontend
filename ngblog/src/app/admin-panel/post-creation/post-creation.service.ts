import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Post} from '../model/post';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class PostCreationService {
  private savePostUrl = 'http://localhost:8080/posts';

  constructor(private http: HttpClient) {
  }

  savePost(post: Post): Observable<any> {
    return this.http.post(this.savePostUrl, post);
  }
}
