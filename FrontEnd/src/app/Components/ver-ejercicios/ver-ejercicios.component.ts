import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Ejercicio } from 'src/app/Interface/ejercicio';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { EjerciciosComponent } from '../forms/ejercicios/ejercicios.component';
import { MetodosService } from 'src/app/Services/metodos.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-ver-ejercicios',
  templateUrl: './ver-ejercicios.component.html',
  styleUrls: ['./ver-ejercicios.component.css']
})
export class VerEjerciciosComponent {
  displayedColumns: string[] = ['nombre','musculos', 'tipo', 'unidad', 'acciones'];
  dataSource = new MatTableDataSource<Ejercicio>();
  api:string = this._endPointsService.apiUrlEjercicio;
  loading:boolean = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;
  
  constructor(private _endPointsService:EndpointsService,
    private _metodos: MetodosService,    
    private router: Router,
    public dialog: MatDialog,
    ) { }

  ngOnInit(): void {
    this.obtenerEjercicios();
    
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    if(this.dataSource.data.length > 0) {
      this.paginator._intl.itemsPerPageLabel = 'Items por pagina'
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  obtenerEjercicios() {  
    this.loading = true;  
    this._endPointsService.obtenerDatos(this.api).subscribe(data => {
      this.loading = false;
      this.dataSource.data = data;
      
    })
  }

  borrarEjercicio(id:number){
    this._endPointsService.borrarItem(id, this.api).subscribe(()=>
    {
      window.location.reload();
      this._metodos.mensaje('Ejercicio eliminado con Exito!',2);
    }, error => {
      Swal.fire({
        icon: 'warning',        
        text: 'No se puede eliminar el ejercicio actualmente.Se encuentra en uso por una rutina.!'        
      })
    });
  }

  addEditEjercicio(id?:number){
    const dialogRef = this.dialog.open(EjerciciosComponent, {
      width:"550px",
      disableClose: true,
      data:{ id:id}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        setTimeout(() => {this.obtenerEjercicios();}, 4000)
        
      }            
    });
  }

}
