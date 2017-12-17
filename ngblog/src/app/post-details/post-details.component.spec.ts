import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PostDetailsComponent} from './post-details.component';
import {PostDetailsService} from './post-details.service';
import {HttpClientModule} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

describe('PostDetailsComponent', () => {
  let component: PostDetailsComponent;
  let fixture: ComponentFixture<PostDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PostDetailsComponent],
      imports: [HttpClientModule],
      providers: [PostDetailsService, ActivatedRoute]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //TODO
  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
