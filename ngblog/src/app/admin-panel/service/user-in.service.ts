import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MessageService} from 'primeng/components/common/messageservice';
import {UserIn} from '../model/userIn';

@Injectable()
export class UserInService {
  saveUserUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient,
              private messageService: MessageService) {
  }

  saveUser(user: UserIn) {
    this.http.post(this.saveUserUrl, user)
      .subscribe(
        undefined,
        () => this.messageService.add({severity: 'error', summary: 'Error during adding user...'}),
        () => this.messageService.add({severity: 'success', summary: 'User added.'}));
  }
}
