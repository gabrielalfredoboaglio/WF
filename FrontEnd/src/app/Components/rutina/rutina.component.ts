import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { User } from 'src/app/auth/interfaces/user';
import { AuthService } from 'src/app/auth/services/auth.service';


@Component({
  selector: 'app-rutina',
  templateUrl: './rutina.component.html',
  styleUrls: ['./rutina.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class RutinaComponent implements OnInit {
  rutinasLista: any;
  series: any;
  ejerciciosLista: any;
  panelOpenState = false;
  user!:User
api:string = this.datosRutina.apiUrlUser
  constructor(private datosRutina: EndpointsService,
    private authService: AuthService,
    ) {
      this.user = <User>this.authService.userValue
    }
  ngOnInit(): void {
    this.datosRutina.obtenerDatosId(this.user.id, this.api ).subscribe((data) => {
      this.rutinasLista = data.routines;
      this.rutinasLista.sort((a: any, b: any) => {
        if (a.title < b.title) {
          return -1; 
        }
        if (a.title > b.title) {
          return 1;
        }
        return 0;
      });
      this.rutinasLista.forEach((rutina: any) => {
        rutina.exercises.sort((a: any, b: any) => {
          if (a.title < b.title) {
            return -1;
          }
          if (a.title > b.title) {
            return 1;
          }
          return 0;
        });
      });
    });
    this.series = [...Array(8)];
  }

  getRutina(rutina: any) {
    this.ejerciciosLista = rutina;
  }
}
