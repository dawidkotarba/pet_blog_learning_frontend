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
  PaginatorModule, PanelModule, TieredMenuModule
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
import {DashboardComponent} from './admin-panel/dashboard/dashboard.component';
import {PostsTableComponent} from './admin-panel/posts-table/posts-table.component';
import {UsersTableComponent} from './admin-panel/users-table/users-table.component';
import {LogoutPageComponent} from './admin-panel/logout-page/logout-page.component';
import {TableModule} from 'primeng/table';


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
        LogoutPageComponent,
        UserCreationComponent,
        SafeHtmlPipe,
        PageNotFoundComponent,
        DashboardComponent,
        PostsTableComponent,
        UsersTableComponent
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
        PaginatorModule,
        TieredMenuModule,
        TableModule
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
