import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Post} from '../model/post';
import {MessageService} from 'primeng/components/common/messageservice';
import {Router} from '@angular/router';


@Injectable()
export class PostCreationService {
  private savePostUrl = 'http://localhost:8080/posts';

  constructor(private http: HttpClient,
              private messageService: MessageService,
              private router: Router) {
  }

  savePost(post: Post) {
    const currentUser = localStorage.getItem('currentUser');
    if (!currentUser) {
      this.router.navigate(['/login']);
      this.messageService.add({severity: 'warn', summary: 'Please login first...'});
    } else {

      const httpOptions = {
        headers: new HttpHeaders({'Authorization': currentUser})
      };

      this.http.post(this.savePostUrl, post, httpOptions)
        .subscribe(
          undefined,
          () => this.messageService.add({severity: 'error', summary: 'Error during adding post...'}),
          () => this.messageService.add({severity: 'success', summary: 'Post added.'}));
    }
  }
}
