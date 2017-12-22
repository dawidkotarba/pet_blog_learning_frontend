import {Component, OnInit} from '@angular/core';
import {Post} from '../model/post';
import {PostCreationService} from './post-creation.service';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {Router} from '@angular/router';
import {MessageService} from 'primeng/components/common/messageservice';
import {AutocompleteService} from '../service/autocomplete.service';
import {Author} from '../../model/author';

@Component({
  selector: 'app-post-creation',
  templateUrl: './post-creation.component.html',
  styleUrls: ['./post-creation.component.css']
})
export class PostCreationComponent implements OnInit {
  post: Post = new Post();
  selectedAuthors: Author[];
  authors: Author[];


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

  save() {
    this.spinnerService.show();
    this.post.authors = this.selectedAuthors.map(author => author.id);
    this.postCreationService.savePost(this.post);
    this.spinnerService.hide();
  }

  searchAuthors(event): any {
    this.autocompleteService.getAuthors(event.query).subscribe(data => {
      this.authors = data;
    });
  }
}
