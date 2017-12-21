import {inject, TestBed} from '@angular/core/testing';

import {AutocompleteService} from './autocomplete.service';
import {HttpClientModule} from '@angular/common/http';

describe('AutocompleteService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AutocompleteService],
      imports: [HttpClientModule]
    });
  });

  it('should be created', inject([AutocompleteService], (service: AutocompleteService) => {
    expect(service).toBeTruthy();
  }));
});
