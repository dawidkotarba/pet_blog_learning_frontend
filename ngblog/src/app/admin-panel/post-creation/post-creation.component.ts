import {Component, OnInit} from '@angular/core';
import {Post} from '../model/post';
import {PostCreationService} from './post-creation.service';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {AutocompleteService} from '../service/autocomplete.service';
import {Author} from '../../model/author';
import {Label} from '../model/label';
import {UtilClass} from '../util/util-class';

@Component({
  selector: 'app-post-creation',
  templateUrl: './post-creation.component.html',
  styleUrls: ['./post-creation.component.css']
})
export class PostCreationComponent implements OnInit {
  post: Post = new Post();
  authors: Author[];
  selectedAuthors: Author[];
  labels: Label[];
  selectedLabels: Label[];
  publishedDate: Date = new Date();

  constructor(private postCreationService: PostCreationService,
              private spinnerService: Ng4LoadingSpinnerService,
              private autocompleteService: AutocompleteService,
              private util: UtilClass) {
    if (!localStorage.getItem('currentUser')) {
      this.util.redirectToLoginPage();
    }
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
    this.postCreationService.savePost(this.post).subscribe(
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
    this.post = new Post();
    this.selectedAuthors = [];
    this.selectedLabels = [];
    this.publishedDate = null;
  }
}
