import {inject, TestBed} from '@angular/core/testing';

import {PostInService} from './post-in.service';
import {HttpClientModule} from '@angular/common/http';

describe('PostInService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PostInService],
      imports: [HttpClientModule]
    });
  });

  it('should be created', inject([PostInService], (service: PostInService) => {
    expect(service).toBeTruthy();
  }));
});
