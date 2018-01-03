import {inject, TestBed} from '@angular/core/testing';

import {PostCreationService} from './post-creation.service';
import {HttpClientModule} from '@angular/common/http';

describe('PostCreationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PostCreationService],
      imports: [HttpClientModule]
    });
  });

  it('should be created', inject([PostCreationService], (service: PostCreationService) => {
    expect(service).toBeTruthy();
  }));
});
