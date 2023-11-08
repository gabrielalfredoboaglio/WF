import {
  Component,
  ViewChild,
  Input,
  ViewEncapsulation,
  ChangeDetectorRef,
} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Rutina } from 'src/app/Interface/rutina';
import { RutinasComponent } from '../forms/rutinas/rutinas.component';
import { MatDialog } from '@angular/material/dialog';
import { Ejercicio, EjercicioR } from 'src/app/Interface/ejercicio';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { MetodosService } from 'src/app/Services/metodos.service';

@Component({
  selector: 'app-editar-ejercicio',
  templateUrl: './editar-ejercicio.component.html',
  styleUrls: ['./editar-ejercicio.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class EditarEjercicioComponent {
  @Input() dataRutinas: any;
  displayedColumns: string[] = [
    'title',
    'numeroSeries',
    'repetition',
    'type',
    'quantity',
    'unit',
    'acciones',
  ];
  dataEjercicios = new MatTableDataSource<Rutina>();
  ejercicio: any;
  api: string = this.Api.apiUrlRutine;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(
    public dialog: MatDialog,
    private Api: EndpointsService,
    private cdr: ChangeDetectorRef,
    private _metodoService: MetodosService
  ) {}

  ngOnInit(): void {
    this.cargar();
  }
  cargar() {
    this.dataEjercicios = this.dataRutinas.exercises;
  }
  openDialog(id?: number): void {
    const dialogRef = this.dialog.open(RutinasComponent, {
      width: '550px',
      disableClose: true,
      data: { idRutina: this.dataRutinas.id, id: id },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.ejercicio = result;
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    });
  }
  eliminarEjercicio(id: number): void {
    const idEjercicio: object = { exercises: [id] };

    this.Api.borrarEjercicioRutina(this.dataRutinas.id, {
      body: idEjercicio,
    }).subscribe(() => {
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    });
    this._metodoService.mensaje('Ejercicio Eliminado con Exito !', 5);
  }
  eliminarRutina(): void {
    this.Api.borrarItem(this.dataRutinas.id, this.api).subscribe(() => {
      setTimeout(() => {
        window.location.reload();
      }, 1000);
    });
    this._metodoService.mensaje('Rutina Eliminada con Exito !', 5);
  }
}
