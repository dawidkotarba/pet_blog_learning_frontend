import {inject, TestBed} from '@angular/core/testing';
import {LoginGuard} from './login.guard';
import {LoginService} from './login-page/login.service';
import {HttpClientModule} from '@angular/common/http';
import {MessageService} from 'primeng/components/common/messageservice';
import {Router} from '@angular/router';
import {UtilClass} from './util/util-class';


describe('LoginGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        LoginService,
        LoginGuard,
        MessageService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy('navigate');
          }
        },
        UtilClass
      ],
      imports: [HttpClientModule]
    });
  });

  it('should ...', inject([LoginGuard], (guard: LoginGuard) => {
    expect(guard).toBeTruthy();
  }));
});
