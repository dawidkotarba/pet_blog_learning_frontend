import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
// primeng
import {AutoCompleteModule, ButtonModule, CheckboxModule, FieldsetModule, PanelModule, CalendarModule} from 'primeng/primeng';
import {MessageService} from 'primeng/components/common/messageservice';
// app
import {PostsComponent} from '../posts/posts.component';
import {PostDetailsComponent} from '../post-details/post-details.component';
import {PostsService} from './posts.service';
import {AppRoutingModule} from '../app-routing.module';
import {APP_BASE_HREF} from '@angular/common';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
import {PostCreationComponent} from '../admin-panel/post-creation/post-creation.component';
import {LoginPageComponent} from '../admin-panel/login-page/login-page.component';
import {UserCreationComponent} from '../admin-panel/user-creation/user-creation.component';


describe('PostsComponent', () => {
  let component: PostsComponent;
  let fixture: ComponentFixture<PostsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        PostsComponent,
        PostDetailsComponent,
        PostCreationComponent,
        LoginPageComponent,
        UserCreationComponent],
      providers: [
        PostsService,
        MessageService,
        {provide: APP_BASE_HREF, useValue: '/'}
      ],
      imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        ButtonModule,
        FieldsetModule,
        PanelModule,
        AppRoutingModule,
        Ng4LoadingSpinnerModule.forRoot(),
        AutoCompleteModule,
        CheckboxModule,
        CalendarModule
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
