import { Component } from '@angular/core';
import { AuthService } from '../auth/services/auth.service';

@Component({
  selector: 'app-page-error',
  templateUrl: './page-error.component.html',
  styles: [
    `
      .btn-gym:hover {
        transition: all 0.5s ease 0s;
      }
    `,
  ],
})
export class PageErrorComponent {}
