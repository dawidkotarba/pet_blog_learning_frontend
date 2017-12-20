import {Component, OnInit} from '@angular/core';
import {LoginService} from './login.service';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  username: string;
  password: string;

  constructor(private loginService: LoginService,
              private spinnerService: Ng4LoadingSpinnerService) {
  }

  ngOnInit() {
  }

  login() {
    this.spinnerService.show();
    this.loginService.login(this.username, this.password);
    this.spinnerService.hide();
  }
}
