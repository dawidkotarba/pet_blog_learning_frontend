import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpClientModule} from '@angular/common/http';

// primefaces
import {ButtonModule} from 'primeng/primeng';
import {FieldsetModule} from 'primeng/primeng';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

// app
import {PostsComponent} from '../posts/posts.component';
import {PostDetailsComponent} from '../post-details/post-details.component';
import {PostsService} from '../posts.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';


describe('PostsComponent', () => {
  let component: PostsComponent;
  let fixture: ComponentFixture<PostsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PostsComponent, PostDetailsComponent],
      providers: [PostsService],
      imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        ButtonModule,
        FieldsetModule
      ]
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
