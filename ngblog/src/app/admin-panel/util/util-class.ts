import {Injectable} from '@angular/core';
import {MessageService} from 'primeng/components/common/messageservice';
import {Router} from '@angular/router';

@Injectable()
export class UtilClass {

  constructor(private router: Router, private messageService: MessageService) {
  }

  redirectToLoginPage() {
    this.router.navigate(['/login']);
    this.messageService.add({severity: 'warn', summary: 'Please login first...'});
  }

  showErrorMessage(summary: string): void {
    this.messageService.add({severity: 'error', summary: summary});
  }

  showSuccessMessage(summary: string): void {
    this.messageService.add({severity: 'success', summary: summary});
  }
}
