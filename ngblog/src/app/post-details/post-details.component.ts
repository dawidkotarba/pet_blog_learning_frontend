import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {PostDetailsService} from './post-details.service';
import {Post} from '../model/post';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {MessageService} from 'primeng/components/common/messageservice';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {
  post: Post = new Post();

  constructor(private  postDetailsService: PostDetailsService,
              private route: ActivatedRoute,
              private spinnerService: Ng4LoadingSpinnerService,
              private messageService: MessageService) {
    this.getPost();
  }

  ngOnInit() {
  }

  getPost(): void {
    const id = +this.route.snapshot.paramMap.get('postId');
    this.spinnerService.show();
    this.postDetailsService.getPost(id).subscribe(post => {
      this.post = post;
      this.spinnerService.hide();
      if (post === null) {
        this.messageService.add({severity: 'error', summary: 'Cannot load this post...'});
      }
    });
  }

  isPostAvailable(): boolean {
    return this.post !== null;
  }
}
