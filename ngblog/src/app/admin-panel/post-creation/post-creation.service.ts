import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Post} from '../model/post';


@Injectable()
export class PostCreationService {
  private savePostUrl = 'http://localhost:8080/posts';

  constructor(private http: HttpClient) {
  }

  savePost(post: Post) {
    const observer = {
      error() {
        console.log('Error during adding post.');
      },
      complete() {
        console.log('Post added successfully.');
      }
    };

    this.http.post<any>(this.savePostUrl, post).subscribe(observer);
  }
}
