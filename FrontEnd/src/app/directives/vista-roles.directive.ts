import {
  Directive,
  Input,
  OnDestroy,
  OnInit,
  TemplateRef,
  ViewContainerRef,
} from '@angular/core';
import { AuthService } from '../auth/services/auth.service';
// import { Role } from '../auth/interfaces/role';
import { Subscription, distinctUntilChanged, map, tap } from 'rxjs';
type Role = 'ROLE_ADMIN' | 'ROLE_COACH' | 'ROLE_CUSTOMER' | 'ROLE_USER';
@Directive({
  selector: '[appVistaRoles]',
})
export class VistaRolesDirective implements OnInit, OnDestroy {
  @Input('appVistaRoles') roles?: Role[];
  private sub?: Subscription;

  constructor(
    private authService: AuthService,
    private viewContainerRef: ViewContainerRef,
    private templateRef: TemplateRef<any>
  ) {}

  ngOnInit(): void {
      this.sub = this.authService.user$
      .pipe(
        map((user) =>
          Boolean(user && user.roles && this.roles?.includes(user.roles))
        ),
        distinctUntilChanged(),
        tap((hasRole) =>
          hasRole
            ? this.viewContainerRef.createEmbeddedView(this.templateRef)
            : this.viewContainerRef.clear()
        )
      )
      .subscribe(); 
  }

  ngOnDestroy(): void {
       if (this.sub) {
      this.sub.unsubscribe();
    } 
  }
}
