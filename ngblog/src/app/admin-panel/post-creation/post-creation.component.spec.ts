import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PostCreationComponent} from './post-creation.component';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
import {PostInService} from '../service/post-in.service';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MessageService} from 'primeng/components/common/messageservice';
import {Router} from '@angular/router';
import {AutoCompleteModule, CalendarModule, EditorModule} from 'primeng/primeng';
import {AutocompleteService} from '../service/autocomplete.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {UtilClass} from '../util/util-class';

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
        BrowserAnimationsModule,
        EditorModule
      ],
      providers: [
        PostInService,
        MessageService,
        {
          provide: Router, useClass: class {
            navigate = jasmine.createSpy('navigate');
          }
        },
        AutocompleteService,
        UtilClass]
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
