import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PostIn} from '../model/postIn';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class PostInService {
  private savePostUrl = 'http://localhost:8080/api/posts';

  constructor(private http: HttpClient) {
  }

  savePost(postIn: PostIn): Observable<any> {
    return this.http.post(this.savePostUrl, postIn);
  }
}
