import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from 'primeng/components/common/messageservice';
import {Router} from '@angular/router';
import {User} from '../model/user';

@Injectable()
export class UserCreationService {
  saveUserUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient,
              private messageService: MessageService,
              private router: Router) {
  }

  saveUser(user: User) {
    const currentUser = localStorage.getItem('currentUser');
    if (!currentUser) {
      this.router.navigate(['/adminPanel/login']);
      this.messageService.add({severity: 'warn', summary: 'Please login first...'});
    } else {
      const httpOptions = {
        headers: new HttpHeaders({'Authorization': currentUser})
      };

      this.http.post(this.saveUserUrl, user, httpOptions)
        .subscribe(
          undefined,
          () => this.messageService.add({severity: 'error', summary: 'Error during adding user...'}),
          () => this.messageService.add({severity: 'success', summary: 'User added.'}));
    }
  }
}
