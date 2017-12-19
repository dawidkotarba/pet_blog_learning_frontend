import {Component, OnInit} from '@angular/core';
import {Post} from '../model/post';
import {PostCreationService} from './post-creation.service';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';

@Component({
  selector: 'app-post-creation',
  templateUrl: './post-creation.component.html',
  styleUrls: ['./post-creation.component.css']
})
export class PostCreationComponent implements OnInit {
  post: Post = new Post();
  authors: string;


  constructor(private postCreationService: PostCreationService,
              private spinnerService: Ng4LoadingSpinnerService) {
  }

  ngOnInit() {
  }

  save() {
    this.spinnerService.show();
    this.post.authors = [parseInt(this.authors)];
    this.postCreationService.savePost(this.post);
    this.spinnerService.hide();
  }
}
