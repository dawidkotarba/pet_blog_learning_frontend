import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MessageService} from 'primeng/components/common/messageservice';
import {User} from '../model/user';

@Injectable()
export class UserCreationService {
  saveUserUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient,
              private messageService: MessageService) {
  }

  saveUser(user: User) {
    this.http.post(this.saveUserUrl, user)
      .subscribe(
        undefined,
        () => this.messageService.add({severity: 'error', summary: 'Error during adding user...'}),
        () => this.messageService.add({severity: 'success', summary: 'User added.'}));
  }
}
