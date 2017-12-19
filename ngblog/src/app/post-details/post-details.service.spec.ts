import {inject, TestBed} from '@angular/core/testing';

import {PostDetailsService} from './post-details.service';
import {HttpClientModule} from '@angular/common/http';

describe('PostDetailsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PostDetailsService],
      imports: [HttpClientModule]
    });
  });

  it('should be created', inject([PostDetailsService], (service: PostDetailsService) => {
    expect(service).toBeTruthy();
  }));
});
