// angular
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {APP_BASE_HREF} from '@angular/common';
// primeng
import {ButtonModule, FieldsetModule, PanelModule} from 'primeng/primeng';
// declarations
import {AppComponent} from './app.component';
import {PostsComponent} from './posts/posts.component';
import {PostDetailsComponent} from './post-details/post-details.component';
import {PostsService} from './posts/posts.service';
import {PostDetailsService} from './post-details/post-details.service';
import {TopbarComponent} from './topbar/topbar.component';
import {AppRoutingModule} from './/app-routing.module';


@NgModule({
  declarations: [
    AppComponent,
    PostsComponent,
    PostDetailsComponent,
    TopbarComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ButtonModule,
    FieldsetModule,
    PanelModule,
    AppRoutingModule
  ],
  providers: [{provide: APP_BASE_HREF, useValue: '/'},
    PostsService,
    PostDetailsService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
