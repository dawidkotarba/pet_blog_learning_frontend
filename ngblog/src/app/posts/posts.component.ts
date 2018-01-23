import {Component, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {MessageService} from 'primeng/components/common/messageservice';

import {Post} from '../model/post';
import {PostsService} from './posts.service';
import {Pagination} from '../model/pagination';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {
  paginatedPosts: Post[] = [];
  pagination: Pagination = new Pagination();

  constructor(private postService: PostsService,
              private spinnerService: Ng4LoadingSpinnerService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.getPaginatedPosts();
  }

  getPaginatedPosts(page: number = 0, pageSize: number = 5): void {
    this.spinnerService.show();
    this.postService.getPaginatedPosts(page, pageSize).subscribe(response => {
      this.pagination = response;
      this.paginatedPosts = response.content;
      this.spinnerService.hide();
      if (this.paginatedPosts.length === 0) {
        this.messageService.add({severity: 'error', summary: 'Cannot load any posts...'});
      }
    });
  }

  paginate(event): void {
    this.getPaginatedPosts(event.page, event.rows);
  }

  arePaginatedPostsAvailable(): boolean {
    return this.paginatedPosts && this.paginatedPosts.length > 0;
  }
}
