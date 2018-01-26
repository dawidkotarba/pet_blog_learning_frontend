import {inject, TestBed} from '@angular/core/testing';

import {UserOutService} from './user-out.service';
import {HttpClientModule} from '@angular/common/http';

describe('UserOutService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserOutService],
      imports: [HttpClientModule]
    });
  });

  it('should be created', inject([UserOutService], (service: UserOutService) => {
    expect(service).toBeTruthy();
  }));
});
