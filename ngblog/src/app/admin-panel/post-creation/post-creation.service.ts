import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Post} from '../model/post';
import {MessageService} from 'primeng/components/common/messageservice';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class PostCreationService {
  private savePostUrl = 'http://localhost:8080/posts';

  constructor(private http: HttpClient,
              private messageService: MessageService,
              private router: Router) {
  }

  savePost(post: Post): Observable<any> {
    const currentUser = localStorage.getItem('currentUser');
    if (!currentUser) {
      this.router.navigate(['/adminPanel/login']);
      this.messageService.add({severity: 'warn', summary: 'Please login first...'});
    } else {
      const httpOptions = {
        headers: new HttpHeaders({'Authorization': currentUser})
      };

      return this.http.post(this.savePostUrl, post, httpOptions);
    }
  }
}
