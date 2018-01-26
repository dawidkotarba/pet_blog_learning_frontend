import {inject, TestBed} from '@angular/core/testing';

import {PostOutService} from './post-out.service';
import {HttpClientModule} from '@angular/common/http';

describe('PostOutService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PostOutService],
      imports: [HttpClientModule]
    });
  });

  it('should be created', inject([PostOutService], (service: PostOutService) => {
    expect(service).toBeTruthy();
  }));
});
