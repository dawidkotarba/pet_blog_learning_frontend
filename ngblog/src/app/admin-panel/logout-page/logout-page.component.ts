import {Component, OnInit} from '@angular/core';
import {LoginService} from '../login-page/login.service';

@Component({
  selector: 'app-logout-page',
  templateUrl: './logout-page.component.html',
  styleUrls: ['./logout-page.component.css']
})
export class LogoutPageComponent implements OnInit {

  constructor(private loginService: LoginService) {
  }

  ngOnInit() {
    this.loginService.logout();
  }

}
