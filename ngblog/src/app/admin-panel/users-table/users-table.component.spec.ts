import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UsersTableComponent} from './users-table.component';
import {TableModule} from 'primeng/table';
import {UserOutService} from '../service/user-out.service';
import {HttpClientModule} from '@angular/common/http';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {MessageService} from 'primeng/components/common/messageservice';

describe('UsersTableComponent', () => {
  let component: UsersTableComponent;
  let fixture: ComponentFixture<UsersTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UsersTableComponent],
      imports: [TableModule, HttpClientModule],
      providers: [UserOutService, Ng4LoadingSpinnerService, MessageService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
