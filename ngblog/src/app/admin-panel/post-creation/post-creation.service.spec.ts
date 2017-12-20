import {inject, TestBed} from '@angular/core/testing';

import {PostCreationService} from './post-creation.service';
import {HttpClientModule} from '@angular/common/http';
import {MessageService} from 'primeng/components/common/messageservice';

describe('PostCreationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PostCreationService, MessageService],
      imports: [HttpClientModule]
    });
  });

  it('should be created', inject([PostCreationService], (service: PostCreationService) => {
    expect(service).toBeTruthy();
  }));
});
