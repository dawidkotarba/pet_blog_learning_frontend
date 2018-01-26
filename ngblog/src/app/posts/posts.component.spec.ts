import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
// primeng
import {
  AutoCompleteModule, ButtonModule, CalendarModule, CheckboxModule, EditorModule, FieldsetModule, PaginatorModule,
  PanelModule, TieredMenuModule
} from 'primeng/primeng';
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
import {SafeHtmlPipe} from '../admin-panel/util/safe-html.pipe';
import {PageNotFoundComponent} from '../page-not-found/page-not-found.component';
import {PostInService} from '../admin-panel/service/post-in.service';
import {PostOutService} from '../admin-panel/service/post-out.service';
import {DashboardComponent} from '../admin-panel/dashboard/dashboard.component';
import {PostsTableComponent} from '../admin-panel/posts-table/posts-table.component';
import {UsersTableComponent} from '../admin-panel/users-table/users-table.component';
import {TableModule} from 'primeng/table';
import {LogoutPageComponent} from '../admin-panel/logout-page/logout-page.component';


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
        UserCreationComponent,
        SafeHtmlPipe,
        PageNotFoundComponent,
        LogoutPageComponent,
        DashboardComponent,
        PostsTableComponent,
        UsersTableComponent
      ],
      providers: [
        PostsService,
        PostInService,
        PostOutService,
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
        CalendarModule,
        EditorModule,
        PaginatorModule,
        TieredMenuModule,
        TableModule
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
