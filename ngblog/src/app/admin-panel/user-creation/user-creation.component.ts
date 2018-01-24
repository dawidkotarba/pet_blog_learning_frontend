import {Component, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {AutocompleteService} from '../service/autocomplete.service';
import {Authority} from '../../model/authority';
import {UserInService} from '../service/user-in.service';
import {UserIn} from '../model/userIn';

@Component({
  selector: 'app-user-creation',
  templateUrl: './user-creation.component.html',
  styleUrls: ['./user-creation.component.css']
})
export class UserCreationComponent implements OnInit {
  user: UserIn = new UserIn();
  authorities: Authority[];
  selectedAuthorities: Authority[];

  constructor(private userInService: UserInService,
              private spinnerService: Ng4LoadingSpinnerService,
              private autocompleteService: AutocompleteService) {
  }

  ngOnInit() {
  }

  save() {
    this.spinnerService.show();
    this.user.authorities = this.selectedAuthorities.map(authority => authority.id);
    this.userInService.saveUser(this.user);
    this.spinnerService.hide();
  }

  searchAuthorities(event): any {
    this.autocompleteService.getAuthorities(event.query).subscribe(data => {
      this.authorities = data;
    });
  }

}
