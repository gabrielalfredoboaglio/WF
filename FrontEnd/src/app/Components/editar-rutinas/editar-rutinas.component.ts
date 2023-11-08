import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import { RutinasComponent } from '../forms/rutinas/rutinas.component';
import { MatDialog } from '@angular/material/dialog';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { ActivatedRoute } from '@angular/router';
import { NuevaRutinaComponent } from '../forms/nueva-rutina/nueva-rutina.component';
import { MetodosService } from 'src/app/Services/metodos.service';

@Component({
  selector: 'app-editar-rutinas',
  templateUrl: './editar-rutinas.component.html',
  styleUrls: ['./editar-rutinas.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class EditarRutinasComponent implements OnInit {
  infoCliente: any;
  idCliente: number;
  api: string = this.datosRutina.apiUrlUser;
  selected = new FormControl(0);
  planCliente: any;
  rutina: any;
  img!: string;

  constructor(
    private datosRutina: EndpointsService,
    private aRoute: ActivatedRoute,
    public dialog: MatDialog,
    private _metodoService: MetodosService
  ) {
    this.idCliente = Number(this.aRoute.snapshot.paramMap.get('id')); //obtenemos id de url
  }
  ngOnInit(): void {
    this.obtenerRutinas();
    this.cargaImagen();
  }
  //Metodo que hace un get al servidor para traer la info de un cliente por id
  obtenerRutinas() {
    this.datosRutina
      .obtenerDatosId(this.idCliente, this.api)
      .subscribe((data) => {
        this.infoCliente = data;
        this.cargaImagen();
        this.planCliente = data.routines;
        this.planCliente.sort((a: any, b: any) => {
          if (a.title < b.title) {
            return -1;
          }
          if (a.title > b.title) {
            return 1;
          }
          return 0;
        });
        this.planCliente.forEach((rutina: any) => {
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
  }

  AgregarRutina(id?: number): void {
    const dialogRef = this.dialog.open(NuevaRutinaComponent, {
      width: '550px',
      disableClose: true,
      data: { id: id },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.rutina = result;
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    });
  }
  cargaImagen() {
    if (this.infoCliente.profileImg === null) {
      this.img = './assets/img/image-placeholder.png';
    } else {
      this.img = 'https://' + this.infoCliente.profileImg;
    }
  }
}
