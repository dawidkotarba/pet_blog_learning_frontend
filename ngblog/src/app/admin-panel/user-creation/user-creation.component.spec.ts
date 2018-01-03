import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UserCreationComponent} from './user-creation.component';
import {AutoCompleteModule, CheckboxModule} from 'primeng/primeng';
import {MessageService} from 'primeng/components/common/messageservice';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
import {Router} from '@angular/router';
import {AutocompleteService} from '../service/autocomplete.service';
import {UserCreationService} from './user-creation.service';
import {UtilClass} from '../util/util-class';

describe('UserCreationComponent', () => {
  let component: UserCreationComponent;
  let fixture: ComponentFixture<UserCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UserCreationComponent],
      imports: [FormsModule,
        HttpClientModule,
        Ng4LoadingSpinnerModule.forRoot(),
        AutoCompleteModule,
        CheckboxModule],
      providers: [UserCreationService,
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
    fixture = TestBed.createComponent(UserCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
