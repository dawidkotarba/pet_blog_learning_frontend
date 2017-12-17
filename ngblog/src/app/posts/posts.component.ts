import {Component, OnInit} from '@angular/core';
import {Post} from '../model/post';
import {PostsService} from './posts.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  posts: Post[] = [];

  constructor(private postService: PostsService) {
  }

  ngOnInit() {
    this.getPosts();
  }

  getPosts(): void {
    this.postService.getPosts().subscribe(posts => this.posts = posts);
  }

  arePostsAvailable(): boolean {
    return this.posts && this.posts.length > 0;
  }
}
