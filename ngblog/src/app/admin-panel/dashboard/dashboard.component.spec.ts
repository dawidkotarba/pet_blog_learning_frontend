import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DashboardComponent} from './dashboard.component';
import {
  AutoCompleteModule, CalendarModule, CheckboxModule, FieldsetModule, PaginatorModule, PanelModule,
  TieredMenuModule
} from 'primeng/primeng';
import {AppRoutingModule} from '../../app-routing.module';
import {PostsComponent} from '../../posts/posts.component';
import {PostDetailsComponent} from '../../post-details/post-details.component';
import {UsersTableComponent} from '../users-table/users-table.component';
import {PostsTableComponent} from '../posts-table/posts-table.component';
import {PostCreationComponent} from '../post-creation/post-creation.component';
import {UserCreationComponent} from '../user-creation/user-creation.component';
import {LogoutPageComponent} from '../logout-page/logout-page.component';
import {LoginPageComponent} from '../login-page/login-page.component';
import {PageNotFoundComponent} from '../../page-not-found/page-not-found.component';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
import {SafeHtmlPipe} from '../util/safe-html.pipe';
import {TableModule} from 'primeng/table';
import {EditorModule} from 'primeng/editor';
import {APP_BASE_HREF} from '@angular/common';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        DashboardComponent,
        PostsComponent,
        PostDetailsComponent,
        PostsTableComponent,
        UsersTableComponent,
        PostCreationComponent,
        UserCreationComponent,
        LoginPageComponent,
        LogoutPageComponent,
        PageNotFoundComponent,
        SafeHtmlPipe
      ],
      imports: [
        PanelModule,
        TieredMenuModule,
        AppRoutingModule,
        Ng4LoadingSpinnerModule,
        PaginatorModule,
        FieldsetModule,
        TableModule,
        EditorModule,
        CalendarModule,
        AutoCompleteModule,
        CheckboxModule
      ],
      providers: [
        {
          provide: APP_BASE_HREF,
          useValue: '/'
        },
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
