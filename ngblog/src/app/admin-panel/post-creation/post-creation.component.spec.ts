import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PostCreationComponent} from './post-creation.component';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
import {PostCreationService} from './post-creation.service';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MessageService} from 'primeng/components/common/messageservice';
import {Router} from '@angular/router';
import {AutoCompleteModule, CalendarModule} from 'primeng/primeng';
import {AutocompleteService} from '../service/autocomplete.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

describe('PostCreationComponent', () => {
  let component: PostCreationComponent;
  let fixture: ComponentFixture<PostCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PostCreationComponent],
      imports: [
        FormsModule,
        HttpClientModule,
        Ng4LoadingSpinnerModule.forRoot(),
        AutoCompleteModule,
        CalendarModule,
        BrowserAnimationsModule],
      providers: [
        PostCreationService,
        MessageService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy('navigate');
          }
        },
        AutocompleteService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
