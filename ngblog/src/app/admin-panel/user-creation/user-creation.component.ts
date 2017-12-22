import {Component, OnInit} from '@angular/core';
import {MessageService} from 'primeng/components/common/messageservice';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {Router} from '@angular/router';
import {AutocompleteService} from '../service/autocomplete.service';
import {Authority} from '../../model/authority';
import {UserCreationService} from './user-creation.service';
import {User} from '../model/user';

@Component({
  selector: 'app-user-creation',
  templateUrl: './user-creation.component.html',
  styleUrls: ['./user-creation.component.css']
})
export class UserCreationComponent implements OnInit {
  user: User = new User();
  authorities: Authority[];
  selectedAuthorities: Authority[];

  constructor(private userCreationService: UserCreationService,
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
    this.user.authorities = this.selectedAuthorities.map(authority => authority.id);
    this.userCreationService.saveUser(this.user);
    this.spinnerService.hide();
  }

  searchAuthorities(event): any {
    this.autocompleteService.getAuthorities(event.query).subscribe(data => {
      this.authorities = data;
    });
  }

}
