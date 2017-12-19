import {inject, TestBed} from '@angular/core/testing';

import {PostCreationService} from './post-creation.service';

describe('PostCreationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PostCreationService]
    });
  });

  it('should be created', inject([PostCreationService], (service: PostCreationService) => {
    expect(service).toBeTruthy();
  }));
});
