import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { EndpointsService } from 'src/app/Services/endpoints.service';
import { MetodosService } from 'src/app/Services/metodos.service';

@Component({
  selector: 'app-rol',
  templateUrl: './rol.component.html',
  styleUrls: ['./rol.component.css']
})
export class RolComponent {form: FormGroup
  

  constructor(
    public dialogRef: MatDialogRef<RolComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public Service: EndpointsService,
    private fb: FormBuilder,
    private _metodoService: MetodosService
  ) {
    this.form = fb.group({
      title:['',Validators.required]
    })
    this.id = data.id;
    
  }
  
  id: number 
 
  ngOnInit(): void {
    
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }
  
  
    Add() {

     
     
      this. Service.cambiarRol( this.id, this.form.value.title).subscribe(() => {
        this._metodoService.mensaje('Nuevo rol asignado', 5);
      })
  }
}