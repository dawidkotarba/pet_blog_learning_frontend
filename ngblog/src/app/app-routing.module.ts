import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PostDetailsComponent} from './post-details/post-details.component';
import {PostsComponent} from './posts/posts.component';
import {PostCreationComponent} from './admin-panel/post-creation/post-creation.component';
import {LoginPageComponent} from './admin-panel/login-page/login-page.component';
import {UserCreationComponent} from './admin-panel/user-creation/user-creation.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {DashboardComponent} from './admin-panel/dashboard/dashboard.component';
import {PostsTableComponent} from './admin-panel/posts-table/posts-table.component';
import {UsersTableComponent} from './admin-panel/users-table/users-table.component';
import {LogoutPageComponent} from './admin-panel/logout-page/logout-page.component';
import {LoginGuard} from './admin-panel/login.guard';

const routes: Routes = [
  {path: '', redirectTo: '/posts', pathMatch: 'full'},
  {path: 'posts', component: PostsComponent},
  {path: 'post/:postId', component: PostDetailsComponent},
  {
    path: 'adminPanel', component: DashboardComponent, canActivate: [LoginGuard], children: [
      {path: 'posts', component: PostsTableComponent, outlet: 'adminPanel'},
      {path: 'add-post', component: PostCreationComponent, outlet: 'adminPanel'},
      {path: 'users', component: UsersTableComponent, outlet: 'adminPanel'},
      {path: 'add-user', component: UserCreationComponent, outlet: 'adminPanel'}
    ]
  },
  {path: 'login', component: LoginPageComponent},
  {path: 'logout', component: LogoutPageComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
