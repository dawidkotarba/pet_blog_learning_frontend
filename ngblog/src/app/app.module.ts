// angular
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {APP_BASE_HREF} from '@angular/common';
// primeng
import {
  AutoCompleteModule, ButtonModule, CalendarModule, CheckboxModule, EditorModule, FieldsetModule, GrowlModule,
  InputTextareaModule, InputTextModule, PaginatorModule, PanelModule, TieredMenuModule
} from 'primeng/primeng';
import {Ng4LoadingSpinnerModule} from 'ng4-loading-spinner';
import {MessageService} from 'primeng/components/common/messageservice';
// declarations
import {AppComponent} from './app.component';
import {PostsComponent} from './posts/posts.component';
import {PostDetailsComponent} from './post-details/post-details.component';
import {PostsService} from './posts/posts.service';
import {PostDetailsService} from './post-details/post-details.service';
import {TopbarComponent} from './topbar/topbar.component';
import {AppRoutingModule} from './app-routing.module';
import {PostCreationComponent} from './admin-panel/post-creation/post-creation.component';
import {LoginPageComponent} from './admin-panel/login-page/login-page.component';
import {LoginService} from './admin-panel/login-page/login.service';
import {UserCreationComponent} from './admin-panel/user-creation/user-creation.component';
import {AuthenticationInterceptor} from './admin-panel/auth/authentication-interceptor';
import {UtilClass} from './admin-panel/util/util-class';
import {ErrorInterceptor} from './app-error-interceptor';
import {SafeHtmlPipe} from './admin-panel/util/safe-html.pipe';
import {TableModule} from 'primeng/table';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {DashboardComponent} from './admin-panel/dashboard/dashboard.component';
import {PostsTableComponent} from './admin-panel/posts-table/posts-table.component';
import {UsersTableComponent} from './admin-panel/users-table/users-table.component';
import {UserInService} from './admin-panel/service/user-in.service';
import {UserOutService} from './admin-panel/service/user-out.service';
import {PostOutService} from './admin-panel/service/post-out.service';
import {PostInService} from './admin-panel/service/post-in.service';
import {LoginGuard} from './admin-panel/login.guard';
import {LogoutPageComponent} from './admin-panel/logout-page/logout-page.component';
import {AutocompleteService} from './admin-panel/service/autocomplete.service';

@NgModule({
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
    InputTextareaModule,
    InputTextModule,
    AutoCompleteModule,
    CheckboxModule,
    CalendarModule,
    EditorModule,
    TieredMenuModule,
    TableModule,
    PaginatorModule
  ],
  providers: [
    {
      provide: APP_BASE_HREF,
      useValue: '/'
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true,
    },
    PostsService,
    PostInService,
    PostOutService,
    UserInService,
    UserOutService,
    PostDetailsService,
    MessageService,
    LoginService,
    AutocompleteService,
    UtilClass,
    LoginGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
