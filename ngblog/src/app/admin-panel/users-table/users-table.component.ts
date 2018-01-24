import {Component, OnInit} from '@angular/core';
import {MessageService} from 'primeng/components/common/messageservice';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {UserOut} from '../model/userOut';
import {UserOutService} from '../service/user-out.service';

@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrls: ['./users-table.component.css']
})
export class UsersTableComponent implements OnInit {

  users: UserOut[] = [];

  constructor(private userOutService: UserOutService,
              private spinnerService: Ng4LoadingSpinnerService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(): void {
    this.spinnerService.show();
    this.userOutService.getUsers().subscribe(users => {
      this.users = users;
      this.spinnerService.hide();
      if (users.length === 0) {
        this.messageService.add({severity: 'error', summary: 'Cannot load any users...'});
      }
    });
  }

}
