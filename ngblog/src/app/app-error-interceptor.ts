import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/throw';
import {MessageService} from 'primeng/components/common/messageservice';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private messageService: MessageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).do(event => {
    }, err => {
      if (err instanceof HttpErrorResponse) {
        const xmlHttpRequest = event.target as XMLHttpRequest;
        const response = JSON.parse(xmlHttpRequest.response);
        const userMessage = response.userMessage;
        const uuid = response.uuid;
        if (userMessage) {
          this.messageService.add({severity: 'error', summary: userMessage, detail: 'Error id: ' + uuid});
        }
        Observable.throw(err);
      }
    });
  }
}
