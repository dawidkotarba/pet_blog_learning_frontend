import {Injectable, Injector} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {LoginService} from '../login-page/login.service';
import {Router} from '@angular/router';
import {MessageService} from 'primeng/components/common/messageservice';
import {UtilClass} from '../util/util-class';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  urlMethodList: Array<[string, string]> = [];
  loginService: LoginService;

  constructor(private injector: Injector,
              private router: Router,
              private messageService: MessageService,
              private util: UtilClass) {
    this.urlMethodList.push(['http://localhost:8080/api/posts', 'POST']);
    this.urlMethodList.push(['http://localhost:8080/api/users', 'POST']);
    this.urlMethodList.push(['http://localhost:8080/api/labels', 'GET']);
    this.urlMethodList.push(['http://localhost:8080/api/authorities', 'GET']);
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const result = this.urlMethodList.filter(value => value[0] === this.trimUrl(request.url) && value[1] === request.method);
    if (result.length !== 0) {
      this.loginService = this.injector.get(LoginService);
      const authHeader = this.loginService.getAuthorizationHeader();

      if (authHeader) {
        const authRequest = request.clone({headers: request.headers.set('Authorization', authHeader)});
        return next.handle(authRequest);
      } else {
        this.util.redirectToLoginPage();
      }
    } else {
      return next.handle(request);
    }
  }

  trimUrl(url: string) {
    if (url.indexOf('?') !== -1) {
      return url.substring(0, url.indexOf('?'));
    }
    return url;
  }
}
