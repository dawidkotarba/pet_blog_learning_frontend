import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/primeng';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private items: MenuItem[];

  constructor() {
  }

  ngOnInit() {
    this.items = [
      {
        label: 'Posts', icon: 'fa-list',
        items: [
          {label: 'Show all', icon: 'fa-table', routerLink: [{outlets: {adminPanel: ['posts']}}]},
          {label: 'New', icon: 'fa-plus', routerLink: [{outlets: {adminPanel: ['add-post']}}]},
          {label: 'Update', icon: 'fa-pencil', routerLink: ['/pagename']},
        ]
      },
      {
        label: 'Users', icon: 'fa-users',
        items: [
          {label: 'Show all', icon: 'fa-table', routerLink: [{outlets: {adminPanel: ['users']}}]},
          {label: 'New', icon: 'fa-plus', routerLink: [{outlets: {adminPanel: ['add-user']}}]},
          {label: 'Update', icon: 'fa-pencil', routerLink: ['/pagename']},
        ]
      },
      {
        label: 'Labels', icon:
          'fa-tag',
        items:
          [
            {label: 'Show all', icon: 'fa-table', url: 'http://www.primefaces.org/primeng'},
            {label: 'New', icon: 'fa-plus', url: 'http://www.primefaces.org/primeng'},
            {label: 'Update', icon: 'fa-pencil', routerLink: ['/pagename']},
          ]
      }
      ,
      {
        label: 'Authors', icon:
          'fa-user-circle-o',
        items:
          [
            {label: 'Show all', icon: 'fa-table', url: 'http://www.primefaces.org/primeng'},
            {label: 'New', icon: 'fa-plus', url: 'http://www.primefaces.org/primeng'},
            {label: 'Update', icon: 'fa-pencil', routerLink: ['/pagename']},
          ]
      }
      ,
      {
        label: 'Comments', icon:
          'fa-comments'
      }
      ,
      {
        label: 'Media', icon:
          'fa-picture-o'
      }
      ,
      {
        label: 'Settings', icon:
          'fa-wrench'
      }
      ,
      {
        separator: true
      }
      ,
      {
        label: 'Log out', icon:
          'fa-sign-out', routerLink:
          ['/logout']
      }
    ]
    ;
  }

}
