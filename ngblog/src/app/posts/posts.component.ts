import {Component, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {MessageService} from 'primeng/components/common/messageservice';

import {Post} from '../model/post';
import {PostsService} from './posts.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  posts: Post[] = [];

  constructor(private postService: PostsService,
              private spinnerService: Ng4LoadingSpinnerService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.getPosts();
  }

  getPosts(): void {
    this.spinnerService.show();
    this.postService.getPosts().subscribe(posts => {
      this.posts = posts;
      this.spinnerService.hide();
      if (posts.length === 0) {
        this.messageService.add({severity: 'error', summary: 'Cannot load any posts...'});
      }
    });
  }

  arePostsAvailable(): boolean {
    return this.posts && this.posts.length > 0;
  }
}
