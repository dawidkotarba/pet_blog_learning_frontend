import {Component, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {AutocompleteService} from '../service/autocomplete.service';
import {Authority} from '../../model/authority';
import {UserCreationService} from './user-creation.service';
import {User} from '../model/user';
import {UtilClass} from '../util/util-class';

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
              private autocompleteService: AutocompleteService,
              private util: UtilClass) {
    if (!localStorage.getItem('currentUser')) {
      this.util.redirectToLoginPage();
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
