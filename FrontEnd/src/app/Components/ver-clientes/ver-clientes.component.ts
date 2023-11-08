import { MatTableDataSource } from '@angular/material/table';
import { Cliente } from 'src/app/Interface/cliente';
import { Component, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { Router } from '@angular/router';
import { RolComponent } from '../forms/rol/rol.component';
import { MatDialog } from '@angular/material/dialog';





@Component({
  selector: 'app-ver-clientes',
  templateUrl: './ver-clientes.component.html',
  styleUrls: ['./ver-clientes.component.css']
})
export class VerClientesComponent implements OnInit  {
  displayedColumns: string[] = ['nombre', 'edad', 'altura', 'sexo', 'peso','rol',  'acciones'];
  dataSource = new MatTableDataSource<Cliente>();
  role:string = 'customer'
  
  
  @ViewChild(MatPaginator) paginator!: MatPaginator
  @ViewChild(MatSort) sort!: MatSort;
  
  constructor(private _endPointsService:EndpointsService,
    public dialog: MatDialog,
    
    private router: Router
    ) { }
  

  ngOnInit(): void {
    this.obtenerClientes();
    
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

  obtenerClientes() {    
    this._endPointsService.UsersRoles(this.role).subscribe(data => {
      this.dataSource.data = data;
      
    })
  } 
  filtroRol() {
    this.obtenerClientes();
    
  }
  
  
editRol(id: number): void {
  const dialogRef = this.dialog.open(RolComponent, {
    width: '550px',
    disableClose: true,
    data: {id:id }
  });dialogRef.afterClosed().subscribe(result => {
      
    
  setTimeout(() => {
    window.location.reload();
  },1000);  
  });
}
}