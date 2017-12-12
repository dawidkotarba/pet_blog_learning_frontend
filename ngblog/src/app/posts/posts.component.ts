import {Component, OnInit} from '@angular/core';
import {Post} from '../model/post';
import {mockposts} from '../model/mock-posts';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  posts = mockposts;
  selectedPost: Post;

  constructor() {
  }

  ngOnInit() {
  }

  onSelect(post) {
    this.selectedPost = post;
  }
}
