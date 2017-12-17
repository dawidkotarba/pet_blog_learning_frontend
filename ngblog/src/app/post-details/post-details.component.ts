import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {PostDetailsService} from './post-details.service';
import {Post} from '../model/post';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css']
})
export class PostDetailsComponent implements OnInit {
  post: Post;

  constructor(private  postDetailsService: PostDetailsService, private route: ActivatedRoute) {
    this.getPost();
  }

  ngOnInit() {
  }

  getPost(): void {
    const id = +this.route.snapshot.paramMap.get('postId');
    this.postDetailsService.getPost(id).subscribe(post => this.post = post);
  }
}
