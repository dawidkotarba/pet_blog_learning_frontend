import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {UtilClass} from './util/util-class';
import {LoginService} from './login-page/login.service';

@Injectable()
export class LoginGuard implements CanActivate {

  constructor(private loginService: LoginService, private util: UtilClass) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.loginService.isAuthenticated()) {
      this.util.redirectToLoginPage();
      return false;
    }
    return true;
  }
}
