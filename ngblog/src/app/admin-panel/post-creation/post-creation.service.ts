import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Post} from '../model/post';
import {MessageService} from 'primeng/components/common/messageservice';


@Injectable()
export class PostCreationService {
  private savePostUrl = 'http://localhost:8080/posts';

  constructor(private http: HttpClient,
              private messageService: MessageService) {
  }

  savePost(post: Post) {
    this.http.post<any>(this.savePostUrl, post)
      .subscribe(
        undefined,
        () => this.messageService.add({severity: 'error', summary: 'Error during adding post...'}),
        () => this.messageService.add({severity: 'success', summary: 'Post added.'}));
  }
}
