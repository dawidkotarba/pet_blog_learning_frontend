import {Component, OnInit} from '@angular/core';
import {PostInService} from '../service/post-in.service';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {AutocompleteService} from '../service/autocomplete.service';
import {Author} from '../../model/author';
import {Label} from '../model/label';
import {UtilClass} from '../util/util-class';
import {PostIn} from '../model/postIn';

@Component({
  selector: 'app-post-creation',
  templateUrl: './post-creation.component.html',
  styleUrls: ['./post-creation.component.css']
})
export class PostCreationComponent implements OnInit {
  post: PostIn = new PostIn();
  authors: Author[];
  selectedAuthors: Author[];
  labels: Label[];
  selectedLabels: Label[];
  publishedDate: Date;

  constructor(private postInService: PostInService,
              private spinnerService: Ng4LoadingSpinnerService,
              private autocompleteService: AutocompleteService,
              private util: UtilClass) {
  }

  ngOnInit() {
  }

  searchLabels(event): any {
    this.autocompleteService.getLabels(event.query).subscribe(data => {
      this.labels = data;
    });
  }

  searchAuthors(event): any {
    this.autocompleteService.getAuthors(event.query).subscribe(data => {
      this.authors = data;
    });
  }

  save() {
    this.populatePostData();
    if (!this.validatePostData()) {
      return;
    }
    this.spinnerService.show();
    this.postInService.savePost(this.post).subscribe(
      undefined,
      () => {
        this.util.showErrorMessage('Error during adding post');

        this.spinnerService.hide();
      },
      () => {
        this.util.showSuccessMessage('This post have been successfully added');
        this.clearPostData();
        this.spinnerService.hide();
      });
  }

  populatePostData(): void {
    if (this.selectedAuthors) {
      this.post.authors = this.selectedAuthors.map(author => author.id);
    }

    if (this.selectedLabels) {
      this.post.labels = this.selectedLabels.map(label => label.id);
    }

    if (this.publishedDate) {
      this.post.published = this.publishedDate.toISOString();
    }
  }

  validatePostData(): boolean {
    if (!this.post.subject) {
      this.util.showErrorMessage('Subject cannot be empty');
      return false;
    }

    if (!this.post.body) {
      this.util.showErrorMessage('Body cannot be empty');
      return false;
    }

    if (!this.post.published) {
      this.util.showErrorMessage('Published date cannot be empty');
      return false;
    }

    if (!this.post.authors) {
      this.util.showErrorMessage('Authors cannot be empty');
      return false;
    }

    if (!this.post.labels) {
      this.util.showErrorMessage('Labels cannot be empty');
      return false;
    }

    return true;
  }

  clearPostData(): void {
    this.post = new PostIn();
    this.selectedAuthors = [];
    this.selectedLabels = [];
    this.publishedDate = null;
  }
}
