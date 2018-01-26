import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PostsTableComponent} from './posts-table.component';
import {TableModule} from 'primeng/table';
import {PostOutService} from '../service/post-out.service';
import {HttpClientModule} from '@angular/common/http';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {MessageService} from 'primeng/components/common/messageservice';

describe('PostsTableComponent', () => {
  let component: PostsTableComponent;
  let fixture: ComponentFixture<PostsTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PostsTableComponent],
      imports: [TableModule, HttpClientModule],
      providers: [PostOutService, Ng4LoadingSpinnerService, MessageService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
