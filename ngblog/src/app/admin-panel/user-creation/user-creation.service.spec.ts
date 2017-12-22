import {inject, TestBed} from '@angular/core/testing';

import {UserCreationService} from './user-creation.service';
import {MessageService} from 'primeng/components/common/messageservice';
import {HttpClientModule} from '@angular/common/http';
import {Router} from '@angular/router';

describe('UserCreationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserCreationService,
        MessageService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy('navigate');
          }
        }],
      imports: [HttpClientModule]
    });
  });

  it('should be created', inject([UserCreationService], (service: UserCreationService) => {
    expect(service).toBeTruthy();
  }));
});
