// angular
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
// primefaces
import {ButtonModule, FieldsetModule} from 'primeng/primeng';
// declarations
import {AppComponent} from './app.component';
import {PostsComponent} from './posts/posts.component';
import {PostDetailsComponent} from './post-details/post-details.component';
import {PostsService} from './posts.service';
import {TopbarComponent} from './topbar/topbar.component';


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
    FieldsetModule
  ],
  providers: [PostsService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
