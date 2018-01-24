import {inject, TestBed} from '@angular/core/testing';

import {UserInService} from './user-in.service';
import {MessageService} from 'primeng/components/common/messageservice';
import {HttpClientModule} from '@angular/common/http';
import {Router} from '@angular/router';

describe('UserInService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserInService,
        MessageService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy('navigate');
          }
        }],
      imports: [HttpClientModule]
    });
  });

  it('should be created', inject([UserInService], (service: UserInService) => {
    expect(service).toBeTruthy();
  }));
});
