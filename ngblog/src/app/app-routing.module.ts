import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PostDetailsComponent} from './post-details/post-details.component';
import {PostsComponent} from './posts/posts.component';
import {PostCreationComponent} from './admin-panel/post-creation/post-creation.component';
import {LoginPageComponent} from './admin-panel/login-page/login-page.component';

const routes: Routes = [
  {path: '', redirectTo: '/posts', pathMatch: 'full'},
  {path: 'posts', component: PostsComponent},
  {path: 'post/:postId', component: PostDetailsComponent},
  {path: 'adminPanel', component: PostCreationComponent},
  {path: 'login', component: LoginPageComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
