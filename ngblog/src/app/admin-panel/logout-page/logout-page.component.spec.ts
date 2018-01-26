import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LogoutPageComponent} from './logout-page.component';
import {LoginPageComponent} from '../login-page/login-page.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
import {Router} from '@angular/router';
import {LoginService} from '../login-page/login.service';
import {MessageService} from 'primeng/components/common/messageservice';

describe('LogoutPageComponent', () => {
  let component: LogoutPageComponent;
  let fixture: ComponentFixture<LogoutPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LogoutPageComponent, LoginPageComponent],
      imports: [FormsModule, HttpClientModule, Ng4LoadingSpinnerModule.forRoot()],
      providers: [LoginService,
        MessageService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy('navigate');
          }
        }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogoutPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
