import {inject, TestBed} from '@angular/core/testing';

import {UserOutService} from './user-out.service';

describe('UserOutService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserOutService]
    });
  });

  it('should be created', inject([UserOutService], (service: UserOutService) => {
    expect(service).toBeTruthy();
  }));
});
