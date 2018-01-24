import {inject, TestBed} from '@angular/core/testing';

import {PostOutService} from './post-out.service';

describe('PostOutService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PostOutService]
    });
  });

  it('should be created', inject([PostOutService], (service: PostOutService) => {
    expect(service).toBeTruthy();
  }));
});
