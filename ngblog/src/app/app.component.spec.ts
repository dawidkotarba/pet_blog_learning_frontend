import {async, TestBed} from '@angular/core/testing';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BrowserModule} from '@angular/platform-browser';
import {TopbarComponent} from './topbar/topbar.component';
import {APP_BASE_HREF} from '@angular/common';
import {FormsModule} from '@angular/forms';
// primeng
import {
  AutoCompleteModule, ButtonModule, CalendarModule, CheckboxModule, EditorModule, FieldsetModule, GrowlModule,
  PaginatorModule, PanelModule
} from 'primeng/primeng';
import {MessageService} from 'primeng/components/common/messageservice';
// app
import {PostsComponent} from './posts/posts.component';
import {PostDetailsComponent} from './post-details/post-details.component';
import {PostsService} from './posts/posts.service';
import {AppRoutingModule} from './/app-routing.module';
import {AppComponent} from './app.component';
import {PostDetailsService} from './post-details/post-details.service';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
import {PostCreationComponent} from './admin-panel/post-creation/post-creation.component';
import {LoginService} from './admin-panel/login-page/login.service';
import {LoginPageComponent} from './admin-panel/login-page/login-page.component';
import {UserCreationComponent} from './admin-panel/user-creation/user-creation.component';
import {SafeHtmlPipe} from './admin-panel/util/safe-html.pipe';
import {PostInService} from './admin-panel/service/post-in.service';
import {PostOutService} from './admin-panel/service/post-out.service';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';


describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        PostsComponent,
        PostDetailsComponent,
        TopbarComponent,
        PostCreationComponent,
        LoginPageComponent,
        UserCreationComponent,
        SafeHtmlPipe,
        PageNotFoundComponent
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
        GrowlModule,
        AutoCompleteModule,
        CheckboxModule,
        CalendarModule,
        EditorModule,
        PaginatorModule
      ],
      providers: [
        {provide: APP_BASE_HREF, useValue: '/'},
        PostsService,
        PostInService,
        PostOutService,
        PostDetailsService,
        MessageService,
        LoginService]
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
