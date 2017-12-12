import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PostsComponent} from './posts.component';
import {PostDetailsComponent} from '../post-details/post-details.component';
import {PostsService} from '../posts.service';

describe('PostsComponent', () => {
  let component: PostsComponent;
  let fixture: ComponentFixture<PostsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PostsComponent, PostDetailsComponent],
      providers: [PostsService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
