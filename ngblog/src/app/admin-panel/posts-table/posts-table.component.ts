import {Component, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {MessageService} from 'primeng/components/common/messageservice';
import {PostOutService} from '../service/post-out.service';
import {PostOut} from '../model/postOut';

@Component({
  selector: 'app-posts-table',
  templateUrl: './posts-table.component.html',
  styleUrls: ['./posts-table.component.css']
})
export class PostsTableComponent implements OnInit {

  posts: PostOut[] = [];

  constructor(private postOutService: PostOutService,
              private spinnerService: Ng4LoadingSpinnerService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.getPosts();
  }

  getPosts(): void {
    this.spinnerService.show();
    this.postOutService.getPosts().subscribe(posts => {
      this.posts = posts;
      this.spinnerService.hide();
      if (posts.length === 0) {
        this.messageService.add({severity: 'error', summary: 'Cannot load any posts...'});
      }
    });
  }

}
