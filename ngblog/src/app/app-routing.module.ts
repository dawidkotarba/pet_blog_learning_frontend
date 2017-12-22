import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PostDetailsComponent} from './post-details/post-details.component';
import {PostsComponent} from './posts/posts.component';
import {PostCreationComponent} from './admin-panel/post-creation/post-creation.component';
import {LoginPageComponent} from './admin-panel/login-page/login-page.component';
import {UserCreationComponent} from './admin-panel/user-creation/user-creation.component';

const routes: Routes = [
  {path: '', redirectTo: '/posts', pathMatch: 'full'},
  {path: 'posts', component: PostsComponent},
  {path: 'post/:postId', component: PostDetailsComponent},
  {path: 'adminPanel/posts/add', component: PostCreationComponent},
  {path: 'adminPanel/users/add', component: UserCreationComponent},
  {path: 'adminPanel/login', component: LoginPageComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
