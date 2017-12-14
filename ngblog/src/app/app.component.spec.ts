import {async, TestBed} from '@angular/core/testing';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
// primefaces
import {ButtonModule, FieldsetModule} from 'primeng/primeng';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
// app
import {AppComponent} from './app.component';
import {PostsComponent} from './posts/posts.component';
import {PostDetailsComponent} from './post-details/post-details.component';
import {PostsService} from './posts.service';
import {TopbarComponent} from './topbar/topbar.component';


describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
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
      ],
      providers: [PostsService]
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
