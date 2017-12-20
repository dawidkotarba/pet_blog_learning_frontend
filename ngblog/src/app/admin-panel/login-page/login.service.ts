import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MessageService} from 'primeng/components/common/messageservice';

@Injectable()
export class LoginService {
  private loginUrl = 'http://localhost:8080/adminPanel/login';

  constructor(private http: HttpClient,
              private messageService: MessageService) {
  }

  login(username: string, password: string) {

    this.http.post(this.loginUrl, {username, password})
      .subscribe(
        undefined,
        () => this.messageService.add({severity: 'error', summary: 'Login failure...'}),
        () => this.messageService.add({severity: 'success', summary: 'Login successfull.'}));
  }
}
