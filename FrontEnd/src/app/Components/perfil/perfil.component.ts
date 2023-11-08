import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { InfoUsuarioComponent } from '../forms/info-usuario/info-usuario.component';
import { AuthService } from 'src/app/auth/services/auth.service';
import { User } from 'src/app/auth/interfaces/user';
import { first } from 'rxjs';
import { UserService } from 'src/app/auth/services/user.service';
import { Sex } from 'src/app/auth/interfaces/sex';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css'],
})
export class PerfilComponent {
  api: string = this._endPointsService.apiUrlUser;
  loading: boolean = false;
  previewImageUrl = '../../../../assets/img/image-placeholder.png';
  _User!: User;
  user$!: User;

  constructor(
    private _endPointsService: EndpointsService,
    public dialog: MatDialog,
    private authService: AuthService,
    private userService: UserService
  ) {
    this._User = <User>this.authService.userValue;
  }

  ngOnInit(): void {
    this.userService
      .getById(this._User.id)
      .pipe(first())
      .subscribe((user) => {
        this.user$ = user;
        if (this.user$ && this.user$.profileImg) {
          this.previewImageUrl = `https://${this.user$.profileImg}`;
        }
      });
  }

  EditUsuario(data: any) {
    const dialogRef = this.dialog.open(InfoUsuarioComponent, {
      width: '500px',
      height: '600px',
      disableClose: false,
      data,
    });
  }
}
