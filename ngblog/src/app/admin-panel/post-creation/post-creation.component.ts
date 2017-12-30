import {Component, OnInit} from '@angular/core';
import {Post} from '../model/post';
import {PostCreationService} from './post-creation.service';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {Router} from '@angular/router';
import {MessageService} from 'primeng/components/common/messageservice';
import {AutocompleteService} from '../service/autocomplete.service';
import {Author} from '../../model/author';
import {Label} from '../model/label';

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
  publishedDate: Date;

  constructor(private postCreationService: PostCreationService,
              private spinnerService: Ng4LoadingSpinnerService,
              private router: Router,
              private messageService: MessageService,
              private autocompleteService: AutocompleteService) {
    if (!localStorage.getItem('currentUser')) {
      this.messageService.add({severity: 'warn', summary: 'Please login first...'});
      this.router.navigate(['/adminPanel/login']);
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
        this.showErrorMessage('Error during adding post');

        this.spinnerService.hide();
      },
      () => {
        this.showSuccessMessage('This post have been successfully added');
        this.clearPostData();
        this.spinnerService.hide();
      });
  }

  populatePostData(): void {
    if (this.selectedAuthors) {
      this.post.authors = this.selectedAuthors.map(author => author.id);
    }

    if (this.selectedLabels){
      this.post.labels = this.selectedLabels.map(label => label.id);
    }

    if (this.publishedDate) {
      this.post.published = this.publishedDate.toISOString();
    }
  }

  validatePostData(): boolean {
    if (!this.post.subject) {
      this.showErrorMessage('Subject cannot be empty');
      return false;
    }

    if (!this.post.body) {
      this.showErrorMessage('Body cannot be empty');
      return false;
    }

    if (!this.post.published) {
      this.showErrorMessage('Published date cannot be empty');
      return false;
    }

    if (!this.post.authors) {
      this.showErrorMessage('Authors cannot be empty');
      return false;
    }

    if (!this.post.labels) {
      this.showErrorMessage('Labels cannot be empty');
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

  showErrorMessage(summary: string): void {
    this.messageService.add({severity: 'error', summary: summary});
  }

  showSuccessMessage(summary: string): void {
    this.messageService.add({severity: 'success', summary: summary});
  }
}
