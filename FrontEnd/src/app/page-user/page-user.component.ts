import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-page-user',
  templateUrl: './page-user.component.html',
  styles: [
    `
      .header-bg {
        background-image: url('../../assets/img/gym3.jpg');
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;
      }
      .btn-gym:hover {
        transition: all 0.5s ease 0s;
      }
    `,
  ],
})
export class PageUserComponent {
  loading: boolean = false;
  constructor(private authService: AuthService, private router: Router) {}

  logout() {
    this.authService.logout();
    this.loading = true;

    setTimeout(() => {
      this.router.navigate(['/auth/login']);
    }, 2000);
  }
}
