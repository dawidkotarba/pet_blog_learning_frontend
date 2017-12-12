import {Component, OnInit} from '@angular/core';
import {Post} from '../model/post';
import {PostsService} from '../posts.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  posts: Post[];
  selectedPost: Post;

  constructor(private postService: PostsService) {
  }

  ngOnInit() {
    this.getPosts();
  }

  onSelect(post) {
    this.selectedPost = post;
  }

  getPosts(): void {
    this.postService.getPosts().subscribe(posts => this.posts = posts);
  }
}
