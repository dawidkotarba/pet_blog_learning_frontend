// angular
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {APP_BASE_HREF} from '@angular/common';
// primeng
import {ButtonModule, FieldsetModule, GrowlModule, InputTextareaModule, PanelModule} from 'primeng/primeng';
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
import {PostCreationService} from './admin-panel/post-creation/post-creation.service';
import {LoginPageComponent} from './admin-panel/login-page/login-page.component';
import {LoginService} from './admin-panel/login-page/login.service';

@NgModule({
  declarations: [
    AppComponent,
    PostsComponent,
    PostDetailsComponent,
    TopbarComponent,
    PostCreationComponent,
    LoginPageComponent
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
    InputTextareaModule
  ],
  providers: [
    {provide: APP_BASE_HREF, useValue: '/'},
    PostsService,
    PostDetailsService,
    MessageService,
    PostCreationService,
    LoginService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
