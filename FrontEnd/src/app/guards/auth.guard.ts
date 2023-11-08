import { Injectable } from '@angular/core';
import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  CanLoad,
} from '@angular/router';

import { AuthService } from '../auth/services/auth.service';
import Swal from 'sweetalert2';

import { Observable, tap } from 'rxjs';
import { Role } from '../auth/interfaces/role';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate, CanLoad {
  constructor(private router: Router, private authService: AuthService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | boolean {
    const user = this.authService.userValue;
    if (user) {
      // Comprobar si la ruta está restringida por roles
      const { roles } = route.data;
      if (roles) {
        // Comprobar si algún rol del usuario coincide con los roles definidos en la ruta
        const authorized = roles.some(
          (role: Role) => user.roles && user.roles.includes(role)
        );

        if (!authorized) {
          // Rol no autorizado, redirigir a la página de inicio
          const Toast = Swal.mixin({
            toast: true,
            position: 'center',
            showConfirmButton: false,
            timer: 2500,
          });
          Toast.fire({
            icon: 'error',
            title: 'No estas Autorizado',
          });
          this.router.navigate(['inicio']);
          return false;
        }
      }
      // Autorizado, retornar true
      return true;
    }

    // Si no ha iniciado sesión, redirigir a la página de inicio de sesión con la URL de retorno
    const Toast = Swal.mixin({
      toast: true,
      position: 'center',
      showConfirmButton: false,
      timer: 2500,
    });
    Toast.fire({
      icon: 'error',
      title: 'Debes iniciar sesión',
    });
    this.router.navigate(['/auth/login'], {
      queryParams: { returnUrl: state.url },
    });
    return false;
  }

  canLoad(): Observable<boolean> | boolean {
    return this.authService.isAuthenticated().pipe(
      tap((valid) => {
        if (!valid) {
          const Toast = Swal.mixin({
            toast: true,
            position: 'center',
            showConfirmButton: false,
            timer: 2500,
          });
          Toast.fire({
            icon: 'error',
            title: 'No haz iniciado sesión',
          });

          this.router.navigateByUrl('/auth/login');
        }
      })
    );
  }
}
